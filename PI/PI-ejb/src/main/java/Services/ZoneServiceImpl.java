package Services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.OilWells;
import Entities.Zone;
import Interfaces.IZoneServiceRemote;


@Stateless
public class ZoneServiceImpl implements IZoneServiceRemote{
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;


	@Override
	public int affecterPuitsAZone(int puitsId, int ZoneId) {
		
		OilWells o = em.find(OilWells.class,puitsId );
		
		Zone z = em.find(Zone.class,ZoneId);
		o.setZone(z);
	    z.setNbre_puits(z.getNbre_puits()+1);
	    z.setTotal(z.getTotal()+o.getQuantite_estime());
		return 0;
		
		
	}


	@Override
	public List<Zone> AfficherLesZones() {
		System.out.println("In findAllZones : ");
		List<Zone> zones = new ArrayList();
		zones=em.createQuery("from Zone",
		Zone.class).getResultList();
		System.out.println(zones);
		
		
		return zones;
	}


	@Override
	public Zone findZoneById(int id) {
		// TODO Auto-generated method stub
		Zone z= em.find(Zone.class, id);
		return z;
	}
	
	

}
