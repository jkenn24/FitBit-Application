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
public class RefreshTokens
{
	//Attributes
	private static String CALL_BACK_URI="http://localhost:8080";
	private static int CALL_BACK_PORT=8080;
	private static String apiKey, apiSecret, clientID, accessTokenItself, tokenType, refreshToken, rawResponse;
	private static Long expiresIn;
	private final String scope = "activity%20heartrate";
	private static FitbitOAuth20ServiceImpl service;
	private static OAuth2AccessToken accessToken;
	
/**
 * Requests tokens from the fitbit API
 */
	public void getTokens(){
		//read credentials from a file
		BufferedReader bufferedReader=null;
		
		// This will reference one line at a time
		String line = null;
		try {
			// File with service credentials.
			FileReader fileReader = new FileReader("Team6Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();
			fileReader = new FileReader("Team6Tokens.txt");
			bufferedReader = new BufferedReader(fileReader);
			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Reading: file not found");
		}
		catch(IOException ex) {
			System.out.println("Reading: error reading file");
		}
		finally{
			try{
				if (bufferedReader!=null)
					// Always close files.
					bufferedReader.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
	}
	
/**
 * Creates the Fitbit service
 */
	public void oAuth(){
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
				.apiKey(clientID)       //fitbit uses the clientID here
				.apiSecret(apiSecret)
				.callback("http://localhost:8080")
				.scope(scope)
				.grantType("authorization_code")
				.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		//    I have authenticated and given you the contents of the response to use
		accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
	}
	
/**
 * Saves the token to a file
 */
	public void saveToken(){
		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time
		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set
		try {
			FileWriter fileWriter; 
			fileWriter = new FileWriter("Team6Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString() );
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Writing: file not found");             
		}
		catch(IOException ex) {
			System.out.println("Writing: error writing file");            
		}
		finally{
			try{
				if (bufferedWriter!=null)
					bufferedWriter.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
	}
	
/**
 * Returns the service
 * @return    the service
 */
	public FitbitOAuth20ServiceImpl getService(){
		return service;
	}
	
/**
 * Returns the OAuthAccessToken
 * @return    the access token
 */
	public OAuth2AccessToken getAccessToken(){
		return accessToken;
	}
}