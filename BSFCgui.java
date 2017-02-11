import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
import java.lang.reflect.Array;

import javax.swing.*;

public class BSFCgui implements ActionListener {
	// data fields
	private JFrame frame;
	private JLabel speedLabel;
	private static String speedLabelText = "Enter Average Speed (MPH): ";
	private JLabel distanceLabel;
	private static String distanceLabelText = "Enter Distance travelled (mi): ";
	private JLabel rpmLabel;
	private static String rpmLabelText = "Enter vehicle RPM: ";
	private JLabel bsfcLabel;
	private static String bsfcLabelText = "Enter BSFC value (g/kwHr): ";
	private JLabel torqueLabel;
	private static String torqueLabelText = "Enter torque (Nm): ";
	private JLabel massLabel;
	private static String massLabelText = "Mass of fuel consumed (kg): ";
	private JLabel mpgLabel;
	private static String mpgLabelText = "MPG for this trip: ";
	private JLabel volumeLabel;
	private static String volumeLabelText = "Volume of fuel consumed (gallons): ";
	public JFormattedTextField speedField;
	public JFormattedTextField distanceField;
	public JFormattedTextField rpmField;
	public JFormattedTextField bsfcField;
	public JFormattedTextField torqueField;
	public JFormattedTextField massField;
	public JFormattedTextField mpgField;
	public JFormattedTextField volumeField;
	private NumberFormat speedFormat;
	private NumberFormat rpmFormat;
	private NumberFormat bsfcFormat;
	private NumberFormat torqueFormat;
	private NumberFormat massFormat;
	private NumberFormat distanceFormat;
	private NumberFormat mpgFormat;
	private NumberFormat volumeFormat;
	private JButton calcButton = new JButton("Calculate");
	private JButton clearButton = new JButton("Clear Fields");
	private Map<Integer, Integer> rpmMap;

	/*
	 * constructor
	 */
	public BSFCgui() throws FileNotFoundException {
		setUpFrame();
		createLabels();
		pairLabelsAndFields();
		setUpFormats();
		setUpFields();
		addPanelToFrame();
		addButtonsToFrame();
		displayFrame();
		addActionListeners();
	}

	/*
	 * next 3 methods contain and search arrays for RPM, Torque, and BSFC
	 */
	private void rpmArray() {		//146 total items
		int[] rpmArray = { 1703, 2059, 1668, 2079, 1970, 1674, 2783, 1761, 2762, 2270, 1527, 1193, 2449, 1658, 2433,
				1600, 1812, 1455, 1933, 1961, 1386, 1610, 1312, 2106, 1608, 1372, 1143, 2033, 1109, 1121, 1512, 1130,
				1487, 1836, 1229, 1512, 1639, 819, 1639, 1184, 1701, 1635, 1008, 1936, 1576, 2197, 1669, 673, 995, 965,
				1500, 1584, 1980, 1607, 1687, 977, 755, 1416, 2530, 2145, 1475, 1708, 1475, 1123, 1757, 2334, 1752, 737,
				1597, 1514, 1639, 731, 702, 1654, 1660, 1654, 1705, 1591, 1591, 1438, 1614, 1439, 1902, 2519, 1605,
				1656, 1665, 1545, 1500, 1465, 1714, 1660, 2031, 1478, 2389, 2149, 1704, 2265, 1640, 2509, 2101, 1838,
				2530, 2117, 2550, 1737, 1886, 1984, 2275, 1666, 1700, 2105, 1300, 1496, 1830, 1656, 2176, 2355, 2134,
				2090, 2061, 2076, 2507, 2076, 1724, 694, 2061, 2176, 700, 1692, 2246, 2502, 2500, 2001, 1594, 1760,
				1890, 1555, 1563, 1725, 1948, 1701, 2431, 1625, 1867, 1972 };
		// search algorithm
	}

	private void torqueArray() { 	//146 total items
		double[] torqueArray = { 13.34124863, 13.34124863, 13.34124863, 13.34124863, 14.45301935, 15.56479007,
				15.56479007, 15.56479007, 15.56479007, 16.67656079, 17.7883315, 17.7883315, 17.7883315, 18.90010222,
				18.90010222, 18.90010222, 18.90010222, 18.90010222, 20.01187294, 20.01187294, 20.01187294, 21.12364366,
				22.23541438, 22.23541438, 22.23541438, 23.3471851, 23.3471851, 23.3471851, 23.3471851, 23.3471851,
				23.3471851, 23.3471851, 24.45895582, 24.45895582, 24.45895582, 24.45895582, 25.57072654, 25.57072654,
				25.57072654, 25.57072654, 26.68249726, 26.68249726, 26.68249726, 26.68249726, 27.79426798, 27.79426798,
				27.79426798, 27.79426798, 28.90603869, 28.90603869, 28.90603869, 28.90603869, 28.90603869, 28.90603869,
				30.01780941, 30.01780941, 30.01780941, 30.01780941, 30.01780941, 30.01780941, 31.12958013, 31.12958013,
				31.12958013, 31.12958013, 31.12958013, 31.12958013, 32.24135085, 32.24135085, 32.24135085, 33.35312157,
				33.35312157, 33.35312157, 33.35312157, 34.46489229, 34.46489229, 34.46489229, 35.57666301, 35.57666301,
				36.68843373, 36.68843373, 36.68843373, 36.68843373, 36.68843373, 36.68843373, 37.80020445, 37.80020445,
				37.80020445, 38.91197517, 38.91197517, 40.02374588, 40.02374588, 40.02374588, 40.02374588, 41.1355166,
				41.1355166, 42.24728732, 43.35905804, 43.35905804, 44.47082876, 44.47082876, 45.58259948, 45.58259948,
				46.6943702, 51.14145307, 51.14145307, 52.25322379, 54.47676523, 55.58853595, 55.58853595, 55.58853595,
				55.58853595, 56.70030667, 57.81207739, 57.81207739, 57.81207739, 58.92384811, 60.03561883, 61.14738955,
				62.25916026, 71.15332602, 83.38280393, 83.38280393, 92.27696968, 94.50051112, 94.50051112, 96.72405255,
				97.83582327, 100.0593647, 100.0593647, 101.1711354, 102.2829061, 102.2829061, 102.2829061, 103.3946769,
				104.5064476, 104.5064476, 104.5064476, 105.6182183, 106.729989, 106.729989, 106.729989, 106.729989,
				108.9535305, 108.9535305, 110.0653012, 110.0653012 };
		// search algorithm
	}

	private void bsfcArray() {		//146 total items
		double[] bsfcArray = {};
		// search algorithm
	}

	/*
	 * sets up the number formats for each entry/display field
	 */
	private void setUpFormats() {
		speedFormat = NumberFormat.getNumberInstance();
		rpmFormat = NumberFormat.getNumberInstance();
		bsfcFormat = NumberFormat.getNumberInstance();
		torqueFormat = NumberFormat.getNumberInstance();
		massFormat = NumberFormat.getNumberInstance();
		distanceFormat = NumberFormat.getNumberInstance();
		mpgFormat = NumberFormat.getNumberInstance();
		volumeFormat = NumberFormat.getNumberInstance();
	}

	/*
	 * adds and orients the labels and text fields within the frame
	 */
	private void addPanelToFrame() {
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(speedLabel);
		labelPane.add(rpmLabel);
		labelPane.add(torqueLabel);
		labelPane.add(bsfcLabel);
		labelPane.add(distanceLabel);
		labelPane.add(massLabel);
		labelPane.add(volumeLabel);
		labelPane.add(mpgLabel);
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(speedField);
		fieldPane.add(rpmField);
		fieldPane.add(torqueField);
		fieldPane.add(bsfcField);
		fieldPane.add(distanceField);
		fieldPane.add(massField);
		fieldPane.add(volumeField);
		fieldPane.add(mpgField);
		frame.add(labelPane, BorderLayout.CENTER);
		frame.add(fieldPane, BorderLayout.LINE_END);
	}

	/*
	 * adds buttons to frame
	 */
	private void addButtonsToFrame() {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(calcButton);
		buttonPanel.add(clearButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		JLabel spacing = new JLabel("     ");
		frame.add(spacing, BorderLayout.EAST);
		frame.add(spacing, BorderLayout.WEST);
	}

	/*
	 * creates labels
	 */
	private void createLabels() {
		speedLabel = new JLabel(speedLabelText);
		distanceLabel = new JLabel(distanceLabelText);
		rpmLabel = new JLabel(rpmLabelText);
		bsfcLabel = new JLabel(bsfcLabelText);
		torqueLabel = new JLabel(torqueLabelText);
		massLabel = new JLabel(massLabelText);
		volumeLabel = new JLabel(volumeLabelText);
		mpgLabel = new JLabel(mpgLabelText);
	}

	/*
	 * initializes labels and sets their text
	 */
	private void pairLabelsAndFields() {
		speedLabel.setLabelFor(speedField);
		distanceLabel.setLabelFor(distanceField);
		rpmLabel.setLabelFor(rpmField);
		bsfcLabel.setLabelFor(bsfcField);
		torqueLabel.setLabelFor(torqueField);
		massLabel.setLabelFor(massField);
		volumeLabel.setLabelFor(volumeField);
		mpgLabel.setLabelFor(mpgField);
	}

	/*
	 * sets layout of frame
	 */
	@SuppressWarnings("static-access")
	public void setUpFrame() {
		frame = new JFrame("BSFC Calculator");
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.getRootPane().setDefaultButton(calcButton);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	/*
	 * displays frame
	 */
	public void displayFrame() {
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/*
	 * sets the formatted text fields
	 */
	public void setUpFields() {
		speedField = new JFormattedTextField(speedFormat);
		speedField.setColumns(10);
		distanceField = new JFormattedTextField(distanceFormat);
		distanceField.setColumns(10);
		rpmField = new JFormattedTextField(rpmFormat);
		rpmField.setColumns(10);
		torqueField = new JFormattedTextField(torqueFormat);
		torqueField.setColumns(10);
		bsfcField = new JFormattedTextField(bsfcFormat);
		bsfcField.setColumns(10);
		massField = new JFormattedTextField(massFormat);
		massField.setColumns(10);
		massField.setEditable(false);
		massField.setForeground(Color.RED);
		volumeField = new JFormattedTextField(volumeFormat);
		volumeField.setColumns(10);
		volumeField.setEditable(false);
		volumeField.setForeground(Color.RED);
		mpgField = new JFormattedTextField(mpgFormat);
		mpgField.setColumns(10);
		mpgField.setEditable(false);
		mpgField.setForeground(Color.RED);
	}

	/*
	 * adds action listener to calc & clear buttons
	 */
	public void addActionListeners() {
		calcButton.addActionListener(this);
		clearButton.addActionListener(this);
	}

	/*
	 * detects the click on calcButton and calculates mass fuel consumed
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(calcButton)) {
			calcButtonAction();
		} else {
			try {
				frame.setVisible(false);
				clearButtonAction();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * re-launches the program with blank fields
	 */
	private void clearButtonAction() throws FileNotFoundException {
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}

	/*
	 * grabs values from text fields and calls the two calculate methods
	 */
	private void calcButtonAction() {
		/*
		 * make it so the program parses the csv file based on bsfc and rpm
		 * value
		 */
		double speed = (long) speedField.getValue();
		double distance = (long) distanceField.getValue();
		double rpm = (long) rpmField.getValue();
		double bsfc = (long) bsfcField.getValue();
		double torque = (long) torqueField.getValue();
		double massConsumed = calculateMass(speed, distance, rpm, bsfc, torque);
		double volumeConsumed = calculateVolume(massConsumed);
		double mpg = calculateMPG(massConsumed, distance);
		massField.setValue(massConsumed);
		volumeField.setValue(volumeConsumed);
		mpgField.setValue(mpg);
		speed = speed * 0.44704; // mph to m/s
		velocityLoop(distance, rpm, torque, bsfc, speed);
	}

	/*
	 * calculates volume of fuel consumed
	 */
	private double calculateVolume(double massConsumed) {
		double poundMass = massConsumed * 2.20462; // kg to lb
		double volume = poundMass / 6.94; // in gallons, 6.94 = density diesel
											// fuel (lb/gal)
		return volume;
	}

	/*
	 * calculates mpg for the trip
	 */
	private double calculateMPG(double massConsumed, double distance) {
		double mpg = 0;
		double poundMass = massConsumed * 2.20462; // kg to lb
		double volume = poundMass / 6.94; // in gallons, 6.94 = density diesel
											// fuel (lb/gal)
		mpg = distance / volume;
		return mpg;
	}

	/*
	 * calculates mass consumed and returns the value as a long
	 */
	private double calculateMass(double speed, double distance, double rpm, double bsfc, double torque) {
		double mass = 0;
		double bsfc1 = bsfc / 3600000; // converts to g/s
		double rpm1 = rpm * (2 * Math.PI / 60);
		speed = speed * 0.000277778;
		mass = (double) (bsfc1 * rpm1 * torque * distance);
		mass = (double) (mass / speed);
		mass = mass / 1000;
		return mass;
	}

	/*
	 * loop to calculate mass and velocities
	 */
	private void velocityLoop(double distance, double w, double T, double BSFC, double velocityVehicle) {
		double wcruise = 0; // get these from csv
		double Tcruise = 0; // get these from csv
		double massVehicle = 1060.045; // (kg)
		double velocityOld = 30 * 0.44704; // accelerating from 30mph
		double Dt = .1;
		double Dvelocity;
		double velocityNew;
		double dist = 0;

		System.out.println("Dist: " + dist);
		System.out.println("Velocity old: " + velocityOld);
		if (velocityVehicle > 20 && velocityVehicle < 125) {
			while (dist < distance) {
				Dvelocity = (w * T - (wcruise * Tcruise)) / (massVehicle * velocityVehicle);
				System.out.println("Velocity old: " + velocityOld);
				velocityNew = velocityOld + Dvelocity;
				velocityOld = velocityNew;
				System.out.println("D velocity: " + Dvelocity);
				System.out.println("Velocity new: " + velocityNew);
				dist = dist + velocityVehicle * Dt; // = SUM(velocity*Dt);
				System.out.println("dist: " + dist + "\n");
				w++;
				T++;
			}
		} else {
			System.out.println("Speed too low or too high.");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}
}