package ca.uwo.csd.cs2212.team06.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import ca.uwo.csd.cs2212.team06.*;
import java.awt.Font;
/**
 * Class creates dashboard tab on the window
 * @author Team06
 * @version 1.0
 * @category Class
 */
public class DashboardTab {
	//Variables
	private JFrame frame;
	private static MyDraggableComponent DailyPanel;
	private static MyDraggableComponent LifetimePanel;
	DashboardLayout summaryTab;
	private SelectDate calendar;
	private static JLabel shelly;
	private static MyDraggableComponent shellyPic;
	private static JLabel icnShelly;
	private JPanel back;
	private static Boolean custom = true;
	
	//static vars for refresh
	private static JLabel lblsteps, lblfloors, lbldistance, lblcalories, lblelevation, dataDate; //for daily totals
	private static JLabel lbllifesteps, lbllifefloors, lbllifedistance, lbllifecalories; //for lifetime totals
	private static JLabel lbldailystepgoal, lbldailyfloorgoal, lbldailydistancegoal, lbldailycaloriegoal, goalDate; //for daily goals
	private static JProgressBar dailyStepProgressBar, dailyFloorProgressBar, dailyDistanceProgressBar, dailyCalorieProgressBar; //for daily goals
	private static JLabel lblweeklystepgoal, lblweeklyfloorgoal, lblweeklydistancegoal; //for weekly goals
	private static JProgressBar weeklyStepProgressBar, weeklyFloorProgressBar, weeklyDistanceProgressBar; //for weekly goals
	private static JLabel bestStepsDay, bestDistanceDay, bestFloorsDay, bestStepsAmount, bestDistanceAmount, bestFloorsAmount;
	private static JLabel sedMinsData, vActMinutesData, lActMinutesData, fActMinutesData, actDate;
	private static JLabel sedLMinsData, vActLMinutesData, lActLMinutesData, fActLMinutesData;
	private static MyDraggableComponent weeklyGoals, dailyGoals, activeDailyMinutes;
	
	/**
	 * Launch the application.
	 *@param args   String[] of arguements
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardTab window = new DashboardTab();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DashboardTab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**************************************** Initialize the frame and panel ***********************************/
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane Tabs = new JTabbedPane(JTabbedPane.TOP);
		Tabs.setBounds(0, 25, 1054, 580);
		frame.getContentPane().add(Tabs);
		
		summaryTab = new DashboardLayout(1030,552);
		Tabs.addTab("Summary", null, new AlphaContainer(summaryTab), null);
		summaryTab.setLayout(null);
		summaryTab.setOpaque(false);
		summaryTab.setBackground(new Color(0,0,0,0));
		
		/**************************************** Creating the Add/Remove window ***********************************/
		
		String s = "Add/ \nRemove \nWidgets";
		JButton addRemove = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
		addRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				final JFrame selectWidgets = new JFrame("Add/ Remove Widgets");
				selectWidgets.setBounds(400, 150, 400, 400);
				selectWidgets.setVisible(true);
				selectWidgets.setLayout(null);
				final JButton applyChanges = new JButton("Apply Changes");
				applyChanges.setBounds(260, 325, 125, 30);
				selectWidgets.add(applyChanges);
				JLabel mssg = new JLabel("Select up to 9 dasboard widgets to be displayed.");
				selectWidgets.add(mssg);
				mssg.setBounds(20, 10, 400, 20);
				
				final JCheckBox calendar = new JCheckBox("Calendar");
				calendar.setBounds(20, 40, 200, 20);
				selectWidgets.add(calendar);
				final JCheckBox dailyGoals = new JCheckBox("Daily Goals");
				dailyGoals.setBounds(20, 70, 200, 20);
				selectWidgets.add(dailyGoals);
				final JCheckBox weeklyGoals = new JCheckBox("Weekly Goals");
				weeklyGoals.setBounds(20, 100, 200, 20);
				selectWidgets.add(weeklyGoals);
				final JCheckBox lifeTotals = new JCheckBox("Lifetime Totals");
				lifeTotals.setBounds(20, 130, 200, 20);
				selectWidgets.add(lifeTotals);
				final JCheckBox dailyTotals = new JCheckBox("Daily Totals");
				dailyTotals.setBounds(20, 160, 200, 20);
				selectWidgets.add(dailyTotals);
				final JCheckBox fitbitLogo = new JCheckBox("Fitbit Logo");
				fitbitLogo.setBounds(20, 190, 200, 20);
				selectWidgets.add(fitbitLogo);
				final JCheckBox shelly = new JCheckBox("Shelly Avatar");
				shelly.setBounds(20, 220, 200, 20);
				selectWidgets.add(shelly);
				final JCheckBox lifeActiveMins = new JCheckBox("Lifetime Active Minutes");
				lifeActiveMins.setBounds(20, 250, 200, 20);
				selectWidgets.add(lifeActiveMins);
				final JCheckBox dailyActiveMins = new JCheckBox("Daily Active Minutes");
				dailyActiveMins.setBounds(20, 280, 200, 20);
				selectWidgets.add(dailyActiveMins);
				final JCheckBox bestDays = new JCheckBox("Best Days");
				bestDays.setBounds(20, 310, 200, 20);
				selectWidgets.add(bestDays);
				
				//JPanel background = new JPanel();
				//background.setBounds(0, 0, 400, 400);
				JLabel backPic = new JLabel();
				backPic.setBounds(0,0,400,400);
				backPic.setIcon(new ImageIcon(new ImageIcon("target/classes/blue sky 3.jpg")
						.getImage().getScaledInstance(1200, (int)(1200/1.6), Image.SCALE_SMOOTH)));
				backPic.setHorizontalAlignment(SwingConstants.CENTER);
				backPic.setVerticalAlignment(SwingConstants.CENTER);
				selectWidgets.add(backPic);
				//background.add(backPic);
				//selectWidgets.add(background);
				
				applyChanges.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						summaryTab.clearScreen();
						int numOnScreen = 0;
						
						if (calendar.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Calendar"));
							numOnScreen++;
						}
						if (dailyGoals.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Daily Goals"));
							numOnScreen++;
						}
						if (weeklyGoals.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Weekly Goals"));
							numOnScreen++;
						}
						if (lifeTotals.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Lifetime"));
							numOnScreen++;
						}
						if (dailyTotals.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Daily"));
							numOnScreen++;
						}
						if (fitbitLogo.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("FitBit"));
							numOnScreen++;
						}
						if (shelly.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Snail"));
							numOnScreen++;
						}
						if (lifeActiveMins.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("ActiveLifeMins"));
							numOnScreen++;
						}
						if (dailyActiveMins.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("ActiveDailyMins"));
							numOnScreen++;
						}
						if (bestDays.isSelected() && numOnScreen < 9){
							summaryTab.addWidget(summaryTab.findWidget("Best Days"));
							numOnScreen++;
						}
						selectWidgets.setVisible(false);
						try {
							summaryTab.storeWidgets();
							summaryTab.remove(back);
							summaryTab.add(back);
							custom = true;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
            }
		});
		
		addRemove.setBounds(915, 150, 100, 300);
		summaryTab.add(addRemove);
		
		/**************************************** Creating Widgets ***********************************/
		
		DailyPanel = new MyDraggableComponent(300,184,summaryTab, "Daily");
		summaryTab.newWidget(DailyPanel);
		DailyPanel.setLayout(null);
		DailyPanel.setOpaque(false);
		
		LifetimePanel = new MyDraggableComponent(300,184,summaryTab, "Lifetime");
		summaryTab.newWidget(LifetimePanel);
		LifetimePanel.setLayout(null);
		LifetimePanel.setOpaque(false);
		
		shellyPic = new MyDraggableComponent(300, 184, summaryTab, "Snail");
		shelly = new JLabel();
		shelly.setBounds(0, 0, 300, 184);
		shellyPic.add(shelly);
		shelly.setOpaque(false);
		summaryTab.newWidget(shellyPic);
		shellyPic.setOpaque(false);
		
		weeklyGoals = new MyDraggableComponent(300, 184, summaryTab, "Weekly Goals");
		summaryTab.newWidget(weeklyGoals);
		weeklyGoals.setLayout(null);
		weeklyGoals.setOpaque(false);
		
		dailyGoals = new MyDraggableComponent(300, 184, summaryTab, "Daily Goals");
		summaryTab.newWidget(dailyGoals);
		dailyGoals.setLayout(null);
		dailyGoals.setOpaque(false);
		
		MyDraggableComponent calWidget = new MyDraggableComponent(300,184,summaryTab, "Calendar");
		calendar = new SelectDate();
		calendar.setPreferredSize(new Dimension(290,175));
		calendar.setBounds(0, 0, 300, 184);
		calendar.setOpaque(false);
		calWidget.add(calendar);
		summaryTab.newWidget(calWidget);
		calWidget.setOpaque(false);

		activeDailyMinutes = new MyDraggableComponent(300,184,summaryTab, "ActiveDailyMins");
		activeDailyMinutes.setBounds(700, 300, 300, 184);
		summaryTab.newWidget(activeDailyMinutes);
		activeDailyMinutes.setLayout(null);
		activeDailyMinutes.setOpaque(false);
		
		MyDraggableComponent lifeDailyMinutes = new MyDraggableComponent(300,184,summaryTab, "ActiveLifeMins");
		lifeDailyMinutes.setBounds(5, 300, 300, 184);
		summaryTab.newWidget(lifeDailyMinutes);
		lifeDailyMinutes.setLayout(null);
		lifeDailyMinutes.setOpaque(false);
		
		MyDraggableComponent bestDays = new MyDraggableComponent(300,184,summaryTab, "Best Days");
		bestDays.setBounds(350, 300, 300, 184);
		summaryTab.newWidget(bestDays);
		bestDays.setLayout(null);
		bestDays.setOpaque(false);
		
		MyDraggableComponent logo = new MyDraggableComponent(300, 184, summaryTab, "FitBit");
		logo.setLayout(null);
		summaryTab.newWidget(logo);
		JLabel icnLogo = new JLabel("");
		icnLogo.setBounds(0, 0, 280, 75);
		double height = icnLogo.getWidth()/((double)623/(double)222);
		icnLogo.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/FitbitLogo_Reversed.png")).getImage().getScaledInstance(icnLogo.getWidth(), (int)height, Image.SCALE_DEFAULT)));
		icnLogo.setLocation(10, 55);
		logo.add(icnLogo);
		logo.setOpaque(false);
		
		/****************************Load the screen*********************/
		summaryTab.loadWidgets();
		summaryTab.loadWidgetsOnScreen();
		
		/********** White translucent background**********/
		back = new JPanel();
		back.setBounds(0, 0, 900, 552);
		back.setBackground(new Color(255,255,255,150));
		summaryTab.add(back);
		
		/**************************************** Setting the Labels in the widgets ***********************************/
		
		JLabel lblToday_1 = new JLabel("Daily Summary for:");
		lblToday_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblToday_1.setBounds(0, 0, 200, 32);
		lblToday_1.setHorizontalAlignment(SwingConstants.CENTER);
		DailyPanel.add(lblToday_1);
		
		JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSteps.setBounds(33,50, 100, 32);
		DailyPanel.add(lblSteps);
		
		JLabel lblFloors = new JLabel("Floors:");
		lblFloors.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFloors.setBounds(33, 75, 100, 32);
		DailyPanel.add(lblFloors);
		
		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDistance.setBounds(33, 125, 100, 32);
		DailyPanel.add(lblDistance);
		
		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCalories.setBounds(33, 100, 100, 32);
		DailyPanel.add(lblCalories);
		
		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblElevation.setBounds(33, 150, 100, 32);
		DailyPanel.add(lblElevation);
		
		/******************************************************************/
		
		JLabel lblLifetime = new JLabel("Lifetime Totals:");
		lblLifetime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLifetime.setBounds(0, 0, 200, 27);
		lblLifetime.setHorizontalAlignment(SwingConstants.CENTER);
		LifetimePanel.add(lblLifetime);
		
		JLabel lblSteps_1 = new JLabel("Steps:");
		lblSteps_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSteps_1.setBounds(33,50, 102, 32);
		LifetimePanel.add(lblSteps_1);
		
		JLabel lblFloors_1 = new JLabel("Floors:");
		lblFloors_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFloors_1.setBounds(33, 75, 102, 32);
		LifetimePanel.add(lblFloors_1);
		
		JLabel lblDistance_1 = new JLabel("Distance:");
		lblDistance_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDistance_1.setBounds(33, 125, 100, 32);
		LifetimePanel.add(lblDistance_1);
		
		JLabel lblCalories_1 = new JLabel("Calories:");
		lblCalories_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCalories_1.setBounds(33, 100, 100, 32);
		LifetimePanel.add(lblCalories_1);
		
		/******************************************************************/
		
		JLabel lblWeekly = new JLabel("This Week's Goals:");
		lblWeekly.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWeekly.setBounds(24, 6, 178, 25);
		weeklyGoals.add(lblWeekly);
		
		JLabel lblSteps_3 = new JLabel("Steps:");
		lblSteps_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSteps_3.setBounds(10, 34, 80, 25);
		weeklyGoals.add(lblSteps_3);
		
		JLabel lblDistance_3 = new JLabel("Distance:");
		lblDistance_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDistance_3.setBounds(10, 86, 80, 25);
		weeklyGoals.add(lblDistance_3);
		
		JLabel lblFloors_3 = new JLabel("Floors:");
		lblFloors_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFloors_3.setBounds(10, 133, 80, 25);
		weeklyGoals.add(lblFloors_3);
		
		/******************************************************************/
		
		JLabel lblDaily = new JLabel("Daily Goals for:");
		lblDaily.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDaily.setBounds(33, 4, 138, 23);
		dailyGoals.add(lblDaily);
		
		JLabel lblSteps_2 = new JLabel("Steps:");
		lblSteps_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSteps_2.setBounds(6, 29, 80, 25);
		dailyGoals.add(lblSteps_2);
		
		JLabel lblCalories_2 = new JLabel("Calories:");
		lblCalories_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCalories_2.setBounds(6, 66, 80, 25);
		dailyGoals.add(lblCalories_2);
		
		JLabel lblDistance_2 = new JLabel("Distance:");
		lblDistance_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDistance_2.setBounds(6, 104, 80, 25);
		dailyGoals.add(lblDistance_2);
		
		JLabel lblFloors_2 = new JLabel("Floors:");
		lblFloors_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFloors_2.setBounds(6, 140, 80, 25);
		dailyGoals.add(lblFloors_2);
		
		/******************************************************************/
		
		JLabel lifeActive = new JLabel("Lifetime Active Minutes: ");
		lifeActive.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lifeDailyMinutes.add(lifeActive);
		lifeActive.setBounds(19,8,220,25);
		
		JLabel sedLMins = new JLabel("Sedintary Minutes:");
		sedLMins.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lifeDailyMinutes.add(sedLMins);
		sedLMins.setBounds(15, 49, 161, 20);
		JLabel vActLMinutes = new JLabel("Very Active Minutes:");
		vActLMinutes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lifeDailyMinutes.add(vActLMinutes);
		vActLMinutes.setBounds(15, 143, 161, 20);
		JLabel lActLMinutes = new JLabel("Lightly Active Minutes:");
		lActLMinutes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lifeDailyMinutes.add(lActLMinutes);
		lActLMinutes.setBounds(15, 81, 170, 20);
		JLabel fActLMinutes = new JLabel("Fairly Active Minutes:");
		fActLMinutes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lifeDailyMinutes.add(fActLMinutes);
		fActLMinutes.setBounds(15, 112, 161, 20);
		
		/******************************************************************/
		
		JLabel dailyActive = new JLabel("Active Minutes for: ");
		dailyActive.setFont(new Font("Tahoma", Font.PLAIN, 20));
		activeDailyMinutes.add(dailyActive);
		dailyActive.setBounds(18,13,176,25);
		
		JLabel sedMins = new JLabel("Sedintary Minutes:");
		sedMins.setFont(new Font("Tahoma", Font.PLAIN, 17));
		activeDailyMinutes.add(sedMins);
		sedMins.setBounds(15, 49, 153, 20);
		JLabel vActMinutes = new JLabel("Very Active Minutes:");
		vActMinutes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		activeDailyMinutes.add(vActMinutes);
		vActMinutes.setBounds(15, 143, 153, 20);
		JLabel lActMinutes = new JLabel("Lightly Active Minutes:");
		lActMinutes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		activeDailyMinutes.add(lActMinutes);
		lActMinutes.setBounds(15, 81, 168, 20);
		JLabel fActMinutes = new JLabel("Fairly Active Minutes:");
		fActMinutes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		activeDailyMinutes.add(fActMinutes);
		fActMinutes.setBounds(15, 112, 168, 20);
		
		/******************************************************************/
		
		JLabel bestDaysTitle = new JLabel("Best Days: ");
		bestDaysTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bestDays.add(bestDaysTitle);
		bestDaysTitle.setBounds(30, 11, 134, 25);
		bestDays.add(bestDaysTitle);
		
		JLabel bestSteps = new JLabel("Best Steps:");
		bestSteps.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestSteps.setBounds(10, 56, 100, 20);
		bestDays.add(bestSteps);
		JLabel bestDistance = new JLabel("Best Distance:");
		bestDistance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bestDistance.setBounds(10, 97, 100, 20);
		bestDays.add(bestDistance);
		JLabel bestFloors = new JLabel("Best Floors:");
		bestFloors.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestFloors.setBounds(10, 137, 100, 20);
		bestDays.add(bestFloors);
		
		/**************************************** Initial Data Calls ***********************************/
		
		/**********************  Daily Goals ***************/
		lbldailystepgoal = new JLabel();
		lbldailystepgoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldailystepgoal.setBounds(133, 27, 80, 25);
		dailyGoals.add(lbldailystepgoal);
		dailyStepProgressBar = new JProgressBar();
		dailyStepProgressBar.setStringPainted(true);
		dailyStepProgressBar.setBounds(16, 49, 265, 25);
		dailyGoals.add(dailyStepProgressBar);
		
		lbldailycaloriegoal = new JLabel();
		lbldailycaloriegoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldailycaloriegoal.setBounds(136, 66, 77, 25);
		dailyGoals.add(lbldailycaloriegoal);
		dailyCalorieProgressBar = new JProgressBar();
		dailyCalorieProgressBar.setStringPainted(true);
		dailyCalorieProgressBar.setBounds(16, 81, 265, 31);
		dailyGoals.add(dailyCalorieProgressBar);
		
		lbldailydistancegoal = new JLabel();
		lbldailydistancegoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldailydistancegoal.setBounds(133, 104, 80, 25);
		dailyGoals.add(lbldailydistancegoal);
		dailyDistanceProgressBar = new JProgressBar();
		dailyDistanceProgressBar.setStringPainted(true);
		dailyDistanceProgressBar.setBounds(15, 120, 265, 31);
		dailyGoals.add(dailyDistanceProgressBar);
		
		lbldailyfloorgoal = new JLabel();
		lbldailyfloorgoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldailyfloorgoal.setBounds(133, 140, 80, 23);
		dailyGoals.add(lbldailyfloorgoal);
		dailyFloorProgressBar = new JProgressBar();
		dailyFloorProgressBar.setBounds(16, 155, 265, 31);
		dailyFloorProgressBar.setStringPainted(true);
		dailyGoals.add(dailyFloorProgressBar);
		
		goalDate = new JLabel();
		goalDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goalDate.setBounds(181, 4, 125, 25);
		dailyGoals.add(goalDate);
		
		/**********************  Weekly Goals ***************/
		
		lblweeklystepgoal = new JLabel();
		lblweeklystepgoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblweeklystepgoal.setBounds(142, 34, 73, 25);
		weeklyGoals.add(lblweeklystepgoal);
		weeklyStepProgressBar = new JProgressBar();
		weeklyStepProgressBar.setBounds(16, 61, 265, 25);
		weeklyStepProgressBar.setStringPainted(true);
		weeklyGoals.add(weeklyStepProgressBar);
		
		lblweeklydistancegoal = new JLabel();
		lblweeklydistancegoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblweeklydistancegoal.setBounds(142, 86, 73, 25);
		weeklyGoals.add(lblweeklydistancegoal);
		weeklyDistanceProgressBar = new JProgressBar();
		weeklyDistanceProgressBar.setBounds(16, 110, 265, 25);		
		weeklyDistanceProgressBar.setStringPainted(true);
		weeklyGoals.add(weeklyDistanceProgressBar);
		
		lblweeklyfloorgoal = new JLabel();
		lblweeklyfloorgoal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblweeklyfloorgoal.setBounds(142, 133, 73, 25);
		weeklyGoals.add(lblweeklyfloorgoal);
		weeklyFloorProgressBar = new JProgressBar();
		weeklyFloorProgressBar.setBounds(15, 158, 265, 25);
		weeklyFloorProgressBar.setStringPainted(true);
		weeklyGoals.add(weeklyFloorProgressBar);
		
		/***********************  Daily Totals *********************/
		lblsteps = new JLabel();
		lblsteps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblsteps.setHorizontalAlignment(SwingConstants.RIGHT);
		lblsteps.setBounds(160, 50, 110, 32);
		DailyPanel.add(lblsteps);
		
		lblfloors = new JLabel();
		lblfloors.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblfloors.setHorizontalAlignment(SwingConstants.RIGHT);
		lblfloors.setBounds(160,75, 110, 32);
		DailyPanel.add(lblfloors);
		
		lbldistance = new JLabel();
		lbldistance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldistance.setBounds(160, 125, 113, 32);
		DailyPanel.add(lbldistance);
		
		lblcalories = new JLabel();
		lblcalories.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcalories.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcalories.setBounds(160, 100, 110, 32);
		DailyPanel.add(lblcalories);
		
		lblelevation = new JLabel();
		lblelevation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblelevation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblelevation.setBounds(160, 150, 113, 32);
		DailyPanel.add(lblelevation);
		
		dataDate = new JLabel();
		dataDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dataDate.setBounds(195, 4, 125, 25);
		DailyPanel.add(dataDate);
		
		/***********************  Lifetime Totals *********************/
		
		lbllifesteps = new JLabel();
		lbllifesteps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbllifesteps.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifesteps.setBounds(160, 50, 110, 32);
		LifetimePanel.add(lbllifesteps);
		
		lbllifefloors = new JLabel();
		lbllifefloors.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbllifefloors.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifefloors.setBounds(160, 75, 110, 32);
		LifetimePanel.add(lbllifefloors);
		
		lbllifedistance = new JLabel();
		lbllifedistance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbllifedistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifedistance.setBounds(160, 125, 110, 32);
		LifetimePanel.add(lbllifedistance);
		
		lbllifecalories = new JLabel();
		lbllifecalories.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbllifecalories.setHorizontalAlignment(SwingConstants.RIGHT);
		lbllifecalories.setBounds(160, 100, 110, 32);
		LifetimePanel.add(lbllifecalories);
		
		/***********************  Best Day Totals *********************/
		
		bestStepsDay = new JLabel();
		bestStepsDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestDays.add(bestStepsDay);
		bestStepsDay.setBounds(115, 56, 100, 20);
		bestDistanceDay = new JLabel();
		bestDistanceDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestDays.add(bestDistanceDay);
		bestDistanceDay.setBounds(115, 96, 100, 20);
		bestFloorsDay = new JLabel();
		bestFloorsDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestDays.add(bestFloorsDay);
		bestFloorsDay.setBounds(115, 137, 100, 20);
		
		bestStepsAmount = new JLabel();
		bestStepsAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestStepsAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		bestDays.add(bestStepsAmount);
		bestStepsAmount.setBounds(217, 56, 70, 20);
		bestDistanceAmount = new JLabel();
		bestDistanceAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestDistanceAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		bestDays.add(bestDistanceAmount);
		bestDistanceAmount.setBounds(217, 96, 70, 20);
		bestFloorsAmount = new JLabel();
		bestFloorsAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bestFloorsAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		bestDays.add(bestFloorsAmount);
		bestFloorsAmount.setBounds(217, 136, 70, 20);
		
		/***********************  Daily Active Totals *********************/
		sedMinsData = new JLabel();
		sedMinsData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sedMinsData.setHorizontalAlignment(SwingConstants.RIGHT);
		activeDailyMinutes.add(sedMinsData);
		sedMinsData.setOpaque(false);
		sedMinsData.setBounds(195, 49, 100, 20);
		vActMinutesData = new JLabel();
		vActMinutesData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		vActMinutesData.setHorizontalAlignment(SwingConstants.RIGHT);
		activeDailyMinutes.add(vActMinutesData);
		vActMinutesData.setBounds(195, 143, 100, 20);
		lActMinutesData = new JLabel();
		lActMinutesData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lActMinutesData.setHorizontalAlignment(SwingConstants.RIGHT);
		activeDailyMinutes.add(lActMinutesData);
		lActMinutesData.setBounds(195, 81, 100, 20);
		fActMinutesData = new JLabel();
		fActMinutesData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		fActMinutesData.setHorizontalAlignment(SwingConstants.RIGHT);
		activeDailyMinutes.add(fActMinutesData);
		fActMinutesData.setBounds(195, 112, 100, 20);
		actDate = new JLabel();
		actDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		actDate.setBounds(195,13,125,25);
		activeDailyMinutes.add(actDate);
		
		/***********************  Lifetime Active Totals *********************/
		
		sedLMinsData = new JLabel();
		sedLMinsData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sedLMinsData.setHorizontalAlignment(SwingConstants.RIGHT);
		lifeDailyMinutes.add(sedLMinsData);
		sedLMinsData.setBounds(198, 49, 85, 20);
		vActLMinutesData = new JLabel();
		vActLMinutesData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		vActLMinutesData.setHorizontalAlignment(SwingConstants.RIGHT);
		lifeDailyMinutes.add(vActLMinutesData);
		vActLMinutesData.setBounds(198, 143, 85, 20);
		lActLMinutesData = new JLabel();
		lActLMinutesData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lActLMinutesData.setHorizontalAlignment(SwingConstants.RIGHT);
		lifeDailyMinutes.add(lActLMinutesData);
		lActLMinutesData.setBounds(198, 81, 85, 20);
		fActLMinutesData = new JLabel();
		fActLMinutesData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		fActLMinutesData.setHorizontalAlignment(SwingConstants.RIGHT);
		lifeDailyMinutes.add(fActLMinutesData);
		fActLMinutesData.setBounds(198, 112, 85, 20);
}
	/**************************************** Refreshing Data Calls ***********************************/
	/**
	 * Loads the data for the Lifetime Totals Widget
	 * 
	 * @throws Exception   thrown if the data cannot be retreived
	 */
	public static void loadLifetimeTotalsData() throws Exception{
		lbllifesteps.setText(Integer.toString(Totals.loadTotals().getLTotals().getSteps()));
		lbllifefloors.setText(Integer.toString(Totals.loadTotals().getLTotals().getFloors()));
		lbllifedistance.setText(Double.toString(truncate(Totals.loadTotals().getLTotals().getDistance(), 3)));
		lbllifecalories.setText(Integer.toString(History.loadHistory().getcHis().getLifeCalories()));
	}
	
	/**
	 * Loads the data for the given Date's Totals Widget
	 * @throws Exception  thrown if data cannot be retreived
	 * @param date     String of the date to check the data in the format "yyyy-mm-dd"
	 * @param screen - the date to be displayed on the screen in the format "mmm. d yy"
	 */
	public static void loadDateTotalsData(String date, String screen) throws Exception{
		try {
			String steps = Integer.toString(History.loadHistory().getsHis().getStepData(date));
			String floors = Integer.toString(History.loadHistory().getfHis().getFloorData(date));
			String distance = Double.toString(truncate(History.loadHistory().getdHis().getDistanceData(date),3));
			String calories = Integer.toString(History.loadHistory().getcHis().getCalData(date));
			String elevation = Double.toString(truncate(History.loadHistory().geteHis().getElevationData(date),3));
			
			if (steps.equals("-1"))
				steps = "0";
			if (floors.equals("-1"))
				floors = "0";
			if (distance.equals("-1.0"))
				distance = "0";
			if (calories.equals("-1"))
				calories = "0.0";
			if (elevation.equals("-1.0"))
				elevation = "0.0";
			
			lblsteps.setText(steps);
			lblfloors.setText(floors);
			lbldistance.setText(distance);	
			lblcalories.setText(calories);
			lblelevation.setText(elevation);
			dataDate.setText(screen);
		} catch(Exception e) {/*Catching holes in the JSON file (skips them)*/}
	}
	
	/**
	 * Loads the data for the WeeklyGoals Widget
	 * 
	 * @throws Exception     thrown if the data cannot be retreived
	 */
	public static void loadWeeklyGoalsData() throws Exception{
		int steps = Totals.loadTotals().getWTotals().getSteps();
		int floors = Totals.loadTotals().getWTotals().getFloors();
		double distance = Totals.loadTotals().getWTotals().getDistance();

		lblweeklystepgoal.setText(Integer.toString(Goals.loadGoals().getWGoals().getSteps()));
		weeklyStepProgressBar.setMaximum(Integer.parseInt(lblweeklystepgoal.getText()));
		weeklyStepProgressBar.setValue(steps);

		lblweeklydistancegoal.setText(Double.toString(Goals.loadGoals().getWGoals().getDistance()));
		weeklyDistanceProgressBar.setMaximum(100);
		weeklyDistanceProgressBar.setValue((int)(distance/Double.parseDouble(lblweeklydistancegoal.getText())*100));
		
		lblweeklyfloorgoal.setText(Integer.toString(Goals.loadGoals().getWGoals().getFloors()));
		weeklyFloorProgressBar.setMaximum(Integer.parseInt(lblweeklyfloorgoal.getText()));
		weeklyFloorProgressBar.setValue(floors);
	}
	
	/**
	 * Loads the data for the Lifetime Active Minutes Widget
	 * 
	 * @throws Exception    thrown if the data cannot be retreived
	 */
	public static void loadLifeActiveMinutes() throws Exception{
		sedLMinsData.setText(Integer.toString(History.loadHistory().getSedHis().getLifeSedentaryMinutes()));
		vActLMinutesData.setText(Integer.toString(History.loadHistory().getVeryHis().getLifeVeryMinutes()));
		lActLMinutesData.setText(Integer.toString(History.loadHistory().getLightHis().getLifeLightMinutes()));
		fActLMinutesData.setText(Integer.toString(History.loadHistory().getFairHis().getLifeFairMinutes()));
	}
	
	/**
	 * Loads the data for the given Date's Active Minutes Widget
	 * 
	 * @throws Exception    thrown if the data cannot be retreived
	 * @param date     String of the date to check the data in the format "yyyy-mm-dd"
	 * @param screen   String the date to be displayed on the screen in the format "mmm. d yy"
	 */
	public static void loadDailyActiveMinutes(String date, String screen) throws Exception{
		try{
			String sed = Integer.toString(History.loadHistory().getSedHis().getMinSedentaryData(date));
			String vAct = Integer.toString(History.loadHistory().getVeryHis().getMinVeryData(date));
			String lAct = Integer.toString(History.loadHistory().getLightHis().getMinLightData(date));
			String fAct = Integer.toString(History.loadHistory().getFairHis().getMinFairData(date));
			
			if (sed.equals("-1"))
				sed = "0";
			if (lAct.equals("-1"))
				lAct = "0";
			if (vAct.equals("-1"))
				vAct = "0";
			if (fAct.equals("-1"))
				fAct = "0";
			
			sedMinsData.setText(sed);
			vActMinutesData.setText(lAct);
			lActMinutesData.setText(vAct);
			fActMinutesData.setText(fAct);
			actDate.setText(screen);
		} catch(Exception e) {/*Catching holes in the JSON file (skips them)*/}
	}
	
	/**
	 * Loads the data for the Best Days Widget
	 * 
	 * @throws Exception   thrown if the data cannot be retreived
	 */
	public static void loadBestDays() throws Exception{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		DateFormat display = new SimpleDateFormat("MMM. d, yy");
		
		String stepsday = Totals.loadTotals().getLTotals().getBestStepsDate();
		stepsday = display.format(format.parse(stepsday));
		String disday = Totals.loadTotals().getLTotals().getBestDistanceDate();
		disday = display.format(format.parse(disday));
		String calday = Totals.loadTotals().getLTotals().getBestFloorsDate();
		calday = display.format(format.parse(calday));
		
		bestStepsDay.setText(stepsday);
		bestDistanceDay.setText(disday);
		bestFloorsDay.setText(calday);
		bestStepsAmount.setText(Integer.toString(Totals.loadTotals().getLTotals().getBestSteps()));
		bestDistanceAmount.setText(Double.toString(truncate(Totals.loadTotals().getLTotals().getBestDistance(), 3)));
		bestFloorsAmount.setText(Integer.toString(Totals.loadTotals().getLTotals().getBestFloors()));
	}
	
	/**
	 * Loads the data for the given Date's Goals Widget
	 * 
	 * @throws Exception   thrown if the data cannot be retreived
	 * @param date      String of the date to check the data in the format "yyyy-mm-dd"
	 * @param screen    String of the date to be displayed on the screen in the format "mmm. d yy"
	 */
	public static void loadDateGoalsData(String date, String screen) throws Exception{
		try{
		lbldailystepgoal.setText(Integer.toString(Goals.loadGoals().getDGoals().getSteps()));
		dailyStepProgressBar.setMaximum(Integer.parseInt(lbldailystepgoal.getText()));
		dailyStepProgressBar.setValue(History.loadHistory().getsHis().getStepData(date));
		
		lbldailycaloriegoal.setText(Integer.toString(Goals.loadGoals().getDGoals().getCalories()));
		dailyCalorieProgressBar.setMaximum(Integer.parseInt(lbldailycaloriegoal.getText()));
		dailyCalorieProgressBar.setValue(History.loadHistory().getcHis().getCalData(date));
		
		lbldailydistancegoal.setText(Double.toString(Goals.loadGoals().getDGoals().getDistance()));
		dailyDistanceProgressBar.setMaximum(100);
		dailyDistanceProgressBar.setValue((int) (History.loadHistory().getdHis().getDistanceData(date) / (double)Goals.loadGoals().getDGoals().getDistance()*100));
		
		lbldailyfloorgoal.setText(Integer.toString(Goals.loadGoals().getDGoals().getFloors()));
		dailyFloorProgressBar.setMaximum(Integer.parseInt(lbldailyfloorgoal.getText()));
		dailyFloorProgressBar.setValue(History.loadHistory().getfHis().getFloorData(date));
		goalDate.setText(screen);
		dailyStepProgressBar.setBackground(new Color(255,255,255,255));
		
		} catch(Exception e) {/*Catching holes in the JSON file (skips them)*/}
	}
	
	/**
	 * Method to update the Widgets when a new date is passed
	 * @param date    Date object of the date passed from the calendar
	 * @throws Exception   thrown if the data cannot be retreived
	 */
	public static void updateWidgets(Date date) throws Exception{
		DateFormat api = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat display = new SimpleDateFormat("MMM d, yy");

		Date today = Calendar.getInstance().getTime();
		if (date.after(today)){
			date = today;
		}
		
		loadDailyActiveMinutes(api.format(date).toString(), display.format(date).toString());
		loadDateTotalsData(api.format(date).toString(), display.format(date).toString());
		loadDateGoalsData(api.format(date).toString(), display.format(date).toString());
	}	
	
	/**
	 * Rounds double numbers to a specific precision to be displayed on the dashboard
	 * 
	 * @param number      double value of the number to round
	 * @param precision   int value of the number of decimal places to round to
	 * @return fractionalPart
	 */
	public static double truncate(double number, int precision)
	{
	    double prec = Math.pow(10, precision);
	    int integerPart = (int) number;
	    double fractionalPart = number - integerPart;
	    fractionalPart *= prec;
	    int fractPart = (int) fractionalPart;
	    fractionalPart = (double) (integerPart) + (double) (fractPart)/prec;
	    return fractionalPart;
	}
	
	/**
	 * Checks whether the dashboard has been customized (by looking for the serialized .dat file)
	 * @return      true if the dashboard has been customized, false otherwise
	 */
	public static Boolean customized(){
		if (custom)
			return true;
		return false;
			
	}
	
	/**
	 * Method to remove a certain colour from a buffered image. Used to take out shelly's background
	 * @param image   BufferedImage object of the to have a colour removed
	 * @return image  BufferedImage object of the image which has had the colour removed
	 */
	public static BufferedImage removeBack(BufferedImage image){
		for (int y = 0; y < image.getHeight(); ++y) {
		    for (int x = 0; x < image.getWidth(); ++x) {
		         int argb = image.getRGB(x, y);
		         if (argb == -3014657)
		         {
		              image.setRGB(x, y, 0);
		         }
		    }
		}
		return image;
	}
	
	/**
	 * Method to update the shelly image on the dashboard
	 * @param shel      BufferedImage object taken of the current shelly
	 */
	public static void updateShelly(BufferedImage shel) {
		BufferedImage snail = DashboardTab.removeBack(shel);
		shelly = new JLabel(new ImageIcon(snail.getScaledInstance(176, 184, Image.SCALE_DEFAULT)));
		shellyPic.remove(shelly);
		shellyPic.add(shelly);
		shellyPic.repaint();
	}
}