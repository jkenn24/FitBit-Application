package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The Heart Rate badge is earned when the user has acheived their heart rate goal for a number of days.
 * Also used to check whether the user has earned the badge by accessing their daily heart rate history.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeHeartRate implements Serializable{
	//Attributes
	private boolean flag;
	private AwardData awards;
	private double percent;
	private static String description = "Reach cardio range heart rate, 7 days in a row";
	
	//Constructor
	public BadgeHeartRate(){
		flag = false;
		try{
			awards = AwardData.loadAwardData();
		}
		catch(Exception e){
			System.out.println("Error: Could not load Award data");
		}
	}
	
	/**
	 * Tests if the heart rate streak has been maintained for a period of time.  If yes, then the Heart Rate badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( awards.getDaysAboveInARow() > 6){
				flag = true;
				percent = 100;
			}
			else{
				percent = (double) awards.getDaysAboveInARow() *(double)(100/7);
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
