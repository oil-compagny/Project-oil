package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Claim;
import Entities.Role;
import Entities.User;
import Services.ClaimserviceRemote;
import Services.ServiceUserRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class HomeRHController implements Initializable{

    @FXML
    private PieChart cb1;

   
    
       Context context;
	
	String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
	String jndiName1 ="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote" ;
    //////////////////////////////////Initialize//////////////////////////////////
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
    	String jndiName1="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
    	Context context;
    	Context context1;
   ///////////////////////////////////////Claim//////////////////////////////////////////////////////////// 	
		try {
			context = new InitialContext();
			ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
			List<Claim> list ;
			
			list = proxy.getClaim();
			int  nS=0;
			int nP=0;
			int nE=0;
			int nO=0;
			int uC=0;
			int uF=0;
			int uA=0;
			
			
			for (int i=0 ; i<list.size();i++){
				
				if(list.get(i).getStatus().equals("Salary")){
					nS++;
				}
				else if (list.get(i).getStatus().equals("Product")){
				nP++;
				}
				else if (list.get(i).getStatus().equals("Event")){
					nE++;
				}
				else{
					nO++;
				}
			}
			ObservableList<PieChart.Data> pieChartData =
	    			 FXCollections.observableArrayList(
	     		            new PieChart.Data("Salary",nS ),
	     		            new PieChart.Data("Product",nP),
	     		           new PieChart.Data("Event",nE),
	     		          new PieChart.Data("Other",nO)
	    					 );
	     		    cb1.setData(pieChartData);
			
		
		
		} catch (NamingException e) {
			
		}
    	
 //////////////////////////////////////////////////User Chart////////////////////////////////////////////////   	
  /* try {
			context1 = new InitialContext();
			ServiceUserRemote proxy = (ServiceUserRemote) context1.lookup(jndiName);
			List<User> list1 ;
			
			list1 = proxy.getallUser();
			
			int uC=0;
			int uF=0;
			
			
			for (int i=0 ; i<list1.size();i++){
				
				if(list1.get(i).getAutorisation().equals(true)){
					uC++;
				}
				else  {
				uF++;
				}
				
				
			}
			
			ObservableList<PieChart.Data> pieChartData1 =
   		            FXCollections.observableArrayList(
   		           
   		           new PieChart.Data("Accepted User", uF),
     		       
                    new PieChart.Data("waiting user", uC));
   		    cb.setData(pieChartData1);
		
		
		} catch (NamingException e) {
		
     		   
		
	}
    */
		    
	
}}
    //////////////////////////////////Pie Chart Claim///////////////////////////////
    
    
    
    
    
    
    
    
    
    
