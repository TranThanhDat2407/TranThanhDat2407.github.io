package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;

import DAO.CartDAO;
import DAO.WishListDAO;

/**
 * Servlet implementation class I18nFilter1
 */
@WebFilter("/*")
public class I18nFilter1 implements HttpFilter {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I18nFilter1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, 
			FilterChain chain) throws IOException, ServletException {
		String lang = (String) req.getSession().getAttribute("lang");
		String accountID = CookiesUtils.get("accountID", req);
		req.setAttribute("accountID", accountID);
		if(!accountID.isEmpty()) {
		countWishList(req,resp);
		countQtyCart(req,resp);
		}
		
	    String paramLang = req.getParameter("lang");
	    if (paramLang != null) {
	        req.getSession().setAttribute("lang", paramLang);
	        lang = paramLang;
	    } else if (lang == null) {
	        lang = "vi";
	        req.getSession().setAttribute("lang", lang);
	    }
			chain.doFilter(req, resp);
}
	
	public void countWishList(HttpServletRequest request, HttpServletResponse response) {
		WishListDAO daoWishList = new WishListDAO();
		
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		
		 int count =  daoWishList.getCountQtyProductWishList(Integer.parseInt(accountID));
		
		
		 request.setAttribute("countWishList", ""+count);
	
	}
	
	public void countQtyCart(HttpServletRequest request, HttpServletResponse response) {
		CartDAO cartDAO = new CartDAO();
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		int count = cartDAO.countCartByUser(Integer.parseInt(accountID));
		
		request.setAttribute("countQtyCart", ""+count);
	}
}
