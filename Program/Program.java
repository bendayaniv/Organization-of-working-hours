package NadavOren_YanivBenDavid.Program;

import java.io.Serializable;

import NadavOren_YanivBenDavid.Controller.Controller;
import NadavOren_YanivBenDavid.Model.Model;
import NadavOren_YanivBenDavid.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("serial")
public class Program extends Application implements Serializable {

	public static void main(String[] args) {
		launch(args);

	}

	@SuppressWarnings("unused")
	@Override
	public void start(Stage arg0) throws Exception {
		View theView = new View(arg0);
		Model theModel = new Model();
		Controller theController = new Controller(theModel, theView);
	}

}
