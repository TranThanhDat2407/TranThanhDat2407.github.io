package controllers.Client.profile.address;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;

import DAO.UserDAO;
import DAO.User_AddressDAO;
import entity.Address;
import entity.User;
import entity.User_Address;

/**
 * Servlet implementation class ClientAddressServlet
 */
@WebServlet({"/client/profile/address/create","/client/profile/address/createAddress"})
public class ClientCreateAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User_AddressDAO userAddressDAO = new User_AddressDAO();
	UserDAO userDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCreateAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		
		if (accountID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/signup");
			return;
		}
		
		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/CreateAddress.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
        if (uri.contains("/client/profile/address/createAddress")) {
        	doCreateAddress(request, response);
        	response.sendRedirect(request.getContextPath() + "/client/payment");
        	return;
        }
		
		doCreateAddress(request, response);
		response.sendRedirect(request.getContextPath() + "/client/profile/address");
		return;
	}

	public void doCreateAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		
		if (accountID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/signup");
			return;
		}
		
		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);
		
		String city = request.getParameter("city");
		String ward = request.getParameter("ward");
		String street = request.getParameter("street");
		userAddressDAO.createAddressClient(Integer.parseInt(accountID), city, ward, street);
	}
}
