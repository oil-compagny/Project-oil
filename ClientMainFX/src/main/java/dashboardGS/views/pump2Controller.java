package dashboardGS.views;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class pump2Controller {
	@FXML
	private Label type;
	@FXML
	private Label up;
	@FXML
	private Label v;
	@FXML
	private Label idP;
	 @FXML
	    private Label idA;
	 @FXML
	    private JFXButton btn_ac;
	// Event Listener on JFXButton.onMouseClicked

	public void setData(String v,String up,String type,int a)
    {
    	
    	this.v.setStyle("-fx-text-fill:FF6600;");
    	this.up.setStyle("-fx-text-fill:FF6600;");
    	this.type.setStyle("-fx-text-fill:FF6600;");
    	
    	this.v.setText(v);
    	this.up.setText(up);
    	this.type.setText(type);
    	idA.setText(String.valueOf(a));
    	}
    public void setId(int id)
    {
    	this.idP.setText(String.valueOf(id));
    }
	@FXML
	public void Accounting_acction(MouseEvent event) throws IOException {
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("Account.fxml"));
		AccountController AcControl = new AccountController();
		Parent root=loader.load();
		AcControl=loader.getController();
		
		AcControl.setData(v.getText(), idP.getText(),up.getText(),type.getText(),
				idA.getText());
		btn_ac.setDisable(true);
		Scene ns= new Scene(root);
		Stage stage=new Stage();
		stage.setScene(ns);
		stage.show();
		
	}
	public JFXButton getBtn_ac() {
		return btn_ac;
	}
	public void setBtn_ac(JFXButton btn_ac) {
		this.btn_ac = btn_ac;
	}
	
}
