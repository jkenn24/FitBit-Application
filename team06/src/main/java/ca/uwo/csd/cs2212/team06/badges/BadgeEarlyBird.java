package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Early Bird badge is earned when the user is active between the times of 4am-6am.
 * Also used to check whether the user has earned the badge by accessing the user's lifetime totals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeEarlyBird implements Serializable{
	//Attributes
	private boolean flag;
	private AwardData awards;
	private double percent;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Workout between 4am and 6am [Greater than 500 steps]";
	
	//Constructor
	public BadgeEarlyBird(String lastdate){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		percent = 0;
		this.lastdate = lastdate;
		flag = false;
		try{
			awards = AwardData.loadAwardData();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if the user was active between 4am-6am. Please consult AwardData.java.
	 * If yes, then the Early Bird badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( awards.timeAwards(date)[1] > 500){
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