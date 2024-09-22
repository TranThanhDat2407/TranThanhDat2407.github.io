package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xalan.xsltc.compiler.sym;

import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.Payment_TypeDAO;
import DAO.UserDAO;
import DAO.User_AddressDAO;
import DAO.User_Payment_MethodDAO;
import entity.Cart;
import entity.Payment_Type;
import entity.User;
import entity.User_Payment_Method;

/**
 * Servlet implementation class ClientPaymentServlet
 */
@WebServlet({"/client/payment","/client/payment/createOrder","/client/payment/createUPM"})
public class ClientPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDao = new UserDAO();
    User_AddressDAO addressDao = new User_AddressDAO();
    User_Payment_MethodDAO upmDao = new User_Payment_MethodDAO();
    CartDAO cartDao = new CartDAO();
    OrderDAO orderDao = new OrderDAO();
    Payment_TypeDAO ptDao = new Payment_TypeDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		try {
			User currentUser = userDao.findByID(Integer.parseInt(accountID));
			List<Object[]> listAddress = addressDao.selectAddressByUserId(currentUser.getId());
			List<User_Payment_Method> listUpm = upmDao.findByUser(currentUser);
			List<Cart> listCart = cartDao.findByUser(currentUser);
			List<Payment_Type> listPT = ptDao.findAll();
			
			request.setAttribute("listPT", listPT);
			request.setAttribute("listCart", listCart);
			request.setAttribute("currentUser", currentUser);
			request.setAttribute("listAddress", listAddress);
			request.setAttribute("listUpm", listUpm);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loi: "+e.getMessage());
		}
		
		String uri = request.getRequestURI();
		if (uri.contains("/client/payment/createOrder")) {
			doCreateOrder(request, response);
		}
		
		
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/payment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		String uri = request.getRequestURI();
		if (uri.contains("/client/payment/createUPM")) {
			doCreateUPM(request, response);
		}
		if (uri.contains("/client/payment/createOrder")) {
			doCreateOrder(request, response);
		}
	}

	protected void doCreateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		int user_id = Integer.parseInt(accountID);
		int upm_id = Integer.parseInt(request.getParameter("upm_id"));
		int address_id = Integer.parseInt(request.getParameter("address_id"));
		
		try {
			orderDao.createOrder(user_id, upm_id, address_id);
			response.sendRedirect(request.getContextPath() + "/client/profile/order");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loi: "+e.getMessage());
		}
	}
	
	protected void doCreateUPM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		User_Payment_Method upm = new User_Payment_Method();
		Payment_Type pt = ptDao.findById(Integer.parseInt(request.getParameter("paymentType")));
		User u = userDao.findByID(Integer.parseInt(accountID));
		String cardNumber = request.getParameter("CardNumber");
		String cardHolderName = request.getParameter("CardHoderName");
		String provider = request.getParameter("Provider");
		String expiryDateStr = request.getParameter("ExpiryDate");
		Date expiryDate = null;
		if (expiryDateStr != null && !expiryDateStr.isEmpty()) {
		    try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        expiryDate = dateFormat.parse(expiryDateStr);
		    } catch (ParseException e) {
		        // Xử lý lỗi nếu chuỗi không đúng định dạng
		        e.printStackTrace();
		    }
		}
		
		upm.setCardHolderName(cardHolderName);
		upm.setCardNumber(cardNumber);
		upm.setDefault(true);
		upm.setExpiryDate(expiryDate);
		upm.setPaymentType(pt);
		upm.setProvider(provider);
		upm.setUser(u);
		try {
			upmDao.create(upm);
			System.out.println("ok lun");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loi: "+e.getMessage());
		}
		
		response.sendRedirect(request.getContextPath() + "/client/payment");
	}
}
