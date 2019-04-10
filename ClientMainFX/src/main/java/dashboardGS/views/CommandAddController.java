package dashboardGS.views;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;

import Entities.Commande;
import Interfaces.IGas_StationServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CommandAddController implements Initializable {

    @FXML
    private JFXTextField mazoutQty;

    @FXML
    private JFXTextField dieselQty;

    @FXML
    private JFXTextField gasolineQty;
    @FXML
    private VBox success;
    
    static String jndiName="PI-ear/PI-ejb/Gas_StationService!Interfaces.IGas_StationServiceRemote";
	static Context context;
	static IGas_StationServiceRemote proxy ;
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
		
	}

    @FXML
    void Order(MouseEvent event) throws IOException {
    	Commande c = new Commande();
    	int d=0;
    	int g=0;
    	int m=0;
    	
    	try {
    		d=Integer.valueOf(dieselQty.getText());
		} catch (NumberFormatException n) {
			// TODO: handle exception
			dieselQty.setText("Error value");
			return;
		}
try {
	g=Integer.valueOf(gasolineQty.getText());
		} catch (NumberFormatException n) {
			// TODO: handle exception
			gasolineQty.setText("Error value");
			return;
		}

try {
	m=Integer.valueOf(mazoutQty.getText());
} catch (NumberFormatException n) {
	mazoutQty.setText("Error value");
	return;
	// TODO: handle exception
}

    	c.setQte_D(d);
    	c.setQte_G(g);
    	c.setQte_M(m);
    	
    	Commande cmd = proxy.addCommand(c, 21,UiController.Current_GS.getId());
    	System.out.println(cmd);
    	success.getChildren().clear();
  	  Node node=FXMLLoader.load(getClass().getResource("/dashboardGS/views/Success.fxml"));
  	  success.getChildren().add(node);
  	  
  	  
  	           Document document= new Document() ;
		  try{
			  
			 
			   
			  PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\SaidaniA\\Desktop\\documentPDF\\commande.pdf"));
			  document.open();
			
			  document.add(new Paragraph("This carburant command is ordered by The Manager *** Saidani Anis *** "));
			  document.add(new Paragraph(" "));
			  document.add(new Paragraph(" "));
			  document.add(new Paragraph(" "));
			  document.add(new Paragraph("you'll find the details of this command right bellow this table :"));
			  document.add(new Paragraph(" "));
			  document.add(new Paragraph(" "));
			
			   //add Table
			  PdfPTable table  = new PdfPTable(4);
			  PdfPCell c1 = new PdfPCell(new Phrase("Provider Adress"));
			  table.addCell(c1);
			  c1 = new PdfPCell(new Phrase("Diesel Qty"));
			  table.addCell(c1);
			  c1 = new PdfPCell(new Phrase("Gasoline qty"));
			  table.addCell(c1);
			  c1 = new PdfPCell(new Phrase("Mazout qty"));
			  table.addCell(c1);
			  table.setHeaderRows(1);
			
			    

  	    table.addCell(cmd.getAdresse_depart());
		
	    table.addCell(mazoutQty.getText());
	    table.addCell(dieselQty.getText());
	    table.addCell(gasolineQty.getText());
			  
			 
			  
			  
			  document.add(table);
			  document.add(new Paragraph(" "));
			  document.add(new Paragraph(" "));
			  
			//add image
			 
			 
			  
			 Image img=Image.getInstance("C:\\Users\\SaidaniA\\Desktop\\logo.png");
			  document.add(img); 
			  document.close();
			  Desktop.getDesktop().open(new File("C:\\Users\\SaidaniA\\Desktop\\documentPDF\\commande.pdf"));
			  System.out.println("done");
		  } catch (Exception e)
		  {
			  System.out.println("e");
		  }
		  
	  }

    }


	

