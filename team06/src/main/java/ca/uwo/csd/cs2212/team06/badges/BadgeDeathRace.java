package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import ca.uwo.csd.cs2212.team06.History;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Death Race badge is earned when the user has reached an elevation of 17000ft and has traveled 125km at a rate of 10ft/floor.
 * Also used to check whether the user has earned the badge by accessing the user's lifetime totals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeDeathRace implements Serializable{
	//Attributes
	private boolean flag;
	private DailyTotals daily;
	private double percent;
	private static double theDeathDistance = 125;
	private static int theDeathHeight = 17000;
	private static int feetPerFloor = 10;
	private DistanceHistory distanceHis;
	private ElevationHistory elevationHis;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Complete the Death Race! Travel 125 km and climb 17,000 feet";
	
	//Constructor
	public BadgeDeathRace(String lastdate){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		this.lastdate = lastdate;
		flag = false;
		try{
			daily = Totals.loadTotals().getDTotals();
			distanceHis = History.loadHistory().getdHis();
			elevationHis = History.loadHistory().geteHis();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if an elevation of 17000ft and a distance of 125km at a rate of 10ft/floor has been achieved.  
	 * If yes, then the Death Race badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if(elevationHis.getElevationData(date) > theDeathHeight && distanceHis.getDistanceData(date) > theDeathDistance){
				flag = true;
				percent = 100;
			}
			else{
				//In case this is the first time they are testing
				//Sets last date to a December 1st 2015
				if(date.equals(lastdate)){
					lastdate = "2015-12-01";
				}
				percent = 0.5*((double)(elevationHis.getElevationData(date) / theDeathHeight) + 0.5*((double)distanceHis.getDistanceData(date) / theDeathDistance));
				cal.add(Calendar.DATE, -1);
				date = df.format(cal.getTime());
				while(!(date.equals(lastdate)) && !(flag)){
					if( distanceHis.getDistanceData(date) > theDeathDistance && elevationHis.getElevationData(date) > theDeathHeight){
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
