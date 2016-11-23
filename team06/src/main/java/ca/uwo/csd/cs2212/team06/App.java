package ca.uwo.csd.cs2212.team06;

import ca.uwo.csd.cs2212.team06.UI.FitBit;
import ca.uwo.csd.cs2212.team06.UI.shellyExperience;
import ca.uwo.csd.cs2212.team06.badges.Badges;

/**
 * Builds the FitBit application.  Depending on the args input, it will run off of either canned data or newly requested data.
 * After determing which data to use it will store the user's goals, totals, history, award data, badges, and inaddtion check if the 
 * user has acquired any new badges.  If an invalid input is put into the command line, an error message will be printed to the screen.
 * @author Team06
 * @version 1.0
 */

public class App{

	/**
	 * Builds the application.  Uses an if statement to determine whether to use test data or to request new info.  The app will not be built 
	 * if too many arguements are used in the command line or if an invalid arguement is used.  If an exception is thrown, the method will 
	 * handle it and print an eror message.
	 * @param args     The input from the command line
	 */
	public static void main(String[] args) {
		
		FitBit ui;
			if(args.length > 0 && args[0].equals("test"))
			{
				FitBit.main(args);
			}
			else if(args.length > 0 && args[0].equals("testReset"))
			{
				try 
				{
					shellyExperience shelly = shellyExperience.loadShelly();
					shelly.resetShelly();
					shellyExperience.storeShelly(shelly);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				FitBit.main(args);
			}
			else if(args.length > 0 && args[0].equals("test25"))
			{
				try 
				{
					shellyExperience shelly = shellyExperience.loadShelly();
					shelly.setLifetimeExperienceTotal(138125);
					shellyExperience.storeShelly(shelly);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				FitBit.main(args);
			}
			else if(args.length > 0 && args[0].equals("test50"))
			{
				try 
				{
					shellyExperience shelly = shellyExperience.loadShelly();
					shelly.setLifetimeExperienceTotal(1073125);
					shellyExperience.storeShelly(shelly);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				FitBit.main(args);
			}
			else if (args.length > 0){
				System.out.println("Invalid Argument");
				System.out.println("Exiting");
			}

		else{
			try{
				Goals goals = new Goals();
				Goals.storeGoals(goals);
				Totals totals = new Totals();
				Totals.storeTotals(totals);
				History history = new History();
				History.storeHistory(history);
				AwardData award = new AwardData();
				AwardData.storeAwardData(award);
				Badges badge = new Badges();
				badge.checkAllBadges("");
				Badges.storeBadges(badge);
				ui = new FitBit();
				ui.main(args);
			}
			catch (Exception e){
				System.out.println("Error refreshing data");
				System.out.println("Run in test mode");
			}
		}
	}
}
