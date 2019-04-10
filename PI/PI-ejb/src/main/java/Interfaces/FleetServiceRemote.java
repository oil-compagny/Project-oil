package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Fleet;
import Entities.User;

@Remote
public interface FleetServiceRemote {

	public void addFleet(Fleet f) ; 
	public void removeFleet(int id) ;
	public void updateFleet(Fleet f) ;
	public Fleet findFleetById(int id) ; 
	public List<Fleet> findAllFleet();
	public Fleet findFleetDetailsById(int id) ; 
	public List <Fleet> ComboFleet(float maxW,String type);
	public List <User> ComboUser();
	public User findUserByid(int id);
	public Fleet FindFleetByMat(String mat);
	public List<Fleet> findAllFleetFCT();

	



}
