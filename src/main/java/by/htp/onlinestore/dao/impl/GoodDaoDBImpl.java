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
import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.constants.GoodFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ImageFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.MeasureFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.SpecificationGoodFieldConstantDeclaration;

public class GoodDaoDBImpl implements GoodDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT = "INSERT INTO `Goods`(`price`, `fk_measures`, `fk_specificationGoods`) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE `Goods` SET `price`=?,`fk_measures`=?,`fk_specificationGoods`=? WHERE `id`=?";
	private static final String SQL_DELETE = "DELETE FROM `Goods` WHERE `id`=?";
	private static final String SQL_READ_ID = "SELECT `id`, `price`, `fk_measures`, `fk_specificationGoods` FROM `Goods` WHERE `id`=?";
	private static final String SQL_READ_ALL = "SELECT `id`, `price`, `fk_measures`, `fk_specificationGoods` FROM `Goods`";
	private static final String SQL_READ_SEARCH = "SELECT `Goods`.`id`, `name`, `Images`.`imageUrl`, `price`, `size`,`fk_measures`, `fk_specificationGoods` FROM `Goods` "
			+ "JOIN `Measures` ON `Goods`.`fk_measures` = `Measures`.`id` JOIN `SpecificationGoods` "
			+ "ON `fk_specificationGoods` = `SpecificationGoods`.`id` JOIN `Images` ON `SpecificationGoods`.`fk_images` = `Images`.`id`"
			+ " WHERE `name` LIKE ?";
	private static final String SQL_READ_SEARCH_PAGES = "SELECT `Goods`.`id`, `name`, `Images`.`imageUrl`, `price`, `size`,`fk_measures`, `fk_specificationGoods` FROM `Goods` "
			+ "JOIN `Measures` ON `Goods`.`fk_measures` = `Measures`.`id` JOIN `SpecificationGoods` "
			+ "ON `fk_specificationGoods` = `SpecificationGoods`.`id` JOIN `Images` ON `SpecificationGoods`.`fk_images` = `Images`.`id`"
			+ " WHERE `name` LIKE ? LIMIT ?, ?";
	private static final String SQL_READ_ALL_FOR_INDEX = "SELECT `Goods`.`id`, `name`, `Images`.`imageUrl`, `price`, `size`,"
			+ "`fk_measures`, `fk_specificationGoods` FROM `Goods` JOIN `Measures` ON `Goods`.`fk_measures` = `Measures`.`id`"
			+ " JOIN `SpecificationGoods` ON `fk_specificationGoods` = `SpecificationGoods`.`id` JOIN `Images` "
			+ "ON `SpecificationGoods`.`fk_images` = `Images`.`id`";
	private static final String SQL_READ_ALL_FOR_INDEX_PAGINATION = "SELECT `Goods`.`id`, `name`, `Images`.`imageUrl`, `price`, `size`,`fk_measures`, `fk_specificationGoods` "
			+ "FROM `Goods` JOIN `Measures` ON `Goods`.`fk_measures` = `Measures`.`id` JOIN `SpecificationGoods` "
			+ "ON `fk_specificationGoods` = `SpecificationGoods`.`id` JOIN `Images` ON `SpecificationGoods`.`fk_images` = `Images`.`id`"
			+ " LIMIT ?, ?";
	private static final String SQL_INSERT_IMAGE="INSERT INTO `Images`(`imageUrl`) VALUES (?)";
	private static final String SQL_INSERT_SPEC_GOOD = "INSERT INTO `SpecificationGoods`(`name`, `description`, `fk_sections`, `fk_images`) VALUES (?,?,?,?)";
	
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerDaoDBImpl.class);

	public GoodDaoDBImpl() {

	}

	@Override
	public void create(Good entity) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {

			ps.setBigDecimal(1, entity.getPrice());
			ps.setInt(2, entity.getMeasureId());
			ps.setInt(3, entity.getSpecificationGoodId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in create method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}
	
	@Override
	public void createNewGood(Good good, SpecificationGood specificationGood, Image image, int measureId,int sectionId) {

		connection = DAOFactory.getDao().getConnectionPool().getConnect();
		PreparedStatement createImage = null;
		PreparedStatement createSpecGood = null;
		PreparedStatement createGood = null;
		try {
			connection.setAutoCommit(false);
			createImage = connection.prepareStatement(SQL_INSERT_IMAGE, Statement.RETURN_GENERATED_KEYS);
			createSpecGood = connection.prepareStatement(SQL_INSERT_SPEC_GOOD, Statement.RETURN_GENERATED_KEYS);
			createGood = connection.prepareStatement(SQL_INSERT);

			createImage.setString(1, image.getImageUrl());

			if (createImage.executeUpdate() > 0) {
				ResultSet rs = createImage.getGeneratedKeys();
				if (rs.next())
					image.setId(rs.getInt(1));
			} else
				throw new SQLException();
			
			createSpecGood.setString(1, specificationGood.getName());
			createSpecGood.setString(2, specificationGood.getDescription());
			createSpecGood.setInt(3, sectionId);
			createSpecGood.setInt(4, image.getId());

			if (createSpecGood.executeUpdate() > 0) {
				ResultSet rs = createSpecGood.getGeneratedKeys();
				if (rs.next())
					specificationGood.setId(rs.getInt(1));
			} else
				throw new SQLException();
			
			createGood.setBigDecimal(1, good.getPrice());
			createGood.setInt(2, measureId);
			createGood.setInt(3, specificationGood.getId());
			createGood.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			logger.error("SQLException in createNewGood method of GoodDaoImpl class", e);
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					logger.error("Transaction can't be rolled back", ex);
				}
			}
		} finally {	
			try {
				if (createImage != null) {
					createImage.close();
				}
				if (createSpecGood != null) {
					createSpecGood.close();
				}
				if (createGood != null) {
					createGood.close();
				}
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				logger.error("Connection can't be autocommit", e);
			}
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
		
	}


	@Override
	public void update(Good entity) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {

			ps.setBigDecimal(1, entity.getPrice());
			ps.setInt(2, entity.getMeasureId());
			ps.setInt(3, entity.getSpecificationGoodId());
			ps.setInt(4, entity.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in update method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	@Override
	public void delete(Good entity) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {

			ps.setInt(1, entity.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in delete method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	@Override
	public Good read(int id) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				return goodBuilder(resultSet);
			}

		} catch (SQLException e) {
			logger.error("SQLException in read method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return null;
	}

	@Override
	public List<Good> readAll() {

		List<Good> goodList = new ArrayList<>();

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				Statement st = connection.createStatement()) {

			resultSet = st.executeQuery(SQL_READ_ALL);

			while (resultSet.next()) {
				goodList.add(goodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in readall method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return goodList;
	}

	@Override
	public List<Good> searchGoods(String searchInput) {

		List<Good> goodList = new ArrayList<>();

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_SEARCH)) {

			ps.setString(1, searchInput);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodList.add(goodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in searchGoods method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return goodList;
	}
	@Override
	public List<GoodListForJsp> searchGoodsWithPages(String searchInput,int beginGood,int endGood) {

		List<GoodListForJsp> goodListForJsp = new ArrayList<>();

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_SEARCH_PAGES)) {

			ps.setString(1, searchInput);
			ps.setInt(2, beginGood);
			ps.setInt(3, endGood);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodListForJsp.add(goodListBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in searchGoodsWithPages method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return goodListForJsp;
	}

	@Override
	public List<GoodListForJsp> findAllGoodsJoinTables() {
		
		List<GoodListForJsp> goodList = new ArrayList<>();

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ALL_FOR_INDEX)) {

			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodList.add(goodListBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in findAllGoodsJoinTables method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return goodList;
	}
	
	@Override
	public List <GoodListForJsp> findAllGoodsJoinTablesWithPages(int beginGood,int endGood) {
		
		List<GoodListForJsp> goodListForJsp = new ArrayList<>();

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ALL_FOR_INDEX_PAGINATION)) {

			ps.setInt(1, beginGood);
			ps.setInt(2, endGood);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodListForJsp.add(goodListBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in findAllGoodsJoinTablesWithPages method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return goodListForJsp;
	}

	private Good goodBuilder(ResultSet rs) {

		Good good;
		try {
			good = Good.newBuilder().setId(rs.getInt(GoodFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID))
					.setPrice(rs.getBigDecimal(GoodFieldConstantDeclaration.REQUEST_PARAM_PRICE))
					.setMeasureId(rs.getInt(GoodFieldConstantDeclaration.REQUEST_PARAM_MEASURE_ID))
					.setSpecificationGoodId(rs.getInt(GoodFieldConstantDeclaration.REQUEST_PARAM_SPECIFICATION_GOOD_ID))
					.build();

			return good;

		} catch (SQLException e) {
			logger.error("SQLException in build method of GoodDaoDBImpl class", e);
		}
		return null;
	}
	private GoodListForJsp goodListBuilder(ResultSet rs) {
		
		GoodListForJsp goodListForJsp;
		try {
			String temp=FormUtil.fixGoogleDriveUrl(rs.getString(ImageFieldConstantDeclaration.REQUEST_PARAM_IMAGE_URL));
			goodListForJsp = GoodListForJsp.newBuilder()
					.setId(rs.getInt(GoodFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID))
					.setName(rs.getString(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_NAME))
					.setImageUrl(temp)
					.setPrice(rs.getBigDecimal(GoodFieldConstantDeclaration.REQUEST_PARAM_PRICE))
					.setSize(rs.getString(MeasureFieldConstantDeclaration.REQUEST_PARAM_SIZE))
					.setMeasureId(rs.getInt(GoodFieldConstantDeclaration.REQUEST_PARAM_MEASURE_ID))
					.setSpecificationGoodId(rs.getInt(GoodFieldConstantDeclaration.REQUEST_PARAM_SPECIFICATION_GOOD_ID))
					.build();
			return goodListForJsp;

		} catch (SQLException e) {
			logger.error("SQLException in imagebuild method of GoodDaoDBImpl class", e);
		}
		return null;
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of GoodDaoDBImpl class", e);
			}
		}
	}

}
