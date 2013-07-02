import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;


public class Fetch_Bizurl {

	/**
	 * @param args
	 */
	public String ret_url(String url1) {
		// TODO Auto-generated method stub

		
		//String url ="http://www.yelp.com/biz/lindas-bar-and-grill-chapel-hill#query:lindas";
		String url = url1;
		String final_url = null;
		try{

        Parser parse = new Parser(url);
     // if you have html you can alternativey use the parse.setInputHTML method
        
      //  System.out.println("Hello World !");
       
     NodeList lstNodes =(NodeList) parse.extractAllNodesThatMatch (new AndFilter (new NodeClassFilter (Div.class), new HasAttributeFilter ("id")));
     if (lstNodes != null && lstNodes.size() > 0)
     {
        Div tag = null;
        
        String temp_t = null;
        for (int itr=0; itr<lstNodes.size(); itr++)
        {
           tag = (Div)lstNodes.elementAt(itr);
           String idAttribute = tag.getAttribute("id");
           if (idAttribute != null && idAttribute.equals("bizUrl"))
           {
               // this will print the div html <div id='two'> some text two </div>

        	//   System.out.println("--------");
        	//   System.out.println(tag.getText());
        	   
       // 	   System.out.println(tag.getChildrenHTML());
        	   
        	   temp_t = tag.getChildrenHTML();
        	   
        	 //  System.out.println("--------");
        	   
        	   
             //  System.out.println(tag.toPlainTextString());
               // now I need to extract the text from this div tag
               Parser tagParser = new Parser();
               tagParser.setInputHTML(tag.toHtml());
               StringBean sb = new StringBean ();
               tagParser.visitAllNodesWith (sb);
       //        System.out.println(sb.getStrings ()); // this will print the content "some text two"
               
             //  System.out.println(sb.getURL());
               
             //  System.out.println("Just Checking");
               String temp = sb.getStrings();
               
             //  System.out.println(temp);
               
             //  System.out.println("-------------");
             //  System.out.println(temp_t);
               
               int k = temp_t.indexOf("url=http%3A%2F%2F");
               
            //   System.out.println("Index is: "+k);
               
               int kk = temp_t.indexOf("&amp");
            //   System.out.println("Index is: "+kk);
               
               final_url = "http://"+temp_t.substring(k+17, kk);
            //   System.out.println("Final URL:"+final_url);
           }
       }
     }
    // System.out.println("-------------");
    // System.out.println("Final URL:"+final_url);
    // System.out.println("End Yo !");
     
     

		} catch(Exception e){
			
			System.out.println("Ok, this didn't work !");
			e.printStackTrace();
		}
		return final_url;	
		
	}

}
