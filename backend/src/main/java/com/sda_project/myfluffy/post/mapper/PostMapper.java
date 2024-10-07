package com.sda_project.myfluffy.post.mapper;

import com.sda_project.myfluffy.post.dto.PostDto;
import com.sda_project.myfluffy.post.model.Post;

public class PostMapper {

    public static PostDto mapToPostDto(Post post, PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setTimestamp(post.getTimestamp());
        postDto.setRewardAmount(post.getRewardAmount());
        postDto.setType(post.getType());
        return postDto;
    }

    public static Post mapToPost(PostDto postDto, Post post) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setContent(postDto.getContent());
        post.setTimestamp(postDto.getTimestamp());
        post.setRewardAmount(postDto.getRewardAmount());
        post.setType(postDto.getType());
        return post;
    }

}
