import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Calculate_score3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			File file = new File("testing_results.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
		
		JSONParser parser = new JSONParser();
		
		JSONObject obj_result = new JSONObject();
		
		Object obj_parser2 = parser.parse(new FileReader("Chapel_Hill_new_test.json"));
		
		JSONObject ob_location = (JSONObject)obj_parser2;
		JSONArray av_location = (JSONArray) ob_location.get("Venues");
		
		Object obj_parser = parser.parse(new FileReader("examples_tfidf.json"));
		
		JSONObject obj_ex = (JSONObject)obj_parser;
		
		ArrayList<Url_Words> al_url = new ArrayList<Url_Words>();
// -----Retrieving all the URL's for a city and storing that into an array list for further calculations -----//	
		for(int i=0;i<av_location.size();i++){
			
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
				
				Word_tf wd = tf1.get(k);
				
				System.out.println("Word:"+wd.word);
				System.out.println("tf:"+wd.tf);
				
			//	den1=den1+ ((int)wd.tf)*((int)wd.tf);
				
			}
			
			al_url.add(new Url_Words((String)ob1.get("name"),temp_url,tf1));
			
			
			
			
			
// ------------------------ End of using Word_tf --------------------- //
			
			
		}
		
// ----------- End of storage of url's into an array list ---------------- //		
		
	// --- Printing to see if things ar working ! -- //	
		for(int i2=0;i2<al_url.size();i2++){
			
			Url_Words uw1 = al_url.get(i2);
			System.out.println("Name:"+uw1.name);
			System.out.println("url:"+uw1.url);
			
				for(int j=0;j<uw1.words.size();j++){
					Word_tf wtf = uw1.words.get(j);
					System.out.println(wtf.word);
					System.out.println(wtf.tf);
					
				}
			
			
		}
		
	// -- End of test printing -- //
		
		
		// -------------------- Calculating Similarity Scores ---------------------//
		
		ArrayList<Profile_Data> profd = new ArrayList<Profile_Data>();
		
		Object obj_parser3 = parser.parse(new FileReader("profiles2013.json"));
		
		JSONObject obj_prof = (JSONObject)obj_parser3;
	
		// use the below line when calling in the whole loop. 
		
	for(int prof_ctr=35; prof_ctr<=37;prof_ctr++){	
		
		JSONArray prof_arr = (JSONArray)obj_prof.get(Integer.toString(prof_ctr));
		
		
					JSONObject obj_ex_temp = obj_ex;
					
		ArrayList<Venue_Scores> vs = new ArrayList<Venue_Scores>();	
		
		
					
	for(int i3=0;i3<al_url.size();i3++){		
		double fin_score=0;
		
		if(al_url.get(i3).words ==null)
			continue;
		ArrayList<Word_tf> tf1 = al_url.get(i3).words;
				
		
					for(int kk=51;kk<=100;kk++){
						try{		
						
						double num =0;
						double den1=0;
						double den2=0;
						JSONArray ex1 = (JSONArray)obj_ex_temp.get(Integer.toString(kk));
						
						
						JSONObject ob_score = (JSONObject)prof_arr.get(kk-51);
				// Error Here
						
						String temp_score_st = (String)ob_score.get("website");
						double temp_score = Double.parseDouble(temp_score_st);
						
							for(int j=0;j<ex1.size();j++){
								
								JSONObject ob_temp = (JSONObject)ex1.get(j);
								
								String temp_word = (String)ob_temp.get("word");
								
								double tf_temp =(Double) ob_temp.get("tf");
								
								for(int k=0;k<tf1.size();k++){
										
										Word_tf wd = tf1.get(k);
										
										if(wd.word.toUpperCase().equals(temp_word.toUpperCase())){
											num = num+(Double)ob_temp.get("tf")*wd.tf*(Double)ob_temp.get("idf")*(Double)ob_temp.get("idf");
											den1 = den1+(Double)ob_temp.get("tf")*(Double)ob_temp.get("tf")*(Double)ob_temp.get("idf")*(Double)ob_temp.get("idf");
											den2=den2+(Double)ob_temp.get("idf")*(Double)ob_temp.get("idf")*wd.tf*wd.tf;
										}
									
									}
								
								
							
								
							}
							
							System.out.println("Denominator 1:"+den1);
							System.out.println("Denominator 2:"+den2);
							System.out.println("Numerator:"+num);
							System.out.println("Temp Score is:"+temp_score);
							
							if(den1==0 || den2==0){
								fin_score= fin_score+0;
								continue;
							}
							else{
							double temp_fin_score = (double)(((double)num)/((Math.sqrt((double)den1))*(Math.sqrt((double)den2))))*temp_score;
						//	fin_score = fin_score + (double)(((double)num)/((Math.sqrt((double)den1))*(Math.sqrt((double)den2))))*temp_score;
							fin_score = fin_score + temp_fin_score;
							System.out.println("Temp Final Score:"+temp_fin_score);
							System.out.println("Final Score:"+fin_score);
							}
							
							
						
					}catch(Exception e){
						continue;
					}
					
					} 
					vs.add(new Venue_Scores(al_url.get(i3).name,al_url.get(i3).url,fin_score));
					System.out.println(al_url.get(i3).name);
					System.out.println(al_url.get(i3).url);
					System.out.println(fin_score);
					
	
					
					
					
	}
	
	profd.add(new Profile_Data(prof_ctr,vs));
	
	System.out.println("Profile is:"+prof_ctr);
	System.out.println(vs.size());
	
	}
	
	
	// --Testing-- //
	
	System.out.println("Start Testing");
	
	for(int i4=0;i4<profd.size();i4++){
		
		System.out.println("Profile ID:"+profd.get(i4).profile_ID);
		
		ArrayList<Venue_Scores> test_vs = profd.get(i4).vs;
		
		for(int j=0;j<test_vs.size();j++){
			
			System.out.println(""+test_vs.get(j).name);
			System.out.println(""+test_vs.get(j).url);
			System.out.println(""+test_vs.get(j).score);
			System.out.println("==========================");
		}
		
		
	}
	
	// --End of Testing-- //
	
		// --------------------------End of Similarity Scores-----------------//	
	
	
		bw.close();
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
