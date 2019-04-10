package dashboard.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Client extends Application {
	public static Stage stage = null;
	public static void main(String[] args) throws MessagingException  {
		// TODO Auto-generated method stub

		//sendMail("smtp.gmail.com", "587","anis.saidani03@gmail.com", "anis.saidani03@gmail.com","Lausanne22","anis.saidani@esprit.tn","Coucou","hello world");
		
		launch(args);
		  
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		
		
		
		Parent root = FXMLLoader.load(getClass().getResource("/dashboardGS/views/Login.fxml"));
		 
	        Scene scene = new Scene(root);
	        
	        stage.setScene(scene);
	        stage.initStyle(StageStyle.UNDECORATED);
	        this.stage = stage;
	        stage.show();
		
	}
	
	
	public static boolean sendMail(String server, String port , String from, final String username,
            final String password, String to, String subject, String body) 
            throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", server);
        prop.put("mail.smtp.port", port);
       
        prop.setProperty("mail.smtp.**ssl.enable", "true");
        prop.setProperty("mail.smtp.**ssl.required", "true");
        prop.put("mail.smtp.ssl.trust", server);
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
        session.getTransport("smtp");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        String msg = body;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        
        Transport.send(message);
        return true;
    }
	
	
	
	
    

}
