package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.config.ApplicationProperties;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import com.jojoldu.book.springboot.config.ApplicationProperties;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //@Autowired
    //ApplicationProperties env;

    @Transactional // transaction enabled, rollback/commit
    public String save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId().toString();
    }

    @Transactional // transaction enabled, rollback/commit
    public String update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("There is no user. id=" + id));
        // dirty checking, update table when this transaction is done
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id.toString();
    }

    @Transactional // transaction enabled, rollback/commit
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no content. id=" + id));
        // dirty checking, update table when this transaction is done
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id){
        //System.out.println("key1 : " + env.getKey());
        //System.out.println("key2 : " + ApplicationProperties.strKey);

        Posts entity = postsRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("There is no user. id=" + id));
        return new PostsResponseDto(entity);
    }

    //@Transactional(readOnly = true) // 조회속도 개선 (CUD 기능이 없는 경우 사용권장)
    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
