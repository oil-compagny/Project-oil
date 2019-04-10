package Entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Claim
 *
 */
@Entity

public class Claim implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idClaim;
	private Date date=Calendar.getInstance().getTime();
	private String text;
	private String status;
	private Boolean treated=false;
	@ManyToOne
	private User claimer;
	private static final long serialVersionUID = 1L;

	public Claim() {
		super();
	}   
	public Integer getClaimId() {
		return this.idClaim;
	}

	public void setClaimId(Integer claimId) {
		this.idClaim = claimId;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}   
	public Boolean getTreated() {
		return this.treated;
	}

	public void setTreated(Boolean treated) {
		this.treated = treated;
	}
	public User getClaimer() {
		return claimer;
	}
	public void setClaimer(User claimer) {
		this.claimer = claimer;
	}
	public Claim(String text) {
		super();
		this.text = text;
	}
	
	
	public Claim(String text, User claimer) {
		super();
		this.text = text;
		this.claimer = claimer;
	}
	
	
	public Claim(String text, String status, User claimer) {
		super();
		this.text = text;
		this.status = status;
		this.claimer = claimer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idClaim == null) ? 0 : idClaim.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Claim other = (Claim) obj;
		if (idClaim == null) {
			if (other.idClaim != null)
				return false;
		} else if (!idClaim.equals(other.idClaim))
			return false;
		return true;
	}
	public Claim(Integer idClaim, Date date, String text, String status, Boolean treated) {
		super();
		this.idClaim = idClaim;
		this.date = date;
		this.text = text;
		this.status = status;
		this.treated = treated;
	}
	public Integer getIdClaim() {
		return idClaim;
	}
	public void setIdClaim(Integer idClaim) {
		this.idClaim = idClaim;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
   
}
