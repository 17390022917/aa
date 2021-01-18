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
import java.util.List;

public class UserFindServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao=new UserDao();
        PrintWriter out;
        try {
            List <Users>userlist=dao.findall();
            response.setContentType("text/html;Charset=utf-8");
            out=response.getWriter();
            out.println("<table border='2' align='center'>");
            out.println("<tr>");
            out.println("<td>用户编号</td>");
            out.println("<td>用户姓名</td>");
            out.println("<td>用户密码</td>");
            out.println("<td>用户性别</td>");
            out.println("<td>用户邮箱</td>");
            out.println("</tr>");
            for(Users users:userlist){
                out.println("<tr>");
                out.println("<td>"+users.getUserId()+"</td>");
                out.println("<td>"+users.getUserName()+"</td>");
                out.println("<td>"+"********"+"</td>");
                out.println("<td>"+users.getSex()+"</td>");
                out.println("<td>"+users.getEmail()+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
