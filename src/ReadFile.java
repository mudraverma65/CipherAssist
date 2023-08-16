import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ReadFile {
	
	public static void main(String args[]) throws IOException {
		
		File f1 = new File("C:\\Users\\dell\\eclipse-workspace\\Assign1\\src\\english.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(f1));
		
		String st;
		
		HashMap<Character,Integer> frequencyF = new HashMap<Character,Integer>();
		  
		
		try {
			while((st =br.readLine())!=null) {
				st = st.replaceAll("[^a-zA-Z0-9]", " ");
				char [] c = st.toCharArray();
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
						if(c[i] != ' ' && j==len-1) {
							
							frequencyF.put(c[i], counter);
							
							
							//if(j==len-1) {	
							//System.out.println(c[i] +"\t" + counter );
						}
						
					}
				}
			}
		}
		catch(IOException e) {
			//e.printStactTrace();
			System.out.println("Error Found");
		}
		
		System.out.println(frequencyF);
		}
	}

			
			
		
		//while((st =br.readLine())!=null) {
			//char [] c = st.toCharArray();
			//for (char ch ='a'; ch<='z'; ch++) {
				//int counter1 = 0;
				//for(int i=0; i<c.length;i++){
					//if(ch == st.charAt(i)) {
						//counter1++;
					//}
				//}
				//if(ch!=' ' ) {
					//System.out.println(ch + "\t" +counter1);
				//}
				
				
			//}
		//}
		
		
		//String s = "mudraverma";
		//char [] c = s.toCharArray();
		//int len = c.length;
		//int i = 0, j = 0, counter = 0;
		
		//for(i=0;i<len;i++) {
			//counter =0;
			//for(j=0;j<len;j++) {
				//if (j<i && c[i] == c[j]) {
					//break;
				//}
				//if(c[j] == c[i]) {
					//counter++;
				//}
				//if(j==len-1) {
					//System.out.println(c[i] +"\t" + counter );
				//}
			//}
		//}
		



