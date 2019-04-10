package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Commande;
import Interfaces.CommandeServiceRemote;

@Stateless
public class CommandeService implements CommandeServiceRemote{
	@PersistenceContext(unitName ="imputation-ejb")
	EntityManager em ;

	@Override
	public void addCommande(Commande c) {
		System.out.println("add Commande  !");
		em.persist(c);
		System.out.println("add fleet done!" );		
	}

	@Override
	public void removeCommande(int id) {
		System.out.println("remove Commande !");
	    em.remove(em.find(Commande.class, id));
	    System.out.println("remove fleet done ! " );		
	}

	@Override
	public void updateCommande(Commande c) {
		System.out.println("update Commande !");
		em.merge(c);
		System.out.println("update persist !");

	}

	@Override
	public Commande findCommandeById(int id) {
		System.out.println("find Commande by id ! ");
		Commande commande =em.find(Commande.class,id);
        System.out.println("ind Commande by id done ! " );			
        return commande;
	}

	@Override
	public List<Commande> findAllCommande() {
		TypedQuery<Commande> liste =   em.createQuery("select t from Commande t WHERE t.validate = 1 and t.received=0 order by t.dateLimite_livraison asc",Commande.class);
		List<Commande> listResultat = liste.getResultList();
		return listResultat;
	}

	@Override
	public List<Commande> findAllCommandeTotreat() {
		TypedQuery<Commande> liste =   em.createQuery("select t from Commande t WHERE t.validate = 0 order by t.dateLimite_livraison desc",Commande.class);
		List<Commande> listResultat = liste.getResultList();
		return listResultat;
	}

	@Override
	public List<Commande> findAllCommandeReceived() {
		TypedQuery<Commande> liste =   em.createQuery("select t from Commande t WHERE t.received =1 and t.validate = 1 order by t.dateLimite_livraison asc",Commande.class);
		List<Commande> listResultat = liste.getResultList();
		return  listResultat;	
	}

	@Override
	public List<Commande> findAllCommandeCanceled() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
