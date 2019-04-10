package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Entities.Claim;
import Entities.User;

/**
 * Session Bean implementation class Claimservice
 */
@Stateless
@LocalBean
public  class Claimservice implements ClaimserviceRemote, ClaimserviceLocal {

    /**
     * Default constructor. 
     */
    
    @PersistenceContext
	private EntityManager em;
    

    
    @Override
	public List<Claim> getClaim() {
		List<Claim> sd= em.createQuery("select c from Claim c").getResultList();
		if(sd.isEmpty())
			return null;
		return sd;
	}
	@Override
	public Claim getNextNotTreatedClaim() {
		List<Claim> sd= em.createQuery("select c from Claim c where c.treated = 0").getResultList();
		if(sd.isEmpty())
			return null;
		return sd.get(0);
	}

	

	@Override
	public int claimNumber(User u) {
		List<Claim> sd= em.createQuery("select c from Claim c where c.claimer.id ="+u.getId()).getResultList();
		return sd.size();
	}

	

	@Override
	public void blockUser(int id ) {
		Claim u=em.find(Claim.class, id);
    	em.remove(u);
		
	}

	@Override
	public void claimIsTreated(int id) {
		Claim u=em.find(Claim.class , id );
    	u.setTreated(true);
    	
    	em.persist(u);
		
	}

	@Override
	public List<Claim> getNextNotTreatedClaims() {
		List<Claim> sd= em.createQuery("select c from Claim c where c.treated = 0").getResultList();
   		//List<Claim> sd=em.createQuery("select c from Claim c where c.claimer.id ="+u.getId(),Claim.class).getResultList();
		System.out.println(sd);
		return sd;
	}
	@Override
    public  void addClaim(Claim c )
    {
    	em.persist(c);
    }
	@Override
	public List<Claim> historyClaim() {
		
			List<Claim> sd=em.createQuery("select c from Claim c where c.treated = 1").getResultList();
			if(sd.isEmpty())
				return null;
			return sd;
		
	}
   @Override
	public List<Claim> nbofClaims() {
		Query q =  em.createQuery("select Count(c), from Claim c where c.treated = 1 ") ;
		return q.getResultList() ;
	}
	
	
	
}
