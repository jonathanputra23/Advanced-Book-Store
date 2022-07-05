package controller;

import java.sql.Date;
import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.ManagerModel;
import model.ProductModel;
import view.ManagerAddStaffView;
import view.ManagerTransactionView;
import view.ManagerView;
import view.ManagerViewAllView;
import view.ProductView;

public class ManagerController extends Controller{
	private ManagerModel manager;
	private static ManagerController controller;
	ManagerModel p = new ManagerModel();
	
	public ManagerController() {
		manager = new ManagerModel();
	}

	public static ManagerController getInstance() {
		return controller = (controller == null)? new ManagerController(): controller;
	}
	@Override
	public View view() {
		return new ManagerView();
	}
	public View viewAll() {
		return new ManagerViewAllView();
	}

	public View viewTransaction() {
		return new ManagerTransactionView();
	}
	
	public View viewAddStaff() {
		return new ManagerAddStaffView();
	}
	

	public void setDate(Integer year, Integer month) {
		p.setYear(year);
		p.setMonth(month);
	}
	
	public Integer getYear() {
		return p.getYear();
	}
	public Integer getMonth() {
		return p.getMonth();
	}
	
	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return manager.getAll();
	}
	ManagerModel x = new ManagerModel();
	public Vector<Model> getAllTransactionDetail() {
		return manager.getAllTransactionDetail();
	}
	public Vector<Model> getEvery() {
		return manager.getEvery();
	}
	public void setTransactionId(Integer transactionId) {
		x.setTransactionId(transactionId);
	}
	public Integer getTransactionId() {
		return x.getTransactionId();
	}

}
