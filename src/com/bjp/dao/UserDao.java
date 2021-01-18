package com.bjp.dao;

import com.alibaba.druid.util.JdbcUtils;
import com.bjp.entity.Users;
import com.bjp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /**
     * 注册
     * @param user
     * @return
     * @throws SQLException
     */
    public int add(Users user) throws SQLException  {
        //JdbcUtil jdbcUtil=new JdbcUtil();
        Connection conn = null;
        PreparedStatement pre = null;
        int count = 0;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //3.获取数据库链接对象

             //conn  =DriverManager.getConnection("jdbc:mysql://localhost:3308/crx", "root", "mysql");
             conn= JdbcUtil.getConnection();
            String sql = "insert into user(userName,password,sex,email) values(?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getSex());
            pre.setString(4, user.getEmail());

            count = pre.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            JdbcUtil.close(pre,conn);

        }
        return count;
    }
    //删除功能
    public  int delete(String userId){
        String sql="delete from user where userId=? ";
        Connection conn=null;
        PreparedStatement pre=null;
        int count=0;
        try {
            conn=JdbcUtil.getConnection();
            pre=conn.prepareStatement(sql);
            pre.setString(1,"userId");
            count=pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(pre,conn);
        }

        return count;
    }

    /**
     * 查询
     * @return
     * @throws SQLException
     */
    public List findall() throws SQLException {

        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        List userList = new ArrayList<>();
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //3.获取数据库链接对象
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3308/crx", "root", "mysql");
            // conn= JdbcUtil.getConnection();
            String sql = "select * from user";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Integer userid = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                Users users = new Users(userid, userName, password, sex, email);
                userList.add(users);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();

            if (pre != null)
                pre.close();

            if (conn != null) {
                conn.close();


            }

        }
        return userList;
    }

    //登录验证
    public int  login(String username,String password){

    String sql="select count(*) from user where userName=? and password=?";
        Connection conn=null;
        PreparedStatement pre=null;
        ResultSet rs=null;
        int count=0;
        try {
            conn=JdbcUtil.getConnection();
            pre=conn.prepareStatement(sql);
            pre.setString(1,username);
            pre.setString(2,password);
            rs=pre.executeQuery();

            while(rs.next()){
             count=rs.getInt("count(*)");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(rs,pre,conn);
        }
        return count;
    }
}

