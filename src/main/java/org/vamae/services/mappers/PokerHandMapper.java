package org.vamae.services.mappers;

import org.vamae.models.PokerHand;
import org.vamae.models.dto.PokerHandDto;
import org.vamae.models.enums.Combination;

public class PokerHandMapper {
    public static PokerHandDto toDto(PokerHand pokerHand) {
        if (pokerHand == null) {
            return null;
        }

        return new PokerHandDto(
                pokerHand.getCombination().toString(),
                CardMapper.toDto(pokerHand.getCards())
        );
    }

    public static PokerHand toPokerHand(PokerHandDto pokerHand) {
        if (pokerHand == null) {
            return null;
        }

        return new PokerHand(
                Combination.valueOf(pokerHand.combination()),
                CardMapper.toCard(pokerHand.cards())
        );
    }
}
