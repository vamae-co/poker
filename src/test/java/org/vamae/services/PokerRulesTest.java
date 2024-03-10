package org.vamae.services;

import org.junit.jupiter.api.Test;
import org.vamae.models.PokerHand;
import org.vamae.models.enums.Combination;
import org.vamae.models.enums.Rank;
import org.vamae.models.enums.Suit;
import org.vamae.models.records.Card;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerRulesTest {
    @Test
    public void testHighCard() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.FOUR),
                new Card(Suit.CLUB, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.HEART, Rank.KING)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.SPADE, Rank.TEN)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.HIGH_CARD, result.getCombination());
    }

    @Test
    public void testOnePair() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.HEART, Rank.QUEEN),
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.QUEEN)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.DIAMOND, Rank.NINE)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.ONE_PAIR, result.getCombination());
    }

    @Test
    public void testTwoPair() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.FOUR),
                new Card(Suit.CLUB, Rank.TWO),
                new Card(Suit.SPADE, Rank.FOUR),
                new Card(Suit.HEART, Rank.KING)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.SPADE, Rank.KING)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.TWO_PAIR, result.getCombination());
    }

    @Test
    public void testThreeOfAKind() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.JACK),
                new Card(Suit.DIAMOND, Rank.FOUR),
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.SPADE, Rank.FOUR),
                new Card(Suit.DIAMOND, Rank.TWO)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.FOUR),
                new Card(Suit.SPADE, Rank.TEN)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.THREE_OF_A_KIND, result.getCombination());
    }

    @Test
    public void testStraight() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.THREE),
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.HEART, Rank.ACE)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.SIX),
                new Card(Suit.SPADE, Rank.TEN)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.STRAIGHT, result.getCombination());
    }

    @Test
    public void testFlush() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.HEART, Rank.THREE),
                new Card(Suit.HEART, Rank.FIVE),
                new Card(Suit.HEART, Rank.SEVEN),
                new Card(Suit.HEART, Rank.NINE)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.HEART, Rank.SIX),
                new Card(Suit.HEART, Rank.TEN)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.FLUSH, result.getCombination());
    }

    @Test
    public void testFullHouse() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.TWO),
                new Card(Suit.CLUB, Rank.TWO),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.HEART, Rank.FIVE)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.CLUB, Rank.FIVE)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.FULL_HOUSE, result.getCombination());
    }

    @Test
    public void testFourOfAKind() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.TWO),
                new Card(Suit.CLUB, Rank.TWO),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.HEART, Rank.FIVE)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.CLUB, Rank.FIVE)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.FOUR_OF_A_KIND, result.getCombination());
    }

    @Test
    public void testStraightFlush() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.HEART, Rank.THREE),
                new Card(Suit.HEART, Rank.FOUR),
                new Card(Suit.HEART, Rank.FIVE),
                new Card(Suit.HEART, Rank.SIX)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.HEART, Rank.SEVEN),
                new Card(Suit.HEART, Rank.TEN)
        );

        PokerHand result = PokerRules.check(tableCards, playerCards);
        result.getCards().forEach(System.out::println);
        assertEquals(Combination.STRAIGHT_FLUSH, result.getCombination());
    }
}