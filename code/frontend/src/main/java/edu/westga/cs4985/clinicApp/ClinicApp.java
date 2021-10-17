package edu.westga.cs4985.clinicApp;
	
import java.io.IOException;

import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class ClinicApp extends Application {
	public static final String GUI_FXML = "view/login/LoginGui.fxml";
	public static final String WINDOW_TITLE = "Clinic";
	public static final String DASHBOARD_GUI = "view/dashboard/DashboardGui.fxml";

	/**
	 * Constructs a new Application object for Image program.
	 * 
	 * @precondition none
	 * @postcondition the object is ready to execute
	 */
	public ClinicApp() {
		super();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = this.loadGui();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}

	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(GUI_FXML));
		return (Pane) loader.load();
	}

	/**
	 * launches the application
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserManager.setUserManager(new UserManager());
		launch(args);
	}

}
