package rummy;

// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import rummy.Card;
import rummy.Set;

/**
 * Graphic representation of Sets layed on the table.
 */
@SuppressWarnings("serial")
class SetPanel extends JPanel {

  final private Set data;
  final public JButton[] array = new JButton[Set.set_max];

  /**
   * Graphic representation of Sets layed on the table.
   */
  public SetPanel(int index) {
    super();
    data = new Set(Card.RANKS[index]);

    for (int i = 0; i < array.length; i++) {
      array[i] = new JButton(" ");
      this.add(array[i]);
    }
  }

}
