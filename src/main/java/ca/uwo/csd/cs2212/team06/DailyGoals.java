package ca.uwo.csd.cs2212.team06;
import ca.uwo.csd.cs2212.team06.dataRetrieval.*;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * This class pulls the json info for the daily goals sections
 * @author Team06
 * @version 1.0
 */
public class DailyGoals implements Serializable{
	//Attributes
	private int steps, calories, floors, distance;
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public DailyGoals(){
		//get goals json
		JSONObject json = fetchDailyGoals();
		//assign attributes
		steps = Integer.parseInt(json.get("steps").toString());
		calories = Integer.parseInt(json.get("caloriesOut").toString());
		floors = Integer.parseInt(json.get("floors").toString());
		distance = (int)Double.parseDouble(json.get("distance").toString());
	}
	
	/**
	 * Creates request for daily goals and sends as a json object
	 * @return JSONObject    json that has the goals
	 */
	private static JSONObject fetchDailyGoals(){
		RequestData req = new RequestData("activities/goals/daily");     //make request
		req.sendRequest();												 //send request
		JSONObject json = new JSONObject(req.getBody());				 //make json from response
		return json.getJSONObject("goals");								 //return goals as json
	}

	/**
	 * @return    int of the number of steps the user has taken
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * @return    int of the number of calories the user has taken
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @return     int of the number of floors the user has taken
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * @return     int of the amount of distance traveled
	 */
	public int getDistance() {
		return distance;
	}
}