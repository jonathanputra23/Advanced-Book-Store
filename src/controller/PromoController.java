package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.PromoModel;
import view.PromoView;

public class PromoController extends Controller{

	private PromoModel promo;
	private static PromoController controller;
	
	public PromoController() {
		promo = new PromoModel();
	}

	public static PromoController getInstance() {
		return controller = (controller == null)? new PromoController(): controller;
	}
	@Override
	public View view() {
		return new PromoView();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return promo.getAll();
	}

	public void insert(String code, Integer discount, String note) {
		PromoModel p = new PromoModel();
		p.setCode(code);
		p.setDiscount(discount);
		p.setNote(note);
		p.insert();
	}

	public void update(String code, Integer discount, String note, Integer Id) {
		PromoModel p = new PromoModel();
		p.setCode(code);
		p.setDiscount(discount);
		p.setNote(note);
		p.setId(Id);
		p.update();
		
	}

	public void delete(Integer id) {
		PromoModel p = new PromoModel();
		p.setId(id);
		p.delete();
		
	}

}
