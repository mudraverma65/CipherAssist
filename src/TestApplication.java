import java.io.FileNotFoundException;
import java.io.IOException;

public class TestApplication {
	public static void main() throws Exception {
		// TODO Auto-generated method stub
		SubstitutionCipher c1 = new SubstitutionCipher();
		c1.ciphertext("C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\cipher");
		c1.originalLanguage("A", "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\french.txt");
		c1.originalLanguage("B", "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\english.txt");
		String s = c1.matchLanguage();
		System.out.println(s);
		c1.guessKeyFromFrequencies(s);
		String s2 = c1.decodeText();
		System.out.println(s2);


	}

}
