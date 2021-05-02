/**********************************************************************
*                 Final Project -- GUI Calculator                     *
*                                                                     *
* PROGRAMMER:        Jacob Hardman - hard7293@bears.unco.edu          *
* CLASS:             CS200 – Object Oriented Programming              *
* INSTRUCTOR:        Dean Zeller                                      *
* TERM:              Spring 2021                                      *
* SUBMISSION DATE:   5/1/2021		                                  *
*                                                                     *
* DESCRIPTION:                                                        *
* The following is an OOP definition for a GUI built using the Swing  *
* library for use in a Calculator program.                            *
*                                                                     *
* ATTRIBUTES:                                                         *
* 	window -- The master Swing window that contains the application.  *
* 	panel -- The master Swing panel that contains all of the child    *
*   	     GUI elements.                                            *
*   menuBar -- The master menu GUI element that contains the child    *
*   		   menu UI elements.                                      *
*   fileMenu -- The menu that contains the option to export the       *
*   			calculator's history to a .txt file.                  *
*   optionsMenu -- The menu that contains the options to change the   *
*   			   UI theme and toggle the sound effects on and off.  *
*   helpMenu -- The menu that contains a link to the program's        *
*   			README file.                                          *
*   exportButton -- Menu option that brings up the export dialog.     *
*   menuItem -- Generic child menu item that is used to add new menu  *
*   			items.                                                *
*   darkThemeOption -- Menu radio button that toggles the UI's        *
*   				   dark mode.                                     *
*   lightThemeOption -- Menu radio button that toggles the UI's       *
*   					light mode.                                   *
*   soundEffectsToggle -- Menu check box that toggles the             *
*   					  areSoundEffectsEnabled attribute.           *
*   constraints -- Dimensions that control the UI's layout.           *
*   scrollPane -- Enables scroll bars on the history pane if          *
*   			  required.                                           *
*   history -- Text area that stores and displays the calculator's    *
*   		   history.                                               *
*   display -- Text field that displays the calculator's active       *
*   		   calculation.                                           *
*   buttons -- The calculator's buttons stored in a List for          *
*   		   convenience.                                           *
*   displayFont, historyFont, buttonFont -- Fonts that are used in    *
*   										the program. All of the   *
*   										fonts are the same except *
*   										for their size.           *
*   beep, boop, errorSound -- Files that contain the sound effects    *
*   						  used in the program.                    *
*   areSoundEffectsEnabled -- Boolean value that tracks whether       *
*   						  sound effects are enabled.              *
*                                                                     *
* OUTSIDE SOURCES: The following outside sources were used to help    *
* 				   finish this assignment. These sources were mainly  *
* 				   used to help me understand how the Swing library   *
* 				   works. Only very rarely did I copy/paste code      *
* 				   and when I did so I ended up changing it           *
* 			       drastically from the original.                     *
* 																	  *
* 	Official Oracle Swing Documentation -- I read the Swing           *
* 		documentation on Oracle's website extensively in order to     *
* 		learn and better understand how to implement the different    *
* 		Swing UI components.										  *
*       https://docs.oracle.com/javase/tutorial/uiswing/components/index.html
*	Stack Overflow -- I read a TON of different threads on Stack
*		Overflow to help me fix any problems that I ran into while
*		developing the calculator. There are probably too many to
*		list but I've included the most relevant links.
*		https://stackoverflow.com/questions/24108974/setmnemonic-and-call-a-method-by-pressing-the-key
*		https://stackoverflow.com/questions/21511511/how-to-arrange-my-gui-in-java
*		https://stackoverflow.com/questions/5647800/implement-an-interface-and-override-methods-in-java
*		https://stackoverflow.com/questions/1081486/setting-background-color-for-a-jframe
*		https://stackoverflow.com/questions/4025780/displaying-%C2%B1-symbol-in-java
*		https://stackoverflow.com/questions/36014524/how-to-resize-the-window-of-my-gui/36015021
*		https://stackoverflow.com/questions/14819293/swing-gridbaglayout-auto-resize-fields
*		https://stackoverflow.com/questions/39949076/is-it-possible-to-use-color-hex-in-jlabel-like-02f7fc
*		https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
*		https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form
*		https://stackoverflow.com/questions/2605032/is-there-an-eval-function-in-java
*		https://stackoverflow.com/questions/2335601/reliably-playing-a-short-sound-in-java
*		https://stackoverflow.com/questions/21683904/how-can-i-open-execute-an-external-file-from-within-java
*		https://stackoverflow.com/questions/15602104/display-file-in-java-swing-gui
*	YouTube -- I also watched a couple different YouTube tutorials    *
*		regarding the implementation of different Swing UI elements.  *
*		https://www.youtube.com/watch?v=5o3fMLPY7qY                   *
*		https://www.youtube.com/watch?v=iE8tZ0hn2Ws					  *
*		https://www.youtube.com/watch?v=6GmW7cw8qMI					  *
*		https://www.youtube.com/watch?v=g2vDARb7gx8					  *
*		https://www.youtube.com/watch?v=-IMys4PCkIA					  *
*		https://www.youtube.com/watch?v=SyZQVJiARTQ				      *
*		https://www.youtube.com/watch?v=TErboGLHZGA&t=0s			  *
*                                                                     *
* COPYRIGHT:                                                          *
* This program uses the open source GNU General Public License.       * 
* See LICENSE.txt for more information.                               *
*                                                                     *
**********************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import jdk.jshell.JShell;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	
	private JFrame window;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu fileMenu, optionsMenu, helpMenu;
	private JMenuItem exportButton, menuItem;
	private JRadioButtonMenuItem darkThemeOption, lightThemeOption;
	private JCheckBoxMenuItem soundEffectsToggle;
	private GridBagConstraints constraints;
	private JScrollPane scrollPane;
	private JTextArea history;
	private JTextField display;
	private ArrayList<JButton> buttons;
	private Font displayFont, historyFont, buttonFont;
	private File beep, boop, errorSound;
	private boolean areSoundEffectEnabled;
	
	/************************************************************************
	 * Method:   Constructor (no parameters)                                *
	 * Purpose:  Creates the GUI for the calculator via helper methods.     *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public GUI() {
		this.Load_Resources();
		this.areSoundEffectEnabled = true;
		
		this.window = new JFrame();
		this.panel = new JPanel();
		
		this.Construct_Menus();
		
		this.Construct_History();
		this.Construct_Display();
		
		this.panel.setLayout(new GridBagLayout());
	    this.panel.setBorder(BorderFactory.createEmptyBorder());
	    
	    this.constraints = new GridBagConstraints();
		
	    this.Set_Window_Constraints();
	    this.Construct_Buttons();
	    this.Set_Button_Constraints();
	    
	    this.window.setBounds(250, 250, 800, 800);
	    this.window.add(panel, BorderLayout.CENTER);
	    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.window.setTitle("Calculator");
	    this.window.setVisible(true);
	}
	
	/************************************************************************
	 * Method:   Load_Resources				                                *
	 * Purpose:  Loads in the fonts and files necessary for display         *
	 * 			 and sound effects.                                         *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Load_Resources() {
		this.displayFont = new Font("Helvetica", Font.BOLD, 32);
		this.historyFont = new Font("Helvetica", Font.BOLD, 24);
		this.buttonFont = new Font("Helvetica", Font.BOLD, 18);
		
		this.beep = new File("Robot_blip-Marianne_Gagnon.wav");
		this.boop = new File("Robot_blip_2-Marianne_Gagnon.wav");
		this.errorSound = new File("A-Tone.wav");
	}
	
	/************************************************************************
	 * Method:   Construct_Menus				                            *
	 * Purpose:  Builds the menus needed to display the program options.    *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Construct_Menus() {
		this.menuBar = new JMenuBar();
		
		this.fileMenu = new JMenu("File");
		this.exportButton = new JMenuItem("Export History To File");
		this.exportButton.addActionListener(this);
		this.fileMenu.add(this.exportButton);
		this.optionsMenu = new JMenu("Options");
		this.menuItem = new JMenuItem("UI THEME:");
		this.optionsMenu.add(this.menuItem);
		this.optionsMenu.addSeparator();
		ButtonGroup themeButtons = new ButtonGroup();
		this.darkThemeOption = new JRadioButtonMenuItem("Dark Theme");
		this.darkThemeOption.setSelected(true);
		this.darkThemeOption.addActionListener(this);
		this.lightThemeOption = new JRadioButtonMenuItem("Light Theme");
		this.lightThemeOption.addActionListener(this);
		themeButtons.add(darkThemeOption);
		themeButtons.add(lightThemeOption);
		this.optionsMenu.add(this.darkThemeOption);
		this.optionsMenu.add(this.lightThemeOption);
		this.optionsMenu.addSeparator();
		this.optionsMenu.addSeparator();
		this.menuItem = new JMenuItem("SOUND EFFECTS:");
		this.optionsMenu.add(this.menuItem);
		this.optionsMenu.addSeparator();
		this.soundEffectsToggle = new JCheckBoxMenuItem("On");
		this.soundEffectsToggle.setSelected(true);
		this.soundEffectsToggle.addActionListener(this);
		this.optionsMenu.add(this.soundEffectsToggle);
		
		this.helpMenu = new JMenu("Help");
		this.menuItem = new JMenuItem("View README");
		this.menuItem.addActionListener(this);
		this.helpMenu.add(this.menuItem);
		
		this.menuBar.add(this.fileMenu);
		this.menuBar.add(optionsMenu);
		this.menuBar.add(helpMenu);
		this.window.setJMenuBar(this.menuBar);
	}
	
	/************************************************************************
	 * Method:   Construct_History				                            *
	 * Purpose:  Builds the history tab of the program that displays past   *
	 * 			 calculations.                                              *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Construct_History() {
		this.history = new JTextArea();
		this.history.setEditable(false);
		this.history.setBackground(Color.decode("#33353a"));
		this.history.setForeground(Color.white);
		this.history.setBorder(BorderFactory.createRaisedBevelBorder());
		this.history.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.history.setFont(historyFont);
		this.scrollPane = new JScrollPane(this.history);
	}
	
	/************************************************************************
	 * Method:   Construct_Display				                            *
	 * Purpose:  Builds the display tab of the program that displays the    *
	 * 			 current calculation being made.                            *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Construct_Display() {
		this.display = new JTextField();
		this.display.setEditable(false);
		this.display.setBackground(Color.decode("#3f4349"));
		this.display.setForeground(Color.white);
		this.display.setBorder(BorderFactory.createRaisedBevelBorder());
		this.display.setHorizontalAlignment(JTextField.TRAILING);
		this.display.setFont(this.displayFont);
	}
	
	/************************************************************************
	 * Method:   Set_Window_Constraints				                        *
	 * Purpose:  Sets the correct size proportions of the history and       *
	 * 			 display UI elements.                                       *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Set_Window_Constraints() {
	    this.constraints.fill = GridBagConstraints.BOTH;
	    this.constraints.gridwidth = 4;
	    this.constraints.gridheight = 1;
	    this.constraints.weightx = 0.5;
	    this.constraints.weighty = 0.3;
	    this.panel.add(this.display, this.constraints);
	    
	    this.constraints.fill = GridBagConstraints.BOTH;
	    this.constraints.gridx = 5;
	    this.constraints.gridy = 0;
	    this.constraints.gridheight = 7;
	    this.constraints.gridwidth = 2;
	    this.constraints.weightx = 1.0;
	    this.constraints.weighty = 1.0;
	    this.panel.add(this.scrollPane, this.constraints);
	}
	
	/************************************************************************
	 * Method:   Construct_Buttons   				                        *
	 * Purpose:  Builds the keypad part of the calculator as buttons.       *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Construct_Buttons() {
		this.buttons = new ArrayList<JButton>();
	    
	    for (int i = 0; i < 20; i++) {
	    	JButton buttonToAdd = new JButton();
	    	buttonToAdd.setForeground(Color.white);
	    	buttonToAdd.setFont(this.buttonFont);
	    	buttonToAdd.addActionListener(this);
	    	switch (i) {
	    	case 0:
	    		buttonToAdd.setText("CE");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 1:
	    		buttonToAdd.setText("C");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_ESCAPE);
	    		break;
	    	case 2:
	    		buttonToAdd.setText("Del");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_BACK_SPACE);
	    		break;
	    	case 3:
	    		buttonToAdd.setText("/");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_DIVIDE);
	    		break;
	    	case 4:
	    		buttonToAdd.setText("7");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD7);
	    		break;
	    	case 5:
	    		buttonToAdd.setText("8");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD8);
	    		break;
	    	case 6:
	    		buttonToAdd.setText("9");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD9);
	    		break;
	    	case 7:
	    		buttonToAdd.setText("*");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_MULTIPLY);
	    		break;
	    	case 8:
	    		buttonToAdd.setText("4");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD4);
	    		break;
	    	case 9:
	    		buttonToAdd.setText("5");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD5);
	    		break;
	    	case 10:
	    		buttonToAdd.setText("6");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD6);
	    		break;
	    	case 11:
	    		buttonToAdd.setText("-");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_SUBTRACT);
	    		break;
	    	case 12:
	    		buttonToAdd.setText("1");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD1);
	    		break;
	    	case 13:
	    		buttonToAdd.setText("2");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD2);
	    		break;
	    	case 14:
	    		buttonToAdd.setText("3");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD3);
	    		break;
	    	case 15:
	    		buttonToAdd.setText("+");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_ADD);
	    		break;
	    	case 16:
	    		buttonToAdd.setText("\u00B1");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 17:
	    		buttonToAdd.setText("0");
	    		buttonToAdd.setBackground(Color.black);
	    		buttonToAdd.setMnemonic(KeyEvent.VK_NUMPAD0);
	    		break;
	    	case 18:
	    		buttonToAdd.setText(".");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_DECIMAL);
	    		break;
	    	case 19:
	    		buttonToAdd.setText("=");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		buttonToAdd.setMnemonic(KeyEvent.VK_ENTER);
	    		break;
	    	}
	    	
	    	this.buttons.add(buttonToAdd);
	    }
	}
	
	/************************************************************************
	 * Method:   Set_Button_Constraints				                        *
	 * Purpose:  Sets the correct size proportions of the keypad buttons.   *
	 * Parameters: None                                                     *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Set_Button_Constraints() {
		for (int i = 0; i < 4; i++) {
	    	this.constraints.fill = GridBagConstraints.BOTH;
		    this.constraints.gridx = i;
		    this.constraints.gridy = 1;
		    this.constraints.gridheight = 1;
		    this.constraints.gridwidth = 1;
		    this.constraints.weightx = 0.5;
		    this.constraints.weighty = 0.5;
		    this.panel.add(this.buttons.get(i), this.constraints);
	    }
	    
	    for (int i = 4; i < 8; i++) {
	    	this.constraints.fill = GridBagConstraints.BOTH;
		    this.constraints.gridx = i - 4;
		    this.constraints.gridy = 2;
		    this.constraints.gridheight = 1;
		    this.constraints.gridwidth = 1;
		    this.constraints.weightx = 0.5;
		    this.constraints.weighty = 0.5;
		    this.panel.add(this.buttons.get(i), this.constraints);
	    }
	    
	    for (int i = 8; i < 12; i++) {
	    	this.constraints.fill = GridBagConstraints.BOTH;
		    this.constraints.gridx = i - 8;
		    this.constraints.gridy = 3;
		    this.constraints.gridheight = 1;
		    this.constraints.gridwidth = 1;
		    this.constraints.weightx = 0.5;
		    this.constraints.weighty = 0.5;
		    this.panel.add(this.buttons.get(i), this.constraints);
	    }
	    
	    for (int i = 12; i < 16; i++) {
	    	this.constraints.fill = GridBagConstraints.BOTH;
		    this.constraints.gridx = i - 12;
		    this.constraints.gridy = 4;
		    this.constraints.gridheight = 1;
		    this.constraints.gridwidth = 1;
		    this.constraints.weightx = 0.5;
		    this.constraints.weighty = 0.5;
		    this.panel.add(this.buttons.get(i), this.constraints);
	    }
	    
	    for (int i = 16; i < 20; i++) {
	    	this.constraints.fill = GridBagConstraints.BOTH;
		    this.constraints.gridx = i - 16;
		    this.constraints.gridy = 5;
		    this.constraints.gridheight = 1;
		    this.constraints.gridwidth = 1;
		    this.constraints.weightx = 0.5;
		    this.constraints.weighty = 0.5;
		    this.panel.add(this.buttons.get(i), this.constraints);
	    }
	}

	/************************************************************************
	 * Method:   actionPerformed    				                        *
	 * Purpose:  Processes actions that the user makes as they interact     *
	 * 			 with the GUI. This includes button presses and changing    *
	 * 			 the program options via the menus.                         *
	 * Parameters:                                                          *
	 * 		e -- The action event logged by the GUI. Used to access the     *
	 * 			 source of the action.                                      *
	 * Return value: None                                                   *
	 ***********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttons.get(0)) {
			if (this.display.getText().equals("") && this.history.getText().equals("")) {
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.errorSound);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				this.display.setText(""); 
				this.history.setText("");
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.boop);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == this.buttons.get(1)) {
			if (this.display.getText().equals("")) {
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.errorSound);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				this.display.setText("");
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.boop);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == this.buttons.get(2)) {
			if (this.display.getText().equals("")) {
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.errorSound);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.boop);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == this.buttons.get(3)) {
			this.display.setText(this.display.getText() + "/");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(4)) {
			this.display.setText(this.display.getText() + "7");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(5)) {
			this.display.setText(this.display.getText() + "8");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(6)) {
			this.display.setText(this.display.getText() + "9");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(7)) {
			this.display.setText(this.display.getText() + "*");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(8)) {
			this.display.setText(this.display.getText() + "4");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(9)) {
			this.display.setText(this.display.getText() + "5");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(10)) {
			this.display.setText(this.display.getText() + "6");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(11)) {
			this.display.setText(this.display.getText() + "-");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(12)) {
			this.display.setText(this.display.getText() + "1");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(13)) {
			this.display.setText(this.display.getText() + "2");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(14)) {
			this.display.setText(this.display.getText() + "3");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(15)) {
			this.display.setText(this.display.getText() + "+");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(16)) {
			if (!this.display.getText().equals("")) {
				if (this.display.getText().charAt(0) == '-') {
					this.display.setText(this.display.getText().substring(1));
				} else {
					this.display.setText("-" + this.display.getText());
				}
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.beep);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.errorSound);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == this.buttons.get(17)) {
			this.display.setText(this.display.getText() + "0");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(18)) {
			this.display.setText(this.display.getText() + ".");
			if (this.areSoundEffectEnabled == true) {
				try {
					this.Play_Sound(this.beep);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.buttons.get(19)) {
			if (!this.display.getText().equals("")) {
				try {
					this.Calculate_Result(this.display.getText());
					this.display.setText("");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.boop);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				if (this.areSoundEffectEnabled == true) {
					try {
						this.Play_Sound(this.errorSound);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == this.exportButton) {
			if (this.history.getText().equals("")) {
				if (this.areSoundEffectEnabled == true) {
        			try {
    					this.Play_Sound(this.errorSound);
    				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
    					e1.printStackTrace();
    				}
        		}
        		JOptionPane.showMessageDialog(this.window,
            		    "Your history is currently empty.",
            		    "Error",
            		    JOptionPane.WARNING_MESSAGE);
			} else {
				String fileName = (String)JOptionPane.showInputDialog(
	                    							 this.window,
	                    							 "Save history as:",
	                    							 "Export History",
	                    							 JOptionPane.PLAIN_MESSAGE);
				fileName += ".txt";
				File exportedHistory = new File(fileName);
				try {
					FileWriter writer = new FileWriter(fileName);
					writer.write(this.history.getText());
					writer.close();
					if (this.areSoundEffectEnabled == true) {
            			try {
        					this.Play_Sound(this.beep);
        				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
        					e1.printStackTrace();
        				}
            		}
            		JOptionPane.showMessageDialog(this.window,
                		    (fileName + " saved successfully."),
                		    "Success",
                		    JOptionPane.PLAIN_MESSAGE);
            		if (!Desktop.isDesktopSupported()) {
        				JOptionPane.showMessageDialog(this.window,
                    		    "ERROR OPENING SAVED FILE",
                    		    "Error",
                    		    JOptionPane.ERROR_MESSAGE);
        			} else {
        				Desktop desktop = Desktop.getDesktop();
        				if (exportedHistory.exists()) {
        					try {
        						desktop.open(exportedHistory);
        					} catch (IOException e1) {
        						e1.printStackTrace();
        					}
        				} else {
        					JOptionPane.showMessageDialog(this.window,
        	            		    (fileName + " not found!"),
        	            		    "Error",
        	            		    JOptionPane.ERROR_MESSAGE);
        				}
        			}
				} catch (IOException e1) {
					if (this.areSoundEffectEnabled == true) {
	        			try {
	    					this.Play_Sound(this.errorSound);
	    				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
	    					e2.printStackTrace();
	    				}
	        			JOptionPane.showMessageDialog(this.window,
	                		    "Error exporting history.",
	                		    "Error",
	                		    JOptionPane.ERROR_MESSAGE);
	        		}
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.darkThemeOption) {
			this.history.setBackground(Color.decode("#33353a"));
			this.history.setForeground(Color.white);
			this.display.setBackground(Color.decode("#3f4349"));
			this.display.setForeground(Color.white);
		    for (int i = 0; i < 20; i++) {
		    	this.buttons.get(i).setForeground(Color.white);
		    	if (i < 4) {
		    		this.buttons.get(i).setBackground(Color.decode("#191a1c"));
		    	} else if (i >= 4 && i < 7) {
		    		this.buttons.get(i).setBackground(Color.black);
		    	} else if (i == 7) {
		    		this.buttons.get(i).setBackground(Color.decode("#191a1c"));
		    	} else if (i >= 8 && i < 11) {
		    		this.buttons.get(i).setBackground(Color.black);
		    	} else if (i == 11) {
		    		this.buttons.get(i).setBackground(Color.decode("#191a1c"));
		    	} else if (i >= 12 && i < 15) {
		    		this.buttons.get(i).setBackground(Color.black);
		    	} else if (i >= 15 && i < 17) {
		    		this.buttons.get(i).setBackground(Color.decode("#191a1c"));
		    	} else if (i == 17) {
		    		this.buttons.get(i).setBackground(Color.black);
		    	} else {
		    		this.buttons.get(i).setBackground(Color.decode("#191a1c"));
		    	}
		    }
		} else if (e.getSource() == this.lightThemeOption) {
			this.history.setBackground(Color.white);
			this.history.setForeground(Color.black);
			this.display.setBackground(Color.white);
			this.display.setForeground(Color.black);
		    for (int i = 0; i < 20; i++) {
		    	this.buttons.get(i).setForeground(Color.black);
		    	if (i < 4) {
		    		this.buttons.get(i).setBackground(Color.lightGray);
		    	} else if (i >= 4 && i < 7) {
		    		this.buttons.get(i).setBackground(Color.white);
		    	} else if (i == 7) {
		    		this.buttons.get(i).setBackground(Color.lightGray);
		    	} else if (i >= 8 && i < 11) {
		    		this.buttons.get(i).setBackground(Color.white);
		    	} else if (i == 11) {
		    		this.buttons.get(i).setBackground(Color.lightGray);
		    	} else if (i >= 12 && i < 15) {
		    		this.buttons.get(i).setBackground(Color.white);
		    	} else if (i >= 15 && i < 17) {
		    		this.buttons.get(i).setBackground(Color.lightGray);
		    	} else if (i == 17) {
		    		this.buttons.get(i).setBackground(Color.white);
		    	} else {
		    		this.buttons.get(i).setBackground(Color.lightGray);
		    	}
		    }
		} else if (e.getSource() == this.soundEffectsToggle) {
			if (this.areSoundEffectEnabled == true) {
				this.areSoundEffectEnabled = false;
				this.soundEffectsToggle.setText("Off");
			} else {
				this.areSoundEffectEnabled = true;
				this.soundEffectsToggle.setText("On");
			}
		} else if (e.getSource() == this.menuItem) {
			File readMe = new File("README.md");
			if (!Desktop.isDesktopSupported()) {
				JOptionPane.showMessageDialog(this.window,
            		    "ERROR OPENING README",
            		    "Error",
            		    JOptionPane.ERROR_MESSAGE);
			} else {
				Desktop desktop = Desktop.getDesktop();
				if (readMe.exists()) {
					try {
						desktop.open(readMe);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this.window,
	            		    "README not found!",
	            		    "Error",
	            		    JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/************************************************************************
	 * Method:   Play_Sound         				                        *
	 * Purpose:  Handles playing a sound effect based on what button is     *
	 * 			 pressed.                                                   *
	 * Parameters:                                                          *
	 * 		fileToPlay -- The sound file that is being triggered.           *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Play_Sound(File fileToPlay) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileToPlay);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(0);
		clip.start();
	}
	
	/************************************************************************
	 * Method:   Calculate_Result     				                        *
	 * Purpose:  Calculates the result of the user inputed calculation and  *
	 * 			 either updates the history UI or displays an error message.*
	 * Parameters:                                                          *
	 * 		problem -- The calculation to process. This is passed to this   *
	 * 				   function by getting the display areas current text.  *
	 * Return value: None                                                   *
	 ***********************************************************************/
	public void Calculate_Result(String problem) throws IOException {
		try(JShell js = JShell.create();) {
            js.onSnippetEvent(snip -> {
                if (snip.status() == jdk.jshell.Snippet.Status.VALID) {
                	if (String.valueOf(snip.value()).equals("null")) {
                		if (this.areSoundEffectEnabled == true) {
                			try {
            					this.Play_Sound(this.errorSound);
            				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            					e1.printStackTrace();
            				}
                		}
                		JOptionPane.showMessageDialog(this.window,
                    		    "Invalid Operation.",
                    		    "Error",
                    		    JOptionPane.ERROR_MESSAGE);
                	} else {
                		this.history.setText(this.history.getText() + "\n" + String.valueOf(snip.value()) + " = " + this.display.getText());
                	}
                } else {
                	if (this.areSoundEffectEnabled == true) {
                		try {
        					this.Play_Sound(this.errorSound);
        				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
        					e1.printStackTrace();
        				}
                	}
                	JOptionPane.showMessageDialog(this.window,
                		    "Invalid Operation.",
                		    "Error",
                		    JOptionPane.ERROR_MESSAGE);
                }
            });
            try {
            	js.eval(js.sourceCodeAnalysis().analyzeCompletion(problem).source());
            } catch (Exception e) {
            	if (this.areSoundEffectEnabled == true) {
            		try {
    					this.Play_Sound(this.errorSound);
    				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
    					e1.printStackTrace();
    				}
            	}
            	JOptionPane.showMessageDialog(this.window,
            		    "Invalid Operation.",
            		    "Error",
            		    JOptionPane.ERROR_MESSAGE);
            }
        }
	}
}
