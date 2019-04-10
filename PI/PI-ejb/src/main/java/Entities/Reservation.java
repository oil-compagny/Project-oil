package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	int id;
	@Temporal(TemporalType.DATE)
	private Date RegistrationDate ;
	private boolean canceled ;

	@OneToOne
	@JoinColumn(name="Fleet")
	private Fleet fleet;
	@OneToOne
	@JoinColumn(name="driver")
	private User chauffeur ;
	@OneToOne
	@JoinColumn(name="commande")
	private Commande commande;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getRegistrationDate() {
		return RegistrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	public Fleet getFleet() {
		return fleet;
	}
	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", RegistrationDate=" + RegistrationDate + ", canceled=" + canceled
				+ ", fleet=" + fleet + ", chauffeur=" + chauffeur + ", commande=" + commande + "]";
	}
	public User getChauffeur() {
		return chauffeur;
	}
	public void setChauffeur(User chauffeur) {
		this.chauffeur = chauffeur;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande c)
	{
		this.commande=c;
	}


}
