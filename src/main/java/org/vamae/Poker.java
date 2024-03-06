package org.vamae;

import java.util.*;
import java.util.stream.Collectors;

public class Poker {
    public static PokerHand check(List<Card> tableCards, List<Card> playerCards) {
        List<Card> allCards = new ArrayList<>(tableCards);
        allCards.addAll(playerCards);

        allCards.sort(Comparator.comparing(Card::rank));

        Map<Rank, Long> rankFrequency = allCards
                .stream()
                .collect(Collectors.groupingBy(Card::rank, Collectors.counting()));

        List<Card> bestComboCards = new ArrayList<>();
        Combination bestHand = Combination.HIGH_CARD;
        bestComboCards.add(allCards.getLast());

        boolean hasPair = false;
        boolean hasThreeOfAKind = false;

        for (Map.Entry<Rank, Long> entry : rankFrequency.entrySet()) {
            if (entry.getValue() == 2) {
                if (hasThreeOfAKind) {
                    bestHand = Combination.FULL_HOUSE;
                    bestComboCards.addAll(getAllCardsWithRankFromList(allCards, entry.getKey()));
                    break;
                } else if (hasPair) {
                    bestHand = Combination.TWO_PAIR;
                    bestComboCards.addAll(getAllCardsWithRankFromList(allCards, entry.getKey()));
                } else {
                    hasPair = true;
                    bestHand = Combination.ONE_PAIR;
                    bestComboCards = allCards
                            .stream()
                            .filter(card -> card.rank() == entry.getKey())
                            .collect(Collectors.toList());
                }
            } else if (entry.getValue() == 3) {
                if (hasThreeOfAKind) {
                    bestHand = Combination.FULL_HOUSE;
                    bestComboCards.addAll(getAllCardsWithRankFromList(allCards, entry.getKey()));
                    bestComboCards.removeFirst();
                    break;
                }
                if (hasPair) {
                    bestHand = Combination.FULL_HOUSE;
                    bestComboCards.addAll(getAllCardsWithRankFromList(allCards, entry.getKey()));
                    break;
                } else {
                    hasThreeOfAKind = true;
                    bestHand = Combination.THREE_OF_A_KIND;
                    bestComboCards = allCards
                            .stream()
                            .filter(card -> card.rank() == entry.getKey())
                            .collect(Collectors.toList());
                }
            } else if (entry.getValue() == 4) {
                bestHand = Combination.FOUR_OF_A_KIND;
                bestComboCards = allCards
                        .stream()
                        .filter(card -> card.rank() == entry.getKey())
                        .collect(Collectors.toList());
                break;
            }
        }

        Optional<List<Card>> straight = getHighestStraight(allCards);
        Optional<List<Card>> flush = getFlushCards(allCards);

        if (straight.isPresent()) {
            if (getFlushCards(straight.get()).isPresent()) {
                bestHand = Combination.STRAIGHT_FLUSH;
            } else {
                bestHand = Combination.STRAIGHT;
            }
            bestComboCards = straight.get();
        } else if (flush.isPresent()) {
            bestHand = Combination.FLUSH;
            bestComboCards = flush.get();
        }

        return new PokerHand(bestHand, bestComboCards);
    }

    private static List<Card> getAllCardsWithRankFromList(List<Card> allCards, Rank rank) {
        return allCards
                .stream()
                .filter(card -> card.rank() == rank)
                .toList();
    }

    private static Optional<List<Card>> getHighestStraight(List<Card> cards) {
        List<Card> straight = new ArrayList<>();

        for (int i = cards.size() - 5; i >= 0; i--) {
            boolean isStraight = true;
            for (int j = i + 1; j < i + 5; j++) {
                if (cards.get(j).rank().ordinal() != cards.get(j - 1).rank().ordinal() + 1) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                straight.addAll(cards.subList(i, i + 5));
                break;
            }
        }
        return straight.isEmpty() ? Optional.empty() : Optional.of(straight);
    }

    private static Optional<List<Card>> getFlushCards(List<Card> cards) {
        Map<Suit, List<Card>> suitMap = cards
                .stream()
                .collect(Collectors.groupingBy(Card::suit));
        return suitMap.values()
                .stream()
                .filter(list -> list.size() >= 5)
                .findAny();
    }
}