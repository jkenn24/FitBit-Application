package ca.uwo.csd.cs2212.team06;

import ca.uwo.csd.cs2212.team06.dataRetrieval.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Used to track and manage the user's weekly totals. Relies on a JSON object created through an API request. 
 * @author Team06
 * @version 1.0
 */

public class WeeklyTotals implements Serializable {
	//Attributes
	private int steps, floors;
	private double distance;
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public WeeklyTotals(){
		int s = 0;
		int f = 0;
		double d = 0;
		Calendar cal = Calendar.getInstance();
		int marker = 0;
		while(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY || marker==0){
			marker = 1;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(cal.getTime());
			RequestData req = new RequestData("activities/date/"+date);
			req.sendRequest();
			JSONObject obj = new JSONObject(req.getBody());
			obj = obj.getJSONObject("summary");
			s += Integer.parseInt(obj.get("steps").toString());
			f += Integer.parseInt(obj.get("floors").toString());
			JSONArray dist = obj.getJSONArray("distances");
			for(int i = 0; i < dist.length(); i++){
				JSONObject arrayObj = dist.getJSONObject(i);
				double y = Double.parseDouble(arrayObj.get("distance").toString());
				d += y;
			}
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}
		steps = s;
		floors = f;
		distance = d;
	}
	
	/**
	 * Returns the steps pulled from the api
	 * @return steps   int value of the steps
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * Returns the distance pulled from the api
	 * @return       double value of the distance traveled
	 */
	public double getDistance() {
		String str = String.format("%1.2f", distance);
		return Double.valueOf(str);
	}

	/**
	 * Returns the floors pulled from the api
	 * @return floors    int value of the number of floors
	 */
	public int getFloors() {
		return floors;
	}
}