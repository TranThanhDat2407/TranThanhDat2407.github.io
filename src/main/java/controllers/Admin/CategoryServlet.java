package controllers.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;
import utils.EncryptUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import DAO.CategoryDAO;
import DAO.UserDAO;
import entity.Category;
import entity.User;
@WebServlet(urlPatterns ={"/admin/category","/admin/category/edit", "/admin/category/update", "/admin/category/delete", 
	"/admin/category/create" ,"/admin/category/search"})

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO = new UserDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminID = CookiesUtils.get("adminID", request);
		if (adminID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		User admin = userDAO.findByID(Integer.parseInt(adminID));
		request.setAttribute("adminName", admin.getName());
		
		String requestURI = request.getRequestURI();
	     if (requestURI.endsWith("/admin/logout")) {
	       CookiesUtils.add( "adminID", "", 0 , response);
	       response.sendRedirect(request.getContextPath() + "/admin/login");
	       return;
	     }
	     String uri = request.getRequestURI();
	     categoryfindAll(request, response);
			
			if(uri.contains("/admin/category/delete")) {
				categoryDelete(request, response);
				request.setAttribute("category", new Category());
			}
			if(uri.contains("/admin/category/edit")) {
				categoryEdit(request, response);
				
			}
			if (uri.contains("/admin/category/search")) {
				categorySearchByName(request, response);
			}
			if (uri.equals("admin/category")) {
				categoryClearForm(request, response);
			}
		request.getRequestDispatcher("/views/AdminTemplate/pages/Category/category.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("/admin/category/create")) {
			categoryCreate(request, response);
		}
		if(uri.contains("/admin/category/update")) {
			categoryUpdate(request, response);
	        
		}
		if(uri.contains("/admin/category/delete")) {
			categoryDelete(request, response);
			request.setAttribute("category", new Category());
		}
		if (uri.contains("/admin/category/search")) {
			categorySearchByName(request, response);
		}
		if (uri.equals("admin/category")) {
			categoryClearForm(request, response);
		}
		doGet(request, response);
	}
	
	public void categoryfindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Category> list = categoryDAO.findAll();
			request.setAttribute("listCategory", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	protected void categoryCreate(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	    	
	    	String name = request.getParameter("name");
	    	boolean hasError = false;
	    	
	    	 if (name == null || name.isEmpty()) {
	             request.setAttribute("errorName", "Category Name cannot be empty.");
	             hasError = true;
	         }
	    	
	    	  if (hasError) {
	              request.setAttribute("name", name);
	             
	              request.setAttribute("listCategory", new CategoryDAO().findAll());
	            categoryfindAll(request, response);
	              return;
	          }
	    	
	        Category ca = new Category();
	        
	        // Chỉ định các giá trị cho thuộc tính
	        ca.setName(request.getParameter("name"));
	        
	        String parentIdStr = request.getParameter("parentCategory");
	        if (parentIdStr != null && !parentIdStr.isEmpty()) {
	            try {
	                int parentId = Integer.parseInt(parentIdStr); // Chuyển đổi giá trị từ String thành int
	                CategoryDAO categoryDAO = new CategoryDAO();
	                Category parentCategory = categoryDAO.findByID(parentId);
	                if (parentCategory != null) {
	                    ca.setParentCategory(parentCategory);
	                } else {
	                    // Xử lý trường hợp không tìm thấy danh mục cha
	                    throw new IllegalArgumentException("Parent category not found");
	                }
	            } catch (NumberFormatException e) {
	                // Xử lý lỗi khi không thể chuyển đổi giá trị
	                e.printStackTrace();
	            }
	        }
	        
	        // Tạo mới danh mục
	        CategoryDAO categoryDAO = new CategoryDAO();
	        categoryDAO.create(ca);
	        request.setAttribute("message", "Create success!");
	        System.out.println("Thêm thành công");
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error: " + e.getMessage());
	        System.out.println("Thêm thất bại");
	        System.out.println(e.getMessage());
	    }
	}

	
	private void categoryUpdate(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        // 1. Lấy ID của danh mục cần cập nhật
	        String idParam = request.getParameter("id");
	        if (idParam == null || idParam.trim().isEmpty()) {
	            throw new IllegalArgumentException("Category ID is missing or empty.");
	        }
	        int categoryId = Integer.parseInt(idParam);

	        // 2. Tìm danh mục hiện tại trong database
	        Category existingCategory = categoryDAO.findByID(categoryId);
	        if (existingCategory == null) {
	            throw new IllegalArgumentException("Category not found for ID: " + categoryId);
	        }

	        // 3. Tạo đối tượng Category mới để cập nhật
	        Category updatedCategory = new Category();
	        updatedCategory.setId(categoryId);

	        // 4. Cập nhật tên danh mục
	        String newName = request.getParameter("name");
	        if (newName != null && !newName.trim().isEmpty()) {
	            updatedCategory.setName(newName.trim());
	        } else {
	            updatedCategory.setName(existingCategory.getName()); // Giữ nguyên tên cũ nếu không có tên mới
	        }

	        // 5. Xử lý danh mục cha
	        String parentCategoryIdParam = request.getParameter("parentCategory");
	        if (parentCategoryIdParam != null && !parentCategoryIdParam.trim().isEmpty()) {
	            int parentCategoryId = Integer.parseInt(parentCategoryIdParam);
	            if (parentCategoryId != categoryId) { // Đảm bảo danh mục không tự tham chiếu đến chính nó
	                Category parentCategory = categoryDAO.findByID(parentCategoryId);
	                if (parentCategory != null) {
	                    updatedCategory.setParentCategory(parentCategory);
	                } else {
	                    // Log warning nếu không tìm thấy danh mục cha
	                    System.out.println("Warning: Parent category with ID " + parentCategoryId + " not found.");
	                }
	            } else {
	                // Log warning nếu danh mục cha trùng với danh mục hiện tại
	                System.out.println("Warning: Category cannot be its own parent.");
	            }
	        } else {
	            updatedCategory.setParentCategory(null); // Đặt là danh mục gốc nếu không có danh mục cha
	        }

	        // 6. Giữ nguyên các thông tin khác
	        updatedCategory.setSubCategories(existingCategory.getSubCategories());
	        updatedCategory.setProducts(existingCategory.getProducts());

	        // 7. Thực hiện cập nhật
	        Category result = categoryDAO.update(updatedCategory);

	        // 8. Kiểm tra kết quả và log
	        if (result != null) {
	            System.out.println("Cập nhật thành công danh mục ID: " + result.getId());
	            System.out.println("Tên mới: " + result.getName());
	            System.out.println("ID danh mục cha: " + 
	                (result.getParentCategory() != null ? result.getParentCategory().getId() : "Không có"));
	            
	            request.setAttribute("message", "Cập nhật danh mục thành công!");
	        } else {
	            System.out.println("Cập nhật thất bại cho danh mục ID: " + categoryId);
	            request.setAttribute("error", "Cập nhật danh mục thất bại.");
	        }

	        // 9. Cập nhật lại danh sách danh mục
	        categoryfindAll(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Lỗi khi cập nhật danh mục: " + e.getMessage());
	        request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
	    }
	}
	
	public void categoryDelete(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	categoryDAO.delete(id);
	        System.out.println("Xóa Thành Công");
	        categoryfindAll(request,response);
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Xóa Thất Bại");
	       
	    }
	}
	
	public void categoryEdit(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String idParam = request.getParameter("id");
	        if (idParam != null && !idParam.trim().isEmpty()) {
	        	
	            int id = Integer.parseInt(idParam);
	            Category category = categoryDAO.findByID(id);
	            
	            request.setAttribute("categoryEdit", category);
	        } else {
	          
	            request.setAttribute("error", "Invalid Category ID.");
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Invalid ID format.");
	    } catch (Exception e) {
	        e.printStackTrace();
	       
	        
	    }
	    try {
	        // Retrieve the list of all categories to populate the dropdown
	        List<Category> listCategory = categoryDAO.findAll();
	        
	        // Set the list of categories as a request attribute
	        request.setAttribute("listCategory", listCategory);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "An error occurred while retrieving the category list.");
	    }
	}

	
	public void categorySearchByName(HttpServletRequest request, HttpServletResponse response) {
	    try {
	       String findName  = request.getParameter("findname");
	        List<Category> list2 =  categoryDAO.findByKeyWord(findName);
			request.setAttribute("listCategory", list2);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void categoryClearForm(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        request.setAttribute("category", new Category());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
