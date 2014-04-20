package matador.controllers;

import matador.persistence.model.User;
import matador.services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by matr on 15.03.14.
 */
@WebServlet("/create")
public class UserNewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/createuser.jsp");
        dispatch.forward(req, resp);

    }
}
