package dashboardGS.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import Entities.Accounting;
import Entities.Cistern;
import Entities.Pump;
import Entities.User;
import Interfaces.IGas_StationServiceRemote;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.VBox;

import javafx.scene.layout.Pane;

public class DashWorkerController implements Initializable {
	@FXML
	private HBox parent;
	@FXML
	private VBox sidebar;
	@FXML
	private JFXButton Identity;
	@FXML
	private Pane content;
	@FXML
	private ScrollPane pumps;
	@FXML
	private HBox pnItems;
	@FXML
    private Label mail;
	@FXML
    private Label FName;
    @FXML
    private Label Lname;
    @FXML
    private Label salary;

	static String jndiName="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
	static Context context;
	static IGas_StationServiceRemote proxy ;
	static int idU;
	static int idG;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			context = new InitialContext();
			 proxy = (IGas_StationServiceRemote)  context.lookup(jndiName);
			 UiController.Current_GS=proxy.ShowGasOfGerant(LoginController.getUser().getStation().getGerant().getId());
			 ShowPumpsAff();
			 
			 salary.setText(String.valueOf(LoginController.getUser().getSalary()));
		} catch (NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setData(User u)
	{
		 mail.setText(u.getMail());
		FName.setText(u.getFirstname());
	   Lname.setText(u.getLastName());
	   salary.setText(String.valueOf(u.getSalary()));
	}
	
	void ShowPumpsAff() throws IOException
	{
		
		HBox hb = new HBox();
		pnItems.getChildren().clear();
		List<Accounting> La = proxy.CurrentPumps(LoginController.getUser().getId());
			System.out.println(La);
			Node node;
			pump2Controller itemCont;
			FXMLLoader loader;
			
		
			if(La!=null){
			for(Accounting a : La)
			{
				
					
				loader = new FXMLLoader(getClass().getResource("/dashboardGS/views/pump2.fxml"));
	            node=loader.load();
	            itemCont=loader.<pump2Controller> getController();
	            itemCont.setData(String.valueOf(a.getPump().getValeurs()),
	            String.valueOf(a.getPump().getUnitPrice()), 
	            String.valueOf(a.getPump().getOtype().getType().toString())
	            ,a.getId());
	            
	            itemCont.setId(a.getPump().getId());
	            if(a.getDone()==1)
	            	itemCont.getBtn_ac().setDisable(true);
	            pnItems.getChildren().add(node);
			}
			}
			

	
	}





}
