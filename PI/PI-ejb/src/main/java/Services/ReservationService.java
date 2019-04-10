package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Commande;
import Entities.Fleet;
import Entities.Reservation;
import Entities.User;
import Interfaces.ReservationServiceRemote;

@Stateless
public class ReservationService implements ReservationServiceRemote{
	@PersistenceContext(unitName ="imputation-ejb")
	EntityManager em ;

	@Override
	public void addReservation(Reservation r) {
		System.out.println("add Reservation  !");
		em.persist(r);
		System.out.println("add Reservation done!" );		
	}

	@Override
	public void removeReservation(int id) {
		System.out.println("remove Reservation !");
	    em.remove(em.find(Reservation.class, id));
	    System.out.println("remove Reservation done ! " );		
	}

	@Override
	public void updateReservation(Reservation r) {
		System.out.println("update Reservation !");
		Reservation reservation =em.find(Reservation.class, r.getId()) ;	
		reservation.setCanceled(r.isCanceled());
		reservation.setChauffeur(r.getChauffeur());
		reservation.setFleet(r.getFleet());
		reservation.setRegistrationDate(r.getRegistrationDate());
		System.out.println("update Reservation done!");

	}

	@Override
	public Reservation findReservationById(int id) {
		System.out.println("find Reservation by id ! ");
		Reservation reservation =em.find(Reservation.class,id);
        System.out.println("ind Reservation by id done ! " );				
        return reservation;
	}

	@Override
	public List<Reservation> findAllReservation() {
		 System.out.println("find all Reservation");
         List <Reservation> reservation =em.createQuery("from Reservation", Reservation.class).getResultList() ;
         System.out.println("find all Reservation done " );				
         return reservation;
	}

	@Override
	public Reservation findDetailReservation(int id) {
		Commande c=em.find(Commande.class, id);
		TypedQuery<Reservation> liste =   em.createQuery("select t from Reservation t WHERE t.commande = :cmd and t.canceled = 0",Reservation.class);
		liste.setParameter("cmd", c);
		Reservation listResultat = liste.getSingleResult();
		return  listResultat;
			}

	@Override
	public List<User>  ComboUser() {
		TypedQuery<User> liste =   em.createQuery("SELECT u FROM user u where u.isActif=0 and u.role like 'Chauffeur' ",User.class);
		List<User>  listResultat = liste.getResultList() ;
		return  listResultat;		
	}

	@Override
	public void CancelReservation(int id) {
		Commande c=em.find(Commande.class, id);
		TypedQuery<Reservation> liste =   em.createQuery("select t from Reservation t WHERE t.commande = :cmd and t.canceled = 0",Reservation.class);
		liste.setParameter("cmd", c);
		Reservation listResultat = liste.getSingleResult();
        listResultat.setCanceled(true);
		
		System.out.println("cqncel Reservation done!");
		
	}

	@Override
	public void updateCommande(Commande c) {
		Commande commande =em.find(Commande.class, c.getId()) ;	
		commande.setValidate(1);
		
		System.out.println("commande treated done!");		
	}

	

}
