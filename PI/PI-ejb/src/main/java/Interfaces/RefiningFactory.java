package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Refining
 *
 */
@Entity

public class RefiningFactory implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)	
private int id;
private int stock;	
@Override
public String toString() {
	return "RefiningFactory [stock=" + stock + "]";
}
@Enumerated(EnumType.STRING)
private Type type;
@OneToMany
private List<OilWells> puits_rafine=new ArrayList();


public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public Type getType() {
	return type;
}
public void setType(Type type) {
	this.type = type;
}

   
}
