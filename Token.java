
//gibt token so aus: type(text) z.B. NUMBER(42) 


public class Token {
    public final TokenType type;  
    public final String text;     

    public Token(TokenType type, String text){ 
        this.type=type; 
        this.text=text;
    }

    public String toString(){ 
        return type + "(" + text + ")";
    }




    //tokentype>token>lexer>parser

    public enum TokenType { //liste aller Tokens/Konstanten
        KEYWORD, //wörter mit vordefinierten Eigenschaften; wiederhole, solange, wenn, methode, Ellipse, Quadrat 
        SYMBOL, //vergleichsoperatoren, Klammern usw.
        INDICATOR, //Namen von Funktionen, Variablen usw.
        NUMBER, 
        STRING,
        COMMENT,
        EOF, //end of file (ende vom eingabetext)
        INVALID
    }
}
