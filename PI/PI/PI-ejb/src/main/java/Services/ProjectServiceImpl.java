package Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Deal;
import Entities.Project;
import Entities.User;
import Entities.Zone;
import Interfaces.IProjectServiceRemote;

@Stateless
@LocalBean
public class ProjectServiceImpl implements IProjectServiceRemote {
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;
	
	@Override
	public List<Project> afficher_projects() {
		// TODO Auto-generated method stub
		List<Project> projects = new ArrayList();
		projects=em.createQuery("from Project",
		Project.class).getResultList();
		System.out.println(projects);
		return projects;
		
	}

	@Override
	public void ajout_deal(Deal d,int idProject,int idPartner) {
		
	   
	    Project p =em.find(Project.class,idProject);
	    User partner=em.find(User.class,idPartner);
	    d.setProject(p);
		d.setPartner(partner);
		 em.persist(d);
		 System.out.println(d);
		
	}


   
	public Deal getBestDeal(int idp)
	{   
		Project p = em.find(Project.class,idp);
		Deal d= (Deal) em.createQuery("select d from Deal d where d.project=:idp AND d.somme_propose<=d.project.somme ")
				.setParameter("idp",p).getSingleResult();
		
	
		return d;
		
	}

}
