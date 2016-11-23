package ca.uwo.csd.cs2212.team06.UI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import ca.uwo.csd.cs2212.team06.Totals;
import ca.uwo.csd.cs2212.team06.DailyGoals;
import ca.uwo.csd.cs2212.team06.Goals;
import ca.uwo.csd.cs2212.team06.History;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Tab for the Shelly component.  This tab will house an image of Shelly and display
 * the amount of experience the user has earned by fulfilling athletic tasks. It is also in charge of
 * the customization options for shelly and keeping track of when they're unlocked. 
 * @author Team06
 */

public class ShellyTab
{

	/**
	 * Class public/private attributes.
	 */
	private static int Level = 1;
	public static int MaxLevel = 50;
	private static double levelTotal;
	public static Image dimg;
	public static JLabel lblSnail;
	public static JLabel lblSnail_2;
	public static JLabel lblSnail_3;
	public static JProgressBar progressBar;
	public static JLabel lblLvlint;
	public static int autoTest = 1;
	private Date currentDate;
	private Date today = new Date();
	public static Timer timer;
	public static int timerTest = 0;
	private int testNumber = 0;
	shellyExperience shelly;

	private JFrame frame;
	private static JPanel ShellyPicPanel;
	public static JPanel Shelly;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JTextField txtShelly;
	
	/**
	 * Main method Launches the application
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ShellyTab window = new ShellyTab();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor which calls an Initialization of the frame
	 * @wbp.parser.entryPoint
	 */
	public ShellyTab()
	{
		initialize();
	}

	/**
	 * Initializes the contents of the frame
	 * {@inheritDoc}
	 * @wbp.parser.entryPoint
	 */
	private void initialize()
	{
		try 
		{
			shelly = shellyExperience.loadShelly();
		} catch (FileNotFoundException e){
			shellyExperience shell = new shellyExperience();
			try {
				shellyExperience.storeShelly(shell);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}catch (Exception e2) 
		{
			e2.printStackTrace();
		}finally{
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Color bc = new Color(209,255,255);//Set Shelly Background Colour
		JTabbedPane Tabs = new JTabbedPane(JTabbedPane.TOP);
		Tabs.setBounds(0, 25, 1054, 595);
		frame.getContentPane().add(Tabs);

		
		/**
		 * JPanel "Shelly" acts as the background panel for the entire tab. It loads
		 * in the tab at the top of the page and sets the back panel for everything in the UI
		 * to rest on. 
		 */

		Shelly = new JPanel();
		Tabs.addTab("New tab", null, Shelly, null);
		Shelly.setLayout(null);
		Shelly.setLayout(null);
		Shelly.setBackground(new Color(0, 0, 0, 150));

		/**
		 * JPanel "ShellyPicPanel" is that blue box where the snail avatar is visible. 
		 * Colour is set to a light blue by the bc variable, declared above at line 152, and
		 * the JPanel allows the snail to be placed on a top layer over the background
		 * and easily seen by the user.
		 */
		
		ShellyPicPanel = new JPanel();
		ShellyPicPanel.setBounds(49, 94, 364, 381);
		Shelly.add(ShellyPicPanel);
		ShellyPicPanel.setBackground(bc);
		ShellyPicPanel.setLayout(null);
		
		/**----------------------------------------------------------------------------------------
		 * Labels lblSnail, lblSnail_2, and lblSnail_3 are used together to present the image     |
		 * of Shelly. While each will be described in detail below, from the offset it is         |
		 * of importance to note that the snail image is comprised of three JLabels, with         |
		 * bouns set on each uch that the snail picture comes together, and customization         |
		 * parts can easily be swapped by the user changing JPanels.                              |
		 * ----------------------------------------------------------------------------------------
		 */

		/**
		 * lblSnail acts as the "body label" for Shelly. Upon initialization, experience.dat will be accessed to
		 * withdraw the last used body by the user. If this is the first run of the program, thn
		 * the default body will be set to black. The bounds are set and remain constant, and by clicking
		 * the body customization buttons, a pallette swap is performed by loading in a .png
		 */
		
		lblSnail = new JLabel("");
		lblSnail.setBounds(0, 51, 375, 324);
		ShellyPicPanel.add(lblSnail);
		lblSnail.setOpaque(false);
		lblSnail.setIgnoreRepaint(false);
		lblSnail.setFocusTraversalPolicyProvider(true);
		lblSnail.setFocusCycleRoot(true);
		lblSnail.setDoubleBuffered(true);
		lblSnail.setAutoscrolls(true);
		lblSnail.addComponentListener(new ComponentAdapter()
		{});
		if(shelly.isBorn() == true)
		{
			lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(shelly.getLastBody())).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));			
		}
		else
		{
			lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_black.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));	
			saveItem("/adult_body_black.png", shelly, "body");
		}		
		
		/**
		 * The txtShelly JTextField is the user editable text field in the top left corner of the
		 * shelly JFrame. The user can type in this field and hit enter to serialize the name of
		 * their custom snail avatar.
		 */
		
		txtShelly = new JTextField();
		txtShelly.setFont(new Font("SansSerif", Font.PLAIN, 20));
		if(shelly.isBorn() == false)
		{
			txtShelly.setText("Shelly");
			try 
			{
				shellyExperience.storeShelly(shelly);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		else
		{
			txtShelly.setText(shelly.getName());
		}
		txtShelly.setBounds(6, 6, 130, 35);
		ShellyPicPanel.add(txtShelly);
		txtShelly.setColumns(10);
		txtShelly.addActionListener(new ActionListener() 
		{
			/**
			 * The method actionPerformed(ActionEvent e) is an action listener that will serialize
			 * the user input in the JTextField upon user input of the "enter" key.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				shelly.setName(txtShelly.getText());
				try 
				{
					shellyExperience.storeShelly(shelly);
					makeScreenshot(ShellyPicPanel);
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}			
		});
		
		/**
		 * JLabel lblSnail_3 acts as the "hat layer" of the composite shelly picture. Much like the other 
		 * JLabels, the initial run of the program will attempt to located the last used hat by the user
		 * in experience.dat and, if one is not found, default to the blank image 'FFFFFF.png.'
		 */
		
		lblSnail_3 = new JLabel("");
		lblSnail_3.setBounds(120, 15, 240, 173);//X1,Y1,X2,Y2
		ShellyPicPanel.add(lblSnail_3);
		//If shelly is already born then its just setting the previous shelly selections the user wanted and resizing the hats correctly
		if(shelly.isBorn() == true)
		{
			int bound1 = shelly.getBound(1), bound2 = shelly.getBound(2), bound3 = shelly.getBound(3), bound4 = shelly.getBound(4);
			lblSnail_3.setBounds(bound1, bound2, bound3, bound4);//X1,Y1,X2,Y2
			lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(shelly.getLastHat())).getImage().getScaledInstance(lblSnail_3.getWidth() - 300, lblSnail_3.getHeight() - 300, Image.SCALE_DEFAULT)));
		}
		else
		{
			//Setting bounds and hats so there is no null pointer
			try 
			{
				shelly.setBounds(120, 15, 240, 173);
				shellyExperience.storeShelly(shelly);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/FFFFFF.png")).getImage().getScaledInstance(lblSnail_3.getWidth() - 300, lblSnail_3.getHeight() - 300, Image.SCALE_DEFAULT)));
			saveItem("/FFFFFF.png", shelly, "hat");
		}

		JLabel lblHitEnterTo = new JLabel("Hit enter to save");
		lblHitEnterTo.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblHitEnterTo.setBounds(16, 38, 82, 33);
		ShellyPicPanel.add(lblHitEnterTo);

		/**
		 * JLabel lblSnail_2 acts as the "shell layer" of the composite shelly picture. Much like the other 
		 * JLabels, the initial run of the program will attempt to located the last used shell by the user
		 * in experience.dat and, if one is not found, default to the green shell.
		 */

		lblSnail_2 = new JLabel("");
		lblSnail_2.setBounds(105, 128, 234, 190);
		ShellyPicPanel.add(lblSnail_2);
		if(shelly.isBorn() == true)
		{
			lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(shelly.getLastShell())).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_green.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
			saveItem("/adult_shell_green.png", shelly, "shell");
		}
		
		/**
		 * A label to show the users current experience level
		 */
		lblLvlint = new JLabel(Integer.toString(Level));     

		lblLvlint = new JLabel(Integer.toString(Level));
		lblLvlint.setBounds(537, 83, 46, 33);
		Shelly.add(lblLvlint);
		lblLvlint.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(449, 92, 36, 14);
		Shelly.add(lblLevel);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		/**
		 * Experience progress bar in the top of the window displays current experience
		 * held out of totoal experience needed to level up.
		 */
		
		progressBar = new JProgressBar();
		progressBar.setBounds(159, 30, 735, 45);
		Shelly.add(progressBar);
		progressBar.setVerifyInputWhenFocusTarget(false);
		progressBar.setOpaque(true);
		progressBar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		progressBar.setBackground(new Color(255, 102, 102));
		progressBar.setAutoscrolls(true);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		progressBar.setForeground(new Color(255, 0, 51));
		progressBar.setString("");
		progressBar.setMaximum(25);
		progressBar.setString(progressBar.getValue()+"/"+progressBar.getMaximum());
		
		
		/**------------------------------------------------------------------------------
		 * Lines 351-847 are responsible for the physical customization of shelly. Each
		 * JButton below will display in itself the item that can be placed on shelly
		 * and when pressed by the user, a branch to the action listener is performed
		 * where the appropriate label (lblSnail for body, lblSnail_2 for shell, and
		 * lblSnail_3 for hat) is altered with the user chosen image. In the case
		 * of the hats, it is often the case that the .png are not geometrically
		 * similar enough to simply overlay them in the way the body and shells are, so
		 * the bounds of the hat must be set upon the activation of the action listener
		 * just prior to the lblSnail_3 having its .png changed
		 * ------------------------------------------------------------------------------
		 */
		
		JButton btnNewButton = new JButton("No Hat");
		btnNewButton.addActionListener(new ActionListener() 
		{
			//The actionPerformed method is an action listener for the button (You can check which button is which by clicking on it in the swing editor, itll take you to the place in code) Serialize this shit!
			public void actionPerformed(ActionEvent e) 
			{
				//Code that reacts to button press
				lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/FFFFFF.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
				saveItem("/FFFFFF.png", shelly, "hat");
			}			
		});
		btnNewButton.setBounds(425, 155, 71, 51);
		Shelly.add(btnNewButton);
		
		button = new JButton("");
		if(shelly.getLifetimeExperience() >= 1375)
		{
			button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					lblSnail_3.setBounds(120, 15, 220, 153); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						//Serializing the bounds
						shelly.setBounds(120,15,220,153);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_pinwheel.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_pinwheel.png", shelly, "hat");
				}
			});
			button.setBounds(498, 155, 71, 51);
			Shelly.add(button);
			button.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_pinwheel.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button.setBounds(498, 155, 71, 51);
			Shelly.add(button);
			button.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_pinwheel_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}

	
		button_1 = new JButton("");
		if(shelly.getLifetimeExperience() >= 9625)
		{
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail_3.setBounds(120, 15, 240, 152); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(120,15,240,152);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_party.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_party.png", shelly, "hat");
				}
			});
			button_1.setBounds(574, 155, 71, 51);
			Shelly.add(button_1);
			button_1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_party.png")).getImage().getScaledInstance(button_1.getWidth(), button_1.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_1.setBounds(574, 155, 71, 51);
			Shelly.add(button_1);
			button_1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_party_grey.png")).getImage().getScaledInstance(button_1.getWidth(), button_1.getHeight(), Image.SCALE_DEFAULT)));
		}

		button_2 = new JButton("");
		if(shelly.getLifetimeExperience() >= 372750)
		{
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button
					lblSnail_3.setBounds(120, 15, 240, 153); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(120,15,240,153);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_wizard.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_wizard.png", shelly, "hat");
				}
			});
			button_2.setBounds(574, 213, 71, 51);
			Shelly.add(button_2);
			button_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_wizard.png")).getImage().getScaledInstance(button_2.getWidth(), button_2.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_2.setBounds(574, 213, 71, 51);
			Shelly.add(button_2);
			button_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_wizard_grey.png")).getImage().getScaledInstance(button_2.getWidth(), button_2.getHeight(), Image.SCALE_DEFAULT)));
		}

	
		button_3 = new JButton("");
		if(shelly.getLifetimeExperience() >= 236375)
		{
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		
					//Code that reacts to button press
					lblSnail_3.setBounds(132, 25, 230, 143); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(132,25,230,143);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_fruit.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_fruit.png", shelly, "hat");
				}
			});
			button_3.setBounds(498, 213, 71, 51);
			Shelly.add(button_3);
			button_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_fruit.png")).getImage().getScaledInstance(button_3.getWidth(), button_3.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_3.setBounds(498, 213, 71, 51);
			Shelly.add(button_3);
			button_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_fruit_grey.png")).getImage().getScaledInstance(button_3.getWidth(), button_3.getHeight(), Image.SCALE_DEFAULT)));
		}

		
		button_4 = new JButton("");
		if(shelly.getLifetimeExperience() >= 138125)
		{
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//Code that reacts to button press
					lblSnail_3.setBounds(140, 25, 230, 143); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(140,25,230,143);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_wings.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_wings.png", shelly, "hat");
				}
			});
			button_4.setBounds(425, 213, 71, 51);
			Shelly.add(button_4);
			button_4.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_wings.png")).getImage().getScaledInstance(button_4.getWidth(), button_4.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_4.setBounds(425, 213, 71, 51);
			Shelly.add(button_4);
			button_4.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_wings_grey.png")).getImage().getScaledInstance(button_4.getWidth(), button_4.getHeight(), Image.SCALE_DEFAULT)));
		}

		
		button_5 = new JButton("");
		if(shelly.getLifetimeExperience() >= 71750)
		{
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail_3.setBounds(115, 25, 230, 143); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(115,25,230,143);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_winter.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_winter.png", shelly, "hat");
				}
			});
			button_5.setBounds(798, 155, 71, 51);
			Shelly.add(button_5);
			button_5.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_winter.png")).getImage().getScaledInstance(button_5.getWidth(), button_5.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_5.setBounds(798, 155, 71, 51);
			Shelly.add(button_5);
			button_5.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_winter_grey.png")).getImage().getScaledInstance(button_5.getWidth(), button_5.getHeight(), Image.SCALE_DEFAULT)));
		}

		
		button_6 = new JButton("");
		if(shelly.getLifetimeExperience() >= 31000)
		{
			button_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {//Code that reacts to button press
					lblSnail_3.setBounds(120, 15, 220, 153); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(120,15,220,153);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_baseball.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));		
					saveItem("/hat_baseball.png", shelly, "hat");
				}
			});
			button_6.setBounds(722, 155, 71, 51);
			Shelly.add(button_6);
			button_6.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_baseball.png")).getImage().getScaledInstance(button_6.getWidth(), button_6.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_6.setBounds(722, 155, 71, 51);
			Shelly.add(button_6);
			button_6.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_baseball_grey.png")).getImage().getScaledInstance(button_6.getWidth(), button_6.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		button_7 = new JButton("");
		if(shelly.getLifetimeExperience() >= 9625)
		{
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail_3.setBounds(130, 55, 200, 113); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(130,55,200,113);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_beret.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));		
					saveItem("/hat_beret.png", shelly, "hat");
				}
			});
			button_7.setBounds(649, 155, 71, 51);
			Shelly.add(button_7);
			button_7.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_beret.png")).getImage().getScaledInstance(button_7.getWidth(), button_7.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_7.setBounds(649, 155, 71, 51);
			Shelly.add(button_7);
			button_7.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_beret_grey.png")).getImage().getScaledInstance(button_7.getWidth(), button_7.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		button_8 = new JButton("");
		if(shelly.getLifetimeExperience() >= 1073125)
		{
			button_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblSnail_3.setBounds(120, 15, 230, 178); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(120,15,230,178);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_crown.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_crown.png", shelly, "hat");
				}
			});
			button_8.setBounds(798, 213, 71, 51);
			Shelly.add(button_8);
			button_8.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_crown.png")).getImage().getScaledInstance(button_8.getWidth(), button_8.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_8.setBounds(798, 213, 71, 51);
			Shelly.add(button_8);
			button_8.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_crown_grey.png")).getImage().getScaledInstance(button_8.getWidth(), button_8.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		button_9 = new JButton("");
		if(shelly.getLifetimeExperience() >= 784875)
		{
			button_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail_3.setBounds(132, 60, 190, 103); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(132,60,190,103);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_tophat.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_tophat.png", shelly, "hat");
				}
			});
			button_9.setBounds(722, 213, 71, 51);
			Shelly.add(button_9);
			button_9.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_tophat.png")).getImage().getScaledInstance(button_9.getWidth(), button_9.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_9.setBounds(722, 213, 71, 51);
			Shelly.add(button_9);
			button_9.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_tophat_grey.png")).getImage().getScaledInstance(button_9.getWidth(), button_9.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		button_10 = new JButton("");
		if(shelly.getLifetimeExperience() >= 553500)
		{
			button_10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail_3.setBounds(120, 135, 200, 123); //Change the bounds of the hat because it doesnt look right as is
					try 
					{
						shelly.setBounds(120,135,200,123);
						shellyExperience.storeShelly(shelly);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					lblSnail_3.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_flowercrown.png")).getImage().getScaledInstance(lblSnail_3.getWidth(), lblSnail_3.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/hat_flowercrown.png", shelly, "hat");
				}
			});
			button_10.setBounds(649, 213, 71, 51);
			Shelly.add(button_10);
			button_10.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_flowercrown.png")).getImage().getScaledInstance(button_10.getWidth(), button_10.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_10.setBounds(649, 213, 71, 51);
			Shelly.add(button_10);
			button_10.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hat_flowercrown_grey.png")).getImage().getScaledInstance(button_10.getWidth(), button_10.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		/**
		 * Shelly's bodies are found below
		 */
		
		JButton button_11 = new JButton("");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Code that reacts to button press
				lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_black.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));
				saveItem("/adult_body_black.png", shelly, "body");
			}
		});
		button_11.setBounds(425, 311, 71, 51);
		Shelly.add(button_11);
		button_11.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_black.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		
		JButton button_12 = new JButton("");
		if(shelly.getLifetimeExperience() >= 9625)
		{
			button_12.addActionListener(new ActionListener() {
				//Code that reacts to button press
				public void actionPerformed(ActionEvent e) {
					lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_blue.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_body_blue.png", shelly, "body");
				}
			});
			button_12.setBounds(498, 311, 71, 51);
			Shelly.add(button_12);
			button_12.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_blue.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_12.setBounds(498, 311, 71, 51);
			Shelly.add(button_12);
			button_12.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		JButton button_13 = new JButton("");
		if(shelly.getLifetimeExperience() >= 71750)
		{
			button_13.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_yellow.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_body_yellow.png", shelly, "body");
				}
			});
			button_13.setBounds(574, 311, 71, 51);
			Shelly.add(button_13);
			button_13.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_yellow.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_13.setBounds(574, 311, 71, 51);
			Shelly.add(button_13);
			button_13.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		JButton button_14 = new JButton("");
		if(shelly.getLifetimeExperience() >= 236375)
		{
			button_14.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_white.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_body_white.png", shelly, "body");
				}
			});
			button_14.setBounds(649, 311, 71, 51);
			Shelly.add(button_14);
			button_14.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_white.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_14.setBounds(649, 311, 71, 51);
			Shelly.add(button_14);
			button_14.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		JButton button_15 = new JButton("");
		if(shelly.getLifetimeExperience() >= 553500)
		{
			button_15.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to button press
					lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_pink.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_body_pink.png", shelly, "body");
				}
			});
			button_15.setBounds(722, 311, 71, 51);
			Shelly.add(button_15);
			button_15.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_pink.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_15.setBounds(722, 311, 71, 51);
			Shelly.add(button_15);
			button_15.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		JButton button_16 = new JButton("");
		if(shelly.getLifetimeExperience() >= 1073125)
		{
			button_16.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblSnail.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_gold.png")).getImage().getScaledInstance(lblSnail.getWidth(), lblSnail.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_body_gold.png", shelly, "body");
				}
			});
			button_16.setBounds(798, 311, 71, 51);
			Shelly.add(button_16);
			button_16.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_gold.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_16.setBounds(798, 311, 71, 51);
			Shelly.add(button_16);
			button_16.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_body_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		/**
		 * Shelly and her shell colours and permutations can be found below
		 */
		
		JButton button_17 = new JButton("");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Code that reacts to the button press
				lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_green.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
				saveItem("/adult_shell_green.png", shelly, "shell");
			}
		});
		button_17.setBounds(425, 424, 71, 51);
		Shelly.add(button_17);
		button_17.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_green.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		
		JButton button_18 = new JButton("");
		if(shelly.getLifetimeExperience() >= 9625)
		{
			button_18.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to the button press
					lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_red.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_shell_red.png", shelly, "shell");
				}
			});
			button_18.setBounds(498, 424, 71, 51);
			Shelly.add(button_18);
			button_18.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_red.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_18.setBounds(498, 424, 71, 51);
			Shelly.add(button_18);
			button_18.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		JButton button_19 = new JButton("");
		if(shelly.getLifetimeExperience() >= 71750)
		{
			button_19.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to the button press
					lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_yellow.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_shell_yellow.png", shelly, "shell");
				}
			});
			button_19.setBounds(574, 424, 71, 51);
			Shelly.add(button_19);
			button_19.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_yellow.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_19.setBounds(574, 424, 71, 51);
			Shelly.add(button_19);
			button_19.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}

		
		JButton button_20 = new JButton("");
		if(shelly.getLifetimeExperience() >= 236375)
		{
			button_20.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to the button press
					lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_dots.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_shell_dots.png", shelly, "shell");
				}
			});
			button_20.setBounds(649, 424, 71, 51);
			Shelly.add(button_20);
			button_20.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_dots.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_20.setBounds(649, 424, 71, 51);
			Shelly.add(button_20);
			button_20.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_dots_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}

		
		JButton button_21 = new JButton("");
		if(shelly.getLifetimeExperience() >= 553500)
		{
			button_21.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to the button press
					lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_flames.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_shell_flames.png", shelly, "shell");
				}
			});
			button_21.setBounds(722, 424, 71, 51);
			Shelly.add(button_21);
			button_21.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_flames.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_21.setBounds(722, 424, 71, 51);
			Shelly.add(button_21);
			button_21.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_flames_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}

		
		JButton button_22 = new JButton("");
		if(shelly.getLifetimeExperience() >= 1073125)
		{
			button_22.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Code that reacts to the button press
					lblSnail_2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_sparkle.png")).getImage().getScaledInstance(lblSnail_2.getWidth(), lblSnail_2.getHeight(), Image.SCALE_DEFAULT)));
					saveItem("/adult_shell_sparkle.png", shelly, "shell");
				}
			});
			button_22.setBounds(798, 424, 71, 51);
			Shelly.add(button_22);
			button_22.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_sparkle.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		else
		{
			button_22.setBounds(798, 424, 71, 51);
			Shelly.add(button_22);
			button_22.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/adult_shell_sparkle_grey.png")).getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)));
		}
		
		/**
		 *JLabels to label what each row of cosmetics are, if you couldn't tell by looking at them. 
		 */
		
		JLabel lblColour = new JLabel("Bodies");
		lblColour.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblColour.setBounds(624, 283, 96, 16);
		Shelly.add(lblColour);
		
		JLabel lblShells = new JLabel("Shells");
		lblShells.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblShells.setBounds(624, 396, 126, 16);
		Shelly.add(lblShells);
		
		JLabel lblHats = new JLabel("Hats");
		lblHats.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblHats.setBounds(627, 127, 61, 16);
		Shelly.add(lblHats);
		Shelly.setOpaque(false);
		
		/**
		 * Method from shellyExperience is called here to display the users current experience, and the date
		 * is kept in line with the other tabs. Date and time are based off of system clock, so they'll only 
		 * be correct if you are! 
		 */
		shellyExperience();
		currentDate = new Date();
		}
	}
	
	/**
	 * A method to check if shelly has been previously "born." 
	 * It checks this to see whether to load the object and add exp, or to make a new object and start shelly from level 1
	 * 
	 * @param shelly is a serializable shelly data object to save to.
	 */
	public void birthShelly(shellyExperience shelly)
	{
		if(shelly.isBorn() == false)
		{
			shelly.setBorn(true);
			shelly.setLifetimeExperience(0);
			shelly.setCurrentExperience(0);
			shelly.setLastDate(today);
		}
	}
	
	/**
	 * the saveItem method is responsible for storing the user chosen customizations as strings
	 * to be loaded in from the experience.dat file. The system message "save successful" will
	 * display upon each chosen item, accurately updating and reflecting the serialized items.
	 * @param item is the physical item
	 * @param shelly is the snail object that has this all serialized to it
	 * @param itemType is either a hat, body, or shell
	 */
	public void saveItem(String item, shellyExperience shelly, String itemType)
	{
		
		if(itemType.equals("hat"))
		{
			shelly.setLastHat(item);
		}
		else if(itemType.equals("body"))
		{
			shelly.setLastBody(item);
		}
		else if(itemType.equals("shell"))
		{
			shelly.setLastShell(item);
		}

		try 
		{
			shellyExperience.storeShelly(shelly);
			makeScreenshot(ShellyPicPanel);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
	
	/**
	 * Gives shelly the experience earned by user for the day
	 */
	@SuppressWarnings("deprecation")
	public void shellyExperience() 
	{
		Date tempDate = new Date();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		String tempD = "2016-03-18";
		
		boolean onCurrentDay = false;
		//Exp earned by person's activity
		//Currently you're getting the date from the shellyExperience class, this might change in the future
		//Getting canned step and distance data for shelly
		try 
		{
			//Load experience.dat to get saved variables
			//If shelly has never been born, make new shelly
			birthShelly(shelly);
			//This will give any lifetime experience earned by shelly and display it
			giveExperience(shelly, shelly.getLifetimeExperience());
			//Last day shelly has been accessed
			currentDate = shelly.getLastDate();
			
			while(onCurrentDay == false)
			{
				if(currentDate.getDate() == tempDate.getDate())
				{
					DailyGoals dGoals = Goals.loadGoals().getDGoals();
					int steps = Totals.loadTotals().getDTotals().getSteps();
					int calories = Totals.loadTotals().getDTotals().getCalories();
					double distance = Totals.loadTotals().getDTotals().getDistance();
					int experienceEarned = (int)(steps*(distance*(Math.sqrt(distance)/3)))/10;
					if(distance > dGoals.getDistance()){experienceEarned = experienceEarned * (int)(experienceEarned*1.3);}
					if(calories > dGoals.getCalories()){experienceEarned = experienceEarned * (int)(experienceEarned*1.3);}
					if(steps > dGoals.getSteps()){experienceEarned = experienceEarned * (int)(experienceEarned*1.3);}
					if(experienceEarned > shelly.getCurrentExperience())
					{
						shelly.setCurrentExperience(experienceEarned);
						shelly.setLastDate(currentDate);
						try
						{
							shellyExperience.storeShelly(shelly);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					onCurrentDay = true;
				}
				else if(currentDate.before(tempDate))
				{
					//Format the date correctly to retrieve the previous days steps and distances from the json
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					//Calculate the experience to be earned
					DailyGoals dGoals = Goals.loadGoals().getDGoals();
					int steps = History.loadHistory().getsHis().getStepData(dateFormat.format(currentDate));
					int calories = Totals.loadTotals().getDTotals().getCalories();
					double distance = History.loadHistory().getdHis().getDistanceData((dateFormat.format(currentDate)));
					int experienceEarned = (int)(steps*(distance*(Math.sqrt(distance)/3)))/10;
					if(distance > dGoals.getDistance()){experienceEarned = experienceEarned * (int)(experienceEarned*1.3);}
					if(calories > dGoals.getCalories()){experienceEarned = experienceEarned * (int)(experienceEarned*1.3);}
					if(steps > dGoals.getSteps()){experienceEarned = experienceEarned * (int)(experienceEarned*1.3);}
					//Give shelly the experience earned from the previous days that were not logged by the program
					giveExperience(shelly, experienceEarned);
					shelly.setLifetimeExperience(experienceEarned);
					currentDate.setDate(currentDate.getDate()+1);
					try
					{
						shellyExperience.storeShelly(shelly);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * Method used to calculated "experience overflow," takes an int and shellyExperience object. In
	 * the event the user earns an amount of experience greater than needed to level up, we dont want
	 * all that extra effort wasted. The experience overflows into the next experience level
	 * and gives the user the exp they earned correctly. An overflow integer will keep
	 * track of the experience the user has over the limit, and a boolean flag will
	 * queue the method in if there is still experience to be distributed into the
	 * next experience tier. 
	 * @param shelly is the serializable shelly object tracking user exp
	 * @param experienceEarned represents the amount the user earned over the exp threshold
	 */
	public void giveExperience(shellyExperience shelly, int experienceEarned)
	{
		//The amount of extra shelly earned by user when he hits a level cap
		int experienceOverflow = 0;
		//The amount of experience left to be earned on level
		int experienceLeftOnBar = 0;
		//If there is any experience that the user has earned but hasn't been given
		//to user, this will be false. Else if all used = true
		boolean expFinished = false;
		
		//While experience has not been totally given to user
		while(expFinished == false)
		{
			//If the experience earned is less then the level amount, give user experience
			//and stop while loop
			if((progressBar.getValue() + experienceEarned) <= progressBar.getMaximum())
			{
				progressBar.setValue(progressBar.getValue() + experienceEarned);
				updateProgressBar(progressBar.getValue() + experienceEarned);	
				try
				{
					shellyExperience.storeShelly(shelly);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				expFinished = true;
			}
			else
			{
				//Calculate experience left on bar
				experienceLeftOnBar = progressBar.getMaximum() - progressBar.getValue();
				//Calculate experience amount left after levelup
				experienceOverflow = experienceEarned - experienceLeftOnBar;
				//Give the user the experience amount
				progressBar.setValue(progressBar.getValue() + experienceEarned);
				//Take away the experience given to the user so you don't give him double the amount
				//next time the while loop loops
				experienceEarned = experienceEarned - progressBar.getValue();
				//Level the user
				checkLvlUp(0);
				//Check to see if you've used all of the experience, if yes then stop loop
				if(experienceOverflow <= 0)
				{
					updateProgressBar(experienceEarned);
					try
					{
						shellyExperience.storeShelly(shelly);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					expFinished = true;
				}
			}
		} //end of while
	}

	/**
	 * Simply updates the progress bar to show the correct amount of 
	 * current/total experience.
	 * @int exp is the exp to display (the value to set the progressbar string to)
	 */
	public void updateProgressBar(int exp)
	{
		progressBar.setString(progressBar.getValue()+"/"+progressBar.getMaximum());
	}

	/**
	 * 'Levels up' Shelly by incrementing her level and recalculating the progressbar maximum
	 * @param i the overflowed experience to be added to the progress bad
	 */
	public static void lvlUp(int i)
	{
		Level+=1;
		//Amount needed for each level
		levelTotal = (Level*((double)Level/4))*100;//Put this somewhere else
		lblLvlint.setText(Integer.toString(Level));
		progressBar.setValue(i);
		//Level Bar Total
		progressBar.setMaximum((int)levelTotal);
	}

	/**
	 * Checks the current level of Shelly
	 * @param i  the current level of Shelly
	 */
	@SuppressWarnings("deprecation")
	public static void checkLvlUp(int i)
	{
		if(Level<50)
		{
			//Updates the string inside the progress bar to show new experience needed
			//progressBar.setString(progressBar.getValue()+"/"+progressBar.getMaximum());

			//If earned exp == to needed experience, level up
			if(progressBar.getValue()==progressBar.getMaximum())
			{
				lvlUp(i);
				progressBar.setString(progressBar.getValue()+"/"+progressBar.getMaximum());
			}
		}
		else
		{
			progressBar.setValue(progressBar.getMaximum());
			progressBar.setString("MAX LEVEL");
		}
	}

	/**
	 * Sets the level of Shelly to the integer passed in
	 * @param i the level to be set
	 */
	public static void setLvl(int i)
	{
		Level = i;
	}
	
	/**
	 * Gets the level of shelly
	 * 
	 * @return an integer level value
	 */
	public int getLevel()
	{
		return Level;
	}
	
	/**
	 * Use this method to create a screenshot of the JPanel object argPanel.
	 *
	 * Author(s):
	 *   Dejan Lekic
	 * License: 
	 *   Public Domain
	 *
	 * @param argPanel JPanel you want to make screenshot of.
	 */
	private static void makeScreenshot(JPanel argPanel) {
	    Rectangle rec = argPanel.getBounds();
	    BufferedImage bufferedImage = new BufferedImage(rec.width, rec.height,
	            BufferedImage.TYPE_INT_ARGB);
	    argPanel.paint(bufferedImage.getGraphics());
	    DashboardTab.updateShelly(bufferedImage);

	} // makeScreenshot method
	
	/**
	 * Static method getShellyPic calls an instance of makeScreenshot with the private variable ShellyPicPanel
	 */
	public static void getShellyPic(){
		makeScreenshot(ShellyPicPanel);
	}
}

