package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public List<Claim> historyClaim(User u) {
		List<Claim> sd=em.createQuery("select c from Claim c where c.claimer.id ="+u.getId(),Claim.class).getResultList();
		System.out.println(sd);
		return sd;
	}

	@Override
	public void blockUser(User u) {
		em.merge(u);
		
	}

	@Override
	public void claimIsTreated(Claim c) {
		em.merge(c);
		
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

}
