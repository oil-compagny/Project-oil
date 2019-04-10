package Services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Accounting;
import Entities.Cistern;
import Entities.Commande;

import Entities.Gas_station;
import Entities.Pump;

import Entities.Shift;
import Entities.Type;
import Entities.User;
import Interfaces.IGas_StationServiceRemote;

@Stateless
public class Gas_StationService implements IGas_StationServiceRemote {
	// imputation-ejb in persistence.xml
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;
	@Override
	public int addStation(Gas_station G) {
		em.persist(G);
		
		return G.getId();
		
	}
	public void AddGerant(int Gerant, int GS)
	{
		User d = em.find(User.class, Gerant);
		Gas_station GStation = em.find(Gas_station.class, GS);
	 GStation.setGerant(d);
	}
	@Override
	public Gas_station ShowGasOfGerant(int Gerant) {
		
		
		// TODO Auto-generated method stub
		User d = em.find(User.class, Gerant);
		TypedQuery <Gas_station> gst = em.createQuery("select g from Gas_station g where g.gerant=:id ",Gas_station.class);
		gst.setParameter("id", d);
		
		
		
		return gst.getSingleResult();
	}
	@Override
	public List<Pump> ShowPumps() {
		
		
		TypedQuery <Pump> gst = em.createQuery("select p from Pump p",Pump.class);
		
	
		return gst.getResultList();
	}

	@Override
	public void AffectWorkerPump(int idW, int idP,String shift) {
		// TODO Auto-generated method stub
		User worker = em.find(User.class, idW);
		
		Pump pump = em.find(Pump.class, idP);
		
		Accounting ac = new Accounting();
		ac.setPump(pump);
		ac.setWorker(worker);
		ac.setDateTrait(new Date());
		ac.setVcheckin(pump.getValeurs());
		ac.setVcheckout(pump.getValeurs());
		
		
		ac.setShift(Shift.valueOf(shift));
		
		em.persist(ac);
		
		
		
	}
	
	@Override
	public List<Pump>  AvailablePumpShift(String shift,List<Cistern> cisterns) {
		List<Accounting> AcL =em.createQuery("select ac from Accounting ac where ac.shift =:shift and ac.dateTrait =:date ",Accounting.class)
				.setParameter("shift", Shift.valueOf(shift)).setParameter("date", new Date())
				.getResultList();
		List <Pump> pumps= new ArrayList<>() ;
		for(Cistern c :cisterns)
		{
			pumps.addAll(c.getPumps());
		}
		
		
		
		if(AcL!=null){
			for(Accounting a : AcL)
			{
			
				pumps.remove(a.getPump());
				
			}
		
		}
		

		return pumps;
	}
	
	@Override
	public void checkcout(int idW, int idP,int value,double price) {
		User worker = em.find(User.class, idW);
		Pump pump = em.find(Pump.class, idP);
		pump.setUnitPrice(10.06);
		// TODO Auto-generated method stub
		Accounting AcL =em.createQuery("select ac from Accounting ac where worker=:worker and pump=:pump and dateTrait=:date",Accounting.class)
				.setParameter("worker",worker).setParameter("pump",pump).setParameter("date",new Date()).getSingleResult();
		pump.setValeurs(value);
		AcL.setVcheckout(value);
		double finalPrice=((AcL.getVcheckout()-AcL.getVcheckin())*pump.getUnitPrice());
		if(price!=finalPrice)
		{
			
			
		}
	}
	@Override
	public Commande addCommand(Commande c,int idF,int idGS) {
		//NADIA
		User fournisseur = em.find(User.class, idF);
		//ANIS
		Gas_station GS = em.find(Gas_station.class,idGS);
		c.setAdresse_depart(fournisseur.getCountry());
		c.setDate_commande(new Date());
		Date d= new Date();
		d.setHours(d.getHours()+240);
		c.setDateLimite_livraison(d);
		c.setAdresse_arrive(GS.getAddress());
		c.setDateLimite_livraison(d);
		c.setReceived(0);
		c.setValidate(0);
		c.setFournisseur(fournisseur);
		c.setGerant(GS.getGerant());
		
		em.persist(c);
		
		return c;
	}
	@Override
	public List<Commande> showCommands(String adr) {
		// TODO Auto-generated method stub
		
		List<Commande> c = em.createQuery("select c from Commande c where c.adresse_arrive=:adr").setParameter("adr",adr ).getResultList();
		
		return c;
	}
	@Override
	public Gas_station deleteCommands(int idC) {
		// TODO Auto-generated method stub
		Commande c =em.find(Commande.class, idC);
	
		Gas_station Gs = em.find(Gas_station.class,c.getGerant().getStation().getId());
		c.setReceived(1);
		for(Cistern cs :Gs.getCisterns())
		{
			if(cs.getOtypeT().getType()==Type.Diesel)
			{
				if(cs.getCurrent()+c.getQte_M()<=cs.getOtypeT().getQuantity())
				{cs.setCurrent(cs.getOtypeT().getQuantity()+c.getQte_D());}
				else cs.setCurrent(cs.getOtypeT().getQuantity());
				
			}else if(cs.getOtypeT().getType()==Type.Mazout)
			{
				if(cs.getCurrent()+c.getQte_M()<=cs.getOtypeT().getQuantity())
				{cs.setCurrent(cs.getCurrent()+c.getQte_M());}
			
				else cs.setCurrent(cs.getOtypeT().getQuantity());
			}else {
				if(cs.getCurrent()+c.getQte_M()<=cs.getOtypeT().getQuantity())
				{cs.setCurrent(cs.getOtypeT().getQuantity()+c.getQte_G());}
				else cs.setCurrent(cs.getOtypeT().getQuantity());
			}

		}
		em.flush();
		return Gs;
	}
	@Override
	public List<Accounting> CurrentPumps(int idU) {
		// TODO Auto-generated method stub
		User u= em.find(User.class, idU);
		List<Accounting> LA= em.createQuery("select a from Accounting a where a.dateTrait= :date and a.worker=:w").
				setParameter("date", new Date()).setParameter("w",u).getResultList();
		return LA;
	}


	

}
