

public class proj_cossim {

	/**
	 * @param args
	 */
	public double ret_cossim(String s1, String s2) {
		// TODO Auto-generated method stub
		
	//	String s1 = "hello world is 25 25 25 25 25 25 25 all I will believe in";
		
	//	String s2 ="hello world is 25 25 25 25 25 25 25 all I will believe in";
		
	//	String s2 = "hello world is 25 25 25 25 25 25 25 all I will believe in";
		
	//	System.out.println(s1);
		
	//	System.out.println(s2);
		
		String words1[] = s1.split("[\\W_]");
		String words2[] = s2.split("[\\W_]");
		
		
		int k1= words1.length;
	//	System.out.println("------------------------------");
		
		int k2 = words2.length;
		int x=0;
		int j=0;
		
		int ctr1 = 0;
		int ctr2=0;
		
		String temp[] = new String[k1];
		
		String temp1[] = new String[k2];
		
	//	HashSet h = new HashSet(testS);
		
// Separate the text into a set of unique strings		
		for(x=0; x<k1; x++){
			
			temp[x]=words1[x];
			if(x>0){
				
				
				for(j=0; j<x;j++){
					
					
				//	System.out.println("temp j is:"+temp[j]);
				//	System.out.println("temp x is:"+temp[x]);
				//	System.out.println("x is:"+x);
					if(temp[j].equals(temp[x])){
						if(temp[j].equals(""))
							continue;
						temp[x] ="";
						ctr1++;
					//	System.out.println("In here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");	
					}
				}
				
			}
			
			
		}
		
/*		
for (String word23: temp){
			
			System.out.println(word23);
			
		}
*/
// End of the separation of the strings

// System.out.println("------------------------------------------------------------");
		
//Separate the text into a set of unique strings		
		for(x=0; x<k2; x++){
			
			temp1[x]=words2[x];
			if(x>0){
				
				
				for(j=0; j<x;j++){
					
					
				//	System.out.println("temp j is:"+temp1[j]);
				//	System.out.println("temp x is:"+temp1[x]);
				//	System.out.println("x is:"+x);
					if(temp1[j].equals(temp1[x])){
						if(temp1[j].equals(""))
							continue;
						temp1[x] ="";
						ctr2++;
					//	System.out.println("In here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");	
					}
				}
				
			}
			
			
		}
		
/*		
for (String word231: temp1){
			
			System.out.println(word231);
			
		}
*/
//End of the separation of the strings

// base for the cos similarity
double base1 = Math.sqrt(temp.length - ctr1);
//System.out.println("temp length is:"+temp.length);

//System.out.println("ctr1 is:"+ctr1);

//System.out.println("base1 is:"+base1);
double base2 = Math.sqrt(temp1.length - ctr2);
//System.out.println("temp length is:"+temp1.length);

//System.out.println("ctr2 is:"+ctr2);

//System.out.println("base2 is:"+base2);
// end of base


// numerator


int num =0;


	
	for (x=0; x<k1; x++ ){
		
		for(j=0; j<k2;j++){
			
			if(temp[x].equals("")){
				break;
			}
			if(temp1[j].equals("")){
				continue;
			}
			
			if(temp[x].equals(temp1[j])){
				
			//	System.out.println("x is:"+x+" and string is:"+temp[x]);
			//	System.out.println("j is:"+j+" and string is:"+temp1[j]);
				num++;
			}
			
		}
		
		
		
	}
	
//	System.out.println("num is:"+num);
	
	
	double cossim = num / (base1 * base2);
	
//	System.out.println("cos sim is:"+cossim);
	


return cossim;

// end of the numerator

		
		
		
		
		
		
		
		

	}

}
