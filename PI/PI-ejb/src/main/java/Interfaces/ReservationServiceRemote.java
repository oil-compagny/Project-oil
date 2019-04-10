package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Commande;
import Entities.Fleet;
import Entities.Reservation;
import Entities.User;


@Remote
public interface ReservationServiceRemote {

	public void addReservation(Reservation r) ; 
	public void removeReservation(int id) ;
	public void updateReservation(Reservation r) ;
	public Reservation findReservationById(int id) ; 
	public List<Reservation> findAllReservation();
	public Reservation findDetailReservation(int id);
	public void CancelReservation(int id);
	public List<User>  ComboUser();
	public void updateCommande(Commande c) ;


}
