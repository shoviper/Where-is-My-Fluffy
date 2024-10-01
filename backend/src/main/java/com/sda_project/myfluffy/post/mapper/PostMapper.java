package com.sda_project.myfluffy.post.mapper;


import com.sda_project.myfluffy.pet.dto.PetDto;
import com.sda_project.myfluffy.pet.mapper.PetMapper;
import com.sda_project.myfluffy.post.dto.PostDto;
import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.mapper.UserMapper;

public class PostMapper {

    public static PostDto mapToPostDto(Post post, PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setTimestamp(post.getTimestamp());
        postDto.setType(post.getType());

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
