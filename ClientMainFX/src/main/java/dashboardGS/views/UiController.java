package dashboardGS.views;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import Entities.Cistern;
import Entities.Gas_station;
import Entities.Type;
import Interfaces.IGas_StationServiceRemote;
import dashboard.main.Client;
import dashboard.tools.FillProgressIndicator;
import dashboard.tools.RingProgressIndicator;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class UiController implements Initializable {



    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private HBox parent;

    @FXML
    private Pane diesel;

    @FXML
    private Pane oils;

    @FXML
    private VBox sidebar;

    @FXML
    private Pane gas;

    @FXML
    private JFXButton Identity;
    @FXML
    private Label auth;

    @FXML
    private Pane content;
    
    public static String jndiNameGS="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
	public static Context contextGS;
	public static IGas_StationServiceRemote proxyGS ;
	public static Gas_station Current_GS;
	
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	try {
			contextGS = new InitialContext();
			 proxyGS = (IGas_StationServiceRemote)  contextGS.lookup(jndiNameGS);
			 Current_GS=proxyGS.ShowGasOfGerant(LoginController.getUser().getId());
			 auth.setText("Welcome : "+LoginController.getUser().getFirstname()+""+LoginController.getUser().getLastName());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	RingProgressIndicator rg  = new RingProgressIndicator();
		FillProgressIndicator dies = new FillProgressIndicator();
		FillProgressIndicator oil= new FillProgressIndicator();
		FillProgressIndicator gass = new FillProgressIndicator();
		dies.getStylesheets().add(RingProgressIndicator.class.getResource("/dashboard/css/Diesel.css").toExternalForm());
		oil.getStylesheets().add(RingProgressIndicator.class.getResource("/dashboard/css/Oil.css").toExternalForm());
		gass.getStylesheets().add(RingProgressIndicator.class.getResource("/dashboard/css/Gas.css").toExternalForm());

	
		
		List <Cistern> L = Current_GS.getCisterns();
    	for (Cistern c : L )
    	{ int a=(int) ((c.getCurrent())*100/c.getOtypeT().getQuantity());
    	System.out.println(a);
    		if(c.getOtypeT().getType()==Type.Mazout)
    		{oil.setProgress(a);}
    		else if(c.getOtypeT().getType()==Type.Gasoline)
    		{gass.setProgress(a);}
    		else
    		{
    			dies.setProgress(a);
    		}
    	}
		
		rg.makeIndeterminate();
		diesel.getChildren().add(dies);
		
		gas.getChildren().add(oil);
		oils.getChildren().add(gass);
        make_window_drageable();
    }

    private void make_window_drageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Client.stage.setX(event.getScreenX() - xOffset);
                Client.stage.setY(event.getScreenY() - yOffset);
                Client.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
        	Client.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
        	Client.stage.setOpacity(1.0f);
        });

    }

    @FXML
    void Affect_user(MouseEvent event) throws IOException {
		 Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/Affect.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }
    @FXML
    void ShowWorkers(MouseEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/Workers.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }
    @FXML
    void Show_Home(MouseEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/Home.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
    }

	public Pane getContent() {
		return content;
	}

	public void setContent(Pane content) {
		this.content = content;
	}
	@FXML
    void Show_Cistern(MouseEvent event) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/Cisterns.fxml"));
		
		 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);

    }
	  @FXML
	    void Show_Command(MouseEvent event) throws IOException {
		  Parent fxml = FXMLLoader.load(getClass().getResource("/dashboardGS/views/Commands.fxml"));
			
			 
	       content.getChildren().removeAll();
	        content.getChildren().setAll(fxml);
	    }
}
