package tn.esprit;


import javax.naming.NamingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class client extends Application {
	

	public static void main(String[] args) throws NamingException  {
	launch(args);
	
	}
	
@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			Parent root=FXMLLoader.load(getClass().getResource("FuelLogisticManager.fxml"));
			Scene scene=new Scene(root, 1752,900);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("emshi brabbi");
			primaryStage.show();
			}catch(Exception e){
			e.getCause();
			

		}
	}
	
}
