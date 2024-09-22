package controllers.Client.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import utils.CookiesUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import DAO.OrderItemDAO;
import DAO.StaticDAO;
import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ClientOrderHistoryServlet
 */
@WebServlet("/client/profile/order/detail")
public class ClientOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	OrderItemDAO daoOItem = new OrderItemDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientOrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		String uri = request.getRequestURI();
		request.setAttribute("accountID", accountID);
		
		if (accountID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/signup");
			return;
		}else if (uri.contains("/client/profile/order/detail")) {
			fillItemDetailByOrderId(request, response);
		}
		
		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/OrderDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void fillItemDetailByOrderId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	   
	    int order_id = Integer.parseInt(request.getParameter("id"));
	    System.out.println("order_id :" + order_id);
	    
	    List<Map<String, Object>> listOrderDetail = daoOItem.findItemDetailByOrder_ID(order_id);
	    
	    String nameUser = "", phone = "", shipping_address = "", payment_type_name = "",thumbnail="";
	    double totalAll = 0.0;
	    int qtyAll = 0;
	    
	    // Duyệt qua từng mục trong danh sách chi tiết đơn hàng
	    for (Map<String, Object> o : listOrderDetail) {
	    	System.out.println("thumbnail :" + o.get("thumbnail"));
	 	    
	        
 
	        Object totalAllObj = o.get("total");
	        if (totalAllObj instanceof Number) {
	            totalAll += ((Number) totalAllObj).doubleValue();
	        }
	        
	        Object qtyAllObj = o.get("qty");
	        if (qtyAllObj instanceof Number) {
	            qtyAll += ((Number) qtyAllObj).intValue();
	        }
	        
	        nameUser = (String) o.get("nameUser");
	        phone = (String) o.get("phone");
	        shipping_address = (String) o.get("shipping_address");
	        payment_type_name = (String) o.get("payment_type_name");
	    }
	    
	   
	    request.setAttribute("listOrderDetail", listOrderDetail);
	    request.setAttribute("nameUser", nameUser);
	    request.setAttribute("phone", phone);
	    request.setAttribute("shipping_address", shipping_address);
	    request.setAttribute("payment_type_name", payment_type_name);
	    request.setAttribute("totalAll", String.valueOf(totalAll));
	    request.setAttribute("qtyAll", String.valueOf(qtyAll));
	}

	
	

}
