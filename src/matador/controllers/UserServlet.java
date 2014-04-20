package matador.controllers;

import matador.persistence.model.User;
import matador.services.UserServices;

import javax.management.InstanceAlreadyExistsException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by matr on 14.03.14.
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices userServices = UserServices.getInstance();
        Collection<User> users = null;
    try{
        userServices.newConnect();
         users = userServices.getAll();
    }
    catch(SQLException e){
        System.out.print("SQL ERROR");
    }
        req.setAttribute("users", users);
        RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/users.jsp");
        dispatch.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserServices userServices = UserServices.getInstance();
        Collection<User> users = null;
        try{
            userServices.newConnect();
         users = userServices.getAll();
        }
        catch(SQLException e){
            System.out.print("SQL ERROR2");
        }
   /*     String pass = null;
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            userServices.newConnect();
            users = userServices.getAll();
            User user = userServices.getByEmail(email);
         if(user != null){
               pass = user.getPassword();
            if(pass.equals(password)){
                 req.setAttribute("users", users);
                RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/users.jsp");
                dispatch.forward(req, resp);
            }
        }
        else{
            RequestDispatcher dispatch = req.getRequestDispatcher("/login.jsp");
            dispatch.forward(req, resp);
        }
    }
    catch(SQLException e){
        System.out.print("SQL ERROR2");
    }

    }*/
        req.setAttribute("users", users);
        RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/users.jsp");
        dispatch.forward(req, resp);
}
}