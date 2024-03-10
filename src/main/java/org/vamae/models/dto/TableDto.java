package org.vamae.models.dto;

import lombok.Builder;
import org.vamae.models.Deck;
import org.vamae.models.records.Card;
import org.vamae.models.records.Settings;

import java.util.List;

@Builder
public record TableDto(
        Settings settings,
        String state,
        List<PlayerDto> players,
        List<Card> cards,
        Deck deck,
        int pot,
        int currentPlayerIndex,
        int lastPlayerIndex,
        int currentBet
) {
}
