import java.awt.*;
import javax.swing.*;

public class BSFCgui {
	private JFrame frame;
	// want this to be like the 285 project with labels and input areas for user
	// to calculate BSFC
	//interact with other classes to get values
	public BSFCgui() {
		setUpFrame();
		centerFrame();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void centerFrame() {
		frame.setLocationRelativeTo(null);
	}

	public void setUpFrame() {
		frame = new JFrame("BSFC Calculator");
		frame.setSize(Constants.getDimension());
	}

	public static void main(String[] args) {
		BSFCgui gui = new BSFCgui();
	}
}
