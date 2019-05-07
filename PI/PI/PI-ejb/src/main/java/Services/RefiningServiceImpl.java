package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Commande;
import Entities.OilWells;
import Entities.RefiningFactory;
import Interfaces.IRefiningServiceRemote;

@Stateless
public class RefiningServiceImpl implements IRefiningServiceRemote {
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;
	
	@Override
	public int ConsulterStock(int refiningId,int quantite_demande) {
		RefiningFactory r=em.find(RefiningFactory.class,refiningId);
		
		if(r.getStock()>=quantite_demande)
		{     
			r.setStock(r.getStock()-quantite_demande);
			return 111;
		}
		else
			System.out.println("raffiner de nouveau");
		
		return 222;
	
	}

	@Override
	public List<RefiningFactory> findAll() {
		// TODO Auto-generated method stub
        List<RefiningFactory> rf = em.createQuery("from RefiningFactory",RefiningFactory.class).getResultList();
		
		return rf;
		
	}

	@Override
	public List<Commande> findCommandes() {
		// TODO Auto-generated method stub
		List<Commande> c=em.createQuery("from Commande", Commande.class).getResultList();
		return c;
	}

	@Override
	public Long Commande_valide() {
		// TODO Auto-generated method stub
		TypedQuery<Long> query=em.createQuery("SELECT COUNT(c) FROM Commande c where c.validate=1",Long.class);
		
		return  query.getSingleResult() ;
				
	}

	@Override
	public List<Commande> findCommandesNonValide() {
		// TODO Auto-generated method stub
		List<Commande> c=em.createQuery("from Commande c Where c.validate=0 ", Commande.class).getResultList();
		return c;
	}

	@Override
	public void Valider_commande(int idC) {
		// TODO Auto-generated method stub
		Commande c =em.find(Commande.class, idC);
		c.setValidate(1);
		
	}

	@Override
	public Commande findCombyID(int id) {
		// TODO Auto-generated method stub
		Commande c = em.find(Commande.class,id);
		return c;
	}

	@Override
	public RefiningFactory updateStock(int value) {
		// TODO Auto-generated method stub
		
			
		List<RefiningFactory> L= em.createQuery("from RefiningFactory",RefiningFactory.class).getResultList();
		if(value>L.get(0).getStock())
		{L.get(0).setStock(0);}
		else L.get(0).setStock(L.get(0).getStock()-value);
		
		return L.get(0);
	}
	
	

}
