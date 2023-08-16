import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class other {
	//Method reads file and stores it in a string
	public static String reader(File f2) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(f2));
		String st = null;
		String m1 = null;
		try {
			while((st =br.readLine())!=null) {
				if(m1 == null) {
					m1 = st;
				}
				else {
					m1 = String.join(" ",m1, st);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m1;
	}
	
	//Method to compare character cases in strings and maintaining case consistency between 
	//cipher text string and plain text string
	public String casePreserve(String cipher, String plain) throws FileNotFoundException {

		char [] c3 = cipher.toCharArray();
		char [] c4 = plain.toCharArray();
		for(int i = 0; i < c3.length; i++) {
			if (Character.isUpperCase(c3[i]) == true )
			{
				c4[i] = Character.toUpperCase(c4[i]);
			}	
		}
		
		StringBuilder s1 =new StringBuilder();
		s1 = s1.append(c4);
		String pa = s1.toString();
		return pa;
	}
	
	//Method to calculate percentage between two values
	public double percentC(int freq, int total) {
		//int total = 26;
		//double percentage = (float)((freq / total) * 100);
		double f= Double.valueOf(freq);
		double t= Double.valueOf(total);
		double k = f/t;
		double percentage = k * 100;
		//System.out.println(percentage);
		return percentage;
	}
}




