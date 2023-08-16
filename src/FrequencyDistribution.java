
public class FrequencyDistribution {
	 
	     public static void main(String[] args) {  
	    	 
	    	int m,n; 
	    	 
	    	double[][] lang = new double[10][10]; 
	        String str = "Picture perfect";  
	        int[] freq = new int[str.length()];  
	        int i, j;  
	          
	        //Converts given string into character array  
	        char string[] = str.toCharArray();  
	          
	        for(i = 0; i <str.length(); i++) {  
	            freq[i] = 1;  
	            for(j = i+1; j <str.length(); j++) {  
	                if(string[i] == string[j]) {  
	                    freq[i]++;  
	                      
	                    //Set string[j] to 0 to avoid printing visited character  
	                    string[j] = '0';  
	                }  
	            }  
	        }
	        
	        for(i = 0; i <freq.length; i++) {
	        	for(m=0; m < freq.length; m++) {
	        		for(n=0; m <freq.length; n++) {
	        			lang[m][n] = string[i];
	        			n++;
	        			lang[m][n] = freq[i];
	        		}
	        	}	
    		}
	        
	        for(m=0; m < lang.length; m++) {
        		for(n=0; m < lang.length; n++) {
        			System.out.println(lang[m][n] + " ");
        		}
        	}
	        
	          
	        //Displays the each character and their corresponding frequency  
	        System.out.println("Characters and their corresponding frequencies");  
	        for(i = 0; i <freq.length; i++) {  
	            if(string[i] != ' ' && string[i] != '0')  
	                System.out.println(string[i] + "-" + freq[i]);  
	        }  
	    }  
	}  
