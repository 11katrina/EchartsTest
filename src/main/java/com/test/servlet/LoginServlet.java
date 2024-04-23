package com.test.servlet;

import com.test.dao.LoginDao;
import com.test.pojo.User;
import com.test.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        LoginDao loginDao = new LoginDao();
        user = loginDao.checkUser(username,password);
        if (user != null) {
            //获取session对象，把要绑定的对象绑定到session对象上
            req.getSession().setAttribute(Constants.USER_SESSION, user);//绑定名，绑定值
            resp.sendRedirect("index.html");
        } else {
            /**
             查无此人，无法登录
             转发给登录页面，提示用户名或密码错误
             */
            req.setAttribute("error", "用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
