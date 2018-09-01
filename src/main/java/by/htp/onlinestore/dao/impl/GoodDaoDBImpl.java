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
import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
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
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerDaoDBImpl.class);

	public GoodDaoDBImpl() {

	}

	@Override
	public void create(Good entity) {

		try (Connection connection = DBConnectionHelper.connect();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {

			ps.setBigDecimal(1, entity.getPrice());
			ps.setInt(2, entity.getMeasureId());
			ps.setInt(3, entity.getSpecificationGoodId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in create method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public void update(Good entity) {

		try (Connection connection = DBConnectionHelper.connect();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {

			ps.setBigDecimal(1, entity.getPrice());
			ps.setInt(2, entity.getMeasureId());
			ps.setInt(3, entity.getSpecificationGoodId());
			ps.setInt(4, entity.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in update method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public void delete(Good entity) {

		try (Connection connection = DBConnectionHelper.connect();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {

			ps.setInt(1, entity.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in delete method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public Good read(int id) {

		try (Connection connection = DBConnectionHelper.connect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				return goodBuilder(resultSet);
			}

		} catch (SQLException e) {
			logger.error("SQLException in read method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return null;
	}

	@Override
	public List<Good> readAll() {

		List<Good> goodList = new ArrayList<>();

		try (Connection connection = DBConnectionHelper.connect(); Statement st = connection.createStatement()) {

			resultSet = st.executeQuery(SQL_READ_ALL);

			while (resultSet.next()) {
				goodList.add(goodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in readall method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return goodList;
	}

	@Override
	public List<Good> searchGoods(String searchInput) {

		List<Good> goodList = new ArrayList<>();

		try (Connection connection = DBConnectionHelper.connect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_SEARCH)) {

			ps.setString(1, searchInput);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodList.add(goodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in searchGoods method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return goodList;
	}
	@Override
	public List<GoodListForJsp> searchGoodsWithPages(String searchInput,int beginGood,int endGood) {

		List<GoodListForJsp> goodListForJsp = new ArrayList<>();

		try (Connection connection = DBConnectionHelper.connect();
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
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return goodListForJsp;
	}

	@Override
	public List<GoodListForJsp> findAllGoodsJoinTables() {
		
		List<GoodListForJsp> goodList = new ArrayList<>();

		try (Connection connection = DAOFactory.getDAO().connectionPool.getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ALL_FOR_INDEX)) {

			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodList.add(goodListBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in findAllGoodsJoinTables method of GoodDaoDBImpl class", e);
		} finally {
			DAOFactory.getDAO().connectionPool.disconnect(connection);
			close(resultSet);
		}
		return goodList;
	}
	
	@Override
	public List <GoodListForJsp> findAllGoodsJoinTablesWithPages(int beginGood,int endGood) {
		
		List<GoodListForJsp> goodListForJsp = new ArrayList<>();

		try (Connection connection = DAOFactory.getDAO().connectionPool.getConnect();
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
			DAOFactory.getDAO().connectionPool.disconnect(connection);
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
