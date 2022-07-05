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

import controller.ProductController;
import controller.PromoController;
import core.model.Model;
import core.view.View;
import model.ProductModel;
import model.PromoModel;

public class PromoView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, codeLbl, discountLbl, noteLbl;
	JTextField codeTxt, discountTxt, noteTxt;
	JButton insert, update, delete;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public PromoView() {
		// TODO Auto-generated constructor stub
		super();
		this.height = 700;
		this.width = 600;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		top = new JPanel();
		mid = new JPanel(new GridLayout(4,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		idLbl = new JLabel("Promo ID: ");
		codeLbl = new JLabel("Promo Code: ");
		discountLbl = new JLabel("Promo Discount: ");
		noteLbl = new JLabel("Promo Note: ");
		idValue = new JLabel("-");
		codeTxt = new JTextField();
		discountTxt = new JTextField();
		noteTxt = new JTextField();
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");	

		this.setTitle("Promo Team Page");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		top.add(sp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(codeLbl);
		mid.add(codeTxt);
		mid.add(discountLbl);
		mid.add(discountTxt);
		mid.add(noteLbl);
		mid.add(noteTxt);
		
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
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String code = codeTxt.getText();
				Integer discount = Integer.parseInt(discountTxt.getText());
				String note = noteTxt.getText();
				if(discount<15000) {
					JOptionPane.showMessageDialog(null, "PROMO MUST BE AT LEAST 15000");
				}
				else {
					PromoController.getInstance().insert(code, discount, note);
					loadData();					
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
				idValue.setText(table.getValueAt(row, 0).toString());
				codeTxt.setText(table.getValueAt(row, 1).toString());
				discountTxt.setText(table.getValueAt(row, 2).toString());
				noteTxt.setText(table.getValueAt(row, 3).toString());
			}
		});
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(idValue.getText());
				String code = codeTxt.getText();
				String note = noteTxt.getText();
				Integer discount = Integer.parseInt(discountTxt.getText());
				if(discount<15000) {
					JOptionPane.showMessageDialog(null, "PROMO MUST BE AT LEAST 15000");
				}
				else {
					PromoController.getInstance().update(code, discount, note, id);
					loadData();
				}
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(idValue.getText());
				PromoController.getInstance().delete(id);
				loadData();
			}
		});
	}
	
	public void loadData() {
		data = new Vector<>();
		header = new Vector<>();
		
		header.add("Promo Id");
		header.add("Promo Code");
		header.add("Promo Discount");
		header.add("Promo Note");
		
		Vector<Model> listProduct  = PromoController.getInstance().getAll();
		
		for(Model model:listProduct) {
			PromoModel p = (PromoModel) model;
			detail = new Vector<>();
			
			detail.add(p.getId().toString());
			detail.add(p.getCode());
			detail.add(p.getDiscount().toString());
			detail.add(p.getNote());
			
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
