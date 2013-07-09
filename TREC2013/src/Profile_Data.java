import java.util.ArrayList;


public class Profile_Data {
	
	int profile_ID;
	
	ArrayList<Venue_Scores> vs;
	
	public Profile_Data(int temp_profile_ID, ArrayList<Venue_Scores> vs2){
		
		vs = vs2;
		
		profile_ID = temp_profile_ID;
		
	}

}
