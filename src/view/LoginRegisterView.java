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

import controller.AdminController;
import controller.LoginRegisterController;
import controller.ManagerController;
import controller.ProductController;
import controller.PromoController;
import controller.RegistrationController;
import core.view.View;

public class LoginRegisterView extends View{
	JPanel top, mid, bot;
	JLabel titleLbl, usernameLbl, passwordLbl;
	JTextField usernameTxt, passwordTxt;
	JButton login, register;
	private Integer currRole;
	
	public LoginRegisterView() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.height = 300;
		this.width = 600;
	}

	@Override
	public void initialize() {
		top = new JPanel(new FlowLayout());
		
		GridLayout gl = new GridLayout(2, 2);
		gl.setVgap(20);
		mid = new JPanel(gl);
		bot = new JPanel(new FlowLayout());
		
		titleLbl = new JLabel("Purple Lane Bookstore Login");
		usernameLbl = new JLabel("Username");
		passwordLbl = new JLabel("Password");
		
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		login = new JButton("Login");
		register = new JButton("Register");
		
		this.setTitle("Login Page");
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
		
		mid.setBorder(new EmptyBorder(50, 50, 50, 50));
		//bot
		bot.add(login);
		bot.add(register);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
	}

	@Override
	public void addListener() {
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameTxt.getText();
				String password = passwordTxt.getText();
				currRole = LoginRegisterController.getInstance().check(username, password);
				if(currRole==1) {
					JOptionPane.showMessageDialog(null, "Welcome, User");
					ProductController.getInstance().view().showForm();
					dispose();
				}
				else if(currRole==2) {
					JOptionPane.showMessageDialog(null, "Welcome, Manager");
					ManagerController.getInstance().view().showForm();
					dispose();
				}
				else if(currRole==3) {
					JOptionPane.showMessageDialog(null, "Welcome, Promo Team");
					PromoController.getInstance().view().showForm();
					dispose();
				}
				else if(currRole==4) {
					JOptionPane.showMessageDialog(null, "Welcome, Admin");
					AdminController.getInstance().view().showForm();
					dispose();
				}
			}
		});
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationController.getInstance().view().showForm();
			}
		});
		
	}
}
