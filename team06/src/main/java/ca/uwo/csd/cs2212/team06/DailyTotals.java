package ca.uwo.csd.cs2212.team06;
import ca.uwo.csd.cs2212.team06.dataRetrieval.*;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class pulls the json info for the daily goals sections
 * @author Team06
 * @version 1.0
 */
public class DailyTotals implements Serializable{
	//Attributes
	private int steps, floors, calories;
	private double distance, elevation;
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public DailyTotals(){
		//get goals json
		JSONObject json = fetchDailyTotals();
		//assign attributes
		steps = Integer.parseInt(json.get("steps").toString());
		floors = Integer.parseInt(json.get("floors").toString());
		calories = Integer.parseInt(json.get("caloriesOut").toString());
		elevation = Double.parseDouble(json.get("elevation").toString());
		JSONArray dArray = json.getJSONArray("distances");
		double dist = 0;
		for(int i = 0; i < dArray.length(); i++){
			JSONObject arrayObj = dArray.getJSONObject(i);
			double y = Double.parseDouble(arrayObj.get("distance").toString());
			dist += y;
		}
		distance = dist;
	}

	/**
	 * Creates request for daily goals and sends as a json object
	 * @return     json object of the daily totals of the user
	 */
	private static JSONObject fetchDailyTotals(){
		RequestData req = new RequestData("activities/date/today");			//make request
		req.sendRequest();													//send request
		JSONObject json = new JSONObject(req.getBody());					//make json from response
		return json.getJSONObject("summary");								//return goals as json
	}

	/**
	 * Returns the daily number of steps
	 * @return    int value of the number of steps
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * Returns the daily floors traversed
	 * @return     int value of the number of floors traveresed
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * Returns the number of calories burned in the daily
	 * @return     int value of the number of calories burned
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * Returns the amount of elevation scaled
	 * @return     double value of the elevation the user has climbed
	 * 
	 */
	public double getElevation() {
		String str = String.format("%1.2f", elevation);
		return Double.valueOf(str);
	}

	/**
	 * Returns the daily distance traveled in km
	 * @return     double value of the distance travelled in km
	 */
	public double getDistance() {
		String str = String.format("%1.2f", distance);
		return Double.valueOf(str);
	}
}