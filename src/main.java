import controller.LoginRegisterController;
import controller.RegistrationController;
import view.LoginRegisterView;

public class main {

	public main() {
		//RegistrationController.getInstance().view().showForm(); run register form
		LoginRegisterController.getInstance().view().showForm(); //run login and register form
	}

	public static void main(String[] args) {
		new main();

	}

}
