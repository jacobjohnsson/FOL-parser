import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tokenizer.*;
import parser.*;

public class Main {
  public static void main (String args[]) {
    if (args.length < 1) {
      System.out.println(args[0]);
      return;
    }

    Scanner sc;
    File file = new File(args[0]);
    try {
      sc = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }

    while (sc.hasNextLine()) {
      testScan(sc.nextLine());
    }
  }

  private static void testScan (String input) {
    Tokenizer tokens = new Tokenizer(input);
    System.out.println(input);
    tokens.print();
    if (!tokens.containsErrors()) {
      System.out.println ("Success");
    } else {
      System.out.println ("Failure");
    }
  }
}
