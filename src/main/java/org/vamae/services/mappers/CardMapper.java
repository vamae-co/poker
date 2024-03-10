package org.vamae.services.mappers;

import org.vamae.models.dto.CardDto;
import org.vamae.models.enums.Rank;
import org.vamae.models.enums.Suit;
import org.vamae.models.records.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static CardDto toDto(Card card) {
        return new CardDto(card.suit().toString(), card.rank().toString());
    }

    public static Card toCard(CardDto card) {
        return new Card(Suit.valueOf(card.suit()), Rank.valueOf(card.rank()));
    }

    public static List<CardDto> toDto(List<Card> cards) {
        return cards.stream()
                .map(CardMapper::toDto)
                .toList();
    }

    public static List<Card> toCard(List<CardDto> cards) {
        return cards.stream()
                .map(CardMapper::toCard)
                .collect(Collectors.toList());
    }
}
