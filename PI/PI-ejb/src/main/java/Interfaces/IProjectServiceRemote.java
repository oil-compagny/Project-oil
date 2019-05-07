package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Deal;
import Entities.Project;
import Entities.User;

@Remote 
public interface IProjectServiceRemote 
{
  public List<Project> afficher_projects();
  public void ajout_deal(Deal d,int idProject,int idUser);

}
