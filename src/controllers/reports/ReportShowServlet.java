package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ReportShowServlet
 */
@WebServlet("/reports/show")
public class ReportShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        long g = (long)em.createNamedQuery("checkReport_good", Long.class)
                .setParameter("employee", login_employee)
                .setParameter("report", r)
                .getSingleResult();

        if(g != 0){
        Report_good good = (Report_good)em.createNamedQuery("Report_good", Report_good.class)
                                .setParameter("employee", login_employee)
                                .setParameter("report", r)
                                .getSingleResult();

        request.setAttribute("report_good", good);
        }

        em.close();

        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("g",g);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}
