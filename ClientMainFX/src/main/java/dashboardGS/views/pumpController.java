package dashboardGS.views;

import java.net.URL;

import java.util.ResourceBundle;

import javax.naming.Context;

import com.jfoenix.controls.JFXButton;

import Interfaces.IGas_StationServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;



public class pumpController implements Initializable{
	@FXML
    private Label v;
	@FXML
    private Label idP;
    @FXML
    private Label up;
    @FXML
    private JFXButton btn;
    @FXML
    private Label type;
    static String jndiName="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
	static Context context;
	static IGas_StationServiceRemote proxy = AffectationController.proxy;
	
	
@Override
public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	

	
	}
	
	
    public void setData(String v,String up,String type)
    {
    	
    	this.v.setStyle("-fx-text-fill:FF6600;");
    	this.up.setStyle("-fx-text-fill:FF6600;");
    	this.type.setStyle("-fx-text-fill:FF6600;");
    	
    	this.v.setText(v);
    	this.up.setText(up);
    	this.type.setText(type);
    	}
    public void setId(int id)
    {
    	this.idP.setText(String.valueOf(id));
    }
    @FXML
    void Affectation(MouseEvent event) {
    	
    	 proxy.AffectWorkerPump(AffectationController.WorkerF.getId(),
    			 Integer.valueOf(idP.getText()),AffectationController.ShiftS);
    	 btn.setDisable(true);
    }


	public JFXButton getBtn() {
		return btn;
	}


	public void setBtn(JFXButton btn) {
		this.btn = btn;
	}
	
    
}
