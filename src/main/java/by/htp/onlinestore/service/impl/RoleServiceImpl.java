package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.RoleDao;
import by.htp.onlinestore.entity.Role;
import by.htp.onlinestore.service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao;

	public RoleServiceImpl() {

	}
	

	public RoleDao getRoleDao() {
		return roleDao;
	}



	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}



	@Override
	public List<Role> getRoleList() {
		
		return roleDao.readAll();
	}

}
