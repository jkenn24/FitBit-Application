package ca.uwo.csd.cs2212.team06;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.json.JSONObject;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Used to initialize the project.  Generates JSON objects and builds the jpanels of the window frame that will help to display the data.
 * @author Team06
 * @version 1.0
 * @category Class
 */

public class Project {
	//static vars for refresh
	static double dist, weeklyDistance;
	static int weeklySteps, weeklyFloors;
	static JSONObject stats, life, dGoals, wGoals;
	static JLabel lblsteps, lblfloors, lbldistance, lblcalories, lblelevation; //for daily totals
	static JLabel lbllifesteps, lbllifefloors, lbllifedistance, lbllifecalories; //for lifetime totals
	static JLabel lbldailystepgoal, lbldailyfloorgoal, lbldailydistancegoal, lbldailycaloriegoal; //for daily goals
	static JProgressBar dailyStepProgressBar, dailyFloorProgressBar, dailyDistanceProgressBar, dailyCalorieProgressBar; //for daily goals
	static JLabel lblweeklystepgoal, lblweeklyfloorgoal, lblweeklydistancegoal; //for weekly goals
	static JProgressBar weeklyStepProgressBar, weeklyFloorProgressBar, weeklyDistanceProgressBar; //for weekly goals
	static JPanel panel, panel_1, panel_5, panel_6; 
	JPanel summaryTab;
	
	private JFrame frame;

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project window = new Project();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the application.
	 */
	public Project() {
		initialize();
	}

	/**
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 622, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JTabbedPane mainWin = new JTabbedPane(JTabbedPane.TOP);
		mainWin.setBounds(6, 33, 610, 325);
		frame.getContentPane().add(mainWin);
		
		
		/***********************************************************    SUMMARY   **********************************************************/
		
		summaryTab = new JPanel();
		mainWin.addTab("Summary", null, summaryTab, null);
		summaryTab.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 10));
		summaryTab.add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JLabel lblToday_1 = new JLabel("Today:");
		lblToday_1.setBounds(0, 0, 150, 16);
		lblToday_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblToday_1);
		
		JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setBounds(10, 28, 61, 16);
		panel.add(lblSteps);
		
		JLabel lblFloors = new JLabel("Floors:");
		lblFloors.setBounds(10, 56, 61, 16);
		panel.add(lblFloors);
		
		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setBounds(10, 84, 61, 16);
		panel.add(lblDistance);
		
		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setBounds(10, 112, 61, 16);
		panel.add(lblCalories);
		
		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setBounds(10, 140, 61, 16);
		panel.add(lblElevation);
			
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		summaryTab.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);
		
		JLabel lblLifetime = new JLabel("Lifetime:");
		lblLifetime.setBounds(0, 0, 150, 16);
		lblLifetime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblLifetime);
		
		JLabel lblSteps_1 = new JLabel("Steps:");
		lblSteps_1.setBounds(10, 28, 61, 16);
		panel_1.add(lblSteps_1);
		
		JLabel lblFloors_1 = new JLabel("Floors:");
		lblFloors_1.setBounds(10, 56, 61, 16);
		panel_1.add(lblFloors_1);
		
		JLabel lblDistance_1 = new JLabel("Distance:");
		lblDistance_1.setBounds(10, 84, 61, 16);
		panel_1.add(lblDistance_1);
		
		JLabel lblCalories_1 = new JLabel("Calories:");
		lblCalories_1.setBounds(10, 112, 61, 16);
		panel_1.add(lblCalories_1);
		
		JPanel panel_2 = new JPanel();
		summaryTab.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblShelly = new JLabel("Shelly");
		lblShelly.setBounds(126, 5, 37, 16);
		panel_2.add(lblShelly);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 31, 277, 139);
		
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblShellypic = new JLabel("");
		lblShellypic.setBounds(6, 5, 265, 128);
		lblShellypic.setIcon(new ImageIcon(new ImageIcon("target/classes/snail.png").getImage().
				getScaledInstance(lblShellypic.getHeight(), lblShellypic.getHeight(), Image.SCALE_DEFAULT)));
		lblShellypic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panel_4.add(lblShellypic);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setMinimumSize(new Dimension(20, 16));
		lblLogo.setPreferredSize(new Dimension(10, 25));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		summaryTab.add(lblLogo, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMinimumSize(new Dimension(10, 50));
		panel_3.setPreferredSize(new Dimension(50, 80));
		summaryTab.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(null);
		
		JLabel lblLogo_1 = new JLabel("Logo");		
		lblLogo_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogo_1.setBounds(6, 6, 120, 68);
		lblLogo_1.setIcon(new ImageIcon(new ImageIcon("target/classes/FitbitLogo.png")
				.getImage().getScaledInstance(lblLogo_1.getWidth(), lblLogo_1.getWidth()/(2153/513), Image.SCALE_DEFAULT)));		
		panel_3.add(lblLogo_1);
		
		/***********************************************************GOALS**********************************************************/
		
		JPanel goals = new JPanel();
		mainWin.addTab("Goals", null, goals, null);
		goals.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBounds(6, 5, 286, 295);
		goals.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblDaily = new JLabel("Daily:");
		lblDaily.setBounds(116, 6, 61, 16);
		panel_6.add(lblDaily);
		
		JLabel lblSteps_2 = new JLabel("Steps:");
		lblSteps_2.setBounds(6, 36, 61, 16);
		panel_6.add(lblSteps_2);
		
		JLabel lblCalories_2 = new JLabel("Calories:");
		lblCalories_2.setBounds(6, 96, 61, 16);
		panel_6.add(lblCalories_2);
		
		JLabel lblDistance_2 = new JLabel("Distance:");
		lblDistance_2.setBounds(6, 156, 61, 16);
		panel_6.add(lblDistance_2);
		
		JLabel lblFloors_2 = new JLabel("Floors:");
		lblFloors_2.setBounds(6, 216, 61, 16);
		panel_6.add(lblFloors_2);		
		
		panel_5 = new JPanel();
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBounds(297, 5, 286, 295);
		goals.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblWeekly = new JLabel("Weekly:");
		lblWeekly.setBounds(122, 6, 61, 16);
		panel_5.add(lblWeekly);
		
		JLabel lblSteps_3 = new JLabel("Steps:");
		lblSteps_3.setBounds(6, 36, 61, 16);
		panel_5.add(lblSteps_3);
		
		JLabel lblDistance_3 = new JLabel("Distance:");
		lblDistance_3.setBounds(6, 156, 61, 16);
		panel_5.add(lblDistance_3);
		
		JLabel lblFloors_3 = new JLabel("Floors:");
		lblFloors_3.setBounds(6, 216, 61, 16);
		panel_5.add(lblFloors_3);
				
		JPanel badges = new JPanel();
		mainWin.addTab("Badges", null, badges, null);

		JPanel shelly = new JPanel();
		mainWin.addTab("Shelly", null, shelly, null);
		
		loadLabels();
		loadLabelsData();
		
		final JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Goals g = new Goals();
				Totals t = new Totals();
				try {
					Goals.storeGoals(g);
					Totals.storeTotals(t);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				loadLabelsData();
				System.out.println("Data refreshed");
				//mainWin.revalidate();
			}
		});
		btnRefresh.setBounds(513, 0, 103, 29);
		frame.getContentPane().add(btnRefresh);
	}

	/**
	 * Sets all variable data
	 */
	private static void loadLabels(){
		try {
			loadDailyGoals();
			loadWeeklyGoals();
			loadDailyTotals();
			loadLifetimeTotals();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadLabelsData(){
		try{
			loadDailyTotalsData();
			loadLifetimeTotalsData();
			loadDailyGoalsData();
			loadWeeklyGoalsData();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static void loadWeeklyGoalsData() throws Exception{
		int steps = Totals.loadTotals().getWTotals().getSteps();
		int floors = Totals.loadTotals().getWTotals().getFloors();
		int distance = (int)Totals.loadTotals().getWTotals().getDistance();
		
		lblweeklystepgoal.setText(Integer.toString(Goals.loadGoals().getWGoals().getSteps()));
		weeklyStepProgressBar.setMaximum(Integer.parseInt(lblweeklystepgoal.getText()));
		weeklyStepProgressBar.setValue(steps);
		
		lblweeklydistancegoal.setText(Double.toString(Goals.loadGoals().getWGoals().getDistance()));
		weeklyDistanceProgressBar.setMaximum((int)Double.parseDouble(lblweeklydistancegoal.getText()));
		weeklyDistanceProgressBar.setValue(distance);
		
		lblweeklyfloorgoal.setText(Integer.toString(Goals.loadGoals().getWGoals().getFloors()));
		weeklyFloorProgressBar.setMaximum(Integer.parseInt(lblweeklyfloorgoal.getText()));
		weeklyFloorProgressBar.setValue(floors);
	}

	private static void loadDailyGoalsData() throws Exception{
		lbldailystepgoal.setText(Integer.toString(Goals.loadGoals().getDGoals().getSteps()));
		dailyStepProgressBar.setMaximum(Integer.parseInt(lbldailystepgoal.getText()));
		dailyStepProgressBar.setValue(Totals.loadTotals().getDTotals().getSteps());
		
		lbldailycaloriegoal.setText(Integer.toString(Goals.loadGoals().getDGoals().getCalories()));
		dailyCalorieProgressBar.setMaximum(Integer.parseInt(lbldailycaloriegoal.getText()));
		dailyCalorieProgressBar.setValue(Totals.loadTotals().getDTotals().getCalories());
		
		lbldailydistancegoal.setText(Double.toString(Goals.loadGoals().getDGoals().getDistance()));
		dailyDistanceProgressBar.setMaximum((int) Double.parseDouble(lbldailydistancegoal.getText()));
		dailyDistanceProgressBar.setValue((int) Totals.loadTotals().getDTotals().getDistance());
		
		lbldailyfloorgoal.setText(Integer.toString(Goals.loadGoals().getDGoals().getFloors()));
		dailyFloorProgressBar.setMaximum(Integer.parseInt(lbldailyfloorgoal.getText()));
		dailyFloorProgressBar.setValue(Totals.loadTotals().getDTotals().getFloors());
	}

	private static void loadLifetimeTotalsData() throws Exception{
		lbllifesteps.setText(Integer.toString(Totals.loadTotals().getLTotals().getSteps()));
		lbllifefloors.setText(Integer.toString(Totals.loadTotals().getLTotals().getFloors()));
		lbllifedistance.setText(Double.toString(Totals.loadTotals().getLTotals().getDistance()));
		lbllifecalories.setText(Integer.toString(Totals.loadTotals().getLTotals().getCalories()));
	}
	
	private static void loadDailyTotalsData() throws Exception{
		lblsteps.setText(Integer.toString(Totals.loadTotals().getDTotals().getSteps()));	
		lblfloors.setText(Integer.toString(Totals.loadTotals().getDTotals().getFloors()));
		lbldistance.setText(Double.toString(Totals.loadTotals().getDTotals().getDistance()));	
		lblcalories.setText(Integer.toString(Totals.loadTotals().getDTotals().getCalories()));
		lblelevation.setText(Double.toString(Totals.loadTotals().getDTotals().getElevation()));
	}
	
	/*******************************************************************************************/

	private static void loadDailyTotals(){
		lblsteps = new JLabel();
		lblsteps.setHorizontalAlignment(SwingConstants.RIGHT);
		lblsteps.setBounds(83, 28, 61, 16);
		panel.add(lblsteps);
		
		lblfloors = new JLabel();
		lblfloors.setHorizontalAlignment(SwingConstants.RIGHT);
		lblfloors.setBounds(83, 56, 61, 16);
		panel.add(lblfloors);
		
		lbldistance = new JLabel();
		lbldistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldistance.setBounds(83, 84, 61, 16);
		panel.add(lbldistance);
		
		lblcalories = new JLabel();
		lblcalories.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcalories.setBounds(83, 112, 61, 16);
		panel.add(lblcalories);
		
		lblelevation = new JLabel();
		lblelevation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblelevation.setBounds(83, 140, 61, 16);
		panel.add(lblelevation);
	}

		/*******************************************************************************************/

	private static void loadLifetimeTotals() throws Exception{
		lbllifesteps = new JLabel();
		lbllifesteps.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifesteps.setBounds(83, 28, 61, 16);
		panel_1.add(lbllifesteps);
		
		lbllifefloors = new JLabel();
		lbllifefloors.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifefloors.setBounds(83, 56, 61, 16);
		panel_1.add(lbllifefloors);
		
		lbllifedistance = new JLabel();
		lbllifedistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifedistance.setBounds(83, 84, 61, 16);
		panel_1.add(lbllifedistance);
		
		lbllifecalories = new JLabel();
		lbllifecalories.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifecalories.setBounds(83, 112, 61, 16);
		panel_1.add(lbllifecalories);
	}
		
		/**********************************************************************************************/

	private static void loadDailyGoals() throws Exception{
		lbldailystepgoal = new JLabel();
		lbldailystepgoal.setBounds(116, 36, 61, 16);
		panel_6.add(lbldailystepgoal);
		dailyStepProgressBar = new JProgressBar();
		dailyStepProgressBar.setStringPainted(true);
		dailyStepProgressBar.setBounds(6, 64, 274, 20);
		panel_6.add(dailyStepProgressBar);
		
		lbldailycaloriegoal = new JLabel();
		lbldailycaloriegoal.setBounds(116, 96, 61, 16);
		panel_6.add(lbldailycaloriegoal);
		dailyCalorieProgressBar = new JProgressBar();
		dailyCalorieProgressBar.setStringPainted(true);
		dailyCalorieProgressBar.setBounds(6, 124, 274, 20);
		panel_6.add(dailyCalorieProgressBar);
		
		lbldailydistancegoal = new JLabel();
		lbldailydistancegoal.setBounds(116, 156, 61, 16);
		panel_6.add(lbldailydistancegoal);
		dailyDistanceProgressBar = new JProgressBar();
		dailyDistanceProgressBar.setStringPainted(true);
		dailyDistanceProgressBar.setBounds(6, 184, 274, 20);
		panel_6.add(dailyDistanceProgressBar);
		
		lbldailyfloorgoal = new JLabel();
		lbldailyfloorgoal.setBounds(116, 216, 61, 16);
		panel_6.add(lbldailyfloorgoal);
		dailyFloorProgressBar = new JProgressBar();
		dailyFloorProgressBar.setBounds(6, 244, 274, 20);
		dailyFloorProgressBar.setStringPainted(true);
		panel_6.add(dailyFloorProgressBar);
	}
			
		/***********************************************************************************************/

	private static void loadWeeklyGoals() throws Exception{
		
		lblweeklystepgoal = new JLabel();
		lblweeklystepgoal.setBounds(122, 36, 61, 16);
		panel_5.add(lblweeklystepgoal);
		weeklyStepProgressBar = new JProgressBar();
		weeklyStepProgressBar.setBounds(6, 64, 274, 20);
		weeklyStepProgressBar.setStringPainted(true);
		panel_5.add(weeklyStepProgressBar);
		
		lblweeklydistancegoal = new JLabel();
		lblweeklydistancegoal.setBounds(122, 156, 61, 16);
		panel_5.add(lblweeklydistancegoal);
		weeklyDistanceProgressBar = new JProgressBar();
		weeklyDistanceProgressBar.setBounds(6, 184, 274, 20);		
		weeklyDistanceProgressBar.setStringPainted(true);
		panel_5.add(weeklyDistanceProgressBar);
		
		lblweeklyfloorgoal = new JLabel();
		lblweeklyfloorgoal.setBounds(122, 216, 61, 16);
		panel_5.add(lblweeklyfloorgoal);
		weeklyFloorProgressBar = new JProgressBar();
		weeklyFloorProgressBar.setBounds(6, 244, 274, 20);
		weeklyFloorProgressBar.setStringPainted(true);
		panel_5.add(weeklyFloorProgressBar);
	}
}