package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Used to track and manage the user's very active minutes history.  Relies on a JSON object created through an API request.
 * @author Team06
 * @version 1.0
 */

public class MinVeryHistory implements Serializable{
	//Attributes
	private String veryHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public MinVeryHistory(){
		//get calorie history json
		veryHistory = fetchMinVeryHistory();
	}

	/**
	 * Creates request for daily goals and sends as a json object
	 * @return    json object that has the goals
	 */
	private static String fetchMinVeryHistory(){
		RequestData req = new RequestData("activities/minutesVeryActive/date/today/max");	//make request
		req.sendRequest();																	//send request
		JSONObject json1 = new JSONObject(req.getBody());									//make json from response
		JSONArray json = json1.getJSONArray("activities-minutesVeryActive");
		return json.toString();																//return goals as json
	}
	
	/**
	 * Returns calorie data from a specific date
	 * @param date  String of the date that has specified 
	 * @return      int value of the calorie data, -1 if date does not exist
	 */
	public int getMinVeryData(String date){
		JSONArray array = new JSONArray(veryHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if (json.get("dateTime").equals(date)) {
				
				return Integer.parseInt(json.get("value").toString());
			}

		}
		return -1;
	}
	
	/**
	 * Returns lifetime very active minutes
	 * @return    int value of the very active minutes
	 */
	public int getLifeVeryMinutes(){
		int i = 0;
		JSONArray array = new JSONArray(veryHistory);
		for(int j = 0; j<array.length();j++){
			i+=Integer.parseInt(array.getJSONObject(j).get("value").toString());
		}
		return i;
	}
}
