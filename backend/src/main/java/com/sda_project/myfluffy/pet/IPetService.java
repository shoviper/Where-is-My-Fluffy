package com.sda_project.myfluffy.pet;

import com.sda_project.myfluffy.dto.PetCreateDto;
import com.sda_project.myfluffy.dto.PetDto;
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
     * @return The PetDto details associated with the user if found, or an empty.
     */
    PetDto fetchMyPet(OAuth2User oAuth2User);

    /**
     * Updates the details of an existing pet.
     *
     * @param id - The ID of the pet to be updated.
     * @param petDto - The PetDto object containing the updated details of the pet.
     * @param oAuth2User - OAuth2User Object.
     */
    void updatePet(Long id, PetDto petDto, OAuth2User oAuth2User);

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
