package view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.ManagerController;
import controller.ProductController;
import controller.RegistrationController;
import core.model.Model;
import core.view.View;
import model.ProductModel;

public class ManagerView extends View{
	
	JPanel top, mid, bot;
	JLabel monthLbl, yearLbl, welcomeLbl;
	JTextField monthTxtField, yearTxtField;
	JButton viewReport, addStaff, viewAll;
	
	public ManagerView() {
		// TODO Auto-generated constructor stub
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.height = 300;
		this.width = 500;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		top = new JPanel(new FlowLayout());
		GridLayout gl = new GridLayout(0, 2);
		gl.setVgap(30);
		gl.setHgap(20);
		mid = new JPanel(gl);
		bot = new JPanel(new FlowLayout());
		
		welcomeLbl = new JLabel("Manager Menu: ");
		monthLbl = new JLabel("Input Month: ");
		yearLbl = new JLabel("Input Year: ");
		
		monthTxtField = new JTextField();
		yearTxtField = new JTextField();
		
		viewReport = new JButton("View Transaction Report");
		addStaff = new JButton("Add Staff");
		viewAll = new JButton("View All Transaction");
		
		this.setTitle("Manager Page");
	}

	@Override
	public void addComponent() {
		top.add(welcomeLbl);
		
		mid.add(monthLbl);
		mid.add(monthTxtField);
		mid.add(yearLbl);
		mid.add(yearTxtField);
		mid.setBorder(new EmptyBorder(50, 50, 50, 50));
		bot.add(viewAll);
		bot.add(viewReport);
		bot.add(addStaff);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	

	}

	@Override
	public void addListener() {
		viewReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(yearTxtField.getDocument().getLength()==0||(Pattern.matches("^[a-zA-Z]+$",yearTxtField.getText()))) {
		            JOptionPane.showMessageDialog(null, "Input year number");  
				}
				else if(monthTxtField.getDocument().getLength()==0||(Pattern.matches("^[a-zA-Z]+$",monthTxtField.getText()))) {
		            JOptionPane.showMessageDialog(null, "Input month number");  
				}
				else {
					dispose();
					ManagerController.getInstance().setDate(Integer.parseInt(yearTxtField.getText()), Integer.parseInt(monthTxtField.getText()));
					ManagerController.getInstance().viewTransaction().showForm();					
				}
			}
		});
		addStaff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagerController.getInstance().viewAddStaff().showForm();
			}
		});
		viewAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagerController.getInstance().viewAll().showForm();
				
			}
		});
	}

}
