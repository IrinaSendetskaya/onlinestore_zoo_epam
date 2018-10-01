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

import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.dao.impl.GoodDaoDBImpl;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.service.GoodService;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.service.impl.GoodServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Good.class, GoodServiceImpl.class })
@PowerMockIgnore("javax.management.*")
public class TestGoodServiceImpl {

	private GoodService goodService;
	private GoodDao goodDao;
	private Good good;
	private List<Good> goods;
	private List<GoodListForJsp> goodsJsp;

	@Before
	public void init() {
		good = Mockito.mock(Good.class);
		goodDao = Mockito.mock(GoodDaoDBImpl.class);
		goodService =ServiceFactory.getService().getGoodDAO();
		goods=new ArrayList<>();

		Mockito.when(goodDao.read(Mockito.anyInt())).thenReturn(good);
		Mockito.when(goodDao.readAll()).thenReturn(goods);
		Mockito.when(goodDao.findAllGoodsJoinTables()).thenReturn(goodsJsp);
		
	}
	
	@Test
	public void getGoodListTest() {
		goods=goodService.getGoodList();
		goodDao.readAll();
		Mockito.verify(goodDao, times(1)).readAll();
	}
	
	@Test
	public void createGoodTest() {
		goodDao.create(good);
		good=goodDao.read(0);
		assertEquals(0, good.getId());
		Mockito.verify(goodDao, times(1)).create(good);
	}

}
