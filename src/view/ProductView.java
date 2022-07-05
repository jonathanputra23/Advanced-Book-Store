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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.ProductController;
import controller.RegistrationController;
import core.model.Model;
import core.view.View;
import model.ProductModel;

public class ProductView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel productIdLbl, productNameLbl, productAuthorLbl, productPriceLbl, productStockLbl, productIdValueLbl,
	productNameValueLbl, productAuthorValueLbl, productPriceValueLbl, productCurrentStock, wantedStockLbl;
	JTextField productStockTxtField;
	JButton addCart, viewCart;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	private Integer userId=ProductController.getInstance().getUserId();
	public ProductView() {
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
		mid = new JPanel(new GridLayout(6,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		productIdLbl = new JLabel("Product ID: ");
		productNameLbl = new JLabel("Product Name: ");
		productAuthorLbl = new JLabel("Product Author: ");
		productPriceLbl = new JLabel("Product Price: ");
		productStockLbl = new JLabel("Product Stock: ");
		wantedStockLbl = new JLabel("Add to Cart: ");
		productIdValueLbl = new JLabel("-");
		productNameValueLbl = new JLabel("-");
		productAuthorValueLbl = new JLabel("-");
		productPriceValueLbl = new JLabel("-");
		productCurrentStock = new JLabel("0");
		productStockTxtField = new JTextField();
		addCart = new JButton("Add to Cart");
		viewCart = new JButton("View Cart");

		this.setTitle("Product View Page");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		top.add(sp);
		
		mid.add(productIdLbl);
		mid.add(productIdValueLbl);
		mid.add(productNameLbl);
		mid.add(productNameValueLbl);
		mid.add(productPriceLbl);
		mid.add(productPriceValueLbl);
		mid.add(productStockLbl);
		mid.add(productCurrentStock);
		mid.add(wantedStockLbl);
		mid.add(productStockTxtField);
		mid.setBorder(new EmptyBorder(20, 20, 20, 20));
		bot.add(addCart);
		bot.add(viewCart);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	
		loadData();

	}

	@Override
	public void addListener() {
		addCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Product is not selected");
				}
				else if(Pattern.matches("^[a-zA-Z]+$", productStockTxtField.getText())) {
					JOptionPane.showMessageDialog(null, "Please input valid number");
				}
				else if(productStockTxtField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input number of stock");
				}
				else if(Integer.parseInt(productStockTxtField.getText())<1) {
					JOptionPane.showMessageDialog(null, "Please input number of stock at least 1");
				}
				else{
					Integer productId = Integer.parseInt(productIdValueLbl.getText());
					String productName = productNameValueLbl.getText();
					String productAuthor = productAuthorValueLbl.getText();
					Integer productPrice = Integer.parseInt(productPriceValueLbl.getText());
					Integer productStock = Integer.parseInt(productCurrentStock.getText());
					Integer ws = Integer.parseInt(productStockTxtField.getText());
					ProductController.getInstance().addToCart(productId, productName, productAuthor, productPrice, productStock, ws);
					loadData();					
				}
			}
		});
		viewCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				CartController.getInstance().passUserId(userId);
				CartController.getInstance().view().showForm();
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
				productIdValueLbl.setText(table.getValueAt(row, 0).toString());
				productNameValueLbl.setText(table.getValueAt(row, 1).toString());
				productAuthorValueLbl.setText(table.getValueAt(row, 2).toString());
				productPriceValueLbl.setText(table.getValueAt(row, 3).toString());
				productCurrentStock.setText(table.getValueAt(row, 4).toString());
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
		header.add("Product Stock");
		
		Vector<Model> listProduct  = ProductController.getInstance().getAll();
		
		for(Model model:listProduct) {
			ProductModel p = (ProductModel) model;
			detail = new Vector<>();
			
			detail.add(p.getProductId().toString());
			detail.add(p.getProductName());
			detail.add(p.getProductAuthor());
			detail.add(p.getProductPrice().toString());
			detail.add(p.getProductStock().toString());
			
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
