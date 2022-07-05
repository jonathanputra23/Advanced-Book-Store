package core.controller;

import java.util.Vector;

import core.model.Model;
import core.view.View;

public abstract class Controller {

	public Controller() {
		// TODO Auto-generated constructor stub
	}

	public abstract View view();
	public abstract Vector<Model> getAll();
}
