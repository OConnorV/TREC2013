import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;	
import org.scribe.oauth.OAuthService;

/**
 * Example for accessing the Yelp API.
 */
public class Yelp {

  OAuthService service;
  Token accessToken;

  /**
   * Setup the Yelp API OAuth credentials.
   *
   * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
   *
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
    this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
  }

  /**
   * Search with term and location.
   *
   * @param term Search term
   * @param latitude Latitude
   * @param longitude Longitude
   * @return JSON string response
   */
  public String search(double latitude, double longitude) {
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
  //  request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("ll", latitude + "," + longitude);
    this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    return response.getBody();
  }

  // CLI
  public static void main(String[] args) {
    // Update tokens here from Yelp developers site, Manage API access.
    String consumerKey = "Oby7sjcfEi75sW1FdWa96w";
    String consumerSecret = "QSyVBeeZUscAzcqZZM-9PwMEozw";
    String token = "_4SdpEN_ucBiqZevvd9D61zdM2baT3vb";
    String tokenSecret = "uZMS9-btIGutR-rVmrCfpf8Cdrg";

    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
    String response = yelp.search(35.9132, -79.0558);
    
    double lat = 35.9132;
    double longi = -79.0558;

    System.out.println(response);
    
    Object temp_obj = JSONValue.parse(response);
    
    JSONObject obj = (JSONObject)temp_obj;
    
    JSONArray resp_obj = (JSONArray)obj.get("businesses");
    
    ArrayList<Venue_Info2> av = new ArrayList<Venue_Info2>();
    
    for(int i=0;i<resp_obj.size();i++){
    	
    	
    	JSONObject item = (JSONObject)resp_obj.get(i);
    	System.out.println(item.get("name"));
    	System.out.println(item.get("url"));
    	System.out.println(item.get("snippet_text"));
    	
        Fetch_Bizurl b = new Fetch_Bizurl();
    
    	System.out.println("Original URL:"+b.ret_url((String)item.get("url")));
    	
    	av.add(new Venue_Info2((String)item.get("name"),(String)b.ret_url((String)item.get("url")),(String)item.get("url"),"City",lat,longi));
    	
    	System.out.println("=====================================");
    	
    }
    
    
    System.out.println(av.size());
    
    for(int i=0;i<av.size();i++){
    	
    	Venue_Info2 v = av.get(i);
    	System.out.println("-----------------------------------------------------------");
    	System.out.println(v.city);
    	System.out.println(v.name);
    	System.out.println(v.url);
    	System.out.println(v.url_yelp);
    	System.out.println(v.latitude+","+v.longitude);
    	System.out.println("-----------------------------------------------------------");
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
  }
}