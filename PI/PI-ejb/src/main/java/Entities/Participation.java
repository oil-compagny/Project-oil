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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPour() {
		return pour;
	}

	public void setPour(int pour) {
		this.pour = pour;
	}

	public int getContre() {
		return contre;
	}

	public void setContre(int contre) {
		this.contre = contre;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
   
}
