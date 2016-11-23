package ca.uwo.csd.cs2212.team06.UI;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Timer;
import ca.uwo.csd.cs2212.team06.Goals;
import ca.uwo.csd.cs2212.team06.History;
import ca.uwo.csd.cs2212.team06.Totals;
import ca.uwo.csd.cs2212.team06.badges.Badges;

/**
 * This is the main window of the program
 * @author Team06
 * @version 1.0
 * @category Class
 */
 
public class FitBit {

	private JFrame frame;
	private JLabel lblClock;
	static DashboardTab dashboard = new DashboardTab();
	private ShellyTab shell = new ShellyTab();
	//shelly.setLifetimeExperienceTotal(138125);
	private AccoladesTab accol = new AccoladesTab();
	private JLabel lastUpdated;
	private static String string;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					if (args.length>0)
						string = args[0];
					FitBit window = new FitBit();
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
	public FitBit() throws Exception{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @exception     thrown if the frame cannot be set
	 */
	private void initialize() throws Exception{

		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);


		JTabbedPane Tabs = new JTabbedPane(JTabbedPane.TOP);
		Tabs.setBounds(0, 25, 1054, 600);
		frame.getContentPane().add(Tabs);
		Tabs.setOpaque(false);

		JPanel DailyDasboardPanel = dashboard.summaryTab;
		Tabs.addTab("Daily Dashboard", null, DailyDasboardPanel, null);
		DailyDasboardPanel.setOpaque(false);
		JPanel AccoladesPanel = accol.badges;
		Tabs.addTab("Accolades", null, AccoladesPanel, null);
		AccoladesPanel.setOpaque(false);
		JPanel ShellyPanel = shell.Shelly;
		Tabs.addTab("Shelly", null, ShellyPanel, null);
		ShellyPanel.setOpaque(false);

		/**************************************************  CLOCK *******************************************/

		final DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy  hh:mm:ss a");
		final Calendar now = Calendar.getInstance();
		lblClock = new JLabel(dateFormat.format(now.getTime()));
		lblClock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClock.setBounds(622, 3, 335, 19);
		lblClock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblClock);
		lblClock.setForeground(Color.WHITE);

		final Timer clock = new Timer();
		class ClockUpdate extends TimerTask {
			public void run() {
				lblClock.setText(dateFormat.format(Calendar.getInstance().getTime()));
				return;
			}
		}
		clock.scheduleAtFixedRate(new ClockUpdate(), 0, 1000);

		/*******************************************************  LOGOS  ***************************************************************/

		JLabel icnLogo = new JLabel("");
		icnLogo.setBounds(14, 605, 255, 100);
		double height = (double)icnLogo.getWidth()*(double)((double)222/(double)623);
		icnLogo.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/FitbitLogo_Reversed.png")).getImage().getScaledInstance(icnLogo.getWidth(), (int)height, Image.SCALE_DEFAULT)));
		frame.getContentPane().add(icnLogo);

		/********************************************************  DATA LOADING  ************************************************************/
		loadLabelsData();
		updateDate(Calendar.getInstance().getTime());
		shell.getShellyPic();

		lastUpdated = new JLabel("Test");
		lastUpdated.setBounds(835, 666, 219, 15);
		final DateFormat refreshFormat = new SimpleDateFormat("MMMMM d, hh:mm a");
		lastUpdated.setForeground(Color.WHITE);
		if (string == null){
			lastUpdated.setText("Last Refresh: " + refreshFormat.format(now.getTime()));
		} else {
			lastUpdated.setText("Running in TEST MODE");
		}
		frame.getContentPane().add(lastUpdated);

		/***********************************************************  REFRESH BUTTON  **********************************************************/

		final JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (string == null){
					btnRefresh.setEnabled(false);
					lastUpdated.setText("Last Refresh: " + refreshFormat.format(Calendar.getInstance().getTime()));
					Goals g = new Goals();
					Totals t = new Totals();
					History h = new History();
					Badges b = new Badges();
					try {
						Goals.storeGoals(g);
						Totals.storeTotals(t);
						History.storeHistory(h);
						b.checkAllBadges("");
						Badges.storeBadges(b);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					loadLabelsData();
					updateDate(Calendar.getInstance().getTime());
					ShellyTab.getShellyPic();

					Timer time = new Timer();
					class RefreshClick extends TimerTask {
						public void run() {
							btnRefresh.setEnabled(true);
							System.out.println("Hi");
						}
					}
					time.schedule(new RefreshClick(), 300000);
					System.out.println("Data refreshed");
				}
			}
		});
		btnRefresh.setBounds(961, 0, 103, 29);
		frame.getContentPane().add(btnRefresh);

		/*************************************************** Background Picture ***************************************/
		JPanel bgPic = new JPanel();
		bgPic = new JPanel();
		bgPic.setBounds(0, 0, 1080, 720);
		bgPic.setLayout(null);
		frame.getContentPane().add(bgPic);
		JLabel lblBackpic = new JLabel("");
		lblBackpic.setBounds(0, 0, 1080, 720);
		bgPic.add(lblBackpic);
		int bg = randomInteger(1,3);
		String s = "";
		switch(bg){
		case 1: s = "src/main/resources/backgrounds/blue sky.jpg";
		break;
		case 2: s = "src/main/resources/backgrounds/blue sky 2.jpg";
		break;
		case 3: s = "src/main/resources/backgrounds/blue sky 3.jpg";
		}
		lblBackpic.setIcon(new ImageIcon(new ImageIcon(s)
				.getImage().getScaledInstance(1200,(int)(1200/1.6),Image.SCALE_SMOOTH)));
		lblBackpic.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackpic.setVerticalAlignment(SwingConstants.TOP);
	}


	/*************************************************** Set all variable data ***************************************************/
	/**
	 * loadLabelsData retrieves all non-daily information to be displayed on the dashboard
	 */
	private static void loadLabelsData(){
		try{
			DashboardTab.loadLifetimeTotalsData();
			DashboardTab.loadWeeklyGoalsData();
			DashboardTab.loadBestDays();
			DashboardTab.loadLifeActiveMinutes();
			updateDate(Calendar.getInstance().getTime());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/***
	 * updateDate retrieves all daily information to be displayed on the dashboard
	 * This method is used when the calendar is clicked
	 * 
	 * @param date     Date object that the Calendar passes
	 */
	public static void updateDate(Date date){
		try {
			DashboardTab.updateWidgets(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * A method that calculates a random integer between the to values given
	 * This method is used to select the background on startup
	 * 
	 * @param min    int to be chosen
	 * @param max    int to be chosen
	 * @return randomNum
	 */
	public int randomInteger(int min, int max) {

		Random rand = new Random();

		// nextInt excludes the top value so we have to add 1 to include the top value
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
