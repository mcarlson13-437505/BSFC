import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class BSFCgui implements ActionListener {
	//data fields
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
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(speedLabel);
        labelPane.add(rpmLabel);
        labelPane.add(torqueLabel);
        labelPane.add(bsfcLabel);
        labelPane.add(distanceLabel);
        labelPane.add(massLabel);
        labelPane.add(volumeLabel);
        labelPane.add(mpgLabel);
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
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
		if(source.equals(calcButton)) {
			calcButtonAction();
		} else {
			clearButtonAction();
		}
	}

	/*
	 * re-launches the program with blank fields
	 */
	private void clearButtonAction() {
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}
	
	/*
	 * grabs values from text fields and calls the two calculate methods
	 */
	private void calcButtonAction() {
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
	}

	private double calculateVolume(double massConsumed) {
		double poundMass = massConsumed * 2.20462; //kg to lb
		double volume = poundMass/6.94; //in gallons, 6.94 = density diesel fuel (lb/gal)
		return volume;
	}

	/*
	 * calculates mpg for the trip
	 */
	private double calculateMPG(double massConsumed, double distance) {
		double mpg = 0;
		double poundMass = massConsumed * 2.20462; //kg to lb
		double volume = poundMass/6.94; //in gallons, 6.94 = density diesel fuel (lb/gal)
		mpg = distance/volume;
		return mpg;
	}

	/*
	 * calculates mass consumed and returns the value as a long
	 */
	private double calculateMass(double speed, double distance, double rpm, double bsfc, double torque) {
		double mass = 0;
		double bsfc1 = bsfc/3600000; //converts to g/s
		double rpm1 = rpm*(2*Math.PI/60);
		speed = speed*0.000277778;
		mass = (double)(bsfc1 * rpm1 * torque * distance);
		mass = (double) (mass/speed);
		mass = mass/1000;
		return mass;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		BSFCgui gui = new BSFCgui();
	}
}