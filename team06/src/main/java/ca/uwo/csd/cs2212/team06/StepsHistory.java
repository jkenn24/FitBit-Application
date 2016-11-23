package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Used to track and manage the user's step count history.  Relies on a JSON object created through a request to the FitBit API.
 * @author Team06
 * @version 1.0
 */

public class StepsHistory implements Serializable{
	//Attributes
	private String stepHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public StepsHistory(){
		//get calorie history json
		stepHistory = fetchStepHistory();
	}

	/**
	 * Creates request for step history and sends as a json object
	 * @return    json object that has the steps
	 */
	private static String fetchStepHistory(){
		RequestData req = new RequestData("activities/steps/date/today/max");		//make request
		req.sendRequest();															//send request
		JSONObject json1 = new JSONObject(req.getBody());							//make json from response
		JSONArray json = json1.getJSONArray("activities-steps");
		return json.toString();														//return goals as json
	}
	
	/**
	 * Returns calorie data from a specific date
	 * @param date  String of the date that has specified 
	 * @return      int value of the calorie data, -1 if data does not exist
	 */
	public int getStepData(String date){
		JSONArray array = new JSONArray(stepHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if(json.get("dateTime").equals(date))
				return Integer.parseInt(json.get("value").toString());
		}
		return -1;
	}
}