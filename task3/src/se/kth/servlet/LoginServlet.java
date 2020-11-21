package se.kth.servlet;

import se.kth.bean.UsersEntity;

import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String persistenceUnitName = "task3-jpa";
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery(
                "SELECT u FROM UsersEntity u WHERE u.username=:username and u.password=:password");
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            UsersEntity user = (UsersEntity) query.getSingleResult();
            byte isAdmin = user.getIsAdmin();
            if (isAdmin == 1) {
                req.getSession().setAttribute("admin", user);
                resp.sendRedirect("admin.jsp");
            } else {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("quiz.jsp");
            }
        } catch (javax.persistence.NoResultException e) {
            resp.sendRedirect("index.jsp?error=1");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
