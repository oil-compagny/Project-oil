package managedBean;

import java.io.Serializable;


import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import Entities.Role;
import Entities.User;
import Services.ServiceUser;

@ManagedBean(name="loginBean") 
@SessionScoped
public class LoginBean implements Serializable {
private static final long serialVersionUID = 1L;

private String login; 
private String password; 
private User employe; 
private Boolean loggedIn;

@EJB
ServiceUser employeService; 

//Getters & Setters

public String doLogin()
{
String navigateTo = "null"; 
employe = employeService.login(login, password);
if (employe != null ) {
navigateTo = "/OilsTemplate/news?faces-redirect=true";
loggedIn = true; 
}
else 
{
FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));}

return navigateTo; 
}
public String doLogout()
{FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
return "/login?faces-redirect=true";}
 //Générer un constructeur sans argument, les Getters et les Setters
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public User getEmploye() {
	return employe;
}
public void setEmploye(User employe) {
	this.employe = employe;
}
public Boolean getLoggedIn() {
	return loggedIn;
}
public void setLoggedIn(Boolean loggedIn) {
	this.loggedIn = loggedIn;
}




}