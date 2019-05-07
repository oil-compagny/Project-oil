package Interfaces;


import java.util.List;

import javax.ejb.Remote;

import Entities.OilWells;
import Entities.Zone;


@Remote
public interface IZoneServiceRemote {
	

	public int affecterPuitsAZone(int puitsId, int ZoneId);
	public List<Zone> AfficherLesZones();
	 public Zone findZoneById(int id);
}
