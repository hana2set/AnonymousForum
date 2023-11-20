package com.study.todocard.service;

import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.dto.CommentResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRoleEnum;
import com.study.todocard.exception.BusinessException;
import com.study.todocard.repository.CardRepository;
import com.study.todocard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        if (requestDto.getCardId() == null) {
            new BusinessException("선택된 카드가 없습니다.");
        }
        Card card = cardRepository.findById(requestDto.getCardId()).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        Comment saveComment = commentRepository.save(new Comment(requestDto, card, user));
        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        // DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        if (!isAccessableUser(user, comment.getUser())) {
            throw new BusinessException("해당 카드에 대한 접근 권한이 없습니다.");
        }

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    public Long deleteComment(Long id, User user) {
        // DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        if (!isAccessableUser(user, comment.getUser())) {
            throw new BusinessException("해당 카드에 대한 접근 권한이 없습니다.");
        }

        commentRepository.delete(comment);
        return id;
    }

    private boolean isAccessableUser(User target_user, User access_user) {
        if (target_user == null || access_user == null) {
            return false;
        }

        if (access_user.getRole() == UserRoleEnum.ADMIN || access_user.getId().equals(target_user.getId())) {
            return true;
        }

        return false;
    }

}