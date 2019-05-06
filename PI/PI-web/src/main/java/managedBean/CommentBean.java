package managedBean;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.stream.events.Comment;

import Entities.Comments;
import Entities.News;
import Entities.User;
import Services.CommentsService;
import Services.NewsService;
import Services.ServiceUser;


@ManagedBean(name="commentBean") 
@SessionScoped

public class CommentBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private CommentsService commentService;
	@EJB
	private NewsService newsService;
	@EJB
	private ServiceUser userService;
	private int id;
	private String content;
	private User publisher;
	private News news;
	@ManagedProperty(value="#{newsBean}")
	NewsBean newsBean;
	public String addComment(int idNews,int idPublisher)
	{
		
		Comments c = new Comments();
		c.setContent(content);
		c.setDate(new Date());
		User u = userService.getUser(idPublisher);
		News n = newsService.getNews(idNews);
		c.setPublisher(u);
		c.setNews(n);
System.out.println("TEST"+c);

		commentService.addComment(c);
		n = newsService.getNews(n.getId());
		 
		return "/OilsTemplate/OneNews?faces-redirect=true";
		
	}
	


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
	
	
	
	
	
}
