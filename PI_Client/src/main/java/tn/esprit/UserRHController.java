package controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Entities.User;
import Services.ServiceUserRemote;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;

public class UserRHController {

    @FXML
    private JFXTextField search;

    @FXML
    private JFXButton accept;

    @FXML
    private JFXButton decline;

    @FXML
    private TableView<User> tbuser;

    @FXML
    private TableColumn<User, String> FN;

    @FXML
    private TableColumn<User, String> LN;

    @FXML
    private TableColumn<User, String> U;

    @FXML
    private TableColumn<User, String> EM;

    @FXML
    private TableColumn<User, String> C;

    @FXML
    private TableColumn<User, String> S;

    @FXML
    private TableColumn<User, String> R;
////////////////////////Initialize///////////////////////////////////////////////
    @FXML
	public void initialize() throws NamingException {
    	
    	LoadData();
    	
    }
    /////////////////////Add User ////////////////////////////////////////////////
    @FXML
    void addUser(ActionEvent event) {
    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
		Context context;
		try {
			context = new InitialContext();
			ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
			User u = tbuser.getSelectionModel().getSelectedItem();
			proxy.Autoriser(u.getId());
			LoadData();
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
		try{
            String host ="smtp.gmail.com" ;
            String user = "mohamedlamine.rahali@esprit.tn";
            String pass = "hammahamma12";
            String to ="";//Chicha help
            String from = "mohamedlamine.rahali@esprit.tn";
            String subject = "This is confirmation to login to the app";
            String messageText = "Your Code is:";
            boolean sessionDebug = false;
           
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            ////////////////send ma
            msg.setText("You have been accepted to login to the app");

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
		

    }
////////////////////////////Decline User/////////////////////////////////////////
    @FXML
    void declineUser(ActionEvent event) {
    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
		Context context;
		try {
			context = new InitialContext();
			ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
			User u = tbuser.getSelectionModel().getSelectedItem();
			proxy.Decline(u.getId());
			LoadData();
		} catch (NamingException e) {
			
			e.printStackTrace();
		}

    }
//////////////////////////////////Search////////////////////////////////////////////
    @FXML
    void searchUser(ActionEvent event) throws NamingException {
    	ObservableList tableuser = tbuser.getItems();
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
            	tbuser.setItems(tableuser);
            }
            String value = newValue.toLowerCase();
            ObservableList<User> subentries = FXCollections.observableArrayList();

            long count = tbuser.getColumns().stream().count();
            for (int i = 0; i < tbuser.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tbuser.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tbuser.getItems().get(i));
                        break;
                    }
                }
            }
            tbuser.setItems(subentries);
        });
        LoadData();

    }
 //////////////////////////////Load Data/////////////////////////////////////////////
    public void LoadData() throws NamingException{
	
	
	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
	Context context;
	context = new InitialContext();
	ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
	List<User>list;
	ObservableList<User>list1=FXCollections.observableArrayList();
	list=proxy.getallUserNotTreated();
	for(int i = 0; i < list.size(); i++){
		if(list.get(i).getAutorisation()==false){
			list1.add(new User(list.get(i).getId(),list.get(i).getCin(),list.get(i).getFirstname(),list.get(i).getLastName()
			,list.get(i).getPhone(),list.get(i).getMail(),list.get(i).getLogin(),list.get(i).getPasswd(),
			list.get(i).getIsActif(),list.get(i).getAutorisation(),list.get(i).getCountry(),list.get(i).getState()
			,list.get(i).getCode(),list.get(i).getAllowedtochangepassword(),list.get(i).getRole()));
	   
			tbuser.setItems(list1);
			
			
		}
		
		FN.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
		LN.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		U.setCellValueFactory(new PropertyValueFactory<>("login"));
		EM.setCellValueFactory(new PropertyValueFactory<>("mail"));
		C.setCellValueFactory(new PropertyValueFactory<>("country"));
		S.setCellValueFactory(new PropertyValueFactory<>("state"));
		R.setCellValueFactory(new PropertyValueFactory<>("role"));
		}
    }
///////////////////////////////////////////////////////////////////////////
}

    
    
    	
    	
    
    
    
    
    
    
    
    
    
    
 

