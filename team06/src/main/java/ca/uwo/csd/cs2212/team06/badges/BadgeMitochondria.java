package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Mitochondria badge is earned when the user has been active for 120 plus minutes.
 * Also used to check whether the user has earned the badge by accessing their daily active minutes.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeMitochondria implements Serializable{
	//Attributes
	private static int activeMinutes = 120;
	private boolean flag;
	private MinVeryHistory veryHistory;
	private double percent;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Reach 120 very active minutes in a day";

	//Constructor
	public BadgeMitochondria(String lastdate){
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
	 * Tests if the user has been active for 120 minutes.  If yes, then the Mitochondria badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( veryHistory.getMinVeryData(date) > activeMinutes){
				flag = true;
				percent = 100;
			}
			else{
				//In case this is the first time they are testing
				//Sets last date to a December 1st 2015
				if(date.equals(lastdate)){
					lastdate = "2015-12-01";
				}
				percent = ((double)veryHistory.getMinVeryData(date)/activeMinutes)*100;
				cal.add(Calendar.DATE, -1);
				date = df.format(cal.getTime());
				while(!(date.equals(lastdate)) && !(flag)){
					if( veryHistory.getMinVeryData(date) > activeMinutes){
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
