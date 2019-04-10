package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import Entities.Claim;
import Services.ClaimserviceRemote;

public class ClaimClientController  implements Initializable{
	@FXML
    private JFXTextArea claimAr;

    @FXML
    private JFXButton back;

    @FXML
    private JFXComboBox<String> cb;

    @FXML
    private JFXButton add;
LoginController lg ;
    @FXML
    void add(ActionEvent event) throws NamingException {
    	
    	String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
    	Context context=new InitialContext();
    	ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
    	 Claim claim=new Claim(claimAr.getText(), cb.getValue(),LoginController.getUser());
    	 //claim.setClaimer(lg.user2);
    	 //Rit ki na7eha set Claimer temchi 
    	 //n7ebbou ya5ouli id mta3 user adhaka 
		 proxy.addClaim(claim);

    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	    Parent root = loader.load();
	    LoginController tsController = loader.getController();
	    back.getScene().setRoot(root);

    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		cb.getItems().addAll("Salary","Product","Event","Other");
		
	}
    

}
