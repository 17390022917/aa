package com.bjp.controller;

import com.bjp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        UserDao dao=new UserDao();
        String username,password;
        request.setCharacterEncoding("utf-8");
        username=request.getParameter("userName");
        password=request.getParameter("password");

        int count=dao.login(username,password);
        if(count==1){
            response.sendRedirect("/myWeb/index.html");
        }else{
            response.sendRedirect("/myWeb/login_error.html");
        }
    }


}
