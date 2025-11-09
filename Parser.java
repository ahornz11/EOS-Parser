import java.util.ArrayList;

public class Parser {
    private final Lexer lexer; 
    private Token current; 
    private Token lookahead = null;
    private final ArrayList<String> deklarierteObjekte = new ArrayList<>();
    private final ArrayList<String> methoden = new ArrayList<>();
    

    public Parser(Lexer lexer){
        this.lexer = lexer;     
        this.current = lexer.nextToken(); 
    }
    
    private Token peek(){
        if(lookahead == null){
            lookahead = lexer.nextToken();
        }
        return lookahead;
    }

    private void advance(){
        if(lookahead != null){
            current = lookahead;
            lookahead = null;
        }
        else{
            current = lexer.nextToken();
        }
    }

    private void eat(Token.TokenType type){ //"frisst" token um zum nächsten zu kommen
        if(current.type == type){ 
            advance(); 

        }
        else{
            throw new RuntimeException("Erwartet: " + type + ", gefunden: " + current); 
        }
    }


    
    public void preparseMethoden(){         //überprüft Methoden vorher
        Token token = current;

        while (token.type != Token.TokenType.EOF){
            if(token.type == Token.TokenType.KEYWORD && token.text.equalsIgnoreCase("methode")){
                Token next = lexer.nextToken();
                if (next.type == Token.TokenType.INDICATOR){
                    methoden.add(next.text);
                    token = lexer.nextToken();
                }
                else{
                    throw new RuntimeException("Erwartet: Methodenname nach 'methode', gefunden: " + next);
                }
            }
            else{
                token = lexer.nextToken();
            }
        }
        lexer.reset();
        current = lexer.nextToken();
        lookahead = null;
    }



    public void parseProgram(){    
        preparseMethoden();                         
        while(current.type != Token.TokenType.EOF){     
            parseStatement();
        }
    }

    public void parseStatement(){                           
        
        if(current.type == Token.TokenType.INDICATOR){     
            if (peek().type == Token.TokenType.SYMBOL && peek().text.equals(":=")) {
                parseZuweisung();
            } 
            else if (peek().type == Token.TokenType.SYMBOL && (peek().text.equals(":") || peek().text.equals(","))) {
                parseParameter();
            } 
            else if (peek().type == Token.TokenType.SYMBOL && peek().text.equals(".")) {
                parseFunktionsaufruf();
            }
            else if (peek().type == Token.TokenType.SYMBOL && peek().text.equals("(")) {
                parseFunktionsaufruf();
            }
            else{
                throw new RuntimeException("Erwartet: Parameter, Funktionsaufruf oder Zuweisung, gefunden: " + current);
            }
        }
        else if(current.type == Token.TokenType.KEYWORD){
            if(current.text.equalsIgnoreCase("wiederhole")){
                parseWiederhole();
            }
            else if(current.text.equalsIgnoreCase("solange")){
                parseSolange();
            }
            else if(current.text.equalsIgnoreCase("wenn")){
                parseWenn();
            }
            else if(current.text.equalsIgnoreCase("für")){
                parseFür();
            }
            else if(current.text.equalsIgnoreCase("methode")){
                parseMethode();
            }
        }
        else if(current.type == Token.TokenType.COMMENT){           
            System.out.println("Kommentar: " + current.text);
            eat(Token.TokenType.COMMENT);
        }
        else {
            throw new RuntimeException("Unbekannter Befehl: " + current);
        }
    }
    

    public void parseParameter() {
        
        if(current.type != Token.TokenType.INDICATOR){
            throw new RuntimeException("Erwartet: Parameter Name, gefunden: " + current);
        }
        System.out.println("Parameter Name: " + current);
        deklarierteObjekte.add(current.text);
        eat(Token.TokenType.INDICATOR);

        while (current.type == Token.TokenType.SYMBOL && current.text.equals(",")) {
            eat(Token.TokenType.SYMBOL);

            if(current.type != Token.TokenType.INDICATOR){
                throw new RuntimeException("Erwartet: weiterer Parameter Name nach ',', gefunden: " + current);
            }

            System.out.println("Parameter Name: " + current);
            deklarierteObjekte.add(current.text);
            eat(Token.TokenType.INDICATOR);
        }

        if(current.type != Token.TokenType.SYMBOL || !current.text.equals(":")) {
            throw new RuntimeException("Erwartet: ':', gefunden: " + current);
        }
        eat(Token.TokenType.SYMBOL);

        if(current.type == Token.TokenType.KEYWORD){
            System.out.println("Parameter Typ: " + current);
            eat(Token.TokenType.KEYWORD);
        }
        else{
            throw new RuntimeException("Erwartet: Parameter Typ, gefunden: " + current);
        }
    }

    public void parseWiederhole(){                          
        //all wiederhole methods here
        eat(Token.TokenType.KEYWORD);
        //wiederholeImmer
        if(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("immer")){
            System.out.println("Befehl: wiederhole immer");
            eat(Token.TokenType.KEYWORD);
        }
        //wiederholeNMal
        else if(current.type == Token.TokenType.NUMBER){
            System.out.println("Befehl: wiederhole n mal");
            System.out.println("n: " + current);
            eat(Token.TokenType.NUMBER);

            if(!(current.text.equalsIgnoreCase("mal"))){
                throw new RuntimeException("Erwartet: 'mal', gefunden: " + current);
            }
            eat(Token.TokenType.KEYWORD);
        }
        //wiederholeSolange
        else if(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("solange")){
            System.out.println("Befehl: wiederhole solange (vorne)");
            eat(Token.TokenType.KEYWORD);
            parseBedingung();
        }
        //wiederholeBis
        else if(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("bis")){
            System.out.println("Befehl: wiederhole bis");
            eat(Token.TokenType.KEYWORD);
            parseBedingung();
        }

        while (!(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("*wiederhole"))){ //statement
            parseStatement();
        }
        eat(Token.TokenType.KEYWORD);

        if(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("solange")){
            System.out.println("Befehl: wiederhole solange (hinten)");
            eat(Token.TokenType.KEYWORD);
            parseBedingung();
        }
    }

    public void parseSolange(){                             
        //solangeTue
        System.out.println("Befehl: solange");
        eat(Token.TokenType.KEYWORD);
        parseBedingung();
        if(!(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("tue"))){
            throw new RuntimeException("Erwartet: 'tue', gefunden: " + current);
        }
        eat(Token.TokenType.KEYWORD);
        while(!(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("*solange"))){
            parseStatement();
        }
        eat(Token.TokenType.KEYWORD);
    }

    public void parseWenn(){                                
        eat(Token.TokenType.KEYWORD);
        parseBedingung();
        if(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("dann")){
            System.out.println("Befehl: wenn dann");
        }
        else{
            throw new RuntimeException("Erwartet: 'dann', gefunden: " + current);
        }
        eat(Token.TokenType.KEYWORD);
        while(current.type != Token.TokenType.KEYWORD ||
           (!current.text.equalsIgnoreCase("sonst") && !current.text.equalsIgnoreCase("*wenn"))){
            parseStatement();
        }
        
        //wennDannSonst
        if (current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("sonst")) {
            System.out.println("Befehl: wenn dann sonst");
            eat(Token.TokenType.KEYWORD); 
            while (current.type != Token.TokenType.KEYWORD || !current.text.equalsIgnoreCase("*wenn")) {
                parseStatement();
            }
        }

        if (!(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("*wenn"))) {
            throw new RuntimeException("Erwartet: '*wenn', gefunden: " + current);
        }
        eat(Token.TokenType.KEYWORD);
    }

    public void parseFür(){                                 
        //fuer
        System.out.println("Befehl: für");
        eat(Token.TokenType.KEYWORD);

        if(current.type == Token.TokenType.INDICATOR){
            System.out.println("Objekt: " + current);
            eat(Token.TokenType.INDICATOR);
        }
        else{
            throw new RuntimeException("Erwartet: Objekt, gefunden: " + current);
        }

        while(!(current.type == Token.TokenType.KEYWORD && current.text.equalsIgnoreCase("*für"))){
            parseStatement();
        }
        eat(Token.TokenType.KEYWORD);
    }

    public void parseMethode(){                             
        System.out.println("Befehl: " + current);
        eat(Token.TokenType.KEYWORD);                           

        if(current.type == Token.TokenType.INDICATOR){          
            System.out.println("Methodenname: " + current);                                                   
        }
        else{
            throw new RuntimeException("Erwartet: Methodenname, gefunden: " + current);
        }
        eat(Token.TokenType.INDICATOR);

        if (current.type == Token.TokenType.SYMBOL && current.text.equals("(")) {
            eat(Token.TokenType.SYMBOL);  

            while (!(current.type == Token.TokenType.SYMBOL && current.text.equals(")"))) {
                parseParameter();  

                if (current.type == Token.TokenType.SYMBOL && current.text.equals(";")) {
                    eat(Token.TokenType.SYMBOL);  
                } else if (!(current.type == Token.TokenType.SYMBOL && current.text.equals(")"))) {
                    throw new RuntimeException("Erwartet: ';' oder ')', gefunden: " + current);
                }
            }
            eat(Token.TokenType.SYMBOL);
        }
        while(current.type != Token.TokenType.KEYWORD || !current.text.equals("ende")){ 
            parseStatement();
        }

        if(!(current.type == Token.TokenType.KEYWORD && current.text.equals("ende"))){  
            throw new RuntimeException("Erwartet: 'ende', gefunden: " + current);
        }
        
        eat(Token.TokenType.KEYWORD);
    }


    public void parseFunktionsaufruf(){                     
        if (!(deklarierteObjekte.contains(current.text)||methoden.contains(current.text))) {
            throw new RuntimeException("Objekt oder Methode '" + current.text + "' wurde nicht deklariert.");
        }
        if(current.type == Token.TokenType.INDICATOR){
            System.out.println("Parameter Name: " + current);
        }
        else{
            throw new RuntimeException("Erwartet: Parameter Name, gefunden: " + current);
        }
        eat(Token.TokenType.INDICATOR); 

        if(current.type == Token.TokenType.SYMBOL && current.text.equals(".")){
            eat(Token.TokenType.SYMBOL);
            if(current.type == Token.TokenType.INDICATOR){
                System.out.println("Funktionsname: " + current);
            }
            else{
                throw new RuntimeException("Erwartet: Funktionsname, gefunden: " + current);
            }
            eat(Token.TokenType.INDICATOR);
        }
        else if(!(current.type == Token.TokenType.SYMBOL && current.text.equals("("))){
            throw new RuntimeException("Erwartet: '.', gefunden: " + current);
        }

        
        if(current.type == Token.TokenType.SYMBOL){
            if(current.text.equals("(")){
                eat(Token.TokenType.SYMBOL);
                if(!(current.type == Token.TokenType.SYMBOL && current.text.equals(")"))){
                    parseArgumentListe();                                                                   
                }
                if(!(current.type == Token.TokenType.SYMBOL && current.text.equals(")"))){
                    throw new RuntimeException("Erwartet: ')', gefunden: " + current);
                }
                eat(Token.TokenType.SYMBOL);
            }
            else if(current.text.equals(":=")){
                eat(Token.TokenType.SYMBOL);
                parseAusdruck();                                                                           
            }
            else{
                throw new RuntimeException("Erwartet: '(' oder ':='");
            }
        }
    }

    public void parseBedingung(){                           
        parseAusdruck();
        if(current.type == Token.TokenType.SYMBOL && 
        (current.text.equals("=")||
        current.text.equals("<")||
        current.text.equals(">")||
        current.text.equals("<=")||
        current.text.equals(">="))){
        eat(Token.TokenType.SYMBOL);
        parseAusdruck();
        }
    }

    public void parseArgumentListe(){                       
        parseAusdruck();        

        while(current.type == Token.TokenType.SYMBOL && current.text.equals(",")){
            eat(Token.TokenType.SYMBOL);
            parseAusdruck();
        }
    }

    public void parseAusdruck(){
        if(current.type == Token.TokenType.SYMBOL && current.text.equals("'")){         //für Textfelder
            System.out.println("Ausdruck (string): ");
            System.out.println(current);
            eat(Token.TokenType.SYMBOL);

            while(!(current.type == Token.TokenType.SYMBOL && current.text.equals("'"))){
                System.out.println(current.text);
                eat(current.type);
            }
            System.out.println(current);
            eat(Token.TokenType.SYMBOL);
        }                            
        else if(current.type == Token.TokenType.SYMBOL && current.text.equals("-")){    //negative Zahlen
            eat(Token.TokenType.SYMBOL);
            if(current.type == Token.TokenType.NUMBER){
                System.out.println("Ausdruck (nummer): NUMBER(-" + current.text + ")");
                eat(Token.TokenType.NUMBER);
            }
        }
        else if(current.type == Token.TokenType.NUMBER){                                        //positive Zahlen
            System.out.println("Ausdruck (nummer): " + current);
            eat(Token.TokenType.NUMBER);
        }
        else if(current.type == Token.TokenType.INDICATOR){                                     //wird eine Funktion aufgerufen?
            Token next =  peek();
            if(next.type == Token.TokenType.SYMBOL && next.text.equals(".")){
                System.out.println("Ausdruck (funktionsbezeichner): " + current);
                parseFunktionsaufruf();
            }
            else{
                System.out.println("Ausdruck (bezeichner): " + current);
                eat(Token.TokenType.INDICATOR);
            }
        }
        else if(current.type == Token.TokenType.KEYWORD && (current.text.equalsIgnoreCase("true")||current.text.equalsIgnoreCase("false"))){        //Boolean
            System.out.println("Ausdruck (boolean): " + current);
            eat(Token.TokenType.KEYWORD);
        }
        else if(current.type == Token.TokenType.KEYWORD){                                       //Schlüsselwort
            System.out.println("Ausdruck (Schluesselwort): " + current);
            eat(Token.TokenType.KEYWORD);
        }
        else{
            throw new RuntimeException("Erwartet: Ausdruck, gefunden: " + current);
        }

        while (current.type == Token.TokenType.SYMBOL &&                                        //für rechnungen
           (current.text.equals("+") || current.text.equals("-") ||
            current.text.equals("*") || current.text.equals("/"))) {
            eat(Token.TokenType.SYMBOL);
            parseAusdruck(); 
            System.out.println("Operator: " + current);
        }
    }
    

    public void parseZuweisung(){
        System.out.println("Variable: " + current);
        eat(Token.TokenType.INDICATOR); 

        if(current.type == Token.TokenType.SYMBOL && current.text.equals(":=")){
            eat(Token.TokenType.SYMBOL); 
        }
        else{
            throw new RuntimeException("Erwartet: ':=', gefunden: " + current);
        }

        parseAusdruck();
    }
}
