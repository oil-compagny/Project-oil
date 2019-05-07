package Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.OilWells;
import Entities.Zone;
import Interfaces.IOilWellServiceRemote;


@Stateless
public class OilWellsService implements IOilWellServiceRemote {
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;

	@Override
	public int ajoutPuit(OilWells o) {
		
		em.persist(o);
		
		System.out.println("add success" + o.getId());
		return o.getId();
	
		}

	@Override
	public List<OilWells> AfficherLesPuits(int q) {

		System.out.println("In find Les Puits : ");
		List<OilWells> puits =em.createQuery("select p from OilWells p where (p.quantite_estime-p.quantite_extraite)>=:quantite_demande_enBarils and p.etat=:s",OilWells.class).
				setParameter("quantite_demande_enBarils",q).setParameter("s",1).getResultList();
		        
		System.out.println(puits);
	
	
		return puits;
	}

	@Override
	public List<OilWells> findAll() {
		// TODO Auto-generated method stub
	
		List<OilWells> puits = em.createQuery("from OilWells",OilWells.class).getResultList();
		
		return puits;
	}

	@Override
	public void UpdatePuits(int idP,int qte_extr) {
		
		OilWells o = em.find(OilWells.class,idP );
		o.setQuantite_extraite(o.getQuantite_extraite()+qte_extr);
		
	}
	
	
	
	
}
