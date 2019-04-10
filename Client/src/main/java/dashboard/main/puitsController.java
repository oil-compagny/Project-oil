package dashboard.main;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class puitsController {
	   @FXML
	    private Label qte_estime;

	    @FXML
	    private Label qte_extraite;
    
	public void setData(int qte_es,int qte_ex)
	{
		String a =String.valueOf(qte_ex);
		String b =String.valueOf(qte_es);
		qte_extraite.setText(a);
		qte_estime.setText(b);
	}
}
