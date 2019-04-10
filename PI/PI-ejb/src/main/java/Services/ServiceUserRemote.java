package Services;

import java.util.List;

import javax.ejb.Remote;

import Entities.User;

@Remote
public interface ServiceUserRemote {
	User getUser();

	public void addUser(User user);

	public List<User> getallUser();

	public User login(String login, String passwd);

	 public List<User> getallUserNotTreated();

	public void Autoriser(int id);

	public void Decline(int id);

	public List<User> getallWorkers();

	public Boolean findByLogin(String login);

	public Boolean findByCin(String cin);

	 public Boolean findByMail(String mail);

	public Boolean findBycode(String code);

	public void AddCode(int id , String code);
	public User findmail(String mail);

	public void SetPass(int id, String passwd);

}
