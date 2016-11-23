package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The BigMac badge is earned when the user has burned over 26000 calories in their lifetime history.  
 * Also used to check whether the user has earned the badge by accessing their lifetime calories burned count.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeBigMac implements Serializable{
	//Attributes
	private static int bigmac = 1000;
	private boolean flag;
	private LifetimeTotals lifetime;
	private double percent;
	private static String description = "Burn off 26000 Big Mac burgers";
	
	//Constructor
	public BadgeBigMac(){
		try{
			lifetime = Totals.loadTotals().getLTotals();
		}
		catch(Exception e){

			System.out.println("Error loading history");

		}
		flag = false;
	}
	
	/**
	 * Tests if the lifetime value is greater than 26000 calories.  If yes, then the BigMac badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if(lifetime.getCalories() > 26000 * bigmac){
				flag = true;
				percent = 100;
			}
			else{
				percent = ((double)lifetime.getCalories() / (26000 * bigmac))*100;
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