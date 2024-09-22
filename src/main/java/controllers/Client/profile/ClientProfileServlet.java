package controllers.Client.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import DAO.OrderDAO;
import DAO.UserDAO;
import DAO.User_AddressDAO;
import entity.Order;
import entity.User;

/**
 * Servlet implementation class ClientProfileServlet
 */
@WebServlet("/client/profile")
public class ClientProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	User_AddressDAO userAddressDAO = new User_AddressDAO();
	OrderDAO orderDAO = new OrderDAO();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		
		if (accountID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/signup");
			return;
		}

	
		List<Object[]> AddressDefault = userAddressDAO.selectAddressByUserIdAndDefault(Integer.parseInt(accountID));
		request.setAttribute("AddressDefault", AddressDefault);

		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);

		List<Order> listOrder = orderDAO.findByUser(Integer.parseInt(accountID));
		request.setAttribute("listOrder", listOrder);
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/UserProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
