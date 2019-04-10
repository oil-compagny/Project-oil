package dashboard.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import Entities.Commande;
import Interfaces.IRefiningServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ItemController implements Initializable{
	@FXML
	private HBox itemC;
	 @FXML
	    private Label gas_station_Name;

	    @FXML
	    private Label gasoline_qty;
	    @FXML
	    private Label Mazout_qty;

	    @FXML
	    private Label diesel_qty;
	    @FXML
	    private Label id;
	    @FXML
	    private JFXButton btnH;
	    public static int idCommande;

	    String jndiName="PI-ear/PI-ejb/RefiningServiceImpl!Interfaces.IRefiningServiceRemote";
	    Context context;
	    

  CommandsController cController = new CommandsController();
	public Label getGas_station_Name() {
			return gas_station_Name;
		}
		public void setGas_station_Name(Label gas_station_Name) {
			this.gas_station_Name = gas_station_Name;
		}
		public Label getGasoline_qty() {
			return gasoline_qty;
		}
		public void setGasoline_qty(Label gasoline_qty) {
			this.gasoline_qty = gasoline_qty;
		}
		public Label getMazout_qty() {
			return Mazout_qty;
		}
		public void setMazout_qty(Label mazout_qty) {
			Mazout_qty = mazout_qty;
		}
		public Label getDiesel_qty() {
			return diesel_qty;
		}
		public void setDiesel_qty(Label diesel_qty) {
			this.diesel_qty = diesel_qty;
		}
	// Event Listener on Button.onMouseClicked
	 @FXML
	    void Handle_command(MouseEvent event) throws IOException, NamingException  {
		 context= new InitialContext();
			IRefiningServiceRemote  proxy;
		  	 
		    proxy=(IRefiningServiceRemote) context.lookup(jndiName);
		    
		     proxy.Valider_commande(Integer.valueOf(id.getText())); 
		 
		     
		      
		      idCommande =Integer.valueOf(id.getText());
		
		 FXMLLoader loader= new FXMLLoader(getClass().getResource("UI.fxml"));
		Parent root=loader.load();
		UIController UIC = loader.getController();
		UIC.getContentArea().getChildren().removeAll();
		
		FXMLLoader loader1= new FXMLLoader(getClass().getResource("Refining.fxml"));
		Node node=loader1.load();
		
		
		
		
		RefiningController refC= loader1.getController();
		refC.setData(Integer.valueOf(gasoline_qty.getText()),Integer.valueOf(diesel_qty.getText()),
				Integer.valueOf(Mazout_qty.getText()));
		UIC.getContentArea().getChildren().add(node);
		
		
		
		Node n = (Node) event.getSource();
		
		Stage s = (Stage) n.getScene().getWindow();
		s.close();
		
		
		
		Scene ns= new Scene(root);
		Stage stage=new Stage();
		stage.setScene(ns);
		stage.show();
		
		
	    }
	 public Label getId() {
		return id;
	}
	public void setId(Label id) {
		this.id = id;
	}
	public void setData(String gas_station_Name ,int gasoline_qty,int diesel_qty,int Mazout_qty,int id,int v)
		{
		     this.gas_station_Name.setText(gas_station_Name);
		    
			String a =String.valueOf(gasoline_qty);
			String b =String.valueOf(diesel_qty);
			String c =String.valueOf(Mazout_qty);
			this.gasoline_qty.setText(a);
			this.diesel_qty.setText(b);
			this.id.setText(String.valueOf(id));
			if(v==1)
			{
				itemC.setDisable(true);
				
			}
		}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
