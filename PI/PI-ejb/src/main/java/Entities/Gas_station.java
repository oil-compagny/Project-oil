package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Gas_station
 *
 */
@Entity

public class Gas_station implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String Name;
	private float Caisse;
	private String Address;

	   
	
/*** RELATIONS ****/
 
	@OneToMany (fetch=FetchType.EAGER)
	private List <Cistern> cisterns  ; 
	@OneToOne(fetch=FetchType.EAGER)
	private User gerant = new User() ;
	
	@OneToMany (mappedBy="station",fetch=FetchType.EAGER)
	private List <User> workers;

/*** Constructeurs ***/ 
	public Gas_station() {
		super();
	}
	
	public Gas_station(String name, String address) {
		super();
		Name = name;
		Address = address;
	}


/**** GETTERS AND SETTERS ****/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getCaisse() {
		return Caisse;
	}
	
	public void setCaisse(float caisse) {
		Caisse = caisse;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}
	
	public User getGerant() {
		return gerant;
	}
	
	public void setGerant(User gerant) {
		this.gerant = gerant;
	}
	
	
	@Override
	public String toString() {
		return "Gas_station [id=" + id + ", Name=" + Name + ", Caisse=" + Caisse 
				+ ", Address=" + Address + ", gerant="
				+ gerant + "]";
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<Cistern> getCisterns() {
		return cisterns;
	}

	public void setCisterns(List<Cistern> cisterns) {
		this.cisterns = cisterns;
	}

	public List<User> getWorkers() {
		return workers;
	}

	public void setWorkers(List<User> workers) {
		this.workers = workers;
	}


}
