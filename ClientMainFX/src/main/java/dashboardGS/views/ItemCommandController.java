package dashboardGS.views;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ItemCommandController {
	@FXML
	private HBox itemC;
	 @FXML
	    private Label gas_station_Name;

	    @FXML
	    private Label gasoline_qty;
	    @FXML
	    private Label Mazout_qty;

	    @FXML
	    private Label diesel_qty;

	    @FXML
	    private Label id;
	    @FXML
	    private JFXButton btnV;

 
	
	// Event Listener on Button.onMouseClicked
	
	 public void setData(String gas_station_Name ,int gasoline_qty,int diesel_qty,int Mazout_qty,int id,int rc)
		{
		     this.gas_station_Name.setText(gas_station_Name);
		    
			String a =String.valueOf(gasoline_qty);
			String b =String.valueOf(diesel_qty);
			String c =String.valueOf(Mazout_qty);
			this.gasoline_qty.setText(a);
			this.diesel_qty.setText(b);
			 this.Mazout_qty.setText(c);;
			this.id.setText(String.valueOf(id));
			if(rc==1)
			{
				btnV.setDisable(true);
				btnV.setStyle("-fx-background-color:orange;");
			}
			
		}
	 @FXML
	    void pull_cistern(MouseEvent event) {
		 UiController.Current_GS=CommandsShowController.proxy.deleteCommands(Integer.valueOf(id.getText()));  
 		btnV.setDisable(true);
 		btnV.setStyle("-fx-background-color:orange;");
	    }
	    @FXML
	    void delete_command(MouseEvent event) {
	    	
	    		
	    		
	    }
}
