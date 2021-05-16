package rummy;

import rummy.Card;

public interface SetInterface {

  public void addCard(Card card);

  public boolean isFull();

  public int getRankIndex();

  public char getRank();

}
