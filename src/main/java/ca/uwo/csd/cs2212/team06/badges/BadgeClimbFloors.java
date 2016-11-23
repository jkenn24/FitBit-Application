package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The climbed floors badge is earned when the user has climbed th eheight of the CN tower(1815 floors).
 * Also used to check whether the user has earned the badge by accessing their lifetime floors total.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeClimbFloors implements Serializable {
	//Attributes
	private boolean flag;
	private double percent;
	private LifetimeTotals lifetime;
	private static int CNTower = 1815;
	private static int feetPerFloor = 10;
	private static String description = "Climb floors to peak of CN Tower [1815 feet]";
	
	//Constructor
	public BadgeClimbFloors(){
		try{
			lifetime = Totals.loadTotals().getLTotals();
		}
		catch(Exception e){
			System.out.println("Error: Could not load lifetime totals");
		}
		flag = false;
	}
	
	/**
	 * Tests if 1815 floors have been climbed.  If yes, then the Climbed Floors badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if((double)lifetime.getFloors() * feetPerFloor > CNTower){
				flag = true;
				percent = 100;
			}
			else{
				percent = ((double)(lifetime.getFloors() * feetPerFloor) / CNTower)*100;
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