<Program> ::= <Statement>* 
<Statement> ::= <Parameter>|<Zuweisung>|<WiederholeImmer>|<WiederholeNMal>|<SolangeTue>|<WiederholeSolange>|<WiederholeBis>|<WennDann>|<WennDannSonst>|<Für>|<Methode>|<Funktionsaufruf>|<Kommentar>
<Zuweisung> ::= <Bezeichner>"."<Funktionsname>":="<Ausdruck>
<Parameter> ::= <Bezeichner> ":" <Typ> 
<Bezeichner> ::= <Buchstabe> {<Buchstabe>|<Ziffer>}
<Buchstabe> ::= "a"|"b"|...|"z"|"A"|"B"|...|"Z"
<Ziffer> ::= "0"|"1"|"2"|...|"9"
<Typ> ::= "RECHTECK"|"QUADRAT"|"FENSTER"|"ELLIPSE"|"KREIS"|"DREIECK"|"LINIE"|"TEXTFELD"|"TURTLE"|"GRUPPE"|"FARBE"|"FIGUR"|"GEFÜLLTEFIGUR"|"integer"|"real"
<WiederholeImmer> ::= "wiederhole" "immer" <Statement>* "*wiederhole"
<WiederholeNMal> ::= "wiederhole" <Zahl> "mal" <Statement>* "*wiederhole" 
<Zahl> ::= <Ziffer>{<Ziffer>}
<SolangeTue> ::= "solange" <Bedingung> "tue" <Statement>* "*solange"
<Bedingung> ::= <Vergleich>
<Vergleich> ::= <Ausdruck> <Vergleichsoperator> <Ausdruck>
<Ausdruck> ::= <Integer>|<Bezeichner>|(<Boolean>)
<Vergleichsoperator> ::= "="|"<"|">"|"<="|">="
<WiederholeSolange> ::= "wiederhole" "solange" <Bedingung> <Statement>* "*wiederhole" | "wiederhole" <Statement>* "*wiederhole" "solange" <Bedingung>
<WiederholeBis> ::= "wiederhole" <Statement>* "*wiederhole" "bis" <Bedingung>
<WennDann> ::= "wenn" <Bedingung> "dann" <Statement> "*wenn"
<WennDannSonst> ::= "wenn" <Bedingung> "dann" <Statement> "sonst" <Statement> "*wenn"
<Für> ::= "für" <Objekt> <Statement> "*für"
<Objekt> ::= <Bezeichner>
<Zeichen> ::= <Ziffer>|<Buchstabe>|" "|"."|","|"!"|"?"|...
<Methode> ::= "methode" <Methodenname> "(" <> ")" <Statement>* "ende"
<Methodenname> ::= <Buchstabe> {<Buchstabe>}
<Funktionsaufruf> ::= <Bezeichner> "." <Funktionsname> "(" <ArgumentListe>? ")" | <Bezeichner> "." <vordefinierteFunktion>
<Kommentar> ::= "//" {<Zeichen>}
<Funktionsname> ::= <Bezeichner>
<vordefinierteFunktion> ::= <AllgemeineFunktion>|<RechteckFunktion>|<QuadratFunktion>|<FensterFunktion>|<EllipseFunktion>|<KreisFunktion>|<DreieckFunktion>|<LinieFunktion>|<TextfeldFunktion>|<TurtleFunktion>|<GruppeFunktion>|<FarbeFunktion>|<FigurFunktion>|<GefülltefigurFunktion>

<AllgemeineFunktion> ::= "verschieben" "("<xKoordinate>","<yKoordinate>")"|"drehen" "("<Drehwinkel>")"|"drehenUm" "("<xDrehpunkt>","<yDrehpunkt>","<Drehwinkel>")"|"strecken" "("<streckungsFaktor>")"|"streckenAn" "("<xZentrum>","<yZentrum>","<streckungsFaktor>")"|"sichtbarSetzen" "("<Boolean>")"|"randstärkeSetzen" "("<IntValue>")"|"randartSetzen" "("<linienart>")"|"randfarbeSetzen" "("<Farbe>")"

<RechteckFunktion> ::= <AllgemeineFunktion>|"eckenSetzen" "("<links>","<oben>","<rechts>","<unten>")"|"linksObenSetzen" "("<links>","<oben>")"|"rechtsUntenSetzen" "("<rechts>","<unten>")"|"verschiebenNach" "("<links>","<oben>")"|"linksSetzen" "("<links>")"|"obenSetzen" "("<oben>")"|"rechtsSetzen" "("<rechts>")"|"untenSetzen" "("<unten>")"|"breiteSetzen" "("<IntValue>")"|"höheSetzen" "("<IntValue>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"

<QuadratFunktion> ::= <AllgemeineFunktion>|"linksObenSetzen" "("<links>","<oben>")"|"verschiebenNach" "("<links>","<oben>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"|"seitenlängeSetzen" "("<IntValue>")"

<FensterFunktion> ::= "verschiebe" "("<xKoordinate>","<yKoordinate>")"|"zeichne" "("<Figur>")"|"gitterein" "("")"|"gitteraus" "("")"|"strichabstandSetzen" "("<IntValue>")"|"nameSetzen" "("<String>")"|"linksSetzen" "("<links>")"|"obenSetzen" "("<oben>")"|"breiteSetzen" "("<IntValue>")"|"höheSetzen" "("<IntValue>")"|"hintergrundfarbeSetzen" "("<Farbe>")"|"gitterfarbeSetzen" "("<Farbe>")"

<EllipseFunktion> ::= <AllgemeineFunktion>|"mittelpunktSetzen" "("<xKoordinate>","<yKoordinate>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"|"radiusxSetzen" "("<IntValue>")"|"radiusySetzen" "("<IntValue>")"|"mittexSetzen" "("<IntValue>")"|"mitteySetzen" "("<IntValue>")"

<KreisFunktion> ::=  <AllgemeineFunktion>|"mittelpunktSetzen" "("<xKoordinate>","<yKoordinate>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"|"radiusSetzen" "("<IntValue>")"|"mittexSetzen" "("<IntValue>")"|"mitteySetzen" "("<IntValue>")"

<DreieckFunktion> ::= <AllgemeineFunktion>|"verschiebenNach" "("<links>","<oben>")"|"eckenSetzen"(<x1>","<y1>","<x2>","<y2>","<x3>","<y3>")"|"spiegleX" "("")"|"spiegleY" "("")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"

<LinieFunktion> ::= <AllgemeineFunktion>|"verschiebenNach" "("<links>","<oben>")"|"punkt1Setzen" "("<xKoordinate>","<yKoordinate>")"|"punkt2Setzen" "("<xKoordinate>","<yKoordinate>")"|"endpunkteSetzen" "("<x1>","<y1>","<x2>","<y2>")"|"x1Setzen" "("<x1>")"|"y1Setzen" "("<y1>")"|"x2Setzen" "("<x2>")"|"y2Setzen" "("<y2>")"|"farbeSetzen" "("<Farbe>")"|"linienStärkeSetzen" "("<IntValue>")"

<TextfeldFunktion> ::= <AllgemeineFunktion>| "eckenSetzen" "("<links>","<oben>","<rechts>","<unten>")"//rechteck, textfeld, gruppe, figur|"linksObenSetzen" "("<links>","<oben>")"|"rechtsUntenSetzen" "("<rechts>","<unten>")"|"verschiebenNach" "("<links>","<oben>")"|"zeileHinzufügen" "("<String>")"|"zeilenLöschen" "("")"|"linksSetzen" "("<links>")"|"obenSetzen" "("<oben>")"|"rechtsSetzen" "("<rechts>")"|"untenSetzen" "("<unten>")"|"breiteSetzen" "("<IntValue>")"|"höheSetzen" "("<IntValue>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"|"groesseAutomatischAnpassenSetzen" "("<Boolean>")"|"ausrichtungHorizontalSetzen" "("<IntValue>")"|"ausrichtungVertikalSetzen" "("<IntValue>")"|"schriftartSetzen" "("<String>")"|"schriftgrößeSetzen" "("<IntValue>")"|"schriftfarbeSetzen" "("<Farbe>")"|"durchsichtigSetzen" "("<Boolean>")"

<TurtleFunktion> ::= "verschieben" "("<xKoordinate>","<yKoordinate>")"|"strecken" "("<streckungsFaktor>")"|"drehen" "("<Drehwinkel>")"|"drehenUm" "("<xDrehpunkt>","<yDrehpunkt>","<Drehwinkel>")"|"streckenAn" "("<xZentrum>","<yZentrum>","<streckungsFaktor>")"|"vor" "("<RealValue>")"|"zurück" "("<RealValue>")"|"linksdrehen" "("<RealValue>")"|"rechtsdrehen" "("<RealValue>")"|"setzeXY" "("<x>","<y>")"|"stiftAuf" "("")"|"stiftAb" "("")"|"allesLöschen" "("")"|"aufPunkt" "(")" ":" <Boolean>|"vorPunkt" "(")" ":" <Boolean>|"linienStärkeSetzen" "("<IntValue>")"|"turtleSichtbarSetzen" "("<Boolean>")"|"xSetzen" "("<x>")"|"ySetzen" "("<y>")"|"kursSetzen" "("<RealValue>")"

<GruppeFunktion> ::= <AllgemeineFunktion>| "eckenSetzen" "("<links>","<oben>","<rechts>","<unten>")"//rechteck, textfeld, gruppe, figur|"linksObenSetzen" "("<links>","<oben>")"|"rechtsUntenSetzen" "("<rechts>","<unten>")"|"verschiebenNach" "("<links>","<oben>")"|"kopiere" "("<Figur>")"|"schlucke" "("<Figur>")"|"spiegleX" "("")"|"spiegleY" "("")"|"linksSetzen" "("<links>")"|"obenSetzen" "("<oben>")"|"rechtsSetzen" "("<rechts>")"|"untenSetzen" "("<unten>")"|"breiteSetzen" "("<IntValue>")"|"höheSetzen" "("<IntValue>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"

<FarbeFunktion> ::= "setzergb" "("<rotwert>","<grünwert>","<blauwert>")"|"rotSetzen" "("<rotwert>")"|"grünSetzen" "("<grünwert>")"|"blauSetzen" "("<blauwert>")"

<FigurFunktion> ::= <AllgemeineFunktion>| "eckenSetzen" "("<links>","<oben>","<rechts>","<unten>")"//rechteck, textfeld, gruppe, figur|"linksObenSetzen" "("<links>","<oben>")"|"rechtsUntenSetzen" "("<rechts>","<unten>")"|"verschiebenNach" "("<links>","<oben>")"|"linksSetzen" "("<links>")"|"obenSetzen" "("<oben>")"|"rechtsSetzen" "("<rechts>")"|"untenSetzen" "("<unten>")"|"breiteSetzen" "("<IntValue>")"|"höheSetzen" "("<IntValue>")"

<GefülltefigurFunktion> ::= <AllgemeineFunktion>|"eckenSetzen" "("<links>","<oben>","<rechts>","<unten>")"//rechteck, textfeld, gruppe, figur|"linksObenSetzen" "("<links>","<oben>")"|"rechtsUntenSetzen" "("<rechts>","<unten>")"|"verschiebenNach" "("<links>","<oben>")"|"linksSetzen" "("<links>")"|"obenSetzen" "("<oben>")"|"rechtsSetzen" "("<rechts>")"|"untenSetzen" "("<unten>")"|"breiteSetzen" "("<IntValue>")"|"höheSetzen" "("<IntValue>")"|"füllfarbeSetzen" "("<Farbe>")"|"füllartSetzen" "("<füllart>")"

<Integer> ::= ["-"] <Zahl>    
<Real> ::= <Zahl>|<Zahl>"."<Ziffer>+
<Boolean> ::= "true" | "false"
<String> ::= '"' {<Zeichen>} '"' //zeichen ohne '"'
<IntValue> ::= <Integer> | <Bezeichner>
<RealValue> ::= <Real> | <Bezeichner>
<xKoordinate> ::= <IntValue>
<yKoordinate> ::= <IntValue>
<links> ::= <IntValue>
<rechts> ::= <IntValue>
<oben> ::= <IntValue>
<unten> ::= <IntValue>
<streckungsFaktor> ::= <RealValue>
<Drehwinkel> ::= <RealValue>
<xDrehpunkt> ::= <IntValue>
<yDrehpunkt> ::= <IntValue>
<xZentrum> ::= <IntValue>
<yZentrum> ::= <IntValue>
<x1> ::= <IntValue>
<x2> ::= <IntValue>
<x3> ::= <IntValue>
<y1> ::= <IntValue>
<y2> ::= <IntValue>
<y3> ::= <IntValue>
<x> ::= <RealValue>
<y> ::= <RealValue>
<rotwert> ::= <IntValue>
<grünwert> ::= <IntValue>
<blauwert> ::= <IntValue>
<Farbe> ::= gelb|rot|grün|blau|weiß|schwarz|braun|hellblau|hellgrün|grau|hellgrau
<linienart> ::= <IntValue>|gestrichelt|durchgezogen|gepunktelt|gestrichpunktelt|unsichtbar
<füllart> ::= <IntValue>|ausgemalt|schraffiert|kariert|durchsichtig 
<Figur> ::= <Bezeichner> 
<ArgumentListe> ::= <Ausdruck> {"," <Ausdruck>}


