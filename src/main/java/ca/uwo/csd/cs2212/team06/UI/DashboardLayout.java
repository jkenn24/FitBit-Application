package ca.uwo.csd.cs2212.team06.UI;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

/**
 * Class to layout the dashboard grid.
 * @author team06
 * @version 1.0
 * @category Class
 */
 
public class DashboardLayout extends JPanel implements Serializable{
	//Attributes
	private static final long serialVersionUID = -4662203491871665905L;
	private int numComponents;
	private static MyDraggableComponent onScreen[][] = new MyDraggableComponent[3][3];
	private MyDraggableComponent allWidgets[] = new MyDraggableComponent[15];
	private int gridWidth;
	private int gridHeight; 
	private int totalWidth;
	private int totalHeight;
	private Boolean moveable = false;
	int gridX;
	int gridY;
	
	/**
	 * The dashboard layout constructor creates a 9x9 grid for the panel
	 * @param panelWidth    int value of the width of the panel
	 * @param panelHeight   int value of the height of the panel
	 */
	public DashboardLayout(int panelWidth, int panelHeight){
		setBounds(0,0, panelWidth, panelHeight);
		super.setOpaque(false);
		super.setBackground(new Color(0,0,0,0));
		totalWidth = panelWidth;
		totalHeight = panelHeight;
		gridWidth = 900;
		gridHeight = 184*3;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				onScreen[i][j] = null;
			}
		}
		numComponents = 0;
	}
	
	/**
	 * Adds a new widget to the array of widgets, but not the the screen. Increments the number of comonents added.
	 * @param add   MyDraggableComponent object component to add
	 */
	public void newWidget(MyDraggableComponent add){
		allWidgets[numComponents] = add;
		numComponents++;
	}
	
	/**
	 * Adds a widget to the first available space on the screen
	 * @param add    MyDraggableComponent object component to add
	 */
	public void addWidget(MyDraggableComponent add){
		super.add(add);
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if (onScreen[j][i] == null){
					onScreen[j][i] = add;
					onScreen[j][i].setLocation(j*gridWidth/3, i*gridHeight/3);
					return;
				}
			}
		}
		try {
			storeWidgets();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the widgets from the array onto the screen. This is used when the array is read from the serialized file
	 */
	public void loadWidgetsOnScreen(){
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if (onScreen[j][i] != null){
					super.add(onScreen[j][i]);
					onScreen[j][i].setLocation(j*gridWidth/3, i*gridHeight/3);
				}
			}
		}
		return;
	}
	
	/**
	 * Called when a widget is moved, it looks to see if the location being moved to is full or not. 
	 * If it is, the panels swap positions, otheriwse, the panel is placed there, and the array is updated
	 * 
	 * @param type    String of the widget being moved so it can be searched for
	 * @param locX    int value of the X location that the panel was dropped
	 * @param locY    int value of the Y location where the panel was dropped
	 */
	public void moveWidget(String type, int locX, int locY){
		Point newSpot;
		if (locX < gridWidth/3){
			if (locY < gridHeight/3){
				newSpot = new Point(0,0);
			} else if (locY < 2*gridHeight/3){
				newSpot = new Point(0,1);
			} else {
				newSpot = new Point(0,2);
			}
		} else if (locX < 2*gridWidth/3){
			if (locY < gridHeight/3){
				newSpot = new Point(1,0);
			} else if (locY < 2*gridHeight/3){
				newSpot = new Point(1,1);
			} else {
				newSpot = new Point(1,2);
			}
		} else {
			if (locY < gridHeight/3){
				newSpot = new Point(2,0);
			} else if (locY < 2*gridHeight/3){
				newSpot = new Point(2,1);
			} else {
				newSpot = new Point(2,2);
			}
		}
		MyDraggableComponent temp;
		Point old = search(type);
		if (onScreen[(int)newSpot.getX()][(int)newSpot.getY()] != null){
			temp = onScreen[(int)newSpot.getX()][(int)newSpot.getY()];
			onScreen[(int)newSpot.getX()][(int)newSpot.getY()] = onScreen[(int)old.getX()][(int)old.getY()];
			onScreen[(int)newSpot.getX()][(int)newSpot.getY()].setLocation((int)newSpot.getX()*gridWidth/3, (int)newSpot.getY()*gridHeight/3);
			onScreen[(int)old.getX()][(int)old.getY()] = temp;
			onScreen[(int)old.getX()][(int)old.getY()].setLocation((int)old.getX()*gridWidth/3, (int)old.getY()*gridHeight/3);
		} else {
			onScreen[(int)newSpot.getX()][(int)newSpot.getY()] = onScreen[(int)old.getX()][(int)old.getY()];
			onScreen[(int)old.getX()][(int)old.getY()] = null;
			onScreen[(int)newSpot.getX()][(int)newSpot.getY()].setLocation((int)newSpot.getX()*gridWidth/3, (int)newSpot.getY()*gridHeight/3);
		}
		try {
			storeWidgets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * Method to clear the screen of all widgets. Used when the user Adds/ Removes Widgets.
	 */
	public void clearScreen(){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if(onScreen[i][j] != null){
					super.remove(onScreen[i][j]);
					onScreen[i][j] = null;
				}
			}
		}
	}
	
	/**
	 * Method returns the Height of the dashboard
	 * @return totalHeight  itn value of the height of the dashboard
	 */
	public int getHeight(){
		return totalHeight;
	}
	
	/**
	 * Method returns the Width of the dashboard
	 * @return totalWidth   int value of the width of the dashboard
	 */
	public int getWidth(){
		return totalWidth;
	}
	
	/**
	 * Searches the screen for the location of the Widget type passed in
	 * @param s    String of the widget being looked for
	 * @return     the point at which the widget is, null if not found
	 */
	public Point search(String s){
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if (onScreen[i][j] != null){
					if (onScreen[i][j].getType().equals(s)){
						return new Point(i,j);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Finds a widget in the array of all possible widgets based on the type of widget passed in
	 * @param s    String of the type of the widget being looked for
	 * @return     the widget if found, null if not
	 */
	public MyDraggableComponent findWidget(String s){
		for (int i=0;i<allWidgets.length;i++){
				if (allWidgets[i] != null){
					if (allWidgets[i].getType().equals(s)){
						return allWidgets[i];
					}
			}
		}
		return null;
	}
	
	/**
	 * Serialization Method to store the dashboard layout. Stores an array of the onScreen widgets
	 * @throws Exception   thron if the the file cannot be written to
	 */
	public void storeWidgets() throws Exception{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/dashboard.dat"));
		
		String loc[][] = new String[3][3];
		for (int i =0; i < 3; i++){
			for (int j=0;j<3;j++){
				if (onScreen[i][j] != null){
					loc[i][j] = onScreen[i][j].getType();
				}
			}
		}
		
		out.writeObject(loc);
		out.close();
	}
	
	/**
	 * Serialization to load the Dashboard layout, reads the widgets to be displayed
	 */
	public void loadWidgets(){
		try {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/dashboard.dat"));
		
		String loc[][] = (String[][])in.readObject();
		for (int i =0; i < 3; i++){
			for (int j=0;j<3;j++){
				if (loc[i][j] != null){
					onScreen[i][j] = findWidget(loc[i][j]);
				}
			}
		}
		in.close();	
		} catch(Exception e){
			onScreen[0][0] = allWidgets[0];
			onScreen[1][0] = allWidgets[1];
			onScreen[2][0] = allWidgets[2];
			onScreen[0][1] = allWidgets[3];
			onScreen[1][1] = allWidgets[4];
			onScreen[2][1] = allWidgets[5];
			onScreen[0][2] = allWidgets[6];
			onScreen[1][2] = allWidgets[7];
			onScreen[2][2] = allWidgets[8];
			loadWidgetsOnScreen();
		}
		return;
	}
}