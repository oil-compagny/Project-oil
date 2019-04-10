package dashboardGS.views;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.jfoenix.controls.JFXTextField;
import Entities.Role;
import Entities.User;
import Interfaces.IUserServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class AddWorker implements Initializable {
	@FXML
    private JFXTextField ref;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField lastname;
    @FXML
    private VBox success;
    
	static String jndiName="PI-ear/PI-ejb/UserServiceImpl!Interfaces.IUserServiceRemote";
	public static Context context;
	public static IUserServiceRemote proxy ;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			context = new InitialContext();
			 proxy = (IUserServiceRemote )  context.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @FXML
    void Add(MouseEvent event) throws IOException {
    	User U = new User();
    	Boolean test = true;
    	try{
    		int a=Integer.parseInt(ref.getText());
    	if(name.getText().equals("") )
    		{
    		name.setText("Give the name");
    		test = false;
    		}
    	else if(lastname.getText().equals(""))
    	{
    		lastname.setText("Give the lastname");
    		test = false;
    	}
    		else if (a<0)
    		{
    			ref.setText("must be integer");
    			test = false;
    		}
    	
    	try
		{Integer.parseInt(ref.getText());
			
		}catch (NumberFormatException e2) {
			ref.setText("must be integer");
			
		}
    	
    	
    	}catch (RuntimeException e) {
			// TODO: handle exception
    		if(name.getText().equals("") )
    		{
    		name.setText("Give the name");
    		test = false;
    		}
    	else if(lastname.getText().equals(""))
    	{
    		lastname.setText("Give the lastname");
    		test = false;
    	}
    		else if (ref.getText().equals(""))
    		{
    			ref.setText("must be integer");
    			test = false;
    		}
    		
		}
    	if(test==true)
    	{
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Dialog");
    		alert.setContentText("Are you ok with this?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    // ... user chose OK
    			U.setFirstname(name.getText());
        		U.setLastName((lastname.getText()));
        		U.setCin(ref.getText());
        		U.setRole(Role.Worker);
        		U.setIsActif(true);
        		U.setSalary(0.0);
        		U.setLogin(U.getFirstname()+U.getLastName());
        		U.setPasswd(U.getFirstname()+U.getCin());
        		U.setMail(U.getFirstname()+U.getLastName()+"@lion.com");
        		U.setAutorisation(true);
        	UiController.Current_GS=proxy.addWorkerToGasStation(U, UiController.Current_GS.getId());
        	  success.getChildren().clear();
        	  Node node=FXMLLoader.load(getClass().getResource("/dashboardGS/views/Success.fxml"));
        	  success.getChildren().add(node);
        		
        		
    		} 
    		else {
    		   
    		   
    		}
    		
    	}
    }
	
}
