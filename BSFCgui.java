import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;

public class BSFCgui implements ActionListener {
	// data fields
	private JFrame frame;
	private JLabel speedLabel;
	private static String speedLabelText = "Enter Average Speed (MPH): ";
	private JLabel distanceLabel;
	private static String distanceLabelText = "Enter Distance travelled (mi): ";
	private JLabel massLabel;
	private static String massLabelText = "Mass of fuel consumed (kg): ";
	private JLabel mpgLabel;
	private static String mpgLabelText = "MPG for this trip: ";
	private JLabel volumeLabel;
	private static String volumeLabelText = "Volume of fuel consumed (gallons): ";
	public JFormattedTextField speedField;
	public JFormattedTextField distanceField;
	public JFormattedTextField massField;
	public JFormattedTextField mpgField;
	public JFormattedTextField volumeField;
	private NumberFormat speedFormat;
	private NumberFormat massFormat;
	private NumberFormat distanceFormat;
	private NumberFormat mpgFormat;
	private NumberFormat volumeFormat;
	private JButton calcButton = new JButton("Calculate");
	private JButton clearButton = new JButton("Clear Fields");

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
		int[] speedArray = { 37, 35, 35, 43, 42, 33, 35, 35, 58, 47, 32, 30, 51, 34, 43, 34, 37, 34, 41, 42, 35, 33, 31,
				43, 34, 29, 25, 35, 35, 29, 31, 36, 25, 39, 35, 23, 34, 29, 35, 20, 35, 35, 19, 40, 33, 47, 27, 1, 17,
				18, 32, 34, 33, 35, 34, 21, 34, 53, 45, 35, 35, 35, 35, 1, 48, 19, 1, 34, 34, 27, 1, 1, 35, 35, 35, 35,
				34, 34, 39, 34, 32, 40, 53, 34, 35, 34, 32, 32, 30, 35, 35, 43, 31, 50, 45, 35, 47, 34, 53, 36, 37, 53,
				45, 53, 29, 27, 35, 47, 35, 36, 44, 8, 7, 6, 35, 37, 50, 37, 45, 20, 20, 20, 42, 27, 1, 17, 34, 1, 27,
				13, 32, 51, 11, 32, 18, 10, 34, 33, 30, 39, 35, 11, 35, 35, 34 };
		double speed = (long) speedField.getValue();
		int index = 0;
		int sentinel = 0;
		while (sentinel != -1) {
			if (index == speedArray.length) {
				sentinel = -1;
				JOptionPane.showMessageDialog(null, "Speed not found in data. Program will re-launch.", "ERROR",
						JOptionPane.WARNING_MESSAGE);
				clearButtonAction();
			} else if (speedArray[index] == speed) {
				sentinel = -1;
			} else {
				index++;
			}
		}

		System.out.println("Index: " + index);
		System.out.println("Speed: " + speed);
		double upper = speed + (speed * .05);
		double lower = speed - (speed * .05);
		System.out.println("Upper: " + upper);
		System.out.println("Lower: " + lower);

		int[] rpmArray = { 1703, 2059, 1668, 2079, 1970, 1674, 2783, 1761, 2762, 2270, 1527, 1193, 2449, 1658, 2433,
				1600, 1812, 1455, 1933, 1961, 1386, 1610, 1312, 2106, 1608, 1372, 1143, 2033, 1109, 1121, 1512, 1130,
				1487, 1836, 1229, 1512, 1639, 819, 1639, 1184, 1701, 1635, 1008, 1936, 1576, 2197, 1669, 673, 995, 965,
				1500, 1584, 1980, 1607, 1687, 977, 755, 1416, 2530, 2145, 1475, 1708, 1475, 1123, 1757, 2334, 1752, 737,
				1597, 1514, 1639, 731, 702, 1654, 1660, 1654, 1705, 1591, 1591, 1438, 1614, 1439, 1902, 2519, 1605,
				1656, 1665, 1545, 1500, 1465, 1714, 1660, 2031, 1478, 2389, 2149, 1704, 2265, 1640, 2509, 2101, 1838,
				2530, 2117, 2550, 1737, 1886, 1984, 2275, 1666, 1700, 2105, 1300, 1496, 1830, 1656, 2176, 2355, 2134,
				2090, 2061, 2076, 2507, 2076, 1724, 694, 2061, 2176, 700, 1692, 2246, 2502, 2500, 2001, 1594, 1760,
				1890, 1555, 1563, 1725, 1948, 1701, 2431, 1625, 1867, 1972 };
		int rpm = rpmArray[index];

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
		double torque = torqueArray[index];
		System.out.println("Torque: " + torque);

		double[] bsfcArray = { 981.1239144, 2558.746305, 418.0431635, 343.806749, 489.1955478, 287.940403, 510.6226268,
				1972.57751, 744.5678257, 278.7559509, 310.8419848, 309.7035376, 4.232277366, 402.263044, 3.380571601,
				298.8411414, 3.905775914, 527.0361828, 235.0504977, 445.1933793, 367.8577462, 3.507899774, 346.0542332,
				3.319653893, 369.2176705, 279.710012, 266.6490523, 200.4144411, 361.953447, 238.4147337, 285.9808226,
				288.4549088, 440.5513508, 282.8235391, 266.3824909, 1177.034733, 305.4711334, 333.7062057, 311.5349385,
				264.4429473, 2.787823037, 2.900359013, 255.3589648, 781.8611823, 314.4626775, 412.1860613, 1314.972644,
				1932.476445, 293.0854415, 285.1594852, 396.9953136, 309.9572376, 234.4460514, 2.723902342, 332.9214856,
				1173.874463, 1594.992209, 396.6373913, 478.6854764, 480.2915996, 170.087446, 199.9606165, 159.2726373,
				209.1960286, 660.9056872, 266.2313493, 921.121357, 1521.261226, 341.9714598, 348.6949409, 838.2107654,
				1482.622717, 1543.870664, 313.0357164, 337.1038441, 376.8120932, 127.9603326, 314.6871073, 314.2601234,
				155.3576403, 309.7818193, 144.1454501, 456.4035018, 463.7623852, 314.5635358, 465.7022618, 303.2279129,
				291.9693866, 319.1305323, 297.8233957, 109.1282783, 290.2838658, 236.6540562, 291.1730044, 329.6000079,
				419.5984822, 261.0353289, 348.7808646, 291.5523331, 316.2166033, 286.6708485, 133.0333098, 307.7263777,
				241.9421334, 179.5948322, 153.7905558, 678.5271689, 637.3135453, 84.49258751, 275.9860469, 312.9681772,
				357.2653112, 138.4100983, 154.7998661, 188.0786942, 298.7523944, 431.9863351, 329.4281517, 578.5478921,
				422.3337333, 273.173962, 342.3346304, 244.150086, 339.4200166, 120.7800437, 538.5060152, 70.22112935,
				322.5586923, 516.0939077, 79.29803116, 314.7035908, 383.0558685, 329.205822, 291.1977361, 284.5858242,
				72.78887319, 261.4457486, 315.751814, 278.2402068, 316.6775381, 305.4363554, 0.6969557593, 208.505942,
				288.1417599, 360.0490781, 234.9573586 };
		double bsfc = bsfcArray[index];
		System.out.println("BSFC: " + bsfc);
		double distance = (long) distanceField.getValue();
		double massConsumed = calculateMass(speed, distance, rpm, bsfc, torque);
		double volumeConsumed = calculateVolume(massConsumed);
		double mpg = calculateMPG(massConsumed, distance);
		System.out.println("RPM: " + rpm);
		System.out.println("Mass consumed: " + massConsumed);
		System.out.println("Volume consumed: " + volumeConsumed);
		System.out.println("MPG: " + mpg);
		massField.setValue(massConsumed);
		volumeField.setValue(volumeConsumed);
		mpgField.setValue(mpg);

		// speed = speed * 0.44704; // mph to m/s
		// velocityLoop(distance, rpm, torque, bsfc, speed);
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
		labelPane.add(distanceLabel);
		labelPane.add(massLabel);
		labelPane.add(volumeLabel);
		labelPane.add(mpgLabel);
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(speedField);
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
		BSFCgui gui = new BSFCgui();
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
		BSFCgui gui = new BSFCgui();
	}
}