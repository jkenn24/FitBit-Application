package ca.uwo.csd.cs2212.team06.badges;

import ca.uwo.csd.cs2212.team06.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Used to track and manage the completion of every badge.  If every badge is earned, then the user will receive the Completionist badge.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class Badges implements Serializable{
	//Attributes
	private boolean[] allBadges;
	private double[] allPercentages;
	private String[] allDescriptions;
	private String lastdate;
	private Calendar cal;
	private DateFormat df;
	
	//Constructor
	public Badges(){
		allBadges = new boolean[21];
		allPercentages = new double[21];
		allDescriptions = new String[21];
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		lastdate = df.format(cal.getTime());
	}
	
	/**
	 * Tests if every badge has been achieved. If a badge has not been achieved, tests if 
	 * it has been achieved since last login
	 *@param s   String to hold every boolean value of the status of the badges
	 */
	public void checkAllBadges(String s){
		if(allBadges[0] == false){
			BadgeBigMac b0 = new BadgeBigMac();
			allBadges[0] = b0.checkBadge();
			allPercentages[0] = b0.getPercent();
			allDescriptions[0] = b0.getDescription();
		}
		if(allBadges[1] == false){
			BadgeCircleTheGlobe b1 = new BadgeCircleTheGlobe();
			allBadges[1] = b1.checkBadge();
			allPercentages[1] = b1.getPercent();
			allDescriptions[1] = b1.getDescription();
		}
		if(allBadges[2] == false){
			BadgeClimbFloors b2 = new BadgeClimbFloors();
			allBadges[2] = b2.checkBadge();
			allPercentages[2] = b2.getPercent();
			allDescriptions[2] = b2.getDescription();
		}
		if(allBadges[3] == false){
			BadgeMordor b3 = new BadgeMordor();
			allBadges[3] = b3.checkBadge();
			allPercentages[3] = b3.getPercent();
			allDescriptions[3] = b3.getDescription();
		}
		if(allBadges[4] == false){
			BadgeOneStep b4 = new BadgeOneStep();
			allBadges[4] = b4.checkBadge();
			allPercentages[4] = b4.getPercent();
			allDescriptions[4] = b4.getDescription();
		}
		if(allBadges[5] == false){
			BadgeToInfinity b5 = new BadgeToInfinity();
			allBadges[5] = b5.checkBadge();
			allPercentages[5] = b5.getPercent();
			allDescriptions[5] = b5.getDescription();
		}
		if(allBadges[6] == false){
			BadgeDeathRace b6 = new BadgeDeathRace(lastdate);
			allBadges[6] = b6.checkBadge();
			allPercentages[6] = b6.getPercent();
			allDescriptions[6] = b6.getDescription();
		}
		if(allBadges[7] == false){
			BadgeCat b7 = new BadgeCat(lastdate);
			allBadges[7] = b7.checkBadge();
			allPercentages[7] = b7.getPercent();
			allDescriptions[7] = b7.getDescription();
		}
		if(allBadges[8] == false){
			BadgeEndurance b8 = new BadgeEndurance(lastdate);
			allBadges[8] = b8.checkBadge();
			allPercentages[8] = b8.getPercent();
			allDescriptions[8] = b8.getDescription();
		}
		if(allBadges[9] == false){
			BadgeMakeGoal b9 = new BadgeMakeGoal(lastdate);
			allBadges[9] = b9.checkBadge();
			allPercentages[9] = b9.getPercent();
			allDescriptions[9] = b9.getDescription();
		}
		if(allBadges[10] == false){
			BadgeMitochondria b10 = new BadgeMitochondria(lastdate);
			allBadges[10] = b10.checkBadge();
			allPercentages[10] = b10.getPercent();
			allDescriptions[10] = b10.getDescription();
		}
		if(allBadges[11] == false){
			BadgeCustomize b11 = new BadgeCustomize();
			allBadges[11] = b11.checkBadge();
			allPercentages[11] = b11.getPercent();
			allDescriptions[11] = b11.getDescription();
		}
		if(allBadges[12] == false){
			BadgeWorkoutDays b12 = new BadgeWorkoutDays(lastdate);
			allBadges[12] = b12.checkBadge();
			allPercentages[12] = b12.getPercent();
			allDescriptions[12] = b12.getDescription();
		}
		if(allBadges[13] == false){
			BadgeNightOwl b13 = new BadgeNightOwl(lastdate);
			if(!s.equals("test")){
				allBadges[13] = b13.checkBadge();
				allPercentages[13] = b13.getPercent();
				allDescriptions[13] = b13.getDescription();
			}
		}
		if(allBadges[14] == false){
			BadgeEarlyBird b14 = new BadgeEarlyBird(lastdate);
			if(!s.equals("test")){
				allBadges[14] = b14.checkBadge();
				allPercentages[14] = b14.getPercent();
				allDescriptions[14] = b14.getDescription();
			}
		}
		if(allBadges[15] == false){
			BadgeTriathlon b15 = new BadgeTriathlon();
			allBadges[15] = b15.checkBadge();
			allPercentages[15] = b15.getPercent();
			allDescriptions[15] = b15.getDescription();
		}
		if(allBadges[16] == false){
			BadgeHeartRate b16 = new BadgeHeartRate();
			allBadges[16] = b16.checkBadge();
			allPercentages[16] = b16.getPercent();
			allDescriptions[16] = b16.getDescription();
		}
		if(allBadges[17] == false){
			BadgeSquirrel b17 = new BadgeSquirrel();
			allBadges[17] = b17.checkBadge();
			allPercentages[17] = b17.getPercent();
			allDescriptions[17] = b17.getDescription();

		}
		if(allBadges[18] == false){
			BadgeBurnedDays b18 = new BadgeBurnedDays(lastdate);
			allBadges[18] = b18.checkBadge();
			allPercentages[18] = b18.getPercent();
			allDescriptions[18] = b18.getDescription();
		}
		if(allBadges[19] == false){
			BadgeNewYears b19 = new BadgeNewYears();
			allBadges[19] = b19.checkBadge();
			allPercentages[19] = b19.getPercent();
			allDescriptions[19] = b19.getDescription();
		}
		if(allBadges[20] == false){
			int i = 0, count = 20;

			boolean test = true;
			while(i < 20){
				if(allBadges[i] == false){
					test = false;
					count--;
				}
				i++;
			}
			allBadges[20] = test;
			allPercentages[20] = count/20;
			allDescriptions[20] = "Get all the Badges";
		}
		
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		lastdate = df.format(cal.getTime());
	}
	
	/**
	 * Returns a boolean array of every badge.  Every index corresponds to a specific badge. 
	 * If every index is true then the badge has been earned.
	 * @return allBadges     boolean[] true if badge achieved Each location in array corresponds to a badge
	 */
	public boolean[] getBadges(){
		return allBadges;
	}
	
	/**
	 * Returns the percent representaion of how much of every badge has been completed
	 * @return allPercentages    double[] of all percentages until completion of every badges
	 */
	public double[] getPercentages(){
		return allPercentages;
	}
	
	/**
	 * Returns the description of every badge so the user knows what they must do in order to earn the badge.
	 * @return allDescriptions   String[] of every badge description
	 */
	public String[] getDescriptions(){
		return allDescriptions;
	}
	
	/**
	 * Stores the badge class to a Badges.dat file.
	 * @param badges   the Badges object to be stored
	 * @exception      thrown if the file cannot be written to or does not exist
	 */
	public static void storeBadges(Badges badges) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("target/classes/Badges.dat"));
        out.writeObject(badges);
        out.close();

	}
	
	/**
	 * Loads the badge class from a Badges.dat file.
	 * @exception    thrown if the file cannot be read or does not exits
	 */
	public static Badges loadBadges() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/Badges.dat"));
        Badges badges = (Badges) in.readObject();
        in.close();
        return badges;
	}
}