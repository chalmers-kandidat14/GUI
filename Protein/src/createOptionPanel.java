import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class createOptionPanel implements ActionListener {
	public static JTextField xTextField, yTextField, zTextField, colTextField;
	private static int WIDTH = 520;
	private static int HEIGHT = 300;
	
	public static JPanel createOpt() {
		JPanel optionPanel = new JPanel();
		JPanel optGUI = new JPanel();
		JPanel GUI = new JPanel();
		
		
		optionPanel.setLayout(new GridLayout(4, 2, 0, 5));
		
		optGUI.setLayout(new BorderLayout(0, 10));
		
		GUI.setLayout(new BorderLayout());
		GUI.setSize(WIDTH, HEIGHT);
		
		JButton nextStep = new JButton("Next");
		nextStep.setActionCommand("Next");
		nextStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				BallFrame.incrConf();
			}
		});
		
		JButton prevStep = new JButton("Previous");
		prevStep.setActionCommand("Previous");
		prevStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				BallFrame.decrConf();
			}
		});
				
		
		JButton addBall = new JButton("Add");
		addBall.setActionCommand("Add");
		addBall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// When the "Change Layout" button is clicked
				// the GridLayout of the buttonPanel is changed
				// based on the grid size and spacing values chosen
				float xCoordinate = Float.parseFloat(xTextField.getText());
				float yCoordinate = Float.parseFloat(yTextField.getText());
				float zCoordinate = Float.parseFloat(zTextField.getText());
				String MolColor = colTextField.getText();
				
				Ball newBall = new Ball(xCoordinate, yCoordinate, zCoordinate, MolColor);
				BallChain currentChain = Conformations.retConf(BallFrame.getConf());
				currentChain.addBall(newBall);
			}
		});

		JLabel coordTitle = new JLabel("Coordinates and\n color on atom");
		JLabel xCoord = new JLabel("X: ");
		JLabel yCoord = new JLabel("Y: ");
		JLabel zCoord = new JLabel("Z: ");
		
		xCoord.setHorizontalAlignment(JLabel.RIGHT);
		yCoord.setHorizontalAlignment(JLabel.RIGHT);
		zCoord.setHorizontalAlignment(JLabel.RIGHT);

		
		//JLabel colorTitle = new JLabel("Color on atom");
		JLabel atomColor = new JLabel("Color: ");
		atomColor.setHorizontalAlignment(JLabel.RIGHT);
		
		
		xTextField = new JTextField(1);
		xTextField.setHorizontalAlignment(JTextField.RIGHT);
		yTextField = new JTextField(1);
		yTextField.setHorizontalAlignment(JTextField.RIGHT);
		zTextField = new JTextField(1);
		zTextField.setHorizontalAlignment(JTextField.RIGHT);
		
		colTextField = new JTextField(1);
		colTextField.setHorizontalAlignment(JTextField.RIGHT);

		optionPanel.add(xCoord);
		optionPanel.add(xTextField);
		optionPanel.add(yCoord);
		optionPanel.add(yTextField);
		optionPanel.add(zCoord);
		optionPanel.add(zTextField);
		optionPanel.add(atomColor);
		optionPanel.add(colTextField);
		
		optGUI.add(optionPanel, BorderLayout.CENTER);
		optGUI.add(coordTitle, BorderLayout.PAGE_START);
		//optGUI.add(colorTitle, BorderLayout.PAGE_START);
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
