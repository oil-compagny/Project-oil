package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.stream.events.Comment;

import Entities.Comments;
import Entities.News;
import Entities.User;
import Interfaces.ICommentsService;

@Stateless
@LocalBean
public class CommentsService implements ICommentsService{
	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;
	
	@Override
	public int addComment(Comments c) {
		
		
		em.persist(c);
		
		// TODO Auto-generated method stub
		return c.getId();
	}

	@Override
	public void deleteComment(int id) {
		// TODO Auto-generated method stub.
		
		Comments c = em.find(Comments.class,id);
		News n = em.find(News.class, c.getNews().getId());
		n.getComments().remove(c);
		
		em.remove(c);
	}
	@Override
	public List<Comments> showComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comments findComments(int id) {
		// TODO Auto-generated method stub
		return em.find(Comments.class, id);
	}

}
