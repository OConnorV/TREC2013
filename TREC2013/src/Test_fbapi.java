import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.User;


public class Test_fbapi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Hello FB !");
		
		FacebookClient fbClient = new DefaultFacebookClient("CAACEdEose0cBADPo4sEmNSmr0Y2BIpWnYuxf0uJldglP2mtRgIrffO6Vr7tbyuGB4rSFT8S1E218ZBDBaUewETCh3nPZBWIPnPxTLj2HZBZC7mViuglQrzjD9hKKT21L3dm4ZBzKr5uvn2wu8bbtAbBERQryDF1QAU3cpyhxvtgZDZD");
		User me = fbClient.fetchObject("asandeepc",com.restfb.types.User.class);
		
		Page pg = fbClient.fetchObject("LindasBarandGrill",Page.class);
		
	//	StatusMessage st = new 
	//	System.out.println(me.getReligion());
		
		JsonObject t1 = fbClient.fetchObject("LindasBarandGrill/statuses",JsonObject.class);
		
	//	System.out.println(t1);
		
		
		System.out.println(pg.getLikes());
		
		JsonArray t2 = (JsonArray)t1.get("data");
		
		for(int i=0; i<t2.length(); i++){
			
			JsonObject t3 = (JsonObject)t2.get(i);
			
			System.out.println(t3.get("message"));
			
			
		}
		
		
	}

}
