import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Urls_to_JSON {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		
		JSONParser parser = new JSONParser();
		
		Object obj_parser2 = parser.parse(new FileReader("corpusData/Lewiston.json"));
		
		JSONObject ob_location = (JSONObject)obj_parser2;
		JSONArray av_location = (JSONArray) ob_location.get("Venues");
		
		ArrayList<Url_Words> al_url = new ArrayList<Url_Words>();
		
		
		JSONObject main_obj = new JSONObject();
		
		
		
		// -----Retrieving all the URL's for a city and storing that into an array list for further calculations -----//	
		int ctr=0;		
		for(int i=0;i<av_location.size();i++){
					
					JSONObject ob_semi = new JSONObject();
					JSONArray ob_semi_ar = new JSONArray();
					
					String temp_url =null;
					JSONObject ob1 = (JSONObject)av_location.get(i);
					System.out.println("Name:"+ob1.get("name"));
					System.out.println("url:"+ob1.get("url"));
					
					temp_url = (String)ob1.get("url");
				
					if(temp_url!=null){
						if(temp_url.contains("%2F")){
							
							int index = temp_url.indexOf("%2F");
							temp_url=temp_url.substring(0, index);
							System.out.println("url:- "+temp_url);
						}
						else{
						System.out.println("url:- "+temp_url);
						}
					}
					else
						System.out.println("url:- "+temp_url);
					
				// --- Retrieve the content of the url and store it into an array list --//
					// If the url is null, just skip all of this and never bother about the url at all. 
					
					Url_Content url_content = new Url_Content();
					
					String []url_words;
					String temp_url_words="";
					
					try{
					if(temp_url==null)
						continue;
					
					url_words = url_content.ret_urlcontent(temp_url);
					
					if(url_words[3].equals("ParserException")){
						continue;			
					}
					
					
					for(String w : url_words){
						temp_url_words = temp_url_words +" "+w;
					}
					} catch(Exception e){
						
						continue;
					}
					
					
				// --- End of retrival of url data --- //	
					
					ArrayList<Word_tf> tf1 = new ArrayList<Word_tf>();
		//------------------------------- using Word_tf -----------------------------//
					
					String [] url_wordsplit = temp_url_words.split("[\\W]");
					
					for(String w : url_wordsplit){
						int temp_count=0;
						if(!w.equals("")){
					//	System.out.println("-"+w+"-");
						
						
							if(tf1.size()==0){
								
								if(w.matches("[+-]?\\d*(\\.\\d+)?"))
									continue;
								
								for(String w2 : url_wordsplit){
									
									if((w2.toUpperCase()).equals(w.toUpperCase())){
										temp_count++;
									}
									
								}
								
								tf1.add(new Word_tf(w,temp_count));
								temp_count=0;
							}
							
							
							else{
								if(w.matches("[+-]?\\d*(\\.\\d+)?"))
									continue;
								
								ArrayList<Word_tf> tf2 = tf1;
								
								int leave_ctr=0;
								
								for(int k=0; k<tf2.size();k++){
									
									String temp_w = tf2.get(k).word;
									if((w.toUpperCase()).equals(temp_w.toUpperCase()))
										leave_ctr=1;
								}
								
								if(leave_ctr ==1)
									continue;
								else{
									
									for(String w2 : url_wordsplit){
										
										if((w2.toUpperCase()).equals(w.toUpperCase())){
											temp_count++;
										}
									}
									
									tf1.add(new Word_tf(w,temp_count));
									temp_count=0;
									
								}
								
							}
						
						
						
						
						
						
						
						
						
						
						
						}
					}
					
				//	double den1=0;
					
					for(int k=0;k<tf1.size();k++){
						
						JSONObject obj_temp1 = new JSONObject();
						
						Word_tf wd = tf1.get(k);
						
						System.out.println("Word:"+wd.word);
						System.out.println("tf:"+wd.tf);
						
						obj_temp1.put("word", wd.word);
						obj_temp1.put("tf", wd.tf);
						
					//	den1=den1+ ((int)wd.tf)*((int)wd.tf);
						
						ob_semi_ar.add(obj_temp1);
						
					}
					
					al_url.add(new Url_Words((String)ob1.get("name"),temp_url,tf1));
					
					ob_semi.put("name", (String)ob1.get("name"));
					ob_semi.put("url", temp_url);
					ob_semi.put("word_f", ob_semi_ar);
					ctr++;
					main_obj.put(Integer.toString(ctr), ob_semi);
					
					
					
		// ------------------------ End of using Word_tf --------------------- //
					
					
				}
				
				FileWriter file = new FileWriter("corpusTF/Lewiston_wordf.json");
				
				file.write(main_obj.toJSONString());
				
				file.flush();
				file.close();
		
		
		
		
		
		System.out.println("Hello World !");
		
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
