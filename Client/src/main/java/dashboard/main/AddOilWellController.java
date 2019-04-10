package dashboard.main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Entities.OilWells;
import Interfaces.IOilWellServiceRemote;
import Interfaces.IZoneServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AddOilWellController implements Initializable {
	  @FXML
	    private JFXButton AjoutPuits;
	  
	  
	   @FXML
	    private JFXTextField qte_estime;

	    @FXML
	    private JFXTextField qte_extraite;

	    @FXML
	    private Label labelZoneName;
	    @FXML
	    private Label L1;
        
	    
	    

	    String jndiName="PI-ear/PI-ejb/OilWellsService!Interfaces.IOilWellServiceRemote";
	    String jndiName1="PI-ear/PI-ejb/ZoneServiceImpl!Interfaces.IZoneServiceRemote";
	    IOilWellServiceRemote proxy1;
	    IZoneServiceRemote  proxy2;
	  	 
		 Context context;
		 Context context1;
	    @FXML
	    void Ajouter_puits(MouseEvent event) {
	   	       boolean test=true;
			  try {
				  context = new InitialContext();
				  
				proxy1=(IOilWellServiceRemote) context.lookup(jndiName);
				OilWells p = new OilWells();
				System.out.println(ZonesController.zs.getLocation());
				labelZoneName.setText(ZonesController.zs.getLocation());
				
				
				try{
					int a =Integer.valueOf(qte_estime.getText()); 
					p.setQuantite_estime(a);
					}
					catch(java.lang.NumberFormatException e)
					{ 
						L1.setText("You forgot to fill the estimated quantity field It must be a number !");
						test=false;
					}
			
				try {
				int b =Integer.valueOf(qte_extraite.getText()); 
				 p.setQuantite_extraite(b);
				}
				catch(java.lang.NumberFormatException e)
				{ 
					L1.setText("You forgot to fill the extracted quantity field  It must be a number !");
					test=false;
					return ;
				}
				
			
				
			   
			    
			    Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setContentText("Do you really sure about saving this ? ");
			    
			    Optional<ButtonType> result = alert.showAndWait();
			    if (result.get()==ButtonType.OK)
			    {
			    	if(test==true)
					   { L1.setVisible(false);
						p.setId(proxy1.ajoutPuit(p));
					    System.out.println(p.getId());
					    context1 = new InitialContext();
						
					    proxy2=(IZoneServiceRemote) context1.lookup(jndiName1);
					    proxy2.affecterPuitsAZone(p.getId(),ZonesController.zs.getId());
			    }
			   }
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

	    }




		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			labelZoneName.setText(ZonesController.zs.getLocation());
		}
}
