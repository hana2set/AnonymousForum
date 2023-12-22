package com.study.todocard.service;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRole;
import com.study.todocard.exception.BusinessException;
import com.study.todocard.exception.ExceptionType;
import com.study.todocard.repository.CardRepository;
import com.study.todocard.util.CardConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;

    @Override
    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        Card saveCard = cardRepository.save(CardConverter.toEntity(requestDto, user));
        return CardConverter.toResponse(saveCard);
    }

    @Override
    public List<CardResponseDto> listCards(User user) {
        return cardRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(CardConverter::toResponse)
                .toList();
    }


    @Override
    public Page<CardResponseDto> listRelationCards(Pageable pageable, User user) {
        List<CardResponseDto> cards = cardRepository.findAllRelationCardByUser(pageable, user)
                .stream()
                .map(CardConverter::toResponse)
                .toList();
        return PageableExecutionUtils.getPage(cards, pageable, () -> cards.size());
    }

    @Override
    public CardResponseDto getCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_CARD));
        return CardConverter.toResponse(card);
    }

    @Transactional
    @Override
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_CARD));
        if (!isAccessableUser(user, card.getUser())) {
            throw new BusinessException(ExceptionType.ONLY_AUTHOR_MOD);
        }

        card.update(requestDto);
        return CardConverter.toResponse(card);
    }

    @Transactional
    @Override
    public Long updateCardComplete(Long id, boolean isComplete, User user) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_CARD));
        if (!isAccessableUser(user, card.getUser())) {
            throw new BusinessException(ExceptionType.ONLY_AUTHOR_MOD);
        }

        card.updateComplete(isComplete);
        return id;
    }

    @Override
    public Long deleteCard(Long id, User user) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_EXIST_CARD));
        if (!isAccessableUser(user, card.getUser())) {
            throw new BusinessException(ExceptionType.ONLY_AUTHOR_DEL);
        }

        cardRepository.delete(card);
        return id;
    }

    private boolean isAccessableUser(User target_user, User access_user) {
        if (target_user == null || access_user == null) {
            return false;
        }

        if (access_user.getRole() == UserRole.ADMIN
                || access_user.getId().equals(target_user.getId())) {
            return true;
        }

        return false;
    }

}