package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.LoginRegisterController;
import controller.ProductController;
import core.model.Model;
import view.LoginRegisterView;

public class LoginRegisterModel extends Model{

	private Integer userId;
	private Integer roleId;
	private String username;
	private String password;
	
	public LoginRegisterModel() {
		this.tableName = "usertbl";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void insert() {
	}

	@Override
	public void update() {

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public Integer check() {
		Integer currRole;
		String query = String.format("SELECT * FROM %s WHERE username = ? AND password = ?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
	              JOptionPane.showMessageDialog(null, "Invalid Username or Password ");  
	              return currRole=null;
	        }
			else {
				String checkRole = String.format("SELECT roleId, userId FROM %s WHERE username =? AND password =?", tableName);
				PreparedStatement crps = con.prepareStatement(checkRole);
				crps.setString(1, username);
				crps.setString(2,  password);
				ResultSet crrs = crps.executeQuery();
				while(crrs.next()) {
					if(crrs.getInt("roleId")==1) {
						ProductController.getInstance().passUserId(crrs.getInt("userId"));
						return currRole=1;
					}
					else if(crrs.getInt("roleId")==2) {	
						return currRole=2;
					}
					else if(crrs.getInt("roleId")==3) {
						return currRole=3;
					}
					else if(crrs.getInt("roleId")==4) {
						return currRole=4;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
