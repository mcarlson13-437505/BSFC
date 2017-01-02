import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BSFCgui implements ActionListener {
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
	private JFormattedTextField speed;
	private JFormattedTextField distance;
	private JFormattedTextField rpm;
	private JFormattedTextField bsfc;
	private JFormattedTextField torque;
	private JButton calcButton = new JButton("Calculate");
	private JButton clearButton = new JButton("Clear Fields");

	public BSFCgui() {
		setUpFrame();
		setUpLabels();
		setUpFields();
		addToFrame();
		displayFrame();
		addActionListeners();
	}
	
	private void addToFrame() {
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(speedLabel);
        labelPane.add(rpmLabel);
        labelPane.add(torqueLabel);
        labelPane.add(bsfcLabel);
        labelPane.add(distanceLabel);
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(speed);
        fieldPane.add(rpm);
        fieldPane.add(torque);
        fieldPane.add(bsfc);
        fieldPane.add(distance);
        frame.add(labelPane, BorderLayout.CENTER);
        frame.add(fieldPane, BorderLayout.LINE_END);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(calcButton);
        buttonPanel.add(clearButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	/*
	 * initializes labels and sets their text
	 */
	private void setUpLabels() {
		speedLabel = new JLabel(speedLabelText);
		distanceLabel = new JLabel(distanceLabelText);
		rpmLabel = new JLabel(rpmLabelText);
		bsfcLabel = new JLabel(bsfcLabelText);
		torqueLabel = new JLabel(torqueLabelText);
	}
	
	/*
	 * sets layout of frame
	 */
	@SuppressWarnings("static-access")
	public void setUpFrame() {
		frame = new JFrame("BSFC Calculator");
		frame.setLayout(new BorderLayout());
		frame.setSize(400, 500);
		frame.setResizable(false);
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
		speed = new JFormattedTextField();
		speed.setValue(new Integer(0));
		speed.setColumns(3);
		distance = new JFormattedTextField();
		distance.setValue(new Integer(0));
		distance.setColumns(6);
		rpm = new JFormattedTextField();
		rpm.setValue(new Integer(0));
		rpm.setColumns(5);
		torque = new JFormattedTextField();
		torque.setValue(new Integer(0));
		torque.setColumns(4);
		bsfc = new JFormattedTextField();
		bsfc.setValue(new Integer(0));
		bsfc.setColumns(4);
	}
	
	/*
	 * adds action listener to calc button
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

	private void clearButtonAction() {
		speed.setValue(0);
		torque.setValue(0);
		bsfc.setValue(0);
		distance.setValue(0);
		rpm.setValue(0);
	}

	private void calcButtonAction() {
		// TODO Auto-generated method stub	
	}

	public static void main(String[] args) {
		BSFCgui gui = new BSFCgui();
	}
}
