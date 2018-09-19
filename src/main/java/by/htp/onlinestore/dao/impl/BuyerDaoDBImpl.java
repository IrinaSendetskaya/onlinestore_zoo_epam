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

import by.htp.onlinestore.dao.BuyerDao;
import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;

public class BuyerDaoDBImpl implements BuyerDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT = "INSERT INTO `Buyers`(`nickname`, `password`, `email`, "
			+ "`mobile`, `address`, `fk_roles`) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE `Buyers` SET `nickname`=?,`password`=?,`email`=?,"
			+ "`mobile`=?,`address`=?,`fk_roles`=? WHERE `id`=?";
	private static final String SQL_DELETE = "DELETE FROM `Buyers` WHERE `id`=?";
	private static final String SQL_READ_ID = "SELECT `id`, `nickname`, `password`, `email`, "
			+ "`mobile`, `address`, `fk_roles` FROM `Buyers` WHERE `id`=?";
	private static final String SQL_READ_ALL = "SELECT `id`, `nickname`, `password`, `email`, "
			+ "`mobile`, `address`, `fk_roles` FROM `Buyers` ";
	private static final String SQL_READ_BY_LOGIN_AND_PASSWORD = "SELECT `id`, `nickname`, `password`, `email`, "
			+ "`mobile`, `address`, `fk_roles`  FROM `Buyers` WHERE `nickname`=? AND `password`=?";

	private static final Logger logger = LoggerFactory.getLogger(BuyerDaoDBImpl.class);

	@Override
	public void create(Buyer buyer) {
//		ResultSet keys;
//		buyer.newBuilder().setId(0);

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, buyer.getNickname());
			ps.setString(2, buyer.getPassword());
			ps.setString(3, buyer.getEmail());
			ps.setString(4, buyer.getMobile());
			ps.setString(5, buyer.getAddress());
			ps.setInt(6, buyer.getRoleId());
			
			ps.executeUpdate();

//			if (1 == ps.executeUpdate()) {
//				keys = ps.getGeneratedKeys();
//				if (keys.next()) {
//					buyer.newBuilder().setId(keys.getInt(1));
//				}
//			}

		} catch (SQLException e) {
			logger.error("SQLException in create method of BuyerDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}

	}

	@Override
	public void update(Buyer buyer) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {

			ps.setString(1, buyer.getNickname());
			ps.setString(2, buyer.getPassword());
			ps.setString(3, buyer.getEmail());
			ps.setString(4, buyer.getMobile());
			ps.setString(5, buyer.getAddress());
			ps.setInt(6, buyer.getRoleId());
			ps.setInt(7, buyer.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in update method of BuyerDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}

	}

	@Override
	public void delete(Buyer buyer) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {

			ps.setInt(1, buyer.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLException in delete method of BuyerDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}

	}

	@Override
	public Buyer read(int id) {

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_ID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return buyerBuilder(resultSet);
			}

		} catch (SQLException e) {
			logger.error("SQLException in read method of BuyerDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return null;
	}

	@Override
	public List<Buyer> readAll() {

		List<Buyer> buyerList = new ArrayList<>();

		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(SQL_READ_ALL);

			while (resultSet.next()) {
				buyerList.add(buyerBuilder(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException in readall method of BuyerDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}

		return buyerList;
	}

	@Override
	public Buyer read(final String login, final String password) {
		
		try (Connection connection = DAOFactory.getDao().getConnectionPool().getConnect();
				PreparedStatement ps = connection.prepareStatement(SQL_READ_BY_LOGIN_AND_PASSWORD)) {

			ps.setString(1, login);
			ps.setString(2, password);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return buyerBuilder(resultSet);
			}

		} catch (SQLException e) {
			logger.error("SQLException in read method of BuyerDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}
		return null;
	}

	private Buyer buyerBuilder(ResultSet rs) {
		Buyer buyer;
		try {
			buyer = Buyer.newBuilder().setId(rs.getInt(BuyerFieldConstantDeclaration.REQUEST_PARAM_BUYER_ID))
					.setNickname(rs.getString(BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN))
					.setPassword(rs.getString(BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS))
					.setEmail(rs.getString(BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL))
					.setMobile(rs.getString(BuyerFieldConstantDeclaration.REQUEST_PARAM_MOBILE))
					.setAddress(rs.getString(BuyerFieldConstantDeclaration.REQUEST_PARAM_ADDRESS))
					.setRoleId(rs.getInt(BuyerFieldConstantDeclaration.REQUEST_PARAM_ROLE_ID)).build();

			return buyer;
		} catch (SQLException e) {
			logger.error("SQLException in build method of BuyerDaoDBImpl class", e);
		}

		return null;
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of BuyerDaoDBImpl class", e);
			}
		}
	}

}
