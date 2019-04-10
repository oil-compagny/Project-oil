package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import Entities.Countries;
import Entities.User;


/**
 * Session Bean implementation class CountrieService
 */
@Stateless
@LocalBean
public class CountrieService implements CountrieServiceRemote, CountrieServiceLocal {

	public CountrieService() {
    	super();
    	}
	
	 
    @PersistenceContext
	private EntityManager em;
    
    @Override
    public List<Countries> findall()
    {
    	List<Countries> sd= em.createQuery("select c from Countries c ").getResultList();
		if(sd.isEmpty())
			return null;
		return  sd ;
    	
    }

	
}
