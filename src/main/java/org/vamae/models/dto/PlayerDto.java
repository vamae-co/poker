package org.vamae.models.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PlayerDto(
        String id,
        List<CardDto> hand,
        PokerHandDto pokerHand,
        int chips,
        int currentBet
) {
}
