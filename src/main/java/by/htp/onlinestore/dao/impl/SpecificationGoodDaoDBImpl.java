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

	public SpecificationGoodDaoDBImpl() {

	}

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
