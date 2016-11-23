package ca.uwo.csd.cs2212.team06.dataRetrieval;

import org.json.JSONArray;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team06.History;

/**
 * Tests the RequestData and prints out the results 
 * @author Team06
 * @version 1.0
 * @category Class
 */
 
 /**
  * Tests the RequestData and prints out the results.
  */
public class testPull {
	int minSpeed = 20;
	public static void main(String[] args){
		RequestData req = new RequestData("activities/recent");
		req.sendRequest();
		System.out.println(new JSONArray(req.getBody()).toString(2));
	}
}