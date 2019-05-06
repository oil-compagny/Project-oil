package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private String cin;
private String Firstname;
private String LastName;
private String Phone;
private String mail;
private String login;
private String passwd;
private Boolean autorisation;
private String country;
private String state;
private String code;
private Boolean Allowedtochangepassword;


private Double salary =0.0;
//@Column(unique=true)


private Boolean isActif;

@Enumerated(EnumType.STRING)
private Role role;

@OneToMany(mappedBy="worker",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
private List <Accounting> accounting =new ArrayList<>() ; 


@ManyToOne
private Gas_station station;
public Gas_station getStation() {
	return station;
}

@OneToMany(mappedBy="publisher",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
private List<Participation> participations; 

public void setStation(Gas_station station) {
	this.station = station;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}


public Double getSalary() {
	return salary;
}

public void setSalary(Double salary) {
	this.salary = salary;
}



public Boolean getIsActif() {
	return isActif;
}

public void setIsActif(Boolean isActif) {
	this.isActif = isActif;
}

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
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
@OneToMany(mappedBy="claimer",cascade=CascadeType.ALL)
private List<Claim> Claims;



@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
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
	if (id != other.id)
		return false;
	return true;
}

public Boolean getAllowedtochangepassword() {
	return Allowedtochangepassword;
}

public void setAllowedtochangepassword(Boolean allowedtochangepassword) {
	Allowedtochangepassword = allowedtochangepassword;
}

public List<Claim> getClaims() {
	return Claims;
}

public void setClaims(List<Claim> claims) {
	Claims = claims;
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