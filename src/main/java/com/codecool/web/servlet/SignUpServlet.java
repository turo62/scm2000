package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DBUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import com.codecool.web.service.simple.SimpleUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/RegistrationServlet")
public class SignUpServlet extends AbstractServlet {
    
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())){
            UserDao userDao = new DBUserDao(connection);
            UserService us = new SimpleUserService(userDao);
            
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            
            String name = req.getParameter("user_name");
            String mail = req.getParameter("email");
            HttpSession session = req.getSession(false);
            session.setAttribute("email", mail);
            mail = (String) session.getAttribute("email");
            String role = req.getParameter("role");
            String password = req.getParameter("password");
            
            if (us.notValidated(connection, name, mail, role, password).equals("newUser")) {
                User user = null;
                us.addUser(name, mail, role, password);
                user = us.getUser(mail);
                session.setAttribute("user", user);
                
                RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
                rd.forward(req, resp);
            } else if(us.notValidated(connection, name, mail, role, password).equals("empty")) {
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                out.println("<font color=red>Please fill in each field</font>");
                rd.include(req, resp);
                
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

