package ca.uwo.csd.cs2212.team06.UI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the serializable class for Shelly Data, it stores and loads the shelly data which can be passed to ShellyTab.java
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class shellyExperience implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attribute
	private int lifetimeExperience; //private int for lifetime experience
	private int currentExperience; //private int for the current experience for the day
	//Change this to its own class later
	private Date lastDate;
	private boolean born;
	private String lastHat;
	private String lastBody;
	private String lastShell;
	private String name;
	private int bound1;
	private int bound2;
	private int bound3;
	private int bound4;

	//Constructor
	public shellyExperience()
	{
	}
	
	/**
	 * Method to reset shelly to level 1, used for testing purposes
	 */
	public void resetShelly()
	{
		this.lifetimeExperience = 0; 
		this.currentExperience = 0;
		this.born = false;
		this.lastHat = "/FFFFFF.png";
		this.lastBody = "/adult_body_black.png";
		this.lastShell = "/adult_shell_green.png";
		this.name = "Shelly";
		this.bound1 = 0;
		this.bound2 = 0;
		this.bound3 = 0;
		this.bound4 = 0;
	}
	/**
	 * method to get the bounds for Shelly's hats as they are in different locations and are of different sizes
	 * @param boundNumber   int value to be set as bound
	 * @return        an integer depending on the bound being looked for (1 is X, 2 is Y, 3 is width, and 4 is Height), 0 if default
	 */
	public int getBound(int boundNumber) 
	{
		if(boundNumber == 1)
		{
			return this.bound1;
		}
		else if(boundNumber == 2)
		{
			return this.bound2;
		}
		else if(boundNumber == 3)
		{
			return this.bound3;
		}
		else if(boundNumber == 4)
		{
			return this.bound4;
		}
		
		return 0;
	}

	/**
	 * Method to set the bounds of the hat to be serialized
	 * @param x1  int value of the X location of the hat png
	 * @param y1  int value of the Y location of the hat png
	 * @param x2  int value of the Width of the hat png
	 * @param y2  int value of the height of the hat png
	 */
	public void setBounds(int x1, int y1, int x2, int y2) 
	{
		this.bound1 = x1;
		this.bound2 = y1;
		this.bound3 = x2;
		this.bound4 = y2;
	}
	
	/**
	 * Method to get the name of shelly from serialized data
	 * @return name    String of shelly's name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * method to set the name of the serialized shelly
	 * @param name    String to set shelly name for serialization
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * Method to get the file name of last hat shelly had on
	 * @return lastHat   String of the file name of the last hat
	 */
	public String getLastHat() 
	{
		return lastHat;
	}

	/**
	 * @param lastHat    String to set
	 */
	public void setLastHat(String lastHat) 
	{
		this.lastHat = lastHat;
	}

	//Getters and Setters
	public String getLastBody() 
	{
		return lastBody;
	}

	public void setLastBody(String lastBody) 
	{
		this.lastBody = lastBody;
	}

	public String getLastShell() 
	{
		return lastShell;
	}

	public void setLastShell(String lastShell) 
	{
		this.lastShell = lastShell;
	}
	
	public boolean isBorn() 
	{
		return born;
	}

	public void setBorn(boolean born) 
	{
		this.born = born;
	}

	//Getters and Setters
	public int getLifetimeExperience() 
	{
		return lifetimeExperience;
	}

	public void setLifetimeExperience(int lifetimeExperience) 
	{
		this.lifetimeExperience = this.lifetimeExperience + lifetimeExperience;
	}
	
	public void setLifetimeExperienceTotal(int lifetimeExperience)
	{
		this.lifetimeExperience = lifetimeExperience;
	}
	
	public int getCurrentExperience() 
	{
		return currentExperience;
	}

	public void setCurrentExperience(int currentExperience) 
	{
		this.currentExperience = currentExperience;
	}
	
	public Date getLastDate()
	{
		return lastDate;
	}
	
	public void setLastDate(Date tempDate)
	{
		this.lastDate = tempDate;
	}
	
	//Load and store methods
	
	/**
	 *Loads Shelly's current experience from experience.datfile
	 * @exception    thrown if the file cannot be read
	 */
	public static shellyExperience loadShelly() throws Exception
	{
		  ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/experience.dat"));
		  shellyExperience shellyExperience = (shellyExperience) in.readObject();
		  in.close();
		  return shellyExperience; //shellyExperience is the variable that we store/load
	}
	
	/**
	 *Stores Shelly's experience to a experience.dat 
	 * @exception    thrown if th efile cannot be written to
	 */
	public static void storeShelly(shellyExperience shellyExperience ) throws Exception
	{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/experience.dat"));
		out.writeObject(shellyExperience);
		out.close();
	}
} //end of class