package ca.uwo.csd.cs2212.team06.UI;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import ca.uwo.csd.cs2212.team06.badges.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This creates the accolades window and shows the accolades earned
 * @author team06
 * @version 1.0
 * @category Class
 */
 
public class AccoladesTab {
	//Attributes
	private static JFrame frame;
	JPanel badges;
	private static JPanel backPicPanel;
	private static JPanel badgePanel;
	private static JFrame popupPanel;
	private JButton btnBadge,btnBadge_1,btnBadge_2,btnBadge_3,btnBadge_4,btnBadge_5,btnBadge_6;
	private JButton btnBadge_7,btnBadge_8,btnBadge_9,btnBadge_10,btnBadge_11,btnBadge_12,btnBadge_13;
	private JButton btnBadge_14,btnBadge_15,btnBadge_16, btnBadge_17,btnBadge_18,btnBadge_19,btnBadge_20;
	private static Badges badgeData;


	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					AccoladesTab window = new AccoladesTab();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the application.
	 * @exception     thrown if the badges cannot be loaded 
	 */
	public AccoladesTab() throws Exception{
			badgeData = Badges.loadBadges();
		//System.out.println(badgeData.getBadges()[0]);
		initialize();
	}

	/**
	 * Initializes the contents of the frame.  This includes a panel for every badge's image, as well as the background.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane Tabs = new JTabbedPane(JTabbedPane.TOP);
		Tabs.setBounds(0, 25, 1054, 595);
		frame.getContentPane().add(Tabs);//new AlphaContainer(Tabs));
		badges = new JPanel();
		badges.setOpaque(true);
		Tabs.addTab("Badges", null, badges, null);
		badges.setLayout(null);
		backPicPanel = new JPanel();
		backPicPanel.setOpaque(false);
		backPicPanel.setBounds(10, 11, 1029, 545);
		backPicPanel.setLayout(null);


		/**************************************PANEL TO STORE BADGES***********************************/	
		badgePanel = new JPanel();
		badgePanel.setOpaque(false);
		badgePanel.setBounds(0, 0, 1029, 545);

		/************************************BACKGROUND PICTURE**************************************/
		backPicPanel.add(badgePanel);
		badgePanel.setLayout(new GridLayout(3, 7, 50, 50));

		/**************************************Lean and Mean***********************************/
		btnBadge = new JButton("");
		btnBadge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[0])
					popup(badgeData.getDescriptions()[0],"Lean and Mean","target/classes/BigMac.png",badgeData.getPercentages()[0],100);
				else
					popup(badgeData.getDescriptions()[0],"Lean and Mean","target/classes/BigMacGrey.png",badgeData.getPercentages()[0],100);
				popupPanel.setVisible(true);	
				}
		});
		badgePanel.add(btnBadge);
		btnBadge.setOpaque(false);
		btnBadge.setFocusPainted(false);
		btnBadge.setContentAreaFilled(false);
		btnBadge.setBorderPainted(false);
		if(badgeData.getBadges()[0]){
			btnBadge.setIcon(new ImageIcon(new ImageIcon("target/classes/BigMac.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/BigMacClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge.setIcon(new ImageIcon(new ImageIcon("target/classes/BigMacGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/BigMacGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Circle the Globe***********************************/
		btnBadge_1 = new JButton("");
		btnBadge_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[1])
					popup(badgeData.getDescriptions()[1],"Circle the Globe","target/classes/CircleTheGlobe.png",badgeData.getPercentages()[1],100);
				else
					popup(badgeData.getDescriptions()[1],"Circle the Globe","target/classes/CircleTheGlobeGrey.png",badgeData.getPercentages()[1],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_1);
		btnBadge_1.setOpaque(false);
		btnBadge_1.setFocusPainted(false);
		btnBadge_1.setContentAreaFilled(false);
		btnBadge_1.setBorderPainted(false);
		if(badgeData.getBadges()[1]){
			btnBadge_1.setIcon(new ImageIcon(new ImageIcon("target/classes/CircleTheGlobe.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_1.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/CircleTheGlobeClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_1.setIcon(new ImageIcon(new ImageIcon("target/classes/CircleTheGlobeGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_1.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/CircleTheGlobeGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************High Rise***********************************/
		btnBadge_2 = new JButton("");
		btnBadge_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[2])
					popup(badgeData.getDescriptions()[2],"High Rise","target/classes/HighRise.png",badgeData.getPercentages()[2],100);
				else
					popup(badgeData.getDescriptions()[2],"High Rise","target/classes/HighRiseGrey.png",badgeData.getPercentages()[2],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_2);
		btnBadge_2.setOpaque(false);
		btnBadge_2.setFocusPainted(false);
		btnBadge_2.setContentAreaFilled(false);
		btnBadge_2.setBorderPainted(false);
		if(badgeData.getBadges()[2]){
			btnBadge_2.setIcon(new ImageIcon(new ImageIcon("target/classes/HighRise.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_2.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/HighRiseClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_2.setIcon(new ImageIcon(new ImageIcon("target/classes/HighRiseGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_2.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/HighRiseGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Hobbit Feet***********************************/
		btnBadge_3 = new JButton("");
		btnBadge_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[3])
					popup(badgeData.getDescriptions()[3],"Hobbit Feet","target/classes/HobbitFeet.png",badgeData.getPercentages()[3],100);
				else
					popup(badgeData.getDescriptions()[3],"Hobbit Feet","target/classes/HobbitFeetGrey.png",badgeData.getPercentages()[3],100);
				popupPanel.setVisible(true);
			}
		});

		badgePanel.add(btnBadge_3);
		btnBadge_3.setOpaque(false);
		btnBadge_3.setBorderPainted(false);
		btnBadge_3.setFocusPainted(false);
		btnBadge_3.setContentAreaFilled(false);
		if(badgeData.getBadges()[3]){
			btnBadge_3.setIcon(new ImageIcon(new ImageIcon("target/classes/HobbitFeet.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_3.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/HobbitFeetClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_3.setIcon(new ImageIcon(new ImageIcon("target/classes/HobbitFeetGrey.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_3.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/HobbitFeetGreyClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************One Small Step***********************************/
		btnBadge_4 = new JButton("");
		btnBadge_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[4])
					popup(badgeData.getDescriptions()[4],"One Small Step","target/classes/OneSmallStep.png",badgeData.getPercentages()[4],100);
				else
					popup(badgeData.getDescriptions()[4],"One Small Step","target/classes/OneSmallStepGrey.png",badgeData.getPercentages()[4],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_4);
		btnBadge_4.setOpaque(false);
		btnBadge_4.setFocusPainted(false);
		btnBadge_4.setContentAreaFilled(false);
		btnBadge_4.setBorderPainted(false);
		if(badgeData.getBadges()[4]){
			btnBadge_4.setIcon(new ImageIcon(new ImageIcon("target/classes/OneSmallStep.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_4.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/OneSmallStepClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_4.setIcon(new ImageIcon(new ImageIcon("target/classes/OneSmallStepGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_4.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/OneSmallStepGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************To Infinity and Beyond***********************************/
		btnBadge_5 = new JButton("");
		btnBadge_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[5])
					popup(badgeData.getDescriptions()[5],"To Infinity and Beyond","target/classes/toInfiniteAndBeyond.png",badgeData.getPercentages()[5],100);
				else
					popup(badgeData.getDescriptions()[5],"To Infinity and Beyond","target/classes/toInfiniteAndBeyondGrey.png",badgeData.getPercentages()[5],100);
				popupPanel.setVisible(true);
			}
		});

		badgePanel.add(btnBadge_5);
		btnBadge_5.setOpaque(false);
		btnBadge_5.setBorderPainted(false);
		btnBadge_5.setFocusPainted(false);
		btnBadge_5.setContentAreaFilled(false);
		if(badgeData.getBadges()[5]){
			btnBadge_5.setIcon(new ImageIcon(new ImageIcon("target/classes/toInfiniteAndBeyond.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_5.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/toInfiniteAndBeyondClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_5.setIcon(new ImageIcon(new ImageIcon("target/classes/toInfiniteAndBeyondGrey.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_5.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/toInfiniteAndBeyondGreyClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Death Race***********************************/
		btnBadge_6 = new JButton("");
		btnBadge_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[6])
					popup(badgeData.getDescriptions()[6],"Death Race","target/classes/DeathRace.png",badgeData.getPercentages()[6],100);
				else
					popup(badgeData.getDescriptions()[6],"Death Race","target/classes/DeathRaceGrey.png",badgeData.getPercentages()[6],100);
				popupPanel.setVisible(true);
			}
		});

		badgePanel.add(btnBadge_6);
		btnBadge_6.setOpaque(false);
		btnBadge_6.setFocusPainted(false);
		btnBadge_6.setBorderPainted(false);
		btnBadge_6.setContentAreaFilled(false);
		if(badgeData.getBadges()[6]){
			btnBadge_6.setIcon(new ImageIcon(new ImageIcon("target/classes/DeathRace.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_6.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/DeathRaceClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_6.setIcon(new ImageIcon(new ImageIcon("target/classes/DeathRaceGrey.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_6.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/DeathRaceGreyClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Cat Nap***********************************/
		btnBadge_7 = new JButton("");
		btnBadge_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[7])
					popup(badgeData.getDescriptions()[7],"Cat Nap","target/classes/CatNap.png",badgeData.getPercentages()[7],100);
				else
					popup(badgeData.getDescriptions()[7],"Cat Nap","target/classes/CatNapGrey.png",badgeData.getPercentages()[7],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_7);
		btnBadge_7.setOpaque(false);
		btnBadge_7.setFocusPainted(false);
		btnBadge_7.setContentAreaFilled(false);
		btnBadge_7.setBorderPainted(false);
		if(badgeData.getBadges()[7]){
			btnBadge_7.setIcon(new ImageIcon(new ImageIcon("target/classes/CatNap.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_7.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/CatNapClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_7.setIcon(new ImageIcon(new ImageIcon("target/classes/CatNapGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_7.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/CatNapGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Endurance***********************************/
		btnBadge_8 = new JButton("");
		btnBadge_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[8])
					popup(badgeData.getDescriptions()[8],"Endurance","target/classes/Endurance.png",badgeData.getPercentages()[8],100);
				else
					popup(badgeData.getDescriptions()[8],"Endurance","target/classes/EnduranceGrey.png",badgeData.getPercentages()[8],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_8);
		btnBadge_8.setOpaque(false);
		btnBadge_8.setFocusPainted(false);
		btnBadge_8.setContentAreaFilled(false);
		btnBadge_8.setBorderPainted(false);
		if(badgeData.getBadges()[8]){
			btnBadge_8.setIcon(new ImageIcon(new ImageIcon("target/classes/Endurance.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_8.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/EnduranceClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_8.setIcon(new ImageIcon(new ImageIcon("target/classes/EnduranceGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_8.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/EnduranceGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Jumping Through Hoops***********************************/
		btnBadge_9 = new JButton("");
		btnBadge_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[9])
					popup(badgeData.getDescriptions()[9],"Jumping Through Hoops","target/classes/JumpingThroughHoops.png",badgeData.getPercentages()[9],100);
				else
					popup(badgeData.getDescriptions()[9],"Jumping Through Hoops","target/classes/JumpingThroughHoopsGrey.png",badgeData.getPercentages()[9],100);
				popupPanel.setVisible(true);			}
		});

		badgePanel.add(btnBadge_9);
		btnBadge_9.setOpaque(false);
		btnBadge_9.setFocusPainted(false);
		btnBadge_9.setContentAreaFilled(false);
		btnBadge_9.setBorderPainted(false);
		if(badgeData.getBadges()[9]){
			btnBadge_9.setIcon(new ImageIcon(new ImageIcon("target/classes/JumpingThroughHoops.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_9.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/JumpingThroughHoopsClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_9.setIcon(new ImageIcon(new ImageIcon("target/classes/JumpingThroughHoopsGrey.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_9.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/JumpingThroughHoopsGreyClicked.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Powerhouse***********************************/
		btnBadge_10 = new JButton("");
		btnBadge_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[10])
					popup(badgeData.getDescriptions()[10],"Powerhouse","target/classes/Powerhouse.png",badgeData.getPercentages()[10],100);
				else
					popup(badgeData.getDescriptions()[10],"Powerhouse","target/classes/PowerhouseGrey.png",badgeData.getPercentages()[10],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_10);
		btnBadge_10.setOpaque(false);
		btnBadge_10.setFocusPainted(false);
		btnBadge_10.setContentAreaFilled(false);
		btnBadge_10.setBorderPainted(false);
		if(badgeData.getBadges()[10]){
			btnBadge_10.setIcon(new ImageIcon(new ImageIcon("target/classes/Powerhouse.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_10.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/PowerhouseClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_10.setIcon(new ImageIcon(new ImageIcon("target/classes/PowerhouseGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_10.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/PowerhouseGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Renovation***********************************/
		btnBadge_11 = new JButton("");
		btnBadge_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[11])
					popup(badgeData.getDescriptions()[11],"Renovation","target/classes/Renovation.png",badgeData.getPercentages()[11],100);
				else
					popup(badgeData.getDescriptions()[11],"Renovation","target/classes/RenovationGrey.png",badgeData.getPercentages()[11],100);
				popupPanel.setVisible(true);
			}
		});

		badgePanel.add(btnBadge_11);
		btnBadge_11.setBorderPainted(false);
		btnBadge_11.setOpaque(false);
		btnBadge_11.setFocusPainted(false);
		btnBadge_11.setContentAreaFilled(false);
		if(badgeData.getBadges()[11]){
			btnBadge_11.setIcon(new ImageIcon(new ImageIcon("target/classes/Renovation.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_11.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/RenovationClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_11.setIcon(new ImageIcon(new ImageIcon("target/classes/RenovationGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_11.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/RenovationGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************No Breaks***********************************/
		btnBadge_12 = new JButton("");
		btnBadge_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[12])
					popup(badgeData.getDescriptions()[12],"No Breaks","target/classes/BreakDayYet.png",badgeData.getPercentages()[12],100);
				else
					popup(badgeData.getDescriptions()[12],"No Breaks","target/classes/BreakDayYetGrey.png",badgeData.getPercentages()[12],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_12);
		btnBadge_12.setOpaque(false);
		btnBadge_12.setFocusPainted(false);
		btnBadge_12.setContentAreaFilled(false);
		btnBadge_12.setBorderPainted(false);
		if(badgeData.getBadges()[12]){
			btnBadge_12.setIcon(new ImageIcon(new ImageIcon("target/classes/BreakDayYet.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_12.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/BreakDayYetClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_12.setIcon(new ImageIcon(new ImageIcon("target/classes/BreakDayYetGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_12.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/BreakDayYetGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Night Owl***********************************/
		btnBadge_13 = new JButton("");
		btnBadge_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[13])
					popup(badgeData.getDescriptions()[13],"Night Owl","target/classes/NightOwl.png",badgeData.getPercentages()[13],100);
				else
					popup(badgeData.getDescriptions()[13],"Night Owl","target/classes/NightOwlGrey.png",badgeData.getPercentages()[13],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_13);
		btnBadge_13.setOpaque(false);
		btnBadge_13.setFocusPainted(false);
		btnBadge_13.setContentAreaFilled(false);
		btnBadge_13.setBorderPainted(false);
		if(badgeData.getBadges()[13]){
			btnBadge_13.setIcon(new ImageIcon(new ImageIcon("target/classes/NightOwl.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_13.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/NightOwlClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_13.setIcon(new ImageIcon(new ImageIcon("target/classes/NightOwlGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_13.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/NightOwlGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Early Bird***********************************/
		btnBadge_14 = new JButton("");
		btnBadge_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[14])
					popup(badgeData.getDescriptions()[14],"Early Bird","target/classes/EarlyBird.png",badgeData.getPercentages()[14],100);
				else
					popup(badgeData.getDescriptions()[14],"Early Bird","target/classes/EarlyBirdGrey.png",badgeData.getPercentages()[14],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_14);
		btnBadge_14.setOpaque(false);
		btnBadge_14.setFocusPainted(false);
		btnBadge_14.setContentAreaFilled(false);
		btnBadge_14.setBorderPainted(false);
		if(badgeData.getBadges()[14]){
			btnBadge_14.setIcon(new ImageIcon(new ImageIcon("target/classes/EarlyBird.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_14.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/EarlyBirdClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_14.setIcon(new ImageIcon(new ImageIcon("target/classes/EarlyBirdGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_14.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/EarlyBirdGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Triple Threat***********************************/
		btnBadge_15 = new JButton("");
		btnBadge_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[15])
					popup(badgeData.getDescriptions()[15],"Triple Threat","target/classes/TripleThreat.png",badgeData.getPercentages()[15],100);
				else
					popup(badgeData.getDescriptions()[15],"Triple Threat","target/classes/TripleThreatGrey.png",badgeData.getPercentages()[15],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_15);
		btnBadge_15.setOpaque(false);
		btnBadge_15.setFocusPainted(false);
		btnBadge_15.setContentAreaFilled(false);
		btnBadge_15.setBorderPainted(false);
		if(badgeData.getBadges()[15]){
			btnBadge_15.setIcon(new ImageIcon(new ImageIcon("target/classes/TripleThreat.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_15.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/TripleThreatClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_15.setIcon(new ImageIcon(new ImageIcon("target/classes/TripleThreatGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_15.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/TripleThreatGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Healthy Heart***********************************/
		btnBadge_16 = new JButton("");
		btnBadge_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[16])
					popup(badgeData.getDescriptions()[16],"Healthy Heart","target/classes/HealthyHeart.png",badgeData.getPercentages()[16],100);
				else
					popup(badgeData.getDescriptions()[16],"Healthy Heart","target/classes/HealthyHeartGrey.png",badgeData.getPercentages()[16],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_16);
		btnBadge_16.setOpaque(false);
		btnBadge_16.setFocusPainted(false);
		btnBadge_16.setContentAreaFilled(false);
		btnBadge_16.setBorderPainted(false);
		if(badgeData.getBadges()[16]){
			btnBadge_16.setIcon(new ImageIcon(new ImageIcon("target/classes/HealthyHeart.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_16.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/HealthyHeartClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_16.setIcon(new ImageIcon(new ImageIcon("target/classes/HealthyHeartGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_16.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/HealthyHeartGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Nutty***********************************/
		btnBadge_17 = new JButton("");
		btnBadge_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[17])
					popup(badgeData.getDescriptions()[17],"Nutty","target/classes/Nutty.png",badgeData.getPercentages()[17],100);
				else
					popup(badgeData.getDescriptions()[17],"Nutty","target/classes/NuttyGrey.png",badgeData.getPercentages()[17],100);
				popupPanel.setVisible(true);	
				}
		});

		badgePanel.add(btnBadge_17);
		btnBadge_17.setOpaque(false);
		btnBadge_17.setFocusPainted(false);
		btnBadge_17.setContentAreaFilled(false);
		btnBadge_17.setBorderPainted(false);
		if(badgeData.getBadges()[17]){
			btnBadge_17.setIcon(new ImageIcon(new ImageIcon("target/classes/Nutty.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_17.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/NuttyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_17.setIcon(new ImageIcon(new ImageIcon("target/classes/NuttyGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_17.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/NuttyGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Burned Days***********************************/
		btnBadge_18 = new JButton("");
		btnBadge_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[18])
					popup(badgeData.getDescriptions()[18],"Burned Days","target/classes/BurnedDays.png",badgeData.getPercentages()[18],100);
				else
					popup(badgeData.getDescriptions()[18],"Burned Days","target/classes/BurnedDaysGrey.png",badgeData.getPercentages()[18],100);
				popupPanel.setVisible(true);				
				}
		});

		badgePanel.add(btnBadge_18);
		btnBadge_18.setOpaque(false);
		btnBadge_18.setBorderPainted(false);
		btnBadge_18.setFocusPainted(false);
		btnBadge_18.setContentAreaFilled(false);
		if(badgeData.getBadges()[18]){
			btnBadge_18.setIcon(new ImageIcon(new ImageIcon("target/classes/BurnedDays.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_18.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/BurnedDaysClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_18.setIcon(new ImageIcon(new ImageIcon("target/classes/BurnedDaysGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_18.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/BurnedDaysGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Resolution***********************************/
		btnBadge_19 = new JButton("");
		btnBadge_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[19])
					popup(badgeData.getDescriptions()[19],"Resolution","target/classes/Resolution.png",badgeData.getPercentages()[19],100);
				else
					popup(badgeData.getDescriptions()[19],"Resolution","target/classes/ResolutionGrey.png",badgeData.getPercentages()[19],100);
				popupPanel.setVisible(true);			}
		});

		badgePanel.add(btnBadge_19);
		btnBadge_19.setBorderPainted(false);
		btnBadge_19.setOpaque(false);
		btnBadge_19.setFocusPainted(false);
		btnBadge_19.setContentAreaFilled(false);
		if(badgeData.getBadges()[19]){
			btnBadge_19.setIcon(new ImageIcon(new ImageIcon("target/classes/Resolution.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_19.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/ResolutionClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_19.setIcon(new ImageIcon(new ImageIcon("target/classes/ResolutionGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_19.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/ResolutionGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}


		/**************************************Completionist***********************************/
		btnBadge_20 = new JButton("");
		btnBadge_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(badgeData.getBadges()[20])
					popup(badgeData.getDescriptions()[20],"Completionist","target/classes/Completionist.png",badgeData.getPercentages()[20],100);
				else
					popup(badgeData.getDescriptions()[20],"Completionist","target/classes/CompletionistGrey.png",badgeData.getPercentages()[20],100);
				popupPanel.setVisible(true);
			}
		});

		badgePanel.add((btnBadge_20));
		btnBadge_20.setBorderPainted(false);
		btnBadge_20.setOpaque(false);
		btnBadge_20.setFocusPainted(false);
		btnBadge_20.setContentAreaFilled(false);
		if(badgeData.getBadges()[20]){
			btnBadge_20.setIcon(new ImageIcon(new ImageIcon("target/classes/Completionist.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_20.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/CompletionistClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		else{
			btnBadge_20.setIcon(new ImageIcon(new ImageIcon("target/classes/CompletionistGrey.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			btnBadge_20.setPressedIcon(new ImageIcon(new ImageIcon("target/classes/CompletionistGreyClicked.png")
					.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}		


		/**************************************Background***********************************/
		badges.add(backPicPanel);
		badgePanel.setOpaque(false);
		JLabel lblBackpic = new JLabel("");
	}


	/**
	 * Creates panel to display badge data
	 *@param description    String of the badge description
	 *@param title          String of the badge title
	 *@param picture        String of the location of picture to display
	 *@param progress       double value of percentage completion of badge
	 *@param Max            int value of the progress bar max (100)
	 */
	private static void popup(String description, String title, String picture, double progress, int max){
			/**********************Initialize PopUp Window***************************/
			if(popupPanel!=null)
				popupPanel.dispose();
			popupPanel = new JFrame();
			popupPanel.setBounds(485, 150, 305, 450);
			popupPanel.setLocationRelativeTo(frame);
			popupPanel.setResizable(false);
			
			/************************Display Background in PopUp Window***************/
			JPanel background = new JPanel();
			background.setBounds(0, 0, 305, 450);
			JLabel backPic = new JLabel();
			backPic.setBounds(0,0,300,425);
			backPic.setIcon(new ImageIcon(new ImageIcon("target/classes/blue sky 3.jpg")
					.getImage().getScaledInstance(1200, (int)(1200/1.6), Image.SCALE_SMOOTH)));
			backPic.setHorizontalAlignment(SwingConstants.CENTER);
			backPic.setVerticalAlignment(SwingConstants.CENTER);
			background.add(backPic);
			JPanel data = new JPanel();
			data.setBounds(5, 5, 295, 440);
			
			/************************Display Badge in PopUp Window***********************/
			JLabel pic = new JLabel("");
			pic.setBounds(0,0,295,290);
			ImageIcon icon = new ImageIcon(new ImageIcon(picture)
					.getImage().getScaledInstance(290, 290, Image.SCALE_SMOOTH));
			pic.setIcon(icon);
			data.add(pic);
			
			
			JPanel jpanel = new JPanel();
			jpanel.setBounds(5, 300, 295, 150);
			data.add(new AlphaContainer(jpanel));
			jpanel.setBackground(new Color(0,0,0,50));
			jpanel.setOpaque(true);
			jpanel.setPreferredSize(new Dimension(295,150));

			/**********************Display Title for PopUp Window*************************/
			JLabel t = new JLabel(title);
			t.setFont(new Font("SansSerif",Font.BOLD,23));
			t.setForeground(Color.WHITE);
			t.setBorder(new LineBorder(Color.BLACK));
			//t.setBounds(5,300,295,10);
			t.setBounds(5,5, 285, 10);
			t.setHorizontalAlignment(SwingConstants.CENTER);
			t.setVerticalAlignment(SwingConstants.CENTER);
			jpanel.add(t);
			t.setOpaque(false);
			t.setBackground(new Color(0,0,0,50));

			/**********************Display Data for PopUp Window******************************/
			JTextArea desc = new JTextArea();
			//desc.setBounds(5, 390, 295, 40);
			desc.setBounds(5,15,285,40);
			Border b = BorderFactory.createLineBorder(Color.BLACK,1);
			desc.setBorder(b);
			desc.setWrapStyleWord(true);
			desc.setFont(new Font("SansSerif",Font.BOLD,16));
			desc.setForeground(Color.WHITE);
			desc.setEditable(false);
			desc.setText(description);
			desc.setLineWrap(true);
			desc.setOpaque(false);
			//desc.setBackground(new Color(0,0,0,15));
			jpanel.add(desc);
			data.setOpaque(false);
			//data.setBackground(new Color(0,0,0,50));
			

			/**************************Display Progress Bar in PopUp Window***********************/
			JProgressBar prog = new JProgressBar();
			prog.setBounds(5,430,295,20);
			prog.setBounds(5, 60, 285, 20);
			prog.setOpaque(false);
			//prog.setBackground(new Color(0,0,0,50));
			prog.setForeground(Color.WHITE);
			Dimension pref = new Dimension();
			pref.width = 290;
			pref.height = 20;
			prog.setPreferredSize(pref);
			prog.setMaximum(max);
			prog.setStringPainted(true);
			jpanel.add(prog);
			prog.setValue((int)progress);
			popupPanel.add(data);
			popupPanel.add(background);
	}
}
