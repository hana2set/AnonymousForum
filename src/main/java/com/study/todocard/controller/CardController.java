package com.study.todocard.controller;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.security.UserDetailsImpl;
import com.study.todocard.service.CardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createCard(requestDto, userDetails.getUser());
    }

    @GetMapping("")
    public List<CardResponseDto> getCards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCards(userDetails.getUser());
    }

    @GetMapping("/{id}")
    public CardResponseDto getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    @PutMapping("/{id}")
    public Long updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(id, requestDto, userDetails.getUser());
    }

    @PutMapping("/{id}/complete")
    public Long updateCardComplete(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCardComplete(id, requestDto.getIsComplete(), userDetails.getUser());
    }

    @DeleteMapping("/{id}")
    public Long deleteCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.deleteCard(id, userDetails.getUser());
    }
}