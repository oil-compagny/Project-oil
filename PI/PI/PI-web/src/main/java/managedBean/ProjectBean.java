package managedBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Entities.Deal;
import Entities.Project;

import Services.ProjectServiceImpl;


@ManagedBean(name = "projectBean") 
@SessionScoped

public class ProjectBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String details;
	private Date date;
	private Float somme;
	private String localisation;
	private String Projectname;
	private int nbreDeal;
	private int nbreDealP1;
	private int nbreDealP2;
	private int nbreDealP3;
	private String nameProject1;
	private String nameProject2;
	private int idProject;
	private Deal perfectDeal;
	
	public int getNbreDealP1() {
		return nbreDealP1;
	}

	public void setNameProject2(String nameProject2) {
		this.nameProject2 = nameProject2;
	}
	
	
	@PostConstruct
	public void init()
	{
		//init the bean
		List<Project> projects=projectservice.afficher_projects();
	    int nbretotal=projects.get(0).getNbreDeals()+projects.get(1).getNbreDeals();
		
	    
	     nbreDealP1=projects.get(0).getNbreDeals();
	     nbreDealP2=projects.get(1).getNbreDeals();
	     nbreDealP3=projects.get(2).getNbreDeals();
	     nameProject1=projects.get(0).getProjectname();
	     nameProject2=projects.get(1).getProjectname();
	     
	    
	    
	    
	     }
	
	
	public int getNbreDealP3() {
		return nbreDealP3;
	}

	public void setNbreDealP3(int nbreDealP3) {
		this.nbreDealP3 = nbreDealP3;
	}

	public int getNbreDeal() {
		return nbreDeal;
	}
	public void setNbreDeal(int nbreDeal) {
		this.nbreDeal = nbreDeal;
	}
	public List<Deal> getDeals() {
		return deals;
	}
	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}
	private List<Deal> deals;
	
	
	public String getProjectname() {
		return Projectname;
	}
	public void setProjectname(String projectname) {
		Projectname = projectname;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getSomme() {
		return somme;
	}
	public void setSomme(Float somme) {
		this.somme = somme;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	@EJB
	ProjectServiceImpl projectservice;
	
 public List<Project> afficher_projects()
 { 
	return(projectservice.afficher_projects());
	
	 
 }
 
  
 
 public String MakeDeal(Project project)
 {
 String navigateTo = "null"; 
 navigateTo = "/OilsTemplate/MakeDeal?faces-redirect=true";
 idProject=project.getId();
 System.out.println(idProject);
 return navigateTo; 
 
 }
 
 public int getIdProject() {
	return idProject;
}







public void setIdProject(int idProject) {
	this.idProject = idProject;
}







public Deal getPerfectDeal() {
	return perfectDeal;
}

public void setPerfectDeal(Deal perfectDeal) {
	this.perfectDeal = perfectDeal;
}

public String perfectDeal(Project project)
 {  
	 String navigateTo = "null"; 
	 navigateTo = "/OilsTemplate/BestDeal?faces-redirect=true";
	 idProject=project.getId();
	 System.out.println(idProject);
	 perfectDeal=projectservice.getBestDeal(project.getId());
	 return navigateTo; 
 }

	public void setNbreDealP1(int nbreDealP1) {
		this.nbreDealP1 = nbreDealP1;
	}


	public int getNbreDealP2() {
		return nbreDealP2;
	}


	public void setNbreDealP2(int nbreDealP2) {
		this.nbreDealP2 = nbreDealP2;
	}

	public String getNameProject1() {
		return nameProject1;
	}

	public void setNameProject1(String nameProject1) {
		this.nameProject1 = nameProject1;
	}



	public String getNameProject2() {
		return nameProject2;
	}


}
