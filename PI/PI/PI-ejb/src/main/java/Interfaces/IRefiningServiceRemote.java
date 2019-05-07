package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Commande;
import Entities.RefiningFactory;

@Remote
public interface IRefiningServiceRemote {
       public int ConsulterStock(int refiningId,int quantite_demande);
       public List<RefiningFactory> findAll();
       public List<Commande> findCommandes();
       public Long Commande_valide();
       public  List<Commande> findCommandesNonValide();
       public void Valider_commande(int idC);
       public Commande findCombyID(int id);
       public RefiningFactory updateStock(int value);
}
