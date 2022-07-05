package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.ProductModel;
import view.ProductView;

public class ProductController extends Controller{

	private ProductModel product;
	private static ProductController controller;
	
	ProductModel p = new ProductModel();
	
	public ProductController() {
		product = new ProductModel();
	}

	public static ProductController getInstance() {
		return controller = (controller == null)? new ProductController(): controller;
	}
	@Override
	public View view() {
		return new ProductView();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return product.getAll();
	}

	public void insert() {

	}

	public void update() {
		
	}

	public void delete() {

	}
	
	public void addToCart(Integer productId, String productName, String productAuthor, Integer productPrice, Integer productStock, Integer ws) {
		p.setProductId(productId);
		p.setProductName(productName);
		p.setProductAuthor(productAuthor);
		p.setProductPrice(productPrice);
		p.setProductStock(productStock);
		p.setWantedStock(ws);
		p.addToCart();
	}
	public void passUserId(Integer userId) {
		p.setUserId(userId);
	}

	public Integer getUserId() {
		return p.getUserId();
	}
	
}
