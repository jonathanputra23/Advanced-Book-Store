package view;

import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableModel;

import controller.AdminController;
import core.model.Model;
import core.view.View;
import model.AdminModel;

public class AdminView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, nameLbl, priceLbl, AuthLbl, StockLbl;
	JTextField nameTxt, priceTxt, AuthTxt, StockTxt;
	JButton insert, update, delete;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public AdminView() {
		// TODO Auto-generated constructor stub
		super();
		this.height = 700;
		this.width = 600;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		top = new JPanel();
		mid = new JPanel(new GridLayout(5,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		idLbl = new JLabel("Product ID: ");
		nameLbl = new JLabel("Product Name: ");
		priceLbl = new JLabel("Product Price: ");
		AuthLbl = new JLabel("Product Author: ");
		StockLbl = new JLabel("Product Stock: ");
		idValue = new JLabel("-");
		AuthTxt = new JTextField();
		StockTxt = new JTextField();
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");

		this.setTitle("Admin Page");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		top.add(sp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(AuthLbl);
		mid.add(AuthTxt);
		mid.add(priceLbl);
		mid.add(priceTxt);
		mid.add(StockLbl);
		mid.add(StockTxt);
		
		bot.add(insert);
		bot.add(update);
		bot.add(delete);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		loadData();
	

	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameTxt.getDocument().getLength()==0) {
					JOptionPane.showMessageDialog(null, "Input valid product name");
				}
				else if(AuthTxt.getDocument().getLength()==0) {
					JOptionPane.showMessageDialog(null, "Input valid product author");
				}
				else if(priceTxt.getDocument().getLength()==0) {
					JOptionPane.showMessageDialog(null, "Product Price must be more than 0");
				}
				else if(!Pattern.matches("^[0-9]+$", priceTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Input valid product price");
				}
				else if(StockTxt.getDocument().getLength()==0) {
					JOptionPane.showMessageDialog(null, "Product Stock must be more than 0");
				}
				else if(!Pattern.matches("^[0-9]+$", StockTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Input valid product stock");
				}
				else {
					String name = nameTxt.getText();
					Integer price = Integer.parseInt(priceTxt.getText());
					String author = AuthTxt.getText();
					Integer stock = Integer.parseInt(StockTxt.getText());
					AdminController.getInstance().insert(name, price, author, stock);
					loadData();
					JOptionPane.showMessageDialog(null, "Successfully add product");
				}
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
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				idValue.setText(table.getValueAt(row,0).toString());
				nameTxt.setText(table.getValueAt(row,1).toString());
				priceTxt.setText(table.getValueAt(row,2).toString());
				AuthTxt.setText(table.getValueAt(row,3).toString());
				StockTxt.setText(table.getValueAt(row,4).toString());

			}
		});
			
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Product is not selected");
				}
				else if(nameTxt.getDocument().getLength()==0) {
					JOptionPane.showMessageDialog(null, "Input valid product name");
				}
				else if(Integer.parseInt(priceTxt.getText())<0||!Pattern.matches("^[0-9]+$", priceTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Input valid product price");
				}
				else if(AuthTxt.getDocument().getLength()==0) {
					JOptionPane.showMessageDialog(null, "Input valid product author");
				}
				else if(Integer.parseInt(StockTxt.getText())<0||!Pattern.matches("^[0-9]+$", StockTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Input valid product stock");
				}
				else {
					Integer id = Integer.parseInt(idValue.getText());
					String name = nameTxt.getText();
					Integer price = Integer.parseInt(priceTxt.getText());
					String author = AuthTxt.getText();
					Integer stock = Integer.parseInt(StockTxt.getText());
					AdminController.getInstance().update(name, price, author, stock, id);
					loadData();
					JOptionPane.showMessageDialog(null, "Successfully updated product");
				}
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Product is not selected");
				}
				else {
					Integer id = Integer.parseInt(idValue.getText());
					AdminController.getInstance().delete(id);
					loadData();
					JOptionPane.showMessageDialog(null, "Successfully deleted product");
				}
			}
		});			
		}

	
	
	public void loadData() {
		data = new Vector<>();
		header = new Vector<>();
		header.add("Product ID");
		header.add("Product Name");
		header.add("Product Price");
		header.add("Product Author");
		header.add("Product Stock");
		
		Vector<Model> listProduct = AdminController.getInstance().getAll();
		for (Model model : listProduct){
			AdminModel p = (AdminModel) model;
			detail = new Vector<>();
			
			detail.add(p.getId().toString());
			detail.add(p.getName());
			detail.add(p.getPrice().toString());
			detail.add(p.getAuthor());
			detail.add(p.getStock().toString());
			
			data.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
		public boolean isCellEditable(int row, int column) {
			return false;
			}
		};
		
		table.setModel(dtm);
	}
	

}
