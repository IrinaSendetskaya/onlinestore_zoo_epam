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
import by.htp.onlinestore.dao.ImageDao;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.util.constants.ImageFieldConstantDeclaration;

/**
 *Class provides operations for performing with Images table in database
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class ImageDaoDBImpl implements ImageDao {

	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT="INSERT INTO `Images`(`imageUrl`) VALUES (?)";
	private static final String SQL_UPDATE="UPDATE `Images` SET `imageUrl`=? WHERE `id`=?";
	private static final String SQL_DELETE="DELETE FROM `Images` WHERE `id`=?";
	private static final String SQL_READ_ID="SELECT `id`, `imageUrl` FROM `Images` WHERE `id`=?";
	private static final String SQL_READ_ALL="SELECT `id`, `imageUrl` FROM `Images`";
	
	private static final Logger logger=LoggerFactory.getLogger(BuyerDaoDBImpl.class);
	
	
	/**
	 * constructor without parameter
	 */
	public ImageDaoDBImpl() {
	
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#create(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public void create(Image entity) {

		connection = DAOFactory.getDao().getConnectionPool().getConnect();
		try(PreparedStatement ps=connection.prepareStatement(SQL_INSERT)
						){
			
			ps.setString(1, entity.getImageUrl());		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in create method of ImageDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#update(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public void update(Image entity) {

		connection = DAOFactory.getDao().getConnectionPool().getConnect();
		try(PreparedStatement ps=connection.prepareStatement(SQL_UPDATE)
						){
			
			ps.setString(1, entity.getImageUrl());
			ps.setInt(2, entity.getId());		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in update method of ImageDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#delete(by.htp.onlinestore.entity.EntityBase)
	 */
	@Override
	public void delete(Image entity) {

		connection = DAOFactory.getDao().getConnectionPool().getConnect();
		try(PreparedStatement ps=connection.prepareStatement(SQL_DELETE)
						){
			
			ps.setInt(1, entity.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in delete method of ImageDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
		}
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#read(int)
	 */
	@Override
	public Image read(int id) {
		
		connection = DAOFactory.getDao().getConnectionPool().getConnect();
		try(PreparedStatement ps=connection.prepareStatement(SQL_READ_ID)
						){
			
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			
			if(resultSet.next()) {
				return imageBuilder(resultSet);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in read method of ImageDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}		
		return null;
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.dao.BaseDao#readAll()
	 */
	@Override
	public List<Image> readAll() {
		
		List<Image> imageList = new ArrayList<>();
		connection = DAOFactory.getDao().getConnectionPool().getConnect();
		try(Statement st=connection.createStatement()
						){
			
			resultSet=st.executeQuery(SQL_READ_ALL);
			
			while(resultSet.next()) {
				imageList.add(imageBuilder(resultSet));
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in readall method of ImageDaoDBImpl class", e);
		} finally {
			DAOFactory.getDao().getConnectionPool().disconnect(connection);
			close(resultSet);
		}		
		return imageList;
	}

	
	/**
	 * get values from ResultSet and set them to Image object
	 * @param rs
	 * @return image
	 */
	private Image imageBuilder(ResultSet rs) {
		
		Image image;
		try {
			image = Image.newBuilder()
					.setId(rs.getInt(ImageFieldConstantDeclaration.REQUEST_PARAM_IMAGE_ID))
					.setImageUrl(rs.getString(ImageFieldConstantDeclaration.REQUEST_PARAM_IMAGE_URL))
					.build();
			
			return image;
			
		} catch (SQLException e) {
			logger.error("SQLException in build method of ImageDaoDBImpl class", e);
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
				logger.error("SQLException in close method rs of ImageDaoDBImpl class", e);
			}
		}
	}

}
