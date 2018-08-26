package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.EntityBase;

public interface BaseDao <T extends EntityBase>{
	
	void create(T entity);
	void update(T entity);
	void delete(T entity);
	T read (int id);
	List <T> readAll();               //все прочитать

}
