package Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import Entities.Claim;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "tab_user")

public class User implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cin;
	private String Firstname;
	private String LastName;
	private String Phone;
	private String mail;
	private String login;
	private String passwd;
	private Boolean isActif;
	private Boolean autorisation;
	private String country;
	private String state;
	private String code;
	private Boolean Allowedtochangepassword;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Boolean getIsActif() {
		return isActif;
	}
	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}
	public Boolean getAutorisation() {
		return autorisation;
	}
	public void setAutorisation(Boolean autorisation) {
		this.autorisation = autorisation;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getAllowedtochangepassword() {
		return Allowedtochangepassword;
	}
	public void setAllowedtochangepassword(Boolean allowedtochangepassword) {
		Allowedtochangepassword = allowedtochangepassword;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Accounting> getAccounting() {
		return accounting;
	}
	public void setAccounting(List<Accounting> accounting) {
		this.accounting = accounting;
	}
	public List<Commande> getCommandesF() {
		return commandesF;
	}
	public void setCommandesF(List<Commande> commandesF) {
		this.commandesF = commandesF;
	}
	public List<Commande> getCommandesG() {
		return commandesG;
	}
	public void setCommandesG(List<Commande> commandesG) {
		this.commandesG = commandesG;
	}
	public List<Claim> getClaims() {
		return Claims;
	}
	public void setClaims(List<Claim> claims) {
		Claims = claims;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", cin=" + cin + ", Firstname=" + Firstname + ", LastName=" + LastName + ", Phone="
				+ Phone + ", mail=" + mail + ", login=" + login + ", passwd=" + passwd + ", isActif=" + isActif
				+ ", autorisation=" + autorisation + ", country=" + country + ", state=" + state + ", code=" + code
				+ ", Allowedtochangepassword=" + Allowedtochangepassword + ", role=" + role + ", accounting="
				+ accounting + ", commandesF=" + commandesF + ", commandesG=" + commandesG + ", Claims=" + Claims + "]";
	}
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "worker")
	private List<Accounting> accounting;

	@OneToMany(mappedBy = "fournisseur")
	private List<Commande> commandesF;
	@OneToMany(mappedBy = "gerant")
	private List<Commande> commandesG;
	@OneToMany(mappedBy="claimer",cascade=CascadeType.ALL)
	private List<Claim> Claims;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Allowedtochangepassword == null) ? 0 : Allowedtochangepassword.hashCode());
		result = prime * result + ((Claims == null) ? 0 : Claims.hashCode());
		result = prime * result + ((Firstname == null) ? 0 : Firstname.hashCode());
		result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
		result = prime * result + ((Phone == null) ? 0 : Phone.hashCode());
		result = prime * result + ((accounting == null) ? 0 : accounting.hashCode());
		result = prime * result + ((autorisation == null) ? 0 : autorisation.hashCode());
		result = prime * result + ((cin == null) ? 0 : cin.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((commandesF == null) ? 0 : commandesF.hashCode());
		result = prime * result + ((commandesG == null) ? 0 : commandesG.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActif == null) ? 0 : isActif.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		User other = (User) obj;
		if (Allowedtochangepassword == null) {
			if (other.Allowedtochangepassword != null)
				return false;
		} else if (!Allowedtochangepassword.equals(other.Allowedtochangepassword))
			return false;
		if (Claims == null) {
			if (other.Claims != null)
				return false;
		} else if (!Claims.equals(other.Claims))
			return false;
		if (Firstname == null) {
			if (other.Firstname != null)
				return false;
		} else if (!Firstname.equals(other.Firstname))
			return false;
		if (LastName == null) {
			if (other.LastName != null)
				return false;
		} else if (!LastName.equals(other.LastName))
			return false;
		if (Phone == null) {
			if (other.Phone != null)
				return false;
		} else if (!Phone.equals(other.Phone))
			return false;
		if (accounting == null) {
			if (other.accounting != null)
				return false;
		} else if (!accounting.equals(other.accounting))
			return false;
		if (autorisation == null) {
			if (other.autorisation != null)
				return false;
		} else if (!autorisation.equals(other.autorisation))
			return false;
		if (cin == null) {
			if (other.cin != null)
				return false;
		} else if (!cin.equals(other.cin))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (commandesF == null) {
			if (other.commandesF != null)
				return false;
		} else if (!commandesF.equals(other.commandesF))
			return false;
		if (commandesG == null) {
			if (other.commandesG != null)
				return false;
		} else if (!commandesG.equals(other.commandesG))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActif == null) {
			if (other.isActif != null)
				return false;
		} else if (!isActif.equals(other.isActif))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (role != other.role)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	public User(Integer id, String cin, String firstname, String lastName, String phone, String mail, String login,
			String passwd, Boolean isActif, Boolean autorisation, String country, String state, String code,
			Boolean allowedtochangepassword, Role role) {
		super();
		this.id = id;
		this.cin = cin;
		this.Firstname = firstname;
		this.LastName = lastName;
		this.Phone = phone;
		this.mail = mail;
		this.login = login;
		this.passwd = passwd;
		this.isActif = isActif;
		this.autorisation = autorisation;
		this.country = country;
		this.state = state;
		this.code = code;
		this.Allowedtochangepassword = allowedtochangepassword;
		this.role = role;
	}
	public User() {
		super();
	}

	
	

}