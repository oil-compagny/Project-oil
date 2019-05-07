package managedBean;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Entities.Deal;
import Entities.Project;
import Services.ProjectServiceImpl;

@ManagedBean(name ="dealBean") 
@SessionScoped
public class DealBean implements Serializable
{	
private Float somme_propose;
private Date date_propose;
private int score;

private Project project;




	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	public Float getSomme_propose() {
		return somme_propose;
	}
	public void setSomme_propose(Float somme_propose) {
		this.somme_propose = somme_propose;
	}
	public Date getDate_propose() {
		return date_propose;
	}
	public void setDate_propose(Date date_propose) {
		this.date_propose = date_propose;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@EJB
	ProjectServiceImpl projectservice;
	
	public String ajouterDeal(int id)
	{ 
		String navigateTo = "null"; 
		 navigateTo = "/OilsTemplate/Deals?faces-redirect=true";
		
		Deal d = new Deal();
		d.setDate_propose(date_propose);
		d.setSomme_propose(somme_propose);
		
		projectservice.ajout_deal(d,id,2);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		 return navigateTo; 
		
	}

}
