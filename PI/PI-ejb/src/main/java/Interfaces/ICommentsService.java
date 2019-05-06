package Interfaces;

import java.util.List;

import Entities.Comments;

public interface ICommentsService {
	public int addComment(Comments c);
	public void deleteComment(int id);
	public List<Comments> showComments();
	public Comments findComments(int id);

}
