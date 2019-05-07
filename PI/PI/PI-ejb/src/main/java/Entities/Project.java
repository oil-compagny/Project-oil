package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private String details;
@Temporal(TemporalType.DATE)
private Date date;
private Float somme;
private String localisation;
private int nbreDeals;



public List<Deal> getDeals() {
	return deals;
}

public void setDeals(List<Deal> deals) {
	this.deals = deals;
}

private String projectname;
@OneToMany(mappedBy="project")
private List<Deal> deals;


	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getProjectname() {
	return projectname;
}

public void setProjectname(String projectname) {
	this.projectname = projectname;
}

public String getDetails() {
	return details;
}

public void setDetails(String details) {
	this.details = details;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Float getSomme() {
	return somme;
}

public void setSomme(Float somme) {
	this.somme = somme;
}

public String getLocalisation() {
	return localisation;
}

public void setLocalisation(String localisation) {
	this.localisation = localisation;
}
public int getNbreDeals() {
	return nbreDeals;
}

public void setNbreDeals(int nbreDeals) {
	this.nbreDeals = nbreDeals;
}
	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}
   
}
