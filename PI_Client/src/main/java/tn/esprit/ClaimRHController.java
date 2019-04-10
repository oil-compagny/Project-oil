package controller;

import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.mail.imap.OlderTerm;

import Entities.Claim;
import Entities.User;
import Services.ClaimserviceRemote;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ClaimRHController {

    @FXML
    private TableView<Claim> tbuser;

    @FXML
    private TableColumn<Claim, Date> date;

    @FXML
    private TableColumn<Claim, String> statut;

    @FXML
    private TableColumn<Claim, String> text;
    @FXML
    private JFXTextField mail;

    @FXML
    private JFXTextField Statut1;
    
    @FXML
    private JFXTextArea text1;

    @FXML
    private JFXTextField search;
/////////////////////////////////////Accept Claim//////////////////////////////////////////////////////
    @FXML
    void AcceptClaim(ActionEvent event) throws NamingException {
    	String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
		Context context;
		context = new InitialContext();
		ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
		Claim c = tbuser.getSelectionModel().getSelectedItem();
		
		proxy.claimIsTreated(c.getClaimId());
Notifications notif = Notifications.create()
.title("Attention")
.text("Claim was Treated!")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.BOTTOM_RIGHT);
notif.darkStyle();
notif.show();


		LoadData();

    }
////////////////////////////////////Decline Claim///////////////////////////////////////////////////////
    @FXML
    void DeclineClaim(ActionEvent event) throws NamingException {
    	String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
		Context context;
		context = new InitialContext();
		ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
		Claim c = tbuser.getSelectionModel().getSelectedItem();
		
		proxy.blockUser(c.getClaimId());
		Notifications notif = Notifications.create()
				.title("Attention")
				.text("The claim has been declined")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
				notif.darkStyle();
				notif.show();


		LoadData();
    }
    ////////////////////////History///////////////////////////////////
    @FXML
    void show(ActionEvent event) {
LoadData1();
    }
////////////////////////////////////////Search Claim/////////////////////////////////////////////////////
    @FXML
    void searchClaim(ActionEvent event) throws NamingException {
    /*/	ObservableList tableuser = tbuser.getItems();
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
            	tbuser.setItems(tableuser);
            }
            String value = newValue.toLowerCase();
            ObservableList<User> subentries = FXCollections.observableArrayList();

            long count = tbuser.getColumns().stream().count();
            for (int i = 0; i < tbuser.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tbuser.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tbuser.getItems().get(i));
                        break;
                    }
                }
            }
            tbuser.setItems(subentries);
        });
        LoadData();*/

    }
    
 //////////////////////////Load Data//////////////////////////////////////////////////////////////////
     public void LoadData(){
			
			String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
			Context context;
			try {
				context = new InitialContext();
				ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
				List<Claim>list;
				ObservableList<Claim>list1=FXCollections.observableArrayList();
				list=proxy.getClaim();
				for(int i = 0; i < list.size(); i++){
					if(list.get(i).getTreated()==false){
					list1.add(new Claim(list.get(i).getClaimId(),list.get(i).getDate(),list.get(i).getText(),list.get(i).getStatus(),list.get(i).getTreated()));
					tbuser.setItems(list1);
					}
				}
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		
			date.setCellValueFactory(new PropertyValueFactory<>("date"));
			statut.setCellValueFactory(new PropertyValueFactory<>("status"));
			text.setCellValueFactory(new PropertyValueFactory<>("text"));

		}
    
 ////////////////////////////////////////////Load History //////////////////////////////////////////
     
     public void LoadData1(){
			
			String jndiName="PI-ear/PI-ejb/Claimservice!Services.ClaimserviceRemote";
			Context context;
			try {
				context = new InitialContext();
				ClaimserviceRemote proxy = (ClaimserviceRemote) context.lookup(jndiName);
				List<Claim>list;
				ObservableList<Claim>list1=FXCollections.observableArrayList();
				list=proxy.historyClaim();
				for(int i = 0; i < list.size(); i++){
					if(list.get(i).getTreated()==true){
					list1.add(new Claim(list.get(i).getClaimId(),list.get(i).getDate(),list.get(i).getText(),list.get(i).getStatus(),list.get(i).getTreated()));
					tbuser.setItems(list1);
					}
				}
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		
			date.setCellValueFactory(new PropertyValueFactory<>("date"));
			statut.setCellValueFactory(new PropertyValueFactory<>("status"));
			text.setCellValueFactory(new PropertyValueFactory<>("text"));

		}
  //////////////////////////Show Claimers////////////////////////////////////////////////////
     @FXML
     void showClaimer(MouseEvent event) {
    	 //mail.setText(tbuser.getSelectionModel().getSelectedItem().);
    	text1.setText(tbuser.getSelectionModel().getSelectedItem().getText());
    	Statut1.setText(tbuser.getSelectionModel().getSelectedItem().getStatus());
     }
    
 ////////////////////////Initialize ////////////////////////////////////////////////////////////////////   
     @FXML
 	public void initialize() throws NamingException {
     	
     	LoadData();
     	
     }
 /////////////////////////////////////////////////////////////    
}
