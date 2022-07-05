package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import view.AdminView;

public class AdminController extends Controller{
	
	private AdminModel product;
	private static AdminController controller;

	public AdminController() {
		// TODO Auto-generated constructor stub
		product = new AdminModel();
	}
	
	public static AdminController getInstance() {
		return controller = (controller == null) ? new AdminController() : controller;
	}

	@Override
	public View view() {
		// TODO Auto-generated method stub
		return new AdminView();
	} 

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return product.getAll();
	}

	
	public void insert(String name, Integer price, String author, Integer stock) {
		// TODO Auto-generated method stub
		AdminModel p = new AdminModel();
		p.setName(name);
		p.setPrice(price);
		p.setAuthor(author);
		p.setStock(stock);
		p.insert();
		
		
		
	}


	public void update(String name, Integer price, String author, Integer stock, Integer id) {
		// TODO Auto-generated method stub
		AdminModel p = new AdminModel();
		p.setName(name);
		p.setPrice(price);
		p.setAuthor(author);
		p.setStock(stock);
		p.setId(id);
		p.update();
	}

	
	public void delete(Integer id) {
		AdminModel p = new AdminModel();
		p.setId(id);
		p.delete();
		
	}



}
