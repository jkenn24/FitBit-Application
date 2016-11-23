package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The Triathalon badge is earned when the user has ran, biked, adn swam all in the same day.
 * Also used to check whether the user has earned the badge by accessing their lifetime totals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeTriathlon implements Serializable{
	private boolean flag;
	private AwardData awards;
	private double percent;
	private static String description = "Bike, Swim and Run in one day";
	/**
	 * Constructor
	 *
	 */
	public BadgeTriathlon(){
		flag = false;
		try{
			awards = AwardData.loadAwardData();
		}
		catch(Exception e){
			System.out.println("Error: Could not load awards");
		}
	}
	
	/**
	 * Tests if the user has ran, biked, and swam in one day. If yes, then the Triathalon badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( awards.getBikeMinutes() > 0 && awards.getSwimMinutes() > 0 && awards.getRunMinutes() > 0 ){
				flag = true;
				percent = 100;
			}
			else{
				percent = 0;
				if(awards.getBikeMinutes() > 0 ){
					percent = percent + (double)(100/3);
				}
				if(awards.getSwimMinutes() > 0 ){
					percent = percent + (double)(100/3);
				}
				if(awards.getRunMinutes() > 0 ){
					percent = percent + (double)(100/3);
				}	
			}
		}
		return flag;
	}
	
	/**
	 * Returns the percent representaion of how much of the badge has been earned.
	 * @return percent    double value of the percent until completion of the badge
	 */
	public double getPercent(){
		return percent;
	}
	
	/**
	 * Returns the description of the badge so the user knows what they must do in order to earn the badge.
	 * @return description   String of the description
	 */
	public String getDescription(){
		return description;
	}
}
