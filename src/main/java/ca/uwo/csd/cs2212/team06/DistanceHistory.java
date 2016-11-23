package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Used to track and manage the user's distance travelled.  Relies on JSON objects that are created from API requests and then read from.
 * @author Team06
 * @version 1.0
 */

public class DistanceHistory implements Serializable{
	//Attributes
	private String distHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public DistanceHistory(){
		//get calorie history json
		distHistory = fetchDistanceHistory();
	}

	/**
	 * Creates request for daily goals and sends as a json object
	 * @return      json object that stores the goals
	 */
	private static String fetchDistanceHistory(){
		RequestData req = new RequestData("activities/distance/date/today/max");	//make request
		req.sendRequest();															//send request
		JSONObject json1 = new JSONObject(req.getBody());							//make json from response
		JSONArray json = json1.getJSONArray("activities-distance");
		return json.toString();														//return goals as json
	}
	
	/**
	 * Returns calorie data from a specific date
	 * @param date   Date to be used to pull the distance travelled from    
	 * @return       double value of the calorie data, -1 if date does not exist
	 */
	public double getDistanceData(String date){
		JSONArray array = new JSONArray(distHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if(json.get("dateTime").equals(date))
				return Double.parseDouble(json.get("value").toString());
		}
		return -1;
	}
}