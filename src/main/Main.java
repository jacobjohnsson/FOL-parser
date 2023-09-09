import java.util.List;

import tokenizer.*;
import parser.*;

public class Main {
  public static void main (String args[]) {
    String input = "exists x, y Brother(x, Richard) + Brother(y, Richard) + -(x = y))";
    System.out.println (input);
    testParse (input);
  }

  private static void testParse (String input) {
    Tokenizer tokens = new Tokenizer(input);
    tokens.print();
    if (Parser.parse(tokens)) {
      System.out.println ("Success");
    } else {
      System.out.println ("Failure");
    }
  }
}
