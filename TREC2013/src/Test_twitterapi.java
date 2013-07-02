import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class Test_twitterapi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("hello world !");
		
		
		ConfigurationBuilder cb =new ConfigurationBuilder();
		
		System.out.println("hello world !");
		cb.setDebugEnabled(true).setOAuthConsumerKey("XD8tFq76ROU5LVpgzG9WQ").setOAuthConsumerSecret("36DNfIDpOTiUww5ePCkZxDIvKzX7PCttzGFDmvQm0").setOAuthAccessToken("14978945-MmIUk7vpBa0cvjR1zhjYPAPOL5NvsYm4ljMKY91TA").setOAuthAccessTokenSecret("YXOuI6jmdMxM7cQ2bVkQIzPdRDpFycIFnWGKPqmas");
		System.out.println("hello world !");
		TwitterFactory tf = new TwitterFactory(cb.build());
		System.out.println("hello world !");
		Twitter twitter = tf.getInstance();
		System.out.println("hello world !");
		
		System.out.println("Seems to be working till here !");
		
		try{
		
	//	System.out.println(twitter.getFollowersIDs(1));
		
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
