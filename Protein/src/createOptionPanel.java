import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class createOptionPanel implements ActionListener {
	public static JTextField xTextField, yTextField, zTextField, colTextField,
			inputAlg;
	private static int WIDTH = 520;
	private static int HEIGHT = 300;

	public static JPanel createOpt() {
		JPanel NPInput = new JPanel();
		JPanel gridNPI = new JPanel();
		JPanel gridNextPrev = new JPanel();
		JPanel optionPanel = new JPanel();
		JPanel algOpt = new JPanel();
		JPanel optGUI = new JPanel();
		JPanel GUI = new JPanel();

		gridNextPrev.setLayout(new GridLayout(1, 3, 0, 5));
		gridNPI.setLayout(new GridLayout(7, 1, 10, 5));
		NPInput.setLayout(new BorderLayout(0, 5));
		algOpt.setLayout(new GridLayout(1, 2, 0, 5));
		optionPanel.setLayout(new GridLayout(5, 2, 0, 5));
		optGUI.setLayout(new BorderLayout(0, 10));
		GUI.setLayout(new BorderLayout(5, 10));

		NPInput.setPreferredSize(new Dimension(200, 300));
		GUI.setSize(WIDTH, HEIGHT);

		JButton inpStream = new JButton("Start inputstream");
		inpStream.setActionCommand("Start inputstream");
		inpStream.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String inAlg = (inputAlg.getText());
				System.out.println(inAlg);
				if (inAlg.isEmpty()) {
					ExAlg alg = new ExAlg();
					alg.runAlg();
					new readChain(alg.getAlgRes());
				} else {
					new ReadAlgInput(inAlg);
				}

			}
		});

		JButton nextStep = new JButton("Next");
		nextStep.setActionCommand("Next");
		nextStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (BallFrame.getConf() < Conformations.confSize() - 1) {
					BallFrame.incrConf();
				}
			}
		});

		JButton prevStep = new JButton("Previous");
		prevStep.setActionCommand("Previous");
		prevStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (BallFrame.getConf() > 0) {
					BallFrame.decrConf();
				}
			}
		});

		JButton moveStep = new JButton("Movie");
		moveStep.setActionCommand("Movie");
		moveStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Thread t = new Thread(new Runnable() {
					public boolean run = true;

					@Override
					public void run() {
						run = true;
						while (run) {
							if (BallFrame.getConf() == 0) {
								try {
									Thread.sleep(4000);
								} catch (InterruptedException e) {
									Thread.currentThread().interrupt();
								}
							}

							if (BallFrame.getConf() < Conformations.confSize() - 1) {
								BallFrame.incrConf();
							} else {
								run = false;
							}

							try {
								Thread.sleep(4000);
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
						}
					}
				});
				t.start();
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

				Ball newBall = new Ball(xCoordinate, yCoordinate, zCoordinate,
						MolColor);
				if (Conformations.confSize() == 0) {
					Conformations.addConf(new BallChain());
				}
				BallChain currentChain = Conformations.retConf(BallFrame
						.getConf());
				currentChain.addBall(newBall);
			}
		});

		JLabel coordTitle = new JLabel("Coordinates and\n color on atom");
		JLabel xCoord = new JLabel("X: ");
		JLabel yCoord = new JLabel("Y: ");
		JLabel zCoord = new JLabel("Z: ");
		JLabel inputAlgorithm = new JLabel("Type chain here");

		xCoord.setHorizontalAlignment(JLabel.RIGHT);
		yCoord.setHorizontalAlignment(JLabel.RIGHT);
		zCoord.setHorizontalAlignment(JLabel.RIGHT);
		inputAlgorithm.setHorizontalAlignment(JLabel.LEFT);

		// JLabel colorTitle = new JLabel("Color on atom");
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

		inputAlg = new JTextField(1);
		inputAlg.setHorizontalAlignment(JTextField.RIGHT);

		gridNextPrev.add(prevStep);
		gridNextPrev.add(nextStep);
		gridNextPrev.add(moveStep);

		gridNPI.add(new JLabel("Start inputstream"));
		gridNPI.add(inpStream);
		gridNPI.add(new JLabel("Conformations"));
		gridNPI.add(gridNextPrev);
		gridNPI.add(inputAlgorithm);
		gridNPI.add(inputAlg);

		NPInput.add(gridNPI, BorderLayout.PAGE_END);

		optionPanel.add(xCoord);
		optionPanel.add(xTextField);
		optionPanel.add(yCoord);
		optionPanel.add(yTextField);
		optionPanel.add(zCoord);
		optionPanel.add(zTextField);
		optionPanel.add(atomColor);
		optionPanel.add(colTextField);
		// optionPanel.add(inputAlgorithm);
		// optionPanel.add(inputAlg);

		optGUI.add(optionPanel, BorderLayout.CENTER);
		optGUI.add(coordTitle, BorderLayout.PAGE_START);
		optGUI.add(addBall, BorderLayout.PAGE_END);

		GUI.add(new JLabel(""), BorderLayout.PAGE_START);
		GUI.add(NPInput, BorderLayout.CENTER);
		GUI.add(optGUI, BorderLayout.PAGE_END);

		return GUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
