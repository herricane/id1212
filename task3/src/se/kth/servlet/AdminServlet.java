package se.kth.servlet;

import se.kth.bean.QuestionsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-question")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String question = req.getParameter("question");
        String choice1 = req.getParameter("choice-1");
        String choice2 = req.getParameter("choice-2");
        String choice3 = req.getParameter("choice-3");
        String choice4 = req.getParameter("choice-4");
        int answer = Integer.parseInt(req.getParameter("answer"));

        String persistenceUnitName = "task3-jpa";
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        QuestionsEntity q = new QuestionsEntity();
        q.setQuestion(question);
        q.setChoice1(choice1);
        q.setChoice2(choice2);
        q.setChoice3(choice3);
        q.setChoice4(choice4);
        q.setAnswer(answer);

        entityManager.persist(q);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();

        resp.sendRedirect("admin.jsp?add=1");
    }
}
