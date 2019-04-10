package controller;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Entities.User;
import Services.ServiceUserRemote;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class WorkersRHController {

    @FXML
    private JFXTextField search;

    @FXML
    private JFXButton delete;

    @FXML
    private TableView<User> tbuser;

    @FXML
    private TableColumn<User, String> FN;

    @FXML
    private TableColumn<User, String> LN;

    @FXML
    private TableColumn<User, String> U;

    @FXML
    private TableColumn<User, String> EM;

    @FXML
    private JFXButton edit;

    @FXML
    private ImageView imageview;

    @FXML
    private Label FirstName;

    @FXML
    private Label LastName;

    @FXML
    private Label Email;
   
////////////////////////////////////////Delete Workers////////////////////////////////////
    @FXML
    void DeleteWorker(ActionEvent event) {
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
/////////////////////////////////////Update Workers/////////////////////////////////////////
    @FXML
    void addP(ActionEvent event) {
    	
    	

    }
////////////////////////////////////Recherche///////////////////////////////////////////
    @FXML
    void searchW(ActionEvent event) throws NamingException {
    	ObservableList<User> tableuser = tbuser.getItems();
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

    
    
    
  //////////////////////////////////Load Data////////////////////////////////////////////////////////  
  public void LoadData() throws NamingException{
    	
    	
    	String jndiName="PI-ear/PI-ejb/ServiceUser!Services.ServiceUserRemote";
    	Context context;
    	context = new InitialContext();
    	ServiceUserRemote proxy = (ServiceUserRemote) context.lookup(jndiName);
    	List<User>list;
    	ObservableList<User>list1=FXCollections.observableArrayList();
    	list=proxy.getallWorkers();
    	for(int i = 0; i < list.size(); i++){
    		if(list.get(i).getAutorisation()==true){
    			list1.add(new User(list.get(i).getId(),list.get(i).getCin(),list.get(i).getFirstname(),list.get(i).getLastName()
    			,list.get(i).getPhone(),list.get(i).getMail(),list.get(i).getLogin(),list.get(i).getPasswd(),
    			list.get(i).getIsActif(),list.get(i).getAutorisation(),list.get(i).getCountry(),list.get(i).getState()
    			,list.get(i).getCode(),list.get(i).getAllowedtochangepassword(),list.get(i).getRole()));
    	   
    			tbuser.setItems(list1);
    			
    			
    		}
    		
    		FN.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
    		LN.setCellValueFactory(new PropertyValueFactory<>("LastName"));
    		U.setCellValueFactory(new PropertyValueFactory<>("login"));
    		EM.setCellValueFactory(new PropertyValueFactory<>("mail"));
    		
    		}
        }
 /////////////////////////////////Show Label/////////////////////////////////////////////////////
  @FXML
  void showWorkers(MouseEvent event) {
	  FirstName.setText(tbuser.getSelectionModel().getSelectedItem().getFirstname());
	  LastName.setText(tbuser.getSelectionModel().getSelectedItem().getLastName());
	  Email.setText(tbuser.getSelectionModel().getSelectedItem().getMail());

  }
    
//////////////////////////////////Initialize ////////////////////////////////////////////////    
    
  @FXML
 	public void initialize() throws NamingException {
  
     	LoadData();
  	
     	
     }
    
    
    
    
    
    
    
    
    
}
