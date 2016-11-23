package ca.uwo.csd.cs2212.team06;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.dataRetrieval.RequestData;

/**
 * Tracks the calories burned by the user.  By requesting the total lifetime calories history it can be determined whether 
 * the calorie based badges have been achieved.
 * @author Team06
 * @version 1.0
 */

public class CaloriesHistory implements Serializable{
	//Attributes
	private String calHistory;
	private static final long serialVersionUID = 1L;

	//Constructor
	public CaloriesHistory(){
		//get calorie history json
		calHistory = fetchCalHistory();
	}

	/**
	 * Creates request for daily goals and sends as a json object
	 * @return JSONObject    json that has the goals in a String format
	 */
	private static String fetchCalHistory(){
		RequestData req = new RequestData("activities/calories/date/today/max");	//make request
		req.sendRequest();															//send request
		JSONObject json1 = new JSONObject(req.getBody());							//make json1 from response
		JSONArray json = json1.getJSONArray("activities-calories");					//get array from json1
		return json.toString();													    //return goals as json
	}
	
	/**
	 * Returns an int value of the number of calories burned given on a specific date.
	 * @return     the calorie data of the user, -1 if date does not exist
	 */
	public int getCalData(String date){
		JSONArray array = new JSONArray(calHistory);
		for(int i = 0; i<array.length();i++){
			JSONObject json = array.getJSONObject(i);
			if(json.get("dateTime").equals(date))
				return Integer.parseInt(json.get("value").toString());
		}
		return -1;
	}
	
	/**
	 * Returns an int of the total  number of caloires the user has burned.
	 * @return    int of the total number of caloires burned
	 */
	public int getLifeCalories(){
		int i = 0;
		JSONArray array = new JSONArray(calHistory);
		for (int j = 0; j<array.length();j++){
			i += Integer.parseInt(array.getJSONObject(j).get("value").toString());
		}
		return i;
	}
	
	/**
	 * Returns an int value of the greatest amount of calories burned on a specified date.
	 * @return    int of the most calories burned on a date
	 */
	public int getBestCalories(){
		JSONArray array = new JSONArray(calHistory);
		JSONObject json = array.getJSONObject(0);
		for(int j = 1; j<array.length();j++){
			int i = Integer.parseInt(json.get("value").toString());
			if(i<Integer.parseInt(array.getJSONObject(j).get("value").toString())){
				json = array.getJSONObject(j);
			}
		}
		return Integer.parseInt(json.get("value").toString());
	}
	
	/**
	 * Returns the date of when the most calories have been burned by teh user.
	 * @return    String of the date the user has burned the most calories
	 */
	public String getBestCaloriesDate(){
		JSONArray array = new JSONArray(calHistory);
		JSONObject json = array.getJSONObject(0);
		for(int j = 1; j<array.length();j++){
			int i = Integer.parseInt(json.get("value").toString());
			if(i<Integer.parseInt(array.getJSONObject(j).get("value").toString())){
				json = array.getJSONObject(j);
			}
		}
		return json.get("dateTime").toString();
	}
}
