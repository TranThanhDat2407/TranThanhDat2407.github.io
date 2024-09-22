package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.List;

import DAO.CategoryDAO;
import DAO.GalleryDAO;
import DAO.ProductDAO;
import DAO.ProductItemDAO;
import entity.Category;
import entity.Product;
import entity.ProductItem;
@WebServlet("/client/products")
/**
 * Servlet implementation class ClientProductServlet
 */
public class ClientProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductDAO proDAO = new ProductDAO();
    CategoryDAO cateDAO = new CategoryDAO();
    GalleryDAO gallDAO = new GalleryDAO();
    ProductItemDAO proItemDAO = new ProductItemDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountID = CookiesUtils.get("accountID", request);
        request.setAttribute("accountID", accountID);
        
        List<Category> listParent = cateDAO.listParent();
        
        
        
        int page = 1;
        int pageSize = 4;
        String sortField = request.getParameter("sortField");
        String sortDirection = request.getParameter("sortDirection");
        String keyword = request.getParameter("keyword");
        Integer categoryId = null;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Giữ giá trị mặc định là 1
        }

        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            // Nếu không có categoryId hoặc không hợp lệ, giữ null
        }

        // Nếu sortField không được chỉ định, mặc định sắp xếp theo id
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }

        // Nếu sortDirection không được chỉ định, mặc định là tăng dần
        if (sortDirection == null || sortDirection.isEmpty()) {
            sortDirection = "ASC";
        }

        List<ProductItem> productItemList = proItemDAO.findAllWithGalleries(page, pageSize, sortField, sortDirection, keyword, categoryId);
        long totalItems = proItemDAO.countProductItems(keyword, categoryId);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Tạo mảng các trang
        int[] pages = new int[totalPages];
        for (int i = 0; i < totalPages; i++) {
            pages[i] = i + 1;
        }

        request.setAttribute("productItemList", productItemList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pages", pages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("sortField", sortField);
        request.setAttribute("sortDirection", sortDirection);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("listParent", listParent);
        request.getRequestDispatcher("/views/ClientTemplate/pages/products.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
