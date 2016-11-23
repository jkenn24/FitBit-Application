package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The BurnedDays badge is earned when the user has acheived their calories burned goal over a period of ten plus days.
 * Also used to check whether the user has earned the badge
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeBurnedDays implements Serializable{
	//Attributes
	private static int calories = 2200;
	private boolean flag;
	private CaloriesHistory calHistory;
	private double percent;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Burn off 2200 calories, 10 days in a row";

	//Constructor
	public BadgeBurnedDays(String lastdate){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		this.lastdate = lastdate;
		flag = false;
		try{
			calHistory = History.loadHistory().getcHis();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if the badge has been earned by looping through the calories burned lifetimes.  If a streak of 
	 * ten plus days has been fulfilled then the badge has been earned.
	 * @return test   true if current streak is above 10, otherwise false
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
			if(calHistory.getCalData(date) > calories ){
				streak++;
			}
			else{
				if(testfails == 0){
					percent = streak*10;
				}
				streak = 0;
				testfails++;
			}
			if(streak == 10){
				test = true;
			}
			cal.add(Calendar.DATE, -1);
			date = df.format(cal.getTime());
		}
		return test;
	}
	
	/**
	 * Tests if the calories burned streak has been achieved.  If yes, then the BurnedDays badge has been earned.
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
	 * 
	 */
	public String getDescription(){
		return description;
	}
}