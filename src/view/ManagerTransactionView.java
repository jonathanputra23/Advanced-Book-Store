package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.ManagerController;
import controller.ProductController;
import controller.RegistrationController;
import core.model.Model;
import core.view.View;
import model.ManagerModel;
import model.ProductModel;

public class ManagerTransactionView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JButton detailButton, backButton, backToMenuButton;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Integer transactionId;
	public ManagerTransactionView() {
		// TODO Auto-generated constructor stub
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.height = 700;
		this.width = 600;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		top = new JPanel();
		mid = new JPanel(new GridLayout(0,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		detailButton = new JButton("Detail Transaction");
		backButton = new JButton("Back Button");
		backToMenuButton = new JButton("Back Button");
		
		this.setTitle("Manager Transaction View Page");
	}

	@Override
	public void addComponent() {
		top.add(sp);
		bot.add(detailButton);
		bot.add(backButton);
		backButton.setVisible(false);
		bot.add(backToMenuButton);
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	
		loadData();

	}

	@Override
	public void addListener() {
		backToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagerController.getInstance().view().showForm();
				
			}
		});
		detailButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectionModel().isSelectionEmpty()) {
		            JOptionPane.showMessageDialog(null, "Table is not selected"); 
				}
				else {
					ManagerController.getInstance().setTransactionId(transactionId);
					loadTransactionDetailData();
					backButton.setVisible(true);
					detailButton.setVisible(false);
					backToMenuButton.setVisible(false);
				}
			}
		});
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
				backButton.setVisible(false);
				detailButton.setVisible(true);
				backToMenuButton.setVisible(true);
			}
		});
	table.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			transactionId = Integer.parseInt(table.getValueAt(row, 0).toString());
		}
	});
	}

	public void loadData() {
		data = new Vector<>();
		header = new Vector<>();
		
		header.add("Transaction Id");
		header.add("Transaction Date");
		header.add("Payment Type");
		header.add("Card Number");
		header.add("Promo Code");
		header.add("User Id");
		
		Vector<Model> listTransaction  = ManagerController.getInstance().getAll();
		
		for(Model model:listTransaction) {
			ManagerModel p = (ManagerModel) model;
			detail = new Vector<>();
			
			detail.add(p.getTransactionId().toString());
			detail.add(p.getTransactionDate().toString());
			detail.add(p.getPaymentType());
			detail.add(p.getCardNumber().toString());
			detail.add(p.getPromoCode());
			detail.add(p.getUserId().toString());
			
			data.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
	}
	public void loadTransactionDetailData() {
		data = new Vector<>();
		header = new Vector<>();
		
		header.add("Transaction Id");
		header.add("Product Id");
		header.add("Product Quantity");
		
		Vector<Model> listTransaction  = ManagerController.getInstance().getAllTransactionDetail();
		
		for(Model model:listTransaction) {
			ManagerModel x = (ManagerModel) model;
			detail = new Vector<>();
			detail.add(x.getTransactionId().toString());
			detail.add(x.getProductId().toString());
			detail.add(x.getProductQty().toString());
			
			data.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
		JOptionPane.showMessageDialog(null, "Transaction Detail is loaded");
	}
}
