package com.bjp.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    /**
     * Druid连接池的工具类
     */

        //定义成员变量 DataSource
        private static DataSource ds;
        static{

            try {
                //加载配置文件
                Properties pro=new Properties();
                pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
                //获取Datasource
                ds= DruidDataSourceFactory.createDataSource(pro);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * 获取连接的方法
         */
        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }
        /**
         * 释放资源
         */
        public static void close(ResultSet sr, Statement stat, Connection conn){
            if(sr!=null){
                try {
                    stat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        public static void close( Statement stat, Connection conn){

        /*if(stat!=null){
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
            close(null,stat,conn);
        }
        /**
         * 获取连接池的方法

         */
        public static DataSource getDatasource(){
            return  ds;
        }

}
