package org.vamae;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokerTest {
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.HIGH_CARD, result.combination);
    }

    @Test
    public void testOnePair() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.FOUR),
                new Card(Suit.CLUB, Rank.TWO),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.HEART, Rank.KING)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.SPADE, Rank.TEN)
        );

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.ONE_PAIR, result.combination);
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.TWO_PAIR, result.combination);
    }

    @Test
    public void testThreeOfAKind() {
        List<Card> tableCards = Arrays.asList(
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.FOUR),
                new Card(Suit.CLUB, Rank.TWO),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.TWO)
        );

        List<Card> playerCards = Arrays.asList(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.SPADE, Rank.TEN)
        );

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.THREE_OF_A_KIND, result.combination);
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.STRAIGHT, result.combination);
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.FLUSH, result.combination);
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.FULL_HOUSE, result.combination);
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.FOUR_OF_A_KIND, result.combination);
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

        PokerHand result = Poker.check(tableCards, playerCards);
        result.cards.forEach(System.out::println);
        assertEquals(Combination.STRAIGHT_FLUSH, result.combination);
    }
}