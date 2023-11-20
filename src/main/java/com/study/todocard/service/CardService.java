package com.study.todocard.service;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRoleEnum;
import com.study.todocard.exception.BusinessException;
import com.study.todocard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        Card saveCard = cardRepository.save(new Card(requestDto, user));
        return new CardResponseDto(saveCard);
    }

    public List<CardResponseDto> getCards(User user) {
        // DB 조회
        return cardRepository.findAllByUserOrderByCreatedAtDesc(user).stream().map(CardResponseDto::new).toList();
    }

    public CardResponseDto getCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        if (!isAccessableUser(user, card.getUser())) {
            throw new BusinessException("해당 카드에 대한 접근 권한이 없습니다.");
        }

        card.update(requestDto);
        return new CardResponseDto(card);
    }

    @Transactional
    public Long updateCardComplete(Long id, boolean isComplete, User user) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        if (!isAccessableUser(user, card.getUser())) {
            throw new BusinessException("해당 카드에 대한 접근 권한이 없습니다.");
        }

        card.updateComplete(isComplete);
        return id;
    }

    public Long deleteCard(Long id, User user) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id).orElseThrow(() -> new BusinessException("선택한 카드는 존재하지 않습니다."));
        if (!isAccessableUser(user, card.getUser())) {
            throw new BusinessException("해당 카드에 대한 접근 권한이 없습니다.");
        }

        cardRepository.delete(card);
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