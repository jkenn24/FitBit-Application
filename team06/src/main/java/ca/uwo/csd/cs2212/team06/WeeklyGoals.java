package ca.uwo.csd.cs2212.team06;

import ca.uwo.csd.cs2212.team06.dataRetrieval.*;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * Used to track and manage the user's weekly goals. Relies on a JSON object created through an API request. 
 * @author Team06
 * @version 1.0
 */

public class WeeklyGoals implements Serializable{
	//Attributes
	private int steps, floors, distance;
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public WeeklyGoals(){
		JSONObject json = fetchWeeklyGoals();									//get goals json
		steps = Integer.parseInt(json.get("steps").toString());					//assign attributes
		floors = Integer.parseInt(json.get("floors").toString());
		distance = (int)Double.parseDouble(json.get("distance").toString());
	}
	
	/**
	 * Creates request for weekly goals and sends as a json object
	 * @return     json object of the weekly goals
	 */
	private static JSONObject fetchWeeklyGoals(){
		RequestData req = new RequestData("activities/goals/weekly");			//make request
		req.sendRequest();														//send request
		JSONObject json = new JSONObject(req.getBody());						//make json from response
		return json.getJSONObject("goals");										//return goals as json
	}
	
	/**
	 * Getter for integer value steps
	 * @return steps    int value of steps
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * getter for floors
	 * @return floors   int value of floors climbed
	 */
	public int getFloors() {
		return floors;
	}
	
	/**
	 * getter for distance
	 * @return distance   int value of distance travelled      
	 */
	public int getDistance() {
		return distance;
	}
}