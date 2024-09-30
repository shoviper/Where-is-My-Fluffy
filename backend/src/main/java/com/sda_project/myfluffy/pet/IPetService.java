package com.sda_project.myfluffy.pet;

import com.sda_project.myfluffy.dto.petDto.PetCreateDto;
import com.sda_project.myfluffy.dto.petDto.PetDto;
import com.sda_project.myfluffy.dto.petDto.PetStatusUpdateDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface IPetService {

    /**
     * Creates a new pet with the given PetDto details.
     *
     * @param oAuth2User - The authenticated OAuth2User object representing the currently logged-in user.
     * @param petCreateDto - The PetCreateDto object containing pet creating details.
     */
    void createPet(OAuth2User oAuth2User, PetCreateDto petCreateDto);

    /**
     * Retrieves the details of a pet based on the provided pet ID.
     *
     * @param id - The ID of the pet to be retrieved.
     * @return The PetDto details if found, otherwise empty.
     */
    PetDto fetchPetById(int id);

    /**
     * Retrieves the pet details associated with a specific user identified by their OAuth2 credentials.
     *
     * @param oAuth2User - The authenticated OAuth2User object representing the currently logged-in user.
     * @return A List of PetDto details associated with the user if found, or an empty list if no pets are found.
     */
    List<PetDto> fetchMyPet(OAuth2User oAuth2User);

    /**
     * Updates the details of an existing pet.
     *
     * @param oAuth2User - OAuth2User Object.
     * @param petStatusUpdateDto - The PetStatusUpdateDto object containing the updated details of the pet.
     * @return boolean indicating if the update of Pet details is successful or not
     */
    boolean updatePetStatus(OAuth2User oAuth2User, PetStatusUpdateDto petStatusUpdateDto);

    /**
     * Deletes a pet from the system based on the given pet ID.
     *
     * @param id - The ID of the pet to be deleted.
     */
    void deletePet(Long id);

    /**
     * Retrieves a list of all pets in the system.
     *
     * @return A List of PetDto objects representing all pets.
     */
    List<PetDto> getAllPets();

}
