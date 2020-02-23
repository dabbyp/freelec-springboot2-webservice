package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) // run SpringRunner when the Test is beginning not JUnit's
@SpringBootTest // kind of Test, ex) @WebMvcTest
public class PostsRepositoryTest {
    @Autowired //sping bean direct injection
    PostsRepository postsRepository;

    @After // Junit call after whenever finishing
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void writing_save_load(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // insert or update
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("dabbyp@naver.com")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_register(){
        LocalDateTime now = LocalDateTime.of(2020,2,22,0,0,0);
        postsRepository.save(
                Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+",modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
