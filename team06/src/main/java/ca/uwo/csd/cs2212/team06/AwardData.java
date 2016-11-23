package ca.uwo.csd.cs2212.team06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * This class is used to check the squirrel badge(called Nutty), the time specific badges(called Early Bird and Night Owl), the triathalon 
 * badge(called Triple Threat), and the heartrate badge (called Healthy Heart) badge.  These badges require this class because of how the user 
 * fulfills their requirments.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class AwardData implements Serializable{
	//request a pull for recent activities and then check against badge requirements
	//as fast as a squirrel attributes
	final private int squirrelActId = 90001;	//biking
	final private int maxSpeed = 20;            //mph
	private boolean achievedSquirrel;
	
	//night owl attributes
	private int nightSteps;
	
	//early bird attributes
	private int earlySteps;
	
	//triathalon attributes
	private int runMinutes;
	private int swimMinutes;
	private int bikeMinutes;
	
	//high heartrate x days in a row
	private int daysAboveInARow;
	
	/**
	 * Constructor for the AwardData.  Using data from the awardData.dat file, it determines whether any badges have been earned.
	 */
	public AwardData(){
		int steps[] = this.timeAwards();
		nightSteps = steps[0];
		earlySteps = steps[1];
		daysAboveInARow = this.heartrateDays();
		
		RequestData req = new RequestData("activities/recent");
		req.sendRequest();
		achievedSquirrel = this.squirrelAward(req);
		int minutes[] = this.triathalete(req);
		runMinutes = minutes[0];
		swimMinutes = minutes[1];
		bikeMinutes = minutes[2];
	}
	
	/**
	 * calculates steps takes between midnight and 2am and 4am and 6am
	 * for use with night owl and early bird badges
	 * @param timeAwards    int[]
	 */
	private int[] timeAwards(){
		int steps[] = {0,0};
		String end = "02:00:00";
		int night = 0;
		int morning = 0;
		//get data
		RequestData req = new RequestData("activities/steps/date/today/1d");
		req.sendRequest();
		JSONObject obj = new JSONObject(req.getBody());
		JSONArray array = obj.getJSONObject("activities-steps-intraday").getJSONArray("dataset");
		
		//calculate night owl data
		int i = 1;
		JSONObject loopObj = array.getJSONObject(0);
		while(!loopObj.get("time").equals(end)){
			night += Integer.parseInt(loopObj.get("value").toString());
			loopObj = array.getJSONObject(i);
			i++;
		}
		
		//calculate early bird data
		String start = "04:00:00";
		String endMorn = "06:00:00";
		while(!loopObj.get("time").equals(start)){
			loopObj = array.getJSONObject(i);
			i++;
		}
		while(!loopObj.get("time").equals(endMorn)){
			morning += Integer.parseInt(loopObj.get("value").toString());
			loopObj = array.getJSONObject(i);
			i++;
		}
		steps[0] = night;
		steps[1] = morning;
		return steps;
	}
	
	/**
	 * Calculates the Night Owl and Early Bird badges.  Does so by pulling the past days data and then 
	 * checking whether the user has exercised during the night or during the morning.
	 * @param date     String of the date
	 * @return steps      an int array where the first index stores the night and the second index stores the morning
	 */
	public int[] timeAwards(String date){
		int steps[] = {0,0};
		String end = "02:00:00";
		int night = 0;
		int morning = 0;
		//get data
		RequestData req = new RequestData("activities/steps/date/"+date+"/1d");
		req.sendRequest();
		JSONObject obj = new JSONObject(req.getBody());
		JSONArray array = obj.getJSONObject("activities-steps-intraday").getJSONArray("dataset");
		
		//calculate night owl data
		int i = 1;
		JSONObject loopObj = array.getJSONObject(0);
		while(!loopObj.get("time").equals(end)){
			night += Integer.parseInt(loopObj.get("value").toString());
			loopObj = array.getJSONObject(i);
			i++;
		}
		
		//calculate early bird data
		String start = "04:00:00";
		String endMorn = "06:00:00";
		while(!loopObj.get("time").equals(start)){
			loopObj = array.getJSONObject(i);
			i++;
		}
		while(!loopObj.get("time").equals(endMorn)){
			morning += Integer.parseInt(loopObj.get("value").toString());
			loopObj = array.getJSONObject(i);
			i++;
		}
		steps[0] = night;
		steps[1] = morning;
		return steps;
	}
	
	/**
	 * Calculates if heart rate was in cardio range for last 7 days
	 * @return max     an int representing number of days heart rate was in cardio range
	 */
	private int heartrateDays(){
		//make request
		RequestData req = new RequestData("activities/heart/date/today/7d");
		req.sendRequest();
		JSONObject json = new JSONObject(req.getBody());
		JSONArray array = json.getJSONArray("activities-heart");
		int currentDays = 0;
		int max = 0;
		int i = 7;
		
		//loop through array
		while(i!=0){
			JSONObject obj = array.getJSONObject(i-1);
			JSONArray arr = obj.getJSONObject("value").getJSONArray("heartRateZones");
			for(int j = 0; j<4;j++){
				JSONObject arrObj = arr.getJSONObject(j);	
				
				//check if object is correct and increment days if appropriate
				if(arrObj.get("name").equals("Cardio")){
					int min = 0;
					try{
						min = Integer.parseInt(arrObj.get("minutes").toString());
					}
					catch (JSONException e){
						min = 0;
					}
					if(min>0){
						currentDays++;
						if(currentDays>max){
							max = currentDays;
						}
					}
					else{
						currentDays = 0;
					}
				}
			}
			i--;
		}
		return max;
	}
	
	/*
	 * checks if the user has broken the 20mph threshold.  If they have, they earn the Squirrel badge(called Nutty).
	 * @param req   the RequestData object that stores the data necessary to determine whether the badge has been earned
	 * @return      true if the user has ran over 20mph, false otherwise
	 */
	public boolean squirrelAward(RequestData req){
		JSONArray array = new JSONArray(req.getBody());
		int i = 0;
		while(i<array.length()){
			JSONObject obj = array.getJSONObject(i);
			if(obj.get("description").equals("over 20mph")){
				return true;
			}
			i++;
		}
		return false;
	}
	
	/**
	 * Checks if client has ran, swam, and biked for the current date.  If so, they have earned the Triple Threat badge.
	 * @param req       the RequestData object that stores the data necessary to determine whether the badge has been earned
	 * @return data[]   int[] of the minutes the user has proven in each of the three given activities
	 */
	public int[] triathalete(RequestData req){
		int data[] = {0,0,0};
		JSONArray array = new JSONArray(req.getBody());
		int i = 0;
		while(i<array.length()){
			JSONObject obj = array.getJSONObject(i);
			String name = obj.get("name").toString();
			if(name.equals("Run"))
				data[0] += Integer.parseInt(obj.get("duration").toString())/60000;
			else if(name.equals("Swimming"))
				data[1] += Integer.parseInt(obj.get("duration").toString())/60000;
			else if(name.equals("Bike"))
				data[2] += Integer.parseInt(obj.get("duration").toString())/60000;
			i++;
		}
		return data;
	}

	/**
	 * Reads from the awardDat.dat file to determine which the badges that the user has earned. 
	 * @return data     a data file of all the badges that have been earned by the user
	 * @throws exception       thrown if the input cannot be read
	 */
	public static AwardData loadAwardData() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/awardData.dat"));
		AwardData data = (AwardData) in.readObject();
		in.close();
		return data;
	}

	/**
	 * Stores the badges that have earned to a .dat file called awardData.dat. 
	 * @throws exception       thrown if the output cannot be written to
	 * @param data     AwardData object to be read from
	 */
	public static void storeAwardData(AwardData data) throws Exception{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/awardData.dat"));
		out.writeObject(data);
		out.close();
	}
	
	/**
	 * Returns a boolean value of whether the Squirrel badge(called Nutty) has been acheived.  Used by the  
	 * @return achievedSquirrel   boolean of whether the Squirrel badge(called Nutty) has been acheived
	 */
	public boolean isAchievedSquirrel() {
		return achievedSquirrel;
	}

	/**
	 * Returns an int value of the number of steps the user has taken at night.  Used by timeAward() to 
	 * determine whether the Night Owl badge has been acheived.
	 * @return nightSteps    int value of the number of steps that have been taken at night
	 */
	public int getNightSteps() {
		return nightSteps;
	}

	/**
	 * Returns an int value of the number of steps the user has taken during the morning.  Used by timeAward() to 
	 * determine whether the Early Bird badge has been acheived.
	 * @return earlySteps    int value of the number of steps that have been taken during the morning
	 */
	public int getEarlySteps() {
		return earlySteps;
	}

	/**
	 * Returns an int value of the number of minutes the user has run.  Used by triathalete() to determine whether 
	 * the user has earned the Triple Threat badge.
	 * @return runMinutes    int value of the number of minutes the user has run
	 */
	public int getRunMinutes() {
		return runMinutes;
	}

	/**
	 * Returns an int value of the number of minutes the user has swam.  Used by triathalete() to determine whether 
	 * the user has earned the Triple Threat badge.
	 * @return swimMinutes    int value of the number of minutes the user has swam
	 */
	public int getSwimMinutes() {
		return swimMinutes;
	}

	/**
	 * Returns an int value of the number of minutes the user has biked.  Used by triathalete() to determine whether 
	 * the user has earned the Triple Threat badge.
	 * @return bikeMinutes    int value of the number of minutes the user has biked
	 */
	public int getBikeMinutes() {
		return bikeMinutes;
	}

	/**
	 * Returns an int value of the number of days the user has had their heart rate above a certain rate.  Used by 
	 * heartrateDays() to determine whether the user has earned the Healthy Heart badge.
	 * @return daysAboveInARow    int value of the number of how many days the user has had their heart rate 
	 *                            above a predetermined perimeter
	 */
	public int getDaysAboveInARow() {
		return daysAboveInARow;
	}

	/**
	 * Used for the test cases.  Uses a pregenerated awardData.dat file that does not require an API request.
	 * @exception     thrown if the awardData.dat file cannot be read
	 */
	public static void main(String[] args) throws Exception{
		AwardData test2 = loadAwardData();
		System.out.println(test2.getDaysAboveInARow());
	}
}
