package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The make a goal badge is earned when the user has completed one of their own personal goals.
 * Also used to check whether the user has earned the badge by accessing their goals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeMakeGoal implements Serializable{
	//Attributes
	private boolean flag;
	private DistanceHistory distanceHis;
	private CaloriesHistory	caloriesHis;
	private StepsHistory stepsHis;
	private DailyGoals dGoals;
	private double percent;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	private String date;
	private static String description = "Reach one of your daily goals";
	
	//Constructor
	public BadgeMakeGoal(String lastdate){
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(cal.getTime());
		this.lastdate = lastdate;
		flag = false;
		try{
			distanceHis = History.loadHistory().getdHis();
			caloriesHis = History.loadHistory().getcHis();
			stepsHis = History.loadHistory().getsHis();
			dGoals = Goals.loadGoals().getDGoals();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
	}
	
	/**
	 * Tests if a goal has been achieved.  If yes, then the make a goal badge has been earned.
	 * @return test   true if badge achieved, otherwise false
	 */
	private boolean testGoals(){
		boolean test = false;
		percent = 25*((double)distanceHis.getDistanceData(date) / dGoals.getDistance()) +
				25*((double)caloriesHis.getCalData(date) / dGoals.getCalories()) + 
				25*((double)stepsHis.getStepData(date) / dGoals.getSteps());
		//In case this is the first time they are testing
		//Sets last date to a December 1st 2015
		if(date.equals(lastdate)){
			lastdate = "2015-12-01";
		}
		while(!(date.equals(lastdate)) && !(test)){
			if((distanceHis.getDistanceData(date) > dGoals.getDistance()) && 
					(caloriesHis.getCalData(date) > dGoals.getCalories()) && (stepsHis.getStepData(date) > dGoals.getSteps())){
				test = true;
			}
			else{
				cal.add(Calendar.DATE, -1);
				date = df.format(cal.getTime());
			}
		}
		return test;
	}
	
	/**
	 * Tests if the heart rate streak has been maintained for a period of time.  If yes, then the Heart Rate badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if( this.testGoals()){
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