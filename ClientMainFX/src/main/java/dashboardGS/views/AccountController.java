package dashboardGS.views;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextField;

import Entities.Accounting;
import Interfaces.IUserServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountController implements Initializable{
	  @FXML
	    private VBox success;

	    @FXML
	    private JFXTextField price;

	    @FXML
	    private JFXTextField vcheckout;

	    @FXML
	    private JFXTextField vchekin;
	    @FXML
	    private Label id;
	    @FXML
	    private JFXTextField up;
	    @FXML
	    private Label type;
	    @FXML
	    private Label idA;
	    static String jndiName="PI-ear/PI-ejb/UserServiceImpl!Interfaces.IUserServiceRemote";
		public static Context context;
		public static IUserServiceRemote proxy ;
		public static int change=0;
		public static String newSalary;
public void setData(String vchekin,String id,String u,String t,String idA)
{
	System.out.println("ICI");
	this.vchekin.setText(vchekin);
	this.id.setText(id);
	up.setText(u);
	type.setText(t);
	this.idA.setText(idA);
}

	    @FXML
	    void proceed(MouseEvent event) {
	    	 Double expected;
	    	 Double total;
	    	 int vin=Integer.valueOf(vchekin.getText());
	    	 int vout ;
	    	 Double up=Double.valueOf(this.up.getText());
	    	
	    	 
	    	
	    	 //CONTROL DE SAISIE 
	    	 
	    	 //TEST Vin>VOUT
	    	 
	    	 //TEST RETRIVE
	    	 try {
		    		vout = Integer.valueOf(vcheckout.getText());
					} catch (NumberFormatException n) {
						// TODO: handle exception
						vcheckout.setText("Error value");
						return;
					}
	    	 try {
	    		total=Double.valueOf(price.getText());
			} catch (NumberFormatException n) {
				// TODO: handle exception
				price.setText("Error value");
				return;
			}
	    	 
	    	 
	    	 
	    	 
	    	 expected=(vout-vin)*up;
	    	 System.out.println("T :_"+total);
	    	 System.out.println("E :_"+expected);
	    	 if(total != expected)
	    	 {
	    		 //ALERT 

	     		Alert alert = new Alert(AlertType.CONFIRMATION);
	     		alert.setTitle("Confirmation Dialog");
	     		alert.setContentText("Expected value "+expected+"found :"+total+"You want to continue ? it will be retrived from salary");
	     		Optional<ButtonType> result = alert.showAndWait();
	    		if (result.get() == ButtonType.OK){
	    			
	    			Accounting Acc=proxy.Accounting(LoginController.getUser().getId(),Integer.valueOf(idA.getText()), total-expected,vout, type.getText());
	    		 //OK
	    		 change=1;
	    		 newSalary=String.valueOf(Acc.getWorker().getSalary());
	    		 Node n = (Node) event.getSource();
	    		 Stage s = (Stage) n.getScene().getWindow();
	    		 s.close();
	    		 
	    		 LoginController.dash.setData(Acc.getWorker());
	    		 
	    		 
	    		 return;
	    		}else return;
	    		 
	    	 }
	    	 
	    	Accounting Acc= proxy.Accounting(LoginController.getUser().getId(),Integer.valueOf(idA.getText()), total-expected,vout, type.getText());
	    	LoginController.dash.setData(Acc.getWorker());
	    	change=1;
	    	newSalary=String.valueOf(Acc.getWorker().getSalary());
    		 Node n = (Node) event.getSource();
    		 Stage s = (Stage) n.getScene().getWindow();
    		 s.close();
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try {
				context = new InitialContext();
				 proxy = (IUserServiceRemote )  context.lookup(jndiName);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
}
