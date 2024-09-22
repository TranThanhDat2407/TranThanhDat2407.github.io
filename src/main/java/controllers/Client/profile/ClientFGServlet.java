package controllers.Client.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;
import utils.EncryptUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ClientFGServlet
 */
@WebServlet("/client/forgot_password")
public class ClientFGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO dao = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientFGServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);

		request.getRequestDispatcher("/views/ClientTemplate/pages/account/ForgotPassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		User user = dao.findByEmail(email);
		
		 if (user != null) {
			 try {
//				 String newPassword = generateRandomPassword();
//				 String encryptedPassword = EncryptUtils.hashPassword(newPassword);
//				 user.setPassword(encryptedPassword);
//				 dao.update(user);
				 CookiesUtils.add( "FGEmail", String.valueOf(user.getEmail()), 1 , response);
				 String OTP = generateRandomPassword();
				 generateOneTimePassword(user,OTP);
				 
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
			    message.setSubject("Nhập Mã OTP");
	
			    String msg = "Xin chào,\r\n"
			    		+ "\r\n"
			    		+ "<br>"
			    		+ "Chúng tôi vừa nhận được yêu cầu lấy lại mật khẩu tài khoản cho bạn vào trang http://localhost:8080/Project-Fruit/client với thông tin tài khoản như sau:\r\n"
			    		+ "<br>"
			    		+ "Email: "+ user.getEmail()+"\r\n"
			    		+ "<br>"
			    		+ "Mã OTP: "+ OTP+"\r\n"
			    		+ "<br>"
			    		+ "Vui lòng nhấp vào nút bên dưới để lấy lại mật khẩu tài khoản trong vòng 60 phút.\r\n"
			    		+ "<br>"
			    		+ "Nếu bạn không phải là người yêu cầu lấy lại mật khẩu, vui lòng bỏ qua email này.\r\n"
			    		+ "\r\n"
			    		+ "<br>"
			    		+ "We have received your request to reset password to access http://localhost:8080/Project-Fruit/client with the following information:\r\n"
			    		+ "<br>"
			    		+ "Email: "+ user.getEmail() + "\r\n"
			    		+ "<br>"
			    		+ "OTP Code: "+ user.getOTP() + "\r\n"
			    		+ "<br>"
			    		+ "Đây là email tự động được gửi từ hệ thống. Quí khách vui lòng không phản hồi lại email này!\r\n"
			    		+ "<br>"
			    		+ "This is an automatic email sent from the system. Please do not reply to this email";

			    MimeBodyPart mimeBodyPart = new MimeBodyPart();
			    mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(mimeBodyPart);

			    message.setContent(multipart);

			    Transport.send(message);
			    response.sendRedirect(request.getContextPath() + "/client/OTP");
			 	}catch(Exception e) {
			 		e.printStackTrace();
			    }     	
	     } else {
	            request.setAttribute("errorMessage", "Emai Không Tồn Tại");
	            request.getRequestDispatcher("/views/ClientTemplate/pages/account/ForgotPassword.jsp").forward(request, response); 
	     }
		
	
	}
	
	private String generateRandomPassword() {
	    String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
	    
	    int passwordLength = 5;
	    
	    Random random = new Random();
	    
	    StringBuilder password = new StringBuilder();
	    for (int i = 0; i < passwordLength; i++) {
	        int randomIndex = random.nextInt(charSet.length());
	        password.append(charSet.charAt(randomIndex));
	    }
	    
	    return password.toString();
	}
	
	public void generateOneTimePassword(User user,String OTP)
	        throws UnsupportedEncodingException, MessagingException {
 
	    user.setOTP(EncryptUtils.hashPassword(OTP));
	    user.setOtpRequestedTime(new Date());
	    
	    dao.update(user); 
	}

}
