# FOL-parser
A parser of first order logics

The syntax for first order logics has symbols that stands for objects, 
relations and objects. This gives us three kinds of symbols: predicate symbols
which stands for relations, contant symbols, which stands for objects and
function symbols, which stands for functions.

Sentence -> AtomicSentence | ComplexSentence
AtomicSentence  -> Predicate | Predicate(Term, ...) | Term = Term
ComplexSentence -> (Sentence) 
                   | [Sentence]
                   | -Sentence
                   | Sentence + Sentence
                   | Sentence . Sentence
                   | Sentence > Sentence
                   | Sentence = Sentence
                   | Quantifier [Variable] Sentence

Term            -> Function(Term, ...)
                   | Constant
                   | Variable

Quantifier      -> forall | exists
Constant        -> ID
Variable        -> ID
Predicate       -> TRUE | FALSE | ID
Function        -> ID

Examples:

Richard has at least two brothers ->
There exists an x and a y such that x is a bother of richard and y is a brother
of richard but x and y are different.

exists x, y Brother(x, Richard) + Brother(y, Richard) + -(x = y)

Sentence
  Quantifier
    exists
  Variable
    ID (x)
  Variable
    ID (y)
  Sentence
    ComplexSentence (+)
      Sentence
        AtomicSentence
          Predicate
            ID (Brother)
          Term
            Variable
              ID (x)
          Term
            Constant
              ID (Richard)
      Sentence
        ComplexSentence (+)
          Sentence
            AtomicSentence
              Predicate
                ID (Brother)
              Term
                Variable
                  ID (x)
              Term
                Constant
                  ID (Richard
          Sentence
            ComplexSentence (-)
              Sentence
                ComplextSentence ( () )
                  Sentence
                    AtomicSentence (=)
                      Term
                        Variable
                          Id (x)
                      Term
                        Variable
                          Id (y)

                

          


"True" Is parsed as
Sentence
  AtomicSentence
    Predicate(TRUE)

"Brother(Richard, John)" Is parsed as
Sentence
  AtomicSentence
    Predicate
      Term
        Constant(Richard)
      Term
        Constant(John)

"Married(Father(Richard), Mother(John))" Is parsed as
Sentence
  AtomicSentence
    Predicate
      Term
        Function ("Father")
          Term
            Constant ("Richard")
      Term
        Function ("Mother")
          Term
            Constant ("John")

Richard's left leg is not the brother of John
-Brother(LeftLeg(Richard), John) Is parsed as
Sentence
  ComplexSentence ("-")
    Sentence
      AtomicSentence
        Predicate
          Term
            Function ("Brother")
              Term
                Function ("LeftLeg")
                  Term
                    Constant ("Richard")
              Term
                Constant ("John")

"forall x King(x) > Person(x)
Sentence
  ComplexSentence
    Quantifier
      forall
    Variable
      ID (x)
    Sentence
      ComplexSentence (>)
        Sentence
          AtomixSentence
            Predicate
              ID (King)
            Term
              Variable
                ID (x)
        Sentence
          AtomicSentence
            Predicate
              ID (Person)
            Term
              Variable
                ID (x)

There exists an object x that is a crown AND x is on John's head
"exists x Crown(x) + OnHead(x, John)"
Sentence
  ComplexSentence
    Quantifier (exists)
    Variable
      ID (x)
    Sentence
      ComplexSentence (+)
        Sentence
          AtomicSentence
            Predicate
              ID (Crown)
            Term
              Variable
                ID (x)
        Sentence
          AtomicSentence
            Predicate
              ID (OnHead)
            Term
              Variable
                ID (x)
            Term
              Contant
                ID (John)

Brothers are siblings
forall x forall y Brother(x, y) > Sibling (x, y)
Sentence
  ComplexSentence
    Quantifier
      forall
    Variable
      ID (x)
    Sentence
      ComplexSentence
        Quantifier
          forall
        Variable
          ID (y)
        Sentence
          ComplexSentence (>)
            Sentence
              AtomicSentence
                Predicate
                  ID (Brother)
                Term
                  Variable
                    ID (x)
                Term
                  Variable
                    (y)
            Sentence
              AtomicSentence
                Predicate
                  ID (Sibling)
                Term
                  Variable
                    ID (x)
                Term
                  Variable
                    (y)

Father(John) = Henry
Sentence
  AtomicSentence (=)
    Term
      Function (Father)
      Term
        Constant
          ID (John)
    Term
      Constant
        ID (Henry)
