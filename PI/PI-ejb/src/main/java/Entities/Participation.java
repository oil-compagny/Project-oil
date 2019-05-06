package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Participation
 *
 */
@Entity

public class Participation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
	
private int pour;

private int contre;
@ManyToOne
@JoinColumn(referencedColumnName="id")
private User publisher;
@ManyToOne
@JoinColumn(referencedColumnName="id")
private News news;

	public Participation() {
		super();
	}
   
}
