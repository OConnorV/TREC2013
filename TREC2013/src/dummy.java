import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class dummy{
	
	
	public static void main(String args[]){
		
		System.out.println("Hello WOOrld !");
		
		JSONObject obj23 = new JSONObject();
	    
	    obj23.put("City","Chapel Hill");
	    
	    
	    
	    JSONArray venues = new JSONArray();
	    
	    for(int i=0;i<20;i++){
	    	
	    	System.out.println(i);
	    	JSONObject obj_name = new JSONObject();
	    	
	    	obj_name.put("Number", i);
	    	
	    	
	    	venues.add(obj_name);
	    	
	    //	venues.add(obj_name);
	    	
	    //	obj23.put("Final Number array", venues);	
	    //	System.out.println("Temp Obj"+obj23);
	    	
	    }
	    
	    
	    
	    obj23.put("Numbers", venues);
		
	    System.out.println("Output:"+obj23);
		
		
		
		
		
	}
	
	
	
	
	
	
}