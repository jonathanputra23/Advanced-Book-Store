package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import core.model.Model;

public class ProductModel extends Model{

	private Integer productId;
	private String productName;
	private String productAuthor;
	private Integer productPrice;
	private Integer productStock;
	private Integer userId;
	private Integer wantedStock;
	
	public Integer getWantedStock() {
		return wantedStock;
	}

	public void setWantedStock(Integer wantedStock) {
		this.wantedStock = wantedStock;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAuthor() {
		return productAuthor;
	}

	public void setProductAuthor(String productAuthor) {
		this.productAuthor = productAuthor;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

	public ProductModel() {
		this.tableName = "producttbl";
	}

	@Override
	public void insert() {

	}

	@Override
	public void update() {

	}

	@Override
	public void delete() {

	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Integer id = rs.getInt("ProductId");
				String name = rs.getString("ProductName");
				String author = rs.getString("ProductAuthor");
				Integer price = rs.getInt("ProductPrice");
				Integer stock = rs.getInt("ProductStock");
				
				ProductModel p = new ProductModel();
				p.setProductId(id);
				p.setProductName(name);
				p.setProductAuthor(author);
				p.setProductPrice(price);
				p.setProductStock(stock);
				
				data.add(p);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void addToCart() {
		Integer currentStock;
		String query = String.format("SELECT productStock FROM %s WHERE productId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				currentStock = rs.getInt("ProductStock");
				if(currentStock<wantedStock) {
					JOptionPane.showMessageDialog(null, "Not enough stock");
				}
				else if(currentStock>=wantedStock) {
					
					/*
					 * String addCart =
					 * String.format("UPDATE %s SET ProductStock=? WHERE ProductId=?", tableName);
					 * PreparedStatement addPs = con.prepareStatement(addCart); addPs.setInt(1,
					 * (currentStock-wantedStock)); addPs.setInt(2, productId);
					 * addPs.executeUpdate();
					 */
					addToCartDB(currentStock);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addToCartDB(Integer productStock) {
		String isCart = String.format("SELECT * FROM carttbl WHERE ProductId=? AND UserId=?");
		PreparedStatement iCps = con.prepareStatement(isCart);
		try {
			iCps.setInt(1, productId);
			iCps.setInt(2, userId);
			ResultSet iCrs = iCps.executeQuery();
			if(iCrs.next()) {
				if((iCrs.getInt("productQuantity")+wantedStock)>productStock) {
					JOptionPane.showMessageDialog(null, "Not enough stock");
				}
				else {
					String existCart = String.format("UPDATE carttbl SET ProductQuantity=? WHERE UserId=? AND ProductId=?");
					PreparedStatement existCartPs = con.prepareStatement(existCart);
					existCartPs.setInt(1, wantedStock+iCrs.getInt("productQuantity"));
					existCartPs.setInt(2, userId);
					existCartPs.setInt(3, productId);
					existCartPs.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully added to Cart");
				}
			}
			else{
				String newCart = String.format("INSERT INTO carttbl VALUES(?, ?, ?)");
				PreparedStatement newCartPs = con.prepareStatement(newCart);
				newCartPs.setInt(1, userId);
				newCartPs.setInt(2, productId);
				newCartPs.setInt(3, wantedStock);
				newCartPs.executeUpdate();
				JOptionPane.showMessageDialog(null, "Successfully added to Cart");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
