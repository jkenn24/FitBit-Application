package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Used to manage and track the user's elevation climbed.  Relies on a JSON object to be created form an API request and then read from.
 * @author Team06
 * @version 1.0
 */

public class ElevationHistory implements Serializable{
	//Attributes
	private String elevHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public ElevationHistory(){
		//get calorie history json
		elevHistory = fetchElevHistory();
	}

	/**
	 * Creates request for daily goals and sends as a json object.
	 * @return       json object that has the goals
	 */
	private static String fetchElevHistory(){
		RequestData req = new RequestData("activities/elevation/date/today/max");	//make request
		req.sendRequest();															//send request
		JSONObject json1 = new JSONObject(req.getBody());							//make json from response
		JSONArray json = json1.getJSONArray("activities-elevation");
		return json.toString();														//return goals as json
	}
	
	/**
	 * Returns calorie data from a specific date.
	 * @return     double value of the calorie data, -1 if date does not exist
	 */
	public double getElevationData(String date){
		JSONArray array = new JSONArray(elevHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if(json.get("dateTime").equals(date))
				return Double.parseDouble(json.get("value").toString());
		}
		return -1;
	}
	
	/**
	 * Returns lifetime elevation
	 * @return    double value of the total lifetime elevation
	 */
	public double getLifeElevation(){
		double i = 0;
		JSONArray array = new JSONArray(elevHistory);
		for(int j = 0; j<array.length(); j++){
			i+= Double.parseDouble(array.getJSONObject(j).get("value").toString());
		}
		return i;
	}
}