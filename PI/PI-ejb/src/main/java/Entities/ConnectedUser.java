package Entities;

public class ConnectedUser {
private static int idConnected;
private static String FirstName;
private static String  LastName;
private static String  mail;
private static String  autorisation;
private static String  actif;
private static String  cin;
private static String  password;
private static String  username;
public static int getIdConnected() {
	return idConnected;
}
public static void setIdConnected(int idConnected) {
	ConnectedUser.idConnected = idConnected;
}
public static String getFirstName() {
	return FirstName;
}
public static void setFirstName(String firstName) {
	FirstName = firstName;
}
public static String getLastName() {
	return LastName;
}
public static void setLastName(String lastName) {
	LastName = lastName;
}
public static String getMail() {
	return mail;
}
public static void setMail(String mail) {
	ConnectedUser.mail = mail;
}
public static String getAutorisation() {
	return autorisation;
}
public static void setAutorisation(String autorisation) {
	ConnectedUser.autorisation = autorisation;
}
public static String getActif() {
	return actif;
}
public static void setActif(String actif) {
	ConnectedUser.actif = actif;
}
public static String getCin() {
	return cin;
}
public static void setCin(String cin) {
	ConnectedUser.cin = cin;
}
public static String getPassword() {
	return password;
}
public static void setPassword(String password) {
	ConnectedUser.password = password;
}
public static String getUsername() {
	return username;
}
public static void setUsername(String username) {
	ConnectedUser.username = username;
}




}
