// service/PetService.java
package com.sda_project.myfluffy.pet;

import com.sda_project.myfluffy.animal_type.AnimalType;
import com.sda_project.myfluffy.animal_type.AnimalTypeRepository;
import com.sda_project.myfluffy.dto.locationDto.LocationCreateDto;
import com.sda_project.myfluffy.dto.locationDto.LocationDto;
import com.sda_project.myfluffy.dto.petDto.PetCreateDto;
import com.sda_project.myfluffy.dto.petDto.PetDto;
import com.sda_project.myfluffy.dto.petDto.PetStatusUpdateDto;
import com.sda_project.myfluffy.dto.userDto.UserDto;
import com.sda_project.myfluffy.enums.Status;
import com.sda_project.myfluffy.exception.InvalidStatusException;
import com.sda_project.myfluffy.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.exception.UnauthorizedException;
import com.sda_project.myfluffy.geolocation.ILocationService;
import com.sda_project.myfluffy.geolocation.Location;
import com.sda_project.myfluffy.geolocation.LocationRepository;
import com.sda_project.myfluffy.mapper.*;
import com.sda_project.myfluffy.user.User;
import com.sda_project.myfluffy.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Service
@AllArgsConstructor
public class PetServiceImpl implements IPetService {

    private PetRepository petRepository;
    private UserRepository userRepository;
    private LocationRepository locationRepository;
    private ILocationService iLocationService;
    private AnimalTypeRepository animalTypeRepository;

    /**
     * Creates a new pet with the given PetDto details.
     *
     * @param oAuth2User - The authenticated OAuth2User object representing the currently logged-in user.
     * @param petCreateDto - The PetCreateDto object containing pet creating details.
     */
    @Override
    public void createPet(OAuth2User oAuth2User, PetCreateDto petCreateDto) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("User must be authenticated to create a pet");
        }

        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Pet-Owner", "email", email));

        AnimalType animalType = animalTypeRepository.findByType(petCreateDto.getAnimalType())
                .orElseThrow(() -> new ResourceNotFoundException("Pet-AnimalType", "type", petCreateDto.getAnimalType()));

        Location createdLocation = createLocationForPet(petCreateDto.getLocation());

        Pet pet = PetCreateMapper.mapToPet(petCreateDto, new Pet());
        pet.setOwnerId(user.getId());
        pet.setLocationId(createdLocation.getId());
        pet.setAnimalTypeId(animalType.getId());

        petRepository.save(pet);
    }

    /**
     * Creates a Location entity for a pet from the provided address.
     *
     * @param address - The address where the pet is located.
     * @return The created Location entity.
     */
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
        Pet pet = petRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pet", "id", Integer.toString(id))
        );

        User user = userRepository.findById(pet.getOwnerId()).orElseThrow(
                () -> new ResourceNotFoundException("Pet-Owner", "id", Integer.toString(pet.getOwnerId()))
        );
        UserDto fetchedUser = UserMapper.mapToUserDto(user, new UserDto());

        Location location = locationRepository.findById(pet.getLocationId()).orElseThrow(
                () -> new ResourceNotFoundException("Pet-Location", "id", Integer.toString(pet.getLocationId()))
        );
        LocationDto fetchedLocation = iLocationService.fetchLocationById(location.getId());

        AnimalType animalType = animalTypeRepository.findById(pet.getAnimalTypeId()).orElseThrow(
                () -> new ResourceNotFoundException("Pet-AnimalType", "id", Integer.toString(pet.getAnimalTypeId()))
        );
        String fetchAnimalType = animalType.getType();

        PetDto petDto = PetMapper.mapToPetDto(pet, new PetDto());

        petDto.setUserDto(fetchedUser);
        petDto.setLocationDto(fetchedLocation);
        petDto.setAnimalType(fetchAnimalType);

        return petDto;
    }

    /**
     * Retrieves the pet details associated with a specific user identified by their OAuth2 credentials.
     *
     * @param oAuth2User - The authenticated OAuth2User object representing the currently logged-in user.
     * @return A List of PetDto details associated with the user if found, or an empty list if no pets are found.
     */
    @Override
    public List<PetDto> fetchMyPet(OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("Unauthorized, Please login to get access.");
        }

        String userEmail = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", userEmail)
        );

        List<Pet> pets = petRepository.findAllByOwnerId(user.getId());

        if (pets.isEmpty()) {
            return Collections.emptyList();
        }

        List<PetDto> petDtos = new ArrayList<>();

        for (Pet pet : pets) {
            UserDto fetchedUser = UserMapper.mapToUserDto(user, new UserDto());

            Location location = locationRepository.findById(pet.getLocationId()).orElseThrow(
                    () -> new ResourceNotFoundException("Pet-Location", "id", Integer.toString(pet.getLocationId()))
            );
            LocationDto fetchedLocation = iLocationService.fetchLocationById(location.getId());

            AnimalType animalType = animalTypeRepository.findById(pet.getAnimalTypeId()).orElseThrow(
                    () -> new ResourceNotFoundException("Pet-AnimalType", "id", Integer.toString(pet.getAnimalTypeId()))
            );
            String fetchAnimalType = animalType.getType();

            PetDto petDto = PetMapper.mapToPetDto(pet, new PetDto());
            petDto.setUserDto(fetchedUser);
            petDto.setLocationDto(fetchedLocation);
            petDto.setAnimalType(fetchAnimalType);

            petDtos.add(petDto);
        }

        return petDtos;
    }

    /**
     * Updates the details of an existing pet.
     *
     * @param oAuth2User - OAuth2User Object.
     * @param petStatusUpdateDto - The PetStatusUpdateDto object containing the updated details of the pet.
     * @return boolean indicating if the update of Pet details is successful or not
     */
    @Override
    public boolean updatePetStatus(OAuth2User oAuth2User, PetStatusUpdateDto petStatusUpdateDto) {
        boolean isUpdated = false;

        if (oAuth2User == null) {
            throw new UnauthorizedException("Unauthorized, Please login to get access.");
        }

        String userEmail = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", userEmail)
        );

        Pet pet = petRepository.findById(petStatusUpdateDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Pet", "id", Integer.toString(petStatusUpdateDto.getId()))
        );

        if (pet.getOwnerId() != user.getId()) {
            throw new UnauthorizedException("Unauthorized, You do not own this pet.");
        }

        pet.setStatus(petStatusUpdateDto.getStatus());
        petRepository.save(pet);

        Status newPetStatus = petStatusUpdateDto.getStatus();

        if (newPetStatus != null) {
            pet.setStatus(newPetStatus);
            petRepository.save(pet);
            isUpdated = true;
        } else {
            throw new InvalidStatusException("Status need to match the ENUM type and can not be null.");
        }

        return isUpdated;
    }

    /**
     * Deletes a pet from the system based on the given pet ID.
     *
     * @param id - The ID of the pet to be deleted.
     */
    @Override
    public void deletePet(Long id) {

    }

    /**
     * Retrieves a list of all pets in the system.
     *
     * @return A List of PetDto objects representing all pets.
     */
    @Override
    public List<PetDto> getAllPets() {
        List<Pet> pets = petRepository.findAll();

        if (pets.isEmpty()) {
            return Collections.emptyList();
        }

        List<PetDto> petDtos = new ArrayList<>();
        for (Pet pet : pets) {
            User user = userRepository.findById(pet.getOwnerId()).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", Integer.toString(pet.getOwnerId()))
            );
            UserDto fetchedUser = UserMapper.mapToUserDto(user, new UserDto());

            Location location = locationRepository.findById(pet.getLocationId()).orElseThrow(
                    () -> new ResourceNotFoundException("Location", "id", Integer.toString(pet.getLocationId()))
            );
            LocationDto fetchedLocation = LocationMapper.mapToLocationDto(location, new LocationDto());

            AnimalType animalType = animalTypeRepository.findById(pet.getAnimalTypeId()).orElseThrow(
                    () -> new ResourceNotFoundException("AnimalType", "id", Integer.toString(pet.getAnimalTypeId()))
            );
            String fetchAnimalType = animalType.getType();

            PetDto petDto = PetMapper.mapToPetDto(pet, new PetDto());
            petDto.setUserDto(fetchedUser);
            petDto.setLocationDto(fetchedLocation);
            petDto.setAnimalType(fetchAnimalType);

            petDtos.add(petDto);
        }

        return petDtos;
    }
}
