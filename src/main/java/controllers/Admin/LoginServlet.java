package controllers.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;
import utils.EncryptUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO dao = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/AdminTemplate/pages/Login/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> listUser = new ArrayList<User>();
		listUser = dao.findAll();

		try {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        User user = dao.findByEmail(email);
	        
	        if (user != null && EncryptUtils.checkPassword(password, user.getPassword()) && user.isRole() == true) {
	        	int userId = user.getId();
	            CookiesUtils.add( "adminID", String.valueOf(userId), 1 , response);
	        	response.sendRedirect(request.getContextPath() + "/admin");
	        } else {
	            request.setAttribute("errorMessage", "Invalid Email Or Password ");
	            request.getRequestDispatcher("/views/AdminTemplate/pages/Login/Login.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
