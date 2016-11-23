package ca.uwo.csd.cs2212.team06.dataRetrieval;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Tests the RequestData and prints out the results 
 * @author Team06
 * @version 1.0
 * @category Class
 */
 
public class TokenService {
	//Attributes
	private static String CALL_BACK_URI="http://localhost:8080";
	private static int CALL_BACK_PORT=8080;
	private static String apiKey, apiSecret, clientID, accessTokenItself, tokenType, refreshToken, rawResponse;
	private static Long expiresIn;
	private static String scope = "activity%20heartrate";
	private static FitbitOAuth20ServiceImpl service;
	private static OAuth2AccessToken accessToken;
	
/**
 * Creates service tokens
 * @param i  if 0 then a token will be retrieved, the OAuth will be 
 * 			 authenticated and the token will be saved.  Else the 
 * 			 token will be saved
 */
	public TokenService(int i){
		if(i==0){
			getTokens();
			oAuth();
			saveToken();
		}
		else
			saveToken();
	}
	
/**
 * Creates tokens from a text file.  Throws and handles its own exceptions.
 */
	private static void getTokens(){
		//read credentials from a file
		BufferedReader bufferedReader=null;
		// This will reference one line at a time
		String line = null;
		try {
			// File with service credentials.

			FileReader fileReader =
					new FileReader("src/main/resources/Team6Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();
			fileReader = new FileReader("src/main/resources/Team6Tokens.txt");
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
				System.out.println("Error closing file\n"+e.getMessage()); 
			}
		}
	}
		
/**
 * Creates authentication tokens
 */
	private static void oAuth(){
		//  Create the Fitbit service
		service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
				.apiKey(clientID)       
				.apiSecret(apiSecret)
				.callback("http://localhost:8080")
				.scope(scope)
				.grantType("authorization_code")
				.build(FitbitApi20.instance());

		//  access token to authenticate request
		accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
	}
	
/**
 * Saves a token
 */
	private static void saveToken(){
		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time
		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set

		try {
			FileWriter fileWriter; 
			fileWriter = new FileWriter("src/main/resources/Team6Tokens.txt");
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
 * Gets the service token
 * @return service    OAuth service
 */
	public FitbitOAuth20ServiceImpl getService(){
		return service;
	}
	
/**
 * Gets the access token
 * @return   the access token
 */
	public OAuth2AccessToken getAccessToken(){
		return accessToken;
	}

/**
 * Sets access token
 * @param token    new token to be set
 */
	public void setAccessToken(OAuth2AccessToken token){
		accessToken = token;
	}
}