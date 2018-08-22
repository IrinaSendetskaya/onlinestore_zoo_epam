package by.htp.onlinestore.service;

import by.htp.onlinestore.service.impl.BasketServiceImpl;
import by.htp.onlinestore.service.impl.BuyerServiceImpl;
import by.htp.onlinestore.service.impl.GoodServiceImpl;
import by.htp.onlinestore.service.impl.ImageServiceImpl;
import by.htp.onlinestore.service.impl.MeasureServiceImpl;
import by.htp.onlinestore.service.impl.RoleServiceImpl;
import by.htp.onlinestore.service.impl.SectionServiceImpl;
import by.htp.onlinestore.service.impl.SpecificationGoodServiceImpl;

public class ServiceFactory {

    private static ServiceFactory service;        //singleton

    public final BuyerService buyerDAO;
    public final RoleService roleDAO;
    public final GoodService goodDAO;
    public final BasketService basketDAO;
    public final ImageService imageDAO;
    public final MeasureService measureDAO;
    public final SectionService sectionDAO;
    public final SpecificationGoodService specificationGoodDAO;

    public ServiceFactory() {
        this.buyerDAO = new BuyerServiceImpl();
        this.roleDAO = new RoleServiceImpl();
        this.goodDAO = new GoodServiceImpl();
        this.basketDAO = new BasketServiceImpl();
        this.imageDAO=new ImageServiceImpl();
        this.measureDAO=new MeasureServiceImpl();
        this.sectionDAO=new SectionServiceImpl();
        this.specificationGoodDAO=new SpecificationGoodServiceImpl();
    }

    public static ServiceFactory getService()
    {
        if (service==null)
        {
            synchronized (ServiceFactory.class){
                if(service==null){
                    service=new ServiceFactory();
                }
            }
        }
        return service;
    }
}
