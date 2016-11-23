package ca.uwo.csd.cs2212.team06.UI;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.*;

public class MyDraggableComponent extends JPanel implements Serializable {
	private static final long serialVersionUID = -4768658831131349892L;
	private int height;
	private int width;
	private int containerWidth;
	private int containerHeight;
	private int deltaX;
	private int deltaY;
	private String type;
	private DashboardLayout dblay;

	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;

	/**
	 * Constructor for a Draggable component
	 * 
	 * @param Jwidth    int value of the width of the component
	 * @param Jheight   int value of the height of the component
	 * @param dash      DashboardLayout object of the Layout that the Component will be a member of (to access the bounds of the parent)
	 * @param typ       String of the type of the Component so it can be searched for in an array of Widgets
	 */
	public MyDraggableComponent(int Jwidth, int Jheight, DashboardLayout dash, String typ){
		super.setSize(Jwidth, Jheight); 
		super.setOpaque(false);
		containerWidth = dash.getWidth();
		containerHeight = dash.getHeight();
		setBorder(new LineBorder(Color.BLACK, 1));
		setBackground(Color.WHITE);
		this.setBounds(0, 0, Jwidth, Jheight);
		setOpaque(false);
		height = Jheight;
		width = Jwidth;
		type = typ;
		dblay = dash;
			addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) { }

				public void mousePressed(MouseEvent e) {
					screenX = e.getXOnScreen();
					screenY = e.getYOnScreen();

					myX = getX();
					myY = getY();
				}

				public void mouseReleased(MouseEvent e){
					int setX = myX + deltaX;
					int setY = myY + deltaY;

					if (setX < 0)
						setX = 0;
					if (setY < 0)
						setY = 0;
					if (setX > containerWidth - width)
						setX = containerWidth - width;
					if (setY > containerHeight - height)
						setY = containerHeight - height;
					try {
						dblay.moveWidget(type, (getX()+e.getX()), (getY()+e.getY()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				public void mouseEntered(MouseEvent e) { }

				public void mouseExited(MouseEvent e) { }

			});
			addMouseMotionListener(new MouseMotionListener() {

				public void mouseDragged(MouseEvent e) {
					deltaX = e.getXOnScreen() - screenX;
					deltaY = e.getYOnScreen() - screenY;

					setLocation(deltaX + myX, deltaY + myY);
				}

				public void mouseMoved(MouseEvent e) {

				}
			});
		}
	/**
	 * Returns the type of of the component so it can be looked up in the array of widgets
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * Sets the string type of the component so it can be looked up in the array of widgets
	 * 
	 * @param type    String to be set
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * Returns the height of the component as an int
	 */
	public int getHeight() {
		return this.height;
	}
	/**
	 * Returns the width of the component as an int
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Load method for a Widget
	 * 
	 * @return drag        MyDraggableComponent object from memory
	 * @exception          thrown if the file cannot be read
	 */
	public static MyDraggableComponent loadHistory() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("target/classes/history.dat"));
		MyDraggableComponent drag = (MyDraggableComponent) in.readObject();
		in.close();
		return drag;
	}
	/**
	 * Store method for a Widget and stores the user history
	 * 
	 * @param drag           MyDraggableComponent object of a widget to store
	 * @throws Exception     thrown if the file cannot be written to
	 */
	public static void storeHistory(MyDraggableComponent drag) throws Exception{
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream("target/classes/history.dat"));
		//out = new ObjectOutputStream(new FileOutputStream("src/main/resources/history.dat"));
		out.writeObject(drag);
		out.close();
	}
}