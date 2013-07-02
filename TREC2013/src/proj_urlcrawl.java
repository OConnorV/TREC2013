import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.tags.MetaTag;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.htmlparser.*;


public class proj_urlcrawl {

	/**
	 * @param args
	 */
	public String[] get_urldata(String url2) throws Exception {
		// TODO Auto-generated method stub

	// First spit out all the meta tags. 
		
	// Proceed with crawl_url3 until you spot the <script , when ever you spot that, delete the part from
	//	there till the end and then continue with the rest of the stuff.
	
// This part is purely for the meta tags	
		
		System.out.println("I say Start !!!!");
		String finalTemp="";
		String k1= "name";
    	String kk1= "keywords";
    	String kkk1="description";
    	String url = url2;
    	String ret1 = print_meta(k1,kk1,url);
    	String ret2 = print_meta(k1,kkk1,url);
    	String words[];
    	
    	if(ret1 != null){
    //	System.out.println(ret1);
    	// Printing each word separately
    	words	= ret1.split(" ");
		
		for (String word : words)  
		{  
			if(word.length()==0){
				continue;
			}
				
		//   System.out.println(word);
		   finalTemp = finalTemp+" "+word;
		//   System.out.println("lenght is:"+word.length());
		}
    	// End of Printing words separately
    	}
    	if(ret2 != null){
    //	System.out.println(ret2);
    	words	= ret2.split(" ");
		
		for (String word : words)  
		{  
			if(word.length()==0){
				continue;
			}
				
		  // System.out.println(word);
		   finalTemp = finalTemp+" "+word;
		//   System.out.println("lenght is:"+word.length());
		}
    	// End of Printing words separately
    	}
// End of the meta tags part
    	
    	// Start of the part that extracts between the tags
    	
    	

		 int k=0;
		 int x=0;
		 String s;
		 BufferedReader r = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		// s = "<head>Hello WOrld yo all !</head>";
	//	 System.out.println("Started");
		int l=0;
		int counter =1;
		String text1;
		String subStr; 
		int c=0;
		int a=0;
		String temp="";
		 // Begining of while loop, that read through the entire web page//
		 
		// while ((s = r.readLine()) != null) {
		while ((s = r.readLine()) != null){
		//	System.out.println("Inside WHile");
			l= s.length();
	//	System.out.println(l);
	
		
		l = s.length();
		
		
		// Deal with the damn <script> part
		
					if(s.contains("<script"))
				{
				//		System.out.println("I see a script");
					c=1;
					a= s.indexOf("<script");
					    
					//    System.out.println("the substring is:"+s.substring(0,a));
						temp=temp+s.substring(0,a);
					//	System.out.println(temp);
					
				
						while(c==1){
										if(s.contains("</script>"))
										{
											
											c=0;
											a=s.indexOf("</script>");
											temp=temp+s.substring(a+9,l);
											s=temp;
											break;
										}
										s=r.readLine();
										l=s.length();
							
						}
						
						
						
				}
				
			
				
				
				//End of dealing with the script part.
					
					// Deal with the damn <style> part
					
					if(s.contains("<style"))
				{
			//			System.out.println("I see a style");
					c=1;
					a= s.indexOf("<style");
				//	System.out.println("a is:"+a);
				//	System.out.println("temp value is:"+temp);
				//	System.out.println("length of the string is:"+s.length());
						temp=temp+s.substring(0,a);
					//	System.out.println(temp);
					
				
						while(c==1){
							
							if(s==null){
								break;
							}
							if(s.contains("</style>"))
										{
											
											c=0;
											a=s.indexOf("</style>");
											l=s.length();
											temp=temp+s.substring(a+8,l);
											s=temp;
											break;
										}
										s=r.readLine();
										if(s==null){
											l=0;
											continue;
										}
										l=s.length();
							
						}
						
						
						
				}
				
			
				
				
				//End of dealing with the style part.
			
		if(s==null)
			break;
					
		if(!s.contains("<")){
			
		//	System.out.println(s);
			
			words	= s.split(" ");
			
			for (String word : words)  
			{  
				if(word.length()==0){
					continue;
				}
					
	//		   System.out.println(word);
			   finalTemp = finalTemp+" "+word;
			//   System.out.println("lenght is:"+word.length());
			}
	    	// End of Printing words separately
			
		}
		else {
			
		//	System.out.println("Inside Else");
		//	System.out.println(s);
			
			counter =0;
			
				do{
					if(s.contains(">")){
		//			System.out.println("Inside Do WHile !");
					
					x=s.indexOf("<");
					if(x>0){
						//System.out.println("In here !");
						subStr=s.substring(0, x);
					//	System.out.println(subStr);
						words	= subStr.split(" ");
						
						for (String word : words)  
						{  
							if(word.length()==0){
								continue;
							}
								
			//			   System.out.println(word);
						   finalTemp = finalTemp+" "+word;
						//   System.out.println("lenght is:"+word.length());
						}
				    	// End of Printing words separately
						l=s.length();
						s=s.substring(x, l);
						l=s.length();
						if(l==0)
							counter=1;
					}
					
					x=s.indexOf(">");
					l=s.length();
					subStr= s.substring(x+1,l);
					s=subStr;
					l=s.length();
		//			System.out.println("String is "+s);
		//			System.out.println("Length is: "+l);
					x=0;
					if(l==0)
						counter=1;
					
					if(s.contains("<")){
			//			System.out.println("This if Looks Ok!");
						
						k=s.indexOf("<");
						text1 = s.substring(x,k);
				//		System.out.println(text1);
						words	= text1.split(" ");
						
						for (String word : words)  
						{  
							if(word.length()==0){
								continue;
							}
								
		//				   System.out.println(word);
						   finalTemp = finalTemp+" "+word;
						//   System.out.println("lenght is:"+word.length());
						}
				    	// End of Printing words separately
						k=s.indexOf(">");
						l=s.length();
						subStr=s.substring(k+1,l);
						s=subStr;
						l=s.length();
		//				System.out.println(l);
						if(l==0)
							counter=1;
					
					}
					else{
						
								if(l==0){
									
								}	
								else{
										l=s.length();
									    text1 = s.substring(x+1, l);
						//				System.out.println(text1);
										words	= text1.split(" ");
										
										for (String word : words)  
										{  
											if(word.length()==0){
												continue;
											}
												
		//								   System.out.println(word);
										   finalTemp = finalTemp+" "+word;
										//   System.out.println("lenght is:"+word.length());
										}
								    	// End of Printing words separately
										s=s.substring(l,l);
										l=s.length();
										counter=1;
									
								}
						
					}
					
					}
					
					else{
						
						counter =1;
					}
				}while(counter ==0);
		}
		
		
		}
		
		
		
	// End of the part that extracts between tags
    	
    	
		
		
		
		
		
		
		
String final_words[];
		
		final_words	= finalTemp.split("[\\W_]");
			
			
//			System.out.println("-----------------------------------------------------");
			
/*		
		for (String final_word : final_words)  
		{  
			if(final_word.length()==0){
				continue;
			}
			
	//		word=word.replace(".", "");
	//		word=word.replace(",","");
			
		   System.out.println(final_word);
		//   System.out.println("lenght is:"+word.length());
		}
		
*/		
		
	
	
	
	
	
	return final_words;
	
	}
	
	
    public String print_meta( String k, String kk,String url){
    	String rt =null;
    	
    	Parser parser = new Parser();

        HasAttributeFilter filter = new HasAttributeFilter(k, kk);

        try {
            parser.setResource(url);
            NodeList list = parser.parse(filter);

            Node node = list.elementAt(0);
    //        Node node2 = list2.elementAt(0);

            if (node instanceof MetaTag) {
                MetaTag meta = (MetaTag) node;
                String description = meta.getAttribute("content");

                rt = description;

            }
            if(node ==null){}
            else{
            String xyz= node.getText();
       //     System.out.println("Starting here yo !");
       // 	System.out.println(xyz);
            }
            
        } catch (ParserException e) {
            e.printStackTrace();
        }

        
    	
    	
    	return rt;
    	
    }
	
	

}

