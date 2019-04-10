package tn.esprit;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Entities.Commande;
import Entities.Fleet;
import Entities.Maintenance;
import Entities.Reservation;
import Entities.TypeFleet;
import Entities.TypeMaintenance;
import Entities.User;
import Interfaces.CommandeServiceRemote;
import Interfaces.FleetServiceRemote;
import Interfaces.MaintenanceServiceRemote;
import Interfaces.ReservationServiceRemote;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuelLogisticManagerController implements Initializable {
	@FXML
	private JFXTabPane TabPaneFleet;
	@FXML
	private Text totFleets;
	@FXML
    private JFXButton deleteFleetBtn;
	@FXML
	private Pane NewCommandsList;
	@FXML
	private Text TextDate;
	@FXML
	private Tab NewCommandsTab;
	@FXML
	private JFXComboBox<String> ComboUserInvisible;
    @FXML
    private JFXButton NCButtonTreatedCommand;
	// ****new commands
    @FXML
    private TableColumn<?, ?> numNewOrder;
	@FXML
	private TableView<Commande> NewCommandsTableView;
	@FXML
	private TableColumn<?, ?> NCDepartureAd;
	@FXML
	private TableColumn<?, ?> NCArrivalAd;
	@FXML
	private TableColumn<?, ?> NCTreated;
	@FXML
	private TableColumn<?, ?> NCReceived;
	@FXML
	private TableColumn<?, ?> NCRegistrationDate;
	@FXML
	private TableColumn<?, ?> NCDeadline;
	@FXML
    private Text textDetail1;
    @FXML
    private Text textDetail2;
    @FXML
    private Text textDetail3;
    @FXML
    private Text textDetail4;
	@FXML
	private JFXButton LeftButtonFleetReservation;
	@FXML
	private JFXButton LeftButtonFleetMaintenance;
    @FXML
    private JFXButton LeftButtonCheckCommands;
	@FXML
	private JFXButton LeftButtonStatics;
    @FXML
    private JFXButton SaveButtonCommande;
	@FXML
	private JFXButton leftbuttoncontact;
	// **** end  commands
    @FXML
    private Pane FleetMaintenancePane;
    @FXML
    private Pane StaticsPane;
    @FXML
    private Pane ContactsPane;
    @FXML
    private AnchorPane CheckCommandsPane;
	@FXML
	private Text NCText;
	@FXML
	private Text actualDate ;
	@FXML 
	private Text nbrCommande;
	@FXML
	private Tab ReceivedCom;
	// ****received commands
	@FXML
	 private Pane PaneDetails;
	@FXML
	 private JFXButton XButton;
    @FXML
    private TableView<Commande> receivedCommandTab;
	@FXML
	private TableColumn<?, ?> adresseDepRC;
	@FXML
	private TableColumn<?, ?> ArrivalAdresseRC;
	@FXML
	private TableColumn<?, ?> TreatedRC;
	@FXML
	private TableColumn<?, ?> ReceivedRC;
	@FXML
	private TableColumn<?, ?> RegisDateRC;
	@FXML
	private TableColumn<?, ?> DeadlineRC;
	@FXML
    private TableColumn<?, ?> numNewOrder1;
	// ****end received commands
	@FXML
    private JFXTextArea cmdNumDet;
    @FXML
    private JFXTextArea cmdTypeDet;
    @FXML
    private JFXTextArea QuantityDet;
    @FXML
    private JFXTextArea ProviderNameDet;
    @FXML
    private JFXTextArea ProviderPhoneDet;
    @FXML
    private JFXTextArea ManagerNameDet;
    @FXML
    private JFXTextArea ManagerPhoneDet;
    @FXML
    private JFXTextArea DriverNameDet;
    @FXML
    private JFXTextArea DriverPhoneDet;
    @FXML
    private JFXTextArea FleetRegistrationNDet;
    @FXML
    private JFXTextArea FleetTypeDet;
    /************ treated commands ***************/
    @FXML
    private JFXComboBox<String> ComboTypeFleet;

    @FXML
    private JFXComboBox<String> ComboBoxAvailableFleet;

    @FXML
    private JFXComboBox<String> Pickdriver;
    @FXML
    private TableView<Commande> TreatedComands;
    @FXML
    private TableColumn<?, ?> DepartureAddressT;
    @FXML
    private TableColumn<?, ?> ArrivalDateT;
    @FXML
    private TableColumn<?, ?> RegistrationDateT;
    @FXML
    private TableColumn<?, ?> DeadlineT;
    @FXML
    private TableColumn<?, ?> numNewOrder2;
    @FXML
    private Text CountTreatedCommand;
    /* end treated commands*/
	@FXML
	private Text nbrRecceivedCommands;
    @FXML
    private Text idDriverInvisible;
	@FXML
	private Pane FleetReservationPane;
	@FXML
	int nbr ;
	@FXML
    private Pane PaneGrid;
    @FXML
	private Text gridBrandF;
	@FXML
	private Text gridTypeF;
	@FXML
	private Text gridPurchaseD;
	@FXML
	private Text gridMaxW;
	@FXML
	private Text gridRegistN;
	@FXML
    private Text gridphoneD;
	@FXML
	private Text gridDriverName;
	@FXML
	private Text gridMainD;
	@FXML
    private JFXButton CancelButton;
	@FXML
    private JFXButton backButton;
	//**********fleets********************
	@FXML
	private Tab tabAjoutFleetPane;
	@FXML
	private JFXTextField  BrandAj;
	@FXML
	private JFXDatePicker datePickerAj;
	@FXML
	private JFXTextField  weightAjout;
	@FXML
	private JFXComboBox<String> typeFleetAjout;
	@FXML
	private JFXTextField  matriculeAj;
	@FXML
	private JFXButton ajoutFleetBtn;
	@FXML
	private Tab TabFleetList;
	@FXML
	private TableView<Fleet> FleetList;
	@FXML
	private TableColumn<?, ?> RegisterList;
	@FXML
	private TableColumn<?, ?> typeFleetList;
	@FXML
	private TableColumn<?, ?> brandList;
	@FXML
	private TableColumn<?, ?> weightList;
	@FXML
	private TableColumn<?, ?> dateAchatList;
	@FXML
	private TableColumn<?, ?> entretienDateList;
	@FXML
	private JFXButton enPanneButton;
	@FXML
	private JFXButton updateFleetButton;
	@FXML
	private Tab TabMainFleet;
	@FXML
	private TableView<Maintenance> MaintenanceTab;
	@FXML
	private TableColumn<?, ?> registerationMain;
	@FXML
	private TableColumn<?, ?> typeMain;
	@FXML
	private TableColumn<?, ?> BrandMain;
	@FXML
	private TableColumn<?, ?> weightMain;
	@FXML
	private TableColumn<?, ?> achatDateMain;// release date 
	@FXML
	private TableColumn<?, ?> maintenanceDate;
	@FXML
	private JFXTextField matriculeRecherche;
    @FXML
    private TableColumn<?, ?> typeRepairMain;
    @FXML
    private TableColumn<?, ?> repairPriceMain;
    @FXML
    private Text nbrmain;
    @FXML
    private PieChart fleetsPie;
    @FXML
    private PieChart CommandsPie;
    @FXML
    private PieChart DriversPie;

	String matUp;
	Boolean test=true;
	int indexId ;
	String matricule ;
	int idFleet;
	String AjoutFleet ;
	int idChauffeur ;
	float MaxW ;
	int x;
	int matUpdate ;
	   Fleet flotte=new Fleet();
	   Fleet fleetByMatricule=new Fleet();
	User driver=new User();
	Commande order =new Commande();
	String typeFleet="" ;
	ObservableList<String> fleets = FXCollections.observableArrayList();
	ObservableList<String> drivers = FXCollections.observableArrayList();
	ObservableList<String> idUsers = FXCollections.observableArrayList();


	public void setReceivedCom(Tab receivedCom) {
		receivedCommands();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		time ();
		
      try {
		NewCommands();
		setReceivedCom(NewCommandsTab);
	    	ComboBox() ;
	      Buttons();
	      fleetListe();
	      
	      System.out.println("bjeh rabi ekhdm normal ");
	      treatedCommands();
	      maintenance();
	      Statics();
	      System.out.println("ligne 198");
	      LoadData();
	      
	} catch (NamingException e) {
		e.printStackTrace();
	}
	}
	public void NewCommands() throws NamingException{
	            String jndi="PI-ear/PI-ejb/CommandeService!Interfaces.CommandeServiceRemote";
	         	Context context;	
			    context = new InitialContext();
			    CommandeServiceRemote proxy = (CommandeServiceRemote) context.lookup(jndi);
			    List<Commande> list = proxy.findAllCommandeTotreat();
			    if(list.isEmpty()){
			  	      PaneDetails.setVisible(false);
			    	receivedCommands(); 
			    }
			    else{
			    nbr= list.size();
		        nbrCommande.setText(Integer.toString(nbr));
				ObservableList<Commande> obsl = FXCollections.observableArrayList(list);
				NewCommandsTableView.setItems(obsl);
			    NCArrivalAd.setCellValueFactory(new PropertyValueFactory<>("adresse_arrive"));
			    NCDepartureAd.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
			    NCRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
			    NCDeadline.setCellValueFactory(new PropertyValueFactory<>("dateLimite_livraison"));
                numNewOrder.setCellValueFactory(new PropertyValueFactory<>("id"));
                NewCommandsTableView.getSelectionModel().setCellSelectionEnabled(true);
			    ObservableList<?> selectedCells = NewCommandsTableView.getSelectionModel().getSelectedCells();

			    selectedCells.addListener(new ListChangeListener<Object>() {
			        @Override
			        public void onChanged(Change<?> c) {
			        	Commande com= NewCommandsTableView.getSelectionModel().getSelectedItem();
			        	MaxW=com.getQte_D()+com.getQte_G()+order.getQte_M();
			           System.out.println("Selected Value  ( treated) :" + com.getId());
			          int  idenTreated=com.getId();
			           order=proxy.findCommandeById(idenTreated);
			          try {
						details(idenTreated);
					} catch (NamingException e) {
						System.out.println(" error appel details command treated ligne 252 ");
						e.printStackTrace();
					}
                        
			        } });}
			    
                
	 }
	public void receivedCommands()
	{
		  String jndi="PI-ear/PI-ejb/CommandeService!Interfaces.CommandeServiceRemote";
			Context context;
			try {
				  context = new InitialContext();
				  CommandeServiceRemote proxy = (CommandeServiceRemote) context.lookup(jndi);
				  List<Commande> list = proxy.findAllCommandeReceived();
				  nbr= list.size();
				  nbrRecceivedCommands.setText(Integer.toString(nbr));
				  ObservableList<Commande> obsl = FXCollections.observableArrayList(list);
				  receivedCommandTab.setItems(obsl);				  
			      ArrivalAdresseRC.setCellValueFactory(new PropertyValueFactory<>("adresse_arrive"));
				  adresseDepRC.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
				  RegisDateRC.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
				  DeadlineRC.setCellValueFactory(new PropertyValueFactory<>("dateLimite_livraison"));
				  receivedCommandTab.getSelectionModel().setCellSelectionEnabled(true);
				    ObservableList<?> selectedCells = receivedCommandTab.getSelectionModel().getSelectedCells();

				    selectedCells.addListener(new ListChangeListener<Object>() {
				        @Override
				        public void onChanged(Change<?> c) {
				        	    
				        	Commande com= receivedCommandTab.getSelectionModel().getSelectedItem();
				           System.out.println("Selected Value  ( treated) :" + com.getId());
				          int  idenTreated=com.getId();
				          try {
							details(idenTreated);
						} catch (NamingException e) {
							System.out.println(" error appel details command treated ligne 252 ");
							e.printStackTrace();
						}
	                        
				        } });
        
				    
				
			} catch (NamingException e) {
	System.out.println("echec");	
	e.printStackTrace();
			}
	}
	private void Buttons() 
{
		 SaveButtonCommande.setOnMousePressed(new EventHandler<MouseEvent>() {
 ///************************************ Alerte *******************************
	        @Override
	        public void handle(MouseEvent event) {
	        	if(ComboBoxAvailableFleet.getSelectionModel().getSelectedItem() == null || Pickdriver.getSelectionModel().getSelectedItem() == null || ComboTypeFleet.getSelectionModel().getSelectedItem() == null )  
	            {
	        		   Alert alert1 = new Alert(Alert.AlertType.ERROR);
		               alert1.setTitle("Please fill all the fields");
		               alert1.setHeaderText(null);          
	        		if(ComboBoxAvailableFleet.getSelectionModel().getSelectedItem() == null && ComboTypeFleet.getSelectionModel().getSelectedItem() != null && Pickdriver.getSelectionModel().getSelectedItem() != null)  
		            {
		               alert1.setContentText("Please choose one fleet !!");
		            }	 
	        		
	        		else	if(ComboTypeFleet.getSelectionModel().getSelectedItem() == null && Pickdriver.getSelectionModel().getSelectedItem() != null && ComboBoxAvailableFleet.getSelectionModel().getSelectedItem() != null )  
		            {
		               alert1.setContentText("Please choose the type of fleet !!");
		                }
	        		else    if(ComboBoxAvailableFleet.getSelectionModel().getSelectedItem() != null && ComboTypeFleet.getSelectionModel().getSelectedItem() != null && Pickdriver.getSelectionModel().getSelectedItem() == null)  
		            {
		               alert1.setContentText("Please pick the driver !!");
		            }
	        		else 
	        	  alert1.setContentText("Please fill all the fields !!");
	                alert1.showAndWait();
	            }
	        	
			    try {
			    	String jndi="PI-ear/PI-ejb/ReservationService!Interfaces.ReservationServiceRemote";
	         	     Context context;	
					context = new InitialContext();
				    ReservationServiceRemote proxy =  (ReservationServiceRemote) context.lookup(jndi);
				    Reservation reserver=new Reservation();
				    reserver.setChauffeur(driver);
				    reserver.setFleet(flotte);
				    reserver.setCommande(order);
				    reserver.setCanceled(false);
				    reserver.setRegistrationDate( new java.util.Date());
			 	  if(proxy.findDetailReservation(order.getId())==null)
				    proxy.addReservation(reserver);
			 	  else{
			 		  proxy.CancelReservation(order.getId());
			 		  proxy.addReservation(reserver);
			 		  proxy.updateCommande(order);
			 		  FleetReservationPane.setVisible(false);
			 		   CheckCommandsPane.setVisible(true);
			 		  NewCommands();
			 			setReceivedCom(NewCommandsTab);
			 		  
			 	  }
				} catch (NamingException e) {
					e.printStackTrace();
				}
			

	        }
	       
	    });
		 ///************************************ Fin Alerte *******************************

		  LeftButtonCheckCommands.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
			  	   PaneDetails.setVisible(false);
		      	  CheckCommandsPane.setVisible(true);
		  	      FleetReservationPane.setVisible(false);
		  	      FleetMaintenancePane.setVisible(false);
		  	      StaticsPane.setVisible(false);
		  	      PaneDetails.setVisible(false);
	            	FleetReservationPane.setVisible(false);
		        }
		    });
		  NCButtonTreatedCommand.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
		  	      PaneDetails.setVisible(false);
	        	PaneDetails.setVisible(false);
	        	PaneDetails.setVisible(false);
	        	FleetReservationPane.setVisible(false);
	        	CheckCommandsPane.setVisible(false);
	  	      FleetReservationPane.setVisible(true);
	  	      FleetMaintenancePane.setVisible(false);
	  	      StaticsPane.setVisible(false);
	  	      PaneDetails.setVisible(false);
	  	      ComboBox();
	  	
	        }
	    });
		  LeftButtonFleetReservation.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
			  	      PaneDetails.setVisible(false);
		      	  CheckCommandsPane.setVisible(false);
		  	      FleetReservationPane.setVisible(true);
		  	      FleetMaintenancePane.setVisible(false);
		  	      PaneDetails.setVisible(false);
		  	      StaticsPane.setVisible(false);
		        }
		    });
		  LeftButtonFleetMaintenance.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
			  	      PaneDetails.setVisible(false);
		      	  CheckCommandsPane.setVisible(false);
		  	      FleetReservationPane.setVisible(false);
		  	      FleetMaintenancePane.setVisible(true);
		  	      StaticsPane.setVisible(false);
		  	    PaneDetails.setVisible(false);
	        	FleetReservationPane.setVisible(false);
		        }
		    });

		  LeftButtonStatics.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
			  	      PaneDetails.setVisible(false);
		      	  CheckCommandsPane.setVisible(false);
		  	      FleetReservationPane.setVisible(false);
		  	      FleetMaintenancePane.setVisible(false);
		  	    PaneDetails.setVisible(false);
	        	FleetReservationPane.setVisible(false);
		  	      StaticsPane.setVisible(true);
		        }
		    });
		 
		  XButton.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
		        	PaneDetails.setVisible(false);
		        }
		    });
		  backButton.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
		        	PaneDetails.setVisible(false);
		        	FleetReservationPane.setVisible(false);
		        	CheckCommandsPane.setVisible(true);
		        	
		        }
		    });
		  CancelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
		        	PaneDetails.setVisible(false);
		        	FleetReservationPane.setVisible(false);
		        	CheckCommandsPane.setVisible(true);
		        	Pickdriver.getItems().clear();
		        	ComboBoxAvailableFleet.getItems().clear();
		        	ComboTypeFleet.getItems().clear();
		        }
		    });
		  ajoutFleetBtn.setOnMousePressed(new  EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
						  if(test!=true)
						   { if(datePickerAj.getValue()==null  )  
				            {
			        	       Alert alert1 = new Alert(Alert.AlertType.ERROR);
				               alert1.setTitle("Please fill all the fields");
				               alert1.setHeaderText(null);
		                               alert1.setContentText("You have to pick the purchase Date !!");
			                       alert1.showAndWait();}
							
					         	try {
					         		    System.out.println(idFleet);
									    String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
							         	Context context;
								    	context = new InitialContext();
								    	FleetServiceRemote proxy = (FleetServiceRemote) context.lookup(jndi);
										fleetByMatricule=proxy.findFleetById(idFleet);
										fleetByMatricule.setMarque(BrandAj.getText());
										fleetByMatricule.setPurchaseDate(Date.from(datePickerAj.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
										fleetByMatricule.setMaintenanceDate(Date.from(datePickerAj.getValue().plusDays(365).atStartOfDay(ZoneId.systemDefault()).toInstant()));
										fleetByMatricule.setMatricule(matriculeAj.getText());
										fleetByMatricule.setMaxWeight(Float.parseFloat(weightAjout.getText()));
										fleetByMatricule.setType(TypeFleet.valueOf(typeFleetAjout.getValue()));
										proxy.updateFleet(fleetByMatricule);
									    BrandAj.setText(null);
										matriculeAj.setText(null);
										weightAjout.setText(null);
										typeFleetAjout.setValue(null);
										test=true;
										 TabPaneFleet.getSelectionModel().select(1);
										 FleetList.getSelectionModel().setCellSelectionEnabled(false);
										 fleetListe();
								} catch (NamingException e) {
									e.printStackTrace();} }
						  if(test!=false)
						   { if(datePickerAj.getValue()==null)  
				            {
			        	       Alert alert1 = new Alert(Alert.AlertType.ERROR);
				               alert1.setTitle("Please fill all the fields");
				               alert1.setHeaderText(null);
		                               alert1.setContentText("Please fill all the fields !!");
			                       alert1.showAndWait();
			                       }
							
					         	try {
					         		    System.out.println(test);
					         		    System.out.println(idFleet);
									    String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
							         	Context context;
								    	context = new InitialContext();
										fleetByMatricule=new Fleet();
								    	FleetServiceRemote proxy = (FleetServiceRemote) context.lookup(jndi);
										fleetByMatricule.setMarque(BrandAj.getText());
										System.out.println(BrandAj.getText());
										fleetByMatricule.setPurchaseDate(Date.from(datePickerAj.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
										fleetByMatricule.setMaintenanceDate(Date.from(datePickerAj.getValue().plusDays(365).atStartOfDay(ZoneId.systemDefault()).toInstant()));
										fleetByMatricule.setMatricule(matriculeAj.getText());
										fleetByMatricule.setMaxWeight(Float.parseFloat(weightAjout.getText()));
										fleetByMatricule.setType(TypeFleet.valueOf(typeFleetAjout.getValue()));
										fleetByMatricule.setMalfunction(false);
										
										proxy.addFleet(fleetByMatricule);
										BrandAj.setText(null);
										matriculeAj.setText(null);
										weightAjout.setText(null);
										typeFleetAjout.setValue(null);
										System.out.println("update done !");
										System.out.println(fleetByMatricule.toString());
										test=true;
										 TabPaneFleet.getSelectionModel().select(1);
										 fleetListe();
								} catch (NamingException e) {
									e.printStackTrace();} }}});
		  
}
    public void time () 
{
    	 LocalDate localDate = LocalDate.now();
         Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
 	  actualDate.setText(date.toString().replace("00:00:00 WAT",""));
    }
    public void treatedCommands() throws NamingException{
    	 String jndi="PI-ear/PI-ejb/CommandeService!Interfaces.CommandeServiceRemote";
         System.out.println("jndi");
		Context context;
		
			    context = new InitialContext();
			    CommandeServiceRemote proxy = (CommandeServiceRemote) context.lookup(jndi);
			    List<Commande> list = proxy.findAllCommande();
			    nbr= list.size();
			    if(list.isEmpty())
			    CountTreatedCommand.setText(" Total Commmands : "+"0");
			    CountTreatedCommand.setText(" Total Commmands : "+Integer.toString(nbr));
				ObservableList<Commande> obsl = FXCollections.observableArrayList(list);
				TreatedComands.setItems(obsl);
				ArrivalDateT.setCellValueFactory(new PropertyValueFactory<>("adresse_arrive"));
			    DepartureAddressT.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
			    RegistrationDateT.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
			    DeadlineT.setCellValueFactory(new PropertyValueFactory<>("dateLimite_livraison"));
			    numNewOrder2.setCellValueFactory(new PropertyValueFactory<>("id"));
			    TreatedComands.getSelectionModel().setCellSelectionEnabled(true);
			    ObservableList<?> selectedCells = TreatedComands.getSelectionModel().getSelectedCells();

			    selectedCells.addListener(new ListChangeListener<Object>() {
			        @Override
			        public void onChanged(Change<?> c) {
			        	    
			        	Commande com= TreatedComands.getSelectionModel().getSelectedItem();
			           System.out.println("Selected Value  ( treated) :" + com.getId());
			          int  idenTreated=com.getId();
			          try {
						details(idenTreated);
					} catch (NamingException e) {
						System.out.println(" error appel details command treated ligne 350 ");
						e.printStackTrace();
					}
                        
			        } });
			    }
    public void details(int x ) throws NamingException
    {
    	
    	  String jndi="PI-ear/PI-ejb/ReservationService!Interfaces.ReservationServiceRemote";
          System.out.println("jndi");
 		Context context;
 		
 			    context = new InitialContext();
 			    ReservationServiceRemote proxy =  (ReservationServiceRemote) context.lookup(jndi);
 			    Reservation r =new Reservation() ;
 			    r=(Reservation) proxy.findDetailReservation(x);
 			    System.out.println(r.toString());
	        	PaneDetails.setVisible(true);
	        	DriverNameDet.setVisible(true);
	            DriverPhoneDet.setVisible(true);
	            FleetTypeDet.setVisible(true);
	            FleetRegistrationNDet.setVisible(true);
	            textDetail1.setVisible(true);
	            textDetail2.setVisible(true);
	            textDetail3.setVisible(true);
	            textDetail4.setVisible(true);
 			    cmdNumDet.setText(Integer.toString(r.getCommande().getId()));
                ProviderNameDet.setText(r.getCommande().getFournisseur().getName()+"  "+r.getCommande().getFournisseur().getLast_name());
                ProviderPhoneDet.setText(r.getCommande().getFournisseur().getPhone());
                ManagerNameDet.setText(r.getCommande().getGerant().getName()+"  "+r.getCommande().getGerant().getLast_name());
                ManagerPhoneDet.setText(r.getCommande().getGerant().getPhone());
                DriverNameDet.setText(r.getChauffeur().getName()+"  "+r.getChauffeur().getLast_name());
                DriverPhoneDet.setText(r.getChauffeur().getPhone());
                FleetTypeDet.setText(r.getFleet().getType().name());
                FleetRegistrationNDet.setText(Integer.toString(r.getFleet().getId()));
                if(r.getCommande().getValidate()==0 && r.getCommande().getReceived()==0 )
                {
                DriverNameDet.setVisible(false);
                DriverPhoneDet.setVisible(false);
                FleetTypeDet.setVisible(false);
                FleetRegistrationNDet.setVisible(false);
                textDetail1.setVisible(false);
                textDetail2.setVisible(false);
                textDetail3.setVisible(false);
                textDetail4.setVisible(false);
                	
                }
                if(r.getCommande().getQte_D()==0)
                {
                	if(r.getCommande().getQte_M()!=0)
                	{
                		if(r.getCommande().getQte_G()!=0)
                    	{
                    		cmdTypeDet.setText("Mazout and Gasoil");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                    	}
                		else 
                		{
                			cmdTypeDet.setText("Mazout");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                		}
                	}
                	else
                	{
                		if(r.getCommande().getQte_G()!=0)
                    	{
                    		cmdTypeDet.setText(" Gasoil");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                    	}
                		else 
                		{
                			cmdTypeDet.setText("there is a mistake");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                		}
                		
                	}
                }
                else 
                {
                	if(r.getCommande().getQte_M()!=0)
                	{
                		if(r.getCommande().getQte_G()!=0)
                    	{
                    		cmdTypeDet.setText("Diesel,Mazout and Gasoil");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                    	}
                		else 
                		{
                			cmdTypeDet.setText("Diesel and Mazout");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                		}
                	}
                	else
                	{
                		if(r.getCommande().getQte_G()!=0)
                    	{
                    		cmdTypeDet.setText("Diesel and Gasoil");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                    	}
                		else 
                		{
                			cmdTypeDet.setText("Diesel");
                    		QuantityDet.setText(Float.toString(r.getCommande().getQte_D()+r.getCommande().getQte_G()+r.getCommande().getQte_M())+"  barrels");
                		}
                		
                	}
                }
                
                
    }
    public void ComboBox() 
   {
	   ObservableList<String> typelist = FXCollections.observableArrayList("tanker", "truck_tancker");
	      ComboTypeFleet.setItems(typelist);
	 
	      ComboTypeFleet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ComboBoxAvailableFleet.getItems().clear();
				Pickdriver.getItems().clear();
				typeFleet=newValue;
				oldValue=newValue;
				System.out.println(typeFleet);
				String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
				Context context;
					  try {
						context = new InitialContext();
						FleetServiceRemote proxy =  (FleetServiceRemote) context.lookup(jndi);
						List <Fleet> names = proxy.ComboFleet(MaxW, ComboTypeFleet.getSelectionModel().selectedItemProperty().get());
						  for (Fleet ids:names)
						  {
							  fleets.add(ids.getMatricule());
						  }
						  List <User> fullNames=proxy.ComboUser();
						  for(User nom:fullNames)
						  {
							  drivers.add(nom.getName()+" "+nom.getLast_name());
							  idUsers.add(String.valueOf(nom.getId()));
							
						  }		  
				       ComboBoxAvailableFleet.setItems(fleets);
				       System.out.println("ligne 214");
				       Pickdriver.setItems(drivers);	
				       System.out.println("ligne 216");
						ComboUserInvisible.setItems(idUsers);
				    	} catch (NamingException e) {
						System.out.println("ligne 235");	
	                    e.printStackTrace();
					}
					 
			}
	  });    
	        System.out.println(typeFleet); 
	       
	        Pickdriver.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					 if(ComboBoxAvailableFleet.getItems().isEmpty())  
			            {
			                Alert alert1 = new Alert(Alert.AlertType.ERROR);
			               alert1.setTitle("Please fill all the fields");
			               alert1.setHeaderText(null);
			               alert1.setContentText("Please choose type of fleet first !!");
			               alert1.showAndWait();
			                }
					 else{
						 matricule=ComboBoxAvailableFleet.getSelectionModel().getSelectedItem();
							try {
								
								 String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
								 Context context;
								 context = new InitialContext();
								  FleetServiceRemote proxy =  (FleetServiceRemote) context.lookup(jndi);
								   flotte = proxy.FindFleetByMat(matricule);
								  System.out.println(flotte.toString());
								 
							} catch (NamingException e) {
								System.out.println("probleme affichage fleet by matricule");
								e.printStackTrace();
							}
					 }
				    System.out.println(matricule);
					indexId=Pickdriver.getSelectionModel().getSelectedIndex();
					System.out.println(Pickdriver.getSelectionModel().getSelectedIndex());
					idChauffeur=Integer.valueOf(idUsers.get(indexId));
					System.out.println(idUsers.get(indexId));
					try {
						
						 String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
						 Context context;
						 context = new InitialContext();
						  FleetServiceRemote proxy =  (FleetServiceRemote) context.lookup(jndi);
						   driver = proxy.findUserByid(idChauffeur);
						  System.out.println(driver.toString());
						 
					} catch (NamingException e) {
						System.out.println("probleme affichage fleet by matricule");
						e.printStackTrace();
					}
					// affichage info // 
                    PaneGrid.setVisible(true);
					gridTypeF.setVisible(true);
					gridTypeF.setText(typeFleet);
				    gridRegistN.setVisible(true);
					gridRegistN.setText(matricule);
					gridBrandF.setVisible(true);
					gridBrandF.setText(flotte.getMarque());
					gridDriverName.setVisible(true);
                    gridDriverName.setText(driver.getName()+" "+driver.getLast_name());
                    gridphoneD.setVisible(true);
                    gridphoneD.setText(driver.getPhone());
                    gridMainD.setVisible(true);
                    gridMainD.setText(flotte.getMaintenanceDate().toString());
                    gridMaxW.setVisible(true);
                    gridMaxW.setText(String.valueOf(flotte.getMaxWeight()));
                    gridPurchaseD.setVisible(true);
                    gridPurchaseD.setText(flotte.getPurchaseDate().toString());
				}
});
			   typeFleetAjout.setItems(typelist);
			   typeFleetAjout.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						AjoutFleet=newValue;
				      }});    
			
   }
    public void fleetListe() throws NamingException {
   	 String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
		 Context context;
		 context = new InitialContext();
		 FleetServiceRemote proxy= (FleetServiceRemote) context.lookup(jndi);
		 List<Fleet> listFleets = proxy.findAllFleetFCT();
		 if(listFleets.isEmpty()==false )
		 {
			 totFleets.setText("Total Fleets : " + String.valueOf(listFleets.size()));
			 ObservableList<Fleet> obsl = FXCollections.observableArrayList(listFleets);
			 FleetList.setItems(obsl);
			 typeFleetList.setCellValueFactory(new PropertyValueFactory<>("type"));
			 RegisterList.setCellValueFactory(new PropertyValueFactory<>("matricule"));
			 brandList.setCellValueFactory(new PropertyValueFactory<>("marque"));
			 weightList.setCellValueFactory(new PropertyValueFactory<>("maxWeight"));
			 dateAchatList.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
			 entretienDateList.setCellValueFactory(new PropertyValueFactory<>("maintenanceDate"));
			
			 ObservableList<?> selectedCells = FleetList.getSelectionModel().getSelectedCells();
			 selectedCells.addListener(new ListChangeListener<Object>() {
			        @Override
			        public void onChanged(Change<?> c) {
			        	    
			        	Fleet fleetByMatricule = FleetList.getSelectionModel().getSelectedItem();
			        	if(fleetByMatricule!=null)
			            {idFleet=fleetByMatricule.getId(); }
			            System.out.println("toooooo button update");
			             updateFleetButton.setOnMousePressed(new  EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								test=false;
						        TabPaneFleet.getSelectionModel().select(0);
							    BrandAj.setText(fleetByMatricule.getMarque());
								matriculeAj.setText(fleetByMatricule.getMatricule());
								weightAjout.setText(String.valueOf(fleetByMatricule.getMaxWeight()));
								typeFleetAjout.setValue(fleetByMatricule.getType().name());
							}});
			           
			        } });
			  deleteFleetBtn.setOnMousePressed(new  EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						try {
							String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
				         	Context context;
							context = new InitialContext();
							FleetServiceRemote proxy = (FleetServiceRemote) context.lookup(jndi);
							Fleet flotte=new Fleet();
							flotte=proxy.findFleetById(idFleet);
							
							proxy.removeFleet(flotte.getId());
							TabPaneFleet.getSelectionModel().select(2);

						} catch (NamingException e) {
							System.out.println("error ligne 601");
							e.printStackTrace();
						}
						
					}});
			  enPanneButton.setOnMousePressed(new  EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						
						try {
							String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
				         	Context context;
							context = new InitialContext();
							FleetServiceRemote proxy = (FleetServiceRemote) context.lookup(jndi);
							Fleet flotte=new Fleet();
							Maintenance maintenance=new Maintenance();
							
							flotte=proxy.findFleetById(idFleet);
							flotte.setMalfunction(true);
							maintenance.setEntryDate( new java.util.Date());
							maintenance.setFleet(flotte);
							maintenance.setPrice(1000);
							maintenance.setReleaseDate(Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
							maintenance.setType(TypeMaintenance.Maintenance);
							proxy.addMaintenance(maintenance);
							proxy.updateFleet(flotte);	
							maintenance();
							TabPaneFleet.getSelectionModel().select(2);
							

						} catch (NamingException e) {
							System.out.println("error ligne 601");
							e.printStackTrace();
						}
						
					}});
		
		 }
		 else  totFleets.setText("Total Fleets : 0" );
		 
   }    			
    public void maintenance() throws NamingException{
    	 String jndi="PI-ear/PI-ejb/MaintenanceService!Interfaces.MaintenanceServiceRemote";
	         	Context context;	
			    context = new InitialContext();
			    MaintenanceServiceRemote proxy =  (MaintenanceServiceRemote) context.lookup(jndi);
			    
			    List<Maintenance> list = proxy.findAllMaintenance();
			    ObservableList<Maintenance> obsl = FXCollections.observableArrayList(list);
			    MaintenanceTab.setItems(obsl);
			   
			    if(list.isEmpty()!=true){
			    	
			    	registerationMain.setCellValueFactory(new PropertyValueFactory<>("fleet") );
				    typeMain.setCellValueFactory(new PropertyValueFactory<>("type"));
				    weightMain.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
				    achatDateMain.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));// release date
				    repairPriceMain.setCellValueFactory(new PropertyValueFactory<>("price"));
				    typeRepairMain.setText("Maintenance");

			    }
			   
			    
			   matriculeRecherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		            if (oldValue != null && (newValue.length() < oldValue.length())) {
		            	MaintenanceTab.setItems(obsl);
		            }
		            String value =newValue.toLowerCase();
		      
		            ObservableList<Maintenance> subentries = FXCollections.observableArrayList();

		            long count = MaintenanceTab.getColumns().stream().count();
		            for (int i = 0; i < MaintenanceTab.getItems().size(); i++) {
		                for (int j = 0; j < count; j++) {
		                    String entry = "" + MaintenanceTab.getColumns().get(j).getCellData(i);
		                    if (entry.toLowerCase().contains(value)) {
		                        //subentries.add(MaintenanceTab.getItems().get(i));
		                    	subentries.add(MaintenanceTab.getItems().get(i));
		                        break;
		                    }
		                }
		            }
		            MaintenanceTab.setItems(subentries);
		        });
		        LoadData();
		      
		        nbrmain.setText(" Total Fleet Maintenance : "+x);
    }
    public void LoadData(){
		
    	
		try {
			 String jndi="PI-ear/PI-ejb/MaintenanceService!Interfaces.MaintenanceServiceRemote";
	         	Context context;	
			    context = new InitialContext();
			    MaintenanceServiceRemote proxy =  (MaintenanceServiceRemote) context.lookup(jndi);
			    List<Maintenance> list;
			    ObservableList<Maintenance>list1=FXCollections.observableArrayList();
			    list = proxy.findAllMaintenance();
			
			for(int i = 0; i < list.size(); i++){
				   x =list.size();
				list1.add(new Maintenance(list.get(i).getEntryDate(),list.get(i).getReleaseDate(),list.get(i).getFleet(),list.get(i).getPrice()));
				MaintenanceTab.setItems(list1);
				
			}
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	
		registerationMain.setCellValueFactory(new PropertyValueFactory<>("fleet") );
	    typeMain.setCellValueFactory(new PropertyValueFactory<>("type"));
	    weightMain.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
	    achatDateMain.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));// release date
	    typeRepairMain.setText("Maintenance");
	    repairPriceMain.setCellValueFactory(new PropertyValueFactory<>("price"));
       
	}
    public void Statics() throws NamingException {
    	String jndi="PI-ear/PI-ejb/FleetService!Interfaces.FleetServiceRemote";
    	String jndi2="PI-ear/PI-ejb/CommandeService!Interfaces.CommandeServiceRemote";
     	Context context;
		context = new InitialContext();
		FleetServiceRemote proxy = (FleetServiceRemote) context.lookup(jndi);
		CommandeServiceRemote proxy2= (CommandeServiceRemote) context.lookup(jndi2);
		List<Fleet> fleetsfct=proxy.findAllFleetFCT();
	    List<Fleet> fleetsNonFct=proxy.findAllFleetNonFCT();
	    List <User> driversNonAc=proxy.findAllNoneActifUser();
	    List<User> drivesAc=proxy.findAllActifUser();
	    List<Commande>treated=proxy2.findAllCommande();
	    List<Commande>Received=proxy2.findAllCommandeReceived();
	    List<Commande>ToTreat=proxy2.findAllCommandeTotreat();
	    ObservableList<PieChart.Data> pieChartData1 =
	    		FXCollections.observableArrayList(
	                    new PieChart.Data("Functional Fleets", fleetsfct.size()),
	                    new PieChart.Data("Broken Down Fleets",fleetsNonFct.size()));
	    fleetsPie.setData(pieChartData1);
	    ObservableList<PieChart.Data> pieChartData2 =
	    		FXCollections.observableArrayList(
	                    new PieChart.Data("Treated Commands", treated.size()),
	                    new PieChart.Data("New Commands", ToTreat.size()),
	                    new PieChart.Data("Received Commands",Received.size()));
	    CommandsPie.setData(pieChartData2);
	    ObservableList<PieChart.Data> pieChartData3 =
	    		FXCollections.observableArrayList(
	                    new PieChart.Data("Actif Drivers", drivesAc.size()),
	                    new PieChart.Data("Non Actif Drivers",driversNonAc.size()));
	    DriversPie.setData(pieChartData3);
	    
    }
}

  
      
    
    
	


