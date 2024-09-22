package controllers.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import DAO.StaticDAO;
import DAO.UserDAO;
import entity.User;
import entity.topProduct;
@WebServlet({"/admin/static","/admin/static/fillTotalRevenue","/admin/static/OrdersCount","/admin/static/UsersCount"})
/**
 * Servlet implementation class StaticServlet
 */
public class StaticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	StaticDAO daoStatic = new StaticDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaticServlet() {
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
		
		List<topProduct> listTopProduct = daoStatic.getBestSellProducts();
		request.setAttribute("listTopProduct", listTopProduct);
		
		 String requestURI = request.getRequestURI();
	     if (requestURI.endsWith("/admin/logout")) {
	       CookiesUtils.add( "adminID", "", 0 , response);
	       response.sendRedirect(request.getContextPath() + "/admin/login");
	       return;
	     }else if (requestURI.contains("/fillTotalRevenue")) {
	    	 testHttpServletRequest(request, response);
	    	 
		} else if (requestURI.contains("/OrdersCount")) {
			GetOrdersCount(request, response);
	    	 
		} else if (requestURI.contains("/UsersCount")) {
			getUsersCount(request, response);
	    	 
		} 
		    request.getRequestDispatcher("/views/AdminTemplate/pages/Static/Static.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public void testHttpServletRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int mounth = Integer.parseInt(request.getParameter("mounth_totalRevenue"));
		int year = Integer.parseInt(request.getParameter("year_totalRevenue"));
		
		
		try {
			List<Object[]> list = daoStatic.getRevenue(year, mounth);
			request.setAttribute("listRequest", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void GetOrdersCount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		try {
			int mounth = Integer.parseInt(request.getParameter("month_OrdersCount"));
			int year = Integer.parseInt(request.getParameter("year_OrdersCount"));
			
			List<Object[]> listOrderCount = daoStatic.sp_GetOrdersCount(year, mounth);
			
			request.setAttribute("listOrderCount", listOrderCount);
			
			System.out.println("mounth ;" +mounth);
			System.out.println("year ;" +year);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error month year");
		}

	}
	
	public void getUsersCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
			int mounth = Integer.parseInt(request.getParameter("month_UsersCount"));
			int year = Integer.parseInt(request.getParameter("year_UsersCount"));
			
			List<Object[]> listUsersCount = daoStatic.getUsersCount(year, mounth);
			request.setAttribute("listUsersCount", listUsersCount);
			
			System.out.println("mounth ;" +mounth);
			System.out.println("year ;" +year);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error getUsersCount");
		}
		
	}
		
		
	}
	
	


