package com.sda_project.myfluffy.post.service;

import com.sda_project.myfluffy.common.dto.response.PostCreationDto;
import com.sda_project.myfluffy.common.dto.response.PostUpdateDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.utils.enums.PostType;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.pet.repository.PetRepository;
import com.sda_project.myfluffy.post.dto.PostDto;
import com.sda_project.myfluffy.post.mapper.PostMapper;
import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.post.repository.PostRepository;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
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
    public void createPost(PostCreationDto postCreationDto, OAuth2User principal) {
        String email = principal.getAttribute("email");
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        Pet pet = petRepository.findById(postCreationDto.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", String.valueOf(postCreationDto.getPetId())));

        Post post = new Post();
        post.setTitle(postCreationDto.getTitle());
        post.setContent(postCreationDto.getContent());
        post.setType(PostType.valueOf(postCreationDto.getType()));
        post.setPet(pet);
        post.setOwner(owner);

        // Save the post to the database
        postRepository.save(post);
    }

    @Override
    public PostDto fetchPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );

        return PostMapper.mapToPostDto(post, new PostDto());
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
            () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        postRepository.delete(post);
        return true;
    }

    @Override
    public boolean updatePost(int id, PostUpdateDto postUpdateDto) {
            Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
            );

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
