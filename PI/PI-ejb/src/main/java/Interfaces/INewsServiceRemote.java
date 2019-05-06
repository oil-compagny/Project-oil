package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.News;

@Remote
public interface INewsServiceRemote {

	public List<News> getAllNews();
	
	
	
}
