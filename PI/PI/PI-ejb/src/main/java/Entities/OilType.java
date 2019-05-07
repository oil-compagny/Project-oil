package Entities;


import javax.persistence.*;


/**
 * Entity implementation class for Entity: Details
 *
 */
@Embeddable

public class OilType implements java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


@Enumerated(EnumType.STRING)
private Type type;

private int quantity;


public Type getType() {
	return type;
}

public void setType(Type type) {
	this.type = type;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}



}
