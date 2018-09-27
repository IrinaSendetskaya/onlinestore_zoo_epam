package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.RoleDao;
import by.htp.onlinestore.entity.Role;
import by.htp.onlinestore.service.RoleService;

/**
 * Class provides methods for working with Roles table.
 * @author Iryna Siandzetskaya
 *
 */
public class RoleServiceImpl implements RoleService {
	
	/**
	 * Declares a object
	 */
	private RoleDao roleDao;

	/**
	 * constructor without parameter
	 */
	public RoleServiceImpl() {

	}
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}



	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}



	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.RoleService#getRoleList()
	 */
	@Override
	public List<Role> getRoleList() {
		
		return roleDao.readAll();
	}

}
