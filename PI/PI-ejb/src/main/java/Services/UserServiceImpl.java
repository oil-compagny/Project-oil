package Services;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Cistern;
import Entities.Gas_station;
import Entities.Role;
import Entities.Shift;
import Entities.Type;
import Entities.User;
import Interfaces.IUserServiceRemote;




@Stateless
public class UserServiceImpl implements IUserServiceRemote {
// imputation-ejb in persistence.xml
@PersistenceContext(unitName = "imputation-ejb")
EntityManager em=null;


	public int addUser(User user) {
	
	em.persist(user);
	System.out.println("add success" + user.getId());
	
	return user.getId();
	}

	public void removeUser(int id) {
	System.out.println("In removeUser : ");
	em.remove(em.find(User.class, id));
	System.out.println("Out of removeUser : ");}


	
	
	public List<User> findAllUsers() {
	System.out.println("In findAllUsers : ");
	List<User> users = em.createQuery("from User",
	User.class).getResultList();
	System.out.println("Out of findAllUsers : ");
	return users;
	}
	public List<User> findAllWorkers() {
		System.out.println("In findAllUsers : ");
		List<User> users = em.createQuery("Select u from User u where u.role=:role",
		User.class).setParameter("role", Role.Worker).getResultList();
		
		return users;
		}
	@Override
	public User findWorkerByref(int ref) {
		User worker=new User();
		
		
		try{
			TypedQuery <User> use = em.createQuery("select u from User u where u.ref=:ref and u.role=:role",User.class);
			use.setParameter("ref",ref).setParameter("role",Role.Worker);
			worker= use.getSingleResult();
		}
			catch (NoResultException re){
			//Ignore this because as per your logic this is ok!
				System.out.println("problem");
				return null;
			}

			
		return worker;
		}
	@Override
public Gas_station  addWorkerToGasStation(User w, int id_G)
{
	
	
	
	Gas_station gs=em.find(Gas_station.class, id_G);
	
	w.setStation(gs);
	em.persist(w);
	 
	 gs.getWorkers().add(w);
	return gs;
	}
	
	@Override
public Gas_station removeUserfromGS(int id,int idGS) {
	System.out.println("In removeUser : ");
	User u = em.find(User.class, id);
	Gas_station gs = em.find(Gas_station.class, idGS);

	
	gs.getWorkers().remove(u);
	
	
	em.remove(u);
	System.out.println("Out of removeUser : ");
	em.flush();
	return gs;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Entities.Accounting Accounting(int idU, int idAc, Double retV,int checkout,String type) {
		// TODO Auto-generated method stub
		User emp=em.find(User.class,idU);
		int vcc;
		
		emp.setSalary(emp.getSalary()+retV);
		Gas_station gs=em.find(Gas_station.class, emp.getStation().getId());
		for (Cistern c :gs.getCisterns())
		{
			if(c.getOtypeT().getType().equals(Type.valueOf(type)))
			{vcc=c.getCurrent()-checkout;
			if (vcc<0)
				c.setCurrent(0);
			else 
				c.setCurrent(vcc);
			}
		}
		Entities.Accounting Ac=em.find(Entities.Accounting.class, idAc);
		Double salary;
	if(Ac.getShift()==Shift.Day ||Ac.getShift()==Shift.Afternoon)
	{
		 salary = (double) (20*8);
	}else salary = (double) (40*8);
	emp.setSalary(emp.getSalary()+salary);
		Ac.setDone(1);
		return Ac;
	}


	}