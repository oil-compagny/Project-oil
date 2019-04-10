package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.awt.TextField;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Entities.Claim;
import Entities.User;
import Services.ClaimserviceRemote;
import Services.ServiceUserRemote;;

public class RHController {
	 @FXML
	    private Pane Homepane;

	    @FXML
	    private Pane noticepane;
	    @FXML
	    private JFXButton decline;

	    @FXML
	    private Pane Claimspane;

	    @FXML
	    private Pane Userspane;

	    @FXML
	    private TableView<User> tbuser;

	    @FXML
	    private TableColumn<User, String> TN;

	    @FXML
	    private TableColumn<User, String> TL;

	    @FXML
	    private TableColumn<User, String> TE;

	    @FXML
	    private TableColumn<User, String> TR;

	    @FXML
	    private TableColumn<User, String> TP;

	    @FXML
	    private TableColumn<User, String> TC;


	    @FXML
	    private JFXTextField search;

	    @FXML
	    private Pane workerspane;

	    @FXML
	    private Button btnHome;

	    @FXML
	    private Button btnClaims;

	    @FXML
	    private Button btnUsers;

	    @FXML
	    private Button btnNotice;

	    @FXML
	    private Button btnWorkers;

	    @FXML
	    private Button btnSignout;

	    @FXML
	    private JFXButton accept;
	    @FXML
	    private TableView<User> TW;

	    @FXML
	    private TableColumn<User, String> TWF;

	    @FXML
	    private TableColumn<User, String> TWL;

	    @FXML
	    private TableColumn<User, String> TWC;

	    @FXML
	    private TableColumn<User, String> TWE;
	    @FXML
	    private TableColumn<User, String> TWR;

	    @FXML
	    private TableColumn<User, String> TWP;

	    @FXML
	    private TableColumn<User, String> TWU;

	    @FXML
	    private JFXButton DeleteW;

	    @FXML
	    private JFXTextField searchW;
	    
	    @FXML
	    private TableView<Claim> TC1;

	    @FXML
	    private TableColumn<Claim, Date> TCD;

	    @FXML
	    private TableColumn<Claim,String> TCS;

	    @FXML
	    private TableColumn<Claim,String> TCT;

	    @FXML
	    private JFXTextField SC;
/////////////////////////////////////////////Traiter Claim//////////////////////////////////////////
	    
	    
	    
////////////////////////////////////////////AccepterClaim///////////////////////////////////////////
	    
	    
	    
/////////////////////////////////////////////////Search Claim/////////////////////////////////////	
	    @FXML
	    void SearchC(ActionEvent event) {
	    	ObservableList tableclaim = tbuser.getItems();
	        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (oldValue != null && (newValue.length() < oldValue.length())) {
	            	tbuser.setItems(tableclaim);
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
	        LoadData();

	    }
	    
////////////////////////////////////////////Load Data Claim////////////////////////////////////////	    
	    public void LoadData2(){
			
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
					TC1.setItems(list1);
					}
				}
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		
			TCD.setCellValueFactory(new PropertyValueFactory<>("date"));
			TCS.setCellValueFactory(new PropertyValueFactory<>("status"));
			TCT.setCellValueFactory(new PropertyValueFactory<>("text"));

		}
	    
	    
/////////////////////////////////////////////Delete Worker//////////////////////////////////////////	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
///////////////////////////////////////////////Recherche User/////////////////////////////////////////////	    
	    
	    @FXML
	    void searchUser(ActionEvent event) {
	    	ObservableList tableuser = tbuser.getItems();
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
	        LoadData();

	    }
//////////////////////////////////////////////////Recherche Workers//////////////////////////////	    
	    @FXML
	    void searchW(ActionEvent event) {
	    	ObservableList tableworker = TW.getItems();
	        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (oldValue != null && (newValue.length() < oldValue.length())) {
	            	TW.setItems(tableworker);
	            }
	            String value = newValue.toLowerCase();
	            ObservableList<User> subentries = FXCollections.observableArrayList();

	            long count = TW.getColumns().stream().count();
	            for (int i = 0; i < TW.getItems().size(); i++) {
	                for (int j = 0; j < count; j++) {
	                    String entry = "" + TW.getColumns().get(j).getCellData(i);
	                    if (entry.toLowerCase().contains(value)) {
	                        subentries.add(TW.getItems().get(i));
	                        break;
	                    }
	                }
	            }
	            TW.setItems(subentries);
	        });
	        LoadData1();

	    }
	    
//////////////////////////////////////////////////Sign Out////////////////////////////////////
	    @FXML
	    void Back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
		    Parent root = loader.load();
		    LoginController tsController = loader.getController();
		    btnSignout.getScene().setRoot(root);

	    }
/////////////////////////////////////////handle Clicks //////////////////////////////////////////
	    @FXML
	    void handleClicks(ActionEvent event) {
	    	if (event.getSource() == btnHome) {
	    		Homepane.setStyle("-fx-background-color : #333645");
	    		Homepane.toFront();
	    		
	    		
	        }
            if (event.getSource() == btnNotice) {
            	noticepane.setStyle("-fx-background-color : #333645");
            	noticepane.toFront();
	        }
            if (event.getSource() == btnClaims) {
            
            	Claimspane.toFront();
	        }
            if (event.getSource() == btnUsers) {
            	Userspane.setStyle("-fx-background-color : #333645");
            	Userspane.toFront();
	        }
            if (event.getSource() == btnWorkers) {
            	workerspane.setStyle("-fx-background-color : #333645");
            	workerspane.toFront();
	        }

	    }
	///////////////////////////////////////// Initialize ///////////////////////////////////////////    
	    @FXML
		public void initialize() throws NamingException {
	    	
	    	LoadData();
	    	LoadData1();
	    	LoadData2();
	    }
////////////////////////////////////load data User//////////////////////////////////////////////////////
		private void LoadData() {
			
			String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
			Context context;
			try {
				context = new InitialContext();
				ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
				List<User>list;
				ObservableList<User>list1=FXCollections.observableArrayList();
				list=proxy.getallUserNotTreated();
				for(int i = 0; i < list.size(); i++){
					if(list.get(i).getAutorisation()==false){
					/*list1.add(new User(list.get(i).getId(),list.get(i).getCin(),list.get(i).getFirstname(),list.get(i).getLastName(),list.get(i).getPhone(),list.get(i).getMail(),list.get(i).getLogin(),list.get(i).getPasswd(),
							list.get(i).getIsActif(),list.get(i).getAutorisation(),list.get(i).getRole()));
					tbuser.setItems(list1);*/
					}
					/*TN.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
					TL.setCellValueFactory(new PropertyValueFactory<>("LastName"));
					TR.setCellValueFactory(new PropertyValueFactory<>("role"));
					TP.setCellValueFactory(new PropertyValueFactory<>("Phone"));
					TC.setCellValueFactory(new PropertyValueFactory<>("cin"));
					TE.setCellValueFactory(new PropertyValueFactory<>("mail"));*/
					
				}
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}	
		}
///////////////////////////////////load Data Worker//////////////////////////////////////////////
          private void LoadData1() {
			
			String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
			Context context;
			try {
				context = new InitialContext();
				ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
				List<User>list;
				ObservableList<User>list1=FXCollections.observableArrayList();
				list=proxy.getallUser();
				for(int i = 0; i < list.size(); i++){
					if(list.get(i).getAutorisation()==true){
					/*list1.add(new User(list.get(i).getId(),list.get(i).getCin(),list.get(i).getFirstname(),list.get(i).getLastName(),list.get(i).getPhone(),list.get(i).getMail(),list.get(i).getLogin(),list.get(i).getPasswd(),
							list.get(i).getIsActif(),list.get(i).getAutorisation(),list.get(i).getRole()));
					TW.setItems(list1);*/
					}
					TWF.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
					TWL.setCellValueFactory(new PropertyValueFactory<>("LastName"));
					TWR.setCellValueFactory(new PropertyValueFactory<>("role"));
					TWP.setCellValueFactory(new PropertyValueFactory<>("Phone"));
					TWC.setCellValueFactory(new PropertyValueFactory<>("cin"));
					TWE.setCellValueFactory(new PropertyValueFactory<>("mail"));
					TWU.setCellValueFactory(new PropertyValueFactory<>("login"));
				}
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}	
		}
/////////////////////////////////////////////accept user///////////////////////////////////////////
		@FXML
	    void acceptUser(ActionEvent event) {
			String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
			Context context;
			try {
				context = new InitialContext();
				ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
				User u = tbuser.getSelectionModel().getSelectedItem();
				proxy.Autoriser(u.getId());
				LoadData();
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
			
			
			

	    }
///////////////////////////////////////////////decline user///////////////////////////////////////
	    @FXML
	    void declineUser(ActionEvent event) {
	    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
			Context context;
			try {
				context = new InitialContext();
				ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
				User u = tbuser.getSelectionModel().getSelectedItem();
				proxy.Decline(u.getId());
				LoadData();
			} catch (NamingException e) {
				
				e.printStackTrace();
			}

	    }
	/////////////////////////////////////////////Delete Workers//////////////////////////////////
	   
	    @FXML
	    void DeleteW(ActionEvent event) {
	    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
			Context context;
			try {
				context = new InitialContext();
				ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
				User u = tbuser.getSelectionModel().getSelectedItem();
				proxy.Decline(u.getId());
				LoadData();
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
	    	
	    	
	    	

	    } 
	    
}
