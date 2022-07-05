package view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginRegisterController;
import controller.RegistrationController;
import core.view.View;

public class RegistrationView extends View{
	JPanel top, mid, bot;
	JLabel titleLbl, usernameLbl, passwordLbl;
	JTextField usernameTxt, passwordTxt;
	JCheckBox agree;
	JButton cancel, register;
	
	public RegistrationView() {
		super();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.height = 300;
		this.width = 500;
	}

	@Override
	public void initialize() {
		top = new JPanel(new FlowLayout());
		
		GridLayout gl = new GridLayout(3, 2);
		gl.setVgap(10);
		mid = new JPanel(gl);
		bot = new JPanel(new FlowLayout());
		
		titleLbl = new JLabel("Registration");
		usernameLbl = new JLabel("Username");
		passwordLbl = new JLabel("Password");
		
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		agree = new JCheckBox("I agree");
		
		cancel = new JButton("Cancel");
		register = new JButton("Register");
		
		this.setTitle("Register Page");
		
	}

	@Override
	public void addComponent() {
		//top
		top.add(titleLbl);
		
		//mid
		mid.add(usernameLbl);
		mid.add(usernameTxt);
		mid.add(passwordLbl);
		mid.add(passwordTxt);
		mid.add(agree);
		
		mid.setBorder(new EmptyBorder(50, 50, 50, 50));
		//bot
		bot.add(cancel);
		bot.add(register);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(usernameTxt.getDocument().getLength()<8) {
		            JOptionPane.showMessageDialog(null, "Username minimum 8 character");  
				}
				else if(passwordTxt.getDocument().getLength()<8) {
		            JOptionPane.showMessageDialog(null, "Password minimum 8 character ");  
				}
				else if(!agree.isSelected()) {
					JOptionPane.showMessageDialog(null, "Need to Agree");  
				}
				else {
					Integer roleId = 1; //user=1
					String username = usernameTxt.getText();
					String password = passwordTxt.getText();
					RegistrationController.getInstance().insert(roleId, username, password);
		            JOptionPane.showMessageDialog(null, "Successfully Registered");  
		            dispose();
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
