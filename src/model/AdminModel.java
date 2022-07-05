package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class AdminModel extends Model{
	
	
	private Integer id;
	private String Name;
	private String Author;
	private Integer price;
	private Integer stock;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public AdminModel() {
		// TODO Auto-generated constructor stub
		this.tableName = "ProductTbl";
		
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		String query = String.format("INSERT INTO %s Values(null, ?, ?, ? ,?)", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, Name);
			ps.setString(2, Author);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		String query = String.format("UPDATE %s SET ProductName=?, ProductPrice=?, ProductAuthor=?, ProductStock=? WHERE ProductId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, Name);
			ps.setInt(2, price);
			ps.setString(3, Author);
			ps.setInt(4, stock);
			ps.setInt(5, id);
			ps.executeUpdate();
		} catch(SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void delete() {
		String query = String.format("DELETE FROM %s WHERE ProductId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Integer id = rs.getInt("ProductId");
				String name = rs.getString("ProductName");
				Integer price = rs.getInt("ProductPrice");
				String Author = rs.getString("ProductAuthor");
				Integer stock = rs.getInt("ProductStock");
				
				AdminModel p = new AdminModel();
				p.setId(id);
				p.setName(name);
				p.setPrice(price);
				p.setAuthor(Author);
				p.setStock(stock);
				
				data.add(p);

			}
			return data;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
  