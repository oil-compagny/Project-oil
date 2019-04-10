package dashboardGS.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import Entities.Cistern;
import Entities.Pump;
import Entities.User;
import Interfaces.IGas_StationServiceRemote;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * @author SaidaniA
 *
 */

public class AffectationController implements Initializable {
	  @FXML
	    private JFXRadioButton Afternoon;

	    @FXML
	    private JFXRadioButton Night;
	    @FXML
	    private JFXTextField ref;
	    

	    @FXML
	    private JFXRadioButton Day;

	    @FXML
	    private FontAwesomeIcon icon_pump;

	    @FXML
	    private VBox user_info;

	    @FXML
	    private FontAwesomeIcon user_icon;

	    @FXML
	    private ScrollPane pumps;

	    @FXML
	    private VBox pnItems;
	    @FXML
	    private JFXComboBox<?> shift;
	    @FXML
	    private Label lastname;
	    @FXML
	    private Label name;
	    public static String jndiName="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
		public static Context context;
		public static IGas_StationServiceRemote proxy ;
		public static User WorkerF;
		public static String ShiftS="";
		static List<Cistern> c = UiController.Current_GS.getCisterns();
		
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			pnItems.getChildren().clear();
			try {
				context = new InitialContext();
				 proxy = (IGas_StationServiceRemote)  context.lookup(jndiName);
				 
				 UiController.Current_GS=proxy.ShowGasOfGerant(LoginController.getUser().getId());
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	        }
		  @FXML
		    void dayC(MouseEvent event) throws IOException {
			  ShiftS="Day";
			  	if(Day.isSelected())
			  	{
			  		Afternoon.setSelected(false);
			  		Night.setSelected(false);
			  		pnItems.getChildren().clear();
			  		ShowPumpsAff("Day",c);
			  		ShiftS="Day";
			  		if(WorkerF==null)
					{
						pnItems.setVisible(false);
					}
			  	}
			  	if(!Night.isSelected()&&!Day.isSelected()&&!Afternoon.isSelected())
		    		pnItems.setVisible(false);
		    }

		    @FXML
		    void afternoonC(MouseEvent event) throws IOException {
		    	ShiftS="Afternoon";
		    	if(Afternoon.isSelected())
			  	{
			  		Day.setSelected(false);
			  		Night.setSelected(false);
			  		pnItems.getChildren().clear();
			  		ShowPumpsAff("Afternoon",c);
			  		if(WorkerF==null)
					{
						pnItems.setVisible(false);
					}
			  		
			  	}
		    	if(!Night.isSelected()&&!Day.isSelected()&&!Afternoon.isSelected())
		    		pnItems.setVisible(false);
		    	
		    }

		    @FXML
		    void nightC(MouseEvent event) throws IOException {
		    	ShiftS="Night";
		    	if(Night.isSelected())
			  	{
			  		Afternoon.setSelected(false);
			  		Day.setSelected(false);
			  		pnItems.getChildren().clear();		
			  		ShowPumpsAff("Night",c);
			  		if(WorkerF==null)
					{
						pnItems.setVisible(false);
					}
			  		
					// TODO Auto-generated method stub
				
			  	}
		    	if(!Night.isSelected()&&!Day.isSelected()&&!Afternoon.isSelected())
		    		pnItems.setVisible(false);

		    }
		    
		    @FXML
		    void searchUser(MouseEvent event) throws NamingException {
		    	UiController.Current_GS=proxy.ShowGasOfGerant(LoginController.getUser().getId());
		    	List <User> workers= UiController.Current_GS.getWorkers();
				for(User w : workers)
				{
					if(w.getCin().equals(ref.getText()))
						WorkerF=w;
				}
				if(WorkerF!=null)
				{
			    name.setText(WorkerF.getFirstname());
				lastname.setText(WorkerF.getLastName());
				pnItems.setVisible(true);
				}else 
				{
					pnItems.setVisible(false);
					name.setText("Not found");
					lastname.setText("Not found");
					
				}

		    }
		    
		    
		    
		    
		    
		    
		    
		    
		void ShowPumpsAff(String Shift,List<Cistern> pumps) throws IOException
		{
			
			HBox hb = new HBox();
			pnItems.getChildren().clear();
				List<Pump> L =proxy.AvailablePumpShift(ShiftS,pumps);
				System.out.println(L);
				Node node;
				pumpController itemCont;
				FXMLLoader loader;
				hb = new HBox();
				
				int j=0;
				hb.setSpacing(10);
				if(L!=null){
				for(int i=0;i<L.size();i++)
				{
					j=j+1;
					loader = new FXMLLoader(getClass().getResource("/dashboardGS/views/pump.fxml"));
		            node=loader.load();
		            itemCont=loader.<pumpController> getController();
		            itemCont.setData(String.valueOf(L.get(i).getValeurs()), 
		            String.valueOf(L.get(i).getUnitPrice()), String.valueOf(L.get(i).getOtype().getType().toString()));
		            itemCont.setId(L.get(i).getId());
		             hb.getChildren().add(node);
		             if(j>3)
		             {
		            	 j=0;
		            	 pnItems.getChildren().add(hb);
		            	 hb=new HBox();
		            	 hb.setSpacing(10);
		             }
		            
				}
				}
				pnItems.getChildren().add(hb);

		
		}
			
		}
	    

	

	

