package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */
@Entity

public class Comments implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String content;

@ManyToOne
@JoinColumn(referencedColumnName="id")
private User publisher;
@ManyToOne
@JoinColumn(referencedColumnName="id")
private News news;


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
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

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Comments other = (Comments) obj;
	if (id != other.id)
		return false;
	return true;
}

@Temporal(TemporalType.TIMESTAMP)
private Date date;
	private static final long serialVersionUID = 1L;

	public Comments() {
		super();
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", content=" + content + ", publisher=" + publisher + ", news=" + news + ", date="
				+ date + "]";
	}
   
}
