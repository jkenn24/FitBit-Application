package ca.uwo.csd.cs2212.team06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Used to read and store the user's history.  Does so by reading and writing to a history.dat file.  
 * Also has a public method used for testing purposes that relies on canned data.
 * @author Team06
 * @version 1.0
 */
 
public class History implements Serializable{

	//Attributes
	private CaloriesHistory cHis;                      //activities/calories  
	private StepsHistory sHis;                         //activities/steps 
	private DistanceHistory dHis;                      //activities/distance 
	private FloorsHistory fHis;                        //activities/floors  
	private ElevationHistory eHis;                     //activities/elevation  
	private MinLightHistory lightHis;                  //activities/minutesLightlyActive  
	private MinFairHistory fairHis;                    //activities/minutesFairlyActive  
	private MinVeryHistory veryHis;                    //activities/minutesVeryActive 
	private MinSedentaryHistory sedHis;
	private static final long serialVersionUID = 1L;

	//Constructor
	public History(){
		cHis = new CaloriesHistory();
		sHis = new StepsHistory();
		dHis = new DistanceHistory();
		fHis = new FloorsHistory();
		eHis = new ElevationHistory();
		lightHis = new MinLightHistory();
		fairHis = new MinFairHistory();
		veryHis = new MinVeryHistory();
		sedHis = new MinSedentaryHistory();
	}

	//Getters
	public CaloriesHistory getcHis() {
		return cHis;
	}

	public StepsHistory getsHis() {
		return sHis;
	}

	public DistanceHistory getdHis() {
		return dHis;
	}

	public FloorsHistory getfHis() {
		return fHis;
	}

	public ElevationHistory geteHis() {
		return eHis;
	}

	public MinLightHistory getLightHis() {
		return lightHis;
	}

	public MinFairHistory getFairHis() {
		return fairHis;
	}

	public MinVeryHistory getVeryHis() {
		return veryHis;
	}
	
	public MinSedentaryHistory getSedHis(){
		return sedHis;
	}

	/**
	 * Loads the user history.  Does so by reading a history.dat file.
	 * @return history    History object of the user
	 * @exception         thrown if the file does not exist or cannot be read
	 */
	public static History loadHistory() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/history.dat"));
		//ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/history.dat"));

		History history = (History) in.readObject();
		in.close();
		return history;
	}
	/**
	 * Stores the user history
	 * @param goals
	 * @throws Exception
	 */
	public static void storeHistory(History history) throws Exception{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/history.dat"));
		//out = new ObjectOutputStream(new FileOutputStream("src/main/resources/history.dat"));
		out.writeObject(history);
		out.close();
	}

	/**
	 * Used for testing. Reads the history from a canned history.dat file.
	 * @param args    String[] of arguements
	 * @exception     thrown if the String[] args is not valid
	 */
	public static void main(String[] args) throws Exception{
		//History history = new History();
		//storeHistory(history);
		History readHistory = loadHistory();
		System.out.println("Lifetime Calories: "+readHistory.getcHis().getLifeCalories());
		System.out.println("Lifetime Elevation: "+readHistory.geteHis().getLifeElevation());
		System.out.println("Lifetime Fairly Active Minutes: "+readHistory.getFairHis().getLifeFairMinutes());
		System.out.println("Lifetime Lightly Active Minutes: "+readHistory.getLightHis().getLifeLightMinutes());
		System.out.println("Lifetime Very Active Minutes: "+readHistory.getVeryHis().getLifeVeryMinutes());
	}
}