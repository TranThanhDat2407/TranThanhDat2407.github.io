package controllers.Client.profile.address;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.List;

import DAO.UserDAO;
import DAO.User_AddressDAO;
import entity.User;

/**
 * Servlet implementation class ClientAddressServlet
 */
@WebServlet({"/client/profile/address","/client/profile/address/set_default"})
public class ClientAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User_AddressDAO userAddressDAO = new User_AddressDAO();
	UserDAO userDAO = new UserDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientAddressServlet() {
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
			response.sendRedirect(request.getContextPath() + "/client/signin");
			return;
		}
		
		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);
		
		String uri = request.getRequestURI();
		if (uri.contains("/client/profile/address/set_default")) {
			String AddressID = request.getParameter("id");
			userAddressDAO.setDefaultAddress(Integer.parseInt(accountID), Integer.parseInt(AddressID));
		}
		
		List<Object[]> listAddress = userAddressDAO.selectAddressByUserIdAndNotDefault(Integer.parseInt(accountID));
		request.setAttribute("listAddress", listAddress);
		
		List<Object[]> AddressDefault = userAddressDAO.selectAddressByUserIdAndDefault(Integer.parseInt(accountID));
		request.setAttribute("AddressDefault", AddressDefault);

		request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/Address.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
