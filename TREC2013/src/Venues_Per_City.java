// change city on line 21
// change file name on line 93
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Venues_Per_City {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Hello World !");
		
		String city_name = "Orlando";
		
		Test_csv venue_latlong = new Test_csv();
		
		ArrayList<Csv_Values> a1 = venue_latlong.Ret_VenueInfo(city_name);
		
		ArrayList<Venue_Info2> av1 = new ArrayList<Venue_Info2>();
		
		Yelp_Getinfo venue_finalinfo = new Yelp_Getinfo();
		
		Csv_Values a;
		
		for(int i=0;i<a1.size();i++){
			
			a=a1.get(i);
		//	ArrayList<Venue_Info2> av2 = venue_finalinfo.venue_information(city_name, a.latitude, a.longitude);
			ArrayList<Venue_Info2> av2 = venue_finalinfo.ret_info(a.latitude, a.longitude,city_name);
			
			av1.addAll(av2);
			
			System.out.println(av2.size());
			
		}
		
		Venue_Info2 av;
		
	//	Venue_Info av2;
		
		int sum=0;
		

//		ArrayList<Venue_Info> av_final = new ArrayList<Venue_Info>();
		
//		ArrayList<Venue_Info> av_temp= new ArrayList<Venue_Info>();
		
		
		JSONObject obj23 = new JSONObject();
	    
	    obj23.put("City",city_name);
	    
	  
	    
	    JSONArray venues = new JSONArray();
		
	for(int i=0;i<av1.size();i++){
			
			av= av1.get(i);
		
			  JSONObject obj_name = new JSONObject();
			System.out.println("Name: "+av.name);
			System.out.println("URL: "+av.url);
			System.out.println("Yelp URL: "+av.url_yelp);
			System.out.println("City: "+av.city);
			System.out.println("Lat: "+av.latitude);
			System.out.println("Long: "+av.longitude);
			obj_name.put("name",av.name);
	     	 obj_name.put("url",av.url);
	     	obj_name.put("yelp url",av.url_yelp);
	     	   obj_name.put("Lat",av.latitude);
	     	   obj_name.put("long",av.longitude);     	   
	     	   venues.add(i, obj_name);
			System.out.println("=================================================================");
			sum=i;
			
		}
	
	obj23.put("Venues", venues);
	
	System.out.println("New Size of the Venues:"+av1.size());
    
    try{
	
    FileWriter file = new FileWriter("Orlando.json");
    file.write(obj23.toJSONString());
    System.out.println("I hope this works !");
    
    System.out.println("And the Object is: "+obj23);
    
    file.flush();
    file.close();
    } catch(Exception e){
    	System.out.println("File Writing turned out bad");
    	e.printStackTrace();
    }
		
		
		
		
		System.out.println("Here");
/*		
		for(int i=0; i<av1.size();i++){
			
			av=av1.get(0);
			
			if(av_final.size()==0){
				av_final.add(new Venue_Info(av.name,av.url,av.city,av.latitude,av.longitude));
				
			}
			
			else{
				
				int ss=0;
				
				for(int j=0; j<av_final.size();j++){
					
					av2 = av_final.get(0);
					
					if(av.name.equals(av2.name))
					{
						continue;
					}
					else{
						ss++;
					}
					
				}
				
				if(ss==av_final.size()){
					av_final.add(new Venue_Info(av.name,av.url,av.city,av.latitude,av.longitude));
				}
				
				
			}
			
		}

*/

/*		
		Venue_Info av11;	
     
		for(int i=0;i<av_final.size();i++){
			
			av11= av_final.get(i);
			
			System.out.println("Name: "+av11.name);
			System.out.println("URL: "+av11.url);
			System.out.println("City: "+av11.city);
			System.out.println("Lat: "+av11.latitude);
			System.out.println("Long: "+av11.longitude);
			System.out.println("=================================================================");
			sum=i;
			
		}
		
*/		
		
		System.out.println("Net total: "+sum);
	}

}
