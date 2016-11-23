package ca.uwo.csd.cs2212.team06;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Used to track and manage the user's lifetime totals.  Loads and stores from/to a totals.dat file.
 * @author Team06
 * @version 1.0
 */

public class Totals implements Serializable{
	//Attributes
	private DailyTotals dTotals;
	private LifetimeTotals lTotals;
	private WeeklyTotals wTotals;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that takes a DailyTotals, LifetimeTotals, and WeeklyTotals object
	 */
	public Totals(){
		dTotals = new DailyTotals();
		lTotals = new LifetimeTotals();
		wTotals = new WeeklyTotals();
	}
	
	/**
	 * Getter to return Daily Totals
	 * @return dTotals   DailyTotals object
	 */
	public DailyTotals getDTotals() {
		return dTotals;
	}
	
	/**
	 * Returns Lifetime Totals
	 * @return lTotals   LifetimeTotals object
	 */
	public LifetimeTotals getLTotals() {
		return lTotals;
	}
	
	/**
	 * Returns Weekly Totals
	 * @return wTotals    WeeklyTotals object 
	 */
	public WeeklyTotals getWTotals(){
		return wTotals;
	}
	
	/**
	 * method Totals creates a persistent ObjectInputStream object "in." Read the totals from totals.dat and close the stream
	 * @return totals    Totals object of the totals read from the file
	 * @exception        thrown if the file cannot be read
	 */
	public static Totals loadTotals() throws Exception{
		  ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/totals.dat"));
		  //ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/totals.dat"));
		  Totals totals = (Totals) in.readObject();
		  in.close();
		  return totals;
	}
	/**
	* storeTotals in an ObjectOutputSteam object
	* @param totals        Totals object that has the data to be written
	* @exception           thrown if the file cannot be written to
	*/
	public static void storeTotals(Totals totals) throws Exception{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/totals.dat"));
		//out = new ObjectOutputStream(new FileOutputStream("src/main/resources/totals.dat"));
		out.writeObject(totals);
		out.close();
	}
	/**
	 * Creates a Totals object and loades the totals, following this, print Daily, Weekly, and 
	 * Lifetime totals to the screen 
	 * @param args          String[] of arguements.  Used for testing purposes.
	 * @exception           thrown if the totals cannot be read or written
	 */
	public static void main(String[] args) throws Exception{
		Totals totals = new Totals();
		storeTotals(totals);
		Totals readTotals= loadTotals();
		System.out.println("Daily total: distance: "+readTotals.getDTotals().getSteps());
		System.out.println("Weekly total: distance: "+readTotals.getWTotals().getSteps());
		System.out.println("Lifetime total: distance: "+readTotals.getLTotals().getSteps());
		System.out.println("Lifetime best steps: "+readTotals.getLTotals().getBestSteps()+
				" on "+readTotals.getLTotals().getBestStepsDate());
		System.out.println("Lifetime best distance: "+readTotals.getLTotals().getBestDistance()+
				" on "+readTotals.getLTotals().getBestDistanceDate());
		System.out.println("Lifetime best floors: "+readTotals.getLTotals().getBestFloors()+
				" on "+readTotals.getLTotals().getBestFloorsDate());
	}
}