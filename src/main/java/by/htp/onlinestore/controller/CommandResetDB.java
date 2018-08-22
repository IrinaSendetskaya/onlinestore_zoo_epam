package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.MessageConstantDeclaration;

class CommandResetDB extends Action {
    @Override
    Action execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (FormUtil.isPost(req)) {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,"POST1. Сброс базы. Если завершается здесь, то возможно вы не скопировали библиотеку mysql-connector-java.jar в lib-local");
            //CreateNewSchema.main(null);
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,"POST2. Сброс базы завершился успешно.");
            req.getSession().invalidate();
        }
        return null;
    }
}