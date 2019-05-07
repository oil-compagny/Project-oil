package Services;

import java.util.List;

import javax.ejb.Remote;

import Entities.Claim;
import Entities.User;

@Remote
public interface ClaimserviceRemote {

	public Claim getNextNotTreatedClaim();
    public void claimIsTreated(Claim c);
	public int claimNumber(User u);
	public java.util.List<Claim> historyClaim(User u);
	public void blockUser(User u);
	public List<Claim> getNextNotTreatedClaims();
	 public void addClaim(Claim c);
	public List<Claim> getClaim();
}