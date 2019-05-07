package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import Entities.News;
import Entities.Participation;
import Entities.User;
import Interfaces.INewsServiceRemote;

@Stateless 
@LocalBean
public class NewsService implements INewsServiceRemote {
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;
	
	@Override
	public List<News> getAllNews() {
		List<News> newss = em.createQuery("select n from News n",News.class).getResultList();
		// TODO Auto-generated method stub
		System.out.println(newss);
		return newss;
	}
	public News getNews(int id)
	{
		return em.find(News.class,id);
	}
	@Override
	public void participate(int idU, int idNews) {
		News n = em.find(News.class,idNews );
		User u = em.find(User.class, idU);
		try{
		Participation p = em.createQuery("select p from Participation p where p.news= :n and p.publisher=:p",Participation.class).setParameter("n", n)
				.setParameter("p",u).getSingleResult();
		if(p!=null)
		{
			if(p.getPour()==0)
			{if(p.getContre()==1)
				p.setContre(0);
				p.setPour(1);
			}else p.setPour(0);
		}
		}
		catch(NoResultException e)
		{
			Participation p=new Participation();
			p.setPublisher(em.find(User.class, idU));
			p.setNews(n);
			p.setPour(1);
			em.persist(p);
			return;
		}
	}
		@Override
		public void Cancel(int idU, int idNews) {
			News n = em.find(News.class,idNews );
			User u = em.find(User.class, idU);
			try{
			Participation p = em.createQuery("select p from Participation p where p.news= :n and p.publisher=:p",Participation.class).setParameter("n", n)
					.setParameter("p",u).getSingleResult();
			if(p!=null)
			{
				if(p.getContre()==0 )
				{if(p.getPour()==1)
					
					p.setPour(0);
				p.setContre(1);
				}else 
					p.setContre(0);
		
			}
			}
			catch(NoResultException e)
			{
				Participation p=new Participation();
				p.setPublisher(em.find(User.class, idU));
				p.setNews(n);
				p.setContre(1);
				em.persist(p);
				return;
			}
		
		// TODO Auto-generated method stub
		
	}
		public Participation particiaption (int idNews,int idU)
		{News n = em.find(News.class,idNews );
		User u = em.find(User.class, idU);
			Participation p = new Participation();
			try{
				 p = em.createQuery("select p from Participation p where p.news= :n and p.publisher=:p",Participation.class).setParameter("n", n)
						.setParameter("p",u).getSingleResult();
				 return p;
			}
			catch(NoResultException e)
			{
				return p;
			}
			}
		}
	
	
