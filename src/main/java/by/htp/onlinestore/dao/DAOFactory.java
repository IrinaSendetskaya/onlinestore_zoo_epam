package by.htp.onlinestore.dao;

import by.htp.onlinestore.connection.ConnectionPool;
import by.htp.onlinestore.connection.IConnectionPool;
import by.htp.onlinestore.dao.impl.BasketDaoDBImpl;
import by.htp.onlinestore.dao.impl.BuyerDaoDBImpl;
import by.htp.onlinestore.dao.impl.GoodDaoDBImpl;
import by.htp.onlinestore.dao.impl.ImageDaoDBImpl;
import by.htp.onlinestore.dao.impl.MeasureDaoDBImpl;
import by.htp.onlinestore.dao.impl.RoleDaoDBImpl;
import by.htp.onlinestore.dao.impl.SectionDaoDBImpl;
import by.htp.onlinestore.dao.impl.SpecificationGoodDaoDBImpl;

/**
 * Class factory provides DAO instance
 * @author Iryna Siandzetskaya
 *
 */
public class DAOFactory {

	/**
	 * DAO factory initialize once
	 */
	private static final DAOFactory dao=new DAOFactory(); 

	private final IConnectionPool connectionPool;
	private final BuyerDao buyerDAO;
	private final RoleDao roleDAO;
	private final GoodDao goodDAO;
	private final BasketDao basketDAO;
	private final ImageDao imageDAO;
	private final MeasureDao measureDAO;
	private final SectionDao sectionDAO;
	private final SpecificationGoodDao specificationGoodDAO;

	
	
	/**
	 * getters
	 * @return instances
	 */
	public static DAOFactory getDao() {
		return dao;
	}


	public IConnectionPool getConnectionPool() {
		return connectionPool;
	}
	

	public BuyerDao getBuyerDAO() {
		return buyerDAO;
	}


	public RoleDao getRoleDAO() {
		return roleDAO;
	}


	public GoodDao getGoodDAO() {
		return goodDAO;
	}


	public BasketDao getBasketDAO() {
		return basketDAO;
	}


	public ImageDao getImageDAO() {
		return imageDAO;
	}


	public MeasureDao getMeasureDAO() {
		return measureDAO;
	}


	public SectionDao getSectionDAO() {
		return sectionDAO;
	}


	public SpecificationGoodDao getSpecificationGoodDAO() {
		return specificationGoodDAO;
	}


	/**
	 * Constructor for initialize all customs instances
	 */
	private DAOFactory() {
		this.connectionPool=ConnectionPool.getInstance();
		this.buyerDAO = new BuyerDaoDBImpl();
		this.roleDAO = new RoleDaoDBImpl();
		this.goodDAO = new GoodDaoDBImpl();
		this.basketDAO = new BasketDaoDBImpl();
		this.imageDAO = new ImageDaoDBImpl();
		this.measureDAO = new MeasureDaoDBImpl();
		this.sectionDAO = new SectionDaoDBImpl();
		this.specificationGoodDAO = new SpecificationGoodDaoDBImpl();
	}

}
