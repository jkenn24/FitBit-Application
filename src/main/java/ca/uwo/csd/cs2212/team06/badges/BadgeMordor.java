package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The Mordor badge is earned when the user has traveled the distance from the Shire to the Tower of Soran.
 * Also used to check whether the user has earned the badge by accessing their distance traveled history.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeMordor implements Serializable{
	//Attributes
	private static double distance = 2863;
	private boolean flag;
	private LifetimeTotals lifetime;
	private double percent;
	private static String description = "Travel from the Shire to Mordor [2863 km]";
	
	//Constructor
	public BadgeMordor(){
		try{
			lifetime = Totals.loadTotals().getLTotals();
		}
		catch(Exception e){
			System.out.println("Error: Could not load Totals");
		}
		flag = false;
	}
	
	/**
	 * Tests if the user has traveled the necessary distance.  If yes, then the Mordor badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if(lifetime.getDistance() > distance){
				flag = true;
				percent = 100;
			}
			else{
				percent = ((double)lifetime.getDistance()/distance)*100;
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
	 *@exception    thrown if the args list is invalid
	 */
	public static void main(String[] args) throws Exception{
		BadgeMordor b = new BadgeMordor();
		b.checkBadge();
		System.out.println(b.getPercent());
	}
}