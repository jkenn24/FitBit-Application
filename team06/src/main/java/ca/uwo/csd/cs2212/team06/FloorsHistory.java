package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Used to track and manage the user's floor history.  Relies on a JSON object to be created form an API request and then read from.
 * @author Team06
 * @version 1.0
 */

public class FloorsHistory implements Serializable{
	//Attributes
	private String floorHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public FloorsHistory(){
		//get calorie history json
		floorHistory = fetchFloorHistory();
	}

	/**
	 * Creates request for daily goals and sends as a json object
	 * @return       json object that has the goals
	 */
	private static String fetchFloorHistory(){
		RequestData req = new RequestData("activities/floors/date/today/max");		//make request
		req.sendRequest();															//send request
		JSONObject json1 = new JSONObject(req.getBody());							//make json from response
		JSONArray json = json1.getJSONArray("activities-floors");
		return json.toString();														//return goals as json
	}
	
	/**
	 * Returns calorie data from a specific date
	 * @return    int value of the calorie data, -1 if the data does not exist
	 */
	public int getFloorData(String date){
		JSONArray array = new JSONArray(floorHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if(json.get("dateTime").equals(date))
				return Integer.parseInt(json.get("value").toString());
		}
		return -1;
	}
}