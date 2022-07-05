package model;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.CartController;
import core.model.Model;

public class CheckoutModel extends Model{

	private Integer productId;
	private Integer productQty;
	private Integer userId;
	private String productAuthor;
	private String productName;
	private Integer productPrice;
	private Integer totalPrice;
	
	private String paymentType;
	private String cardNumber;
	private String promoCode;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getProductAuthor() {
		return productAuthor;
	}

	public void setProductAuthor(String productAuthor) {
		this.productAuthor = productAuthor;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public CheckoutModel() {
		this.tableName = "carttbl";
	}

	@Override
	public void insert() {
		
	}

	@Override
	public void update() {

	}
	
	@Override
	public void delete() {
		String query = String.format("SELECT productId, productQuantity FROM %s WHERE userId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, CartController.getInstance().getUserId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String querys = String.format("DELETE FROM %s WHERE ProductId=? AND userId=?", tableName);
				PreparedStatement pss = con.prepareStatement(querys);
				try {
					pss.setInt(1, rs.getInt(1));
					pss.setInt(2, CartController.getInstance().getUserId());
					pss.executeUpdate();
					String cStock = String.format("SELECT productStock FROM producttbl WHERE ProductId=?");
					PreparedStatement rSps = con.prepareStatement(cStock);
					try {
						rSps.setInt(1, rs.getInt(1));
						ResultSet currentStock = rSps.executeQuery();
						String uStock = String.format("UPDATE producttbl SET ProductStock=? WHERE ProductId=?");
						PreparedStatement uPs = con.prepareStatement(uStock);
						if(currentStock.next()) {
							uPs.setInt(1, (currentStock.getInt("productStock")-rs.getInt(2)));
							uPs.setInt(2, rs.getInt(1));			
							uPs.executeUpdate();
						}	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Vector<Model> getAll() {
		totalPrice=0;
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM %s WHERE userId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, CartController.getInstance().getUserId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer productId = rs.getInt("ProductId");
				Integer userId = rs.getInt("userId");
				Integer productQty = rs.getInt("ProductQuantity");
				CheckoutModel p = new CheckoutModel();
				p.setProductId(productId);
				getCartProductDetail(p, productId);
				p.setUserId(userId);
				p.setProductQty(productQty);
				p.setTotalPrice(addPriceToTotal(p));
				data.add(p);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void getCartProductDetail(CheckoutModel p, Integer productId) {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM producttbl WHERE productId=?");
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String productAuthor = rs.getString("ProductAuthor");
				String productName = rs.getString("ProductName");
				Integer productPrice = rs.getInt("ProductPrice");
				
				p.setProductAuthor(productAuthor);
				p.setProductName(productName);
				p.setProductPrice(productPrice);
				
				data.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertIntoTransactionTbl() {
		Integer transactionId=1;
		String getMaxTransactionId = String.format("SELECT MAX(transactionId) FROM transactionTbl");
		PreparedStatement getMaxId = con.prepareStatement(getMaxTransactionId);
		ResultSet getMaxRs;
		java.util.Date now = new java.util.Date();
		Date nowSql = new Date(now.getTime());
			try {
				getMaxRs = getMaxId.executeQuery();
				while(getMaxRs.next()) {
					transactionId = getMaxRs.getInt(1)+1;
				}
				String query = String.format("INSERT INTO transactiontbl VALUES(?, ?, ?, ?, ?, ?)");
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, transactionId);
				ps.setDate(2, nowSql);
				ps.setString(3, paymentType);
				ps.setLong(4, Long.parseLong(cardNumber));
				ps.setString(5, promoCode);
				ps.setInt(6, CartController.getInstance().getUserId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void insertIntoTransactionDetailTbl() {
		Integer transactionId=1;
		String getMaxTransactionId = String.format("SELECT MAX(transactionId) FROM transactionTbl");
		PreparedStatement getMaxId = con.prepareStatement(getMaxTransactionId);
		ResultSet getMaxRs;
		try {
			getMaxRs = getMaxId.executeQuery();
			while(getMaxRs.next()) {
				transactionId = getMaxRs.getInt(1);
			}
			String query = String.format("INSERT INTO transactiondetailtbl VALUES(?, ?, ?)");
			PreparedStatement ps = con.prepareStatement(query);
			Vector<Model> listProduct  = CartController.getInstance().getAll();
			for(Model model:listProduct) {
				CartModel p = (CartModel) model;
				try {
					ps.setInt(1, transactionId);
					ps.setInt(2, p.getProductId());
					ps.setInt(3, p.getProductQty());
					ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		delete();
		}
	
	public Integer checkPromoVal() {
		Integer discVal=0;
		String query = String.format("SELECT * FROM promoTbl WHERE promoCode=?");
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, promoCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				discVal = rs.getInt("promoDiscount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return discVal;
	}
	
	public Integer addPriceToTotal(CheckoutModel p) {
		totalPrice = totalPrice + (p.getProductPrice()*p.getProductQty());
		return totalPrice;
	}

	}
