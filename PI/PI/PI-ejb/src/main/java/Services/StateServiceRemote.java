package Services;

import java.util.List;

import javax.ejb.Remote;

import Entities.States;

@Remote
public interface StateServiceRemote {

	List<States> mapcs(String cn, int ci);

}
