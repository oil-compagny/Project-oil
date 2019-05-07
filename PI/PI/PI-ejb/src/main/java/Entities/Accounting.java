package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Accounting
 *
 */
@Entity

public class Accounting implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

private int vcheckin ; 

private int vcheckout ; 
private int done;
@Column(name="price")
private double price=0;




@ManyToOne(cascade=CascadeType.ALL)
@JoinColumn(referencedColumnName="id")
private User worker ; 

@ManyToOne
@JoinColumn(referencedColumnName="id")
private Pump pump ; 

@Temporal(TemporalType.DATE)
private Date dateTrait;
@Enumerated(EnumType.STRING)
private Shift shift;
public double getprice() {
	return price;
}

public void setTprice(double tprice) {
	price = tprice;
}



public Accounting() {
	super();
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getVcheckin() {
	return vcheckin;
}

public void setVcheckin(int vcheckin) {
	this.vcheckin = vcheckin;
}

public int getVcheckout() {
	return vcheckout;
}

public void setVcheckout(int vcheckout) {
	this.vcheckout = vcheckout;
}



public User getWorker() {
	return worker;
}

public void setWorker(User worker) {
	this.worker = worker;
}

public Pump getPump() {
	return pump;
}

public void setPump(Pump pump) {
	this.pump = pump;
}


@Override
public String toString() {
	return "Accounting [id=" + id + "]";
}

public Date getDateTrait() {
	return dateTrait;
}

public void setDateTrait(Date dateTrait) {
	this.dateTrait = dateTrait;
}

public Shift getShift() {
	return shift;
}

public void setShift(Shift shif) {
	this.shift = shif;
}

public int getDone() {
	return done;
}

public void setDone(int done) {
	this.done = done;
}

/********* GETTERS AND SETTERS ************/


}
