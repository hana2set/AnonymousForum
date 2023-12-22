package com.study.todocard.service;

import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.dto.CommentResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import com.study.todocard.exception.BusinessException;
import com.study.todocard.repository.CardRepository;
import com.study.todocard.repository.CommentRepository;
import com.study.todocard.util.CommentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) // @Mock 사용을 위해 설정합니다.
class CommentServiceTest {

    @Mock
    CommentRepository commentRepository;

    @Mock
    CardRepository cardRepository;

    @Autowired
    CommentConverter commentConverter;

    @Test
    @DisplayName("코멘트 수정 성공")
    void test2() {
        // given
        Long CommentId = 200L;
        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setContents("테스트 수정 성공");

        Card card = new Card();

        User user = new User();
        user.setId(100L);

        Comment comment = commentConverter.getComment(requestDto, card, user);

        CommentService commentService = new CommentService(commentRepository, cardRepository, commentConverter);

        given(commentRepository.findById(CommentId)).willReturn(
                Optional.of(comment)
        );

        // when
        CommentResponseDto result = commentService.updateComment(CommentId, requestDto, user);

        // then
        assertEquals("테스트 수정 성공", result.getContents());
    }

    @Test
    @DisplayName("코멘트 수정 실패 - 작성자가 아닌 유저가 코멘트 수정")
    void test1() {
        // given
        Long CommentId = 200L;
        CommentRequestDto requestDto = new CommentRequestDto();

        Card card = new Card();

        User user1 = new User();
        user1.setId(100L);

        Comment comment = commentConverter.getComment(requestDto, card, user1);

        CommentService commentService = new CommentService(commentRepository, cardRepository, commentConverter);

        given(commentRepository.findById(CommentId)).willReturn(
                Optional.of(comment)
        );

        User user2 = new User();
        user1.setId(200L);

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            CommentResponseDto result = commentService.updateComment(CommentId, requestDto, user2);
        });

        // then
        assertEquals("작성자만 삭제/수정할 수 있습니다", exception.getMessage());
    }
}