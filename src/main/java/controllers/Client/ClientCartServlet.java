package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.List;

import DAO.CartDAO;
import DAO.ProductDAO;
import DAO.ProductItemDAO;
import DAO.UserDAO;
import entity.Cart;
import entity.ProductItem;
import entity.User;

/**
 * Servlet implementation class ClientCartServlet
 */
@WebServlet({"/client/cart","/client/cart/updateQuantity","/client/cart/remove","/client/cart/createOrUpdate"})
public class ClientCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CartDAO cartDao = new CartDAO();
    UserDAO userDao = new UserDAO();
    ProductDAO productDAO = new ProductDAO();
    ProductItemDAO productItemDAO = new ProductItemDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		
		String uri = request.getRequestURI();
		
		
		
		if(!accountID.isEmpty()) {
			doFindCartByUserId(request, response);
		}
		
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String uri = request.getRequestURI();
	        if (uri.contains("/client/cart/updateQuantity")) {
	            doUpdateQuantity(request, response);
	        } else if(uri.contains("/client/cart/remove")){
	            doRemoveCart(request, response);
	        }else if(uri.contains("/client/cart/createOrUpdate"))
	        {
	        	createOrUpdateCart(request, response);
	        }
	        else {
	        	doGet(request, response);
	        }
	}
	
	protected void createOrUpdateCart(HttpServletRequest request, HttpServletResponse response) {
		String productIdParam = request.getParameter("product_id");
		String accountID = CookiesUtils.get("accountID", request);
		User currentUser = userDao.findByID(Integer.parseInt(accountID));
		    if (productIdParam != null && !productIdParam.isEmpty()) {
		        try {
		            int productId = Integer.parseInt(productIdParam);
		            int productItemId = productDAO.getProductItemId(productId);
		            //Lấy ProductItem theo productItemId
		            ProductItem pi = productItemDAO.findById(ProductItem.class, productItemId);
		            // TODO: Thêm logic thêm vào giỏ hàng ở đây
		            
		           
		            //Lấy cart theo User
		            List<Cart> listCart = cartDao.findByUser(currentUser);
		            if(listCart == null) 
		            {
		            	Cart cart = new Cart();
		            	cart.setProductItem(pi);
		            	cart.setUser(currentUser);
		            	cart.setQty(1);
		            	cartDao.create(cart);
		            	System.out.println("Cart rong");
		            }else //Nếu tồn tại thì kiểm tra các cart trong list
		            {
		            	Cart cartExist = cartDao.findByUserAndProductItem(currentUser, pi);
		            	if(cartExist == null) 
		            	{
		            		Cart cart = new Cart();
			            	cart.setProductItem(pi);
			            	cart.setUser(currentUser);
			            	cart.setQty(1);
			            	cartDao.create(cart);
			            	System.out.println("Truong hop 2");
		            	}else 
		            	{
		            		cartDao.updateQuantity(cartExist.getId(), cartExist.getQty()+1);
		            		System.out.println("Truong hop 3");
		            	}
		            }
		            response.sendRedirect(request.getContextPath() + "/client/products");
		        } catch (IOException e) {
		            request.getSession().setAttribute("cartMessage", "ID sản phẩm không hợp lệ.");
		        }
			
		    }
			
			
		
	}

	protected void doFindCartByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		User currentUser = userDao.findByID(Integer.parseInt(accountID));
		try {
			List<Cart> listCart = cartDao.findByUser(currentUser);
			request.setAttribute("listCart", listCart);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loi: "+e.getMessage());
		}
	}

	protected void doUpdateQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
            cartDao.updateQuantity(cartId, newQuantity);
            
            // Redirect back to the cart page
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error - maybe redirect to an error page
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
	
	protected void doRemoveCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cartId = Integer.parseInt(request.getParameter("cart_id"));
			cartDao.delete(cartId);
			
			doGet(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
