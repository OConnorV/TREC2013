import java.io.FileReader;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Url_Stream1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Hello World !");
		
		String url = "http://thetopofthehill.com/";
		
		proj_urlcrawl p1 = new proj_urlcrawl();
		try{
		String words1[] = p1.get_urldata(url);
		
		int i=0;
		
		System.out.println("Let the bodies hit the floor !");
		
		String base_url=null;
		
		for(String w : words1){
			
		base_url=base_url+" "+w;	
		System.out.println(w);	
		i++;	
		}
		
		System.out.println("Content of base URL:"+base_url);
		
		
// --------------------- Parsing the profile2013.json ----------------------------- //		
		JSONParser parser = new JSONParser();
		
		Object obj_parser = parser.parse(new FileReader("profiles2013.json"));
		
		JSONObject obj = (JSONObject)obj_parser;
		
		JSONArray a1 = (JSONArray)obj.get("35");
		
		for(int k=0; k<a1.size();k++){
			
			JSONObject ob1 = new JSONObject();
			
			ob1 = (JSONObject)a1.get(k);
			
			System.out.println("Attraction id is:"+ob1.get("attraction_id"));
			System.out.println("Website :"+ob1.get("website"));
			System.out.println("Description :"+ob1.get("description"));
			
		}
		
// ------------------------------ End of parsing ---------------------------------//
		
// -----------------------Parsing of examples2013.json-----------------------------//
		
		
		
		JSONParser parser2 = new JSONParser();
		
		Object obj_parser2 = parser2.parse(new FileReader("examples2013.json"));
		
		JSONObject obj2 = (JSONObject)obj_parser2;
		
		//JSONObject a2 = (JSONObject)obj2.get("51");
		System.out.println("------ Start ------");
		
		double []edge = new double[50];
		
		String base_url2=null;
		
		proj_cossim cos_sim = new proj_cossim();
		
		String words2[];
		
		for(int k=51; k<=100;k++){
		
			JSONObject a2 = (JSONObject)obj2.get(Integer.toString(k));
	//	for(int k=0; k<a2.size();k++){
			
	//		JSONObject ob2 = new JSONObject();
			
	//		ob2 = obj2;
			System.out.println(k);
			System.out.println("url is:"+a2.get("url"));
			String temp_url_cont = (String)a2.get("url");
			try{
			words2 = p1.get_urldata(temp_url_cont);
			
			for(String w2 : words2){
				
				base_url2=base_url2+" "+w2;	
				System.out.println(w2);	
				i++;	
				}
			base_url2=base_url2+" "+ a2.get("description")+" "+a2.get("title");
			
			edge[k-51] = cos_sim.ret_cossim(base_url2, base_url);
			
			} catch(Exception e1){
				System.out.println("Inside exception");
				edge[k-51]=0;
				continue;
				
			}
			System.out.println("Description :"+a2.get("description"));
			System.out.println("Title :"+a2.get("title"));
			System.out.println("=====================================");
			
	//	}
			
		}
		
		System.out.println("-----------------------------Start of scoring----------------------------");
		
		Arrays.sort(edge);
		
		for(int k=0; k<50;k++){
			
			System.out.println("Score of "+(k+51)+" is:"+edge[k]);
			
		}
		
		
		
// --------------------------End of parsing----------------------------------------//		
		
		
		
		
		
		
		} catch(Exception e){
			
			System.out.println("Something is wrong!");
			e.printStackTrace();
		}
		

	}

}
