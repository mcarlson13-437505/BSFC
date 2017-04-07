import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;

public class BSFCgui implements ActionListener {
	// data fields
	private JFrame frame;
	private JLabel initialSpeedLabel, finalSpeedLabel, massLabel, mpgLabel, volumeLabel;
	private JLabel speedRangeLabel;
	private JLabel spacing;
	private static String speedRangeLabelText = "    (Whole #'s between 30 and 60 MPH)";
	private static String initialSpeedLabelText = "Enter Initial Speed (MPH): ";
	private static String finalSpeedLabelText = "Enter Final Speed (MPH): ";
	private static String massLabelText = "Mass of fuel consumed (g): ";
	private static String mpgLabelText = "MPG for this trip: ";
	private static String volumeLabelText = "Volume of fuel consumed (gallons): ";
	public JFormattedTextField initialSpeedField, finalSpeedField, massField, mpgField, volumeField;
	private NumberFormat initialSpeedFormat, finalSpeedFormat, massFormat, mpgFormat, volumeFormat;
	private JButton calcButton = new JButton("Calculate");
	private JButton clearButton = new JButton("Clear Fields");

	int w1 = 1500, w2 = 1504, w3 = 1635, w4 = 1703, w5 = 1980, w6 = 2079, w7 = 2145, w8 = 2449, w9 = 2762;
	double t1 = 33.35312157, t2 = 28.90603869, t3 = 26.68249726, t4 = 13.34124863, t5 = 31.12958013, t6 = 31.12958013,
			t7 = 40.02374588, t8 = 15.56479007, t9 = 36.68843373;

	private double[] t30 = { 17.7883315, 40.02374588, 106.729989 }, t31 = { 22.23541438, 23.3471851, 41.1355166 },
			t32 = { 17.7883315, 28.90603869, 36.68843373, 38.91197517, 38.91197517, 102.2829061, 104.5064476 },
			t33 = { 15.56479007, 21.12364366, 27.79426798, 28.90603869, 106.729989 },
			t34 = { 18.90010222, 18.90010222, 18.90010222, 22.23541438, 25.57072654, 28.90603869, 30.01780941,
					30.01780941, 32.24135085, 33.35312157, 35.57666301, 36.68843373, 36.68843373, 37.80020445,
					37.80020445, 44.47082876, 100.0593647, 105.6182183, 110.0653012 },
			t35 = { 13.34124863, 13.34124863, 15.56479007, 15.56479007, 20.01187294, 23.3471851, 23.3471851,
					24.45895582, 25.57072654, 26.68249726, 26.68249726, 28.90603869, 31.12958013, 31.12958013,
					31.12958013, 31.12958013, 34.46489229, 34.46489229, 34.46489229, 35.57666301, 37.80020445,
					40.02374588, 40.02374588, 43.35905804, 55.58853595, 55.58853595, 58.92384811, 106.729989,
					108.9535305, 110.0653012 },
			t36 = { 23.3471851, 45.58259948, 55.58853595 },
			t37 = { 13.34124863, 18.90010222, 45.58259948, 60.03561883, 62.25916026 },
			t39 = { 24.45895582, 36.68843373, 106.729989 }, t40 = { 26.68249726, 36.68843373 }, t41 = { 20.01187294 },
			t42 = { 14.45301935, 20.01187294, 94.50051112 },
			t43 = { 13.34124863, 18.90010222, 22.23541438, 40.02374588 }, t44 = { 56.70030667 },
			t45 = { 30.01780941, 42.24728732, 51.14145307, 71.15332602 },
			t47 = { 16.67656079, 27.79426798, 43.35905804, 55.58853595 }, t48 = { 31.12958013 },
			t50 = { 41.1355166, 61.14738955 }, t51 = { 17.7883315, 102.2829061 },
			t53 = { 30.01780941, 36.68843373, 44.47082876, 46.6943702, 51.14145307 }, t58 = { 15.56479007 };

	private double[] b30 = { 309.7035376, 297.8233957, 316.6775381 }, b31 = { 346.0542332, 285.9808226, 291.1730044 },
			b32 = { 310.8419848, 396.9953136, 144.1454501, 291.9693866, 319.1305323, 383.0558685, 284.5858242 },
			b33 = { 287.940403, 3.507899774, 314.4626775, 234.4460514, 278.2402068 },
			b34 = { 402.263044, 298.8411414, 527.0361828, 369.2176705, 305.4711334, 309.9572376, 332.9214856,
					396.6373913, 341.9714598, 348.6949409, 314.6871073, 314.2601234, 309.7818193, 314.5635358,
					303.2279129, 291.5523331, 322.5586923, 315.751814, 234.9573586 },
			b35 = { 418.0431635, 510.6226268, 367.8577462, 200.4144411, 361.953447, 266.3824909, 311.5349385,
					170.087446, 199.9606165, 159.2726373, 209.1960286, 313.0357164, 337.1038441, 376.8120932,
					127.9603326, 465.7022618, 109.1282783, 290.2838658, 261.0353289, 637.3135453, 275.9860469,
					298.7523944, 288.1417599, 360.0490781 },
			b36 = { 288.4549088, 286.6708485, 312.9681772 }, b37 = { 133.0333098, 431.9863351, 578.5478921 },
			b39 = { 282.8235391, 155.3576403, 305.4363554 }, b40 = { 781.8611823, 456.4035018 }, b41 = { 235.0504977 },
			b42 = { 489.1955478, 445.1933793, 339.4200166 }, b43 = { 343.806749, 236.6540562 }, b44 = { 357.2653112 },
			b45 = { 480.2915996, 419.5984822, 241.9421334, 422.3337333 },
			b47 = { 278.7559509, 412.1860613, 348.7808646 }, b48 = { 266.2313493 }, b50 = { 329.6000079, 329.4281517 },
			b51 = { 329.205822 }, b53 = { 478.6854764, 463.7623852, 316.2166033, 307.7263777, 179.5948322 },
			b58 = { 744.5678257 };

	@SuppressWarnings("rawtypes")
	private JComboBox torqueDropDown;
	@SuppressWarnings("unused")
	private String accelType;
	private JLabel torqueLabel;

	/*
	 * constructor
	 */
	public BSFCgui() {
		setUpFrame();
		createLabels();
		pairLabelsAndFields();
		setUpFormats();
		setUpFields();
		createTorqueDropDown();
		addPanelToFrame();
		addButtonsToFrame();
		displayFrame();
		addActionListeners();
	}

	/*
	 * grab values in order to calculate
	 */
	private void valueGrabber() {
		double initialSpeed = (long) initialSpeedField.getValue();
		double finalSpeed = (long) finalSpeedField.getValue();
		if (initialSpeed < 30 || finalSpeed > 60 || initialSpeed > finalSpeed) {
			JOptionPane.showMessageDialog(null, "Invalid speed input. Program will re-launch.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			clearButtonAction();
		}
		double torqueToUse = grabTorque(initialSpeed);
		double bsfcToUse = grabBsfc(initialSpeed);
		velocityLoop(torqueToUse, bsfcToUse, initialSpeed, finalSpeed);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createTorqueDropDown() {
		String[] torqueDropStrings = { "          Slow", "          Moderate", "          Quick" };
		torqueDropDown = new JComboBox(torqueDropStrings);
	}

	/*
	 * sets up the number formats for each entry/display field
	 */
	private void setUpFormats() {
		initialSpeedFormat = NumberFormat.getNumberInstance();
		finalSpeedFormat = NumberFormat.getNumberInstance();
		massFormat = NumberFormat.getNumberInstance();
		mpgFormat = NumberFormat.getNumberInstance();
		volumeFormat = NumberFormat.getNumberInstance();
	}

	/*
	 * adds and orients the labels and text fields within the frame
	 */
	private void addPanelToFrame() {
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(initialSpeedLabel);
		labelPane.add(finalSpeedLabel);
		labelPane.add(speedRangeLabel);
		labelPane.add(torqueLabel);
		labelPane.add(massLabel);
		labelPane.add(volumeLabel);
		labelPane.add(mpgLabel);
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(initialSpeedField);
		fieldPane.add(finalSpeedField);
		fieldPane.add(spacing);
		fieldPane.add(torqueDropDown);
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
		JLabel spacing = new JLabel("    ");
		frame.add(spacing, BorderLayout.WEST);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	/*
	 * creates labels
	 */
	private void createLabels() {
		initialSpeedLabel = new JLabel(initialSpeedLabelText);
		finalSpeedLabel = new JLabel(finalSpeedLabelText);
		speedRangeLabel = new JLabel(speedRangeLabelText);
		speedRangeLabel.setForeground(Color.RED);
		spacing = new JLabel("           ");
		torqueLabel = new JLabel("Select acceleration style: ");
		massLabel = new JLabel(massLabelText);
		volumeLabel = new JLabel(volumeLabelText);
		mpgLabel = new JLabel(mpgLabelText);
	}

	/*
	 * initializes labels and sets their text
	 */
	private void pairLabelsAndFields() {
		initialSpeedLabel.setLabelFor(initialSpeedField);
		finalSpeedLabel.setLabelFor(finalSpeedField);
		torqueLabel.setLabelFor(torqueDropDown);
		massLabel.setLabelFor(massField);
		volumeLabel.setLabelFor(volumeField);
		mpgLabel.setLabelFor(mpgField);
	}

	/*
	 * sets layout of frame
	 */
	@SuppressWarnings("static-access")
	public void setUpFrame() {
		frame = new JFrame("Efficiency Calculator");
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
	 * grabs correct torque based on acceleration style and input speeds
	 */
	private double grabTorque(double speed) {
		double torqueToUse = 0;
		double[] array = findTorqueArray(speed);
		String choiceSlow = "          Slow";
		String choiceMod = "          Moderate";
		String choiceQuick = "          Quick";
		String choice = (String) torqueDropDown.getSelectedItem();
		if (choice.equals(choiceSlow)) {
			torqueToUse = slowAcceleration(array);
		} else if (choice.equals(choiceMod)) {
			torqueToUse = moderateAcceleration(array);
		} else if (choice.equals(choiceQuick)) {
			torqueToUse = quickAcceleration(array);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid acceleration input. Program will re-launch.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
		return torqueToUse;
	}

	private double grabBsfc(double speed) {
		double bsfcToUse = 0;
		double[] array = findBsfcArray(speed);
		String choiceSlow = "          Slow";
		String choiceMod = "          Moderate";
		String choiceQuick = "          Quick";
		String choice = (String) torqueDropDown.getSelectedItem();
		if (choice.equals(choiceSlow)) {
			bsfcToUse = slowAcceleration(array);
		} else if (choice.equals(choiceMod)) {
			bsfcToUse = moderateAcceleration(array);
		} else if (choice.equals(choiceQuick)) {
			bsfcToUse = quickAcceleration(array);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid acceleration input. Program will re-launch.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
		return bsfcToUse;
	}

	private double quickAcceleration(double[] array) {
		double valueToUse = max(array);
		return valueToUse;
	}

	private double[] findBsfcArray(double speed) {
		double[] arrayToUse = {};
		if (speed == 30.0) {
			arrayToUse = b30;
		} else if (speed == 31.0) {
			arrayToUse = b31;
		} else if (speed == 32.0) {
			arrayToUse = b32;
		} else if (speed == 33.0) {
			arrayToUse = b33;
		} else if (speed == 34.0) {
			arrayToUse = b34;
		} else if (speed == 35.0) {
			arrayToUse = b35;
		} else if (speed == 36.0) {
			arrayToUse = b36;
		} else if (speed == 37.0) {
			arrayToUse = b37;
		} else if (speed == 38.0) {
			arrayToUse = b39;
		} else if (speed == 39.0) {
			arrayToUse = b39;
		} else if (speed == 40.0) {
			arrayToUse = b40;
		} else if (speed == 41.0) {
			arrayToUse = b41;
		} else if (speed == 42.0) {
			arrayToUse = b42;
		} else if (speed == 43.0) {
			arrayToUse = b43;
		} else if (speed == 44.0) {
			arrayToUse = b44;
		} else if (speed == 45.0) {
			arrayToUse = b45;
		} else if (speed == 46.0) {
			arrayToUse = b47;
		} else if (speed == 47.0) {
			arrayToUse = b47;
		} else if (speed == 48.0) {
			arrayToUse = b48;
		} else if (speed == 49.0) {
			arrayToUse = b48;
		} else if (speed == 50.0) {
			arrayToUse = b50;
		} else if (speed == 51.0) {
			arrayToUse = b51;
		} else if (speed == 53.0) {
			arrayToUse = b53;
		} else if (speed == 54.0) {
			arrayToUse = b53;
		} else if (speed == 55.0) {
			arrayToUse = b53;
		} else if (speed == 56.0) {
			arrayToUse = b58;
		} else if (speed == 57.0) {
			arrayToUse = b58;
		} else if (speed == 58.0) {
			arrayToUse = b58;
		} else if (speed == 59.0) {
			arrayToUse = b58;
		} else if (speed == 60.0) {
			arrayToUse = b58;
		}
		return arrayToUse;
	}

	private double[] findTorqueArray(double speed) {
		double[] arrayToUse = {};
		if (speed == 30.0) {
			arrayToUse = t30;
		} else if (speed == 31.0) {
			arrayToUse = t31;
		} else if (speed == 32.0) {
			arrayToUse = t32;
		} else if (speed == 33.0) {
			arrayToUse = t33;
		} else if (speed == 34.0) {
			arrayToUse = t34;
		} else if (speed == 35.0) {
			arrayToUse = t35;
		} else if (speed == 36.0) {
			arrayToUse = t36;
		} else if (speed == 37.0) {
			arrayToUse = t37;
		} else if (speed == 38.0) {
			arrayToUse = t39;
		} else if (speed == 39.0) {
			arrayToUse = t39;
		} else if (speed == 40.0) {
			arrayToUse = t40;
		} else if (speed == 41.0) {
			arrayToUse = t41;
		} else if (speed == 42.0) {
			arrayToUse = t42;
		} else if (speed == 43.0) {
			arrayToUse = t43;
		} else if (speed == 44.0) {
			arrayToUse = t44;
		} else if (speed == 45.0) {
			arrayToUse = t45;
		} else if (speed == 46.0) {
			arrayToUse = t47;
		} else if (speed == 47.0) {
			arrayToUse = t47;
		} else if (speed == 48.0) {
			arrayToUse = t48;
		} else if (speed == 49.0) {
			arrayToUse = t48;
		} else if (speed == 50.0) {
			arrayToUse = t50;
		} else if (speed == 51.0) {
			arrayToUse = t51;
		} else if (speed == 53.0) {
			arrayToUse = t53;
		} else if (speed == 54.0) {
			arrayToUse = t53;
		} else if (speed == 55.0) {
			arrayToUse = t53;
		} else if (speed == 56.0) {
			arrayToUse = t58;
		} else if (speed == 57.0) {
			arrayToUse = t58;
		} else if (speed == 58.0) {
			arrayToUse = t58;
		} else if (speed == 59.0) {
			arrayToUse = t58;
		} else if (speed == 60.0) {
			arrayToUse = t58;
		}
		return arrayToUse;
	}

	private double moderateAcceleration(double[] array) {
		double valueToUse = mid(array);
		return valueToUse;
	}

	private double slowAcceleration(double[] array) {
		double valueToUse = min(array);
		return valueToUse;
	}

	private double min(double[] array) {
		double valueToUse = 0;
		double min = 1000000;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		valueToUse = min;
		return valueToUse;
	}

	private double mid(double[] array) {
		double valueToUse = 0;
		double mid = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] <= mid || array[i] >= mid) {
				mid = array[i];
			}
		}
		valueToUse = mid;
		return valueToUse;
	}

	private double max(double[] array) {
		double valueToUse = 0;
		double max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		valueToUse = max;
		return valueToUse;
	}

	/*
	 * sets the formatted text fields
	 */
	public void setUpFields() {
		initialSpeedField = new JFormattedTextField(initialSpeedFormat);
		initialSpeedField.setColumns(10);
		finalSpeedField = new JFormattedTextField(finalSpeedFormat);
		finalSpeedField.setColumns(10);
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

	// /*
	// * calculates volume of fuel consumed during acceleration
	// */
	// private double calculateVolume(double massConsumed) {
	// return (massConsumed / 6.183); // lb/gal
	// }

	/*
	 * loop to calculate mass and velocities
	 */
	private void velocityLoop(double torque, double bsfc, double initialSpeed, double finalSpeed) {
		double currentV = initialSpeed;
		double rpm = currentV / .0212;
		double Tc = getCruiseTorque(currentV);
		double Wc = rpm * (2 * Math.PI / 60);
		double deltaT = 0;
		String choiceSlow = "          Slow";
		String choiceMod = "          Moderate";
		String choiceQuick = "          Quick";
		String choice = (String) torqueDropDown.getSelectedItem();
		if (choice.equals(choiceSlow)) {
			deltaT = 1;
		} else if (choice.equals(choiceMod)) {
			deltaT = .5;
		} else if (choice.equals(choiceQuick)) {
			deltaT = .25;
		}
		int sentinel = 1;
		rpm = rpm * (2 * Math.PI / 60); // rad/sec
		double deltaV; // mi/hr
		double finalV = finalSpeed;
		double twoPercent = finalV - (finalV * .02);
		double dist = 0; // mi
		double massUsed = 0; // g
		double deltaMass; // g
		int count = 0;

		while (sentinel != -1) {
			if (twoPercent >= finalSpeed || currentV > finalSpeed) {
				sentinel = -1;
			}
			deltaV = ((((rpm * torque) - (Wc * Tc)) * deltaT) / (1060045 * (currentV * .000277778))) * 1.404;
			// if (deltaV < 0) {
			// deltaV = deltaV * (-1);
			// }
			// TODO: remove ANY data points <= cruising torque
			// TODO: check low and middle torques to see if they are below cruising
			dist = dist + ((currentV * .000277778) * deltaT); // mi
			deltaMass = ((bsfc / 360000) * torque * rpm * dist) / currentV; // g
			massUsed = massUsed + deltaMass;
			currentV = currentV + deltaV;
			count++;
			if (count > 200) {
				sentinel = -1;
			}
			Tc = Tc + 2;
			Wc = Wc + 15;
		}
		massUsed = massUsed * 100;
		double volumeConsumed = (massUsed * 0.00220462) / 6.183;
		double mpg = (dist / volumeConsumed);
		massField.setValue(massUsed);
		volumeField.setValue(volumeConsumed);
		mpgField.setValue(mpg);
	}

	/*
	 * cruising torque solve y = .0212x where y = torque, x = rpm
	 */

	private double getCruiseTorque(double currentV) {
		double t = 0;
		double fivePercentA = currentV + (currentV * .05);
		double fivePercentB = currentV - (currentV * .05);
		if (currentV == 32) {
			t = t1;
		} else if (currentV == 34 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t2;
		} else if (currentV == 35 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t3;
		} else if (currentV == 43 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t5;
		} else if (currentV == 37 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t4;
		} else if (currentV == 45 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t6;
		} else if (currentV == 48 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t7;
		} else if (currentV == 51 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t8;
		} else if (currentV == 58 || fivePercentB <= currentV || fivePercentA >= currentV) {
			t = t9;
		} else {
			t = 31.6;
		}
		return t;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}
}