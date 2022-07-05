package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.CartController;
import core.model.Model;

public class CartModel extends Model{

	private Integer productId;
	private Integer productQty;
	private Integer userId;
	private String productAuthor;
	private String productName;
	private Integer productPrice;
	
	private Integer totalPrice;
	
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

	public CartModel() {
		this.tableName = "carttbl";
	}

	@Override
	public void insert() {

	}

	@Override
	public void update() {

	}
	
	public void delete(Integer productId, Integer productQty) {
		String query = String.format("DELETE FROM %s WHERE ProductId=? AND userId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, productId);
			ps.setInt(2, CartController.getInstance().getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				CartModel p = new CartModel();
				p.setProductId(productId);
				getCartProductDetail(p, productId);
				p.setUserId(userId);
				p.setProductQty(productQty);
				data.add(p);
				p.setTotalPrice(addPriceToTotal(p));
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void getCartProductDetail(CartModel p, Integer productId) {
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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Integer addPriceToTotal(CartModel p) {
		totalPrice = totalPrice + (p.getProductPrice()*p.getProductQty());
		return totalPrice;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
}
