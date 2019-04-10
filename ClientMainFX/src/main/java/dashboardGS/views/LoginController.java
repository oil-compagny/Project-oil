package dashboardGS.views;


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
    
    private static User Currentuser=null;
    
    
    User user2= new User();
    
    User user1=new User();
  

    public static User getUser() {
		return Currentuser;
	}
	public static void setUser(User user) {
		Currentuser = user;
	}
	  public static DashWorkerController dash;

   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Media media = new Media(new File("F:\\JEE\\workspace\\ClientMainFX\\src\\main\\java\\dashboardGS\\video\\1.mp4").toURI().toString());
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
		 String pass=txtPass.getText();
		 List<User> st =proxy.getallUser();
			for (User user : st) {
				if(user.getLogin().equals(txtLogin.getText())
						&&user.getPasswd().equals(pass))
             {
	/////////////////////////////////////////////////////////Autorisarion////////////////////////////////
				  

/////////////////////////////////////////////////////////Anis Role///////////////////////////////////////////////////////////					
					         
					  if  (user.getRole()==Role.Gerant){
			        	  Currentuser = user;	
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboardGS/views/DashGS.fxml"));
	                    Parent root = loader.load();
	                   
	                    supbtn.getScene().setRoot(root);
	
	
	}
			          if  (user.getRole()==Role.Worker){
			        	  Currentuser = user;	
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboardGS/views/DashWorker.fxml"));
     Parent root = loader.load();
    dash =new DashWorkerController();
    dash=loader.getController();
    dash.setData(Currentuser);
     supbtn.getScene().setRoot(root);


		}         
					          
					          
					          
					          
					          
				  
/////////////////////////////////////////////////////Mayamen Role//////////////////////////////////////////////////////
					          
					          
					          
					          
					          
					          
////////////////////////////////////////////////////Nadia Role////////////////////////////////////////////////////////////
	
					          
					          
					          
					          
/////////////////////////////////////////////////////Amine Role////////////////////////////////////////////////////////				  
				  
				  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
						  
             }
				
			  
	    } 
	if(Currentuser==null)
	{
		
			  System.out.println("Authentication 8alta");
	            Alert alerteLogin = new Alert(Alert.AlertType.WARNING);
	            alerteLogin.setTitle("Failure Authentication");
	            alerteLogin.setContentText("check Login and Password");
	            alerteLogin.showAndWait();	  
			
	}
	 }
////////////////////////////////////////////Inscription//////////////////////////////////////////	 
	    @FXML
	    void inscription(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Inscription.fxml"));
            Parent root = loader.load();
           
            supbtn.getScene().setRoot(root);
	    }
	 
}
