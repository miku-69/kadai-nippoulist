package controllers.report_good;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.Report_good;
import utils.DBUtil;

/**
 * Servlet implementation class ReportGoodAdd
 */
@WebServlet("/reports/report_good/add")
public class ReportGoodAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGoodAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  String _token = (String)request.getParameter("_token");
      //  if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Report_good g = new Report_good();

            g.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            g.setReport(em.find(Report.class,Integer.parseInt(request.getParameter("report_id"))));

            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "いいねしました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");
      //  }
    }

}
