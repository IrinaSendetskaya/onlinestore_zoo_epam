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
import by.htp.onlinestore.dao.MeasureDao;
import by.htp.onlinestore.entity.Measure;
import by.htp.onlinestore.util.constants.MeasureFieldConstantDeclaration;

public class MeasureDaoDBImpl implements MeasureDao {
	
	private Connection connection;
	private ResultSet resultSet = null;

	private static final String SQL_INSERT="INSERT INTO `Measures`(`size`) VALUES (?)";
	private static final String SQL_UPDATE="UPDATE `Measures` SET `size`=? WHERE `id`=?";
	private static final String SQL_DELETE="DELETE FROM `Measures` WHERE `id`=?";
	private static final String SQL_READ_ID="SELECT `id`, `size` FROM `Measures` WHERE `id`=?";
	private static final String SQL_READ_ALL="SELECT `id`, `size` FROM `Measures`";
	
	private static final Logger logger=LoggerFactory.getLogger(BuyerDaoDBImpl.class);
	
	

	public MeasureDaoDBImpl() {
	
	}

	@Override
	public void create(Measure entity) {

		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_INSERT)
						){
			
			ps.setString(1, entity.getSize());		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in create method of MeasureDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public void update(Measure entity) {

		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_UPDATE)
						){
			
			ps.setString(1, entity.getSize());
			ps.setInt(2, entity.getId());		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in update method of MeasureDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public void delete(Measure entity) {

		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_DELETE)
						){
			
			ps.setInt(1, entity.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in delete method of MeasureDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
		}
	}

	@Override
	public Measure read(int id) {
		
		try(Connection connection=DBConnectionHelper.connect();
				PreparedStatement ps=connection.prepareStatement(SQL_READ_ID)
						){
			
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			
			if(resultSet.next()) {
				return measureBuilder(resultSet);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in read method of MeasureDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}		
		return null;
	}

	@Override
	public List<Measure> readAll() {
		
		List<Measure> measureList = new ArrayList<>();

		try(Connection connection=DBConnectionHelper.connect();
				Statement st=connection.createStatement()
						){
			
			resultSet=st.executeQuery(SQL_READ_ALL);
			
			while(resultSet.next()) {
				measureList.add(measureBuilder(resultSet));
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in readall method of MeasureDaoDBImpl class", e);
		} finally {
			DBConnectionHelper.disconnect(connection);
			close(resultSet);
		}		
		return measureList;
	}

	
	private Measure measureBuilder(ResultSet rs) {
		
		Measure measure;
		try {
			measure = Measure.newBuilder()
					.setId(rs.getInt(MeasureFieldConstantDeclaration.REQUEST_PARAM_MEASURE_ID))
					.setSize(rs.getString(MeasureFieldConstantDeclaration.REQUEST_PARAM_SIZE))
					.biuld();
			
			return measure;
			
		} catch (SQLException e) {
			logger.error("SQLException in build method of MeasureDaoDBImpl class", e);
		}		
		return null;
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("SQLException in close method rs of MeasureDaoDBImpl class", e);
			}
		}
	}

}
