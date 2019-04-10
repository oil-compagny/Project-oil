package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Fleet;
import Entities.Maintenance;
import Entities.Reservation;
import Entities.User;
import Interfaces.FleetServiceRemote;

@Stateless
public class FleetService implements  FleetServiceRemote{

	@PersistenceContext(unitName ="imputation-ejb")
	EntityManager em ;
	
	@Override
	public void addFleet(Fleet f) {
		System.out.println("add fleet  !");
		em.persist(f);
		System.out.println("add fleet done!" );		
	}

	@Override
	public void removeFleet(int id) {
		System.out.println("remove fleet !");
	    em.remove(em.find(Fleet.class, id));
	    System.out.println("remove fleet done ! " );		
	}

	@Override
	public void updateFleet(Fleet f) {
		System.out.println("update fleet !");
        Fleet fleet =em.find(Fleet.class, f.getId()) ;	
        fleet.setAvailable(f.getAvailable());
        fleet.setMaintenanceDate(f.getMaintenanceDate());
        fleet.setMalfunction(f.getMalfunction());
        fleet.setMaxWeight(f.getMaxWeight());
        fleet.setPurchaseDate(f.getPurchaseDate());
        fleet.setType(f.getType());
        fleet.setMarque(f.getMarque());
        fleet.setMatricule(f.getMatricule());
        System.out.println("update fleet  done !");		
	}

	@Override
	public Fleet findFleetById(int id) {
		System.out.println("find fleet by id ! ");
		Fleet fleet =em.find(Fleet.class,id);
        System.out.println("ind fleet by id done ! " );				
        return fleet;		
	}

	@Override
	public List<Fleet> findAllFleet() {
		 System.out.println("find all fleet");
         List <Fleet> fleet =em.createQuery("from Fleet", Fleet.class).getResultList() ;
         System.out.println("find all fleet done " );				
         return fleet;
	}

	@Override
	public Fleet findFleetDetailsById(int id) {
		return null ;
				}

	@Override
	public List<Fleet> ComboFleet(float maxW, String type) {
		List<Fleet> liste = em.createQuery("select u from Fleet u where u.malfunction= :val and u.type like CONCAT(:type,'%') and u.maxWeight> :maxWeight",Fleet.class).setParameter("val",false).setParameter("type",type).setParameter("maxWeight",maxW).getResultList();
		return  liste;		
	}

	@Override
	public List<User> ComboUser() {
		String role="Chauffeur";
		List<User> liste = em.createQuery("select u from User u where  u.role like CONCAT('%',:type ) and u.isActif= :val ",User.class).setParameter("type",role).setParameter("val",false).getResultList();
		return  liste;	
	}

	@Override
	public User findUserByid(int id) {
		User u =em.find(User.class,id);		
		return u;
	}

	@Override
	public Fleet FindFleetByMat(String mat) {
		Fleet f = em.createQuery("select u from Fleet u where  u.matricule like :type  ",Fleet.class).setParameter("type",mat).getSingleResult();
		return  f;
	}

	@Override
	public List<Fleet> findAllFleetFCT() {
		List<Fleet> liste = em.createQuery("select u from Fleet u where u.malfunction= :val",Fleet.class).setParameter("val",false).getResultList();
		return  liste;
	}

	@Override
	public List<Maintenance> findByFleet(Fleet f) {
		List<Maintenance> liste = em.createQuery("select u from maintenance u where u.fleet=:val",Maintenance.class).setParameter("val",f).getResultList();
		return  liste;
	}
	@Override
	public void removeReservation(int id) {
		System.out.println("remove maintenance !");
	    em.remove(em.find(Reservation.class, id));
	    System.out.println("remove maintenance done ! " );		
	}
	@Override
	public List<Reservation> findByFleet(Reservation f) {
		List<Reservation> liste = em.createQuery("select u from reservation u where u.fleet=:val",Reservation.class).setParameter("val",f).getResultList();
		return  liste;
	}
	@Override
	public void removeMaintenance(int id) {
		System.out.println("remove maintenance !");
	    em.remove(em.find(Maintenance.class, id));
	    System.out.println("remove maintenance done ! " );		
	}

	@Override
	public void addMaintenance(Maintenance m) {
		System.out.println("add maintainence  !");
		em.persist(m);
		System.out.println("add maintainence done!" );
				
	}

	@Override
	public List<Fleet> findAllFleetNonFCT() {
		List<Fleet> liste = em.createQuery("select u from Fleet u where u.malfunction= :val ",Fleet.class).setParameter("val",true).getResultList();
		return  liste;	
	}

	

	@Override
	public List<User> findAllNoneActifUser() {
		String role="Chauffeur";
		List<User> liste = em.createQuery("select u from User u where  u.role like CONCAT('%',:type ) and u.isActif= :val ",User.class).setParameter("type",role).setParameter("val",true).getResultList();
		return  liste;			
	}

	@Override
	public List<User> findAllActifUser() {
		String role="Chauffeur";
		List<User> liste = em.createQuery("select u from User u where  u.role like CONCAT('%',:type ) and u.isActif= :val ",User.class).setParameter("type",role).setParameter("val",false).getResultList();
		return  liste;	
	}

}
