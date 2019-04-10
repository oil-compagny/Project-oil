package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Maintenance;
import Interfaces.MaintenanceServiceRemote;

@Stateless
public class MaintenanceService implements  MaintenanceServiceRemote{

	@PersistenceContext(unitName ="imputation-ejb")
	EntityManager em ;
	
	@Override
	public void addMaintenance(Maintenance m) {
		System.out.println("add maintainence  !");
		em.persist(m);
		System.out.println("add maintainence done!" );
				
	}

	@Override
	public void removeMaintenance(int id) {
		System.out.println("remove maintenance !");
	    em.remove(em.find(Maintenance.class, id));
	    System.out.println("remove maintenance done ! " );		
	}

	@Override
	public void updateMaintenance(Maintenance m) {
		System.out.println("update maintenance !");
        Maintenance main =em.find(Maintenance.class, m.getId()) ;	
        main.setEntryDate(m.getEntryDate());
        main.setFleet(m.getFleet());
        main.setPrice(m.getPrice());
        main.setReleaseDate(m.getReleaseDate());
        main.setType(m.getType());
        System.out.println("update maintenance  done !");
	}

	@Override
	public Maintenance findMaintenanceById(int id) {
		System.out.println("find maintaince by id ! ");
		Maintenance main =em.find(Maintenance.class,id);
        System.out.println("ind maintaince by id done ! " );				
        return main;
	}

	@Override
	public List<Maintenance> findAllMaintenance() {
		 System.out.println("find all maintaince");
         List <Maintenance> main =em.createQuery("from Maintenance", Maintenance.class).getResultList() ;
         System.out.println("find all maintenances done " );		
         return main;		
	}
	

}
