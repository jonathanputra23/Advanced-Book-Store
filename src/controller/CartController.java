package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.CartModel;
import model.ProductModel;
import view.CartView;
import view.ProductView;

public class CartController extends Controller{

	private CartModel cart;
	private static CartController controller;
	
	CartModel p = new CartModel();
	
	public CartController() {
		cart = new CartModel();
	}

	public static CartController getInstance() {
		return controller = (controller == null)? new CartController(): controller;
	}
	@Override
	public View view() {
		return new CartView();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return cart.getAll();
	}

	public void insert() {

	}

	public void update() {
		
	}

	public void delete(Integer productId, Integer productQty) {
		p.setProductId(productId);
		p.setProductQty(productQty);
		p.delete(p.getProductId(), p.getProductQty());
	}
	
	public void passUserId(Integer userId) {
		p.setUserId(userId);
	}
	
	public Integer getUserId() {
		return p.getUserId();
	}
	
	public void getCartDetail() {
		p.getCartProductDetail(cart, null);
	}
	
}
