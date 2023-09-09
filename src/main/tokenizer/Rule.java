package tokenizer;

class Rule {
  private String regex;
  private Token token;

  Rule (String regex, Token token) {
    this.regex = regex;
    this.token = token;
  }

  String regex() {
    return regex;
  }

  Token token() {
    return token;
  }
}
