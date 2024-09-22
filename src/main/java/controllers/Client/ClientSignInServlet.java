package controllers.Client;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ClientSignInServlet
 */
@WebServlet("/client/signin")
@MultipartConfig
public class ClientSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO dao = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientSignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String lang = (String) request.getSession().getAttribute("lang");
			Part part = request.getPart("QR");
			System.out.println(part);
			if(part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
				try {
				
					String filePath  = "";
					File dir = new File(request.getServletContext().getRealPath("/files"));
				    if (!dir.exists()) {
				        dir.mkdirs();
				    }
				    File file = new File(dir, part.getSubmittedFileName());
				    part.write(file.getAbsolutePath());
					System.out.println(file.getAbsolutePath());
					filePath = file.getAbsolutePath();
					String charset = "UTF-8";
	
					Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
					hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

					String qrCodeData;
		
					qrCodeData = readQR(filePath, charset, hashMap);
					System.out.println("QRCode output: " + qrCodeData);
					String[] qrCodeParts = qrCodeData.split(" ");
					String email = qrCodeParts[0];
					String password = qrCodeParts[1];
					System.out.println(email + password);
	
		
					User user = dao.findByEmail(email);
					if (user != null && EncryptUtils.checkPassword(password, user.getPassword())) {
						int userId = user.getId();
						CookiesUtils.add("accountID", String.valueOf(userId), 1, response);
	        response.sendRedirect(request.getContextPath() + "/client");
	      } else {
	    	  if (lang.equals("vi")) {
	    		   request.setAttribute("errorMessage", "Mã QR không hợp lệ");
	            } else {
	            	 request.setAttribute("errorMessage", "Invalid QR Code");
	            }
	     
	        request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
	      }
		} catch (NotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	else {
		try {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	       
	        User user = dao.findByEmail(email);
	        
	        if(email.equals("")) {
	        	 
	        	  if (lang.equals("vi")) {
	        		  request.setAttribute("errorMessage", "Sai Email Hoặc Mật Khẩu");
		            } else {
		              request.setAttribute("errorMessage", "Invalid Emai Or Password!");
		            }
		         request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
	        }
	        if(password.equals("")) {
	        	if (lang.equals("vi")) {
	        		  request.setAttribute("errorMessage", "Sai Email Hoặc Mật Khẩu");
		            } else {
		              request.setAttribute("errorMessage", "Invalid Emai Or Password!");
		            }
		         request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
	        }
	        
	        if (user != null && EncryptUtils.checkPassword(password, user.getPassword())) {
	        	int userId = user.getId();
	            CookiesUtils.add( "accountID", String.valueOf(userId), 1 , response);
	        	response.sendRedirect(request.getContextPath() + "/client");
	        }else if(!EncryptUtils.checkPassword(password, user.getPassword())){
	        	if (lang.equals("vi")) {
	        		  request.setAttribute("errorMessage", "Sai Email Hoặc Mật Khẩu");
		            } else {
		              request.setAttribute("errorMessage", "Invalid Emai Or Password!");
		            }
	            request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
	        }  else {
	        	if (lang.equals("vi")) {
	        		  request.setAttribute("errorMessage", "Sai Email Hoặc Mật Khẩu");
		            } else {
		              request.setAttribute("errorMessage", "Invalid Emai Or Password!");
		            }
	            request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	}
	
	 public static String readQR(String path, String charset,
             Map<EncodeHintType, ErrorCorrectionLevel> hashMap)
            		 throws FileNotFoundException, IOException, NotFoundException {
		 BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				 new BufferedImageLuminanceSource(
						 ImageIO.read(new FileInputStream(path)))));
		 Result result = new MultiFormatReader().decode(binaryBitmap);

		 return result.getText();
  

}

}
