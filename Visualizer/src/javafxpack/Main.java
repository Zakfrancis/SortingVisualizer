package javafxpack;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.fxml.*;
public class Main extends Application {

	public Parent root;
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	

}
