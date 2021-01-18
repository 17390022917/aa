package com.bjp.controller;

import com.bjp.dao.UserDao;
import com.bjp.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UserAddServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String userName,password,sex,email;
    UserDao dao=new UserDao();
    int count=0;
    PrintWriter out=null;
    userName=request.getParameter("userName");
    password=request.getParameter("password");
    sex=request.getParameter("sex");
    email=request.getParameter("email");
        Users users=new Users(null,userName,password,sex,email);
        try {
            count=dao.add(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;Charset=utf-8");
         out=response.getWriter();
         if(count==1){
             out.print("注册成功");
         }else{
             out.print("注册失败");
         }

    }
}
