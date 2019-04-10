package dashboardGS.views;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;


import Interfaces.IUserServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;



public class WorkerItemController implements Initializable {
	@FXML
	private Label name;
	@FXML
	private Label salary;
	@FXML
    private JFXButton del;
	@FXML
    private Label idU;
	static String jndiName="PI-ear/PI-ejb/UserServiceImpl!Interfaces.IUserServiceRemote";
	 static Context context;
	 static IUserServiceRemote proxy ;
	 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		try {
			context = new InitialContext();
			 proxy = (IUserServiceRemote )  context.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void delete_user(MouseEvent event) throws IOException {
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		proxy.removeUser(Integer.valueOf(idU.getText()));
			UiController.Current_GS=proxy.removeUserfromGS(Integer.valueOf(idU.getText()),UiController.Current_GS.getId());
			name.setDisable(true);
			salary.setDisable(true);
			del.setDisable(true);
		}
	
	 
		
	
		}
	public void setData(String name,String lasName,Double salary,int id)
	{
		this.name.setText(name+" "+lasName);
		this.salary.setText(String.valueOf(salary));
		this.idU.setText(String.valueOf(id));
		if(id==13)
		{
			del.setVisible(false);
		}
	}
	}
	

