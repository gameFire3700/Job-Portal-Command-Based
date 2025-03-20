package com.sudhanshu.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DbConnection 
{
	static Connection con;
    static
    {
        try
        {
             //Class.forName("com.mysql.cj.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public  static Connection getConnect()
    {
        String jdbc_url=null, username=null, password=null;
        try
        {
             InputStream is=DbConnection.class.getResourceAsStream("Db.properties");
             Properties p=new Properties();
             p.load(is);
             
             jdbc_url=p.getProperty("jdbc-url");
             username=p.getProperty("username");
             password=p.getProperty("password");
             
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        try 
        {
           //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal","root","860240");
         
            MysqlDataSource ds=new MysqlDataSource();
            
            ds.setURL(jdbc_url);
            ds.setUser(username);
            ds.setPassword(password);
            
            con=ds.getConnection();
            
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
        return con; 
    }
}
