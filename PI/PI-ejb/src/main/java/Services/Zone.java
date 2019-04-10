package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Zone
 *
 */
@Entity

public class Zone implements Serializable {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String location;
private int total;	
@Temporal(TemporalType.DATE)
private Date date_debut;
@Temporal(TemporalType.DATE)
private Date date_fin;
private int nbre_puits;
@OneToMany(mappedBy="zone", 
cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
fetch=FetchType.EAGER)
private List <OilWells> oilwells = new ArrayList();
@Override
public String toString() {
	//String R=oilwells.toString();
	
	return "Zone [location=" + location + ", total=" + total + ", date_debut=" 
			+ date_debut + ", date_fin=" + date_fin
			+ ", nbre_puits=" + nbre_puits ;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public Date getDate_debut() {
	return date_debut;
}
public void setDate_debut(Date date_debut) {
	this.date_debut = date_debut;
}
public Date getDate_fin() {
	return date_fin;
}
public void setDate_fin(Date date_fin) {
	this.date_fin = date_fin;
}
public int getNbre_puits() {
	return nbre_puits;
}
public void setNbre_puits(int nbre_puits) {
	this.nbre_puits = nbre_puits;
}
public List<OilWells> getOilwells() {
	return oilwells;
}
public void setOilwells(List<OilWells> oilwells) {
	this.oilwells = oilwells;
}


}


