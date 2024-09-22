package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import DAO.ProductDAO;
import DAO.WishListDAO;
import entity.ProductItem;
import entity.User;
import entity.WishList;

/**
 * Servlet implementation class ClientWishlistServlet
 */
@WebServlet({ "/client/wishlist", "/client/wishlist/create", "/client/wishlist/delete","/client/wishlist/InsertWL_DProduct" })
public class ClientWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WishListDAO daoWishList = new WishListDAO();
	ProductDAO  daoProduct = new ProductDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientWishlistServlet() {
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
		String uri = request.getRequestURI();
		  String lang = (String) request.getSession().getAttribute("lang");
		   
		
		
		if (uri.contains("/client/wishlist/create")) {
			createWishList(request, response);
			return;
		}else if (uri.contains("/client/wishlist/delete")) {
			deleteWishList(request, response);
		}else if (uri.contains("/client/wishlist/InsertWL_DProduct")) {
			createWishListProductDetail(request, response);
		}
		
		
		if(lang.equals("vi")) {
		request.setAttribute("thongbao", "Bạn Phải Đăng Nhập!");
		}else {
		request.setAttribute("thongbao", "You need to sign in!");
		}
		
		if(!accountID.isEmpty()) {
		countWishList(request, response);
		fillProductFromWishList(request, response);
		if(lang.equals("vi")) {
			request.setAttribute("productSize", "Sản Phẩm");
			}else {
				request.setAttribute("productSize", "Items");
			}
		}else {
			request.setAttribute("productSize", "");
		}
		
		
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/wishlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String uri = request.getRequestURI();
			  String lang = (String) request.getSession().getAttribute("lang");
			  if (uri.contains("/client/wishlist/create")) {
					createWishList(request, response);
					return;
				} else if (uri.contains("/client/wishlist/delete")) {
					deleteWishList(request, response);
				} else if (uri.contains("/client/wishlist/InsertWL_DProduct")) {
					createWishListProductDetail(request, response);
				}
		
		
		countWishList(request, response);
		fillProductFromWishList(request, response);
	}
	public void createWishListProductDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		try {
			int product_id = Integer.parseInt(request.getParameter("WL_product_id"));
			System.out.println("product_idproduct_idproduct_idproduct_id : " + product_id);
			int product_item_id = daoProduct.getProductItemId(product_id);
		
	
	 
		
	
		
		fillProductFromWishList(request, response);
		try {
			List<WishList> listWL = daoWishList.getListWlByID_User(Integer.parseInt(accountID));

			if (listWL == null) {
				ProductItem prItem = new ProductItem();
				User user = new User();
				prItem.setId(product_item_id);
				user.setId(Integer.parseInt(accountID));
				WishList wl = new WishList();
				wl.setProductItem(prItem);
				wl.setUser(user);
				daoWishList.create(wl);
				countWishList(request, response);
				System.out.println("Thêm WishList Thành Công ");
				response.sendRedirect(request.getContextPath() + "/client/product_detail?product_id=" + product_id);
				return;

			}

			boolean checkListWL = false;
			for (WishList w : listWL) {
				if (w.getProductItem().getId() == product_item_id) {
					System.out.println("Sản phẩm đã có trong yêu thích ");
					checkListWL = true;
					response.sendRedirect(request.getContextPath() + "/client/product_detail?product_id=" + product_id);
					return;
				}
			}

			if (!checkListWL) {
				ProductItem prItem = new ProductItem();
				User user = new User();
				prItem.setId(product_item_id);
				user.setId(Integer.parseInt(accountID));
				WishList wl = new WishList();
				wl.setProductItem(prItem);
				wl.setUser(user);
				daoWishList.create(wl);
				countWishList(request, response);
				System.out.println("Thêm WishList Thành Công ");

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Create WishList");
		}

		response.sendRedirect(request.getContextPath() + "/client/product_detail?product_id=" + product_id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("product_id ko par dc product_id");
		}
	}
	public void createWishList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		int product_item_id = Integer.parseInt(request.getParameter("product_item_id"));
		fillProductFromWishList(request, response);
		try {
			List<WishList> listWL = daoWishList.getListWlByID_User(Integer.parseInt(accountID));

			if (listWL == null) {
				ProductItem prItem = new ProductItem();
				User user = new User();
				prItem.setId(product_item_id);
				user.setId(Integer.parseInt(accountID));
				WishList wl = new WishList();
				wl.setProductItem(prItem);
				wl.setUser(user);
				daoWishList.create(wl);
				countWishList(request, response);
				System.out.println("Thêm WishList Thành Công ");
				response.sendRedirect(request.getContextPath() + "/client/products");
				return;

			}

			boolean checkListWL = false;
			for (WishList w : listWL) {
				if (w.getProductItem().getId() == product_item_id) {
					System.out.println("Sản phẩm đã có trong yêu thích ");
					checkListWL = true;
					response.sendRedirect(request.getContextPath() + "/client/products");
					return;
				}
			}

			if (!checkListWL) {
				ProductItem prItem = new ProductItem();
				User user = new User();
				prItem.setId(product_item_id);
				user.setId(Integer.parseInt(accountID));
				WishList wl = new WishList();
				wl.setProductItem(prItem);
				wl.setUser(user);
				daoWishList.create(wl);
				countWishList(request, response);
				System.out.println("Thêm WishList Thành Công ");

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Create WishList");
		}

		response.sendRedirect(request.getContextPath() + "/client/products");
	
	}

	public void countWishList(HttpServletRequest request, HttpServletResponse response) {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		 String lang = (String) request.getSession().getAttribute("lang");
		int count = daoWishList.getCountQtyProductWishList(Integer.parseInt(accountID));
		request.setAttribute("countWishList", "" + count);
		
		 if(count == 0) {
			 if(lang.equals("vi")) {
		    	request.setAttribute("thongbao", "Chưa có sản phẩm nào trong yêu thích!");
			 }else {
				 request.setAttribute("thongbao", "There are no products in the wish list!");
			 }
		    }
	}

	public void fillProductFromWishList(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String accountID = CookiesUtils.get("accountID", request);
	    request.setAttribute("accountID", accountID);
	    
	 
	    List<Object[]> listWL_Render = daoWishList.getFillProduct_WishList(Integer.parseInt(accountID));
	  
	    request.setAttribute("listProductFromWishList", listWL_Render);
	
	}

	
	
	public void deleteWishList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String accountID = CookiesUtils.get("accountID", request);
	    request.setAttribute("accountID", accountID);
		String idPrItem = request.getParameter("product_item_id_delete");
		
		try {
			System.out.println("idPrItemidPrItemidPrItemidPrItem :" + idPrItem);
			daoWishList.deleteWishList(Integer.parseInt(idPrItem), Integer.parseInt(accountID));
			System.out.println("DELETED WISH LIST");
			countWishList(request, response);
		} catch (Exception e) {
		e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/client/wishlist");
	}
	
}
