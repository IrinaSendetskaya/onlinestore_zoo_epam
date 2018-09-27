package by.htp.onlinestore.service;

import by.htp.onlinestore.service.impl.BasketServiceImpl;
import by.htp.onlinestore.service.impl.BuyerServiceImpl;
import by.htp.onlinestore.service.impl.GoodServiceImpl;
import by.htp.onlinestore.service.impl.ImageServiceImpl;
import by.htp.onlinestore.service.impl.MeasureServiceImpl;
import by.htp.onlinestore.service.impl.RoleServiceImpl;
import by.htp.onlinestore.service.impl.SectionServiceImpl;
import by.htp.onlinestore.service.impl.SpecificationGoodServiceImpl;

/**
 *  Class factory provides Service instance
 * @author Iryna Siandzetskaya
 *
 */
public class ServiceFactory {
	/**
	 * Service factory initialize once
	 */
    private static final ServiceFactory service=new ServiceFactory();        
    
    private final BuyerService buyerDAO;
    private final RoleService roleDAO;
    private final GoodService goodDAO;
    private final BasketService basketDAO;
    private final ImageService imageDAO;
    private final MeasureService measureDAO;
    private final SectionService sectionDAO;
    private final SpecificationGoodService specificationGoodDAO;

    /**
	 * getters
	 * @return instances
	 */
    public static ServiceFactory getService() {
		return service;
	}


	public BuyerService getBuyerDAO() {
		return buyerDAO;
	}


	public RoleService getRoleDAO() {
		return roleDAO;
	}


	public GoodService getGoodDAO() {
		return goodDAO;
	}


	public BasketService getBasketDAO() {
		return basketDAO;
	}


	public ImageService getImageDAO() {
		return imageDAO;
	}


	public MeasureService getMeasureDAO() {
		return measureDAO;
	}


	public SectionService getSectionDAO() {
		return sectionDAO;
	}


	public SpecificationGoodService getSpecificationGoodDAO() {
		return specificationGoodDAO;
	}

	/**
	 * Constructor for initialize all customs instances
	 */
	private ServiceFactory() {
        this.buyerDAO = new BuyerServiceImpl();
        this.roleDAO = new RoleServiceImpl();
        this.goodDAO = new GoodServiceImpl();
        this.basketDAO = new BasketServiceImpl();
        this.imageDAO=new ImageServiceImpl();
        this.measureDAO=new MeasureServiceImpl();
        this.sectionDAO=new SectionServiceImpl();
        this.specificationGoodDAO=new SpecificationGoodServiceImpl();
    }
}
