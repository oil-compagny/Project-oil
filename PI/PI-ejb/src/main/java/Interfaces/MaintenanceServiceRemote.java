package Interfaces;

import java.util.List;


import javax.ejb.Remote;

import Entities.Maintenance;

@Remote
public interface MaintenanceServiceRemote {

	public void addMaintenance(Maintenance m) ; 
	public void removeMaintenance(int id) ;
	public void updateMaintenance(Maintenance m) ;
	public Maintenance findMaintenanceById(int id) ; 
	public List<Maintenance> findAllMaintenance();
}
