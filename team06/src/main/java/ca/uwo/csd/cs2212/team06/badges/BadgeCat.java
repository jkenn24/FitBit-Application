package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Cat badge is earned if the user has been sedantary for as long as a cat is on average(16 hours).
 * Also used to check whether the user has earned the badge by accessing their lifetime sedantary minutes.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeCat implements Serializable{
	//Attributes
	private static int catMinutes = 16*60;
	private boolean flag;
	private MinSedentaryHistory sedentaryHistory;
	private double percent;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Remain sedentary for as long as a cat [16 hours]";
	
	//Constructor
	public BadgeCat(String lastdate){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		this.lastdate = lastdate;
		flag = false;
		try{
			sedentaryHistory = History.loadHistory().getSedHis();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if the user has been sedantary for 16 plus hours.  If yes, then the cat badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if(sedentaryHistory.getMinSedentaryData(date) > catMinutes){
				flag = true;
				percent = 100;
			}
			else{
				//In case this is the first time they are testing
				//Sets last date to a December 1st 2015
				if(date.equals(lastdate)){
					lastdate = "2015-12-01";
				}
				percent = ((double)sedentaryHistory.getMinSedentaryData(date)/catMinutes)*100;

				cal.add(Calendar.DATE, -1);
				date = df.format(cal.getTime());
				while(!(date.equals(lastdate)) && !(flag)){
					if( sedentaryHistory.getMinSedentaryData(date) > catMinutes){
						flag = true;
						percent = 100;
					}
					else{
						cal.add(Calendar.DATE, -1);
						date = df.format(cal.getTime());
					}
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