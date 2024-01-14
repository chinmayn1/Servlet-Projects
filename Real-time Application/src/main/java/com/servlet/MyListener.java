package com.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.ArrayList;
import com.modal.*;
public class MyListener implements ServletContextListener {
 public void contextInitialized(ServletContextEvent e) {
  ArrayList list = new ArrayList();
  try {
   Class.forName("com.mysql.jdbc.Driver");
   Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/real-time-application", "root", "root");
   PreparedStatement ps = con.prepareStatement("select * from user");
   ResultSet rs = ps.executeQuery();
   while (rs.next()) {
    User u = new User();
    u.setId(rs.getInt(1));
    u.setName(rs.getString(2));
    u.setPassword(rs.getString(3));
    list.add(u);
   }
   con.close();
  } catch (Exception ex) {
   System.out.print(ex);
  }
  // storing the ArrayList object in ServletContext
  ServletContext context = e.getServletContext();
  context.setAttribute("data", list);
 }
 public void contextDestroyed(ServletContextEvent arg0) {
  System.out.println("project undeployed...");
 }
}