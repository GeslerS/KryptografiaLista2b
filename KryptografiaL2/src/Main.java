import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;

public class Main {
	public static void main(String[] args) {
		/*
		 * Rozwiazanie: Klucz 35aa3cdf290b7b3d Tekst
		 * "Is it better or worse to listen to music while you work? What about white noise?"
		 */
		String doZdeszyfrowania = "11100101 11000000 11011010 11001001 00111101 10111011 00101000 11011000 01011111 11100010 00100100 10100011 00111000 01010100 11110000 11100100 10101001 10110011 11000100 11000100 11111111 01110110 00110111 11110010 10001011 00101111 11000010 10001010 10110101 01011001 11101110 01000101 11110000 10000110 11100000 10001011 00010010 11010001 11000010 11110000 10111100 10000000 00010101 10100111 00111011 00100010 11001100 00000000 00110000 00101011 01100100 00110110 10000111 01100010 01100111 10100000 01010101 10010000 00110011 10000100 01111010 00101011 10111110 10110011 11111010 10010010 10110010 11000100 00110010 10101010 00011101 00100110 10011000 00010110 00101000 11000000 01000100 11110100 10111110 00011101";
		String pierwszaCzescKlucza = "";
		String drugaCzescKlucza = "290b7b3d";
		boolean pasuje = false;
		RC4Engine rc4engine = new RC4Engine();

		// Zamiana na chary
		StringBuilder temp = new StringBuilder();
	
		doZdeszyfrowania = doZdeszyfrowania.replace(" ", "");
		 for(int i = 0; i < doZdeszyfrowania.length()/8; i++){
		 temp.append((char)
		 Integer.parseInt(doZdeszyfrowania.substring(i * 8, (i * 8) + 8),2));
		 }
		doZdeszyfrowania = temp.toString();

		for (int i = 0; i < (int) Math.pow(2, 32); i++) {
			pierwszaCzescKlucza = ("00000000" + Integer.toHexString(i));
			pierwszaCzescKlucza = pierwszaCzescKlucza.substring(pierwszaCzescKlucza.length() - 8);
			rc4engine.init(true, new KeyParameter((pierwszaCzescKlucza + drugaCzescKlucza).getBytes()));

			temp.setLength(0);
			pasuje = true;
			for (int j = 0; j < doZdeszyfrowania.length(); j++) {
				char znak = (char) rc4engine.returnByte((byte) (doZdeszyfrowania.charAt(j)));
				if (!nalezyDoTekstu(znak)) {
					pasuje = false;
					break;
				} else
					temp.append(znak);
			}
			if (pasuje) {
				System.out.println(pierwszaCzescKlucza + drugaCzescKlucza);
				System.out.println(temp.toString());
			}

		}

	}

	public static boolean nalezyDoTekstu(int znak) {

		if (44 <= znak && znak <= 59) {
			return true;
		} else if (63 <= znak && znak <= 90) {
			return true;
		} else if (97 <= znak && znak <= 122) {
			return true;
		} else if (32 <= znak && znak <= 34) {
			return true;
		} else {
			return false;
		}
	}
}