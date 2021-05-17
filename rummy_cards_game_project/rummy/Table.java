package rummy;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import rummy.Card;
import rummy.Deck;
import rummy.Hand;
import rummy.Set;
import rummy.Stack;

/**
 * This GUI assumes that you are using a 52 card deck and that you have 13 sets
 * in the deck. The GUI is simulating a playing table
 * @author Christopher L. Ayala-Griffin
 */
@SuppressWarnings("serial")
public class Table extends JFrame implements ActionListener {

  final private static int numDealtCards = 9;

  final private List<Set> laidSets = new ArrayList<Set>();

  final private Stack stackDeck = new Stack();
  final private Deck cardDeck = new Deck();

  final private Hand p1Hand = new Hand();
  final private Hand p2Hand = new Hand();

  final private SetPanel[] setPanels = new SetPanel[13];

  final public JLabel stackGraphic = new JLabel();
  final public JLabel deckGraphic = new JLabel();

  final public JButton p1DrawFromDeckButton = new JButton("Draw from Deck");
  final public JButton p2DrawFromDeckButton = new JButton("Draw from Deck");

  final public JButton p1DrawFromStackButton = new JButton("Draw from Stack");
  final public JButton p2DrawFromStackButton = new JButton("Draw from Stack");

  final public JButton p1LayOnTableButton = new JButton("Lay on Table");
  final public JButton p2LayOnTableButton = new JButton("Lay on Table");

  final public JButton p1DiscardButton = new JButton("Discard");
  final public JButton p2DiscardButton = new JButton("Discard");

  public JList<Card> p1HandPile = new JList<Card>(p1Hand.getHand());
  public JList<Card> p2HandPile = new JList<Card>(p2Hand.getHand());

  private boolean p1Turn = true;
  private boolean playerDrawn = false;
  private boolean playerTurn = false;


  /**
   * Constructs the table where the Rummy game will take place. Takes care of the
   * UI and the logic as well.
   */
  public Table() {

    // Setup Table Layout
    super("The Card Game of the Century");
    setLayout(new BorderLayout());
    setSize(1200,700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create all the Set UI components in the table
    this.createSetPanelsInTable();

    // Prepare each players' hands
    // NOTE: This is necessary before painting Players' controls
    this.dealCardsToPlayers();

    // Place each player's controls on the table
    this.paintPlayersControls();

    // After dealing to players, the next card from the Deck initiates the Stack
    // NOTE: This is necessary before preparing Players' controls
    Card firstStackCard = cardDeck.dealCard();
    stackDeck.addCard(firstStackCard);
    stackGraphic.setIcon(firstStackCard.getCardImage());

    // Initialize all the interactive components for both players
    this.preparePlayerControls();

  }

  /**
   * Paints Player 1 and Player 2 controls along with the Deck and the Stack.
   */
  private void paintPlayersControls() {

    // Initialize the center section of the table
    JPanel centerSection = new JPanel(new GridLayout(1, 3));

    // Add Player 1 controls to the leftmost part of the table's middle section
    centerSection.add(new PlayerPanel("Player 1", p1HandPile, p1DrawFromStackButton, p1DrawFromDeckButton,
        p1LayOnTableButton, p1DiscardButton));

    // Add the Deck and Stack images to the center of the middle section
    centerSection.add(new PilePanel(stackGraphic, deckGraphic));

    // Add Player 2 controls to the rightmost part of the table's middle section
    centerSection.add(new PlayerPanel("Player 2", p2HandPile, p2DrawFromStackButton, p2DrawFromDeckButton,
        p2LayOnTableButton, p2DiscardButton));

    // Add middle section to the actual global center
    this.add(centerSection, BorderLayout.CENTER);

  }

  /**
   * Creates slots in the Table where the players can lay their sets.
   */
  private void createSetPanelsInTable() {

    // Instantiate the 13 set piles in the UI
    for (int i = 0; i < Card.RANKS.length; i++) {
      setPanels[i] = new SetPanel(Card.getRankIndex(Card.RANKS[i]));
    }

    // Add 4 set piles in the UI at the top
    JPanel topPanels = new JPanel();
    topPanels.add(setPanels[0]);
    topPanels.add(setPanels[1]);
    topPanels.add(setPanels[2]);
    topPanels.add(setPanels[3]);

    JPanel northSection = new JPanel();
    northSection.add(topPanels);
    this.add(northSection, BorderLayout.NORTH);

    // Add 5 set piles in the UI at the bottom
    JPanel bottomPanels = new JPanel();
    bottomPanels.add(setPanels[4]);
    bottomPanels.add(setPanels[5]);
    bottomPanels.add(setPanels[6]);
    bottomPanels.add(setPanels[7]);
    bottomPanels.add(setPanels[8]);

    JPanel southSection = new JPanel();
    southSection.add(bottomPanels);
    this.add(southSection, BorderLayout.SOUTH);

    // Add 2 set piles in the UI at the left
    JPanel westSection = new JPanel(new GridLayout(2, 1));
    setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
    setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
    westSection.add(setPanels[9]);
    westSection.add(setPanels[10]);
    this.add(westSection, BorderLayout.WEST);

    // Add 2 set piles in the UI at the right
    JPanel eastSection = new JPanel(new GridLayout(2, 1));
    setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
    setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
    eastSection.add(setPanels[11]);
    eastSection.add(setPanels[12]);
    this.add(eastSection, BorderLayout.EAST);

  }

  /**
   * Registers the buttons in the UI to enable user interaction.
   */
  private void preparePlayerControls() {

    p1DrawFromDeckButton.addActionListener(this);
    p1DrawFromStackButton.addActionListener(this);
    p1DrawFromStackButton.setEnabled(!stackDeck.isEmpty());
    p1LayOnTableButton.addActionListener(this);
    p1LayOnTableButton.setEnabled(false);
    p1DiscardButton.addActionListener(this);
    p1DiscardButton.setEnabled(false);

    p2DrawFromDeckButton.addActionListener(this);
    p2DrawFromDeckButton.setEnabled(false);
    p2DrawFromStackButton.addActionListener(this);
    p2DrawFromStackButton.setEnabled(false);
    p2LayOnTableButton.addActionListener(this);
    p2LayOnTableButton.setEnabled(false);
    p2DiscardButton.addActionListener(this);
    p2DiscardButton.setEnabled(false);

  }

  /**
   * Deals 9 cards to each player.
   */
  private void dealCardsToPlayers() {

    // Create Player 1 hand of 9 cards
    for (int i = 0; i < numDealtCards; i++) {
      Card c = cardDeck.dealCard();
      p1Hand.addCard(c);
    }

    // Create Player 2 hand of 9 cards
    for (int i = 0; i < numDealtCards; i++) {
      Card c = cardDeck.dealCard();
      p2Hand.addCard(c);
    }

    if (true) {
      System.out.println("Initial Player 1: " + p1Hand.toString());
      System.out.println("Initial Player 2: " + p2Hand.toString());
    }

  }

  /**
   * Handles logic to lay a single card on the table.
   * @param card the Card that will be laid on the table.
   */
  private void layCardOnTable(Card card) {
    char rank = card.getRank();
    char suit = card.getSuit();
    int suitIndex = Card.getSuitIndex(suit);
    int rankIndex = Card.getRankIndex(rank);
    System.out.println("\tLaying " + card.toString().toUpperCase());
    setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
  }

  /**
   * Announces through standard output who the winner is.
   */
  private void announceWinner() {
    int p1MinusP2 = p1Hand.compareTo(p2Hand);
    int p1Points = p1Hand.evaluateHand();
    int p2Points = p2Hand.evaluateHand();

    System.out.println("Points: " + p1Points + " (P1) vs. " + p2Points + " (P2)");

    if (p1MinusP2 < 0) {
      System.out.println("Player 1 Wins!");
    } else if (p1MinusP2 == 0) {
      System.out.println("It's a tie!");
    } else {
      System.out.println("Player 2 Wins!");
    }

    System.exit(0);

  }

  /**
   * Handles user clicking on Draw from Deck button.
   * @param playersHand the Hand of the player who clicked on the button.
   */
  private void handleDrawFromDeck(Hand playersHand) {

    Card card = cardDeck.dealCard();

    // Don't do anything if deck is empty
    if (card == null) {
      return;
    }

    // Add card to hand
    playersHand.addCard(card);

    if (true) {
      if (!playerTurn) {
        System.out.println(p1Turn ? "Player 1" : "Player 2");
        playerTurn = true;
      }
      System.out.println("\tAdded from Deck: " + card.toString().toUpperCase());
    }

    // Game Over
    if (cardDeck.isEmpty()) {
      deckGraphic.setIcon(new ImageIcon(Card.blankimg_filepath));
      announceWinner();
    }

  }

  /**
   * Handles user clicking on Draw from Stack button
   * @param playersHand the Hand of the player who clicked on the button.
   */
  private void handleDrawFromStack(Hand playersHand) {

    Card card = stackDeck.removeCard();
    if (card == null) {
      return;
    }

    // First change the image in the stack
    Card topCard = stackDeck.peek();
    if (topCard == null) {
      stackGraphic.setIcon(new ImageIcon(Card.blankimg_filepath));
    } else {
      stackGraphic.setIcon(topCard.getCardImage());
    }

    // Then add the removed card to the player's hand
    playersHand.addCard(card);

    if (true) {
      if (!playerTurn) {
        System.out.println(p1Turn ? "Player 1" : "Player 2");
        playerTurn = true;
      }
      System.out.println("\tAdded from Stack: " + card.toString().toUpperCase());
    }

  }

  /**
   * Handles user clicking on Lay on Table button.
   * @param playersHand     the Hand of the player who clicked on the button.
   * @param playersHandPile the UI representation of the player's hand.
   */
  private void handleLayOnTable(Hand playersHand, JList<Card> playersHandPile) {

    List<Card> selectedCards = playersHandPile.getSelectedValuesList();

    // Abort if there are no selected cards
    if (selectedCards.isEmpty()) {
      return;
    }

    // If only one card is selected, check if it fits in a Set already on the table
    if (selectedCards.size() == 1) {

      Card selectedCard = selectedCards.get(0);

      // Find the Set where the card fits, and lay it on the table
      for (int i = 0; i < laidSets.size(); i++) {
        if (selectedCard.getRank() == laidSets.get(i).getRank()) {
          laidSets.get(i).addCard(selectedCard);
          layCardOnTable(selectedCard);
          playersHand.removeCard(selectedCard);
          break;
        }
      }

      // Game Over
      if (playersHand.isEmpty()) {
        announceWinner();
      }

      return;

    }

    // First check if selectedCards forms a Set before laying
    if (!Set.isSet(selectedCards)) {
      return;
    }

    // Lay Set on table, one card at a time
    for (int i = 0; i < selectedCards.size(); i++) {
      Card card = selectedCards.get(i);
      layCardOnTable(card);
      playersHand.removeCard(card);
    }

    // Keep track of the Set that has been layed on the table (for later)
    Set newSet = new Set(selectedCards);
    laidSets.add(newSet);

    // Game Over
    if (playersHand.isEmpty()) {
      announceWinner();
    }

  }

  /**
   * Handles user clicking on "Discard" button.
   * @param playersHand     the Hand of the player who clicked on the button.
   * @param playersHandPile the UI representation of the player's hand.
   */
  private void handleDiscard(Hand playersHand, JList<Card> playersHandPile) {

    List<Card> selectedCards = playersHandPile.getSelectedValuesList();

    // Don't do anything if no card is selected or more than one card is selected
    if (selectedCards == null || selectedCards.size() != 1) {
      return;
    }

    // Discard from hand
    Card selectedCard = selectedCards.get(0);
    playersHand.removeCard(selectedCard);
    stackDeck.addCard(selectedCard);
    stackGraphic.setIcon(selectedCard.getCardImage());

    if (true) {
      System.out.println("\tDiscarded: " + selectedCard.toString().toUpperCase());
      System.out.println("\tHand now: " + playersHand.toString());
    }

    // Game Over
    if (playersHand.isEmpty()) {
      announceWinner();
    }

  }

  /**
   * Generic function that mimics a player's move (drawing from deck/stack, laying
   * sets/runs and finally discarding).
   * @param playersHand     the Hand of the player who clicked on the button.
   * @param playersHandPile the UI representation of the player's hand.
   */
  private void makeMove(Hand playersHand, JList<Card> playersHandPile) {

    // Draw from Deck or Stack
    if (stackDeck.isEmpty()) {
      handleDrawFromDeck(playersHand);
    } else {

      // Choose whether to draw from Deck or from Stack
      int oneOrTwo = ThreadLocalRandom.current().nextInt(1, 3);
      if (oneOrTwo == 1) {
        handleDrawFromDeck(playersHand);
      } else {
        handleDrawFromStack(playersHand);
      }
    }

    // Search for a Set in the hand and lay on the table
    Card[] set = playersHand.findSet();
    if (set != null) {

      // Select indices from hand before laying on table
      int[] indices = new int[set.length];
      for (int i = 0; i < set.length; i++) {
        indices[i] = playersHand.findCard(set[i]);
      }

      playersHandPile.setSelectedIndices(indices);

      handleLayOnTable(playersHand, playersHandPile);

    } else {
      //
    }

    // Select a random card from the hand to discard
    int handSize = playersHandPile.getModel().getSize();
    int randomIndex = ThreadLocalRandom.current().nextInt(0, handSize);
    playersHandPile.setSelectedIndex(randomIndex);

    // Discard to Stack
    handleDiscard(playersHand, playersHandPile);

  }

  /**
   * Enable and disable the players' buttons depending on their turn and if
   * they've drawn or not.
   */
  private void setButtonStates() {

    if (p1Turn) {

      if (playerDrawn) {
        p1DrawFromDeckButton.setEnabled(false);
        p1DrawFromStackButton.setEnabled(false);
        p1LayOnTableButton.setEnabled(true);
        p1DiscardButton.setEnabled(true);
      } else {
        p1DrawFromDeckButton.setEnabled(!cardDeck.isEmpty());
        p1DrawFromStackButton.setEnabled(!stackDeck.isEmpty());
        p1LayOnTableButton.setEnabled(false);
        p1DiscardButton.setEnabled(false);
      }

    } else {

      if (playerDrawn) {
        p2DrawFromDeckButton.setEnabled(false);
        p2DrawFromStackButton.setEnabled(false);
        p2LayOnTableButton.setEnabled(true);
        p2DiscardButton.setEnabled(true);
      } else {
        p2DrawFromDeckButton.setEnabled(!cardDeck.isEmpty());
        p2DrawFromStackButton.setEnabled(!stackDeck.isEmpty());
        p2LayOnTableButton.setEnabled(false);
        p2DiscardButton.setEnabled(false);
      }

    }

  }

  /**
   * Handles a user's action triggered by any of the registered buttons.
   * 
   * @param e the ActionEvent represnting the user's action.
   */
  public void actionPerformed(ActionEvent e) {

    // Get event source
    Object src = e.getSource();

    if (p1Turn) {

      // Draw from Deck
      if (p1DrawFromDeckButton == src && !playerDrawn) {
        handleDrawFromDeck(p1Hand);
        playerDrawn = true;
      }

      // Draw from Stack
      if (!playerDrawn && !stackDeck.isEmpty() && p1DrawFromStackButton == src) {
        handleDrawFromStack(p1Hand);
        playerDrawn = true;
      }

      // Lay set on table
      if (playerDrawn && p1LayOnTableButton == src) {
        handleLayOnTable(p1Hand, p1HandPile);
      }

      // Discard to Stack
      if (p1DiscardButton == src && playerDrawn) {

        // Discard one card at a time
        int[] num = p1HandPile.getSelectedIndices();
        if (num.length != 1)
          return;

        handleDiscard(p1Hand, p1HandPile);
        p1Turn = false;
        playerTurn = false;
        playerDrawn = false;

        // AI makes a move
        if (true) {
          makeMove(p2Hand, p2HandPile);
          playerTurn = false;
          p1Turn = true;
        }

      }

    } else if (false) {

      // Draw from Deck
      if (p2DrawFromDeckButton == src && !playerDrawn) {
        handleDrawFromDeck(p2Hand);
        playerDrawn = true;
      }

      // Draw from Stack
      if (p2DrawFromStackButton == src && !playerDrawn && !stackDeck.isEmpty()) {
        handleDrawFromStack(p2Hand);
        playerDrawn = true;
      }

      // Lay set on table
      if (p2LayOnTableButton == src && playerDrawn) {
        handleLayOnTable(p2Hand, p2HandPile);
      }

      // Discard to Stack
      if (p2DiscardButton == src && playerDrawn) {

        // Only discard one card at a time
        int[] num = p2HandPile.getSelectedIndices();
        if (num.length != 1)
          return;

        handleDiscard(p2Hand, p2HandPile);
        p1Turn = true;
        playerTurn = false;
        playerDrawn = false;

      }

    }
    // After action has been processed, change button states accordingly
    setButtonStates();

  }

}
