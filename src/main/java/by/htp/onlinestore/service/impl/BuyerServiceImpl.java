package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.BuyerDao;
import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.BuyerService;
import by.htp.onlinestore.util.CheckBuyerUtil;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.ValidationRegex;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

/**
 * Class provides methods for working with Buyers table.
 * @author Iryna Siandzetskaya
 *
 */
public class BuyerServiceImpl implements BuyerService {
	
	/**
	 * Declares a object and initialize
	 */
	private BuyerDao buyerDao=DAOFactory.getDao().getBuyerDAO();

	/**
	 * constructor without parameter
	 */
	public BuyerServiceImpl() {

	}
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public BuyerDao getBuyerDao() {
		return buyerDao;
	}



	public void setBuyerDao(BuyerDao buyerDao) {
		this.buyerDao = buyerDao;
	}



	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#getBuyerList()
	 */
	@Override
	public List<Buyer> getBuyerList() {

		return buyerDao.readAll();
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#getBuyerByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Buyer getBuyerByLoginAndPassword(String login, String password) {
		return buyerDao.read(login, password);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#update(by.htp.onlinestore.entity.Buyer)
	 */
	@Override
	public void update(Buyer entity) {

		buyerDao.update(entity);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#delete(by.htp.onlinestore.entity.Buyer)
	 */
	@Override
	public void delete(Buyer entity) {

		buyerDao.delete(entity);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#create(by.htp.onlinestore.entity.Buyer)
	 */
	@Override
	public void create(Buyer entity) {

		buyerDao.create(entity);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#checkBuyerByLogin(java.lang.String)
	 */
	@Override
	public String checkBuyerByLogin(String login) {

		if(login.length()==0)
			return "";
		else if (!FormUtil.validateString(login, ValidationRegex.REGEX_LOGIN)) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_LOGIN_VALIDE, false);
		}
		else if (buyerDao.readByLogin(login) != null) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_LOGIN_NOT_FREE, false);
		}
		else
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_LOGIN_FREE, true);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#checkBuyerByEmail(java.lang.String)
	 */
	@Override
	public String checkBuyerByEmail(String email) {
		
		if(email.length()==0)
			return "";
		else if (!FormUtil.validateString(email, ValidationRegex.REGEX_EMAIL)) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_EMAIL_VALIDE, false);
		}
		else if (buyerDao.readByEmail(email) != null) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_EMAIL_NOT_FREE, false);
		}
		else
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_EMAIL_FREE, true);
	
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#checkBuyerByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public String checkBuyerByLoginAndPassword(String login, String password) {
		
		if (!(FormUtil.validateString(login, ValidationRegex.REGEX_LOGIN)||FormUtil.validateString(password, ValidationRegex.REGEX_PASS))) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_SIGNUP_VALIDE, false);
		}
		else if (getBuyerByLoginAndPassword(login, password) != null) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_SIGNUP_LOGIN_NOT_FREE, false);
		}
		else
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_SIGNUP_OK, true);
	
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#checkBuyerByPassword(java.lang.String)
	 */
	@Override
	public String checkBuyerByPassword(String password) {
		
		if(password.length()==0)
			return "";
		else if (!FormUtil.validateString(password, ValidationRegex.REGEX_PASS)) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_PASS_VALIDE, false);
		}
		else if (buyerDao.readByPassword(password) != null) {
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_PASS_NOT_FREE, false);
		}
		else
			return CheckBuyerUtil.styleCheckUserDataResult(MessageConstantDeclaration.MSG_CHECK_IF_PASS_FREE, true);
	
	}

}
