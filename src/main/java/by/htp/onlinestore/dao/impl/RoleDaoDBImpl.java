package by.htp.onlinestore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import by.htp.onlinestore.connection.DBConnectionHelper;
import by.htp.onlinestore.dao.RoleDao;
import by.htp.onlinestore.entity.Role;
import by.htp.onlinestore.util.RoleFieldConstantDeclaration;

public class RoleDaoDBImpl implements RoleDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT="INSERT INTO `Roles`(`role`) VALUES (?)";
	private static final String SQL_UPDATE="UPDATE `Roles` SET `role`=? WHERE `id`=?";
	private static final String SQL_DELETE="DELETE FROM `Roles` WHERE `id`=?";
	private static final String SQL_READ_ID="SELECT `id`, `role` FROM `Roles` WHERE `id`=?";
	private static final String SQL_READ_ALL="SELECT `id`, `role` FROM `Roles`";
	private static final String SQL_READ_WHERE="SELECT `id`, `role` FROM `Roles` WHERE ? ";
	
	private static final Logger logger=LoggerFactory.getLogger(BuyerDaoDBImpl.class);
	
	public RoleDaoDBImpl() {

	}

	@Override
	public void create(Role entity) {

		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_INSERT)
						){
			ps.setString(1, entity.getRole());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException in create method of RoleDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}

	}

	@Override
	public void update(Role entity) {

		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_UPDATE)
						){
			ps.setString(1, entity.getRole());
			ps.setInt(2, entity.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException in update method of RoleDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}

	}

	@Override
	public void delete(Role entity) {

		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_DELETE)
						){
			
			ps.setInt(1, entity.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in delete method of RoleDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public Role read(int id) {
		
		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_READ_ID)
						){
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			if(resultSet.next()) {
				return roleBuilder(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in read method of RoleDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		
		return null;
	}

	@Override
	public List<Role> readAll() {
		
		List<Role> roleList = new ArrayList<>();

		try(Connection connection=DBConnectionHelper.connect();
				Statement st=connection.createStatement()
						){
			resultSet=st.executeQuery(SQL_READ_ALL);
			while(resultSet.next()) {
				roleList.add(roleBuilder(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException in readall method of RoleDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		
		return roleList;
	}

	@Override
	public List<Role> getAll(String where) {
		
		List<Role> roleList = new ArrayList<>();
		
		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_READ_WHERE)
						){
			ps.setString(1, where);
			resultSet=ps.executeQuery();
			
			while(resultSet.next()) {
				roleList.add(roleBuilder(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException in readwhere method of RoleDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		
		return roleList;
	}
	
	private Role roleBuilder(ResultSet rs) {
		Role role;
		try {
			role = Role.newBuilder()
					.setId(rs.getInt(RoleFieldConstantDeclaration.REQUEST_PARAM_ROLE_ID))
					.setRole(rs.getString(RoleFieldConstantDeclaration.REQUEST_PARAM_ROLE_NAME))
					.build();
			
			return role;
		} catch (SQLException e) {
			logger.error("SQLException in build method of RoleDaoDBImpl class", e);
		}
		
		return null;
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of RoleDaoDBImpl class", e);
			}
		}
	}

}
