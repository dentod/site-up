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
 * Created by matr on 15.03.14.
 */
@WebServlet("/save")
public class SaveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices userServices = UserServices.getInstance();
        try{
        userServices.closeConnect();
        }
        catch(SQLException e){

        }
        req.getSession().invalidate();
        RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/logout.jsp");
        dispatch.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices userServices = UserServices.getInstance();
        User user;
        Collection<User> users = null;
        String oldEmail = req.getParameter("oldEmail");
        String email = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userRole = req.getParameter("role");
try{
        if (userServices.getByEmail(email) == null && !email.equals("")) {
            userServices.add(email ,password, firstName, lastName, userRole);

        } else if (userServices.getByEmail(email) != null && !email.equals("") && !oldEmail.equals("")) {
            userServices.update(oldEmail,email,password, firstName, lastName, userRole);
        }
         users = userServices.getAll();
}
catch(SQLException e){
    System.out.print("save");
}
        req.setAttribute("users", users);
        RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/users.jsp");
        dispatch.forward(req, resp);

    }
}
