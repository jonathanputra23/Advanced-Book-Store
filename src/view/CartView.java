package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

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
import controller.CheckoutController;
import controller.ProductController;
import controller.RegistrationController;
import core.model.Model;
import core.view.View;
import model.CartModel;
import model.ProductModel;

public class CartView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel productIdLbl, userIdLbl, productQtyLbl, productNameLbl, productPriceLbl, productAuthorLbl,
	userIdVal, productIdVal, productQtyVal, productNameVal, productPriceVal, productAuthorVal,
	totalPriceLbl, totalPriceVal;
	JButton checkout, removeItem, backButton;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	
	
	public CartView() {
		// TODO Auto-generated constructor stub
		super();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.height = 700;
		this.width = 500;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		top = new JPanel();
		mid = new JPanel(new GridLayout(3,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		userIdLbl = new JLabel("User ID: ");
		productIdLbl = new JLabel("Product ID: ");
		productNameLbl = new JLabel("Product Name: ");
		productAuthorLbl = new JLabel("Product Author: ");
		productPriceLbl = new JLabel("Product Price: ");
		productQtyLbl = new JLabel("Product Quantity: ");
		totalPriceLbl = new JLabel("Total Price: ");
		
		userIdVal = new JLabel("-");
		productIdVal = new JLabel("-");
		productNameVal = new JLabel("-");
		productAuthorVal = new JLabel("-");
		productPriceVal = new JLabel("-");
		productQtyVal = new JLabel("-");
		totalPriceVal = new JLabel("0");
		
		checkout = new JButton("Checkout");
		removeItem = new JButton("Remove");
		backButton = new JButton("Back");
		
		this.setTitle("Cart View Page");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		top.add(sp);
		
		mid.add(productIdLbl);
		mid.add(productIdVal);
		mid.add(productNameLbl);
		mid.add(productNameVal);
		mid.add(productAuthorLbl);
		mid.add(productAuthorVal);
		mid.add(productPriceLbl);
		mid.add(productPriceVal);
		mid.add(productQtyLbl);
		mid.add(productQtyVal);
		mid.add(totalPriceLbl);
		mid.add(totalPriceVal);
		mid.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		bot.add(checkout);
		bot.add(removeItem);
		bot.add(backButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		loadData();

	}

	@Override
	public void addListener() {
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProductController.getInstance().view().showForm();
				
			}
		});
		checkout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				CheckoutController.getInstance().view().showForm();
			}
		});
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Product is not selected");
				}
				else {
					Integer productId = Integer.parseInt(productIdVal.getText());
					Integer productQty = Integer.parseInt(productQtyVal.getText());
					CartController.getInstance().delete(productId, productQty);
					loadData();				
					JOptionPane.showMessageDialog(null, "Product is removed from cart");
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
				int row = table.getSelectedRow();
				productIdVal.setText(table.getValueAt(row, 0).toString());
				productNameVal.setText(table.getValueAt(row, 1).toString());
				productAuthorVal.setText(table.getValueAt(row, 2).toString());
				productPriceVal.setText(table.getValueAt(row, 3).toString());
				productQtyVal.setText(table.getValueAt(row, 4).toString());
				
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ProductController.getInstance().view().showForm();
				super.windowClosing(e);
			}
		});

	}
	public void loadData() {
		data = new Vector<>();
		header = new Vector<>();
		
		header.add("Product Id");
		header.add("Product Name");
		header.add("Product Author");
		header.add("Product Price");
		header.add("Product Qty");
		
		Vector<Model> listProduct  = CartController.getInstance().getAll();
		for(Model model:listProduct) {
			CartModel p = (CartModel) model;
			detail = new Vector<>();
			
			detail.add(p.getProductId().toString());
			detail.add(p.getProductName());
			detail.add(p.getProductAuthor());
			detail.add(p.getProductPrice().toString());
			detail.add(p.getProductQty().toString());
			data.add(detail);
			totalPriceVal.setText(p.getTotalPrice().toString());
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
	}
}
