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

public class DAOFactory {

	private static DAOFactory dao; // singleton

	public IConnectionPool connectionPool;
	public final BuyerDao buyerDAO;
	public final RoleDao roleDAO;
	public final GoodDao goodDAO;
	public final BasketDao basketDAO;
	public final ImageDao imageDAO;
	public final MeasureDao measureDAO;
	public final SectionDao sectionDAO;
	public final SpecificationGoodDao specificationGoodDAO;

	public DAOFactory() {
		connectionPool = new ConnectionPool();
		this.buyerDAO = new BuyerDaoDBImpl();
		this.roleDAO = new RoleDaoDBImpl();
		this.goodDAO = new GoodDaoDBImpl();
		this.basketDAO = new BasketDaoDBImpl();
		this.imageDAO = new ImageDaoDBImpl();
		this.measureDAO = new MeasureDaoDBImpl();
		this.sectionDAO = new SectionDaoDBImpl();
		this.specificationGoodDAO = new SpecificationGoodDaoDBImpl();
	}

	public static DAOFactory getDAO() {
		if (dao == null) {
			synchronized (DAOFactory.class) {
				if (dao == null) {
					dao = new DAOFactory();
				}
			}
		}
		return dao;
	}
}
