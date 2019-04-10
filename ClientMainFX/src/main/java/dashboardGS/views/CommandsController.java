package dashboardGS.views;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import javafx.scene.layout.Pane;

public class CommandsController {
	@FXML
    private VBox workeritem;

    @FXML
    private ScrollPane workers;

    @FXML
    private Pane content;

   

    @FXML
    void Add_Command(MouseEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/CommandAdd.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }

    

    @FXML
    void Show_Command(MouseEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/CommandsShow.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }

    @FXML
    void Curent_Affectations(MouseEvent event) {

    }
}
