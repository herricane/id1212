package se.kth.servlet;

import se.kth.bean.QuestionsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String persistenceUnitName = "task3-jpa";
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int result = 0;

        Enumeration<String> questionIds = req.getParameterNames();
        while (questionIds.hasMoreElements()) {
            String id = questionIds.nextElement();
            QuestionsEntity q = entityManager.find(QuestionsEntity.class, Integer.parseInt(id));
            int choice = Integer.parseInt(req.getParameter(id));

            if (q.getAnswer() == choice) {
                result += 1;
            }
        }

        req.getSession().setAttribute("result", result);
        resp.sendRedirect("result.jsp");
    }
}
