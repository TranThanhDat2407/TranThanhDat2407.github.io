package controllers.Client.profile;

import jakarta.servlet.ServletException;
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
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ClientFGServlet
 */
@WebServlet({"/client/OTP","/client/OTP/reOTP"})
public class ClientOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO dao = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String accountID = CookiesUtils.get("accountID", request);
//		request.setAttribute("accountID", accountID);
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/OTP.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String OTP = request.getParameter("OTP");
		String email = CookiesUtils.get("FGEmail", request);
		User user = dao.findByEmail(email);
		String uri = request.getRequestURI();
		String lang = (String) request.getSession().getAttribute("lang");
		if(uri.contains("reOTP")) {
			try {
			     request.setAttribute("errorMessage", "");
				 generateOneTimePassword(user);
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
				    		+ "Mã OTP: "+ user.getOTP()+"\r\n"
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
					request.getRequestDispatcher("/views/ClientTemplate/pages/account/OTP.jsp").forward(request, response); 
				 	}catch(Exception e) {
				 		e.printStackTrace();
				    }     	
		}else {
		
		 if (user != null && EncryptUtils.checkPassword(OTP, user.getOTP()) && user.isOTPRequired()) {
			 try {
				 
				 String newPassword = generateRandomPassword();
				 String encryptedPassword = EncryptUtils.hashPassword(newPassword);
				 user.setPassword(encryptedPassword);
				 String qrCodeFileName = "";
				  if (user.getQR() != null && !user.getQR().isEmpty()) {
					   qrCodeFileName = updateUserQRImage(user,newPassword, request, response);
			        } else {
			           qrCodeFileName = createUserQRImage(user,newPassword, request, response);
			          
			        }
				  System.out.println(qrCodeFileName);
				 user.setQR(qrCodeFileName);
				 user.setOTP(OTP);
				 dao.update(user);
				 
				 generateOneTimePassword(user);
				 
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
			    message.setSubject("Lấy Lại Mật Khẩu");
	
			    MimeBodyPart messageBodyPart = new MimeBodyPart();

			    messageBodyPart.setText("\"Xin chào,\r\n\"\r\n"
	                    + "\r\n"
	           
	                    + "Chúng tôi vừa nhận được yêu cầu lấy lại mật khẩu tài khoản cho bạn tại trang http://localhost:8080/Project-Fruit/client với thông tin tài khoản như sau:\r\n"
	               
	                    + "Email: " + user.getEmail() + "\r\n"
	                
	                    + "Mật Khẩu Mới: " + newPassword + "\r\n"
	              
	                    + "Vui lòng nhấp vào nút bên dưới để lấy lại mật khẩu tài khoản trong vòng 60 phút.\r\n"
	                  
	                    + "Nếu bạn không phải là người yêu cầu lấy lại mật khẩu, vui lòng bỏ qua email này.\r\n"
	                    + "\r\n"
	                   
	                    + "We have received your request to reset password to access http://localhost:8080/Project-Fruit/client with the following information:\r\n"
	                  
	                    + "Email: " + user.getEmail() + "\r\n"
	                   
	                    + "Mật Khẩu Mới: " + newPassword + "\r\n"
	                    + "<br>\r\n"
	                    + "Đây là email tự động được gửi từ hệ thống. Quí khách vui lòng không phản hồi lại email này!");
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
			    resetOneTimePassword(user);
			    response.sendRedirect(request.getContextPath() + "/client/signin");
			    
			 	}catch(Exception e) {
			 		e.printStackTrace();
			    }     	
	     } else if(!user.isOTPRequired()) {
	    	 try {
				resetOneTimePassword(user);
			
				   if (lang.equals("vi")) {
						request.setAttribute("errorMessage", "Mã OTP của bạn đã hết hạn!");
		            } else {
		                request.setAttribute("errorMessage", "Your OTP is expire!");
		            }
				request.getRequestDispatcher("/views/ClientTemplate/pages/account/OTP.jsp").forward(request, response); 
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
	     }
		 else {
			 if (lang.equals("vi")) {
					request.setAttribute("errorMessage", "Mã OTP không hợp lệ!");
	            } else {
	                request.setAttribute("errorMessage", "Invalid OTP Code!");
	            }
	            request.getRequestDispatcher("/views/ClientTemplate/pages/account/OTP.jsp").forward(request, response); 
	     }
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
	
	public void generateOneTimePassword(User user)
	        throws UnsupportedEncodingException, MessagingException {
	    String OTP = generateRandomPassword();
	     
	    user.setOTP(OTP);
	    user.setOtpRequestedTime(new Date());
	     
	    dao.update(user);     
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

	
	private String updateUserQRImage(User user,String password, HttpServletRequest request, HttpServletResponse response) {
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
	
	public void resetOneTimePassword(User user)
	        throws UnsupportedEncodingException, MessagingException {
	     
	    user.setOTP(null);
	    user.setOtpRequestedTime(null);
	     
	    dao.update(user);     
	}
	
	private Hashtable<EncodeHintType, Object> generateQRCodeHints() {
	    Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
	    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	    return hints;
	}
	

	
	

}
