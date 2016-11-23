package ca.uwo.csd.cs2212.team06;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Used to track and manage the user's goals.  The goals are pulled fom the API through a request that is stored in a JSON object.  Afterwards the 
 * goals are recorded to a goals.dat file.
 * @author Team06
 * @version 1.0
 */
public class Goals implements Serializable{
	//Attributes
	private DailyGoals dGoals;
	private WeeklyGoals wGoals;
	private static final long serialVersionUID = 1L;

	//Constructor
	public Goals(){
		dGoals = new DailyGoals();
		wGoals = new WeeklyGoals();
	}

	/**
	 * Returns the  daily goals of the user
	 * @return dGoals    the DailyGoals of the user
	 */
	public DailyGoals getDGoals() {
		return dGoals;
	}

	/**
	 * Returns the weekly goals of the user
	 * @return wGoals    the WeeklyGoals fo the user
	 */
	public WeeklyGoals getWGoals(){
		return wGoals;
	}

	/**
	 * Loads the user goals
	 * @return goals       Goals object of the user
	 * @exception   thrown if the file input cannot be read from
	 */
	public static Goals loadGoals() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/goals.dat"));
		//ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/goals.dat"));

		Goals goals = (Goals) in.readObject();
		in.close();
		return goals;
	}
	/**
	 * Stores the user's goals to a specifies file
	 * @param goals     Goals object that contains the user's goals
	 * @exception       thrown if the file does not exist 
	 */
	public static void storeGoals(Goals goals) throws Exception{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/goals.dat"));
		//out = new ObjectOutputStream(new FileOutputStream("src/main/resources/goals.dat"));
		out.writeObject(goals);
		out.close();
	}

	/**
	 * Get and stores goals from user
	 * @param args   String[] of arguements
	 * @exception    thrown if the goals cannot be read or stored
	 */
	public static void main(String[] args) throws Exception{
		Goals goals = new Goals();
		storeGoals(goals);
		Goals readGoals = loadGoals();
		System.out.println(readGoals.getDGoals().getDistance());
	}
}