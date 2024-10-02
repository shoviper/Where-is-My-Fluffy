package com.sda_project.myfluffy.post.controller;

import com.sda_project.myfluffy.common.dto.response.PostCreationDto;
import com.sda_project.myfluffy.common.dto.response.PostUpdateDto;
import com.sda_project.myfluffy.common.dto.response.ResponseDto;
import com.sda_project.myfluffy.common.utils.constants.AppConstants;
import com.sda_project.myfluffy.post.dto.PostDto;
import com.sda_project.myfluffy.post.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class PostController {

    private IPostService iPostService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostCreationDto postCreationDto,
                                                @AuthenticationPrincipal OAuth2User principal) {
            iPostService.createPost(postCreationDto, principal);
            return new ResponseEntity<>("Post created successfully!", HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<PostDto>> fetchAllPosts() {
        List<PostDto> postDtos = iPostService.fetchAllPosts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> fetchPostById(@PathVariable int id) {
        PostDto postDto = iPostService.fetchPostById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postDto);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PostDto>> fetchPostsByOwnerId(@PathVariable int ownerId) {
        List<PostDto> postDtos = iPostService.fetchPostsByOwnerId(ownerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePostById(@PathVariable int id) {
        boolean isDeleted = iPostService.deletePostById(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AppConstants.STATUS_200, AppConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AppConstants.STATUS_417, AppConstants.MESSAGE_417_DELETE));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable int id, @RequestBody PostUpdateDto postUpdateDto) {
        // postUpdateDto.setId(id);  // Ensure the ID is set from the path
        boolean isUpdated = iPostService.updatePost(id, postUpdateDto);
        if (isUpdated) {
            return new ResponseEntity<>("Post updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update post.", HttpStatus.BAD_REQUEST);
    }


}
