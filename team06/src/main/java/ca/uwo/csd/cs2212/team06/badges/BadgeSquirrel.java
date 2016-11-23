package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The Squirrel badge is earned when the user has biked at the average speed of a squirrel running.
 * Also used to check whether the user has earned the badge by accessing their lifetime totals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeSquirrel implements Serializable{
	//Attributes
	private boolean flag;
	private AwardData awards;
	private double percent;
	private static String description = "Reach the maximum FitBit speed";
	
	//Constructor
	public BadgeSquirrel(){
		flag = false;
		try{
			awards = AwardData.loadAwardData();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if the user has biked at the speed of the average squirrel's running speed.  
	 * If yes, then the Squirrel badge has been earned. Please consult AwardsBadge.java for more info
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( awards.isAchievedSquirrel()){
				flag = true;
				percent = 100;
			}
			else{
				percent = 0;
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