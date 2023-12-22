package com.study.todocard.service;

import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.dto.CommentResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRole;
import com.study.todocard.exception.BusinessException;
import com.study.todocard.exception.ExceptionType;
import com.study.todocard.repository.CardRepository;
import com.study.todocard.repository.CommentRepository;
import com.study.todocard.util.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final CommentConverter commentConverter;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Card card = cardRepository.findById(requestDto.getCardId())
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_CARD));
        Comment saveComment = commentRepository.save(commentConverter.getComment(requestDto, card, user));
        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        // DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_COMMENT));
        if (!isAccessableUser(user, comment.getUser())) {
            throw new BusinessException(ExceptionType.ONLY_AUTHOR_MOD);
        }

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    public ResponseEntity deleteComment(Long id, User user) {
        // DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_COMMENT));
        if (!isAccessableUser(user, comment.getUser())) {
            throw new BusinessException(ExceptionType.ONLY_AUTHOR_DEL);
        }

        commentRepository.delete(comment);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private boolean isAccessableUser(User target_user, User access_user) {
        if (target_user == null || access_user == null) {
            return false;
        }

        if (access_user.getRole() == UserRole.ADMIN || access_user.getId().equals(target_user.getId())) {
            return true;
        }

        return false;
    }

}