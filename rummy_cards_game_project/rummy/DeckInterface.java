package rummy;

import rummy.Card;

public interface DeckInterface {

  public Card peek();

  public void addCard(Card card);

  public int getSizeOfDeck();

  public Card dealCard();

  public Card removeCard();

  public void shuffle();

  public boolean isEmpty();

  public void restoreDeck();

}
