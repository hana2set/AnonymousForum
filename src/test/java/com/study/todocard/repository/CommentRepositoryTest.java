package com.study.todocard.repository;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRoleEnum;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //실제 DB 테스트
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Card card1;
    private Card card2;


    @BeforeEach //TODO beforeAll로 하려면?
    void setUp() {
        CommentRequestDto requestDto1 = new CommentRequestDto();
        requestDto1.setContents("첫번째 코멘트");

        CommentRequestDto requestDto2 = new CommentRequestDto();
        requestDto2.setContents("두번째 코멘트");

        User user = new User("robi", "12341234", "test@test.com", UserRoleEnum.USER);

        CardRequestDto cardRequestDto = new CardRequestDto();
        cardRequestDto.setTitle("card_title");

        card1 = new Card(cardRequestDto, user);

        entityManager.persist(user);
        entityManager.persist(card1);

        commentRepository.save(new Comment(requestDto1, card1, user));
        commentRepository.save(new Comment(requestDto2, card1, user));

        cardRequestDto = new CardRequestDto();
        cardRequestDto.setTitle("card_title");
        card2 = new Card(cardRequestDto, user);
        entityManager.persist(card2);

    }

    @Test
    @DisplayName("댓글 작성일 순 정렬 조회")
    void test1() {
        // given
        // when
        List<Comment> comments1 = commentRepository.findAllByCardOrderByCreatedAtDesc(card1);
        List<Comment> comments2 = commentRepository.findAllByCardOrderByCreatedAtDesc(card2);

        //then
        assertEquals(comments1.isEmpty(), false);
        assertEquals(comments2.isEmpty(), true);
    }
}