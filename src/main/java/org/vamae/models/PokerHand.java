package org.vamae.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.vamae.models.enums.Combination;
import org.vamae.models.records.Card;

import java.util.List;

@AllArgsConstructor
@Getter
public class PokerHand {
    private Combination combination;
    private List<Card> cards;
}
