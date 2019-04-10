package dashboardGS.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Cistern;
import Entities.Type;
import Interfaces.IGas_StationServiceRemote;
import Interfaces.IUserServiceRemote;
import dashboard.tools.FillProgressIndicator;
import dashboard.tools.RingProgressIndicator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class HomeController implements Initializable {
	@FXML
	private Pane content;
	@FXML
	private Pane diesel;
	@FXML
	private Pane oils;
	@FXML
	private Pane gas;
	
	  public static String jndiNameGS="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
		public static Context contextGS;
		public static IGas_StationServiceRemote proxyGS ;
   public void initialize(URL url, ResourceBundle rb) {
       // TODO
	   try {
			contextGS = new InitialContext();
			 proxyGS = (IGas_StationServiceRemote)  contextGS.lookup(jndiNameGS);
			 UiController.Current_GS=proxyGS.ShowGasOfGerant(LoginController.getUser().getId());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   	RingProgressIndicator rg  = new RingProgressIndicator();
		FillProgressIndicator dies = new FillProgressIndicator();
		FillProgressIndicator oil= new FillProgressIndicator();
		FillProgressIndicator gass = new FillProgressIndicator();
		dies.getStylesheets().add(RingProgressIndicator.class.getResource("/dashboard/css/Diesel.css").toExternalForm());
		oil.getStylesheets().add(RingProgressIndicator.class.getResource("/dashboard/css/Oil.css").toExternalForm());
		gass.getStylesheets().add(RingProgressIndicator.class.getResource("/dashboard/css/Gas.css").toExternalForm());

		oil.setProgress(20);
		
		List <Cistern> L = UiController.Current_GS.getCisterns();
   	for (Cistern c : L )
   	{ 
   		int a=(int) ((c.getCurrent())*100/c.getOtypeT().getQuantity());
   		System.out.println(a);
   		if(c.getOtypeT().getType()==Type.Mazout)
   		{oil.setProgress(a);}
   		else if(c.getOtypeT().getType()==Type.Gasoline)
   		{gass.setProgress(a);}
   		else
   		{
   			dies.setProgress(a);
   		}
   	}
		
		rg.makeIndeterminate();
		diesel.getChildren().add(dies);
		
		gas.getChildren().add(oil);
		oils.getChildren().add(gass);
       
   }

}
