//change state on line 46

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Test_csv {

	/**
	 * @param args
	 */
	public ArrayList Ret_VenueInfo(String city_name) {
		// TODO Auto-generated method stub

//		System.out.println("Hello World !");
		
		String fileName = "csv1.txt";
		
		File file = new File(fileName);
		ArrayList<Csv_Values> a1 = new ArrayList<Csv_Values>();
		try{
			
			Scanner inputStream = new Scanner(file);
		//	inputStream.next();
			
	//		String dummy = "hello,world,why,you,know";
			
	//		String[] test = dummy.split(",");
			
	//		System.out.println("Dummy here:"+test[4]);
			String last =null;
			String first =null;
			first = inputStream.next();
			
		//	System.out.println("=============First=================");
		//	System.out.println(first);
		//	System.out.println("===================================");
		
			int i=0;
			while(inputStream.hasNext()){
				String data = inputStream.next();
			//	String [] values = data.split(",");
			//	System.out.println(values[0]);
				
				if(data.contains(city_name) && data.contains("IL")){
			//	System.out.println(data);
			
				String [] values = data.split(",");
				int j=0;
				
				String temp_city =null;
				double temp_latitude =0;
				double temp_longitude =0;
				
				for(String k : values){
					
		//			System.out.println(k);
					if(j==3){
				//		System.out.println("Inside the j loop:"+k);
						temp_city = k;
					}
					if(j==5){
				//		System.out.println("Inside the j loop:"+k);
						temp_latitude= Double.parseDouble(k);
					}
					if(j==6){
				//		System.out.println("Inside the j loop:"+k);
						temp_longitude=Double.parseDouble(k);
					}
					j++;
				}
				
				a1.add(new Csv_Values(temp_city,temp_latitude,temp_longitude));
				
				last=data;
				}
				i++;
				
			}
//			System.out.println("==================================================");
			
		//	System.out.println(last);
			
		//	System.out.println("i is: "+i);
			
			
		//	ArrayList<Csv_Values> a1 = new ArrayList<Csv_Values>();
			
		//	a1.add(new Csv_Values("Hello",2,3));
			
		//	Csv_Values a = a1.get(0);
			
			Csv_Values a;
/*			
			for(int j=0; j<a1.size();j++){
			a=a1.get(j);
			System.out.println("City:"+a.city+" Latitude: "+a.latitude+" Longitude: "+a.longitude);
		//	System.out.println(j);
			}
*/			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return a1;
		
	}

}
