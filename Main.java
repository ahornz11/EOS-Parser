

public class Main {

    static String input;
    public static void main(String[] args){ //EOS Quellcode
       String input = """ 

        r:RECHTECK
        f:FENSTER

        f.zeichne(r)
        r.EckenSetzen(-10,30,60,-50)
        r.FüllfarbeSetzen(gelb)

        r.verschieben(30,20)
        wiederhole 30 mal
            r.drehen(2)
        *wiederhole

                    """;
        
            System.out.println("start");
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer);

            parser.parseProgram();

            System.out.println("fertig");
        
    }
}

