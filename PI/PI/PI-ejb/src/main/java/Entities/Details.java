package Entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Details
 *
 */
@Entity

public class Details implements Serializable {
@Id 
private int id;

@Enumerated(EnumType.STRING)
private Type type;

private int quantity;

}
