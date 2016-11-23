package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import ca.uwo.csd.cs2212.team06.UI.DashboardTab;
import java.io.Serializable;

/**
 * The Customize badge is earned when the user personalized their daily dashboard.
 * Also used to check whether the user has earned the badge by accessing a boolean.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeCustomize implements Serializable{
	//Attributes
	private boolean flag;
	private double percent;
	private static String description = "Customize the User Interface";
	
	//Constructor
	public BadgeCustomize(){
		flag = false;
		percent = 0;
	}
	
	/**
	 * Tests if the dashboard has been customized.  If yes, then the Customize badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(DashboardTab.customized()){
			percent = 100;
			return true;
		}
		percent = 0;
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