package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: News
 *
 */
@Entity

public class News implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String title;
private String content;
private String img;
@Temporal(TemporalType.TIMESTAMP)
private Date datePub;

@OneToMany(mappedBy="news",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
private List<Participation> participations;
@OneToMany(mappedBy="news",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
private List<Comments> comments = new ArrayList();
public News() {
		super();
	}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public Date getDatePub() {
	return datePub;
}
public void setDatePub(Date datePub) {
	this.datePub = datePub;
}
public List<Participation> getParticipations() {
	return participations;
}
public void setParticipations(List<Participation> participations) {
	this.participations = participations;
}
public List<Comments> getComments() {
	return comments;
}
public void setComments(List<Comments> comments) {
	this.comments = comments;
}
@Override
public String toString() {
	return "News [id=" + id + ", title=" + title + ", content=" + content + ", img=" + img + "]";
}
   
}
