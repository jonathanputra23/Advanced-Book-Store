package controller;

import java.math.BigInteger;
import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.CartModel;
import model.CheckoutModel;
import model.ProductModel;
import view.CartView;
import view.CheckoutView;
import view.ProductView;

public class CheckoutController extends Controller{

	private CheckoutModel cart;
	private static CheckoutController controller;
	

	
	public CheckoutController() {
		cart = new CheckoutModel();
	}

	public static CheckoutController getInstance() {
		return controller = (controller == null)? new CheckoutController(): controller;
	}
	@Override
	public View view() {
		return new CheckoutView();
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

	public void delete() {
		CheckoutModel p = new CheckoutModel();
		p.delete();
	}
	
	public void getCartDetail() {
		CheckoutModel p = new CheckoutModel();
		p.getCartProductDetail(cart, null);
	}
	
	public void insertIntoTransactionTbl(String paymentType, String cardNumber, String promoCode) {
		CheckoutModel p = new CheckoutModel();
		p.setPaymentType(paymentType);
		p.setCardNumber(cardNumber);
		p.setPromoCode(promoCode);
		p.insertIntoTransactionTbl();
	}
	
	public void insertIntoTransactionDetailTbl() {
		CheckoutModel p = new CheckoutModel();
		p.insertIntoTransactionDetailTbl();
	}
	
	public Integer checkPromo(String promoCode) {
		CheckoutModel p = new CheckoutModel();
		p.setPromoCode(promoCode);
		return p.checkPromoVal();
	}
	
	public Integer getTotalPrice() {
		CheckoutModel p = new CheckoutModel();
		return p.getTotalPrice();
	}
}
