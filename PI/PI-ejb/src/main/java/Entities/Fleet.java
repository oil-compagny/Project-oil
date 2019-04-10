package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Fleet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	int id;
	@Temporal(TemporalType.DATE)
	private Date purchaseDate ;
	private Boolean malfunction ;
	private float maxWeight ;
	private String matricule ;
	private String marque ;
	private Boolean available ;
	@Temporal(TemporalType.DATE)
	private Date maintenanceDate ; 
	@Enumerated(EnumType.STRING)
	private TypeFleet type ;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="entretien",joinColumns={@JoinColumn(name="fleet")}, inverseJoinColumns={@JoinColumn(name="maintenance")})
	private List <Maintenance> Maintenances ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Boolean getMalfunction() {
		return malfunction;
	}
	public void setMalfunction(Boolean malfunction) {
		this.malfunction = malfunction;
	}
	public float getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(float maxWeight) {
		this.maxWeight = maxWeight;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Date getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public TypeFleet getType() {
		return type;
	}
	public void setType(TypeFleet type) {
		this.type = type;
	}
	
	public List<Maintenance> getMaintenances() {
		return Maintenances;
	}
	public void setMaintenances(List<Maintenance> maintenances) {
		Maintenances = maintenances;
	}
	@Override
	public String toString() {
		return "Fleet [id=" + id + ", purchaseDate=" + purchaseDate + ", malfunction=" + malfunction + ", maxWeight="
				+ maxWeight + ", available=" + available + ", maintenanceDate=" + maintenanceDate + ", type=" + type
			+"]";
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	
}
