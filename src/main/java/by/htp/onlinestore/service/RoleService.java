package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Role;

/**
 * Interface provides methods for working with Roles table.
 * @author Iryna Siandzetskaya
 *
 */
public interface RoleService {

	/**
	 * gets all roles
	 * @return list of roles
	 */
	List<Role> getRoleList();
}
