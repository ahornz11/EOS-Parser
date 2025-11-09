//zerbricht code zu tokens die der Parser lesen kann
import java.util.HashSet;
import java.util.Set;

public class Lexer {
    private final String input; 
    private int position=0;          
    private static final Set<String> KEYWORDS = new HashSet<>();
    private static final Set<String> Funktionen = new HashSet<>();
    private static final Set<String> VordMethoden = new HashSet<>();

    static {
            KEYWORDS.add("wiederhole");     //konstanten
            KEYWORDS.add("immer");    
            KEYWORDS.add("mal");
            KEYWORDS.add("*wiederhole");
            KEYWORDS.add("solange");
            KEYWORDS.add("tue");
            KEYWORDS.add("*solange");
            KEYWORDS.add("bis");
            KEYWORDS.add("wenn");
            KEYWORDS.add("dann");
            KEYWORDS.add("*wenn");
            KEYWORDS.add("sonst");
            KEYWORDS.add("für");
            KEYWORDS.add("*für");
            KEYWORDS.add("methode");
            KEYWORDS.add("ende");
            KEYWORDS.add("gelb");           //farbe
            KEYWORDS.add("rot");
            KEYWORDS.add("grün");
            KEYWORDS.add("blau");
            KEYWORDS.add("weiß");
            KEYWORDS.add("schwarz");
            KEYWORDS.add("braun");
            KEYWORDS.add("hellblau");
            KEYWORDS.add("hellgrün");
            KEYWORDS.add("grau");
            KEYWORDS.add("hellgrau");
            KEYWORDS.add("gestrichelt");    //linien/randart
            KEYWORDS.add("durchgezogen");
            KEYWORDS.add("gepunktelt");
            KEYWORDS.add("gestrichpunktelt");
            KEYWORDS.add("unsichtbar");
            KEYWORDS.add("ausgemalt");      //fuellart
            KEYWORDS.add("schraffiert");
            KEYWORDS.add("kariert");
            KEYWORDS.add("durchsichtig");
            KEYWORDS.add("zentriert");      //ausrichtung
            KEYWORDS.add("linksbuendig");
            KEYWORDS.add("rechtsbuendig");
            KEYWORDS.add("obenbuendig");
            KEYWORDS.add("untenbuendig");
            KEYWORDS.add("true");           //boolean    
            KEYWORDS.add("false");
            KEYWORDS.add("vieleck");
            KEYWORDS.add("rechteck");       //typ
            KEYWORDS.add("quadrat");
            KEYWORDS.add("fenster");
            KEYWORDS.add("ellipse");
            KEYWORDS.add("kreis");
            KEYWORDS.add("dreieck");
            KEYWORDS.add("linie");
            KEYWORDS.add("textfeld");
            KEYWORDS.add("turtle");
            KEYWORDS.add("gruppe");
            KEYWORDS.add("farbe");
            KEYWORDS.add("figur");
            KEYWORDS.add("gefülltefigur");  
            KEYWORDS.add("real");
            KEYWORDS.add("integer");
            KEYWORDS.add("string");
        }

    static {
        Funktionen.add("rechteck");
        Funktionen.add("quadrat");
        Funktionen.add("fenster");
        Funktionen.add("ellipse");
        Funktionen.add("kreis");
        Funktionen.add("dreieck");
        Funktionen.add("linie");
        Funktionen.add("textfeld");
        Funktionen.add("turtle");
        Funktionen.add("gruppe");
        Funktionen.add("farbe");
        Funktionen.add("figur");
        Funktionen.add("gefülltefigur");
    }

    static{         //prolly useless
        VordMethoden.add("verschieben");
    }
       

    public Lexer(String input){ 
        this.input = input; 
    }


    private void skipWhitespace(){ //Methode überspringt Leerzeichen und Zeilenumbrüche im überprüften Code
        while (position<input.length() && Character.isWhitespace(input.charAt(position))){
            position++;
        }
    }

    

    public Token nextToken(){  
        skipWhitespace();
        if(position >= input.length()){                            //EOF
            return new Token(Token.TokenType.EOF,"");                                       
        }

        
        char ch = input.charAt(position); 


        if(ch == '/' && position + 1 < input.length() && input.charAt(position + 1) == '/'){    //COMMENT
            position += 2;
            int start = position;
            while (position < input.length() && input.charAt(position) != '\n'){
                position++;
            }
            return new Token(Token.TokenType.COMMENT, input.substring(start, position));
        }

        if(ch == '"'){                                                                          //STRING
            position++;
            int start = position;
            while (position < input.length() && input.charAt(position) != '"'){
                position++;
            }
            if(position >= input.length()){
                return new Token(Token.TokenType.INVALID, input.substring(start - 1));  
            }
            String str = input.substring(start, position);
            position++; //überspringt schliesendes "
            return new Token(Token.TokenType.STRING, str); 
        }

        if(Character.isDigit(ch)){                                                              //NUMBER
            int start = position;
            while (position < input.length() && Character.isDigit(input.charAt(position))){ 
                position++;
            }
            if(position < input.length() && input.charAt(position) == '.' && (position +1 <  input.length() && Character.isDigit(input.charAt(position+1)))){   
                position++;
                while(position < input.length() && Character.isDigit(input.charAt(position))){
                    position++;
                }
            }
            return new Token(Token.TokenType.NUMBER, input.substring(start, position)); //returnt die zahl; input substring schneidet alle ziffern der zahl von start bis position aus
        }

        if(Character.isLetter(ch)||ch == '*'){                                                  //KEYWORD + INDICATOR
            int start = position;
            while(position < input.length() && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_'|| input.charAt(position) == '*')){
                position++;
            }
            String word = input.substring(start, position);
            if(KEYWORDS.contains(word.toLowerCase())){
                return new Token(Token.TokenType.KEYWORD, word);                                //KEYWORD
            }
            else{
                return new Token(Token.TokenType.INDICATOR, word);                              //INDICATOR
            }
        }

        if(ch == ':' && position + 1 < input.length() && input.charAt(position + 1) == '='){    //SYMBOL
            position += 2;
            return new Token(Token.TokenType.SYMBOL, ":=");
        }
        if(":;/*,.()+-=<>'".indexOf(ch)>=0){                                                       
            position++;
            return new Token(Token.TokenType.SYMBOL, Character.toString(ch));
        }
                                                                                  
        return new Token(Token.TokenType.INVALID, String.valueOf(input.charAt(position++)));    //INVALID

        
    }
    
    public void reset(){
        position = 0;
    }
}
