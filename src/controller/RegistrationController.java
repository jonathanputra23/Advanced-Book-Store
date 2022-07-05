package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.ProductModel;
import model.RegistrationModel;
import view.ProductView;
import view.RegistrationView;

public class RegistrationController extends Controller{

	private RegistrationModel registration;
	private static RegistrationController controller;
	
	public RegistrationController() {
		registration = new RegistrationModel();
	}

	public static RegistrationController getInstance() {
		return controller = (controller == null)? new RegistrationController(): controller;
	}
	@Override
	public View view() {
		return new RegistrationView();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return registration.getAll();
	}

	public void insert(Integer roleId, String username, String password) {
		RegistrationModel p = new RegistrationModel();
		p.setRoleId(roleId);
		p.setUsername(username);
		p.setPassword(password);
		p.insert();
	}

	public void update() {

		
	}

	public void delete() {

		
	}

}
