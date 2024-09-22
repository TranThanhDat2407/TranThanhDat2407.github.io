package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;
import utils.EncryptUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.beanutils.BeanUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ClientSignUpServlet
 */
@WebServlet("/client/signup")
@MultipartConfig
public class ClientSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO dao = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientSignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String repassword = request.getParameter("repassword");
	        boolean hasError = false;
	        String lang = (String) request.getSession().getAttribute("lang");
//	        String lang = request.getParameter("lang");
	        
	        //validate name
	        if (name == null || name.trim().isEmpty()) {
	            hasError = true;
	            if (lang.equals("vi")) {
	                request.setAttribute("ErrorName", "Tên không được để trống!");
	            } else {
	                request.setAttribute("ErrorName", "Please fill the name!");
	            }
	          
	        }
	        
	        //validate phone
	        if (phone == null || phone.trim().isEmpty()) {
	            hasError = true;
	            if (lang.equals("vi")) {
	                request.setAttribute("ErrorPhone", "Số điện thoại không được để trống!");
	            } else {
	                request.setAttribute("ErrorPhone", "Please fill the phone number!");
	            }	
	         
	        }else if(!phone.matches("(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b")) {
	        	hasError = true;
	        	 if (lang.equals("vi")) {
	        	        request.setAttribute("ErrorPhone", "Số điện thoại không đúng định dạng!");
	        	    } else {
	        	        request.setAttribute("ErrorPhone", "The phone number is not in the correct format!");
	        	    }
	        }
	        
	        //validate email
	        User user = dao.findByEmail(email);
	        if (email == null || email.trim().isEmpty()) {
	            hasError = true;
	            if (lang.equals("vi")) {
	                request.setAttribute("ErrorEmail", "Email không được để trống!");
	            } else {
	                request.setAttribute("ErrorEmail", "Please fill the email!");
	            }        
	        }else if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	        	hasError = true;
	        	if (lang.equals("vi")) {
	                request.setAttribute("ErrorEmail", "Email không đúng định dạng!");
	            } else {
	                request.setAttribute("ErrorEmail", "The email is not in the correct format!");
	            }
	        }else if(user != null) {
	        	hasError = true;
	        	if (lang.equals("vi")) {
	                request.setAttribute("ErrorEmail", "Email đã tồn tại!");
	            } else {
	                request.setAttribute("ErrorEmail", "The email is already exist!");
	            }
	        }
	        
	        //validate password
	        if (password == null || password.trim().isEmpty()) {
	            hasError = true;
	            if (lang.equals("vi")) {
	            	request.setAttribute("ErrorPassword", "Password không được để trống!"); 
	            } else {
	            	request.setAttribute("ErrorPassword", "Please fill the password!"); 
	            }
	                    
	        }else if(!password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
	        	hasError = true;
	        	 if (lang.equals("vi")) {
	        	        request.setAttribute("ErrorPassword", "Mật khẩu phải có ít nhất 6 kí tự và ít nhất 1 chữ số, 1 kí tự viết hoa!");
	        	    } else {
	        	        request.setAttribute("ErrorPassword", "The password must be at least 6 characters long and contain at least one uppercase letter and one digit!");
	        	    }
	           
	            
	        }
	       
	        if (repassword == null || repassword.trim().isEmpty()) {
	            hasError = true;
	            if (lang.equals("vi")) {
	                request.setAttribute("ErrorRePassword", "Vui lòng xác nhận lại mật khẩu!");
	            } else {
	                request.setAttribute("ErrorRePassword", "Please confirm the password!");
	            }
	        } else if (!repassword.equals(password)) {
	            hasError = true;
	            if (lang.equals("vi")) {
	                request.setAttribute("ErrorRePassword", "Mật khẩu và xác nhận mật khẩu không khớp!");
	            } else {
	                request.setAttribute("ErrorRePassword", "The password and confirm password do not match!");
	            }
	        }
    
	        if (hasError) {
	        	request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignUp.jsp").forward(request, response);
		    } else {
		    	 try {
		 	        User user1 = new User();
		 	        BeanUtils.populate(user1, request.getParameterMap());
		 	        user1.setCreate_at(new Date());
		 	        user1.setUpdate_at(new Date());
		 	        user1.setPassword(EncryptUtils.hashPassword(password));
		 	        String qrCodeFileName = createUserQRImage(user1,request.getParameter("password"), request, response);
				    user1.setQR(qrCodeFileName);
		 	        dao.create(user1);
		 	        System.out.println("Thêm Thành Công");
		 	        
		 	       Properties properties = new Properties();
				    properties.put("mail.transport.protocol", "smtp");
			        properties.put("mail.smtp.auth", "true");
			        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			        properties.put("mail.smtp.starttls.enable", "true");
			        properties.put("mail.smtp.host", "smtp.gmail.com");

			        properties.put("mail.smtp.port", "587");

				    Session session = Session.getInstance(properties, new Authenticator() {
			            @Override
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
			                return new javax.mail.PasswordAuthentication("datttps37451@fpt.edu.vn", "ampz endc bdsm zqdx");
			            }
			        });
				    
				    
				    Message message = new MimeMessage(session);
				    message.setFrom(new InternetAddress("datttps37451@fpt.edu.vn"));
				    message.setRecipients(
				    Message.RecipientType.TO, InternetAddress.parse(email));
				    message.setSubject("Thành Viên Mới");
		
				    
				    MimeBodyPart messageBodyPart = new MimeBodyPart();

				    messageBodyPart.setText("Cảm ơn " + user1.getEmail()
				    		+ " đã đăng ký tài khoản. Đính kèm là QR code của bạn.");
				    Multipart multipart = new MimeMultipart();
				    MimeBodyPart attachmentPart = new MimeBodyPart();
				    File dir = new File(request.getServletContext().getRealPath("/files"));
			        if (!dir.exists()) {
			            dir.mkdirs();
			        }
				    File file = new File(dir,qrCodeFileName);
				    FileDataSource source = new FileDataSource(file);
				    attachmentPart.setDataHandler(new DataHandler(source));
				    attachmentPart.setFileName(file.getName());

			
				    multipart.addBodyPart(messageBodyPart);
				    multipart.addBodyPart(attachmentPart);

				    message.setContent(multipart);

				    Transport.send(message);
		 	        
		 	    } catch (Exception e) {
		 	        e.printStackTrace();
		 	        System.out.println("Thêm Thất Bại");
		 	    }
		    	request.getRequestDispatcher("/views/ClientTemplate/pages/account/SignIn.jsp").forward(request, response);
		    }
	        
	    } catch (Exception e) {
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
	
	private String createUserQRImage(User user ,String password, HttpServletRequest request, HttpServletResponse response) {
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

	        return qrCodeFileName;
	    } catch (WriterException | IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
