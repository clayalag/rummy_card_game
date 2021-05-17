package rummy;

import java.util.Arrays;
import java.util.List;

import rummy.Table;

public class Proj2 {

  public static void main(String args[]) {

    // allow max of two arguments
    if (args.length > 2) {
      System.out.println("Usage is java rummy.Main [-h] [{-0, -1, -2}]");
      return;
    }

    // Pre-parse arguments
    List<String> arguments = Arrays.asList(args);
    boolean containsAtLeastH = arguments.contains("-h");
    boolean containsAtLeastOneValidNumber = arguments.contains("-0") || arguments.contains("-1")
        || arguments.contains("-2");

    // Default Flags
    boolean p1IsCPU = false;
    boolean p2IsCPU = false;
    boolean login = false;
    int numberOfInteractivePlayers = 2;

    // Check for logging
    if (containsAtLeastH) {
      login = true;
    }

    // Check the number of interactive players
    if (containsAtLeastOneValidNumber) {
      String arg = arguments.get(1);
      if (arg.equals("-0")) {
        numberOfInteractivePlayers = 0;
      } else if (arg.equals("-1")) {
        numberOfInteractivePlayers = 1;
      } else if (arg.equals("-2")) {
        numberOfInteractivePlayers = 2;
      } else {
        System.out.println("Invalid number of interactive players: " + arg);
        return;
      }
    }

    // Switch flags accordingly
    switch (numberOfInteractivePlayers) {
    case 0:
      p1IsCPU = true;
      p2IsCPU = true;
      break;
    case 1:
      p2IsCPU = true;
      break;
    case 2:
    default:
    }

    Table t = new Table();
    t.setVisible(true);

  }

}
