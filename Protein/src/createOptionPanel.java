import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class createOptionPanel implements ActionListener {
	public static JTextField xTextField, yTextField;
	private static int WIDTH = 520;
	private static int HEIGHT = 300;
	
	public static JPanel createOpt() {
		JPanel optionPanel = new JPanel();
		JPanel optGUI = new JPanel();
		JPanel GUI = new JPanel();
		
		optGUI.setLayout(new BorderLayout());
		GUI.setLayout(new BorderLayout());
		GUI.setSize(WIDTH, HEIGHT);
		
		Border outline = BorderFactory.createLineBorder(Color.black);
		optionPanel.setBorder(outline);

		JButton addBall = new JButton("Add");
		addBall.setActionCommand("Add");
		addBall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// When the "Change Layout" button is clicked
				// the GridLayout of the buttonPanel is changed
				// based on the grid size and spacing values chosen
				int xCoordinate = Integer.parseInt(xTextField.getText());
				int yCoordinate = Integer.parseInt(yTextField.getText());
			}
		});

		JLabel coordTitle = new JLabel("Coordinates");
		JLabel xCoord = new JLabel("X: ");
		JLabel yCoord = new JLabel("Y: ");

		// Left side is a 2 x 2 gridlayout
		optionPanel.setLayout(new GridLayout(2, 2));

		xTextField = new JTextField(3);
		xTextField.setHorizontalAlignment(JTextField.RIGHT);
		yTextField = new JTextField(3);
		yTextField.setHorizontalAlignment(JTextField.RIGHT);

		optionPanel.add(xCoord);
		optionPanel.add(xTextField);
		optionPanel.add(yCoord);
		optionPanel.add(yTextField);
		
		optGUI.add(optionPanel, BorderLayout.CENTER);
		optGUI.add(coordTitle, BorderLayout.PAGE_START);
		optGUI.add(addBall, BorderLayout.PAGE_END);
		
		GUI.add(new JLabel(""), BorderLayout.PAGE_START);
		GUI.add(new JLabel(""), BorderLayout.CENTER);
		GUI.add(optGUI, BorderLayout.PAGE_END);
		
		return GUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
