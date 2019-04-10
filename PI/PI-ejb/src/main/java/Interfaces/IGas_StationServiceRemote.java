package Interfaces;


import java.util.List;

import javax.ejb.Remote;

import Entities.Accounting;
import Entities.Cistern;
import Entities.Commande;
import Entities.Gas_station;
import Entities.Pump;
import Services.Gas_StationService;


@Remote
public interface IGas_StationServiceRemote {
public int addStation(Gas_station G);
public void AddGerant(int Gerant, int GS);
public Gas_station ShowGasOfGerant(int Gerant);
public void AffectWorkerPump(int idW, int idP,String shift);
public List<Pump> AvailablePumpShift(String shift,List<Cistern> cisterns);
public void checkcout(int idW,int idP,int value,double price);
public List<Pump> ShowPumps();
public Commande addCommand(Commande c,int idF,int idG);
public List<Commande> showCommands(String adr);
public Gas_station deleteCommands(int idC);
public List<Accounting> CurrentPumps(int idU);
}
