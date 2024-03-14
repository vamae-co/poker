package org.vamae.models.dto;

import lombok.Builder;
import org.vamae.models.records.Card;

import java.util.List;

@Builder
public record PlayerDto(
        String id,
        List<Card> hand,
        PokerHandDto pokerHand,
        int chips,
        int currentBet
) {
}
