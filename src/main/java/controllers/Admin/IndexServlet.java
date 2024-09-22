package controllers.Admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.List;

import DAO.StaticDAO;
import DAO.UserDAO;
import entity.User;
import entity.topProduct;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet({"/admin","/admin/logout"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adminID = CookiesUtils.get("adminID", request);
		if (adminID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		User admin = userDAO.findByID(Integer.parseInt(adminID));
		request.setAttribute("adminName", admin.getName());
		
		 String requestURI = request.getRequestURI();
	     if (requestURI.endsWith("/admin/logout")) {
	       CookiesUtils.add( "adminID", "", 0 , response);
	       response.sendRedirect(request.getContextPath() + "/admin/login");
	       return;
	     }

		List<topProduct> top5 = new StaticDAO().getTopSellProduct();
		request.setAttribute("top5Proc", top5);

		
		
		try {
			int orderCountByMonth = new StaticDAO().getOrdersCountByMonth();
			request.setAttribute("countOrders", orderCountByMonth);
		} catch (javax.persistence.NoResultException e) {
		    request.setAttribute("countOrders", 0);
		}
		
		try {
		int userCountByMonth = new StaticDAO().getUsersCountByMonth();
		request.setAttribute("countUsers", userCountByMonth);
		}catch(javax.persistence.NoResultException e){
		request.setAttribute("countUsers", 0);
		}

		double totalRevenue = new StaticDAO().getRevenueCountByMonth();
		request.setAttribute("totalRevenue", totalRevenue);

		request.getRequestDispatcher("/views/AdminTemplate/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
