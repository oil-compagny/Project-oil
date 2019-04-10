package controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Entities.Role;
import Entities.User;
import Entities.ConnectedUser;

import Services.ServiceUserRemote;

public class LoginController  implements Initializable{

    @FXML
    private MediaView mediaView;

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXPasswordField txtPass;
    
    @FXML
    private JFXButton supbtn;

    @FXML
    private JFXButton sinbtn;
    
    
    @FXML
    private JFXButton pswbtn;
    
    private static User user;
    
    
    
    static User user2= new User();
    
    User user1=new User();
  

    public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		LoginController.user = user;
	}
	  

   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Media media = new Media(new File("C://Users/hamat/Downloads/Video/1.mp4").toURI().toString());
		MediaPlayer player= new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		player.setVolume(0);
		player.play();
	}
///////////////////////////////////////////////////Login//////////////////////////////////////////////	
	 @FXML
	    void connection(ActionEvent event) throws NamingException, IOException  {
		 
		 String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
		 Context context;
		 context = new InitialContext();
		 ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
		 User s=new User();
		 List<User> st =proxy.getallUser();
			for (User user : st) {
				if(user.getLogin().equals(txtLogin.getText())&&user.getPasswd().equals(txtPass.getText()))
                {
				  if(user.getAutorisation().equals(true)){
					
					          if  (user.getRole()==Role.HR){
					                       user2 = user;	
					                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Skeleton.fxml"));
					                    Parent root = loader.load();
					                    SkeletonController rh = loader.getController();
					                    supbtn.getScene().setRoot(root);
					
					
					}
				  
					          else {
					        	  //ena n7ebbou ya5ou lahna user conecter 
					        	  //behi enti wenti tseti fel les attribut fel user connecté ki taamel login ??
					        	  user2 = user;	
				                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ClaimClient.fxml"));
				                    Parent root = loader.load();
				                  ClaimClientController rh = loader.getController();
				                    supbtn.getScene().setRoot(root);
					          }      
					          
					          
					          
					          
					          

				  }
				    else if ( !user.getAutorisation().equals(true))
 {
					  System.out.println("Autorisation Mazelt");
			            Alert alerteLogin = new Alert(Alert.AlertType.WARNING);
			            alerteLogin.setTitle("Failure Authorization");
			            alerteLogin.setContentText("you still don't have access to the app");
			            alerteLogin.showAndWait(); }		  
                }
				
			else  if (!user.getLogin().equals(txtLogin.getText())&&!user.getPasswd().equals(txtPass.getText())){
				
					 System.out.println("blabla");
					 Alert alert =new Alert(Alert.AlertType.ERROR);
					 alert.setTitle("Failure Authentification");
					 alert.setContentText("please check login or password");
					 alert.showAndWait();
					 
				  }	
	    } 
			}
////////////////////////////////////////////Inscription//////////////////////////////////////////	 
	    @FXML
	    void inscription(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Inscription.fxml"));
            Parent root = loader.load();
           
            supbtn.getScene().setRoot(root);
	    }
////////////////////////////////Rucce Pass/////////////////////////////////////////////////	    
	    @FXML
	    void Lost(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ForgetPassword.fxml"));
            Parent root = loader.load();
           
            pswbtn.getScene().setRoot(root);

	    }
}
