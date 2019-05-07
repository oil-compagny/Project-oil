package Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pump
 *
 */
@Entity

public class Pump implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int valeurs;
	private double unitPrice;
	@Embedded
	private OilType Otype ; 	
	@OneToMany(mappedBy="pump",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List <Accounting> accounting ;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValeurs() {
		return valeurs;
	}
	public void setValeurs(int valeurs) {
		this.valeurs = valeurs;
	}
	public OilType getOtype() {
		return Otype;
	}
	public void setOtype(OilType otype) {
		Otype = otype;
	}
	public List<Accounting> getAccounting() {
		return accounting;
	}
	public void setAccounting(List<Accounting> accounting) {
		this.accounting = accounting;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return "Pump [id=" + id + ", valeurs=" + valeurs + ", unitPrice=" + unitPrice + ", Otype=" + Otype + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Pump other = (Pump) obj;
		if (id != other.id)
			return false;
		return true;
	}

	} 

	
	
	
	

