package dashboardGS.views;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.User;

import Interfaces.IUserServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.layout.VBox;

public class ShowWorkersController implements Initializable {
	@FXML
	private Label name;
	@FXML
	private Label salary;
	@FXML
	private Label salary1;
	@FXML
	private ScrollPane workers;
	@FXML
	private VBox workeritem;
	
	static String jndiName="PI-ear/PI-ejb/UserServiceImpl!Interfaces.IUserServiceRemote";
	public static Context context;
	public static IUserServiceRemote proxy ;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		Node node;
		WorkerItemController itemCont;
		FXMLLoader loader;
		try {
			context = new InitialContext();
			 proxy = (IUserServiceRemote)  context.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List <User> L = UiController.Current_GS.getWorkers();
		if(L!=null){
		for(User u : L)
		{
			
			loader = new FXMLLoader(getClass().getResource("/dashboardGS/views/WorkersItem.fxml"));
            try {
				node=loader.load();
				   itemCont=loader.<WorkerItemController> getController();
		            itemCont.setData(u.getFirstname(),u.getLastName(),u.getSalary(),u.getId());
		            workeritem.getChildren().add(node);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
         
            
            
		}
		}
	}

}
