package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Used to track and manage the user's lightly active minutes history.  Relies on a JSON object created through an API request.
 * @author Team06
 * @version 1.0
 */

public class MinLightHistory implements Serializable{
	//Attributes
	private String lightHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public MinLightHistory(){
		//get calorie history json
		lightHistory = fetchMinLightHistory();
	}

	/**
	 * Creates request for daily goals and sends as a json object
	 * @return    json object that has the goals
	 */
	private static String fetchMinLightHistory(){
		RequestData req = new RequestData("activities/minutesLightlyActive/date/today/max");	//make request
		req.sendRequest();																		//send request
		JSONObject json1 = new JSONObject(req.getBody());										//make json from response
		JSONArray json = json1.getJSONArray("activities-minutesLightlyActive");
		return json.toString();																	//return goals as json
	}
	
	/**
	 * Returns calorie data from a specific date
	 * @param date  String of the date that has been specified 
	 * @return      int value of the calorie data, -1 if date does not exist
	 */
	public int getMinLightData(String date){
		JSONArray array = new JSONArray(lightHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if(json.get("dateTime").equals(date))
				return Integer.parseInt(json.get("value").toString());
		}
		return -1;
	}
	
	/**
	 * Returns lifetime lightly active minutes
	 * @return    int value of the lightly active minutes
	 */
	public int getLifeLightMinutes(){
		int i = 0;
		JSONArray array = new JSONArray(lightHistory);
		for(int j = 0; j<array.length();j++){
			i+=Integer.parseInt(array.getJSONObject(j).get("value").toString());
		}
		return i;
	}
}