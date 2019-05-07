package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cistern
 *
 */
@Entity

public class Cistern implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Embedded
	private OilType OtypeT ; 
	private int Current =0;
	
	@OneToMany (fetch=FetchType.EAGER)
	private List <Pump> pumps  = new ArrayList() ;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




	public OilType getOtypeT() {
		return OtypeT;
	}

	public void setOtypeT(OilType otypeT) {
		OtypeT = otypeT;
	}

	public List<Pump> getPumps() {
		return pumps;
	}

	public void setPumps(List<Pump> pumps) {
		this.pumps = pumps;
	}

	public int getCurrent() {
		return Current;
	}

	public void setCurrent(int current) {
		Current = current;
	}

   
}
