package Services;

import java.util.List;

import javax.ejb.Remote;

import Entities.Claim;
import Entities.User;

@Remote
public interface ClaimserviceRemote {

	public Claim getNextNotTreatedClaim();
    public void claimIsTreated(int id);
	public int claimNumber(User u);
	public List<Claim> historyClaim();
	public void blockUser(int id );
	public List<Claim> getNextNotTreatedClaims();
	 public void addClaim(Claim c);
	public List<Claim> getClaim();
	public List<Claim> nbofClaims();
}