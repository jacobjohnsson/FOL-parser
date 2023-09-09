package parser;

import java.util.List;

import tokenizer.*;

public class Parser {

  public static boolean parse (Tokenizer tokens) {
    return !tokens.containsErrors();

    //return sentence(tokens);
  }

  private static boolean sentence (Tokenizer tokens) {
    return true;
  }
}
