package com.study.todocard.service;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRole;
import com.study.todocard.exception.BusinessException;
import com.study.todocard.exception.ExceptionType;
import com.study.todocard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CardService {

    /**
     * 게시글 생성
     * @param requestDto 게시글 생성 요청정보
     * @param user 게시글 생성 요청자
     * @return 게시글 생성 결과
     */
    CardResponseDto createCard(CardRequestDto requestDto, User user);

    /**
     * 작성 게시글 전체 조회
     * @param user 게시글 조회 요청자
     * @return 게시글 조회 결과
     */
    List<CardResponseDto> listCards(User user);


    /**
     * 관련 게시글 전체 조회 (댓글작성 등)
     * @param user 게시글 조회 요청자
     * @return 게시글 조회 결과
     */
    Page<CardResponseDto> listRelationCards(Pageable pageable, User user);

    /**
     * 선택 게시물 조회
     * @param id 게시글 Id
     * @return 게시글 조회 결과
     */
    CardResponseDto getCard(Long id);

    /**
     * 선택 게시물 수정
     * @param id 게시글 Id
     * @return 게시글 조회 결과
     */
    CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user);

    /**
     * 선택 게시물 완료 여부 수정
     * @param id 게시글 Id
     * @return 게시글 Id 반환
     */
    Long updateCardComplete(Long id, boolean isComplete, User user);

    /**
     * 선택 게시물 삭제
     * @param id 게시글 Id
     * @param user 게시글 삭제 요청 유저
     * @return 게시글 Id 반환
     */
    Long deleteCard(Long id, User user);
}