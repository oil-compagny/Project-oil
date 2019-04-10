package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OilWells
 *
 */
@Entity

public class OilWells implements Serializable {

@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quantite_estime;
		result = prime * result + quantite_extraite;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OilWells other = (OilWells) obj;
		if (quantite_estime != other.quantite_estime)
			return false;
		if (quantite_extraite != other.quantite_extraite)
			return false;
		return true;
	}

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private int quantite_estime;
public OilWells()
{
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getQuantite_estime() {
	return quantite_estime;
}
public void setQuantite_estime(int quantite_estime) {
	this.quantite_estime = quantite_estime;
}
public int getQuantite_extraite() {
	return quantite_extraite;
}
public void setQuantite_extraite(int quantite_extraite) {
	this.quantite_extraite = quantite_extraite;
}
public int getEtat() {
	return etat;
}
public void setEtat(int etat) {
	this.etat = etat;
}


public Zone getZone() {
	return zone;
}
public void setZone(Zone zone) {
	this.zone = zone;
}

private int quantite_extraite;

private int etat;

@ManyToOne
private Zone zone;
@Override


public String toString() {
	return "OilWells [quantite_estime=" + quantite_estime + ", quantite_extraite=" 
			+ quantite_extraite + ", etat="
			+ etat+ "]";
}
}
