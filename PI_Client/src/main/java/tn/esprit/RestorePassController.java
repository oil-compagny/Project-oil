package controller;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Entities.User;
import Services.ServiceUserRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class RestorePassController {

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField pass;

    @FXML
    private JFXButton send;

    @FXML
    void changeP(ActionEvent event) throws NamingException, IOException {
    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
		 Context context;
		 context = new InitialContext();
		 ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
    
    	User u =proxy.findmail(ForgetPasswordController.Emailneeded);
    	if(u.getCode().equals(code.getText())){
    		proxy.SetPass(u.getId(), pass.getText());
    		
    	}
    	Notifications notif=Notifications.create()
					.title("Registration")
					.text("Registred with success")
					.position(Pos.TOP_CENTER)
					.graphic(null)
					.hideAfter(Duration.seconds(5));
		    notif.darkStyle();
			notif.showInformation();
    
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
       
        send.getScene().setRoot(root);
    	
    	
    	

    }

}
