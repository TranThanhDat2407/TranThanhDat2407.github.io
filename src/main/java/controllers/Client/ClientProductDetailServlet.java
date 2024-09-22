package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import DAO.CartDAO;
import DAO.OrderItemDAO;
import DAO.ProductDAO;
import DAO.ProductItemDAO;
import DAO.ReviewDAO;
import DAO.UserDAO;
import entity.Cart;
import entity.OrderItem;
import entity.ProductItem;
import entity.Review;
import entity.User;

@WebServlet({"/client/product_detail","/client/product_detail/addToCart"})
public class ClientProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductItemDAO productItemDAO = new ProductItemDAO();
	ProductDAO productDAO = new ProductDAO();
	ReviewDAO reviewDao = new ReviewDAO();
	OrderItemDAO oiDao = new OrderItemDAO();
	UserDAO userDao = new UserDAO();
	CartDAO cartDao = new CartDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		
		
		String productIdParam = request.getParameter("product_id");
		
		if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdParam);
                Map<String, Object> productDetails = productItemDAO.getProductDetails(productId);
                List<String> thumbnails = productDAO.findThumbnailsByProductId(productId);
                if (!thumbnails.isEmpty()) {
                	request.setAttribute("thumbnails", thumbnails);
                }
                if (productDetails != null) {
                    request.setAttribute("productDetails", productDetails);
                } else {
                    request.setAttribute("errorMessage", "Product not found");
                }
                fillReview(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid product ID");
            }
        } else {
            request.setAttribute("errorMessage", "Product ID is required");
        }
		
		
		
		
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/product_detail.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
        if (uri.contains("/client/product_detail/addToCart")) {
        	addToCart(request, response);
        }else {
        	doGet(request, response);
        }
	}
	
	
	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String productIdParam = request.getParameter("product_id");
	    int qty = Integer.parseInt(request.getParameter("quantity"));
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
	            	cart.setQty(qty);
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
		            	cart.setQty(qty);
		            	cartDao.create(cart);
		            	System.out.println("Truong hop 2");
	            	}else 
	            	{
	            		cartDao.updateQuantity(cartExist.getId(), cartExist.getQty()+qty);
	            		System.out.println("Truong hop 3");
	            	}
	            }
	            // Chuyển hướng trở lại trang chi tiết sản phẩm
	            response.sendRedirect(request.getContextPath() + "/client/product_detail?product_id=" + productId);
	        } catch (NumberFormatException e) {
	            request.getSession().setAttribute("cartMessage", "ID sản phẩm không hợp lệ.");
	            response.sendRedirect(request.getContextPath() + "/client/product_detail");
	        }
	    } else {
	        System.out.println("khong tim thay id");
	    }
	}
	
	protected void fillReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productIdParam = request.getParameter("product_id");
		if (productIdParam != null && !productIdParam.isEmpty()) {
			int productId = Integer.parseInt(productIdParam);
			Map<String, Integer> reviewCounts = reviewDao.getProductReviewCounts(productId);
			List<Review> reviews = reviewDao.getProductReviews(productId);
			request.setAttribute("reviewCounts", reviewCounts);
			request.setAttribute("reviews", reviews);
        }
	}
	
	protected void createReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String accountID = CookiesUtils.get("accountID", request);
	    if (accountID == null || accountID.isEmpty()) {
	        response.sendRedirect(request.getContextPath() + "/client/login");
	        return;
	    }

	    String productIdParam = request.getParameter("product_id");
	    String ratingValueParam = request.getParameter("ratingValue");

	    // Check if required parameters are present and not empty
	    if (productIdParam == null || productIdParam.isEmpty() || ratingValueParam == null || ratingValueParam.isEmpty()) {
	        request.setAttribute("errorMessage", "Invalid input. Please provide all required information.");
	        doGet(request, response);
	        return;
	    }

	    try {
	        int productId = Integer.parseInt(productIdParam);
	        int ratingValue = Integer.parseInt(ratingValueParam);
	        User currentUser = userDao.findByID(Integer.parseInt(accountID));

	        String comment = request.getParameter("comment");
	        if (comment == null) {
	            comment = "ok ban oi"; // Set to empty string if null
	        }

	        // Check if the user has purchased the product
	        OrderItemDAO orderItemDAO = new OrderItemDAO();
	        int orderItemId = orderItemDAO.getOrderItemIdByUserAndProduct(currentUser.getId(), productId);

	        if (orderItemId == 0) {
	            request.setAttribute("errorMessage", "You can only review products you have purchased.");
	            doGet(request, response);
	            return;
	        }

	        OrderItem orderItem = oiDao.findById(orderItemId);

	        // Create a new Review object
	        Review review = new Review();
	        review.setOrderedProduct(orderItem);
	        review.setUser(currentUser);
	        review.setRatingValue(ratingValue);
	        review.setComment(comment);
	        review.setCreateAt(new Date());
	        review.setUpdateAt(new Date());

	        // Save the review
	        reviewDao.create(review);

	        // Redirect back to the product detail page
	        response.sendRedirect(request.getContextPath() + "/client/product_detail?product_id=" + productId);

	    } catch (NumberFormatException e) {
	        request.setAttribute("errorMessage", "Invalid input. Please provide valid numeric values.");
	        doGet(request, response);
	    } catch (Exception e) {
	        request.setAttribute("errorMessage", "An error occurred while processing your review. Please try again.");
	        doGet(request, response);
	    }
	}
}
