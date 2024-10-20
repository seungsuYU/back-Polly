package com.sparta.spring_prepare;

import com.sparta.spring_prepare.Entity.PostList;
import com.sparta.spring_prepare.Repository.PostListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class testlist {

    @Autowired
    private PostListRepository postListRepository;

    @Test
    public void createPostTest() {
        // given
        PostList post = new PostList();
        post.setTitle("다섯번째");
        post.setContent("나");
        post.setAuthor("나");

        // when
        PostList savedPost = postListRepository.save(post);

        // then
        assertThat(savedPost.getId()).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("다섯번째");
    }

    @Test
    public void findPostByIdTest() {
        // given
        PostList post = new PostList();
        post.setTitle("테스트 게시물");
        post.setContent("테스트 내용");
        post.setAuthor("작성자1");
        postListRepository.save(post);

        // when
        Optional<PostList> foundPost = postListRepository.findById(post.getId());

        // then
        assertThat(foundPost.isPresent()).isTrue();
        assertThat(foundPost.get().getTitle()).isEqualTo("테스트 게시물");
    }

    @Test
    public void updatePostTest() {
        // given
        PostList post = new PostList();
        post.setTitle("수정 전 제목");
        post.setContent("수정 전 내용");
        post.setAuthor("수정 전");
        postListRepository.save(post);

        // when
        post.setTitle("수정 후 제목");
        post.setContent("수정 진행중");
        PostList updatedPost = postListRepository.save(post);

        // then
        assertThat(updatedPost.getTitle()).isEqualTo("수정 후 제목");
    }

    @Test
    public void deletePostTest() {
        // given
        PostList post = new PostList();
        post.setTitle("삭제할 게시물");
        post.setContent("삭제할 내용");
        post.setAuthor("작성자1");
        postListRepository.save(post);

        // when
        postListRepository.deleteById(post.getId());

        // then
        Optional<PostList> deletedPost = postListRepository.findById(post.getId());
        assertThat(deletedPost.isEmpty()).isTrue(); // 삭제되었는지 확인
    }

    @Test
    public void findAllPostsTest() {
        // given
        PostList post1 = new PostList();
        post1.setTitle("게시물1");
        post1.setContent("내용1");
        post1.setAuthor("작성자1");

        PostList post2 = new PostList();
        post2.setTitle("게시물2");
        post2.setContent("내용2");
        post2.setAuthor("작성자2");

        postListRepository.save(post1);
        postListRepository.save(post2);

        // when
        List<PostList> posts = postListRepository.findAll();

        // then
        assertThat(posts.size()).isEqualTo(2);
    }
}
