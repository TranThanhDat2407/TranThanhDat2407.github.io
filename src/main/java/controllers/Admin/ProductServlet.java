package controllers.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import utils.CookiesUtils;
import utils.EncryptUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SetAttribute;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;

import DAO.CategoryDAO;
import DAO.GalleryDAO;
import DAO.ProductDAO;
import DAO.ProductItemDAO;
import DAO.UserDAO;
import entity.Category;
import entity.Gallery;
import entity.Product;
import entity.ProductItem;
import entity.User;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin/products", "/admin/products/edit", "/admin/products/update", "/admin/products/delete",
		"/admin/products/create", "/admin/products/search", "/admin/products/gallery", "/admin/products/gallery/edit",
		"/admin/products/gallery/update", "/admin/products/gallery/delete", "/admin/products/gallery/create",
		"/admin/products/gallery/search", "/admin/products/productItem", "/admin/products/productItem/edit", "/admin/products/productItem/updateOrInsert",
		"/admin/products/productItem/delete", "/admin/products/productItem/search"})

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	ProductDAO productDAO = new ProductDAO();
	CategoryDAO categoryDAO = new CategoryDAO();
	GalleryDAO galleryDAO = new GalleryDAO();
	ProductItemDAO productItemDao = new ProductItemDAO();
	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminID = CookiesUtils.get("adminID", request);

		if (adminID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		User admin = userDAO.findByID(Integer.parseInt(adminID));
		request.setAttribute("adminName", admin.getName());

		String requestURI = request.getRequestURI();
		if (requestURI.endsWith("/admin/logout")) {
			CookiesUtils.add("adminID", "", 0, response);
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		String uri = request.getRequestURI();
		productfindAll(request, response);
		galleryfindAll(request, response);
		productItemfindAll(request, response);
		if (uri.contains("admin/products/delete")) {
			productDelete(request, response);
			request.setAttribute("product", new Product());
		}
		if (uri.contains("admin/products/edit")) {
			productEdit(request, response);
		}
		if (uri.contains("admin/products/search")) {
			productSearchByName(request, response);
		}
		if (uri.equals("admin/products")) {
			productClearForm(request, response);
		}

		/* URI GALLERY */

		if (uri.contains("admin/products/gallery/delete")) {
			galleryDelete(request, response);
			request.setAttribute("gallery", new Gallery());
		}
		if (uri.contains("admin/products/gallery/edit")) {
			galleryEdit(request, response);
		}
		if (uri.contains("admin/products/gallery/search")) {

		}
		if (uri.equals("admin/products/gallery")) {
			galleryClearForm(request, response);
		}

		/* URI PRODUCTITEM */
		
		if (uri.contains("admin/products/productItem/edit")) {
			productItemEdit(request, response);
		}
		if (uri.contains("admin/products/productItem/delete")) {
			productItemDelete(request, response);
		}
		if (uri.equals("admin/products/productItem")) {
			productItemClearForm(request, response);
		}
		
		request.getRequestDispatcher("/views/AdminTemplate/pages/Products/products.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		if (uri.contains("/admin/products/create")) {
			productCreate(request, response);
		}
		if (uri.contains("/admin/products/update")) {
			productUpdate(request, response);
		}
		if (uri.contains("/admin/products/delete")) {
			productDelete(request, response);
			request.setAttribute("product", new Product());
		}
		if (uri.contains("/admin/products/search")) {
			productSearchByName(request, response);
		}
		if (uri.equals("admin/products")) {
			productClearForm(request, response);
		}
		/*
		 * URI Gallery
		 */
		if (uri.contains("admin/products/gallery/create")) {
			galleryCreate(request, response);
		}
		if (uri.contains("admin/products/gallery/update")) {
			galleryUpdate(request, response);
		}
		if (uri.contains("admin/products/gallery/delete")) {
			galleryDelete(request, response);
			request.setAttribute("gallery", new Gallery());
		}
		if (uri.contains("admin/products/gallery/search")) {
		}
		if (uri.equals("admin/products/gallery")) {
			galleryClearForm(request, response);
		}
		/*
		 * URI ProductItem
		 */

		if (uri.contains("/admin/products/productItem/updateOrInsert")) {
			productItemUpdateOrInsert(request, response);
		}
		
		if (uri.contains("admin/products/productItem/delete")) {
			productItemDelete(request, response);
		}
		
		if (uri.equals("admin/products/productItem")) {
			productItemClearForm(request, response);
		}
		doGet(request, response);
	}

	public void productfindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Product> list = productDAO.findAll();

			request.setAttribute("listProduct", list);
			request.setAttribute("listCategory", categoryDAO.findAll());
			productItemfindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void productCreate(HttpServletRequest request, HttpServletResponse response) {
		try {

			
			String name = request.getParameter("name");
	        String description = request.getParameter("description");
	        boolean hasError = false;

	        // Kiểm tra trường name
	        if (name == null || name.isEmpty()) {
	            request.setAttribute("errorName", "Product Name cannot be empty.");
	            hasError = true;
	        }

	        // Kiểm tra trường description
	        if (description == null || description.isEmpty()) {
	            request.setAttribute("errorDescription", "Description cannot be empty.");
	            hasError = true;
	        }

	        // Kiểm tra lỗi và chuyển hướng lại form nếu có lỗi
	        if (hasError) {
	        	request.setAttribute("name", name);
	            request.setAttribute("description", description);
	            request.setAttribute("listCategory", new CategoryDAO().findAll()); 
	            galleryfindAll(request, response);
	            return;
	        }
			
			
			
			
			Product produt = new Product();
			produt.setName(request.getParameter("name"));
			produt.setDescription(request.getParameter("description"));
			produt.setCreateAt(new Date());
			produt.setUpdateAt(new Date());

			String category = request.getParameter("category");

			if (category != null && !category.isEmpty()) {
				try {
					int id = Integer.parseInt(category);
					CategoryDAO categoryDAO = new CategoryDAO();
					Category cateDao = categoryDAO.findByID(id);
					if (cateDao != null) {
						produt.setCategory(cateDao);
					} else {
						// Xử lý trường hợp không tìm thấy danh mục cha
						throw new IllegalArgumentException("category not found");
					}
				} catch (NumberFormatException e) {
					// Xử lý lỗi khi không thể chuyển đổi giá trị
					e.printStackTrace();
				}
			}

			ProductDAO proDao = new ProductDAO();

			proDao.create(produt);

			System.out.println("Thêm Thành Công");

			productfindAll(request, response);

		} catch (Exception e) {
			System.out.println("Thêm thất bại " + e.getMessage());
			request.setAttribute("listCategory", categoryDAO.findAll());
		}
	}

	public void productUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("chạy đến hàm Update");

			int id = Integer.parseInt(request.getParameter("id"));
			Product pr = productDAO.findByID(id);
			request.setAttribute("productEdit", pr);
			System.out.println("ProductEdit  : " + pr);

			if (pr != null) {
				int productId = pr.getCategory().getId();
				System.out.println("productId_Update: " + productId);

				// Validate category
				String categoryParam = request.getParameter("category");
				if (categoryParam != null && !categoryParam.isEmpty()) {
					try {
						int categoryID = Integer.parseInt(categoryParam);
						// Tiếp tục xử lý categoryID
						Category idCategory = categoryDAO.findByID(categoryID);
						if (idCategory != null) {
							pr.setCategory(idCategory);
							System.out.println("ID Category: " + idCategory);
						} else {
							System.out.println("Category not found with ID: " + categoryID);
							request.setAttribute("error", "Category not found.");
							return;
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid category ID format: " + categoryParam);
						request.setAttribute("error", "Invalid category ID format.");
						return;
					}
				} else {
					System.out.println("Category parameter is missing or empty.");
					request.setAttribute("error", "Category parameter is missing.");
					return;
				}

				String name = request.getParameter("name");
				String description = request.getParameter("description");

				pr.setName(name);
				pr.setDescription(description);
				pr.setUpdateAt(new Date());

				productDAO.update(pr);

				System.out.println("Sửa Thành Công");
				productfindAll(request, response);

			} else {
				System.out.println("không tìm thấy id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sửa Thất Bại: " + e.getMessage());
		}

	}

	public void productEdit(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Product pr = productDAO.findByID(id);

			request.setAttribute("productEdit", pr);

			System.out.println("Edit ID: " + pr);
			request.setAttribute("listCategory", categoryDAO.findAll());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void productDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			productDAO.delete(id);
			System.out.println("Xóa Thành Công");
			productfindAll(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Xóa Thất Bại");

		}
	}

	public void productSearchByName(HttpServletRequest request, HttpServletResponse response) {
		try {
			String findname = request.getParameter("findname");
			List<Product> list2 = productDAO.findByKeyWord(findname);
			request.setAttribute("listProduct", list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void productClearForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("product", new Product());
			request.setAttribute("listCategory", categoryDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* End Servlet Product */
	/*
	 * ***********************************************************************************/
	/* Start Servlet Gallery */

	public void galleryfindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Gallery> list = galleryDAO.findAll();
//			
//			for (Gallery gallery : list) {
//				System.out.println("Gallery ID: " + gallery.getId());
//				System.out.println("Gallery Product ID: " + gallery.getProduct().getId());
//				System.out.println("Gallery Thumbnails: " + gallery.getThumbnail());
//			}

			request.setAttribute("listGallery", list);

			request.setAttribute("listProduct", productDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void galleryCreate(HttpServletRequest request, HttpServletResponse response) {
		try {
			
//			String thumbnail = request.getParameter("thumbnail");
//			 boolean hasError = false;
//	     
//	        if (thumbnail == null || thumbnail.isEmpty()) {
//	            request.setAttribute("errorThubnail","Thumbnail cannot be empty.");
//	            hasError = true;
//	        }
//	        if (hasError) {
//	        	request.setAttribute("thumbnail", thumbnail);
//	        	request.setAttribute("listProduct", productDAO.findAll());
//	            productfindAll(request, response);
//	            return;
//	        }
//			
			
			
			File dir = new File(request.getServletContext().getRealPath("/imgs/products"));
			if (!dir.exists()) {
				dir.mkdirs();
			}

			Part photo = request.getPart("thumbnail");
			if (photo != null && photo.getSize() > 0) {
				String photoFileName = photo.getSubmittedFileName();
				File photoFile = new File(dir, photoFileName);
				photo.write(photoFile.getAbsolutePath());
				request.setAttribute("thumbnail", photoFileName);

				Gallery gallery = new Gallery();
				gallery.setThumbnail(photoFileName);

				String product = request.getParameter("product");

				if (product != null && !product.isEmpty()) {
					try {
						int id = Integer.parseInt(product);
						Product pr = productDAO.findByID(id);
						if (pr != null) {
							gallery.setProduct(pr);
						} else {
							throw new IllegalArgumentException("Product not found");
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						request.setAttribute("errorMessage", "Invalid product ID format.");
						return;
					}
				}

				galleryDAO.create(gallery);

				System.out.println("Thêm Thành Công");
				galleryfindAll(request, response);

			} else {
				request.setAttribute("errorMessage", "No file uploaded or file is empty.");
			}
		} catch (Exception e) {
			System.out.println("Thêm thất bại: " + e.getMessage());
			request.setAttribute("errorMessage", "Error while adding gallery.");
			request.setAttribute("listProduct", productDAO.findAll());
			e.printStackTrace();
		}
	}

	public void galleryUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("Chạy đến hàm Update");

			int id = Integer.parseInt(request.getParameter("id"));
			Gallery gl = galleryDAO.findByID(id);
			request.setAttribute("galleryEdit", gl);
			System.out.println("GalleryEdit: " + gl);
			if (gl != null) {
				int galleryId = gl.getProduct().getId();
				System.out.println("ProductID_Update: " + galleryId);

			}

			if (gl != null) {
				String productString = request.getParameter("product");
				if (productString != null && !productString.isEmpty()) {
					try {
						int productID = Integer.parseInt(productString);
						Product idProduct = productDAO.findByID(productID);
						if (idProduct != null) {
							gl.setProduct(idProduct);
							System.out.println("ID Product: " + idProduct);
						} else {
							System.out.println("Product not found with ID: " + productID);
							request.setAttribute("error", "Product not found.");
							return;
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid product ID format: " + productString);
						request.setAttribute("error", "Invalid product ID format.");
						return;
					}
				} else {
					System.out.println("Product parameter is missing or empty.");
					request.setAttribute("error", "Product parameter is missing.");
					return;
				}

				Part photo = request.getPart("thumbnail");
				if (photo != null && photo.getSize() > 0) {
					String photoFileName = photo.getSubmittedFileName();
					String contextPath = request.getServletContext().getRealPath("/imgs/products");
					File dir = new File(contextPath);

					System.out.println("Image Path: " + contextPath);

					if (!dir.exists()) {
						dir.mkdirs();
					}
					File photoFile = new File(dir, photoFileName);
					photo.write(photoFile.getAbsolutePath());

					gl.setThumbnail(photoFileName);
				}
				System.out.println("Thumbnail: " + gl.getThumbnail());
				galleryDAO.update(gl);

				System.out.println("Sửa Thành Công");
				galleryfindAll(request, response);

			} else {
				System.out.println("Không tìm thấy gallery với ID: " + id);
				request.setAttribute("error", "Gallery not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sửa Thất Bại: " + e.getMessage());
			request.setAttribute("errorMessage", "Error while updating gallery.");
		}
	}

	public void galleryEdit(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Gallery gl = galleryDAO.findByID(id);

			request.setAttribute("galleryEdit", gl);

			System.out.println("Edit ID: " + gl);
			request.setAttribute("listProduct", productDAO.findAll());
			productItemfindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void galleryDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			galleryDAO.delete(id);
			System.out.println("Xóa Thành Công");
			galleryfindAll(request, response);

		} catch (Exception e) {
			System.out.println("Xóa Thất Bại" + e.getMessage());
			e.printStackTrace();

		}
	}

	
	public void gallerySearchByID(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        int id = Integer.parseInt(request.getParameter("id"));
	        List<Gallery> gallery = galleryDAO.findByKeyWord(id);
	        
	        if (gallery != null) {
	            request.setAttribute("gallery", gallery);
	        } else {
	            request.setAttribute("errorMessage", "Gallery not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "An error occurred while searching for the gallery.");
	    }
	}

	 

	public void galleryClearForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("gallery", new Gallery());

			request.setAttribute("listProduct", productDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* End Servlet Gallery */
	/*
	 * ***********************************************************************************/
	/* Start Servlet Stock */
	
	public void productItemfindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<ProductItem> list = productItemDao.findAll();
			

			request.setAttribute("listProductItem", list);
			request.setAttribute("listCategory", categoryDAO.findAll());
			request.setAttribute("listProduct", productDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productItemEdit(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		ProductItem pi = productItemDao.findByID(id);
		System.out.println(pi);
		request.setAttribute("piEdit", pi);
		request.setAttribute("listCategory", categoryDAO.findAll());
		request.setAttribute("listProduct", productDAO.findAll());
	}
	
	public void productItemUpdateOrInsert(HttpServletRequest request, HttpServletResponse response) {
		int productId = Integer.parseInt(request.getParameter("product"));
		int qtyInStock = Integer.parseInt(request.getParameter("qty_in_stock"));
		float price = Float.parseFloat(request.getParameter("price"));
		float originalPrice = Float.parseFloat(request.getParameter("original_price"));
		try {
			productItemDao.updateOrInsertProductItem(productId, qtyInStock, price, originalPrice);
			productItemfindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loi :"+e.getMessage());
		}
		
		
	}
	
	public void productItemDelete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			productItemDao.deleteProductItem(id);
			productItemfindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loi :"+e.getMessage());
		}
	}
	
	public void productItemClearForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("piEdit", new ProductItem());
		productfindAll(request, response);
		galleryfindAll(request, response);
	}
}
