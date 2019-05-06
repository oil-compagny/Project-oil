package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.News;
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
	
	
	
}
