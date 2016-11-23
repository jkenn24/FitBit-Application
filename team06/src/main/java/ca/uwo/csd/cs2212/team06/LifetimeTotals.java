package ca.uwo.csd.cs2212.team06;

import ca.uwo.csd.cs2212.team06.dataRetrieval.*;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * LifetimeTotals class that takes information from the api and saves it under the totals tab so the user can see their totals. 
 * Info is pulled from the FitBit.api so totals are persistent.
 * @author Team06
 * @version 1.0
 */

public class LifetimeTotals implements Serializable{
	//attributes
	private int steps, floors, calories;
	private double distance;
	private static final long serialVersionUID = 1L;
	private int bestSteps, bestFloors, bestCalories;
	private double bestDistance;
	private String bestStepsDate, bestFloorsDate, bestDistanceDate, bestCaloriesDate;

	//Constructor
	public LifetimeTotals(){
		JSONObject json = fetchTotals();				//get goals json
		JSONObject best = json.getJSONObject("best").getJSONObject("total");
		JSONObject life = json.getJSONObject("lifetime").getJSONObject("total");
		steps = Integer.parseInt(life.get("steps").toString());					//assign attributes
		floors = Integer.parseInt(life.get("floors").toString());
		calories = Integer.parseInt(life.get("caloriesOut").toString());
		distance = Double.parseDouble(life.get("distance").toString());
		bestSteps = Integer.parseInt(best.getJSONObject("steps").get("value").toString());
		bestStepsDate = best.getJSONObject("steps").get("date").toString();
		bestFloors = (int)Math.round(Double.parseDouble((best.getJSONObject("floors").get("value").toString())));
		bestFloorsDate = best.getJSONObject("floors").get("date").toString();
		bestDistance = Double.parseDouble(best.getJSONObject("distance").get("value").toString());
		bestDistanceDate = best.getJSONObject("distance").get("date").toString();
	}
	
	//Getters
	public String getBestStepsDate() {
		return bestStepsDate;
	}

	public String getBestFloorsDate() {
		return bestFloorsDate;
	}

	public String getBestDistanceDate() {
		return bestDistanceDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getBestSteps() {
		return bestSteps;
	}
	
	public int getBestFloors() {
		return bestFloors;
	}
	
	public double getBestDistance() {
		return bestDistance;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public int getFloors() {
		return floors;
	}
	
	public double getDistance() {
		String str = String.format("%1.2f", distance);
		return Double.valueOf(str);
	}
	
	public int getCalories() {
		return calories;
	}

	/**
	 * Creates request for weekly goals and sends as a json object
	 * @return json     json object of the weekly goals
	 */
	private static JSONObject fetchTotals(){
		RequestData req = new RequestData("activities");						//make request
		req.sendRequest();														//send request
		JSONObject json = new JSONObject(req.getBody());						//make json from response
		return json;//.getJSONObject("lifetime").getJSONObject("total");	    //return goals as json
	}
}