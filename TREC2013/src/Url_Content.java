import java.net.URL;

import org.htmlparser.parserapplications.StringExtractor;
import org.htmlparser.util.ParserException;
import org.omg.CORBA.portable.InputStream;


public class Url_Content {

	public String[] ret_urlcontent(String url){
		
		
			
			try{
				
		//		URL url2 = new URL(url);
		//		InputStream inp_st = (InputStream) url2.openStream();
		//		System.out.println("Inside the String Extractor");
			 
			
			StringExtractor se = new StringExtractor(url);
			
			String content = se.extractStrings(false);
		//	System.out.println("Content is:"+content);
			String []w = content.split("[\\W]");
			return w;
			}
			catch(Exception e){
				System.out.println("See this ain't working !");
				return null;
			}
			
		
		
		
	}
	
}
