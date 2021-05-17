# Rummy Card Game

 Christopher L. Ayala Griffin
 CCOM4029
 
 ## Objectives
 • To implement a stack-like ADT from scratch
 • To create and extend ADTs to serve a particular purpose
 • To explore control flow choices in an interactive application
 • To use and potentially extend a GUI
 • To recognize design patterns in the implementation
 • To add two more design patterns to your implementation
 
 ## Overview
 * In this project, you will complete an interactive version of the Rummy card game. We will give you basic UI code and a Card class, as well as specify an interface that must be implement for the Hand, Deck, and Set classes that is functional but implements no logic of the game. Other class design choices are yours. There are many variations of Rummy. You are required to follow the rules listed below.

### Usage

• Compile source files with `javac rummy/Proj2.java` and `javac rummy/Game.java`
• To run:
   1. **player vs. computer game, run `java rummy.Proj2`**
   2. **computer vs. computer game, run `java rummy.Game`**
        - **`java rummy.Proj2 > output.txt` or `java rummy.Game > output.txt` creates a file containing your logs**


• Optionally the program takes two command line arguments:
   
   - the `-0`, `-1`, and `-2` flags indicate the number of interactive players in the game
    
     - the `-h` flag enables logging of actions

### Rules of Play

* These rules are a slight adaptation of standard Rummy rules. The objective of this game is to make melds (sets and runs) of similar cards and lay them on the table until all cards in your hand have been disposed of. A set is three or more cards of the same rank, such as three aces or four sevens. A run is three or more sequential cards of the same suit, such as the three-four-five of clubs or the jack-queen-king of diamonds. The ace is always low.

* Each player is dealt nine cards from the stockpile (Deck). The next card is turned face-up to initiate the discard pile (Stack). The deck is placed face-down on the table. In each turn, a player draws one card from either the Deck or Stack. They may either lay sets and/or runs on the table or place a card on Stack. The turn ends with a discard. If a player is able to lay all remaining cards on the table at the end of a turn, the game is over.

* The game is over when either one player is out of cards or the stock is exhausted. At this point, players count the points remaining in their hands. Aces are worth one, face cards are worth ten, and all other cards are worth their numeric value. Lowest value hand wins. Ties are possible.
    
### Cards Values

• Aces = 1 point

• Face = 10 points

• All other cards are worth their face value

### Buttons Actions

• *Draw from Stack*: draws from the Stack
• *Draw from Deck*: draws from the Deck
• *Lay on Table*: lays card on the table or lays the selected card on a Set on the table
• *Discard*: puts away the selected card in the Stack and terminates the player's turn

### Project Specifications

* In this project, you will be implementing a stack class, named MyStack, from scratch. It should provide the standard stack operations of push, pop, top, and isEmpty. You should use generics for this class.

* You are also expected to write the Hand, Deck, and Pile classes that implements the HandInterface, DeckInterface, and PileInterface interfaces. You may not change the methods given in these interfaces, but you may add methods to these classes. Hand and Deck may be derived from other Java classes, such as those in the Collection framework. Pile should be derived from your MyStack class. Your Deck class should provide a constructor which creates a 52-card deck of shuffled cards.

* Your Hand class should maintain the cards in sorted order. Your Hand class should provide a play() function to implement a computer opponent if you chose to do the extra credit option with the switches. This automated player can be really stupid, but must follow the rules. Specifically, for each choice the automated player must make (for example, whether to draw from stock or discards), a random choice is fine. A more intelligent play routine would be extra credit. The base project need look only for sets in the Hand. Runs are left as extra credit. We will be providing you with the code for the UI, interactive game control, and the Card class. The program takes command line arguments. The -h switch enables logging of actions (by default there is no logging) which should match the Sample Results.

* The project is in Java 13 SE [Java 16 SE](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)

### Extra Credit Doings

1. The card display in the UI is very minimalist. There are card images available in the file named cards.zip. Use a different layout manager or container with images to spruce up the appearance of your game. Worth five points.
2.  Modify the Table so that the game begins with the first card being on the Stack. Worth five points.
3. The random decisions in automated play make for a really dumb player. Add some heuristics to improve performance in automated play. Discuss each rule you add and why you expect it to improve performance in your README file. Worth two points for each rule (for a max of five rules).The random decisions in automated play make for a really dumb player. Add some heuristics to improve performance in automated play. Discuss each rule you add and why you expect it to improve performance in your README file. Worth two points for each rule (for a max of five rules).
6. The player number switch (-0, -1, or -2) gives the number of interactive players in the game (by default this is 2, indicating two interactive players). Worth ten points.

### Deliverables

1.  *.java files (including Proj2.java and Card.java)
2. p2-output.txt
3. README.md, including instructions for how to run your GUI if you implement one for extra credit
4. Any items required for extra credit options


### References 

 [Official Java Tutorials & Documentation](https://docs.oracle.com)
 
 [GeeksForGeeks Bubble Sort](https://www.geeksforgeeks.org/bubble-sort/)
 
 [Programiz Java Stack ADT Implementation](https://www.programiz.com/java-programming/examples/stack-implementation)
 
 [Tutorials Point - Design Patterns Tutorial](https://www.tutorialspoint.com/design_pattern/design_pattern_overview.htm)
 
 I want to thank all the peers who gave me suggestions and answered my questions both in class and in forums outside of class.
 
 
