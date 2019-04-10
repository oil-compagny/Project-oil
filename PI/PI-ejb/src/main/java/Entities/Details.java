package Entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Details
 *
 */
@Entity

public class Details implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

@Enumerated(EnumType.STRING)
private Type type;

private float quantity;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Type getType() {
	return type;
}

public void setType(Type type) {
	this.type = type;
}

public float getQuantity() {
	return quantity;
}

public void setQuantity(float quantity) {
	this.quantity = quantity;
}

}
