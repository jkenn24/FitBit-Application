package ca.uwo.csd.cs2212.team06.dataRetrieval;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuthService;
import ca.uwo.csd.cs2212.team06.App;
import com.github.scribejava.core.model.*; //Request Verb
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;
import java.awt.Desktop;
import java.net.URI;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Example of using access/refresh pair, and authorization credentials
 * @author Team06
 * @version 1.0
 * @category Class
 */
 
public class RequestData
{
	//Attributes
	private static String requestURLPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
	private static String requestBody;
	private Response res;
	private static TokenService service;
	
	//Constructor
	public RequestData(String req){
		requestBody = req;
		service = new TokenService(0);
	}
	
/**
 * sends a request to the Fitbit API
 */
	public void sendRequest() {
		String requestUrl;
		requestUrl = requestURLPrefix + requestBody + ".json";
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service.getService());
		service.getService().signRequest(service.getAccessToken(), request);
		Response response = request.send();
		int statusCode = response.getCode();
		switch(statusCode){
		case 200:
			System.out.println("Retrieving data: "+requestBody);
			break;
		case 400:
			System.out.println("Bad Request - may have to talk to Beth");
			break;
		case 401:
			System.out.println("Likely Expired Token");
			System.out.println("Refreshing Token");
			service().setAccessToken(service().getService().refreshOAuth2AccessToken(service().getAccessToken()));
			request = new OAuthRequest(Verb.GET, requestUrl, service().getService());
			service().getService().signRequest(service().getAccessToken(), request);
			response = request.send();
			new TokenService(1);
			break;
		case 429:
			System.out.println("Rate limit exceeded");
			break;
		default:
		}		
		res = response;
	}
	
/**
 * Returns the requestURLPrefix
 * @return requestURLPrefix   the requestURLPrefix
 */
	public static String getRequestURLPrefix(){
		return requestURLPrefix;
	}
	
/**
 * Return body for json
 * @return   the json body
 */
	public String getBody(){
		return res.getBody();
	}
		
/**
 * Returns service code
 * @return  the code
 */
	public int getCode(){
		return  res.getCode();
	}
	
/**
 * Returns the URL
 * @return  the URL
 */
	public String getURL(){
		return requestURLPrefix+requestBody+".json";
	}
	
/**
 * Returns the service
 * @return service  the service
 */
	private static TokenService service(){
		return service;
	}
}