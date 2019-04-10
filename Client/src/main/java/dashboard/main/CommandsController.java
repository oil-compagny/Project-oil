package dashboard.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Commande;
import Entities.RefiningFactory;
import Entities.Zone;
import Interfaces.IRefiningServiceRemote;
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

public class CommandsController implements Initializable{
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
	
	 String jndiName="PI-ear/PI-ejb/RefiningServiceImpl!Interfaces.IRefiningServiceRemote";
	    Context context;
	    
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	
		try {
			
			context= new InitialContext();
			IRefiningServiceRemote  proxy;
		  	 
		    proxy=(IRefiningServiceRemote) context.lookup(jndiName);
			
			 
		    List<Commande> commandes = new ArrayList<>();
		      commandes=proxy.findCommandes();
		     
		        System.out.println(commandes);
					 
					 Node node;
					 ItemController ItemCont;
					 FXMLLoader loader;
					
					 
					 
					 for(int i=0;i<commandes.size();i++)
					 {
					
				    loader = new FXMLLoader(getClass().getResource("Item.fxml"));
					node=loader.load();
				 
				    ItemCont=loader.getController();
				    ItemCont.setData(commandes.get(i).getAdresse_arrive(),commandes.get(i).getQte_D(),commandes.get(i).getQte_G()
				    		,commandes.get(i).getQte_M(),commandes.get(i).getId(),commandes.get(i).getValidate());
				     vbID.getChildren().add(node);
				
				       }
			 
			
			
			
			
		         } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		         } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		   
	
	
}

   

	
	
}
