import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class A1 extends SubstitutionCipher{

	public static void main(String[] args) throws Exception {
		//Path of my cipher text file stored
		String cipher = "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\cipher";
		
		//Initializing an instance of object without any parameters
		SubstitutionCipher c1 = new SubstitutionCipher();
			
		//Storing contents of file cipher as ciphertext after removing the letter frequency distribution.  
		if(c1.ciphertext(cipher) == true) {
			System.out.println("Valid CipherText");
		}
		//Linking contents of file with the language and storing its letter frequency distribution.
		c1.originalLanguage("A", "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\english.txt");
		c1.originalLanguage("B", "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\french.txt");
		
		System.out.println("Language "+ c1.matchLanguage()+" is the best fit");
		String l1 = c1.matchLanguage();
		
		if(c1.guessKeyFromFrequencies(l1) == true) {
			System.out.println("Key is " + c1.getKey());
		}
		
		if(c1.keyIsValid() == true) {
			System.out.println("Key Valid");
		}
	
		System.out.println("Plaintext :\n"+c1.decodeText());
		
		//Creating a hashmap to store key provided by user.
		HashMap<Character,Character> key2 = new  HashMap<Character,Character>();
		SubstitutionCipher c2 = new SubstitutionCipher("english", key2);
		key2.put('a','d');
		key2.put('b', 'f');
		key2.put('c', 'w');
		key2.put('d', 'e');
		
		c2.ciphertext("C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\cipher");
		c2.originalLanguage("A", "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\eglish.txt");
		c2.originalLanguage("B", "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\french.txt");
		System.out.println(c2.keyIsValid());
		System.out.println(c2.getKey());
		System.out.println("\nPlaintext :\n"+c2.decodeText());
		

	}
}
