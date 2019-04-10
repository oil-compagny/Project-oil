package dashboard.main;

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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientWorker extends Application {
	public static Stage stage = null;
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/dashboardGS/views/DashWorker.fxml"));
		 
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        
        stage.show();
	
	}

	public static void main(String[] args) throws AddressException, MessagingException {
		//launch(args);
		mail();
	}
	public static void mail() throws AddressException, MessagingException
	{
		 String host ="smtp.gmail.com" ;
         String user = "mohamedlamine.rahali@esprit.tn";
         String pass = "hammahamma12";
         String to = "anis.saidani@esprit.tn";
         String from = "mohamedlamine.rahali@esprit.tn";
         String subject = "This is confirmation number for your expertprogramming account. Please insert this number to restore your password.";
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
         msg.setText("Your Code is ");

        Transport transport=mailSession.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        System.out.println("message send successfully");
    
 	
	}
}
