
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
	
	//selv implementerede sorterings algoritme med mergesort ArrayList version
	public static ArrayList<Brøk> sorting(ArrayList<Brøk> list)
	{
		//test om listen er 1 lang og derfor gået igennem eller sorteret
		if (list.size() <= 1)
		{
			return list;
		}
		
		//lav arraylist til venstre og højre del af listen
		ArrayList<Brøk> leftList = new ArrayList<Brøk>();
		ArrayList<Brøk> rightList = new ArrayList<Brøk>();
		
		for (int i = 0; i < list.size(); i++)
		{
			//tilføj første halvdel
			if (i < list.size() / 2)
			{
				leftList.add(list.get(i));
			}
			//tilføj anden halvdel
			else
			{
				rightList.add(list.get(i));
			}
			
		}
			//kalder mergeSort rekursivt indtil listerne er delt op
			leftList = sorting(leftList);
			rightList = sorting(rightList);
			
			//returnere listen sorteret
			return merge(leftList, rightList);
	}
	//liste der samler right og left og sammentidig sammeligner dem ArrayList version
	public static ArrayList<Brøk> merge(ArrayList<Brøk> left, ArrayList<Brøk> right)
	{
		ArrayList<Brøk> result = new ArrayList<Brøk>();
		
		while (left.size() != 0 && right.size() != 0)
		{
			//ser hvad for en der er størst med compare metode
			// hvis compare returner et tal under 0 er første brøk i left mindre end første brøk i right og skal derfor tilføjes til result
			if (left.get(0).compareTo(right.get(0)) < 0)
			{
				//tilføjer første brøk i left til result hvis den er større end første brøk i right
				result.add(left.get(0));
				//fjerne brøkken fra left efter den er tilføjet til result
				left.remove(0);
			}
			// gør det modsatte for når første brøk i right er større end første i left
			else
			{
				result.add(right.get(0));
				right.remove(0);
			}
		}
		
		//nu er der rester i en af listerne og det bliver rykket til result
		while (left.size() != 0)
		{
			result.add(left.get(0));
			left.remove(0);
		}
		
		while (right.size() != 0)
		{
			result.add(right.get(0));
			right.remove(0);
		}
		//returnere result listen
		return result;
	}
	
	//array version af mergesort
	public static Brøk[] sorting(Brøk[] array)
	{
		//test om listen er 1 lang og derfor gået igennem eller sorteret
		if (array.length <= 1)
		{
			return array;
		}
		
		//lav arraylist til venstre og højre del af listen
		Brøk[] leftArray = Arrays.copyOfRange(array, 0, array.length / 2);
		Brøk[] rightArray = Arrays.copyOfRange(array, array.length / 2, array.length);
		
		//rekursivt kalder mergeSort indtil listerne er delt op
		leftArray = sorting(leftArray);
		rightArray = sorting(rightArray);
		
		//returnere listen sorteret
		return merge(leftArray, rightArray);
	}
	
	//liste der samler right og left og sammentidig sammeligner dem
	public static Brøk[] merge(Brøk[] left, Brøk[] right)
	{
		ArrayList<Brøk> result = new ArrayList<Brøk>();
		
		while (left.length != 0 && right.length != 0)
		{
			//ser hvad for en der er størst med compare metode
			// hvis compare returner et tal under 0 er første brøk i left mindre end første brøk i right og skal derfor tilføjes til result
			if (left[0].compareTo(right[0]) < 0)
			{
				//tilføjer første brøk i left til result hvis den er større end første brøk i right
				result.add(left[0]);
				//fjerne brøkken fra left efter den er tilføjet til result
				left = Arrays.copyOfRange(left, 1, left.length);
			}
			// gør det modsatte for når første brøk i right er større end første i left
			else
			{
				result.add(right[0]);
				right = Arrays.copyOfRange(right, 1, right.length);
			}
		}
		
		//nu er der rester i en af listerne og det bliver rykket til result
		while (left.length != 0)
		{
			result.add(left[0]);
			left = Arrays.copyOfRange(left, 1, left.length);
		}
		
		while (right.length != 0)
		{
			result.add(right[0]);
			right = Arrays.copyOfRange(right, 1, right.length);
		}
		//returnere result listen
		Brøk resultArray[] = new Brøk[result.size()];
		return result.toArray(resultArray);
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
		Brøk k1 = new Brøk(4, 5);
		Brøk k2 = new Brøk(58, 62);
		Brøk k3 = new Brøk(59, 24);
		Brøk k4 = new Brøk(33, 31);
		Brøk k5 = new Brøk(30, 68);
		Brøk k6 = new Brøk(51, 22);
		Brøk k7 = new Brøk(33, 84);
		Brøk k8 = new Brøk(90, 79);
		Brøk k9 = new Brøk(52, 7);
        mineBrøker.add(f2);
        mineBrøker.add(f1);
        mineBrøker.add(f3);
		mineBrøker.add(k1);
		mineBrøker.add(k2);
		mineBrøker.add(k3);
		mineBrøker.add(k4);
		mineBrøker.add(k5);
		mineBrøker.add(k6);
		mineBrøker.add(k7);
		mineBrøker.add(k8);
		mineBrøker.add(k9);
		
		
        // sortering af listen ovenfor
		ArrayList<Brøk> mergeList = sorting(mineBrøker);
        Collections.sort(mineBrøker);
		
		System.out.println("default sorting");
        for (Brøk f : mineBrøker) {
            System.out.print("  " + f);
        }
        System.out.println("\nmerge sort med ArrayList");
		
		for (Brøk f : mergeList) {
            System.out.print("  " + f);
        }
        System.out.println();
		
		//test af mergesort med array i stedet for ArrayList
		Brøk test[] = { f2, f1, f3, k1, k2, k3, k4, k5, k6, k7, k8, k9};
		
		test = sorting(test);
		System.out.println("merge sort med array");
		
		for (Brøk f : test)
		{
			System.out.print("  " + f);
		}
        
		
		//test med tilfældige brøkker
		Random rand = new Random();
		ArrayList<Brøk> randomMerge = new ArrayList<Brøk>();
		ArrayList<Brøk> collectionSortRandom = new ArrayList<Brøk>();
		Brøk randomMergeArray[] = new Brøk[11];
		
		for (int i = 0; i <= 10; i++)
		{
			// + 1 for ellers kunne den blive 0
			Brøk brøk = new Brøk(rand.nextInt(100) + 1, rand.nextInt(100) + 1);
			randomMerge.add(brøk);
			collectionSortRandom.add(brøk);
			randomMergeArray[i] = brøk;
		}
		//sortere dem med de forskellige metoder
		Collections.sort(collectionSortRandom);
		randomMerge = sorting(randomMerge);
		randomMergeArray = sorting(randomMergeArray);
		
		System.out.println("\n\n\nTest med tilfældige brøkker\n\nCollections.sort()");
		for (Brøk brøk : collectionSortRandom)
		{
			System.out.print(brøk + " ");
		}
		System.out.println("\n");
		System.out.println("mergeSort med ArrayList");
		
		for (Brøk brøk : randomMerge)
		{
			System.out.print(brøk + " ");
		}
		
		System.out.println("\n\nmerge sort med array");
		
		for (Brøk brøk : randomMergeArray)
		{
			System.out.print(brøk + " ");
		}
		
		System.out.println("\n");
		
		
        // test af ulovlig brøk (dvs. nævner = 0)
        Brøk fEx = new Brøk(13, 0);    // skal kaste exception
        System.out.println(fEx);
        
  }   // main
}   // class
