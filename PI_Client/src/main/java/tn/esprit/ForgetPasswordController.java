package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Entities.User;
import Services.ServiceUserRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ForgetPasswordController {
   
	static String Emailneeded ;
	
    @FXML
    private JFXTextField mail;

    @FXML
    private JFXButton code;

    @FXML
    void sendcode(ActionEvent event) throws NamingException, IOException {
   
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
    	
    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
		 Context context;
		 context = new InitialContext();
		 ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
		 System.out.println(mail.getText());
		 User u	= proxy.findmail(mail.getText());
		 System.out.println(u.getId());
		 
		 
    	proxy.AddCode(u.getId(), sb.toString());
    	
    	System.out.println(u.getId());
    	try{
            String host ="smtp.gmail.com" ;
            String user = "mohamedlamine.rahali@esprit.tn";
            String pass = "hammahamma12";
            String to = mail.getText();
            String from = "mohamedlamine.rahali@esprit.tn";
            String subject = "This is confirmation number for your expertprogramming account. Please insert this number to restore your password.";
            String messageText = "Your Code is:";
            boolean sessionDebug = false;
           Emailneeded = mail.getText();
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
            msg.setText("Your Code is "+"          "+ sb.toString());

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    	
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RestorePass.fxml"));
        Parent root = loader.load();
       
        code.getScene().setRoot(root);
    	
    	
    	
    	
    }
    
    
}


    
    
    
    

