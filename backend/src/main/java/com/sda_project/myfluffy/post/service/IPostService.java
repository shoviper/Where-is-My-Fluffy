package com.sda_project.myfluffy.post.service;

import com.sda_project.myfluffy.post.dto.PostCreationDto;

import java.util.List;

import com.sda_project.myfluffy.post.dto.PostUpdateDto;
import com.sda_project.myfluffy.post.dto.PostDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IPostService {

    /**
     * @param postCreationDto - Post data transfer object
     * @param oAuth2User       - Current authenticated user
     */
    void createPost(PostCreationDto postCreationDto, OAuth2User oAuth2User);

    /**
     * @param page the page number to retrieve (zero-based index).
     * @param size the number of posts per page.
     * @param sortBy the field by which to sort the results (e.g., "title", "timestamp").
     * @param sortDir the direction of sorting ("asc" for ascending, "desc" for descending).
     * @return List of all posts
     */
    List<PostDto> fetchAllPosts(int page, int size, String sortBy, String sortDir);

    /**
     * @param id - Post ID
     * @return PostDto containing post details
     */
    PostDto fetchPostById(int id);

    /**
     * @param oAuth2User - OAuth2User Object.
     * @return List of posts related to a logged-in User.
     */
    List<PostDto> fetchMyPost(OAuth2User oAuth2User);

    /**
     * @param ownerId - Owner (User) ID
     * @param page the page number to retrieve (zero-based index).
     * @param size the number of posts per page.
     * @param sortBy the field by which to sort the results (e.g., "title", "timestamp").
     * @param sortDir the direction of sorting ("asc" for ascending, "desc" for descending).
     * @return List of posts related to a specific owner
     */
    List<PostDto> fetchPostsByOwnerId(int ownerId, int page, int size, String sortBy, String sortDir);

    /**
     * @param id - Post ID
     * @param postUpdateDto - Post data transfer object
     * @return boolean indicating if the post was successfully updated
     */
    boolean updatePost(int id, PostUpdateDto postUpdateDto);

    /**
     * @param id - Post ID
     * @return boolean indicating if the post was successfully deleted
     */
    boolean deletePostById(int id);

}
