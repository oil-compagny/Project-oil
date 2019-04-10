package tn.esprit.monClient;



import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.OilWells;
import Entities.RefiningFactory;
import Entities.Zone;
import Interfaces.IOilWellServiceRemote;
import Interfaces.IRefiningServiceRemote;
import Interfaces.IZoneServiceRemote;
import Services.OilWellsService;
import Services.ZoneServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Client extends Application {
	public static Stage stage = null;
	public static void main(String[] args) throws InterruptedException,NamingException 
	{
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.show();
	}
			
			
}
