package com.sda_project.myfluffy.post.service;

import com.sda_project.myfluffy.common.observers.events.post.PostCreatedEvent;
import com.sda_project.myfluffy.pet.mapper.PetCreateMapper;
import com.sda_project.myfluffy.post.dto.PostCreationDto;
import com.sda_project.myfluffy.post.dto.PostUpdateDto;
import com.sda_project.myfluffy.common.exception.ResourceNotFoundException;
import com.sda_project.myfluffy.common.exception.UnauthorizedException;
import com.sda_project.myfluffy.common.utils.enums.PostType;
import com.sda_project.myfluffy.geolocation.dto.LocationDto;
import com.sda_project.myfluffy.geolocation.service.ILocationService;
import com.sda_project.myfluffy.pet.dto.PetDto;
import com.sda_project.myfluffy.pet.mapper.PetMapper;
import com.sda_project.myfluffy.pet.model.Pet;
import com.sda_project.myfluffy.pet.repository.PetRepository;
import com.sda_project.myfluffy.post.dto.PostDto;
import com.sda_project.myfluffy.post.mapper.PostMapper;
import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.post.repository.PostRepository;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.mapper.UserMapper;
import com.sda_project.myfluffy.user.model.User;
import com.sda_project.myfluffy.user.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements IPostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private PetRepository petRepository;
    private ILocationService iLocationService;
    private ApplicationEventPublisher eventPublisher;

    /**
     * @param postCreationDto - Post data transfer object
     * @param oAuth2User       - Current authenticated user
     */
    @Override
    @Transactional
    public void createPost(PostCreationDto postCreationDto, OAuth2User oAuth2User) {
        User owner = getAuthenticatedUser(oAuth2User);

        Pet pet = petRepository.findById(postCreationDto.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", String.valueOf(postCreationDto.getPetId())));

        if (!pet.getPetOwner().equals(owner)) {
            throw new UnauthorizedException("UNAUTHORIZED. You do not own this pet!");
        }

        Post post = new Post();
        post.setTitle(postCreationDto.getTitle());
        post.setContent(postCreationDto.getContent());
        post.setType(postCreationDto.getType());
        post.setPet(pet);
        post.setPostOwner(owner);

        postRepository.save(post);

        eventPublisher.publishEvent(new PostCreatedEvent(this, post));

    }

    private User getAuthenticatedUser(OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            throw new UnauthorizedException("User must be authenticated");
        }
        String email = oAuth2User.getAttribute("email");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    /**
     * @param id - Post ID
     * @return PostDto containing post details
     */
    @Override
    public PostDto fetchPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        return mapPostToDto(post);
    }

    /**
     * @return List of all posts
     */
    @Override
    public List<PostDto> fetchAllPosts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postsPage = postRepository.findAll(pageable);

        return postsPage.getContent().stream()
                .map(this::mapPostToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param oAuth2User - OAuth2User Object.
     * @return List of posts related to a logged-in User.
     */
    @Override
    public List<PostDto> fetchMyPost(OAuth2User oAuth2User) {
        User owner = getAuthenticatedUser(oAuth2User);
        List<Post> posts = owner.getMyPosts();
        return posts.stream()
                .map(this::mapPostToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param ownerId - Owner (User) ID
     * @return List of posts related to a specific owner
     */
    @Override
    public List<PostDto> fetchPostsByOwnerId(int ownerId, int page, int size, String sortBy, String sortDir) {
        User owner = userRepository.findById(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(ownerId))
        );

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postsPage = postRepository.findByPostOwner(owner, pageable);

        return postsPage.getContent().stream()
                .map(this::mapPostToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param id - Post ID
     * @param postUpdateDto - Post data transfer object
     * @return boolean indicating if the post was successfully updated
     */
    @Override
    @Transactional
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
            post.setType(postUpdateDto.getType());
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

    /**
     * @param id - Post ID
     * @return boolean indicating if the post was successfully deleted
     */
    @Override
    @Transactional
    public boolean deletePostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        post.setPet(null);
        postRepository.delete(post);
        return true;
    }

    private PostDto mapPostToDto(Post post) {
        UserDto ownerDto = UserMapper.mapToUserDto(post.getPostOwner(), new UserDto());

        Pet pet = post.getPet();
        PetDto petDto = PetMapper.mapToPetDto(post.getPet(), new PetDto());

        UserDto petOwnerDto = UserMapper.mapToUserDto(pet.getPetOwner(), new UserDto());
        LocationDto locationDto = iLocationService.fetchLocationById(pet.getLocation().getId());
        String animalType = pet.getAnimalType().getType();
        UserDto founderDto = Optional.ofNullable(pet.getFounder())
                .map(founder -> UserMapper.mapToUserDto(founder, new UserDto()))
                .orElse(null);

        petDto.setUser(petOwnerDto);
        petDto.setLocation(locationDto);
        petDto.setAnimalType(animalType);
        petDto.setFounder(founderDto);

        PostDto postDto = PostMapper.mapToPostDto(post, new PostDto());
        postDto.setUser(ownerDto);
        postDto.setPet(petDto);

        return postDto;
    }
}
