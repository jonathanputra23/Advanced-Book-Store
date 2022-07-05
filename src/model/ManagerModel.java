package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.ManagerController;
import core.model.Model;

public class ManagerModel extends Model{
	private Integer month;
	private Integer year;
	private Integer transactionId;
	private Date transactionDate;
	private String paymentType;
	private Long cardNumber;
	private String promoCode;
	private Integer userId;
	private Integer productId;
	private Integer productQty;
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductQty() {
		return productQty;
	}

	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM transactiontbl WHERE date_format(transactionDate, '%%Y-%%m')=('%d-%d')", ManagerController.getInstance().getYear(), ManagerController.getInstance().getMonth());
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer transactionId = rs.getInt("transactionId");
				Date transactionDate = rs.getDate("transactionDate");
				String paymentType = rs.getString("paymentType");
				Long cardNumber = rs.getLong("cardNumber");
				String promoCode = rs.getString("promoCode");
				Integer userId = rs.getInt("userId");
				
				ManagerModel p = new ManagerModel();
				p.setTransactionId(transactionId);
				p.setTransactionDate(transactionDate);
				p.setPaymentType(paymentType);
				p.setCardNumber(cardNumber);
				p.setPromoCode(promoCode);
				p.setUserId(userId);
				
				data.add(p);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Vector<Model> getAllTransactionDetail() {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM transactiondetailtbl WHERE transactionId=?");
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, ManagerController.getInstance().getTransactionId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer transactionId = ManagerController.getInstance().getTransactionId();
				Integer productId = rs.getInt("productId");
				Integer productQty = rs.getInt("productQuantity");
				ManagerModel x = new ManagerModel();
				x.setTransactionId(transactionId);
				x.setProductId(productId);
				x.setProductQty(productQty);
				
				data.add(x);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Vector<Model> getEvery() {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM transactiontbl");
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer transactionId = rs.getInt("transactionId");
				Date transactionDate = rs.getDate("transactionDate");
				String paymentType = rs.getString("paymentType");
				Long cardNumber = rs.getLong("cardNumber");
				String promoCode = rs.getString("promoCode");
				Integer userId = rs.getInt("userId");
				
				ManagerModel p = new ManagerModel();
				p.setTransactionId(transactionId);
				p.setTransactionDate(transactionDate);
				p.setPaymentType(paymentType);
				p.setCardNumber(cardNumber);
				p.setPromoCode(promoCode);
				p.setUserId(userId);
				
				data.add(p);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber2) {
		this.cardNumber = cardNumber2;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
