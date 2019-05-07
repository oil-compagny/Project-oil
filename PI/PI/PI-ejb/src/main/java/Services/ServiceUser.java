package Services;

import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Claim;
import Entities.User;

/**
 * Session Bean implementation class ServiceUser
 */
@Stateless
@LocalBean
public class ServiceUser implements ServiceUserRemote, ServiceUserLocal {

	 
    @PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ServiceUser() {
        super();
    }
    
    @Override
	public User getUser() {
		List<User> sd= em.createQuery("select c from User c").getResultList();
		if(sd.isEmpty())
			return null;
		return sd.get(1);
	}
    @Override
    public List<User> getallUser()
    {
    	List<User> sd= em.createQuery("select c from User c").getResultList();
		if(sd.isEmpty())
			return null;
		return sd;
    	
    }

    
    @Override
    public List<User> getallUserNotTreated()
    {
    	List<User> sd= em.createQuery("select c from User c where c.autorisation = 0").getResultList();
		if(sd.isEmpty())
			return null;
		return  sd ;
    	
    }
    @Override
    public List<User> getallWorkers()
    {
    	List<User> sd= em.createQuery("select c from User c where c.autorisation = 1").getResultList();
		if(sd.isEmpty())
			return null;
		return  sd ;
    	
    }
    @Override
    public  void addUser(User user )
    {
    	em.persist(user);
    }
    @Override
    public User login(String login, String passwd ){
    	User u =new User();
    	u= em.createQuery("select c from User c where c.login=:l and c.passwd=:p",User.class)
    			.setParameter("l", login).setParameter("p", passwd).getSingleResult();
    	
    	
    	return u;
    		
		}
    
    @Override
    public void Autoriser(int id )
    {
    	User u=em.find(User.class, id);
    	u.setAutorisation(true);
    	em.persist(u);
    }
    
    
    @Override
    public void AddCode(int id, String code )
    {
    	User u=em.find(User.class, id);
    	u.setCode(code);
    	em.persist(u);
    }
    @Override
    public void SetPass(int id, String passwd )
    {
    	User u=em.find(User.class, id);
    	u.setPasswd(passwd);
    	em.persist(u);
    }
    
    
    @Override
    public void Decline(int id )
    {
    	User u=em.find(User.class, id);
    	em.remove(u);
    	
    }
    
    @Override
	public Boolean findByLogin(String login) {
		User user = null;
		Boolean r = false ;
		try {
			user = em.createQuery("SELECT u FROM User u WHERE u.login=:l", User.class)
					.setParameter("l", login)
					.getSingleResult();
		} catch (Exception e) {
		}
		
		if (user != null) { r=true ;}
		
		return r;
	}
    
    @Override
   	public Boolean findByCin(String cin) {
   		User user = null;
   		Boolean r = false ;
   		try {
   			user = em.createQuery("SELECT u FROM User u WHERE u.cin=:l", User.class)
   					.setParameter("l", cin)
   					.getSingleResult();
   		} catch (Exception e) {
   		}
   		
   		if (user != null) { r=true ;}
   		
   		return r;
   	}
    
    
    @Override
   	public Boolean findBycode(String code) {
   		User user = null;
   		Boolean r = false ;
   		try {
   			user = em.createQuery("SELECT u FROM User u WHERE u.code=:l", User.class)
   					.setParameter("l", code)
   					.getSingleResult();
   		} catch (Exception e) {
   		}
   		
   		if (user != null) { r=true ;}
   		
   		return r;
   	} 
    @Override
   	public Boolean findByMail(String mail) {
   		User user = null;
   		Boolean r = false ;
   		try {
   			user = em.createQuery("SELECT u FROM User u WHERE u.mail=:l", User.class)
   					.setParameter("l", mail)
   					.getSingleResult();
   		} catch (Exception e) {
   		}
   		
   		if (user != null) { r=true ;}
   		
   		return r;
   	}

	@Override
	public User findmail(String mail) {
		
	    	User sd=em.createQuery("select c from User c where c.mail=:l ",User.class)
	    			.setParameter("l", mail).getSingleResult();
	    	
			return  sd ;
	    }
	 
    
    
    	
    
}
