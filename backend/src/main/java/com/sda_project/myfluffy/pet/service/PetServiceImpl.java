// service/PetService.java
package com.sda_project.myfluffy.pet.service;

import com.sda_project.myfluffy.animal_type.model.AnimalType;
import com.sda_project.myfluffy.animal_type.repository.AnimalTypeRepository;
import com.sda_project.myfluffy.common.observers.events.pet.PetFounderChangeEvent;
import com.sda_project.myfluffy.geolocation.dto.LocationCreateDto;
import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.pet.dto.*;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.common.utils.enums.Status;
import com.sda_project.myfluffy.common.observers.events.pet.PetStatusChangeEvent;
import com.sda_project.myfluffy.common.exception.InvalidStatusException;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.exception.UnauthorizedException;
import com.sda_project.myfluffy.geolocation.service.ILocationService;
import com.sda_project.myfluffy.geolocation.model.Location;
import com.sda_project.myfluffy.pet.mapper.PetCreateMapper;
import com.sda_project.myfluffy.pet.mapper.PetCreateResponseMapper;
import com.sda_project.myfluffy.pet.mapper.PetMapper;
import com.sda_project.myfluffy.pet.repository.PetImageRepository;
import com.sda_project.myfluffy.pet.repository.PetRepository;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.pet.model.PetImage;
import com.sda_project.myfluffy.user.mapper.UserMapper;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetServiceImpl implements IPetService {

    private PetRepository petRepository;
    private UserRepository userRepository;
    private ILocationService iLocationService;
    private AnimalTypeRepository animalTypeRepository;
    private ApplicationEventPublisher eventPublisher;
    private PetImageRepository petImageRepository;

    /**
     * Creates a new pet with the given PetDto details.
     *
     * @param oAuth2User   - The authenticated OAuth2User object representing the
     *                     currently logged-in user.
     * @param petCreateDto - The PetCreateDto object containing pet creating
     *                     details.
     * @return petDto - PetDto object
     */
    @Override
    @Transactional
    public PetCreateResponseDto createPet(OAuth2User oAuth2User, PetCreateDto petCreateDto) {
        User user = getAuthenticatedUser(oAuth2User);
        AnimalType animalType = animalTypeRepository.findById(petCreateDto.getAnimalType())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Pet-AnimalType", "type", petCreateDto.getAnimalType()));
        Location location = createLocationForPet(petCreateDto.getLocation());

        Pet pet = PetCreateMapper.mapToPet(petCreateDto, new Pet());
        pet.setPetOwner(user);
        pet.setLocation(location);
        pet.setAnimalType(animalType);
        pet.setStatus(Status.MISSING);

        petRepository.save(pet);

        return PetCreateResponseMapper.mapToPetCreateResponseDto(pet, new PetCreateResponseDto());
    }

    private User getAuthenticatedUser(OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("User must be authenticated");
        }
        String email = oAuth2User.getAttribute("email");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    private Location createLocationForPet(String address) {
        LocationCreateDto locationCreateDto = new LocationCreateDto();
        locationCreateDto.setAddress(address);
        return iLocationService.createLocation(locationCreateDto);
    }

    /**
     * Retrieves the details of a pet based on the provided pet ID.
     *
     * @param id - The ID of the pet to be retrieved.
     * @return An Optional containing the PetDto details if found, otherwise empty.
     */
    @Override
    public PetDto fetchPetById(int id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", Integer.toString(id)));
        return mapPetToDto(pet);
    }

    /**
     * Retrieves the pet details associated with a specific user identified by their
     * OAuth2 credentials.
     *
     * @param oAuth2User - The authenticated OAuth2User object representing the
     *                   currently logged-in user.
     * @return A List of PetDto details associated with the user if found, or an
     *         empty list if no pets are found.
     */
    @Override
    public List<PetDto> fetchMyPet(OAuth2User oAuth2User) {
        User owner = getAuthenticatedUser(oAuth2User);
        List<Pet> pets = owner.getMyPets();
        return pets.stream()
                .map(this::mapPetToDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates the details of an existing pet.
     *
     * @param oAuth2User         - OAuth2User Object.
     * @param petStatusUpdateDto - The PetStatusUpdateDto object containing the
     *                           updated details of the pet.
     * @return boolean indicating if the update of Pet details is successful or not
     */
    @Override
    @Transactional
    public boolean updatePetStatus(OAuth2User oAuth2User, PetStatusUpdateDto petStatusUpdateDto) {
        User user = getAuthenticatedUser(oAuth2User);
        Pet pet = findOwnedPet(petStatusUpdateDto.getId(), user);
        updateStatusIfValid(pet, petStatusUpdateDto.getStatus());
        return true;
    }

    private void updateStatusIfValid(Pet pet, Status newStatus) {
        if (newStatus != Status.FOUND) {
            throw new InvalidStatusException("Invalid status update");
        }
        pet.setStatus(newStatus);
        pet.setFounder(null);
        petRepository.save(pet);

        eventPublisher.publishEvent(new PetStatusChangeEvent(this, pet));
    }

    /**
     * Updates the details of an existing pet.
     *
     * @param oAuth2User          - OAuth2User Object.
     * @param petFounderUpdateDto - PetFounderUpdateDto Object.
     * @return boolean indicating if the update of Pet details is successful or not
     */
    @Override
    @Transactional
    public boolean addFounder(OAuth2User oAuth2User, PetFounderUpdateDto petFounderUpdateDto) {
        User user = getAuthenticatedUser(oAuth2User);
        Pet pet = findOwnedPet(petFounderUpdateDto.getPetId(), user);

        if (pet.getStatus() != Status.FOUND) {
            throw new InvalidStatusException("Pet must be in FOUND status to assign a founder.");
        }

        User founder = userRepository.findById(petFounderUpdateDto.getFounderId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id",
                        Integer.toString(petFounderUpdateDto.getFounderId())));

        pet.setFounder(founder);
        petRepository.save(pet);

        eventPublisher.publishEvent(new PetFounderChangeEvent(this, pet));

        return true;
    }

    private Pet findOwnedPet(int petId, User owner) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", Integer.toString(petId)));
        if (!pet.getPetOwner().equals(owner)) {
            throw new UnauthorizedException("You do not own this pet.");
        }
        return pet;
    }

    /**
     * Deletes a pet from the system based on the given pet ID.
     *
     * @param id - The ID of the pet to be deleted.
     */
    @Override
    @Transactional
    public boolean deletePet(int id) {
        Pet pet = petRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pet", "id", Integer.toString(id)));
        petRepository.deleteById(pet.getId());
        return true;
    }

    /**
     * Retrieves a list of all pets in the system.
     *
     * @return A List of PetDto objects representing all pets.
     */
    @Override
    public List<PetDto> getAllPets() {
        return petRepository.findAll().stream()
                .map(this::mapPetToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PetDto updatePetImageBase64(int petId, String base64Image) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", Integer.toString(petId)));

        PetImage petImage = new PetImage();
        petImage.setImageBase64(base64Image);
        PetImage createdPetImage = petImageRepository.save(petImage);

        pet.setPetImage(createdPetImage);
        petRepository.save(pet);

        return PetMapper.mapToPetDto(pet, new PetDto());
    }

    private PetDto mapPetToDto(Pet pet) {
        UserDto ownerDto = UserMapper.mapToUserDto(pet.getPetOwner(), new UserDto());
        LocationDto locationDto = iLocationService.fetchLocationById(pet.getLocation().getId());
        String animalType = pet.getAnimalType().getType();
        UserDto founderDto = Optional.ofNullable(pet.getFounder())
                .map(founder -> UserMapper.mapToUserDto(founder, new UserDto()))
                .orElse(null);
        Optional<PetImage> petImage = petImageRepository.findById(pet.getPetImage().getId());

        PetDto petDto = PetMapper.mapToPetDto(pet, new PetDto());
        petDto.setUser(ownerDto);
        petDto.setLocation(locationDto);
        petDto.setAnimalType(animalType);
        petDto.setFounder(founderDto);
        petImage.ifPresent(image -> petDto.setImage(image.getImageBase64()));
        return petDto;
    }

}
