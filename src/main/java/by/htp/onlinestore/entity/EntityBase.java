package by.htp.onlinestore.entity;

import java.io.Serializable;

public abstract class EntityBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1894332867975051185L;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EntityBase() {
		super();
	}

	public EntityBase(int id) {
		super();
		this.id = id;
	}


}
