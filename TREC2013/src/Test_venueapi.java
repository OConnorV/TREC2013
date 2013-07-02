import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Test_venueapi {

	/**
	 * @param args
	 */
	public ArrayList venue_information(String city_name, double lat1, double long1) {
		// TODO Auto-generated method stub
		
		ArrayList<Venue_Info> av = new ArrayList<Venue_Info>();
		
		try{
			
		
			
			String city = city_name ;
			
			double l1=lat1;
			double l2=-long1;
			
			String k= Double.toString(l1)+","+Double.toString(l2);
			
			//String ll=URLEncoder.encode("40.7142,74.0064", "UTF-8");
			String ll=URLEncoder.encode(k, "UTF-8");
			String access_token = "ZK4YZ51GCAL5V1CDQWXVIKF2XB43QXOIVGYL4ENPQWDC0FQP&v=20130531";
			
			String url="https://api.foursquare.com/v2/venues/search?ll="+ll+"&oauth_token="+access_token;
			
			URL new_url = new URL(url);
			
			URLConnection httpc = new_url.openConnection();
			
			
			
			httpc.setDoInput(true);
            httpc.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpc.getInputStream()));
            String strLine = "";
            String content="";
     //       System.out.println("Connection made !");
            while ((strLine = in.readLine()) != null){
                content=content+strLine;
           }
           in.close();
           
     //      System.out.println("Ok, here it goes !");
           
   //        System.out.println(content);
           
           
           
		
           Object obj=JSONValue.parse(content);
           JSONObject main_obj=(JSONObject)obj;
           JSONObject response_obj = (JSONObject)main_obj.get("response");
           JSONArray groups_arr = (JSONArray)response_obj.get("groups");
          
           JSONObject test1 = (JSONObject)main_obj.get("meta");
//           System.out.println();
//           System.out.println(test1.get("code"));
//           System.out.println();
           JSONArray test2 = (JSONArray)response_obj.get("venues");
           
      //     System.out.println("The total array size is:"+test2.size());
           
           for(int i=0; i< test2.size(); i++){
        	   
        	   JSONObject item_obj = (JSONObject)test2.get(i);
        	   
        	   
        	   if(item_obj.get("url")==null){
        		   continue;
        	   }
        //	   System.out.println(item_obj.get("name"));
        //	   System.out.println(item_obj.get("url"));
        	   av.add(new Venue_Info((item_obj.get("name")).toString(),item_obj.get("url").toString(),city,l1,l2));
        	   
        	   
        	   
        	   
      //  	   System.out.println("=====================");
        	   
           }
       /*    
           for(int i=0; i<av.size();i++){
        	   
        	   Venue_Info temp_v = av.get(i);
        	   
        	   System.out.println("Name: "+temp_v.name);
        	   System.out.println("URL: "+temp_v.url);
        	   System.out.println("City: "+temp_v.city);
        	   System.out.println("Latitude: "+temp_v.latitude);
        	   System.out.println("Loongitude: "+temp_v.longitude);
        	   
        	   System.out.println("=====================");
        	   
           }
      */     
           
           
			
			
			
		} catch(Exception e){
			System.out.println("It didn't work, and the stack tree is: "+e.getStackTrace());
		}
		return av;
	}

}
