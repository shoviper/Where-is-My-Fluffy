package com.sda_project.myfluffy.post;

import com.sda_project.myfluffy.dto.PostCreationDto;
import com.sda_project.myfluffy.dto.PostDto;
import com.sda_project.myfluffy.dto.PostUpdateDto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;

public interface IPostService {

    /**
     * @param postCreationDto - Post data transfer object
     * @param principal - Current authenticated user
     * @return boolean indicating if the creation of the post was successful
     */
    boolean createPost(PostCreationDto postCreationDto, OAuth2User principal);
    /**
     * @return List of all posts
     */
    List<PostDto> fetchAllPosts();

    /**
     * @param id - Post ID
     * @return PostDto containing post details
     */
    PostDto fetchPostById(int id);

    /**
     * @param ownerId - Owner (User) ID
     * @return List of posts related to a specific owner
     */
    List<PostDto> fetchPostsByOwnerId(int ownerId);

    /**
     * @param id - Post ID
     * @return boolean indicating if the post was successfully deleted
     */
    boolean deletePostById(int id);

    /**
     * @param id - Post ID
     * @param postCreationDto - Post data transfer object
     * @return boolean indicating if the post was successfully updated
     */
    public boolean updatePost(int id,PostUpdateDto postCreationDto);
}
