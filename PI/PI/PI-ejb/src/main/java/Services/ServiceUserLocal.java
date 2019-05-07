package Services;

import java.util.List;

import javax.ejb.Local;

import Entities.User;

@Local
public interface ServiceUserLocal {

	User getUser();
	void addUser(User user);
	public List<User> getallUser();

}
