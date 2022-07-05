package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.LoginRegisterModel;
import model.ProductModel;
import model.RegistrationModel;
import view.LoginRegisterView;
import view.ProductView;
import view.RegistrationView;

public class LoginRegisterController extends Controller{

	private LoginRegisterModel loginRegister;
	private static LoginRegisterController controller;
	
	public LoginRegisterController() {
		loginRegister = new LoginRegisterModel();
	}

	public static LoginRegisterController getInstance() {
		return controller = (controller == null)? new LoginRegisterController(): controller;
	}
	@Override
	public View view() {
		return new LoginRegisterView();
	}

	@Override
	public Vector<Model> getAll() {
		return loginRegister.getAll();
	}

	public Integer check(String username, String password) {
		LoginRegisterModel p = new LoginRegisterModel();
		Integer currRole;
		p.setUsername(username);
		p.setPassword(password);
		return currRole=p.check();
	}

	public void update() {

		
	}

	public void delete() {

		
	}

}
