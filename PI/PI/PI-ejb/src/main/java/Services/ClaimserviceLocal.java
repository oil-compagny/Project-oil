package Services;

import java.util.List;

import javax.ejb.Local;

import Entities.Claim;
import Entities.User;

@Local
public interface ClaimserviceLocal {

	public Claim getNextNotTreatedClaim();
    public void claimIsTreated(Claim c);
	public int claimNumber(User u);
	public java.util.List<Claim> historyClaim(User u);
	public void blockUser(User u);
	public List<Claim> getNextNotTreatedClaims();
}
