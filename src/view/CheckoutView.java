package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.CheckoutController;
import controller.ProductController;
import core.model.Model;
import core.view.View;
import model.CartModel;
import model.CheckoutModel;

public class CheckoutView extends View{
	
	JPanel top, mid, bot, paymentPnl, promoPnl;
	JTable table;
	JScrollPane sp;
	JLabel paymentTypeLbl, cardNumLbl, promoCodeLbl, totalPriceLbl, totalPriceVal, promoValLbl, promoValVal;
	JRadioButton cashRB, creditRB;
	JTextField cardNumVal, promoCodeVal;
	JButton payButton, cancelButton, checkPromoVal;
	ButtonGroup paymentRB;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	
	public CheckoutView() {
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
		mid = new JPanel(new GridLayout(0,2));
		bot = new JPanel();
		paymentPnl = new JPanel();
		promoPnl = new JPanel(new GridLayout(1,0));
		table = new JTable();
		sp = new JScrollPane(table);
		paymentTypeLbl = new JLabel("Payment Type: ");
		cardNumLbl = new JLabel("Card Number: ");
		promoCodeLbl = new JLabel("Promo Code: ");
		promoValLbl = new JLabel("Promo Discount (Rupiah): ");
		totalPriceLbl = new JLabel("Total Price: ");
		
		cashRB = new JRadioButton("Cash");
		cashRB.setSelected(true);
		creditRB = new JRadioButton("Credit");
		
		cashRB.setActionCommand("Cash");
		creditRB.setActionCommand("Credit");
		paymentPnl.add(cashRB);
		paymentPnl.add(creditRB);
		paymentRB = new ButtonGroup();
		paymentRB.add(cashRB);
		paymentRB.add(creditRB);
		
		cardNumVal = new JTextField();
		promoCodeVal = new JTextField();
		promoValVal = new JLabel("0");
		totalPriceVal = new JLabel("0");
		checkPromoVal = new JButton("Go");
		payButton = new JButton("Pay");
		cancelButton = new JButton("Cancel");

		this.setTitle("Checkout Page");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		top.add(sp);

		mid.add(paymentTypeLbl);
		mid.add(paymentPnl);
		
		mid.add(cardNumLbl);
		mid.add(cardNumVal);
		cardNumLbl.setVisible(false);
		cardNumVal.setVisible(false);
		
		mid.add(promoCodeLbl);
		promoPnl.add(promoCodeVal);
		promoPnl.add(checkPromoVal);
		mid.add(promoPnl);
		
		mid.add(promoValLbl);
		mid.add(promoValVal);
		mid.add(totalPriceLbl);
		mid.add(totalPriceVal);
		mid.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		bot.add(payButton);
		bot.add(cancelButton);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		loadData();

	}

	@Override
	public void addListener() {
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				CartController.getInstance().view().showForm();
				
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CartController.getInstance().view().showForm();
				super.windowClosing(e);
			}
		});
		payButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(promoCodeVal.getDocument().getLength()!=0) {
					Integer priceAfterPromo=0;
					Integer currPromoVal;
					currPromoVal = CheckoutController.getInstance().checkPromo(promoCodeVal.getText());
					promoValVal.setText(currPromoVal.toString());
					if(currPromoVal>0) {
						JOptionPane.showMessageDialog(null, "Promo found");
						loadData();
						if(table.getRowCount()==0) {
							JOptionPane.showMessageDialog(null, "No data in cart");
						}
						if(paymentRB.getSelection().getActionCommand()=="Credit"&&table.getRowCount()!=0) {
							if(cardNumVal.getDocument().getLength()<16) {
								JOptionPane.showMessageDialog(null, "Card Number length must be at least 16");
							}
							else if(!Pattern.matches("^[0-9]+$", cardNumVal.getText())) {
								JOptionPane.showMessageDialog(null, "Please input valid card number");
							}
							else if(cardNumVal.getDocument().getLength()>=20) {
								JOptionPane.showMessageDialog(null, "Card Number must be less than 20");
							}
							else {
								CheckoutController.getInstance().insertIntoTransactionTbl(paymentRB.getSelection().getActionCommand(), cardNumVal.getText(),
										promoCodeVal.getText());	
								CheckoutController.getInstance().insertIntoTransactionDetailTbl();
								JOptionPane.showMessageDialog(null, "Transaction Success");
								CheckoutController.getInstance().delete();
								ProductController.getInstance().view().showForm();
								dispose();
							}
						}
						else if(paymentRB.getSelection().getActionCommand()=="Cash"&&table.getRowCount()!=0) {
								CheckoutController.getInstance().insertIntoTransactionTbl(paymentRB.getSelection().getActionCommand(), "0",
										promoCodeVal.getText());	
								CheckoutController.getInstance().insertIntoTransactionDetailTbl();
								JOptionPane.showMessageDialog(null, "Transaction Success");
								CheckoutController.getInstance().delete();
								ProductController.getInstance().view().showForm();
								dispose();						
							}
					}
					else if(currPromoVal==0){
						JOptionPane.showMessageDialog(null, "Promo not found!");
					}
					}
				else if(promoCodeVal.getDocument().getLength()==0) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "No data in cart");
					}
					if(paymentRB.getSelection().getActionCommand()=="Credit"&&table.getRowCount()!=0) {
						if(cardNumVal.getDocument().getLength()<16) {
							JOptionPane.showMessageDialog(null, "Card Number length must be at least 16");
						}
						else if(!Pattern.matches("^[0-9]+$", cardNumVal.getText())) {
							JOptionPane.showMessageDialog(null, "Please input valid card number");
						}
						else if(cardNumVal.getDocument().getLength()>=20) {
							JOptionPane.showMessageDialog(null, "Card Number must be less than 20");
						}
						else{
							CheckoutController.getInstance().insertIntoTransactionTbl(paymentRB.getSelection().getActionCommand(),cardNumVal.getText(),
									promoCodeVal.getText());	
							CheckoutController.getInstance().insertIntoTransactionDetailTbl();
							JOptionPane.showMessageDialog(null, "Transaction Success");
							CheckoutController.getInstance().delete();
							ProductController.getInstance().view().showForm();
							dispose();
						}
					}
					else if(paymentRB.getSelection().getActionCommand()=="Cash"&&table.getRowCount()!=0) {
						CheckoutController.getInstance().insertIntoTransactionTbl(paymentRB.getSelection().getActionCommand(), "0",
								promoCodeVal.getText());	
						CheckoutController.getInstance().insertIntoTransactionDetailTbl();
						JOptionPane.showMessageDialog(null, "Transaction Success");
						CheckoutController.getInstance().delete();
						ProductController.getInstance().view().showForm();
						dispose();						
					}
					
				}
				}
		});
		creditRB.addMouseListener(new MouseListener() {
			
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
				cardNumLbl.setVisible(true);
				cardNumVal.setVisible(true);
			}
		});
		cashRB.addMouseListener(new MouseListener() {
			
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
				cardNumLbl.setVisible(false);
				cardNumVal.setVisible(false);
			}
		});
		checkPromoVal.addActionListener(new ActionListener() {
			Integer priceAfterPromo=0;
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer currPromoVal;
				currPromoVal = CheckoutController.getInstance().checkPromo(promoCodeVal.getText());
				promoValVal.setText(currPromoVal.toString());
				if(currPromoVal>0) {
					JOptionPane.showMessageDialog(null, "Promo found");
					loadData();
				}
				else {
					JOptionPane.showMessageDialog(null, "Promo not found");
				}
			}
		});

	}
	public void loadData() {
		Integer currTotalPrice=0;
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
			currTotalPrice = (p.getTotalPrice()-Integer.parseInt(promoValVal.getText()));
		}
		totalPriceVal.setText(currTotalPrice.toString());
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
	}
	
}
