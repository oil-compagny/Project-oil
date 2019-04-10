package dashboard.main;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Zone;
import Interfaces.IZoneServiceRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.show();
    }

    public static void main(String[] args) throws NamingException {
    	 
   
    
        launch(args);
    }

}
