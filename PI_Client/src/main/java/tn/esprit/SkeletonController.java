package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import Entities.Claim;
import Services.ClaimserviceRemote;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class SkeletonController implements Initializable  {

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnWorkers;

    

    @FXML
    private JFXButton btnSignOut;

    @FXML
    private JFXButton btnClaims;
    @FXML
    private Label count;

    @FXML
    private AnchorPane holderPane;
    AnchorPane Contact,HomeRH,Claims,Users,Workers;
///////////////////////////////////////////Login//////////////////////////////////////////////
    @FXML
    void SwitchLogin(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
       
        btnSignOut.getScene().setRoot(root);

    }
//////////////////////////////////////////////Claims/////////////////////////////////////////////
    @FXML
    void switchClaims(ActionEvent event) throws IOException {
    	
    	Claims = FXMLLoader.load(getClass().getResource("../gui/ClaimsRH.fxml"));
    	setNode(Claims);
    	
    	

    }
///////////////////////////////////////////////Load data claim////////////////////////////////////////////
    public void LoadData1(){
		
		String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
		Context context;
		try {
			context = new InitialContext();
			ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
			List<Claim>list;
			
			ObservableList<Claim>list1=FXCollections.observableArrayList();
			list=proxy.getNextNotTreatedClaims();
			count.setText(String.valueOf(list.size()));
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getTreated()==true){
				list1.add(new Claim(list.get(i).getClaimId(),list.get(i).getDate(),list.get(i).getText(),list.get(i).getStatus(),list.get(i).getTreated()));
				
				}
			}
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	
		

	}
/////////////////////////////////////////////////Home///////////////////////////////////////////////
    @FXML
    void switchHome(ActionEvent event) {
    	setNode(HomeRH);
    }
///////////////////////////////////////////Users///////////////////////////////////////////////////
    @FXML
    void switchUser(ActionEvent event) throws IOException {
    	Users = FXMLLoader.load(getClass().getResource("../gui/UsersRH.fxml"));
    	setNode(Users);
    	

    }
////////////////////////////////////////////Workers///////////////////////////////////////////////////
    @FXML
    void switchWorkers(ActionEvent event) throws IOException {
    	Workers = FXMLLoader.load(getClass().getResource("../gui/WorkersRH.fxml"));
    	setNode(Workers);

    }
////////////////////////////////////////////Initialize//////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			HomeRH = FXMLLoader.load(getClass().getResource("../gui/HomeRH.fxml"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        setNode(HomeRH);
        LoadData1();

    }
/////////////////////////////////////Node/////////////////////////////////////////////////////
  
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    
}
