import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;

public class BSFCgui implements ActionListener {
	// data fields
	private JFrame frame;
	private JLabel speedLabel, distanceLabel, massLabel, mpgLabel, volumeLabel;
	private JLabel speedRangeLabel;
	private JLabel spacing;
	private static String speedRangeLabelText = "    (Whole # between 30 and 60 MPH)";
	private static String speedLabelText = "Enter Average Speed: ";
	private static String distanceLabelText = "Enter Distance travelled (mi): ";
	private static String massLabelText = "Mass of fuel consumed (kg): ";
	private static String mpgLabelText = "MPG for this trip: ";
	private static String volumeLabelText = "Volume of fuel consumed (gallons): ";
	public JFormattedTextField speedField, distanceField, massField, mpgField, volumeField;
	private NumberFormat speedFormat, massFormat, distanceFormat, mpgFormat, volumeFormat;
	private JButton calcButton = new JButton("Calculate");
	private JButton clearButton = new JButton("Clear Fields");

	private double[] bsfcArray = { 1932.476445, 1594.992209, 660.9056872, 1521.261226, 1482.622717, 1543.870664,
			538.5060152, 516.0939077, 1173.874463, 188.0786942, 154.7998661, 138.4100983, 261.4457486, 291.1977361,
			208.505942, 314.7035908, 293.0854415, 70.22112935, 285.1594852, 72.78887319, 255.3589648, 921.121357,
			264.4429473, 273.173962, 342.3346304, 244.150086, 1177.034733, 266.6490523, 440.5513508, 1314.972644,
			838.2107654, 678.5271689, 120.7800437, 79.29803116, 279.710012, 238.4147337, 333.7062057, 153.7905558,
			309.7035376, 297.8233957, 316.6775381, 346.0542332, 285.9808226, 291.1730044, 310.8419848, 396.9953136,
			144.1454501, 291.9693866, 319.1305323, 383.0558685, 284.5858242, 287.940403, 3.507899774, 314.4626775,
			234.4460514, 278.2402068, 402.263044, 298.8411414, 527.0361828, 369.2176705, 305.4711334, 309.9572376,
			332.9214856, 396.6373913, 341.9714598, 348.6949409, 314.6871073, 314.2601234, 309.7818193, 314.5635358,
			303.2279129, 291.5523331, 322.5586923, 315.751814, 234.9573586, 2558.746305, 418.0431635, 510.6226268,
			1972.57751, 367.8577462, 200.4144411, 361.953447, 266.3824909, 311.5349385, 2.787823037, 2.900359013,
			2.723902342, 170.087446, 199.9606165, 159.2726373, 209.1960286, 313.0357164, 337.1038441, 376.8120932,
			127.9603326, 465.7022618, 109.1282783, 290.2838658, 261.0353289, 637.3135453, 275.9860469, 298.7523944,
			0.6969557593, 288.1417599, 360.0490781, 288.4549088, 286.6708485, 312.9681772, 981.1239144, 3.905775914,
			133.0333098, 431.9863351, 578.5478921, 282.8235391, 155.3576403, 305.4363554, 781.8611823, 456.4035018,
			235.0504977, 489.1955478, 445.1933793, 339.4200166, 343.806749, 3.380571601, 3.319653893, 236.6540562,
			357.2653112, 480.2915996, 419.5984822, 241.9421334, 422.3337333, 278.7559509, 412.1860613, 348.7808646,
			84.49258751, 266.2313493, 329.6000079, 329.4281517, 4.232277366, 329.205822, 478.6854764, 463.7623852,
			316.2166033, 307.7263777, 179.5948322, 744.5678257 }; // g/kwHr

	private double[] speedArray = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 6, 7, 8, 10, 11, 11, 13, 17, 17, 18, 18, 19, 19, 20, 20,
			20, 20, 23, 25, 25, 27, 27, 27, 27, 27, 29, 29, 29, 29, 30, 30, 30, 31, 31, 31, 32, 32, 32, 32, 32, 32, 32,
			33, 33, 33, 33, 33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 35, 35, 35,
			35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35,
			36, 36, 36, 37, 37, 37, 37, 37, 39, 39, 39, 40, 40, 41, 42, 42, 42, 43, 43, 43, 43, 44, 45, 45, 45, 45, 47,
			47, 47, 47, 48, 50, 50, 51, 51, 53, 53, 53, 53, 53, 58 }; // MPH

	private int[] rpmArray = { 673, 755, 1757, 737, 731, 702, 694, 700, 977, 1830, 1496, 1300, 1890, 2001, 2431, 2246,
			995, 2061, 965, 1760, 1008, 1752, 1184, 2061, 2076, 2507, 1512, 1143, 1487, 1669, 1639, 1886, 1724, 1692,
			1372, 1121, 819, 1737, 1193, 1465, 1725, 1312, 1512, 1478, 1527, 1500, 1439, 1545, 1500, 2502, 1594, 1674,
			1610, 1576, 1980, 1563, 1658, 1600, 1455, 1608, 1639, 1584, 1687, 1416, 1597, 1514, 1591, 1591, 1614, 1605,
			1665, 1640, 2176, 1555, 1972, 2059, 1668, 2783, 1761, 1386, 2033, 1109, 1229, 1639, 1701, 1635, 1607, 1475,
			1708, 1475, 1123, 1654, 1660, 1654, 1705, 1656, 1714, 1660, 1704, 1984, 1666, 1656, 1701, 1625, 1867, 1130,
			2101, 1700, 1703, 1812, 1838, 2176, 2134, 1836, 1438, 1948, 1936, 1902, 1933, 1970, 1961, 2076, 2079, 2433,
			2106, 2031, 2105, 2145, 2149, 2117, 2090, 2270, 2197, 2265, 2275, 2334, 2389, 2355, 2449, 2500, 2530, 2519,
			2509, 2530, 2550, 2762 }; // RPM

	/*
	 * constructor
	 */
	public BSFCgui() {
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
	 * grab values in order to calculate
	 */
	private void valueGrabber() {
		double speed = (long) speedField.getValue();
		if (speed < 30 || speed > 60) {
			JOptionPane.showMessageDialog(null, "Invalid speed input. Program will re-launch.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			clearButtonAction();
		}
		double upper = speed + (speed * .05);
		double lower = speed - (speed * .05);
		System.out.println("Upper five %: " + upper);
		System.out.println("Lower five %: " + lower);
		int index = 0;
		int sentinel = 0;
		while (sentinel != -1) {
			if (index == speedArray.length) {
				upper = speed + (speed * .1);
				lower = speed - (speed * .1);
				System.out.println("Upper Ten %: " + upper);
				System.out.println("Lower Ten %: " + lower);
				valueGrabber();
			} else if (speedArray[index] == speed || (speedArray[index] < upper && speedArray[index] > lower)) {
				sentinel = -1;
			} else {
				index++;
			}
		}
		System.out.println("Index: " + index);
		System.out.println("Speed: " + speed);
		int rpm = rpmArray[index];
		// finds the average of torques within that array for calculations
		double torqueToUse = torqueGrabber(speed);
		System.out.println("Torque: " + torqueToUse);
		double bsfc = bsfcArray[index];
		System.out.println("BSFC: " + bsfc);
		double distance = (long) distanceField.getValue();
		double massConsumed = calculateMass(speed, distance, rpm, bsfc, torqueToUse);
		double volumeConsumed = calculateVolume(massConsumed);
		double mpg = calculateMPG(massConsumed, distance);
		System.out.println("RPM: " + rpm);
		System.out.println("Mass consumed: " + massConsumed);
		System.out.println("Volume consumed: " + volumeConsumed);
		System.out.println("MPG: " + mpg + "\n");
		massField.setValue(massConsumed);
		volumeField.setValue(volumeConsumed);
		mpgField.setValue(mpg);
		// speed = speed * 0.44704; // mph to meter/s
		// velocityLoop(distance, rpm, torque, bsfc, speed);
	}

	/*
	 * grabs the correct average torque from specified array
	 */
	private double torqueGrabber(double speed) {
		double torqueToUse = 0;
		if (speed == 30.0) {
			torqueToUse = 54.847;
		} else if (speed == 31.0) {
			torqueToUse = 28.906;
		} else if (speed == 32.0) {
			torqueToUse = 52.571;
		} else if (speed == 33.0) {
			torqueToUse = 40.024;
		} else if (speed == 34.0) {
			torqueToUse = 42.306;
		} else if (speed == 35.0) {
			torqueToUse = 39.246;
		} else if (speed == 36.0) {
			torqueToUse = 41.506;
		} else if (speed == 37.0) {
			torqueToUse = 40.024;
		} else if (speed == 38.0) {
			torqueToUse = (40.024 + 55.959) / 2;
		} else if (speed == 39.0) {
			torqueToUse = 55.959;
		} else if (speed == 40.0) {
			torqueToUse = 31.685;
		} else if (speed == 41.0) {
			torqueToUse = 20.012;
		} else if (speed == 42.0) {
			torqueToUse = 42.988;
		} else if (speed == 43.0) {
			torqueToUse = 23.625;
		} else if (speed == 44.0) {
			torqueToUse = 56.700;
		} else if (speed == 45.0) {
			torqueToUse = 48.640;
		} else if (speed == 46.0) {
			torqueToUse = (48.640 + 35.855) / 2;
		} else if (speed == 47.0) {
			torqueToUse = 35.855;
		} else if (speed == 48.0) {
			torqueToUse = 31.130;
		} else if (speed == 49.0) {
			torqueToUse = (31.130 + 51.141) / 2;
		} else if (speed == 50.0) {
			torqueToUse = 51.141;
		} else if (speed == 51.0) {
			torqueToUse = 60.036;
		} else if (speed == 52.0) {
			torqueToUse = (60.036 + 41.803) / 2;
		} else if (speed == 53.0) {
			torqueToUse = 41.803;
		} else if (speed == 54.0) {
			torqueToUse = 60.345;
		} else if (speed == 55.0) {
			torqueToUse = 60.345;
		} else if (speed == 56.0) {
			torqueToUse = 60.345;
		} else if (speed == 57.0) {
			torqueToUse = 60.345;
		} else if (speed == 58.0) {
			torqueToUse = 15.565;
		} else if (speed == 59.0) {
			torqueToUse = 60.345;
		} else if (speed == 60.0) {
			torqueToUse = 60.345;
		}
		return torqueToUse;
	}

	/*
	 * sets up the number formats for each entry/display field
	 */
	private void setUpFormats() {
		speedFormat = NumberFormat.getNumberInstance();
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
		labelPane.add(speedRangeLabel);
		labelPane.add(distanceLabel);
		labelPane.add(massLabel);
		labelPane.add(volumeLabel);
		labelPane.add(mpgLabel);
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(speedField);
		fieldPane.add(spacing);
		fieldPane.add(distanceField);
		fieldPane.add(massField);
		fieldPane.add(volumeField);
		fieldPane.add(mpgField);
		frame.add(labelPane, BorderLayout.CENTER);
		frame.add(fieldPane, BorderLayout.LINE_END);
	}

	/*
	 * adds Calc and Clear buttons to frame
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
		speedRangeLabel = new JLabel(speedRangeLabelText);
		speedRangeLabel.setForeground(Color.RED);
		spacing = new JLabel("           ");
		distanceLabel = new JLabel(distanceLabelText);
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
			valueGrabber();
		} else {
			clearButtonAction();
		}
	}

	/*
	 * re-launches the program with blank fields
	 */
	private void clearButtonAction() {
		frame.setVisible(false);
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}

	/*
	 * calculates volume of fuel consumed
	 */
	private double calculateVolume(double massConsumed) {
		double poundMass = massConsumed * 2.20462; // kg to lb
		double volume = poundMass / 6.002257; // in gallons, 6.002257 = density
												// regular
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
		speed = speed * 0.000277778; // convert mph to mi/sec
		mass = (double) (bsfc1 * rpm1 * torque * distance);
		mass = (double) (mass / speed);
		mass = mass / 1000;
		return mass;
	}

	// /*
	// * loop to calculate mass and velocities
	// */
	// private void velocityLoop(double distance, double w, double T, double
	// BSFC, double velocityVehicle) {
	// double wcruise = 0; // get these from csv
	// double Tcruise = 0; // get these from csv
	// double massVehicle = 1060.045; // (kg)
	// double velocityOld = 30 * 0.44704; // accelerating from 30mph
	// double Dt = .1;
	// double Dvelocity;
	// double velocityNew;
	// double dist = 0;
	//
	// System.out.println("Dist: " + dist);
	// System.out.println("Velocity old: " + velocityOld);
	// if (velocityVehicle > 20 && velocityVehicle < 125) {
	// while (dist < distance) {
	// Dvelocity = (w * T - (wcruise * Tcruise)) / (massVehicle *
	// velocityVehicle);
	// System.out.println("Velocity old: " + velocityOld);
	// velocityNew = velocityOld + Dvelocity;
	// velocityOld = velocityNew;
	// System.out.println("D velocity: " + Dvelocity);
	// System.out.println("Velocity new: " + velocityNew);
	// dist = dist + velocityVehicle * Dt; // = SUM(velocity*Dt);
	// System.out.println("dist: " + dist + "\n");
	// w++;
	// T++;
	// }
	// } else {
	// System.out.println("Speed too low or too high.");
	// }
	// }

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}
}