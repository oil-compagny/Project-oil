package dashboardGS.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;

import Interfaces.IGas_StationServiceRemote;
import Interfaces.IUserServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javafx.scene.layout.Pane;

public class WorkersController implements Initializable {
	@FXML
	private Pane content;
	@FXML
	private ScrollPane workers;
	@FXML
	private VBox workeritem;
	
	 @FXML
	    private Label nbr;
	
	
    @FXML
    void Add_Workers(MouseEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/AddWorker.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }
    @FXML
    void Show_Workers(MouseEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/ShowWorkers.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		  nbr.setText(String.valueOf(UiController.Current_GS.getWorkers().size()));
	}

   
}
