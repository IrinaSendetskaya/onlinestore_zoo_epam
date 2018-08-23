package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.dao.impl.BasketDaoDBImpl;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class CommandIndex extends Action {
	
	@Override
    Action execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object o = session.getAttribute("buyer");   //извлекаем из сессии покупателя
        Buyer buyer;
        if (o!=null){
            buyer=(Buyer) o;
        }
        else {
        	req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
            return Actions.LOGIN.command;
        }

        List<Good> goods=new ArrayList<>();
        List<SpecificationGood> goodsSpecification=new ArrayList<>();

        //если нажата кнопка "Поиск"
        if (req.getParameter("searchBtn")!= null ) {

            String nameSearch=FormUtil.getString(req,"searchInput", ValidationRegex.REGEX_ALL_SYMBOL);
            //выводит список товаро только по части слова Поиска
            goods=DAOFactory.getDAO().goodDAO.getAll(" WHERE Name LIKE '%"+nameSearch+"%'");
            for (Good good : goods) {
            	SpecificationGood goodSpecification=DAOFactory.getDAO().specificationGoodDAO.read(good.getSpecificationGoodId());
            	goodsSpecification.add(goodSpecification);
			}

            req.setAttribute("goods",goods);
            req.setAttribute("specificationsGoods",goodsSpecification);
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар по Вашему поиску найден!");
            return null;
        }
        else
        {
            //выводит весь список товаров
            goods = DAOFactory.getDAO().goodDAO.readAll();
            goodsSpecification=DAOFactory.getDAO().specificationGoodDAO.readAll();  //??
            req.setAttribute("goods",goods);
            req.setAttribute("specificationsGoods",goodsSpecification);
        }


        //если нажата кнопка "В корзину"
        if (req.getParameter("Update")!= null && buyer.getRoleId()!=3) {
            int idGood = FormUtil.getInt(req, "id");
            BigDecimal price=FormUtil.getBigDecimal(req,"price");
            String name=goodsSpecification.get(0).getName();
            Date dateOrder=returnCurrentDate();
            int id=0;
            double quantity= 1.0;
            int fk_buyers=buyer.getId();
            int fk_goods=idGood;
            int fk_specificationGoods=goodsSpecification.get(0).getId();

           // BigDecimal sum=quantity*price;

           // Basket basket=new Basket(id,quantity,sum,dateOrder,fk_buyers,fk_goods);
           // DAOFactory.getDAO().basketDAO.create(basket);

            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар " + name + " добавлен в корзину");
            return null;

        }
        return null;
    }
	
	private Date returnCurrentDate() {
		
		Calendar calendar=Calendar.getInstance();	
		Date curDate=new Date(calendar.getTime().getTime());
		//LocalDate dateTime=curDate.toLocalDate();
		return curDate;
	}
}