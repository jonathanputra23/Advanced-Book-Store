package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import core.model.Model;

public class PromoModel extends Model{

	private Integer id;
	private String code;
	private Integer discount;
	private String note;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PromoModel() {
		this.tableName = "promotbl";
	}

	@Override
	public void insert() {
		String checkQuery = String.format("SELECT PromoCode FROM %s WHERE PromoCode=?", tableName);
		PreparedStatement cQps = con.prepareStatement(checkQuery);
		try {
			cQps.setString(1, code);
			ResultSet cQrs = cQps.executeQuery();
			if(cQrs.next()) {
				JOptionPane.showMessageDialog(null, "PROMO MUST BE UNIQUE");
			}
			else {
				String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?)", tableName);
				PreparedStatement ps = con.prepareStatement(query);
				try {
					ps.setString(1, code);
					ps.setInt(2, discount);
					ps.setString(3, note);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully add promo");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		String checkQuery = String.format("SELECT PromoCode FROM %s WHERE PromoCode=?", tableName);
		PreparedStatement cQps = con.prepareStatement(checkQuery);
		try {
			cQps.setString(1, code);
			ResultSet cQrs = cQps.executeQuery();
			if(cQrs.next()) {
				JOptionPane.showMessageDialog(null, "PROMO MUST BE UNIQUE");
			}
			else {
				String query = String.format("UPDATE %s SET PromoCode=?, PromoDiscount=?, PromoNote=? WHERE PromoId=?", tableName);
				PreparedStatement ps = con.prepareStatement(query);
				try {
					ps.setString(1, code);
					ps.setInt(2,  discount);
					ps.setString(3, note);
					ps.setInt(4, id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully update promo");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete() {
		String query = String.format("DELETE FROM %s WHERE PromoId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully delete promo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Integer id = rs.getInt("PromoId");
				String code = rs.getString("PromoCode");
				Integer discount = rs.getInt("PromoDiscount");
				String note = rs.getString("PromoNote");
				
				PromoModel p = new PromoModel();
				p.setId(id);
				p.setCode(code);
				p.setDiscount(discount);
				p.setNote(note);
				
				data.add(p);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
