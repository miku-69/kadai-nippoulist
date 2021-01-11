package controllers.report_good;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report_good;
import utils.DBUtil;

/**
 * Servlet implementation class ReportGoodRemove
 */
@WebServlet("/reports/report_good/remove")
public class ReportGoodRemove extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGoodRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report_good g = em.find(Report_good.class, Integer.parseInt(request.getParameter("good_id")));

        em.getTransaction().begin();
        em.remove(g);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "いいね解除しました。");

        request.removeAttribute("good_id");

        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
