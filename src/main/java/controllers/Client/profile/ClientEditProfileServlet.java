package controllers.Client.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;
import utils.EncryptUtils;

import java.io.IOException;
import java.util.List;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ClientEditProfileServlet
 */
@WebServlet("/client/profile/edit")
public class ClientEditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientEditProfileServlet() {
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
		
		
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/EditProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		String lang = (String) request.getSession().getAttribute("lang");
		
		if (accountID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/signup");
			return;
		}

		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		boolean hasError = false;

		if (name == null || name.trim().isEmpty()) {
			hasError = true;
			  if (lang.equals("vi")) {
	                request.setAttribute("ErrorName", "Tên không được để trống!");
	            } else {
	                request.setAttribute("ErrorName", "Please fill the name!");
	            }
		} 
		
		if (phone == null || phone.trim().isEmpty()) {
	            hasError = true;
	            if (lang.equals("vi")) {
	                request.setAttribute("ErrorPhone", "Số điện thoại không được để trống!");
	            } else {
	                request.setAttribute("ErrorPhone", "Please fill the phone number!");
	            }
	         
	        }else if(!phone.matches("(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b")) {
	        	hasError = true;
	        	 if (lang.equals("vi")) {
	        	        request.setAttribute("ErrorPhone", "Số điện thoại không đúng định dạng!");
	        	    } else {
	        	        request.setAttribute("ErrorPhone", "The phone number is not in the correct format!");
	        	    }
	        }
	        

		
		if (hasError) {
			request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/EditProfile.jsp")
					.forward(request, response);
		} else {
			user.setName(name);
			user.setPhone(phone);
			userDAO.update(user);
		    response.sendRedirect(request.getContextPath() + "/client/profile");

		}
	}

}
