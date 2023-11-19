package com.study.todocard.controller;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.service.CardService;
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
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto) {
        return cardService.createCard(requestDto);
    }

    @GetMapping("/cards")
    public List<CardResponseDto> getCards() {
        return cardService.getCards();
    }

    @GetMapping("/cards/{id}")
    public CardResponseDto getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    @PutMapping("/cards/{id}")
    public Long updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto) {
        return cardService.updateCard(id, requestDto);
    }

    @DeleteMapping("/cards/{id}/{password}")
    public Long deleteCard(@PathVariable Long id) {
        return cardService.deleteCard(id);
    }
}