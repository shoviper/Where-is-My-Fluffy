package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.postDto.PostDto;
import com.sda_project.myfluffy.post.Post;

public class PostMapper {

    public static PostDto mapToPostDto(Post post, PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }
    public static Post mapToPost(PostDto postDto, Post post) {
        post.setId(post.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

}
