package dashboardGS.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextField;

import Entities.Commande;
import Entities.User;
import Interfaces.IGas_StationServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CommandsShowController implements Initializable{
	@FXML
	private ScrollPane scrollPaneID;
	
	 @FXML
	 private Pane ContenetArea;
	 
	 
	 
	 

	public ScrollPane getScrollPaneID() {
		return scrollPaneID;
	}
	public void setScrollPaneID(ScrollPane scrollPaneID) {
		this.scrollPaneID = scrollPaneID;
	}
	@FXML
	private VBox vbID;
	
	
    static String jndiName="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
	static Context context;
	public static IGas_StationServiceRemote proxy ;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		try {
			context = new InitialContext();
			 proxy = (IGas_StationServiceRemote)  context.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   FXMLLoader loader;
		   Node node;
		List <Commande> L = proxy.showCommands(UiController.Current_GS.getAddress());
		ItemCommandController itemCont ;
		vbID.getChildren().clear();
		if(L!=null){
		for(Commande c : L)
		{
			
			loader = new FXMLLoader(getClass().getResource("/dashboardGS/views/ItemCommand.fxml"));
			
            try {
				node=loader.load();
				   itemCont=loader. getController();
		            itemCont.setData(c.getAdresse_arrive(), c.getQte_G(), c.getQte_D(), c.getQte_M(),c.getId(),c.getReceived());
		            vbID.getChildren().add(node);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		}
		}
	
}
   

	

