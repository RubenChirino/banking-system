package banking;

import java.util.Random;
import java.util.regex.Pattern;

public final class Util {

	private Util() {}
	
	public static String generateBankAccountNumber() {
        Random rand = new Random();
        String card = "BE";
        for (int i = 0; i < 14; i++)
        {
            int n = rand.nextInt(10) + 0;
            card += Integer.toString(n);
        }
        /* for (int i = 0; i < 16; i++)
        {
            if(i % 4 == 0) {
            	System.out.print(" ");
            }
            System.out.print(card.charAt(i));
        } */
        return card;
    }
	
	public static boolean patternMatches(String str, String regex) {
	    return Pattern.compile(regex)
	      .matcher(str)
	      .matches();
	}
	
}
