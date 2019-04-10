package dashboard.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientWorker extends Application {
	public static Stage stage = null;
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/dashboardGS/views/DashWorker.fxml"));
		 
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.show();
	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
