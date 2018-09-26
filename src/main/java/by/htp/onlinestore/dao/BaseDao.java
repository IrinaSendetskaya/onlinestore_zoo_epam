package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.EntityBase;

/**
 * Interface provides CRUD methods for access to database data.
 * 
 * @author Iryna Siandzetskaya
 *
 * @param <T>
 */
public interface BaseDao<T extends EntityBase> {

	/**
	 * it creates a new entity in database
	 *
	 * @param entity
	 */
	void create(T entity);

	/**
	 * it updates an entity in database
	 * @param entity
	 */
	void update(T entity);

	/**
	 * it deletes an entity from database
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * it gets an entity from database
	 * @param id
	 * @return an entity
	 */
	T read(int id);

	/**
	 * it gets all entities from database
	 * @return List entity
	 */
	List<T> readAll(); // все прочитать

}
