package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Cistern;
import Entities.Pump;

@Remote
public interface ICisternServiceRemote {
public Cistern findCistern(int id);
public int addPump(Pump P);
public int addCistern(Cistern C);
public void addPumpToCistern(int C);


public List<Cistern> ShowAllCistern(int idGS);




}
