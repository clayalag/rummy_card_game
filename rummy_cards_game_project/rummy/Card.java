// Card.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a playing card.  Uses classes Rank and Suit for
// expressing the card value.

package rummy;

import javax.swing.ImageIcon;

import rummy.CardInterface;
/**
 * Representation of a single playing card. A card consists of a suit value
 * (e.g. hearts, spades, diamonds, clubs), a rank value (e.g. ace, 7, king), and
 * an image of the front of the card. A card object is immutable; once
 * instantiated, the values cannot change.
 *
 * @author John K. Estell adapted by Patti Ordonez
 * @version 1.0
 */
public class Card implements CardInterface, Comparable<Card> {

  final public static char[] SUITS = { 'c', 'd', 'h', 's' };
  final public static char[] RANKS = { 'a', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k' };
  final public static String IMAGE_DIR = "cards/";
  final public static String BLANK_IMAGE_FILENAME = "blank.gif";
  final public static String CARDBACK_IMAGE_FILENAME = "cardback.png";
  final public static String BLANK_IMAGE_FILEPATH = IMAGE_DIR + BLANK_IMAGE_FILENAME;
  final public static String CARDBACK_IMAGE_FILEPATH = IMAGE_DIR + CARDBACK_IMAGE_FILENAME;
  final private char suitValue;
  final private char rankValue;
  final private ImageIcon cardImage;

  /**
   * Creates a new playing card.
   * @param suit the suit value of this card.
   * @param rank the rank value of this card
   */

  public Card(char suit, char rank) {
    suitValue = suit;
    rankValue = rank;
    cardImage = new ImageIcon(getImageFile());
  }

  /**
   * Returns the index of the Suit in the defined static array <code>SUITS</code>
   * @param suit the suit value of this card.
   */

  public static int getSuitIndex(char suit) {
    switch (suit) {
    case 'c':
      return 0;
    case 'd':
      return 1;
    case 'h':
      return 2;
    case 's':
      return 3;
    default:
      return -1;
    }
  }

  /**
   * Returns the Index of the rank in the defined static array <code>RANKS</code>.
   * @param rank the rank value of this card.
   */
  // NOTE: NOT IN INTERFACE
  public static int getRankIndex(char rank) {
    switch (rank) {
    case 'a':
      return 0;
    case '2':
    case '3':
    case '4':
    case '5':
    case '6':
    case '7':
    case '8':
    case '9':
      return rank - '1';
    case 't':
      return 9;
    case 'j':
      return 10;
    case 'q':
      return 11;
    case 'k':
      return 12;
    default:
      return -1;
    }
  }

  /**
   * Returns the path to the card's image.
   * @return a String representing a file path.
   */
  public String getImageFile() {
    return IMAGE_DIR + toString() + ".gif";
  }

  /**
   * Returns the suit of the card.
   * @return a char representing the suit value of the card.
   */
  public char getSuit() {
    return this.suitValue;
  }

  /**
   * Returns the rank of the card.
   * @return a char representing the rank value of the card.
   */
  public char getRank() {
    return this.rankValue;
  }

  /**
   * Returns the graphic image of the card.
   * @return an icon containing the graphic image of the card.
   */
  public ImageIcon getCardImage() {
    return this.cardImage;
  }

  /**
   * Returns a description of this card.
   * @return the name of the card.
   */
  public String toString() {
    return "" + this.getRank() + this.getSuit();
  }

  /**
   * Compares two cards for the purposes of sorting.
   * Cards are ordered by their
   * rank value.
   * @param otherCard the other card
   * @return a negative integer, zero, or a positive integer is this card is
   * less than, equal to, or greater than the referenced card.
   */
  public int compareTo(Card otherCard) {
    int rankDiff = getRankIndex(this.rankValue) - getRankIndex(otherCard.rankValue);
    return rankDiff;
  }

}
