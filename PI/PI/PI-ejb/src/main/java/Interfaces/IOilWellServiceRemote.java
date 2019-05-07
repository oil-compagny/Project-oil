package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.OilWells;
import Entities.Zone;

@Remote
public interface IOilWellServiceRemote {
	
public int ajoutPuit(OilWells o);
public  List<OilWells> AfficherLesPuits(int quantite_demande_enBarils);
public List<OilWells> findAll();
public void UpdatePuits(int idP, int qte_extr);
}
