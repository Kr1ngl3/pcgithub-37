
// Gruppens navne
/*
* Opgave: Aflevering 04 
* Modificeret kode: Kenneth Johansen
* 					Simon Meyer
* 					Thomas Bech
*/

// Windows' default codepage i terminalen er 850.
// Hvis Java kildekode filen er gemt i codepage UTF-8,
// skal vi benytte følgende:
//    Kør programmet ved fortolkning:   java -Dfile.encoding="UTF-8" Brøk.java
//        eller
//    Kompiler programmet:      javac -encoding "UTF-8" Brøk.java
//    Kør dernæst programmet:   java Brøk

// Biblioteker til brug ved afprøvning:
import java.util.ArrayList;
import java.util.Collections;

/**
  * Klassen Brøk er en udvidelse af Java' indbyggede klasse Number.
  * Klassen skal implementere Comparable (dvs. brøker skal kunne sammenlignes).
  * Sammenligning kan f.eks. benyttes ved sortering af brøker (i en liste).
  */
public class Brøk extends Number implements Comparable<Brøk> {

    // local fields / attributes
    private Integer tæller;
    private Integer nævner;

    /** Constructor 1: Opretter en ny instance af Brøk,
      *                som t/n, givet heltallene t og n
      *                Hvis n er 0 skal programmet stoppes med exception.
      */
    public Brøk(Integer t, Integer n) throws IllegalArgumentException {
        if (n == 0) {
            throw new IllegalArgumentException("Nævner = 0 er ulovlig !");
        }
		tæller = t;
		nævner = n;
    }

    /** Constructor 2: Opretter en ny instance af Brøk,
      *                givet et heltal t
      */
    public Brøk(Integer t) {
        tæller = t;
        nævner = 1;
    }

    /** Plus (addition): 
      *   Opretter en ny instance af Brøk, 
      *   som summen af brøkerne t/n og other
      */
    public Brøk plus(Brøk other) {
        Integer nyTæller = other.getnævner() * tæller + nævner * other.gettæller();
        //ny nævner ved at gange de 2 nævnere sammen
		Integer nyNævner = other.getnævner() * nævner; 
		// bruger sfd() til at finde den
		Integer fællesNævner = sfd(nyTæller, nyNævner);
        
        return new Brøk(nyTæller / fællesNævner, nyNævner / fællesNævner );
    }

    /** Plus (addition): 
      *   Opretter en ny instance af Brøk, 
      *   som tallet other
      */
    public Brøk plus(Integer other) {
        return plus(new Brøk(other));
    }

    /** Minus (subtraktion): 
      *   Opretter en ny instance af Brøk, 
      *   som differencen af brøkerne t/n og other
      */
    public Brøk minus(Brøk other) {
		//vi finder ny tæller og nævner på samme måde somm plus bare med minus
        Integer nyTæller = other.getnævner() * tæller - nævner * other.gettæller();
        Integer nyNævner = other.getnævner() * nævner;
        Integer fællesNævner = sfd(nyTæller, nyNævner);
		
		return new Brøk(nyTæller / fællesNævner, nyNævner / fællesNævner);
    }

    /** Minus (subtraktion): 
      *   Opretter en ny instance af Brøk, 
      *   som tallet other
      */
    public Brøk minus(Integer other) {
		//return minus metode med en ny brøk med tæller other 
        return minus(new Brøk(other));
    }

    /** Gange (multiplikation): 
      *   Opretter en ny instance af Brøk, 
      *   som produktet af brøkerne t/n og other
      */
    public Brøk gange(Brøk other) {
        Integer nyTæller, nyNævner, fællesNævner;
		//for at gange, ganger man de 2 tæller sammen og de 2 nævnere sammen
        nyTæller = other.gettæller() * tæller;
        nyNævner = other.getnævner() * nævner;
        fællesNævner = sfd(nyTæller, nyNævner);
		
		return new Brøk(nyTæller / fællesNævner, nyNævner / fællesNævner);
    }

    /** Gange (multiplikation): 
      *   Opretter en ny instance af Brøk, 
      *   som tallet other
      */
    public Brøk gange(Integer other) {
		//return gange metode med en ny brøk med tæller other 
        return gange(new Brøk(other));
    }

    /** Divider (division): 
      *   Opretter en ny instance af Brøk, 
      *   som division af brøkerne t/n og other
      */
    public Brøk divider(Brøk other) {
		//for at dividere ganger man tælleren for den første med nævneren med den anden og omvendt
        Integer nyTæller, nyNævner, fællesNævner;
        nyTæller = tæller * other.getnævner();
        nyNævner = nævner * other.gettæller();
        fællesNævner = sfd(nyTæller, nyNævner);
		
		return new Brøk(nyTæller / fællesNævner, nyNævner / fællesNævner);
    }

    /** Divider (division): 
      *   Opretter en ny instance af Brøk, 
      *   som division med tallet other
      */
    public Brøk divider(Integer other) {
        return divider(new Brøk(other));
    }


    /** Getter for attribut tæller */
    public Integer gettæller() {
        return this.tæller;
    }

    /** Setter for attribut tæller */
    public void settæller(Integer tæller) {
        this.tæller = tæller;
    }

    /** Getter for attribut nævner */
    public Integer getnævner() {
        return this.nævner;
    }

    /** Setter for attribut nævner */
    public void setnævner(Integer nævner) {
        this.nævner = nævner;
    }

    /** Forkort: 
      *   Opdaterer tæller og nævner,
      *   vha. det fundne SFD heltal
      */
    public void forkort() {
        Integer fællesNævner;

        fællesNævner = sfd(tæller, nævner);
        tæller /= fællesNævner;
        nævner /= fællesNævner;
    }
	

    /** "Print" metoden toString */
    public String toString() {
        if (tæller < 0 && nævner < 0) {
            tæller *= -1; nævner *= -1;
        }
        if (tæller > 0 && nævner < 0) {
            tæller *= -1; nævner *= -1;
        }
        return tæller.toString() + "/" + nævner.toString();
    }

    /** compareTo: sammenligningsmetode til brug ved sortering
      *   returnerer et heltal > 0 hvis brøken t/n er større end brøken other,
      *   returnerer heltallet 0 hvis brøkerne t/n og other er ens,
      *   returnerer et heltal < 0 hvis brøken t/n er mindre end brøken other.
      */
	  
	  
	// gange den ene nævner med den andens tæller og omvendt for at få en fælles nævner så brøkkerne kan sammenlignes
    public int compareTo(Brøk other) {

		Integer t1 = tæller * other.getnævner();
		Integer t2 = other.gettæller() * nævner;
        return t1 - t2;

    } 

    /** SFD (største fælles divisor)
      * Hjælpefunktion, der skal finde SFD for heltallene t og n
      * (herved kan brøken t/n forkortes).
      * Returnerer det fundne SFD heltal.
      */
	  
    private static Integer sfd(Integer t, Integer n) {
        // Always set t and n to positive
        t = ( t > 0) ? t : -t;
        n = ( n > 0) ? n : -n;
        while (t != n) {
            if (t > n)
                t -= n;
            else
                n -= t;
        }
        return t;    // SFD
    }
    
    /** equals: Sammenligninger to brøker
      *   returnerer true hvis brøkerne er ens,
      *   returnerer false hvis brøkerne er forskellige.
      */
    
	
	// gange den ene nævner med den andens tæller og omvendt for at få en fælles nævner så brøkkerne kan sammenlignes
	public boolean equals(Brøk other) {
		
		Integer t1 = tæller * other.getnævner();
		Integer t2 = other.gettæller() * nævner;
		if (t1 == t2)
			return true;
		else
			return false;

    }

    /** Fire funktioner der skal konvertere brøken t/n
      * til double, eller float, eller int, eller long.
      */
    public double doubleValue() {
        return tæller.doubleValue() / nævner.doubleValue();
    }

    public float floatValue() {
        return tæller.floatValue() / nævner.floatValue();
    }

    public int intValue() {
        return tæller.intValue() / nævner.intValue();
    }

    public long longValue() {
        return tæller.longValue() / nævner.longValue();
    }

    /** main: Hovedprogram
      *   Skal teste ALLE de ovenstående metoder og attributter.
      */
    public static void main(String[] args) {
        // instancer af brøker:
        Brøk f1 = new Brøk(1, 2);
        Brøk f2 = new Brøk(20, 3);
        Brøk f3 = new Brøk(100, 4);
        Brøk f4 = new Brøk(6, 9);
        Brøk f5 = new Brøk(2, 3);
        Brøk f6 = new Brøk(-3, 7);
        Brøk f7 = new Brøk(24, -30);
        Brøk f8 = new Brøk(-24, -30);
                
        // test af print af brøker:
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
        System.out.println(f7);
        System.out.println(f8);
        
        // test af plus:
        System.out.println(f1.plus(f2));
        System.out.println(f1.plus(1));
        
        // test af minus:
        Brøk tmp = f1.minus(f2);
        System.out.println(f1.minus(f2));
        System.out.println("t= " + tmp.gettæller() + " n= " + tmp.getnævner());
        System.out.println(f1.minus(1));
        
        // test af gange:
        System.out.println(f1.gange(f2));
        System.out.println(f1.gange(2));
        
        // test af divider:
        System.out.println(f1.divider(f2));
        System.out.println(f1.divider(2));	
        
        // test af forkort:
        f3.forkort();
        System.out.println(f3);
        f7.forkort();
        System.out.println(f7);
        f8.forkort();
        System.out.println(f8);
        
        // test af negativ brøk:
        System.out.println(f6);
        System.out.println(f8);
        
        // test af equals:
        System.out.println(f2.equals(f3));
        System.out.println(f4.equals(f5));
        
        // test af konverterings-funktioner:
        System.out.println(f2.doubleValue());
        System.out.println(f2.floatValue());
        System.out.println(f2.intValue());
        System.out.println(f2.longValue());

        // test af liste med brøker:
        ArrayList<Brøk> mineBrøker = new ArrayList<Brøk>();
        mineBrøker.add(f2);
        mineBrøker.add(f1);
        mineBrøker.add(f3);
        // sortering af listen ovenfor
        Collections.sort(mineBrøker);

        for (Brøk f : mineBrøker) {
            System.out.print("  " + f);
        }
        System.out.println();
        
        // test af ulovlig brøk (dvs. nævner = 0)
        Brøk fEx = new Brøk(13, 0);    // skal kaste exception
        System.out.println(fEx);
        
  }   // main
}   // class
