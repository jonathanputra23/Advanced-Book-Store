package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class RegistrationModel extends Model{

	private Integer userId;
	private Integer roleId;
	private String username;
	private String password;
	
	public RegistrationModel() {
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
		String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?)", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, roleId);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub
		return null;
	}


}
