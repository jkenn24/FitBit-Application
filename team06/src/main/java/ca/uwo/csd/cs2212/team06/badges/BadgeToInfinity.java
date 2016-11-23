package ca.uwo.csd.cs2212.team06.badges;

import java.io.Serializable;
import ca.uwo.csd.cs2212.team06.LifetimeTotals;
import ca.uwo.csd.cs2212.team06.Totals;

/**
 * The ToInfinity badge is earned when the user has climbed the distance it takes to get to the moon(384400000 floors).
 * Also used to check whether the user has earned the badge by accessing their lifetime totals.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeToInfinity implements Serializable{
	//Attributes
	boolean flag;
	private static double metresperfloor = 3;
	private static int theMoon = 384400000;
	private LifetimeTotals lifetime;
	private double percent;
	private static String description = "Climb floors to the moon [128133333 floors]";
	
	//Constructor
	public BadgeToInfinity(){
		try{
			lifetime = Totals.loadTotals().getLTotals();
		}
		catch(Exception e){
			System.out.println("Error: Could not load Totals");
		}
		flag = false;
	}
	
	/**
	 * Tests if the user has climbed 384400000 floors. If yes, then the To Infinity badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if(lifetime.getFloors() * metresperfloor > theMoon){
				flag = true;
				percent = 100;
			}
			else{
				percent = (((double)lifetime.getFloors() * metresperfloor)/ (double)theMoon)*100;
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
	
	/**
	 *@param args   String[] of arguements
	 *@exception    thrown if the arguement list is invalid
	 */
	public static void main(String[] args) throws Exception{
		BadgeToInfinity b = new BadgeToInfinity();
		b.checkBadge();
		System.out.println(b.getPercent());
	}
}