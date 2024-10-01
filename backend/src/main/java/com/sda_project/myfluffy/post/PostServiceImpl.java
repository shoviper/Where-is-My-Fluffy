package com.sda_project.myfluffy.post;

import com.sda_project.myfluffy.dto.PostDto;
import com.sda_project.myfluffy.dto.PostUpdateDto;
import com.sda_project.myfluffy.enums.PostType;
import com.sda_project.myfluffy.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.mapper.PostMapper;
import com.sda_project.myfluffy.pet.Pet;
import com.sda_project.myfluffy.pet.PetRepository;
import com.sda_project.myfluffy.user.UserRepository;
import com.sda_project.myfluffy.user.User;
import com.sda_project.myfluffy.dto.PostCreationDto;
import lombok.AllArgsConstructor;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class PostServiceImpl implements IPostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private PetRepository petRepository;

    @Override
    public boolean createPost(PostCreationDto postCreationDto, OAuth2User principal) {
        // Get the current authenticated user (owner of the post)
        String email = principal.getAttribute("email");
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        // Get the Pet entity based on petId
        Pet pet = petRepository.findById(postCreationDto.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", String.valueOf(postCreationDto.getPetId())));

        // Create a new Post entity
        Post post = new Post();
        post.setTitle(postCreationDto.getTitle());
        post.setContent(postCreationDto.getContent());
        post.setType(PostType.valueOf(postCreationDto.getType()));  // Assumes PostType is enum
        post.setPet(pet);
        post.setOwner(owner);

        // Save the post to the database
        postRepository.save(post);

        return true;
    }


    @Override
    public PostDto fetchPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        PostDto mapper = PostMapper.mapToPostDto(post, new PostDto());
        return mapper;
    }

    @Override
    public List<PostDto> fetchPostsByOwnerId(int ownerId) {
        return postRepository.findByOwnerId(ownerId).stream()
                .map(post -> PostMapper.mapToPostDto(post, new PostDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deletePostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(
            // public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
            //     super(String.format("%s not found with the given input data %s: '%s'", resourceName, fieldName, fieldValue));
            // }
            () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        postRepository.delete(post);
        return true;
    }

    @Override
    public boolean updatePost(int id,PostUpdateDto postUpdateDto) {
            // Fetch the existing post by ID
            Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
            );

            // Update the fields from the DTO
            if (postUpdateDto.getTitle() != null) {
                post.setTitle(postUpdateDto.getTitle());
            }

            if (postUpdateDto.getContent() != null) {
                post.setContent(postUpdateDto.getContent());
            }

            if (postUpdateDto.getType() != null) {
                post.setType(PostType.valueOf(postUpdateDto.getType()));
            }

            if (postUpdateDto.getPetId() > 0) {
                Pet pet = petRepository.findById(postUpdateDto.getPetId()).orElseThrow(
                    () -> new ResourceNotFoundException("Pet", "id", String.valueOf(postUpdateDto.getPetId()))
                );
                post.setPet(pet);
            }

            // Save the updated post
            postRepository.save(post);

            return true;
        }

    @Override
    public List<PostDto> fetchAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostMapper.mapToPostDto(post, new PostDto()))
                .collect(Collectors.toList());
    }
}
