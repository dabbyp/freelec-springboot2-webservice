package com.jojoldu.book.springboot.web;

import lombok.RequiredArgsConstructor;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public String save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto).toString();
    }

}
