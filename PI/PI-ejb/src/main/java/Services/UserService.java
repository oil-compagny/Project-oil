package Services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.User;
import Interfaces.UserServiceRemote;

@Stateless
public class UserService implements UserServiceRemote{
	@PersistenceContext(unitName ="imputation-ejb")
	EntityManager em ;

	@Override
	public User findUserById(int id) {
		User u =em.find(User.class,id);		
		return u;
	}

}
