package by.htp.onlinestore;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import by.htp.onlinestore.dao.BuyerDao;
import by.htp.onlinestore.dao.impl.BuyerDaoDBImpl;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.BuyerService;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.service.impl.BuyerServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Buyer.class, BuyerServiceImpl.class })
@PowerMockIgnore("javax.management.*")
public class TestBuyerServiceImpl {

	private BuyerService buyerService;
	private BuyerDao buyerDao;
	private Buyer buyer;
	private List<Buyer> buyers;

	@Before
	public void init() {
		buyer = Mockito.mock(Buyer.class);
		buyerDao = Mockito.mock(BuyerDaoDBImpl.class);
		buyerService =ServiceFactory.getService().getBuyerDAO();
		buyers=new ArrayList<>();

		Mockito.when(buyerDao.read(Mockito.anyInt())).thenReturn(buyer);
		Mockito.when(buyerDao.readAll()).thenReturn(buyers);


	}
	
	@Test
	public void getBuyerListTest() {
		buyers=buyerService.getBuyerList();
		buyerDao.readAll();
		Mockito.verify(buyerDao, times(1)).readAll();
	}
	
	@Test
	public void getBuyerByLoginAndPassword() {
		buyer=buyerService.getBuyerByLoginAndPassword("admin", "admin");
		buyerDao.read(1);
		assertEquals(1, buyer.getId());
		Mockito.verify(buyerDao, times(1)).read(buyer.getId());
	}
}
