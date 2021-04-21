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
*                                                                     *
* COPYRIGHT:                                                          *
* This program uses the open source MIT license. See LICENSE.txt for  *
* more information.                                                   *
*                                                                     *
**********************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	
	private JFrame window;
	private JPanel panel;
	private GridBagConstraints constraints;
	private JScrollPane scrollPane;
	private JTextArea history;
	private JTextField display;
	private ArrayList<JButton> buttons;
	private Font displayFont;
	private Font historyFont;
	private Font buttonFont;
	private File beep;
	private File boop;
	private File errorSound;
	private Clip beepClip;
	private Clip boopClip;
	private Clip errorClip;
	private AudioInputStream audioStream;
	
	public GUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.displayFont = new Font("Helvetica", Font.BOLD, 32);
		this.historyFont = new Font("Helvetica", Font.BOLD, 24);
		this.buttonFont = new Font("Helvetica", Font.BOLD, 18);
		
		this.beep = new File("Robot_blip-Marianne_Gagnon.wav");
		this.boop = new File("Robot_blip_2-Marianne_Gagnon.wav");
		this.errorSound = new File("A-Tone.wav");
		
		this.audioStream = AudioSystem.getAudioInputStream(beep);
		this.beepClip = AudioSystem.getClip();
		
		this.audioStream = AudioSystem.getAudioInputStream(boop);
		this.boopClip = AudioSystem.getClip();
		
		this.audioStream = AudioSystem.getAudioInputStream(errorSound);
		this.errorClip = AudioSystem.getClip();
		
		this.window = new JFrame();
		
		this.panel = new JPanel();
		
		this.history = new JTextArea();
		this.history.setEditable(false);
		this.history.setBackground(Color.decode("#33353a"));
		this.history.setForeground(Color.white);
		this.history.setBorder(BorderFactory.createRaisedBevelBorder());
		this.history.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//this.history.setLineWrap(true);
		this.history.setFont(historyFont);
		
		this.display = new JTextField();
		this.display.setEditable(false);
		this.display.setBackground(Color.decode("#3f4349"));
		this.display.setForeground(Color.white);
		this.display.setBorder(BorderFactory.createRaisedBevelBorder());
		this.display.setHorizontalAlignment(JTextField.TRAILING);
		this.display.setFont(this.displayFont);
		
		this.scrollPane = new JScrollPane(this.history);
		
	    this.panel.setLayout(new GridBagLayout());
	    this.panel.setBorder(BorderFactory.createEmptyBorder());
	    
	    this.constraints = new GridBagConstraints();
	    
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
	    		break;
	    	case 2:
	    		buttonToAdd.setText("Del");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 3:
	    		buttonToAdd.setText("/");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 4:
	    		buttonToAdd.setText("7");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 5:
	    		buttonToAdd.setText("8");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 6:
	    		buttonToAdd.setText("9");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 7:
	    		buttonToAdd.setText("*");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 8:
	    		buttonToAdd.setText("4");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 9:
	    		buttonToAdd.setText("5");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 10:
	    		buttonToAdd.setText("6");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 11:
	    		buttonToAdd.setText("-");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 12:
	    		buttonToAdd.setText("1");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 13:
	    		buttonToAdd.setText("2");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 14:
	    		buttonToAdd.setText("3");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 15:
	    		buttonToAdd.setText("+");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 16:
	    		buttonToAdd.setText("\u00B1");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 17:
	    		buttonToAdd.setText("0");
	    		buttonToAdd.setBackground(Color.black);
	    		break;
	    	case 18:
	    		buttonToAdd.setText(".");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	case 19:
	    		buttonToAdd.setText("=");
	    		buttonToAdd.setBackground(Color.decode("#191a1c"));
	    		break;
	    	}
	    	
	    	this.buttons.add(buttonToAdd);
	    }
	    
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
	    
	    this.window.setBounds(250, 250, 800, 800);
	    this.window.add(panel, BorderLayout.CENTER);
	    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.window.setTitle("Calculator");
	    this.window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttons.get(0)) {
			this.display.setText("");
			this.history.setText("");
		} else if (e.getSource() == this.buttons.get(1)) {
			this.display.setText("");
		} else if (e.getSource() == this.buttons.get(2)) {
			if (this.display.getText().equals("")) {
				//play error sound here
			} else {
				this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));
			}
		} else if (e.getSource() == this.buttons.get(3)) {
			this.display.setText(this.display.getText() + "/");
			this.beepClip.start();
		} else if (e.getSource() == this.buttons.get(4)) {
			this.display.setText(this.display.getText() + "7");
		} else if (e.getSource() == this.buttons.get(5)) {
			this.display.setText(this.display.getText() + "8");
		} else if (e.getSource() == this.buttons.get(6)) {
			this.display.setText(this.display.getText() + "9");
		} else if (e.getSource() == this.buttons.get(7)) {
			this.display.setText(this.display.getText() + "*");
		} else if (e.getSource() == this.buttons.get(8)) {
			this.display.setText(this.display.getText() + "4");
		} else if (e.getSource() == this.buttons.get(9)) {
			this.display.setText(this.display.getText() + "5");
		} else if (e.getSource() == this.buttons.get(10)) {
			this.display.setText(this.display.getText() + "6");
		} else if (e.getSource() == this.buttons.get(11)) {
			this.display.setText(this.display.getText() + "-");
		} else if (e.getSource() == this.buttons.get(12)) {
			this.display.setText(this.display.getText() + "1");
		} else if (e.getSource() == this.buttons.get(13)) {
			this.display.setText(this.display.getText() + "2");
		} else if (e.getSource() == this.buttons.get(14)) {
			this.display.setText(this.display.getText() + "3");
		} else if (e.getSource() == this.buttons.get(15)) {
			this.display.setText(this.display.getText() + "+");
		} else if (e.getSource() == this.buttons.get(16)) {
			this.display.setText(this.display.getText() + "\u00B1");
		} else if (e.getSource() == this.buttons.get(17)) {
			this.display.setText(this.display.getText() + "0");
		} else if (e.getSource() == this.buttons.get(18)) {
			this.display.setText(this.display.getText() + ".");
		} else if (e.getSource() == this.buttons.get(19)) {
			this.history.setText(this.history.getText() + "\n" + this.display.getText());
			this.display.setText("");
		}
	}
}
