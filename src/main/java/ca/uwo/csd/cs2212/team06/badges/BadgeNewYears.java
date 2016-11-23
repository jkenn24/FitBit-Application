package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The New Years badge is earned when the user is active on New Years day(Jan 1st).
 * Also used to check whether the user has earned the badge by accessing their lifetime totals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeNewYears implements Serializable{
	//Attributes
	private boolean flag;
	private MinVeryHistory veryHistory;
	private MinFairHistory fairHistory;
	private double percent;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Workout on New Years Eve";
	
	//Constructor
	public BadgeNewYears(){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		flag = false;
		try{
			veryHistory = History.loadHistory().getVeryHis();
			fairHistory = History.loadHistory().getFairHis();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if the user has been active on New Years Day (Jan 1st).  If yes, then the Mordor badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		String year = date.substring(0, 3);
		if(flag == false){
			if(date.equals(year+"-12-31") ){
				if(veryHistory.getMinVeryData(date) > 0 || fairHistory.getMinFairData(date)> 0){
					flag = true;
					percent = 100;
				}
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
