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

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.dao.SectionDao;
import by.htp.onlinestore.entity.Section;
import by.htp.onlinestore.util.constants.SectionFieldConstantDeclaration;

public class SectionDaoDBImpl implements SectionDao {
	
	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT="INSERT INTO `Sections`(`section`) VALUES (?)";
	private static final String SQL_UPDATE="UPDATE `Sections` SET `section`=? WHERE `id`=?";
	private static final String SQL_DELETE="DELETE FROM `Sections` WHERE `id`=?";
	private static final String SQL_READ_ID="SELECT `id`, `section` FROM `Sections` WHERE `id`=?";
	private static final String SQL_READ_ALL="SELECT `id`, `section` FROM `Sections`";
	
	private static final Logger logger=LoggerFactory.getLogger(BuyerDaoDBImpl.class);

	public SectionDaoDBImpl() {
	
	}

	@Override
	public void create(Section entity) {

		try(Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps=connection.prepareStatement(SQL_INSERT)
						){
			ps.setString(1, entity.getSectionTitle());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException in create method of SectionDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}

	}

	@Override
	public void update(Section entity) {

		try(Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps=connection.prepareStatement(SQL_UPDATE)
						){
			ps.setString(1, entity.getSectionTitle());
			ps.setInt(2, entity.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException in update method of SectionDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}

	}

	@Override
	public void delete(Section entity) {

		try(Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps=connection.prepareStatement(SQL_DELETE)
						){
			
			ps.setInt(1, entity.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in delete method of SectionDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	@Override
	public Section read(int id) {
		
		try(Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps=connection.prepareStatement(SQL_READ_ID)
						){
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			if(resultSet.next()) {
				return sectionBuilder(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in read method of SectionDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		
		return null;
	}

	@Override
	public List<Section> readAll() {
		
		List<Section> sectionList = new ArrayList<>();

		try(Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				Statement st=connection.createStatement()
						){
			resultSet=st.executeQuery(SQL_READ_ALL);
			while(resultSet.next()) {
				sectionList.add(sectionBuilder(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException in readall method of SectionDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		
		return sectionList;
	}

	
	private Section sectionBuilder(ResultSet rs) {
		Section section;
		try {
			section = Section.newBuilder()
					.setId(rs.getInt(SectionFieldConstantDeclaration.REQUEST_PARAM_SECTION_ID))
					.setSectionTitle(rs.getString(SectionFieldConstantDeclaration.REQUEST_PARAM_SECTION_NAME))
					.build();
			
			return section;
		} catch (SQLException e) {
			logger.error("SQLException in build method of SectionDaoDBImpl class", e);
		}
		
		return null;
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of SectionDaoDBImpl class", e);
			}
		}
	}

}
