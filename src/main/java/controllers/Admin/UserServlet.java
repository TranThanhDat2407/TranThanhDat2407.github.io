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


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import DAO.UserDAO;
import DAO.User_AddressDAO;
import entity.Address;
import entity.User;
import entity.UserAddressId;
import entity.User_Address;


@WebServlet({"/admin/user","/admin/user/edit", "/admin/user/update", "/admin/user/delete", 
	"/admin/user/create" ,"/admin/user/search","/admin/user/export_excel","/admin/user/import_excel",
	"/admin/user/address/edit","/admin/user/address/update", "/admin/user/address/delete",
	"/admin/user/address/create","/admin/user/address/search"})
/**
 * Servlet implementation class UserServlet
 */
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15 // 15 MB
)
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	User_AddressDAO userAddressDAO = new User_AddressDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		addressfindAll(request, response);
		userfindAll(request, response); 
		
		if(uri.contains("admin/user/delete")) {
			userDelete(request,response);
			request.setAttribute("user", new User());
		}
		if(uri.contains("admin/user/address/delete")) {
			addressDelete(request, response);
		}
		if(uri.contains("admin/user/edit")) {
			userEdit(request,response);
		}
		if(uri.contains("admin/user/address/edit")) {
			addressEdit(request, response);
		}
		if (uri.contains("admin/user/search")) {
			userSearchByName(request, response);
		}
		if (uri.contains("admin/user/address/search")) {
			addressSearchByName(request, response);
		}
		if (uri.equals("admin/user")) {
			userClearForm(request, response);
		}
		if (uri.contains("admin/user/export_excel")) {
			doExportUsers(request, response);
		}

		request.getRequestDispatcher("/views/AdminTemplate/pages/Users/users.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		if (uri.contains("/admin/user/create")) {
			userCreate(request, response);
		}
		if (uri.contains("/admin/user/address/create")) {
			addressCreate(request, response);
		}
		if(uri.contains("/admin/user/update")) {
			userUpdate(request,response); 
		}
		if(uri.contains("/admin/user/address/update")) {
			addressUpdate(request, response);
		}
		if(uri.contains("/admin/user/delete")) {
			userDelete(request,response);
			request.setAttribute("user", new User());
		}
		if(uri.contains("admin/user/address/delete")) {
			addressDelete(request, response);
		
		}
		if (uri.contains("/admin/user/search")) {
			userSearchByName(request, response);
		}
		if (uri.contains("admin/user/address/search")) {
			addressSearchByName(request, response);
		}
		if (uri.equals("admin/user")) {
			userClearForm(request, response);
		}
		
		if(uri.contains("/admin/user/import_excel")) {
			doImportStudent(request, response);
		}
		
		doGet(request, response);
	}

	public void userfindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> list = userDAO.findAll();
			request.setAttribute("listUser", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addressfindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User_Address> listAddress = userAddressDAO.findAllUserAddresses();
			request.setAttribute("listAddress", listAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void userCreate(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	
	    	String name = request.getParameter("name");
			String phone = request.getParameter("phone");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String role = request.getParameter("role");
	        boolean hasError = false;
	        if (name == null || name.trim().isEmpty()) {
	            hasError = true;
	            request.setAttribute("ErrorName", "Please fill the name!");
	        }
	        
	        //validate phone
	        if (phone == null || phone.trim().isEmpty()) {
	            hasError = true;
	         
	                request.setAttribute("ErrorPhone", "Please fill the phone number!");
	            
	         
	        }else if(!phone.matches("(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b")) {
	        	hasError = true;
	        	
	        	        request.setAttribute("ErrorPhone", "The phone number is not in the correct format!");
	        	    
	        }
	        
	        //validate email
	        User user1 = userDAO.findByEmail(email);
	        if (email == null || email.trim().isEmpty()) {
	            hasError = true;
	            
	                request.setAttribute("ErrorEmail", "Please fill the email!");
	                 
	        }else if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	        	hasError = true;
	        	
	                request.setAttribute("ErrorEmail", "The email is not in the correct format!");
	            
	        }else if(user1 != null) {
	        	hasError = true;
	       
	                request.setAttribute("ErrorEmail", "The email is already exist!");
	            
	        }
	        
	        //validate password
	        if (password == null || password.trim().isEmpty()) {
	            hasError = true;
	            
	            	request.setAttribute("ErrorPassword", "Please fill the password!"); 
	            
	                    
	        }else if(!password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
	        	hasError = true;
	        	 
	        	        request.setAttribute("ErrorPassword", "The password must be at least 6 characters long and contain at least one uppercase letter and one digit!");
   
	        }
	 
	        if (hasError) {
	            userfindAll(request, response);
		    } else {
	        User user = new User();
	        BeanUtils.populate(user, request.getParameterMap());
	        user.setCreate_at(new Date());
	        user.setUpdate_at(new Date());
			user.setPassword(EncryptUtils.hashPassword(user.getPassword()));
			String qrCodeFileName = createUserQRImage(user,request.getParameter("password"), request, response);
		    user.setQR(qrCodeFileName);
	        userDAO.create(user);

	        System.out.println("Thêm Thành Công");
	        userfindAll(request, response);
		    }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Thêm Thất Bại");
	    }
	}
	
	public void addressCreate(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	String city= request.getParameter("cityal");
	    	String userID = request.getParameter("userIDal");
	
	    	String ward = request.getParameter("wardal");
	    	String street = request.getParameter("streetal");
	    	String isDefault = request.getParameter("isDefaultal");
	    	
	    	System.out.println(userID);
	    	System.out.println(city);
	    	System.out.println(ward);
	    	System.out.println(street);
	    	System.out.println(isDefault);
	    	userAddressDAO.createAddressAdmin(Integer.parseInt(userID),city , ward, street, Boolean.parseBoolean(isDefault));
	    	
	        System.out.println("Thêm Thành Công");
	        addressfindAll(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Thêm Thất Bại");
	    }
	}
	
	
	private void userUpdate(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	  
	    try {
	    	
	    	String name = request.getParameter("name");
			String phone = request.getParameter("phone");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String role = request.getParameter("role");
	        String id = request.getParameter("id");
	        boolean hasError = false;
	        
	        if (id == null || id.trim().isEmpty()) {
	            hasError = true;
	            request.setAttribute("ErrorID", "Please select the user!");
	        }
	        
	        if (name == null || name.trim().isEmpty()) {
	            hasError = true;
	            request.setAttribute("ErrorName", "Please fill the name!");
	            
	          
	        }
	        
	        //validate phone
	        if (phone == null || phone.trim().isEmpty()) {
	            hasError = true;
	         
	                request.setAttribute("ErrorPhone", "Please fill the phone number!");
	            
	         
	        }else if(!phone.matches("(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b")) {
	        	hasError = true;
	        	
	        	        request.setAttribute("ErrorPhone", "The phone number is not in the correct format!");
	        	    
	        }
	        
	        //validate email
	        User user1 = userDAO.findByEmail(email);
	        if (email == null || email.trim().isEmpty()) {
	            hasError = true;
	            
	                request.setAttribute("ErrorEmail", "Please fill the email!");
	                 
	        }else if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	        	hasError = true;
	        	
	                request.setAttribute("ErrorEmail", "The email is not in the correct format!");
	            
	        }else if(user1 != null) {
	        	hasError = true;
	       
	                request.setAttribute("ErrorEmail", "The email is already exist!");
	            
	        }
	        
	        //validate password
	        if (password == null || password.trim().isEmpty()) {
	            hasError = true;
	            
	            	request.setAttribute("ErrorPassword", "Please fill the password!"); 
	            
	                    
	        }else if(!password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
	        	hasError = true;
	        	 
	        	        request.setAttribute("ErrorPassword", "The password must be at least 6 characters long and contain at least one uppercase letter and one digit!");
   
	        }
	        
	        if(hasError) {
	        	userfindAll(request, response);
	        }else {
	  	    int userId = Integer.parseInt(request.getParameter("id"));
	  	    
	  	    User user = userDAO.findByID(userId);
	        
	        User updatedUser = new User();
	        updatedUser.setId(user.getId());
	        updatedUser.setCreate_at(user.getCreate_at());
	        updatedUser.setUpdate_at(new Date());
	    
	        BeanUtils.populate(updatedUser, request.getParameterMap());
	        updatedUser.setPassword(EncryptUtils.hashPassword(updatedUser.getPassword())); 
	        
	        if (user.getQR() != null && !user.getQR().isEmpty()) {
	            updateUserQRImage(user,request.getParameter("password"), request, response);
	        } else {
	            String qrCodeFileName = createUserQRImage(user,request.getParameter("password"), request, response);
	            updatedUser.setQR(qrCodeFileName);
	        }
	        
	        userDAO.update(updatedUser);
	        System.out.println("Sửa Thành Công");
	        userfindAll(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Sửa Thất Bại");
	    }
	
}
	private void addressUpdate(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	  
	    try {
	    	String city= request.getParameter("cityal");
	    	String userID = request.getParameter("userIDal");
	    	String addressID = request.getParameter("addressID");
	    	String ward = request.getParameter("wardal");
	    	String street = request.getParameter("streetal");
	    	String isDefault = request.getParameter("isDefaultal");
	
	    	userAddressDAO.updateUserAddressAdmin(Integer.parseInt(userID),Integer.parseInt(addressID),city,ward,street,Boolean.parseBoolean(isDefault));
	    	
	        addressfindAll(request, response);   
	        System.out.println("Sửa Thành Công");

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Sửa Thất Bại");
	    }
	}
	
	public void userDelete(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	userDAO.delete(id);
	        System.out.println("Xóa Thành Công");
	        userfindAll(request, response);
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Xóa Thất Bại");
	       
	    }
	}
	
	public void addressDelete(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	String id = request.getParameter("id");
			String[] parts = id.split(" ");
			String userId = parts[0];
			String addressId = parts[1];
	    	userAddressDAO.deleteUserAddress(Integer.parseInt(userId),Integer.parseInt(addressId));
	        System.out.println("Xóa Thành Công");
	        addressfindAll(request, response);
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Xóa Thất Bại");
	       
	    }
	}
	
	public void userEdit(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	int id = Integer.parseInt(request.getParameter("id"));
	        User user = userDAO.findByID(id);
	        request.setAttribute("user", user);
	    } catch (Exception e) {
	        e.printStackTrace();  
	    }
	}
	
	public void addressEdit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String[] parts = id.split(" ");
		String userId = parts[0];
		String addressId = parts[1];

		try {
			  User user = userDAO.findByID(Integer.parseInt(userId));
			  Address address = userAddressDAO.findByID(Integer.parseInt(addressId));
			    
			  UserAddressId userAddressId = new UserAddressId(user, address);
		    
		    User_Address ua = userAddressDAO.findByUserAddressId(userAddressId);
		    request.setAttribute("ua", ua);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void userSearchByName(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String findname = request.getParameter("findname");
	        List<User> list2 = userDAO.findByKeyWord(findname);
			request.setAttribute("listUser", list2);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void addressSearchByName(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String findnamea = request.getParameter("findnamea");
			List<User_Address> listAddress = userAddressDAO.findByUserName(findnamea);
			request.setAttribute("listAddress", listAddress);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void userClearForm(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        request.setAttribute("user", new User());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void doExportUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Export student to excel ******");
		
			try {
				List<User> listUsers = new ArrayList<>();
				XSSFWorkbook workbook = new XSSFWorkbook();
				
				listUsers = userDAO.findAll();
				XSSFSheet sheet = workbook.createSheet("Users");
				sheet.setColumnWidth(0, 6000);
				sheet.setColumnWidth(1, 4000);
				sheet.setColumnWidth(2, 8000);
				sheet.setColumnWidth(3, 5000);
				sheet.setColumnWidth(4, 8000);
				sheet.setColumnWidth(5, 8000);
				sheet.setColumnWidth(6, 8000);
				sheet.setColumnWidth(7, 8000);
				sheet.setColumnWidth(8, 8000);
				Row header = sheet.createRow(0);
				
				CellStyle headerStyle = workbook.createCellStyle();
				headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
				headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
				XSSFFont font = ((XSSFWorkbook) workbook).createFont();
				font.setFontName("Arial");
				font.setFontHeightInPoints((short) 16);
				font.setBold(true);
				headerStyle.setFont(font);
				
				Cell headerCell = header.createCell(0);
				headerCell.setCellValue("No.");
				headerCell.setCellStyle(headerStyle);
	
				headerCell = header.createCell(1);
				headerCell.setCellValue("User ID");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(2);
				headerCell.setCellValue("Password");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(3);
				headerCell.setCellValue("User Name");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(4);
				headerCell.setCellValue("Phone");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(5);
				headerCell.setCellValue("Email");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(6);
				headerCell.setCellValue("Role");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(7);
				headerCell.setCellValue("Create_at");
				headerCell.setCellStyle(headerStyle);
				
				headerCell = header.createCell(8);
				headerCell.setCellValue("Update_at");
				headerCell.setCellStyle(headerStyle);
				
				CellStyle style = workbook.createCellStyle();
				style.setWrapText(true);
				int rowIndex = 1;
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				for(User u:listUsers) {
					Row row = sheet.createRow(rowIndex);
					Cell cell = row.createCell(0);
					cell.setCellValue(rowIndex);
					cell.setCellStyle(style);
					
					cell = row.createCell(1);
					cell.setCellValue(u.getId());
					cell.setCellStyle(style);
					
					cell = row.createCell(2);
					cell.setCellValue(u.getPassword());
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(u.getName());
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(u.getPhone());
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(u.getEmail());
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(u.isRole()? "Admin":"User");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(simpleDateFormat.format(u.getCreate_at()));
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(simpleDateFormat.format(u.getUpdate_at()));
					cell.setCellStyle(style);
					rowIndex++;
					System.out.println(u.getName());
				}
				

				String path = "/views/excel/UserList.xlsx";
				
				String fileLocationAndName = request.getServletContext().getRealPath(path);

				System.out.println(fileLocationAndName);
				FileOutputStream outputStream = new FileOutputStream(fileLocationAndName);
				workbook.write(outputStream);
				workbook.close();
				outputStream.close();
//				request.getRequestDispatcher(path).forward(request, response);
				request.getRequestDispatcher("/admin/user").forward(request, response);
			}catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			
			}
	}			
			private void doImportStudent(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
			
					if(request.getParameter("importUser") != null) {
						Part userExcelFile = request.getPart("userExcelFile");
				
						List<User> listUser = new ArrayList<>();
						
						if(userExcelFile != null && userExcelFile.getSize() > 0) {
							
							String path = "/views/excel/" + userExcelFile.getSubmittedFileName();
							
							String fileName = request.getServletContext().getRealPath(path);
							
							userExcelFile.write(fileName);
							
							System.out.println(fileName);
							DataFormatter formatter = new DataFormatter();
						
							FileInputStream file = new FileInputStream(new File(fileName));
							XSSFWorkbook workbook = new XSSFWorkbook(file);
							XSSFSheet sheet = workbook.getSheetAt(0);
							Iterator<Row> rowIterator = sheet.iterator();
							System.out.println(workbook);
							System.out.println(rowIterator);
							try {
								//skip the first row - header row
								if(rowIterator.hasNext())
									rowIterator.next();
								while (rowIterator.hasNext()) 
								{
									Row row = rowIterator.next();
									User u = new User();
//									u.setId(Integer.parseInt(row.getCell(0).getStringCellValue()));
									
									u.setPassword(EncryptUtils.hashPassword(row.getCell(2).getStringCellValue()));
//									u.setPassword(EncryptUtils.hashPassword(String.valueOf(row.getCell(2).getNumericCellValue())));
									
									u.setName(row.getCell(3).getStringCellValue());
								
						            u.setPhone(row.getCell(4).getStringCellValue());
// 								    u.setPhone(String.valueOf(row.getCell(4).getNumericCellValue()));
 								    
									u.setEmail(row.getCell(5).getStringCellValue());
									u.setRole(row.getCell(6).getStringCellValue().equals("Admin") ? true : false);
								//System.out.println(row.getCell(4).getStringCellValue() + row.getCell(5).getStringCellValue() + row.getCell(6).getStringCellValue());	
									u.setCreate_at(new Date());
									u.setUpdate_at(new Date());
//									birthday = row.getCell(3).getDateCellValue().toString();
//									System.out.println("Birthday: " + birthday);
//									st.setBirthday(row.getCell(3).getDateCellValue());
									String qrCodeFileName = createUserQRImage(u,row.getCell(2).getStringCellValue(), request, response);
								    u.setQR(qrCodeFileName);
									userDAO.create(u);
								
									listUser.add(u);
									System.out.println("User ID: "+ u.getId());								
									}
								file.close();
							}catch(Exception e) {
								System.out.println(e.getMessage());


							}
						}
						request.setAttribute("listUser", listUser);
					}	
			}
			
//			private void createUserQRImage(User user, HttpServletRequest request, HttpServletResponse response) {
//			    try {
//			        // Generate the QR code image
//			        BufferedImage qrCodeImage = generateQRCodeImage(user.getEmail(), 200, 200);
//
//			        // Save the QR code image to a file
//			        String qrCodeFilePath = "D:\\FPT Polytechnic\\Ki4\\JAVA4\\Project-Fruit\\Project-Fruit\\src\\main\\webapp\\views\\QRCode\\Users\\" + user.getEmail() + ".png";
//			        File qrCodeFile = new File(qrCodeFilePath);
//			        ImageIO.write(qrCodeImage, "png", qrCodeFile);
//
//			        user.setQR(qrCodeFilePath);
//			        userDAO.update(user);
//			        
//			    } catch (WriterException | IOException e) {
//			        e.printStackTrace();
//			    }
//			}
			
			private String createUserQRImage(User user ,String password, HttpServletRequest request, HttpServletResponse response) {
			    try {
			        // Generate the QR code image
			        BufferedImage qrCodeImage = generateQRCodeImage(user.getEmail()+ " " + password , 200, 200);

			        // Save the QR code image to a file
			        String qrCodeFileName = "User" + user.getEmail() + ".png";
			        String qrCodeFilePath = "D:\\FPT Polytechnic\\Ki4\\JAVA4\\Project-Fruit\\Project-Fruit\\src\\main\\webapp\\views\\QRCode\\Users\\"  + qrCodeFileName;
			        File qrCodeFile = new File(qrCodeFilePath);
			 
			        File dir = new File(request.getServletContext().getRealPath("/files"));
			        if (!dir.exists()) {
			            dir.mkdirs();
			        }
			        File qrCodeFile1 = new File(dir, qrCodeFileName);
			        ImageIO.write(qrCodeImage, "png", qrCodeFile);
			        ImageIO.write(qrCodeImage, "png", qrCodeFile1);
			        return qrCodeFileName;
			    } catch (WriterException | IOException e) {
			        e.printStackTrace();
			        return null;
			    }
			}

			private void updateUserQRImage(User user,String password, HttpServletRequest request, HttpServletResponse response) {
			    try {
			        
			    	BufferedImage qrCodeImage = generateQRCodeImage(user.getEmail()+ " " + password , 200, 200);

			    	String qrCodeFileName = "User" + user.getEmail() + ".png";
			    	String qrCodeFilePath = "D:\\FPT Polytechnic\\Ki4\\JAVA4\\Project-Fruit\\Project-Fruit\\src\\main\\webapp\\views\\QRCode\\Users\\"  + qrCodeFileName;
			    	File qrCodeFile = new File(qrCodeFilePath);
				    
				    File dir = new File(request.getServletContext().getRealPath("/files"));
			        if (!dir.exists()) {
			            dir.mkdirs();
			        }
			        File qrCodeFile1 = new File(dir, qrCodeFileName);
			        ImageIO.write(qrCodeImage, "png", qrCodeFile);
			        ImageIO.write(qrCodeImage, "png", qrCodeFile1);
			    } catch (WriterException | IOException e) {
			        e.printStackTrace();
			    }
			}
			
			private BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException {
			    QRCodeWriter qrCodeWriter = new QRCodeWriter();
			    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, generateQRCodeHints());
			    BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			    Graphics2D graphics = qrCodeImage.createGraphics();
			    graphics.setColor(Color.WHITE);
			    graphics.fillRect(0, 0, width, height);
			    graphics.setColor(Color.BLACK);
			    for (int x = 0; x < width; x++) {
			        for (int y = 0; y < height; y++) {
			            if (bitMatrix.get(x, y)) {
			                graphics.fillRect(x, y, 1, 1);
			            }
			        }
			    }
			    graphics.dispose();
			    return qrCodeImage;
			}

			private Hashtable<EncodeHintType, Object> generateQRCodeHints() {
			    Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
			    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			    return hints;
			}
			
	}
	
	

