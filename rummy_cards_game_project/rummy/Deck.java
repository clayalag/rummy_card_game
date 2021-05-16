package rummy;

import java.util.Collections;
import java.util.LinkedList;

import rummy.DeckInterface;

/**
 * Operations over the deck of cards: adding,
 * removing, and shuffling cards.
 */
public class Deck implements DeckInterface {

  final private LinkedList<Card> deck;

  /**
   * Creates deck of cards
   */

  public Deck() {
    deck = new LinkedList<Card>();

    for (int i = 0; i < Card.SUITS.length; i++) {
      for (int j = 0; j < Card.RANKS.length; j++) {
        Card card = new Card(Card.SUITS[i], Card.RANKS[j]);
        deck.add(card);
      }
    }

    shuffle();
  }

  /**
   * Returns next Card in the deck
   * @return a Card representing the next card in the deck.
   */
  public Card peek() {
    return isEmpty() ? null : deck.getLast();
  }

  /**
   * Adds a Card to the deck
   * @param card Card to be added.
   */
  public void addCard(Card card) {
    deck.add(card);
  }

  /**
   * Returns the number of Cards on the deck
   * @return number of cards on the deck
   */
  public int getSizeOfDeck() {
    return deck.size();
  }

  /**
   * Method to deal the cards which removes the first card of the stack
   * and check if there are still cards in the stack
   * @return null if no cards, else show first card
   */
  public Card dealCard() {
    return isEmpty() ? null : deck.removeLast();
  }

  /**
   * Method to remove the last card of the stack
   * @return null if no cards, else show last card
   */
  public Card removeCard() {
    return isEmpty() ? null : deck.removeLast();
  }

  /**
   * Shuffles the cards in the deck.
   */
  public void shuffle() {
    Collections.shuffle(deck);
  }

  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
  public boolean isEmpty() {
    return deck.isEmpty();
  }

  /**
   * Restores the deck to "full deck" status.
   */
  public void restoreDeck() {
    deck.removeAll(deck);
  }

}
