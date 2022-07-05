package core.model;
import java.util.Vector;

import connect.Connect;

public abstract class Model {

	protected String tableName;
	protected Connect con = Connect.getConnection();
	
	public Model() {
		// TODO Auto-generated constructor stub
	}

	public abstract void insert();
	public abstract void update();
	public abstract void delete();
	public abstract Vector<Model> getAll();

}
