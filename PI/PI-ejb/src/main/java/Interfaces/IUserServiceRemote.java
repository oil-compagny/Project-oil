package Interfaces;


import java.util.List;
import javax.ejb.Remote;

import Entities.Gas_station;
import Entities.User;



@Remote
public interface IUserServiceRemote {
public int addUser(User user);
public void removeUser(int id);
public void updateUser(User user);
public User findWorkerByref(int ref);
public List<User> findAllUsers();
public List<User> findAllWorkers();
public Gas_station  addWorkerToGasStation(User w, int id_G);
public Gas_station removeUserfromGS(int id,int idGS);
public Entities.Accounting Accounting(int idU,int idAc,Double retV,int vcheckout,String Type);
}