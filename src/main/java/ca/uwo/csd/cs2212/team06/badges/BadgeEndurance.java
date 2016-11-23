package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Endurance badge is earned when the user has been very active for 60 minutes plus over a period of days.
 * Also used to check whether the user has earned the badge by accessing their daily very active minutes.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeEndurance implements Serializable{
	//Attributes
	private static int activeMinutes = 60;
	private boolean flag;
	private MinVeryHistory veryHistory;
	private double percent;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Reach 60 very active minutes, 10 days in a row";
	
	//Constructor
	public BadgeEndurance(String lastdate){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		this.lastdate = lastdate;
		flag = false;
		try{
			veryHistory = History.loadHistory().getVeryHis();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	/**
	 * Tests if the user has maintained a streak of very active minutes over a period of days.  Please consult AwardData.java.
	 * @return test   true if badge achieved, otherwise false
	 */
	private boolean testStreak(){
		boolean test = false;
		int streak = 0;
		int testfails = 0;
		//In case this is the first time they are testing
		//Sets last date to a December 1st 2015
		if(date.equals(lastdate)){
			lastdate = "2015-12-01";
		}
		while(!(date.equals(lastdate)) && !(test)){
			if(veryHistory.getMinVeryData(date) > activeMinutes ){
				streak++;
			}
			else{
				if(testfails == 0){
					percent = streak*(double)(100/7);

				}
				streak = 0;
				testfails++;
			}
			if(streak == 7){
				test = true;
			}
			cal.add(Calendar.DATE, -1);
			date = df.format(cal.getTime());
		}
		return test;
	}
	
	/**
	 * Tests if the streak has been maintained for a period of time.  If yes, then the Endurance badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( this.testStreak()){
				flag = true;
				percent = 100;
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