package com.servlet;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet ("/RegisterServlet")
public class RegisterServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType ("text/html");
        PrintWriter out = response.getWriter ();
        String name = request.getParameter ("name");
        String email = request.getParameter ("email");
        String password = request.getParameter ("password");
        
        HttpSession session = request.getSession ();
        session.setAttribute ("email", email);
        try
        {
            Class.forName ("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/real-time-application", "root", "root");
            PreparedStatement ps = con.prepareStatement ("insert into login values(1,?,?,?)");
            ps.setString (1, name);
            ps.setString (2, email);
            ps.setString (3, password);
            int i = ps.executeUpdate ();
            if (i > 0)
             out.print ("You are successfully registered...");
            request.getRequestDispatcher ("Welcome1").include (request, response);
        }
        catch (Exception e2)
        {
            System.out.println (e2);
        }
        out.close ();
    }
}