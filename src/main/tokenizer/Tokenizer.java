package tokenizer;

import java.util.List;
import java.util.LinkedList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Tokenizer {
  private List<Token> tokens;
  private int tokenPosition;
  private int inputPosition;

  private Rule[] rules;

  public Tokenizer (String input) {
    this.tokenPosition = 0;
    this.inputPosition = 0;

    this.rules = new Rule[15];
    this.rules[0] = new Rule ("^\\+",         Token.AND);
    this.rules[1] = new Rule ("^\\.",         Token.OR);
    this.rules[2] = new Rule ("^\\-",         Token.NEG);
    this.rules[3] = new Rule ("^\\>",         Token.IMPLY);
    this.rules[4] = new Rule ("^\\=",         Token.EQUAL);
    this.rules[5] = new Rule ("^true",        Token.TRUE);
    this.rules[6] = new Rule ("^false",       Token.FALSE);
    this.rules[7] = new Rule ("^\\(",         Token.PAREN_L);
    this.rules[8] = new Rule ("^\\)",         Token.PAREN_R);
    this.rules[9] = new Rule ("^exists",      Token.EXISTS);
    this.rules[10] = new Rule ("^forall",     Token.FORALL);
    this.rules[11] = new Rule ("^,",          Token.COMMA);
    this.rules[12] = new Rule ("^[a-zA-Z][a-zA-Z0-9]*", Token.ID);
    this.rules[13] = new Rule ("^\\s+",       Token.WHITESPACE);

    this.rules[14] = new Rule ("^*", Token.UNKNOWN);

    this.tokens = tokenize(input);
  }

  private List<Token> tokenize (String input) {
    List<Token> tokens = new LinkedList<Token>();
    Token lastToken;

    do {
      lastToken = findToken(input);
      if (lastToken != Token.WHITESPACE) {
        tokens.add(lastToken);
      }
    } while (inputPosition < input.length() && lastToken != Token.UNKNOWN);

    tokens.add(Token.EOF);

    return tokens;
  }

  private Token findToken (String input) {
    Pattern p;
    Matcher m;
    String result;
    Token resultToken = Token.UNKNOWN;

    for (Rule r : rules) {
      p = Pattern.compile(r.regex());
      m = p.matcher(input.substring(inputPosition));
      if (m.find()) {
        //System.out.println("Found " + r.regex());
        result = m.group();
        inputPosition += result.length();
        return r.token();
      }
    }

    return Token.UNKNOWN;
  }

  public boolean containsErrors () {
    return tokens.contains(Token.UNKNOWN);
  }

  public void print () {
    for (Token t : tokens) {
      System.out.print(t + ", ");
    }
  }
}
