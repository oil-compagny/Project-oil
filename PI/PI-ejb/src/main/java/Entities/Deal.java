package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Deal
 *
 */
@Entity

public class Deal implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private Float somme_propose;

@Temporal(TemporalType.DATE)
private Date date_propose;

private int score;

public Deal(Float somme_propose, Date date_propose) {
	super();
	this.somme_propose = somme_propose;
	this.date_propose = date_propose;
}



@ManyToOne
private User partner;

@ManyToOne
private Project project;

	public User getPartner() {
	return partner;
}

public void setPartner(User partner) {
	this.partner = partner;
}

public Project getProject() {
	return project;
}

public void setProject(Project project) {
	this.project = project;
}

	public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Float getSomme_propose() {
	return somme_propose;
}

public void setSomme_propose(Float somme_propose) {
	this.somme_propose = somme_propose;
}

public Date getDate_propose() {
	return date_propose;
}

public void setDate_propose(Date date_propose) {
	this.date_propose = date_propose;
}


	
	public Deal() {
		super();
	}
   
}
