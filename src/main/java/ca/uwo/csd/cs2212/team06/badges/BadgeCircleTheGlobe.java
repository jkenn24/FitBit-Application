package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.Serializable;

/**
 * The Circle The Globe badge is earned when the user has traveled the distance of the entire globe.
 * Also used to check whether the user has earned the badge by accessing their lifetime distance travelled.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class BadgeCircleTheGlobe implements Serializable {
	//Attributes
	private boolean flag;
	private double percent;
	private static int theWorld = 40075;
	private LifetimeTotals lifetime;
	private static String description = "Travel the distance of the Earth [40,075 km]";
	
	//Constructor
	public BadgeCircleTheGlobe(){
		try{
			lifetime = Totals.loadTotals().getLTotals();
		}
		catch(Exception e){
			System.out.println("Error: Could not load History");
		}
		flag = false;
	}
	
	/**
	 * Tests if the user has walked the distance of the entire globe.  If yes, then the Circle The Globe badge has been earned.
	 * @return flag   true if badge achieved, otherwise false
	 */
	public boolean checkBadge(){
		if(flag == false){
			if(lifetime.getDistance() > theWorld){
				flag = true;
				percent = 100;
			}
			else{
				percent = ((double)lifetime.getDistance() / theWorld)*100;
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
	public static void main(String[] args) throws Exception{
		BadgeCircleTheGlobe b = new BadgeCircleTheGlobe();
		b.checkBadge();
		System.out.println(b.getPercent());
	}
}