// service/PetService.java
package com.sda_project.myfluffy.pet;

import com.sda_project.myfluffy.animal_type.AnimalType;
import com.sda_project.myfluffy.animal_type.AnimalTypeRepository;
import com.sda_project.myfluffy.dto.*;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements IPetService {

    private PetRepository petRepository;
    private UserRepository userRepository;
    private LocationRepository locationRepository;
    private ILocationService iLocationService;
    private AnimalTypeRepository animalTypeRepository;

//    public Pet updatePetStatus(int petId, Status status, OAuth2User oAuth2User) {
//        Pet pet = petRepository.findById(petId).orElseThrow();
//        int id = Integer.parseInt(oAuth2User.getAttribute("id"));
//
//        if (pet.getOwner().getId() != id) {
//            throw new UnauthorizedException("cannot update other users pets");
//        }
//        pet.setStatus(status);
//        return petRepository.save(pet);
//    }

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
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Pet-Owner", "email", email);
        }

        int ownerId = optionalUser.get().getId();

        String animalType = petCreateDto.getAnimalType();
        Optional<AnimalType> optionalAnimalType = animalTypeRepository.findByType(animalType);

        if (optionalAnimalType.isEmpty()) {
            throw new ResourceNotFoundException("Pet-AnimalType", "type", email);
        }

        int animalTypeId = optionalAnimalType.get().getId();

        LocationCreateDto locationCreateDto = new LocationCreateDto();
        locationCreateDto.setAddress(petCreateDto.getLocation());
        Location createdLocation = iLocationService.createLocation(locationCreateDto);

        int locationId = createdLocation.getId();

        Pet pet = PetCreateMapper.mapToPet(petCreateDto, new Pet());

        pet.setOwnerId(ownerId);
        pet.setLocationId(locationId);
        pet.setAnimalTypeId(animalTypeId);

        petRepository.save(pet);

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
     * @return An Optional containing the PetDto details associated with the user if found, or an empty.
     */
    @Override
    public PetDto fetchMyPet(OAuth2User oAuth2User) {
        return null;
    }

    /**
     * Updates the details of an existing pet.
     *
     * @param id         - The ID of the pet to be updated.
     * @param petDto     - The PetDto object containing the updated details of the pet.
     * @param oAuth2User - OAuth2User Object.
     */
    @Override
    public void updatePet(Long id, PetDto petDto, OAuth2User oAuth2User) {

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
        return List.of();
    }
}
