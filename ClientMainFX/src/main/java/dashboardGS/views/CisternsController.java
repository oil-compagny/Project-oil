package dashboardGS.views;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Cistern;
import Entities.Pump;
import Entities.Type;
import Interfaces.ICisternServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CisternsController implements Initializable{
	@FXML
	private Label nbP;
	@FXML
	private Label capacity;
	@FXML
	private Label current;
	@FXML
	private Pane content;
	@FXML
	private Label type;
	@FXML
	private ScrollPane pumps;
	@FXML
	private VBox pnItems;
	@FXML
    private Label idC;
		static String jndiName="PI-ear/PI-ejb/Cistern_Service!Interfaces.ICisternServiceRemote";
	 static Context context;
	 static ICisternServiceRemote proxy ;
	/* static String jndiName1="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
	 static Context context1;
	 static IGas_StationServiceRemote proxy1 ;*/
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			context = new InitialContext();
			 proxy = (ICisternServiceRemote)  context.lookup(jndiName);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void Diesel_Pumps(MouseEvent event) {
		// TODO Autogenerated
		for(Cistern c : proxy.ShowAllCistern(UiController.Current_GS.getId()))
		{
			if(c.getOtypeT().getType()==Type.Diesel)
			{
				
				capacity.setText(String.valueOf(c.getOtypeT().getQuantity()));
				 current.setText(String.valueOf(c.getCurrent()));;
				
				 type.setStyle("-fx-text-fill:#ffae00;");
				 type.setText("DIESEL");
				 idC.setText(String.valueOf(c.getId()));
				 nbP.setText(String.valueOf(c.getPumps().size()));
				 pnItems.getChildren().clear();
				 try {
						ShowPumpsAff(c.getPumps());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void Gasoline_Pumps(MouseEvent event) {
		// TODO Autogenerated
		for(Cistern c : proxy.ShowAllCistern(UiController.Current_GS.getId()))
		{
			if(c.getOtypeT().getType()==Type.Gasoline)
			{
				
				capacity.setText(String.valueOf(c.getOtypeT().getQuantity()));
				 current.setText(String.valueOf(c.getCurrent()));;
				
				 type.setStyle("-fx-text-fill:green;");
				 type.setText("GASOLINE");
				 idC.setText(String.valueOf(c.getId()));
				 nbP.setText(String.valueOf(c.getPumps().size()));
				 pnItems.getChildren().clear();
				 try {
						ShowPumpsAff(c.getPumps());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void Mazout_Pumps(MouseEvent event) {
		// TODO Autogenerated
		for(Cistern c : proxy.ShowAllCistern(UiController.Current_GS.getId()))
		{
			if(c.getOtypeT().getType()==Type.Mazout)
			{
				
				capacity.setText(String.valueOf(c.getOtypeT().getQuantity()));
				 current.setText(String.valueOf(c.getCurrent()));;
				
				 type.setStyle("-fx-text-fill:black;");
				 type.setText("MAZOUT");
				 idC.setText(String.valueOf(c.getId()));
				 nbP.setText(String.valueOf(c.getPumps().size()));
				 try {
					ShowPumpsAff(c.getPumps());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void Add_Pump(MouseEvent event) {
		// TODO Autogenerated
		proxy.addPumpToCistern(proxy.findCistern(Integer.valueOf(idC.getText())).getId());
	}
	void ShowPumpsAff(List<Pump> L) throws IOException
	{
		pnItems.getChildren().clear();
		HBox hb = new HBox();
		
			
			
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
	             itemCont.getBtn().setVisible(false);
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

	
}}
