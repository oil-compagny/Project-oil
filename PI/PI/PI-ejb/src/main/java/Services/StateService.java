package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.States;
import Entities.Countries;


/**
 * Session Bean implementation class StateService
 */
@Stateless
@LocalBean
public class StateService implements StateServiceRemote, StateServiceLocal {
	 @PersistenceContext
		private EntityManager em;
	 
	 public StateService() {
	    	super();
	    	}
	 @Override
		public List <States> mapcs(String cn , int ci) {
			Countries c = null;
			List<States> LS = null;
			
			
			try {
				c = em.createQuery("select co from Countries co where co.name=:name ", Countries.class)
						.setParameter("name", cn).getSingleResult();
			} catch (Exception e) {
			}
			try {
				LS = em.createQuery("select st from States st where st.country_id=:sci ", States.class)
						.setParameter("sci", c.getId()).getResultList();
			} catch (Exception e) {
			}
			
			
			
			return LS;
		}
    
   

}
