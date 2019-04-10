package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Cistern;
import Entities.Gas_station;
import Entities.OilType;
import Entities.Pump;

import Interfaces.ICisternServiceRemote;
@Stateless
public class Cistern_Service implements ICisternServiceRemote {

	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;
	
	
	@Override
	public int addCistern(Cistern C) {
		em.persist(C);
		System.out.println("add success" + C.getId());
		return C.getId();
	}


	@Override
	public int addPump(Pump P) {
		em.persist(P);
		System.out.println("add success" + P.getId());
		return P.getId();
	}


	@Override
	public void addPumpToCistern(int C) {
			Cistern c = em.find(Cistern.class,C );
			Pump pump = new Pump();
			OilType o = new OilType();
			o.setType(c.getOtypeT().getType());
			o.setQuantity(0);
			pump.setOtype(o);
			pump.setUnitPrice(10.0);
			em.persist(pump);
			c.getPumps().add(pump);
			
		
	}


	@Override
	public List<Cistern> ShowAllCistern(int idGS) {
		// TODO Auto-generated method stub
		Gas_station gs = em.find(Gas_station.class, idGS);
		List <Cistern> L = gs.getCisterns();
		return L;
	}


	@Override
	public Cistern findCistern(int id) {
		// TODO Auto-generated method stub
		Cistern c= em.find(Cistern.class,id);
		return c;
	}




}
