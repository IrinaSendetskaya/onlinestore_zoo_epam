package by.htp.onlinestore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;

import by.htp.onlinestore.connection.DBConnectionHelper;
import by.htp.onlinestore.dao.BasketDao;
import by.htp.onlinestore.entity.Basket;

public class BasketDaoDBImpl implements BasketDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT = "INSERT INTO `Baskets`(`quantity`, "
			+ "`sum`, `dateOrder`, `statusOrder`, `fk_buyers`, `fk_goods`) VALUES " + "(?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE `Baskets` SET `quantity`=?,"
			+ "`sum`=?,`dateOrder`=?,`statusOrder`=?,`fk_buyers`=?,`fk_goods`=? WHERE `id`=?";
	private static final String SQL_DELETE = "DELETE FROM `Baskets` WHERE `id`=?";
	private static final String SQL_READ_ID = "SELECT `id`, `quantity`, `sum`,"
			+ " `dateOrder`, `statusOrder`, `fk_buyers`, `fk_goods` FROM `Baskets` WHERE `id`=?";
	private static final String SQL_READ_ALL = "SELECT `id`, `quantity`, `sum`, `dateOrder`, "
			+ "`statusOrder`, `fk_buyers`, `fk_goods` FROM `Baskets` ";
	private static final String SQL_READ_WHERE = "SELECT `id`, `quantity`, `sum`, `dateOrder`, "
			+ "`statusOrder`, `fk_buyers`, `fk_goods` FROM `Baskets` ? ";

	private static final Logger logger = LoggerFactory.getLogger(BasketDaoDBImpl.class);

	@Override
	public void create(Basket basket) {

		connection = DBConnectionHelper.connect();
		try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {

			ps.setInt(1, basket.getQuantity());
			ps.setBigDecimal(2, basket.getSum());
			ps.setDate(3, basket.getDateOrders());
			ps.setString(4, basket.getStatusOrders());
			ps.setInt(5, basket.getBuyerId());
			ps.setInt(6, basket.getGoodId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in create method of BasketDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}

	}

	@Override
	public void update(Basket basket) {

		connection = DBConnectionHelper.connect();
		try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {

			ps.setInt(1, basket.getQuantity());
			ps.setBigDecimal(2, basket.getSum());
			ps.setDate(3, basket.getDateOrders());
			ps.setString(4, basket.getStatusOrders());
			ps.setInt(5, basket.getBuyerId());
			ps.setInt(6, basket.getGoodId());
			ps.setInt(7, basket.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in update method of BasketDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}

	}

	@Override
	public void delete(Basket basket) {

		connection = DBConnectionHelper.connect();
		try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {

			ps.setInt(1, basket.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in delete method of BasketDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}

	}

	@Override
	public Basket read(int id) {
		
		connection = DBConnectionHelper.connect();
		try (PreparedStatement ps = connection.prepareStatement(SQL_READ_ID)) {

			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			if(resultSet.next()) {
				return basketBuilder(resultSet);
			}

		} catch (SQLException e) {
			logger.error("SQLException in read method of BasketDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return null;
	}

	@Override
	public List<Basket> readAll() {

		List<Basket> basketList = new ArrayList<>();

		try (Connection connection = DBConnectionHelper.connect(); 
				Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(SQL_READ_ALL);

			while (resultSet.next()) {
				basketList.add(basketBuilder(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException in readall method of BasketDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}

		return basketList;
	}

	@Override
	public List<Basket> getAll(String where) {

		List<Basket> basketList = new ArrayList<>();

		connection = DBConnectionHelper.connect();
		try (PreparedStatement ps = connection.prepareStatement(SQL_READ_WHERE)){

			ps.setString(1, where);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				basketList.add(basketBuilder(resultSet));

			}
		} catch (SQLException e) {
			logger.error("SQLException in getallWhere method of BasketDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}
		return basketList;
	}

	private Basket basketBuilder(ResultSet rs) {
		Basket basket;
		try {
			basket=Basket.newBuilder()
					.setId(rs.getInt("id"))
					.setQuantity(rs.getInt("quantity"))
					.setSum(rs.getBigDecimal("sum"))
			        .setDateOrders(rs.getDate("dateOrder"))
			        .setStatusOrders(rs.getString("statusOrder"))
			        .setBuyerId(rs.getInt("fk_buyers"))
			        .setGoodId(rs.getInt("fk_goods"))
			        .build();
			
			return basket;
		} catch (SQLException e) {
			logger.error("SQLException in build method of BasketDaoDBImpl class", e);
		}
		return null;
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of BasketDaoDBImpl class", e);
			}
		}
	}

}
