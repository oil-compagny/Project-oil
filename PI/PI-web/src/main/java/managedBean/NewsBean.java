package managedBean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import Entities.Comments;
import Entities.News;
import Entities.Participation;
import Entities.User;
import Services.CommentsService;
import Services.NewsService;
import Services.ServiceUser;

@ManagedBean(name = "newsBean" ,eager=true) 
@SessionScoped
public class NewsBean {
@EJB
private NewsService newsService;

private String pageId;;
private int id;
private String title;
private String content;
private int pour;
private int contre;
private String img;
private List<Comments> comments;
private Date datePub;
private Comments comment=new Comments();
private News SelectedNews;
private Participation participation;
private List<String> grosmots;

@EJB
private CommentsService commentService;


@EJB
private ServiceUser userService;
public NewsService getNewsService() {
	return newsService;
}






public void setNewsService(NewsService newsService) {
	this.newsService = newsService;
}






public News getSelectedNews() {
	return SelectedNews;
}






public void setSelectedNews(News selectedNews) {
	SelectedNews = selectedNews;
}






public String getTitle() {
	return title;
}






public String getPageId() {
	return pageId;
}






public void setPageId(String pageId) {
	this.pageId = pageId;
}

public int getId() {
	return id;
}




public void setId(int id) {
	this.id = id;
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




public List<News> getAllNews()
{
	
	return newsService.getAllNews();

}

 public List<Comments> getComments() {
	return comments;
}




public void setComments(List<Comments> comments) {
	this.comments = comments;
}




public Comments getComment() {
	return comment;
}






public void setComment(Comments comment) {
	this.comment = comment;
}






public Participation getParticipation() {
	return participation;
}






public void setParticipation(Participation participation) {
	this.participation = participation;
}






public String readMore(News news,int idU) 
 {String navigateTo = "/OilsTemplate/OneNews?faces-redirect=true";

id=news.getId();
img=news.getImg();
comments= news.getComments();
title=news.getTitle();
content=news.getContent();
datePub=news.getDatePub();
participation = newsService.particiaption(id,idU);
	return navigateTo;
	 
 }
public String addComment(int idNews,int idPublisher)
{
	grosmots=new ArrayList<>();
	grosmots.add("gm1");
	grosmots.add("gm2");
	Comments c = new Comments();
	System.out.println(grosmots);
	for(String s :grosmots)
	{ System.out.println("TEST "+s);
	System.out.println(content.indexOf(s));
		if(content.indexOf(s)!=-1)
		{
			FacesContext.getCurrentInstance().addMessage("form:txt", new FacesMessage("Bad Credentials"));
			return null;
		}
	}
	c.setContent(comment.getContent());
	c.setDate(new Date());
	User u = userService.getUser(idPublisher);
	News n = newsService.getNews(idNews);
	c.setPublisher(u);
	c.setNews(n);
System.out.println("TEST"+c);

	commentService.addComment(c);
	n = newsService.getNews(n.getId());
	 comments=n.getComments();
	 comment.setContent("");
	return "/OilsTemplate/OneNews?faces-redirect=true";
	
}
public String deleteComment(int idComment,int idNews)
{
	
	Comments c = commentService.findComments(idComment);
	commentService.deleteComment(idComment);
	News n = newsService.getNews(idNews);

	n = newsService.getNews(n.getId());
	n.getComments().remove(c);
	 comments=n.getComments();
	 
	return "/OilsTemplate/OneNews?faces-redirect=true";
	
}
public String Participate(int idUser,int idNews)
{
	newsService.participate(idUser, idNews);
	participation=newsService.particiaption(idNews, idUser);
	 
	return "/OilsTemplate/OneNews?faces-redirect=true";
	
}
public String Cancel(int idUser,int idNews)
{
	newsService.Cancel(idUser, idNews);
	participation=newsService.particiaption(idNews, idUser);
	 
	return "/OilsTemplate/OneNews?faces-redirect=true";
	
}
}
