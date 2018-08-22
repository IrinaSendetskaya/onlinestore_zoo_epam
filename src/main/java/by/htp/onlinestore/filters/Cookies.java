package by.htp.onlinestore.filters;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cookies {
//    public static void setCookie(HttpServletResponse resp, Buyer buyer) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
//        DesEncrypter encrypter = DesEncrypter.getInstance();
//        List<Cookie> cookies = new ArrayList<>();
//        cookies.add(new Cookie("login", buyer.getNickname()));
//        cookies.add(new Cookie("password", encrypter.encrypt(buyer.getPassword())));
//        for (int i = 0; i < cookies.size(); i++) {
//            Cookie cookie = cookies.get(i);
//            cookie.setMaxAge(60);
//            resp.addCookie(cookie);
//        }
//    }
//
//    public static Buyer getCookie(HttpServletRequest request) throws SQLException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IOException, IllegalBlockSizeException {
//        DesEncrypter encrypter = DesEncrypter.getInstance();
//        Cookie[] myCookies = request.getCookies();
//        String cookieLogin = "";
//        String cookiePassword = "";
//        for (int i = 0; i < myCookies.length; i++) {
//            Cookie cookie = myCookies[i];
//            if ("login".equals(cookie.getName())) {
//                cookieLogin = cookie.getValue();
//            }
//            if ("password".equals(cookie.getName())) {
//                cookiePassword = encrypter.decrypt(cookie.getValue());
//            }
//        }
//        if (cookieLogin.equals("")) {
//            return null;
//        }
//        List<Buyer> users = DAO.getDAO().buyerDAO.getAll(String.format("WHERE login='%s' and password='%s'", cookieLogin, cookiePassword));
//        return users.get(0);
//    }
}
