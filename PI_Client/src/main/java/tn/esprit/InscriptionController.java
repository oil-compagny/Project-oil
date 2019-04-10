package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.EventLoop.State;

import Entities.Countries;
import Entities.Role;
import Entities.States;
import Entities.User;
import Services.CountrieServiceRemote;
import Services.ServiceUserRemote;
import Services.StateServiceRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;



public class InscriptionController implements Initializable  {

    @FXML
    private AnchorPane idW;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtMail;

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXTextField txtCin;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXComboBox<String> comboRole;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton registerbtn;

    @FXML
    private Label LW;

    @FXML
    private Label IDW;

    @FXML
    private Label nameW;

    @FXML
    private Label PW;

    @FXML
    private Label LastW;

    @FXML
    private Label MW;

    @FXML
    private Label PDW;
    @FXML
    private JFXComboBox<String> cb;

    @FXML
    private JFXComboBox<String> cb1;

    
/////////////////////////////////////////////////INITIALIZE///////////////////////////////////
@Override
public void initialize(URL location, ResourceBundle resources) {
comboRole.getItems().addAll("Gerant","Fournisseur","Chauffeur");



         if(txtMail.getText().length() != 0 && txtName.getText().length() != 0
             &&txtCin.getText().length() != 0 && txtLogin.getText().length() != 0
              &&txtPassword.getText().length() != 0 && txtLastName.getText().length() != 0)
               {
                 registerbtn.setDisable(false);

                }
          else{
                  registerbtn.setDisable(true);
                }
         
         BuildItems();
         
         
} 
    
    
    
    
    
/////////////////////////////////////Add User///////////////////////////////////////////////
    @FXML
    void AddUser(ActionEvent event) {
    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
    	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                ; 
StringBuilder sb = new StringBuilder(8); 

for (int i = 0; i < 8; i++) { 
int index 
= (int)(AlphaNumericString.length() 
    * Math.random()); 

sb.append(AlphaNumericString 
      .charAt(index)); 
}
    	
   		try {
   				Context context3 = new InitialContext();
   				ServiceUserRemote proxy3 =(ServiceUserRemote) context3.lookup(jndiName);
   				User user=new User();
   				 user.setCin(txtCin.getText());
   				 user.setFirstname(txtName.getText());
   				 user.setLastName(txtLastName.getText());
   				 user.setPhone(txtPhone.getText());
   				 user.setMail(txtMail.getText());
   				 user.setLogin(txtLogin.getText());
   				 user.setPasswd(txtPassword.getText());
   				 user.setIsActif(false);
   				 user.setAutorisation(false);
   				 user.setAllowedtochangepassword(false);
   				 user.setState(cb1.getSelectionModel().getSelectedItem());
   				 user.setCountry(cb.getSelectionModel().getSelectedItem());
   				 user.setRole(Role.valueOf(comboRole.getSelectionModel().getSelectedItem()));
   				 user.setCode(sb.toString());
   				
   				proxy3.addUser(user);
   				Notifications notif=Notifications.create()
   						.title("Registration")
   						.text("Registred with success")
   						.position(Pos.TOP_CENTER)
   						.graphic(null)
   						.hideAfter(Duration.seconds(5));
   			    notif.darkStyle();
   				notif.showInformation();
   				
   				
   				
   					
   		} catch (NamingException e1) {
   			
   			e1.printStackTrace();
   		}
   		

    }

  /////////////////////////////////Mail//////////////////////////////////////////////////  

    @FXML
    void test1(KeyEvent event) {
    	String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(txtMail.getText());

		if (controler.matches()) {
		
			MW.setText(" ");
			System.out.println("lol");
			
		} else {
			
			MW.setText("please type a valid email address !");
			
		}

    	

    }
////////////////////////////////////////Login///////////////////////////////////////////
    @FXML
    void test2(KeyEvent event) throws NamingException {
    	String jndiNameCategory = "PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
		Context context = new InitialContext();
		ServiceUserRemote proxyCategory = (ServiceUserRemote) context.lookup(jndiNameCategory);
		Boolean r = proxyCategory.findByLogin(txtLogin.getText());
		LW.setVisible(false);
		LW.setText(" ");

		if (r == true) {
			LW.setVisible(true);
			LW.setText("Login already exists !");
			
		}
		else {LW.setText("");
		
			
			
		}

    }
//////////////////////////////////////Cin//////////////////////////////////////////////
    @FXML
    void test3(KeyEvent event) throws NamingException {

    	  String jndiNameCategory = "PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
  		Context context = new InitialContext();
  		ServiceUserRemote proxyCategory = (ServiceUserRemote) context.lookup(jndiNameCategory);
  		Boolean r = proxyCategory.findByCin(txtCin.getText());
  		IDW.setVisible(false);
  		IDW.setText(" ");

  		if (r == true) {
  			
  			IDW.setVisible(true);
  			IDW.setText("Identity Document already exists !");
  			
  		}
  		else {IDW.setText("");
  		
  			
  			
  		}
    }
///////////////////////////////////Name///////////////////////////////////////////////
    @FXML
    void test4(KeyEvent event) {
    	 if (txtName.getText() == null || txtName.getText().length() == 0)  {
            nameW.setVisible(true);
            nameW.setText("please type a valid First Name");
            
         }
  	   else{
  		    nameW.setText("");
  			
  	   }

    }
//////////////////////////////////////////Phone///////////////////////////////////////
    @FXML
    void test5(KeyEvent event) {
    	if (txtPhone.getText() == null || txtPhone.getText().length()<8) {
  			try {
  				int i = Integer.parseInt(txtPhone.getText());
  			} catch (NumberFormatException e) {
  				PW.setVisible(true);
  				PW.setText("please type only numbers !");
  				
  			}
  		}

  		else {
  			PW.setText("");
  			
  			
  		}


    }
///////////////////////////////Last Name////////////////////////////////////////////
    @FXML
    void test6(KeyEvent event) {
    	if (txtLastName.getText() == null || txtLastName.getText().length() == 0) {
	          LastW.setVisible(true);
	          LastW.setText("please type a valid last Name");
	          
	       }
		   else{
			   LW.setText("");
				
		   }

    	

    }
/////////////////////////////////////Password//////////////////////////////////////////
    @FXML
    void test7(KeyEvent event) {
    	 if (txtPassword.getText() == null || txtPassword.getText().length() == 0) {
	          PDW.setVisible(true);
	          PDW.setText("please type a valid Password");
	          
	       }
		   else{
			    PW.setText("");
				
		   }


    }
///////////////////////////////////////States////////////////////////////////////////////////
   
	public void BuildItems1(){
        String jndiName="PI-ear/PI-ejb/StateService!Services.StateServiceRemote";
  	  try {
  		  
  		 Context context=new InitialContext();
  		StateServiceRemote proxy = (StateServiceRemote) context.lookup(jndiName);
  		List<States> list ;
  		ObservableList<States> list1 = FXCollections.observableArrayList();
  		ObservableList<String> list2 = FXCollections.observableArrayList();
  		list=proxy.mapcs(cb.getValue().toString(), 1) ;
  		for(int i = 0; i < list.size(); i++){
  			list2.add(list.get(i).getName());
  		//list1.add(list2.get(i));	
  		cb1.setItems(list2);
  		}
  		System.out.print(list);
  	} catch (NamingException e) {
  		 //TODO Auto-generated catch block
  		e.printStackTrace();
  	}
  	registerbtn.setDisable(false);
	}
    
    
    
///////////////////////////////////////Countries/////////////////////////////////////////////    
	public void BuildItems(){
		
     String jndiName="PI-ear/PI-ejb/CountrieService!Services.CountrieServiceRemote";
  	  try {
  		 Context context=new InitialContext();
  		CountrieServiceRemote proxy = (CountrieServiceRemote) context.lookup(jndiName);
  		List<Countries> list ;
  		ObservableList<Countries> list1 = FXCollections.observableArrayList();
  		ObservableList<String> list2 = FXCollections.observableArrayList();
  		list=proxy.findall() ;
  		for(int i = 0; i < list.size(); i++){
  			list2.add(list.get(i).getName());
  		//list1.add(list2.get(i));	
  			cb.setItems(list2);
  		}
  		System.out.print(list1);
  	} catch (NamingException e) {
  		 //TODO Auto-generated catch block
  		e.printStackTrace();
  	}
	}
	
/////////////////////////////Back to  Login//////////////////////////////////
	@FXML
    void out(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
       
        btnLogin.getScene().setRoot(root);
    }
}
