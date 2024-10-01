package com.sda_project.myfluffy.post.service;

import com.sda_project.myfluffy.common.dto.response.PostCreationDto;

import java.util.List;

import com.sda_project.myfluffy.common.dto.response.PostUpdateDto;
import com.sda_project.myfluffy.post.dto.PostDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IPostService {

    /**
     * @param postCreationDto - Post data transfer object
     * @param principal       - Current authenticated user
     */
    void createPost(PostCreationDto postCreationDto, OAuth2User principal);
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
    public boolean updatePost(int id, PostUpdateDto postCreationDto);
}
