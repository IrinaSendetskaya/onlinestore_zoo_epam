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
import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.util.GoodFieldConstantDeclaration;

public class GoodDaoDBImpl implements GoodDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT = "INSERT INTO `Goods`(`price`, `fk_measures`, `fk_specificationGoods`) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE `Goods` SET `price`=?,`fk_measures`=?,`fk_specificationGoods`=? WHERE `id`=?";
	private static final String SQL_DELETE = "DELETE FROM `Goods` WHERE `id`=?";
	private static final String SQL_READ_ID = "SELECT `id`, `price`, `fk_measures`, `fk_specificationGoods` FROM `Goods` WHERE `id`=?";
	private static final String SQL_READ_ALL = "SELECT `id`, `price`, `fk_measures`, `fk_specificationGoods` FROM `Goods`";
	private static final String SQL_READ_SPECGOOD_ID = "SELECT `id`, `price`, `fk_measures`, `fk_specificationGoods` FROM `Goods` WHERE `fk_specificationGoods`=?";

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
	public List<Good> searchGoods(int specGoodId) {

		List<Good> goodList = new ArrayList<>();

		try (Connection connection = DBConnectionHelper.connect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_SPECGOOD_ID)) {

			ps.setInt(1, specGoodId);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				goodList.add(goodBuilder(resultSet));
			}

		} catch (SQLException e) {
			logger.error("SQLException in readwhere method of GoodDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return goodList;
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
