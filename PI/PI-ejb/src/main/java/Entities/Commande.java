package Entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Commande
 *
 */
@Entity

public class Commande implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
private String adresse_depart;
private String adresse_arrive;
private int validate;
private int received ;
private Date date_commande;
private Date dateLimite_livraison;
private int qte_D;
private int qte_G;
private int qte_M;


@ManyToOne
@JoinColumn(name="fournisseur",referencedColumnName="id")
private User fournisseur;
@ManyToOne
@JoinColumn(name="gerant",referencedColumnName="id")
private User gerant;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAdresse_depart() {
	return adresse_depart;
}
public void setAdresse_depart(String adresse_depart) {
	this.adresse_depart = adresse_depart;
}
public String getAdresse_arrive() {
	return adresse_arrive;
}
public void setAdresse_arrive(String adresse_arrive) {
	this.adresse_arrive = adresse_arrive;
}
public int getValidate() {
	return validate;
}
public void setValidate(int validate) {
	this.validate = validate;
}
public int getReceived() {
	return received;
}
public void setReceived(int received) {
	this.received = received;
}
public Date getDate_commande() {
	return date_commande;
}
public void setDate_commande(Date date_commande) {
	this.date_commande = date_commande;
}
public Date getDateLimite_livraison() {
	return dateLimite_livraison;
}
public void setDateLimite_livraison(Date dateLimite_livraison) {
	this.dateLimite_livraison = dateLimite_livraison;
}
public int getQte_D() {
	return qte_D;
}
public void setQte_D(int qte_D) {
	this.qte_D = qte_D;
}
public int getQte_G() {
	return qte_G;
}
public void setQte_G(int qte_G) {
	this.qte_G = qte_G;
}
public int getQte_M() {
	return qte_M;
}
public void setQte_M(int qte_M) {
	this.qte_M = qte_M;
}
public User getFournisseur() {
	return fournisseur;
}
public void setFournisseur(User fournisseur) {
	this.fournisseur = fournisseur;
}
public User getGerant() {
	return gerant;
}
public void setGerant(User gerant) {
	this.gerant = gerant;
}
@Override
public String toString() {
	return "Commande [id=" + id + ", adresse_depart=" + adresse_depart + ", adresse_arrive=" + adresse_arrive
			+ ", validate=" + validate + ", received=" + received + ", date_commande=" + date_commande
			+ ", dateLimite_livraison=" + dateLimite_livraison + ", qte_D=" + qte_D + ", qte_G=" + qte_G + ", qte_M="
			+ qte_M + ", fournisseur=" + fournisseur + ", gerant=" + gerant + "]";
}


}
