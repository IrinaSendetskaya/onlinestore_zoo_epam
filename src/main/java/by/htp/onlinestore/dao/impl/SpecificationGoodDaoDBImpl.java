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
import by.htp.onlinestore.dao.SpecificationGoodDao;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.util.constants.SpecificationGoodFieldConstantDeclaration;

/**
 * Class provides operations for performing with SpecificationGoods table in database
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class SpecificationGoodDaoDBImpl implements SpecificationGoodDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT = "INSERT INTO `SpecificationGoods`(`name`, `description`, `fk_sections`, `fk_images`) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE `SpecificationGoods` SET `name`=?,`description`=?,`fk_sections`=?,`fk_images`=? WHERE `id`=?";
	private static final String SQL_DELETE = "DELETE FROM `SpecificationGoods` WHERE `id`=?";
	private static final String SQL_READ_ID = "SELECT `id`, `name`, `description`, `fk_sections`, `fk_images` FROM `SpecificationGoods` WHERE `id`=?";
	private static final String SQL_READ_ALL = "SELECT `id`, `name`, `description`, `fk_sections`, `fk_images` FROM `SpecificationGoods`";
	private static final String SQL_READ_SEARCH = "SELECT `id`, `name`, `description`, `fk_sections`, `fk_images` FROM `SpecificationGoods` WHERE `name` LIKE ?";

	private static final Logger logger = LoggerFactory.getLogger(BuyerDaoDBImpl.class);

	/**
	 * constructor without parameter
	 */
	public SpecificationGoodDaoDBImpl() {

	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#create(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public void create(SpecificationGood entity) {

		try (Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {

			ps.setString(1, entity.getName());
			ps.setString(2, entity.getDescription());
			ps.setInt(3, entity.getSectionId());
			ps.setInt(4, entity.getImageId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in create method of SpecificationGoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#update(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public void update(SpecificationGood entity) {

		try (Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {

			ps.setString(1, entity.getName());
			ps.setString(2, entity.getDescription());
			ps.setInt(3, entity.getSectionId());
			ps.setInt(4, entity.getImageId());
			ps.setInt(5, entity.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in update method of SpecificationGoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#delete(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public void delete(SpecificationGood entity) {

		try (Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {

			ps.setInt(1, entity.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in delete method of SpecificationGoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}
	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#read(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public SpecificationGood read(int id) {

		try (Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				return specificationGoodBuilder(resultSet);
			}

		} catch (SQLException e) {
			logger.error("SQLException in read method of SpecificationGoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#readAll(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public List<SpecificationGood> readAll() {

		List<SpecificationGood> specificationGoodList = new ArrayList<>();

		try (Connection connection=DAOFactory.getDao().getConnectionPool().getConnect(); Statement st = connection.createStatement()) {

			resultSet = st.executeQuery(SQL_READ_ALL);

			while (resultSet.next()) {
				specificationGoodList.add(specificationGoodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in readall method of SpecificationGoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return specificationGoodList;
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#searchGoods(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public List<SpecificationGood> searchGoods(String searchInput) {
		
		List<SpecificationGood> specificationGoodList = new ArrayList<>();

		try (Connection connection=DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_SEARCH)) {

			ps.setString(1, searchInput);
			
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				specificationGoodList.add(specificationGoodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in readwhere method of SpecificationGoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return specificationGoodList;
	}

	/**
	 * get values from ResultSet and set them to SpecificationGood object
	 * @param rs
	 * @return specificationGood
	 */
	private SpecificationGood specificationGoodBuilder(ResultSet rs) {

		SpecificationGood specificationGood;
		try {
			specificationGood = SpecificationGood.newBuilder()
					.setId(rs.getInt(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_SPEC_GOOD_ID))
					.setName(rs.getString(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_NAME))
					.setDescription(rs.getString(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_DESCRIPTION))
					.setSectionId(rs.getInt(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_SECTION_ID))
					.setImageId(rs.getInt(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_IMAGE_ID)).build();

			return specificationGood;

		} catch (SQLException e) {
			logger.error("SQLException in build method of SpecificationGoodDaoDBImpl class", e);
		}
		return null;
	}

	/**
	 * it closes resources ResultSet
	 * @param rs
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of SpecificationGoodDaoDBImpl class", e);
			}
		}
	}

}
