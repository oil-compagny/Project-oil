package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Maintenance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	int id;
	private float price ;
	@Temporal(TemporalType.DATE)
	private Date entryDate ;
	@Temporal(TemporalType.DATE)
	private Date releaseDate ;
	@Enumerated(EnumType.STRING)
	private TypeMaintenance type ;
	@OneToOne
	@JoinColumn(name="fleet")
	private Fleet fleet;
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public TypeMaintenance getType() {
		return type;
	}
	public void setType(TypeMaintenance type) {
		this.type = type;
	}
	public Maintenance() {
		
	}
	@Override
	public String toString() {
		return "Maintenance [id=" + id + ", price=" + price + ", entryDate=" + entryDate + ", releaseDate="
				+ releaseDate + ", type=" + type + "]";
	}
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public Fleet getFleet() {
		return fleet;
	}
	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
	}
 	
	
	
}
