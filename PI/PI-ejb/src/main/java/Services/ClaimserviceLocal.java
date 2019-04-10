package Services;

import java.util.List;

import javax.ejb.Local;

import Entities.Claim;
import Entities.User;

@Local
public interface ClaimserviceLocal {

	public Claim getNextNotTreatedClaim();
    public void claimIsTreated(int id);
	public int claimNumber(User u);
	public List<Claim> historyClaim();
	public void blockUser(int id );
	public List<Claim> getNextNotTreatedClaims();
}
