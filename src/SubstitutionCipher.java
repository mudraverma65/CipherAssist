import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

public class SubstitutionCipher extends other {
	
	char k,v;
	String s2;
	double percentage;
	String st=null;
	public String ciphert1;
	public HashMap<Character, Character> key = new HashMap<Character, Character>();
	public String language;
	String lanFile = "C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\english.txt";  
	public HashMap<Character,Double> CipherF = new HashMap<Character,Double>();
	HashMap<String , HashMap<Character, Double>> KeyLang = new HashMap<>();
	
	//Default Constructor
	SubstitutionCipher(){
		
	}
	
	//Parameterized Constructor
	public SubstitutionCipher (String lang, HashMap<Character, Character> keyc){
		this.key = keyc;
		this.language = lang;
		
	}
	
	//Links the file content with the language name and removes the frequency distribution of the language.
	public boolean originalLanguage( String lang, String lanFile ) throws Exception{
		try {
			if(lang!=null && lanFile !=null) {
				File f1 = new File(lanFile);
				
				//HashMap to store character and its frequency.
				HashMap<Character,Double> frequencyF = new HashMap<Character,Double>();
				
				//Reads content of file and stores in string
				st = reader(f1);
				
				//Converts the content of string to lowercase so that our table
				//does not take repetitive characters
				st = st.toLowerCase();
				
				// Convert String to array
				char [] c = st.toCharArray();
				
				//get length of array
				int len = c.length;
				//looping through 2 for loops and getting the frequency of each value stored in a counter.
				int i = 0, j = 0, counter = 0;
				for(i=0;i<len;i++) {
					counter =0;
					for(j=0;j<len;j++) {
						if (j<i && c[i] == c[j]) {
						break;
						}
						if(c[j] == c[i]) {
							counter++;
						}
						//Ignoring index values such as space and making sure that only alphabets get stored in the map.
						if(c[i] != ' ' && j==len-1 && Character.isAlphabetic(c[i])) {
							//calling a percent method defined in other.java to remove percentage
							percentage = percentC(counter, len);
							
							//Storing frequency of each character in a map 
							frequencyF.put(c[i], percentage);
							//System.out.println(c[i] +"\t" + percentage );
						}
					}
				}
				
				//KeyLang is the map which contains character distribution per language.
				KeyLang.put(lang, frequencyF);
				return true;
			}
			else return false;
		}
		
		catch (Exception e) {
			return false;
		}
		
	}
	
	
	
	//calculate frequency of ciphertext here so that not needed at the time of guess key.
	
	Boolean ciphertext( String CipherFile ) throws Exception {
		try {
			if(CipherFile!=null) {
				File f2 = new File(CipherFile);
				ciphert1 = reader(f2);
				String c1 = ciphert1.toLowerCase();
				char [] c = c1.toCharArray();
				int len = c.length; 
				int i = 0, j = 0, counter = 0;
				for(i=0;i<len;i++) {
					counter =0;
					for(j=0;j<len;j++) {
						if (j<i && c[i] == c[j]) {
							break;
						}
						if(c[j] == c[i]) {
							counter++;
						}
						if(c[i] != ' ' && j==len-1 && Character.isAlphabetic(c[i])) {
								percentage = percentC(counter, len);
								CipherF.put(c[i], percentage);
								//System.out.println(c[i] +"\t" + percentage );
								}
							}
						}
					return true;
			}
			else return false;
		}
		catch(Exception e) {
			return false;
		}
		
		
	}
	
	//Method to convert ciphertext to plaintext based on key
	public String decodeText() throws Exception {
		
		//creating object of Stringbuilder class for plaintext
		StringBuilder plaintext = new StringBuilder();
		
		//converting characters to lowercase to maintain consistency
		String c1 = ciphert1.toLowerCase();
		
		//Converting to array
		char [] c = c1.toCharArray();
		int len = c.length;
		for(int i=0; i<len; i++) {
			
			//Checking to see whether our key contains mapping for 1st ciphertext character.
			if(key.containsKey(c[i])) {
				
				//Changing value of ciphertext to value associated in the key.
				c[i] = key.get(c[i]);
				
				//Appending characters in plaintext 
				//First Object
				if( plaintext == null){
					plaintext.insert(0, c[i]);
				}
				else {
					plaintext = plaintext.append(c[i]);

				}
			}
			else {
				plaintext.append(c[i]);
			}
		}
		
		//Converting Stringbuilder object to string
		String pt = plaintext.toString(); 
		
		//Passing the original cipher text and new plaintext through a method to switch 
		//cases of plaintext character to its original one before toLowerCase() was called. 
		pt = casePreserve(ciphert1, pt);
		return pt;
	}
	
	//Updating value of existing plaintext with new ciphertext value.
	
	Boolean setDecodeLetter( Character plaintextChar, Character ciphertextChar ) {
		
		//Checking whether key contains value or not 
		if(key.containsKey(plaintextChar)) {
			
			//Replacing value in key to the new one
			key.put(plaintextChar, ciphertextChar);
			return true;
		}
		return false;
	}
	
	
	// Returns key used by current object
	Map<Character, Character> getKey(){		
		return key;
	}
	
	//Checks whether the key is valid
	Boolean keyIsValid( ) {
		if(key!=null) {
			
			//Replacing all special characters in ciphertext string.
			ciphert1 = ciphert1.replaceAll("[^a-zA-Z0-9]", " ");
			int m =0;
			char [] c = ciphert1.toCharArray();
			int len = c.length;
			for(int i=0; i<len; i++){
				//Check that each character in the plaintext maps to a ciphertext.
				if(key.containsKey(c[i])) {
					m++;
				}
				else
				{
					break;
				}
			}
			return true;
			}
			else {
				return false;
			}
		
	}
	
	//Returns the key which is the best match 
	Boolean guessKeyFromFrequencies( String language ) {
		if(language!=null) {
			//Map to store cipher map values in descending order.
			HashMap<Character, Double> cipherMap = new HashMap<Character, Double> ();
			
			//Map to store language map values in descending order.
			HashMap<Character, Double> LanguageMap = new HashMap<Character, Double>();
			
			//Reference from https://www.geeksforgeeks.org/collections-reverseorder-java-examples/ to sort values in descending order.
			//Storing values in descending order in map cipherMap.
			cipherMap = CipherF
			        .entrySet()
			        .stream()
			        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			        .collect(
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
			                LinkedHashMap::new));
			
			//Storing hashmap stored for each language in KeyLang in a local map 
			HashMap<Character, Double> m1 = KeyLang.get(language); 
			//Storing values in descending order in map m1.
			LanguageMap = m1
			        .entrySet()
			        .stream()
			        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			        .collect(
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
			                LinkedHashMap::new));
			
			//Creating Iterator class objects to iterate through  
			Iterator<Map.Entry<Character, Double>> h1 = cipherMap.entrySet().iterator();
			Iterator<Map.Entry<Character, Double>> h2 = LanguageMap.entrySet().iterator();
			//Iterating till ciphermap has a next object 
			while(h1.hasNext()) {
					
					//Storing next values in entry objects
					Map.Entry<Character, Double> m3 = (Map.Entry<Character, Double>)h1.next();
					Map.Entry<Character, Double> m2 = (Map.Entry<Character, Double>)h2.next();
					
					//storing keys of both the maps in a new object
					//the values were already sorted in descending 
					//order so that the characters with highest frequency get stored first.
					k = (char) m3.getKey();
					v = (char) m2.getKey();
					//Storing the plaintext and ciphertext character.
					key.put(k, v);
				}
			return true;
		}
		else return false;
			
	}
	
	
	//Finds sum of difference 	
	String matchLanguage( ) {
		double temp1=0;
		double r,t;
		double SumofD = 0;
		HashMap<Character, Double> temp = new HashMap<>();
		HashMap<String, Double> absF = new HashMap<>();
		//Iterating for each language Map
		Iterator<Entry<String, HashMap<Character, Double>>> a1 = KeyLang.entrySet().iterator();
		while(a1.hasNext()) {
			//Referencing values in each entry of hashmap
			Map.Entry<String, HashMap<Character, Double>> b1 = (Map.Entry<String, HashMap<Character, Double>>)a1.next();
			temp = b1.getValue();
			
			//Removing absolute difference of highest frequencies based 
			for(char a='a';a<='z'; a++) {
				if(temp.containsKey(a) && CipherF.containsKey(a)) {
					r = temp.get(a);
					t = CipherF.get(a);
					temp1 = r - t;
					temp1 = Math.abs(temp1);
				}
				else if(temp.containsKey(a) == true && CipherF.containsKey(a) == false  ) {
					temp1 = temp.get(a);
				}
				else if(temp.containsKey(a) == false && CipherF.containsKey(a) == true  ) {
					temp1 = CipherF.get(a);
				}
				else {
					temp1 =0;
				}
				
				//Calculating sum and storing it in a local hashmap 
				SumofD = SumofD + temp1;
				absF.put(b1.getKey(), SumofD);
			}
			SumofD =0;	//Initializing value to 0
			}
			
			//Finding the language with smallest sum of difference and passing the language name.
			Entry<String, Double> min = Collections.min(absF.entrySet(), new Comparator<Entry<String, Double>>() {
				public int compare(Entry<String, Double> entry1, Entry<String, Double> entry2) {
					return entry1.getValue().compareTo(entry2.getValue());
				}
			});
			return min.getKey();
	}
	
}
