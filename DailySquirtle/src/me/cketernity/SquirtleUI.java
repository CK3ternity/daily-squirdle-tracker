package me.cketernity;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

public class SquirtleUI {
	
	private static JFrame frame;
	private static JLabel nextGuessPokemon = new JLabel();
	private static JLabel[] historyLabels;
	private static boolean[] gen;
	private static boolean[] type1;
	private static boolean[] type2;
	private static boolean[] height;
	private static boolean[] weight;
	
	// Images
	private static Icon correctImg = new ImageIcon("src/me/cketernity/img/correct.png");
	private static Icon wrongImg = new ImageIcon("src/me/cketernity/img/wrong.png");
	private static Icon wrongposImg = new ImageIcon("src/me/cketernity/img/wrongpos.png");
	private static Icon upImg = new ImageIcon("src/me/cketernity/img/up.png");
	private static Icon downImg = new ImageIcon("src/me/cketernity/img/down.png");
	private static Icon correctSelectedImg = new ImageIcon("src/me/cketernity/img/correctselected.png");
	private static Icon wrongSelectedImg = new ImageIcon("src/me/cketernity/img/wrongselected.png");
	private static Icon wrongposSelectedImg = new ImageIcon("src/me/cketernity/img/wrongposselected.png");
	private static Icon upSelectedImg = new ImageIcon("src/me/cketernity/img/upselected.png");
	private static Icon downSelectedImg = new ImageIcon("src/me/cketernity/img/downselected.png");
	
	// Buttons
	private static JButton upGenButton;
	private static JButton correctGenButton;
	private static JButton downGenButton;
	private static JButton correctType1Button;
	private static JButton wrongposType1Button;
	private static JButton wrongType1Button;
	private static JButton correctType2Button;
	private static JButton wrongposType2Button;
	private static JButton wrongType2Button;
	private static JButton upHeightButton;
	private static JButton correctHeightButton;
	private static JButton downHeightButton;
	private static JButton upWeightButton;
	private static JButton correctWeightButton;
	private static JButton downWeightButton;
	
	public SquirtleUI() {
		if(frame == null) {
			frame = new JFrame("Daily Squirtle");
			init();
			createFrame();
		}
	}
	
	private void init() {
		gen = new boolean[3];
		type1 = new boolean[3];
		type2 = new boolean[3];
		height = new boolean[3];
		weight = new boolean[3];
		
		historyLabels = new JLabel[8];
		for(int i = 0; i < historyLabels.length; i++) {
			historyLabels[i] = new JLabel((i + 1) + ")");
		}
	}
	private void createFrame() {
		frame.getContentPane().add(createInteractionPanel(), BorderLayout.WEST);
		frame.getContentPane().add(createHistoryPanel());
		setNextGuess("Bibarel");
		
		frame.setSize(742, 301);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private JPanel createInteractionPanel() {
		JPanel interactionPanel = new JPanel(new BorderLayout());
		
		interactionPanel.add(createNextGuessPanel(), BorderLayout.NORTH);
		interactionPanel.add(createInputPanel(), BorderLayout.CENTER);
		interactionPanel.add(createSubmitPanel(), BorderLayout.SOUTH);
		
		return interactionPanel;
	}
	private JPanel createNextGuessPanel() {
		JPanel nextGuessPanel = new JPanel(new BorderLayout());
		
		nextGuessPanel.add(new JLabel("Next Guess: "), BorderLayout.WEST);
		nextGuessPanel.add(nextGuessPokemon);
		
		return nextGuessPanel;
	}
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new FlowLayout());
		inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		// --------------------
		// | Generation Panel |
		// --------------------
		
		// Resources
		JLabel genLabel = new JLabel("Generation");
		upGenButton = new JButton(upImg);
		correctGenButton = new JButton(correctImg);		
		downGenButton = new JButton(downImg);
		
		upGenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('g', 'u');
				upGenButton.setIcon(upSelectedImg);
				correctGenButton.setIcon(correctImg);
				downGenButton.setIcon(downImg);
			}
		});
		correctGenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('g', 'c');
				upGenButton.setIcon(upImg);
				correctGenButton.setIcon(correctSelectedImg);
				downGenButton.setIcon(downImg);
			}
		});
		downGenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('g', 'd');
				upGenButton.setIcon(upImg);
				correctGenButton.setIcon(correctImg);
				downGenButton.setIcon(downSelectedImg);
			}
		});
		
		JPanel genPanel = new JPanel();
		GroupLayout genLayout = new GroupLayout(genPanel);
		genPanel.setLayout(genLayout);
		
		genLayout.setAutoCreateGaps(false);
		genLayout.setAutoCreateContainerGaps(false);
		
		genLayout.setHorizontalGroup(
			genLayout.createSequentialGroup()
				.addGroup(genLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(genLabel)
					.addComponent(upGenButton)
					.addComponent(correctGenButton)
					.addComponent(downGenButton))
		);
		genLayout.setVerticalGroup(
			genLayout.createSequentialGroup()
				.addComponent(genLabel)
				.addComponent(upGenButton)
				.addComponent(correctGenButton)
				.addComponent(downGenButton)
		);
		
		// ----------------
		// | Type 1 Panel |
		// ----------------
		
		//Resources
		JLabel type1Label = new JLabel("Type 1");
		correctType1Button = new JButton(correctImg);		
		wrongposType1Button = new JButton(wrongposImg);
		wrongType1Button = new JButton(wrongImg);
		
		correctType1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('1', 'c');
				correctType1Button.setIcon(correctSelectedImg);
				wrongposType1Button.setIcon(wrongposImg);
				wrongType1Button.setIcon(wrongImg);
			}
		});
		wrongposType1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('1', 'p');
				correctType1Button.setIcon(correctImg);
				wrongposType1Button.setIcon(wrongposSelectedImg);
				wrongType1Button.setIcon(wrongImg);
			}
		});
		wrongType1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('1', 'w');
				correctType1Button.setIcon(correctImg);
				wrongposType1Button.setIcon(wrongposImg);
				wrongType1Button.setIcon(wrongSelectedImg);
			}
		});
		
		JPanel type1Panel = new JPanel();
		GroupLayout type1Layout = new GroupLayout(type1Panel);
		type1Panel.setLayout(type1Layout);
		
		type1Layout.setAutoCreateGaps(false);
		type1Layout.setAutoCreateContainerGaps(true);
		
		type1Layout.setHorizontalGroup(
			type1Layout.createSequentialGroup()
				.addGroup(type1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(type1Label)
					.addComponent(correctType1Button)
					.addComponent(wrongposType1Button)
					.addComponent(wrongType1Button))
		);
		type1Layout.setVerticalGroup(
				type1Layout.createSequentialGroup()
				.addComponent(type1Label)
				.addComponent(correctType1Button)
				.addComponent(wrongposType1Button)
				.addComponent(wrongType1Button)
		);
		
		// ----------------
		// | Type 2 Panel |
		// ----------------
		
		// Resources
		JLabel type2Label = new JLabel("Type 2");
		correctType2Button = new JButton(correctImg);		
		wrongposType2Button = new JButton(wrongposImg);
		wrongType2Button = new JButton(wrongImg);
		
		correctType2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('2', 'c');
				correctType2Button.setIcon(correctSelectedImg);
				wrongposType2Button.setIcon(wrongposImg);
				wrongType2Button.setIcon(wrongImg);
			}
		});
		wrongposType2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('2', 'p');
				correctType2Button.setIcon(correctImg);
				wrongposType2Button.setIcon(wrongposSelectedImg);
				wrongType2Button.setIcon(wrongImg);
			}
		});
		wrongType2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('2', 'w');
				correctType2Button.setIcon(correctImg);
				wrongposType2Button.setIcon(wrongposImg);
				wrongType2Button.setIcon(wrongSelectedImg);
			}
		});
		
		JPanel type2Panel = new JPanel();
		GroupLayout type2Layout = new GroupLayout(type2Panel);
		type2Panel.setLayout(type2Layout);
		
		type2Layout.setAutoCreateGaps(false);
		type2Layout.setAutoCreateContainerGaps(false);
		
		type2Layout.setHorizontalGroup(
			type2Layout.createSequentialGroup()
				.addGroup(type2Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(type2Label)
					.addComponent(correctType2Button)
					.addComponent(wrongposType2Button)
					.addComponent(wrongType2Button))
		);
		type2Layout.setVerticalGroup(
				type2Layout.createSequentialGroup()
				.addComponent(type2Label)
				.addComponent(correctType2Button)
				.addComponent(wrongposType2Button)
				.addComponent(wrongType2Button)
		);
		
		// ----------------
		// | Height Panel |
		// ----------------
		
		// Resources
		JLabel heightLabel = new JLabel("Height");
		upHeightButton = new JButton(upImg);
		correctHeightButton = new JButton(correctImg);
		downHeightButton = new JButton(downImg);
		
		upHeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('h', 'u');
				upHeightButton.setIcon(upSelectedImg);
				correctHeightButton.setIcon(correctImg);
				downHeightButton.setIcon(downImg);
			}
		});
		correctHeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('h', 'c');
				upHeightButton.setIcon(upImg);
				correctHeightButton.setIcon(correctSelectedImg);
				downHeightButton.setIcon(downImg);
			}
		});
		downHeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('h', 'd');
				upHeightButton.setIcon(upImg);
				correctHeightButton.setIcon(correctImg);
				downHeightButton.setIcon(downSelectedImg);
			}
		});
		
		JPanel heightPanel = new JPanel();
		GroupLayout heightLayout = new GroupLayout(heightPanel);
		heightPanel.setLayout(heightLayout);
		
		heightLayout.setAutoCreateGaps(false);
		heightLayout.setAutoCreateContainerGaps(true);
		
		heightLayout.setHorizontalGroup(
			heightLayout.createSequentialGroup()
				.addGroup(heightLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(heightLabel)
					.addComponent(upHeightButton)
					.addComponent(correctHeightButton)
					.addComponent(downHeightButton))
		);
		heightLayout.setVerticalGroup(
			heightLayout.createSequentialGroup()
				.addComponent(heightLabel)
				.addComponent(upHeightButton)
				.addComponent(correctHeightButton)
				.addComponent(downHeightButton)
		);
		
		// ----------------
		// | Weight Panel |
		// ----------------
		
		// Resources
		JLabel weightLabel = new JLabel("Weight");
		upWeightButton = new JButton(upImg);
		correctWeightButton = new JButton(correctImg);		
		downWeightButton = new JButton(downImg);
		
		upWeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('w', 'u');
				upWeightButton.setIcon(upSelectedImg);
				correctWeightButton.setIcon(correctImg);
				downWeightButton.setIcon(downImg);
			}
		});
		correctWeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('w', 'c');
				upWeightButton.setIcon(upImg);
				correctWeightButton.setIcon(correctSelectedImg);
				downWeightButton.setIcon(downImg);
			}
		});
		downWeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SquirtleUI.getButtonPress('w', 'd');
				upWeightButton.setIcon(upImg);
				correctWeightButton.setIcon(correctImg);
				downWeightButton.setIcon(downSelectedImg);
			}
		});
		
		JPanel weightPanel = new JPanel();
		GroupLayout weightLayout = new GroupLayout(weightPanel);
		weightPanel.setLayout(weightLayout);
		
		weightLayout.setAutoCreateGaps(false);
		weightLayout.setAutoCreateContainerGaps(false);
		
		weightLayout.setHorizontalGroup(
			weightLayout.createSequentialGroup()
				.addGroup(weightLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(weightLabel)
					.addComponent(upWeightButton)
					.addComponent(correctWeightButton)
					.addComponent(downWeightButton))
		);
		weightLayout.setVerticalGroup(
			weightLayout.createSequentialGroup()
				.addComponent(weightLabel)
				.addComponent(upWeightButton)
				.addComponent(correctWeightButton)
				.addComponent(downWeightButton)
		);
		
		inputPanel.add(genPanel);
		inputPanel.add(type1Panel);
		inputPanel.add(type2Panel);
		inputPanel.add(heightPanel);
		inputPanel.add(weightPanel);
		
		return inputPanel;
	}
	private JPanel createSubmitPanel() {
		JPanel submitPanel = new JPanel(new BorderLayout());
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean[][] array = new boolean[][] {gen, type1, type2, height, weight};
				
				boolean finished = true;
				boolean oneTrue = false;
				for(boolean[] stats: array) {
					for(boolean value: stats) {
						if(value) {
							oneTrue = true;
						}
					}
					
				}
				if(!oneTrue) {
					JOptionPane.showMessageDialog(frame, "One of the attributes have not been selected!", "Error", JOptionPane.ERROR_MESSAGE);
					finished = false;
				}
				
				if(finished) {					
					if(Main.updateResults(array)) {
						JOptionPane.showMessageDialog(frame, "Congratulations!");
						frame.dispose();
					} else {
						Main.increaseRound();
					}
				}
			}
		});
		
		submitPanel.add(submitButton, BorderLayout.WEST);
		
		return submitPanel;
	}
	
	private JPanel createHistoryPanel() {
		JPanel historyPanel = new JPanel();
		historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
		
		historyPanel.add(new JLabel("History"));
		for(int i = 0; i < historyLabels.length; i++) {
			historyPanel.add(historyLabels[i]);
		}
		
		return historyPanel;
	}
	
	public static void setNextGuess(String name) {
		addToHistoryPanel(nextGuessPokemon.getText());
		nextGuessPokemon.setText(name);
	}
	public static void reset() {
		upGenButton.setIcon(upImg);
		correctGenButton.setIcon(correctImg);
		downGenButton.setIcon(downImg);
		
		correctType1Button.setIcon(correctImg);
		wrongposType1Button.setIcon(wrongposImg);
		wrongType1Button.setIcon(wrongImg);
		
		correctType2Button.setIcon(correctImg);
		wrongposType2Button.setIcon(wrongposImg);
		wrongType2Button.setIcon(wrongImg);
		
		upHeightButton.setIcon(upImg);
		correctHeightButton.setIcon(correctImg);
		downHeightButton.setIcon(downImg);
		
		upWeightButton.setIcon(upImg);
		correctWeightButton.setIcon(correctImg);
		downWeightButton.setIcon(downImg);
	}
	private static void addToHistoryPanel(String name) {
		if(Main.getRound() > 0)
			historyLabels[Main.getRound() - 1].setText((Main.getRound()) + ") " + name);
	}
	public static void getNewPokemon() {
		new AddPokemonUI();
	}
	
	// infoType- g (gen), 1 (type 1), 2 (type 2), h (height), w (weight)
	// input- c (correct), w (wrong), p (wrongpos), u (up), d (down)
	private static void getButtonPress(char infoType, char input) {
		if(infoType == 'g') {
			switch(input) {
			case 'u':
				gen[0] = true;
				gen[1] = false;
				gen[2] = false;
				break;
			case 'c':
				gen[0] = false;
				gen[1] = true;
				gen[2] = false;
				break;
			case 'd':
				gen[0] = false;
				gen[1] = false;
				gen[2] = true;
				break;
			default:
				System.out.println("There should be no way to see this message, 1");
			}
		} else if(infoType == '1') {
			switch(input) {
			case 'c':
				type1[0] = true;
				type1[1] = false;
				type1[2] = false;
				break;
			case 'p':
				type1[0] = false;
				type1[1] = true;
				type1[2] = false;
				break;
			case 'w':
				type1[0] = false;
				type1[1] = false;
				type1[2] = true;
				break;
			default:
				System.out.println("There should be no way to see this message, 2");
			}
		} else if(infoType == '2') {
			switch(input) {
			case 'c':
				type2[0] = true;
				type2[1] = false;
				type2[2] = false;
				break;
			case 'p':
				type2[0] = false;
				type2[1] = true;
				type2[2] = false;
				break;
			case 'w':
				type2[0] = false;
				type2[1] = false;
				type2[2] = true;
				break;
			default:
				System.out.println("There should be no way to see this message, 3");
			}
		} else if(infoType == 'h') {
			switch(input) {
			case 'u':
				height[0] = true;
				height[1] = false;
				height[2] = false;
				break;
			case 'c':
				height[0] = false;
				height[1] = true;
				height[2] = false;
				break;
			case 'd':
				height[0] = false;
				height[1] = false;
				height[2] = true;
				break;
			default:
				System.out.println("There should be no way to see this message, 4");
			}
		} else if(infoType == 'w') {
			switch(input) {
			case 'u':
				weight[0] = true;
				weight[1] = false;
				weight[2] = false;
				break;
			case 'c':
				weight[0] = false;
				weight[1] = true;
				weight[2] = false;
				break;
			case 'd':
				weight[0] = false;
				weight[1] = false;
				weight[2] = true;
				break;
			default:
				System.out.println("There should be no way to see this message, 5");
			}
		} else {
			System.out.println("There should be no way to see this message");
		}
	}
}
class AddPokemonUI {
	
	private static JFrame frame;
	private static JTextField nameField;
	private static JSpinner genSpinner;
	private static JComboBox<String> type1ComboBox;
	private static JComboBox<String> type2ComboBox;
	private static JSpinner heightSpinner;
	private static JSpinner weightSpinner;
	
	public AddPokemonUI() {
		frame = new JFrame("Add a Pokemon");
		createFrame();
	}
	
	private static void createFrame() {
		
		frame.getContentPane().add(createNewPokemonPanel());
		
		frame.setSize(287, 273);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	private static JPanel createNewPokemonPanel() {
		JPanel newPokemonPanel = new JPanel(new BorderLayout());
		
		newPokemonPanel.add(createLabelPanel(), BorderLayout.NORTH);
		newPokemonPanel.add(createInputPanel(), BorderLayout.CENTER);
		newPokemonPanel.add(createSubmitPanel(), BorderLayout.SOUTH);
		
		return newPokemonPanel;
	}
	private static JPanel createLabelPanel() {
		JPanel labelPanel = new JPanel(new BorderLayout());
		JLabel noPokemonFoundLabel = new JLabel(" No Pokemon found! What is your next guess?");
		
		labelPanel.add(noPokemonFoundLabel, BorderLayout.WEST);
		return labelPanel;
	}
	private static JPanel createInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		
		// --------------
		// | Name Panel |
		// --------------
		
		JPanel namePanel = new JPanel();
		GroupLayout nameLayout = new GroupLayout(namePanel);
		namePanel.setLayout(nameLayout);
		
		nameLayout.setAutoCreateGaps(false);
		nameLayout.setAutoCreateContainerGaps(true);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameField = new JTextField("");
		
		nameLayout.setHorizontalGroup(
				nameLayout.createSequentialGroup()
					.addComponent(nameLabel)
					.addComponent(nameField)
		);
		nameLayout.setVerticalGroup(
			nameLayout.createSequentialGroup()
				.addGroup(nameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(nameLabel)
					.addComponent(nameField))
		);
		
		// --------------------
		// | Generation Panel |
		// --------------------
		
		JPanel genPanel = new JPanel();
		GroupLayout genLayout = new GroupLayout(genPanel);
		genPanel.setLayout(genLayout);
		
		genLayout.setAutoCreateGaps(false);
		genLayout.setAutoCreateContainerGaps(true);
		
		JLabel genLabel = new JLabel("Generation: ");
		SpinnerListModel genModel = new SpinnerListModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"} );
		genSpinner = new JSpinner(genModel);
		
		genLayout.setHorizontalGroup(
			genLayout.createSequentialGroup()
				.addComponent(genLabel)
				.addComponent(genSpinner)
		);
		genLayout.setVerticalGroup(
			genLayout.createSequentialGroup()
				.addGroup(genLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(genLabel)
						.addComponent(genSpinner))
		);
		
		// --------------
		// | Type Panel |
		// --------------
		
		JPanel typePanel = new JPanel();
		GroupLayout typeLayout = new GroupLayout(typePanel);
		typePanel.setLayout(typeLayout);
		
		typeLayout.setAutoCreateGaps(true);
		typeLayout.setAutoCreateContainerGaps(true);
		
		JLabel typeLabel = new JLabel("Type: ");
		type1ComboBox = new JComboBox<String>(Main.getTypes());
		type1ComboBox.setSelectedIndex(7);
		type2ComboBox = new JComboBox<String>(Main.getTypes());
		type2ComboBox.setSelectedIndex(17);
		
		typeLayout.setHorizontalGroup(
			typeLayout.createSequentialGroup()
				.addComponent(typeLabel)
				.addComponent(type1ComboBox)
				.addComponent(type2ComboBox)
		);
		typeLayout.setVerticalGroup(
			typeLayout.createSequentialGroup()
				.addGroup(typeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(typeLabel)
					.addComponent(type1ComboBox)
					.addComponent(type2ComboBox))
		);
		
		// --------------
		// | Size Panel |
		// --------------
		
		JPanel sizePanel = new JPanel();
		GroupLayout sizeLayout = new GroupLayout(sizePanel);
		sizePanel.setLayout(sizeLayout);
		
		sizeLayout.setAutoCreateGaps(true);
		sizeLayout.setAutoCreateContainerGaps(true);
		
		JLabel heightLabel = new JLabel("Height: ");
		SpinnerListModel heightModel = new SpinnerListModel(getHeights());
		heightSpinner = new JSpinner(heightModel);
		JLabel weightLabel = new JLabel("Weight: ");
		SpinnerListModel weightModel = new SpinnerListModel(getWeights());
		weightSpinner = new JSpinner(weightModel);
		
		sizeLayout.setHorizontalGroup(
			sizeLayout.createSequentialGroup()
				.addComponent(heightLabel)
				.addComponent(heightSpinner)
				.addComponent(weightLabel)
				.addComponent(weightSpinner)
		);
		sizeLayout.setVerticalGroup(
			sizeLayout.createSequentialGroup()
				.addGroup(sizeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(heightLabel)
						.addComponent(heightSpinner)
						.addComponent(weightLabel)
						.addComponent(weightSpinner))
		);
		
		inputPanel.add(namePanel);
		inputPanel.add(genPanel);
		inputPanel.add(typePanel);
		inputPanel.add(sizePanel);
		
		return inputPanel;
	}
	private static JPanel createSubmitPanel() {
		JPanel submitPanel = new JPanel();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean allInformationPresent = true;
				
				String name = nameField.getText();
				int gen = Integer.parseInt(genSpinner.getValue().toString());
				String type1 = type1ComboBox.getSelectedItem().toString();
				String type2 = type2ComboBox.getSelectedItem().toString();
				double height = Double.parseDouble(heightSpinner.getValue().toString());
				double weight = Double.parseDouble(weightSpinner.getValue().toString());
				
				if(nameField.getText().isEmpty())
					allInformationPresent = false;
				
				if(gen > Main.getGenHigh() || gen < Main.getGenLow())
					allInformationPresent = false;
				
				if(type1ComboBox.getSelectedItem().equals("None"))
					allInformationPresent = false;
				
				if(type1ComboBox.getSelectedIndex() == type2ComboBox.getSelectedIndex())
					allInformationPresent = false;
				
				if(height > Main.getHeightHigh() || height < Main.getHeightLow())
					allInformationPresent = false;
				
				if(weight > Main.getWeightHigh() || weight < Main.getWeightLow())
					allInformationPresent = false;
				
				if(allInformationPresent) {
					Pokemon p = new Pokemon(name, gen, type1, type2, height, weight, Main.getRound());
					Main.addNewPokemon(p);
					
					SquirtleUI.setNextGuess(p.getName());
					SquirtleUI.reset();
					
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "There is something wrong with the information!", "Error", JOptionPane.ERROR_MESSAGE);
					System.out.println("Gen: " + Main.getGenHigh() + "-" + Main.getGenLow());
					System.out.println("Height: " + Main.getHeightHigh() + "-" + Main.getHeightLow());
					System.out.println("Weight: " + Main.getWeightHigh() + "-" + Main.getWeightLow());
				}
			}
		});
		
		submitPanel.add(submitButton);
		
		return submitPanel;
	}
	private static ArrayList<String> getHeights() {
		ArrayList<String> heights = new ArrayList<String>();
		for(int i = 1; i <= 1000; i++) {
			heights.add((i / 10.0) + "");
		}
		
		return heights;
	}
	private static ArrayList<String> getWeights() {
		ArrayList<String> weights = new ArrayList<String>();
		for(int i = 1; i <= 9999; i++) {
			weights.add((i / 10.0) + "");
		}
		
		return weights;
	}
}
