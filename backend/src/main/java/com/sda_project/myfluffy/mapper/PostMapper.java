package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.PetDto;
import com.sda_project.myfluffy.dto.PostDto;
import com.sda_project.myfluffy.dto.UserDto;
import com.sda_project.myfluffy.post.Post;

public class PostMapper {

    public static PostDto mapToPostDto(Post post, PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setTimestamp(post.getTimestamp());
        postDto.setType(post.getType());
        
        // Assuming you have mappers for User and Pet to convert them to DTOs
        if (post.getOwner() != null) {
            postDto.setUserDto(UserMapper.mapToUserDto(post.getOwner(), new UserDto()));
        }
        
        if (post.getPet() != null) {
            postDto.setPetDto(PetMapper.mapToPetDto(post.getPet(), new PetDto()));
        }

        return postDto;
    }

    public static Post mapToPost(PostDto postDto, Post post) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

}
