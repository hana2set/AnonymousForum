package com.study.todocard.controller;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.security.UserDetailsImpl;
import com.study.todocard.service.CardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createCard(requestDto, userDetails.getUser());
    }

    @GetMapping("/cards")
    public List<CardResponseDto> getCards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCards(userDetails.getUser());
    }

    @GetMapping("/cards/{id}")
    public CardResponseDto getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    @PutMapping("/cards/{id}")
    public Long updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto) {
        return cardService.updateCard(id, requestDto);
    }

    @DeleteMapping("/cards/{id}")
    public Long deleteCard(@PathVariable Long id) {
        return cardService.deleteCard(id);
    }
}