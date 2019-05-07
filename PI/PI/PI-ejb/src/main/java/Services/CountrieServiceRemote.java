package Services;

import java.util.List;

import javax.ejb.Remote;

import Entities.Countries;
import Entities.User;

@Remote
public interface CountrieServiceRemote {

	

	public List<Countries> findall();

}
