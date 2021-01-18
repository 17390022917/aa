package com.bjp.controller;

import com.bjp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class UserDeleteServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String userId;
        UserDao dao=new UserDao();
        PrintWriter out=null;
        userId=request.getParameter("userId");
        int count=dao.delete(userId);
        response.setContentType("text/html; charset=utf-8");
        out=response.getWriter();
        if(count==1){
            out.print("删除成功");
        }else{
            out.print("删除失败");
        }
    }
}
