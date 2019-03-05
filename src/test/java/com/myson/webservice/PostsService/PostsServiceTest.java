package com.myson.webservice.PostsService;

import com.myson.webservice.domain.Posts;
import com.myson.webservice.domain.PostsRepository;
import com.myson.webservice.dto.PostsSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다() {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("pg-sh@naver.com")
                .content("테스트 본문")
                .title(("테스트 타이틀"))
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}