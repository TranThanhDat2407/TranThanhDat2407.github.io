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
 * Servlet implementation class ClientChangePasswordServlet
 */
@WebServlet("/client/profile/change_password")
public class ClientChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientChangePasswordServlet() {
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

		if (accountID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/signup");
			return;
		}

		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/ChangePassword.jsp")
		.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountID = CookiesUtils.get("accountID", request);
		request.setAttribute("accountID", accountID);
		 String lang = (String) request.getSession().getAttribute("lang");
		User user = userDAO.findByID(Integer.parseInt(accountID));
		request.setAttribute("user", user);

		String password = request.getParameter("password");
		String newPassword = request.getParameter("newpassword");
		String reNewPassword = request.getParameter("renewpassword");
		boolean hasError = false;

		if (password == null || password.trim().isEmpty()) {
			hasError = true;
			 if (lang.equals("vi")) {
				 request.setAttribute("ErrorPassword", "Mật khẩu cũ không được để trống!");
	            } else {
	            	request.setAttribute("ErrorPassword", "Please fill the old password!");
	            }
		} else if (!EncryptUtils.checkPassword(password, user.getPassword())) {
			hasError = true;
			 if (lang.equals("vi")) {
				 request.setAttribute("ErrorPassword", "Mật khẩu cũ không khớp!");
	            } else {
	            	request.setAttribute("ErrorPassword", "Invalid password!");
	            }
		}

		if (newPassword == null || newPassword.trim().isEmpty()) {
			hasError = true;
			 if (lang.equals("vi")) {
				 request.setAttribute("ErrorNewPassword", "Mật khẩu mới không được để trống!");
	            } else {
	            	request.setAttribute("ErrorNewPassword", "Please fill the new password!");
	            }
			
		} else if (!newPassword.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6}$")) {
			hasError = true;
			 if (lang.equals("vi")) {
				 request.setAttribute("ErrorNewPassword",
							"Mật khẩu phải có ít nhất 6 kí tự và ít nhất 1 chữ số, 1 kí tự viết hoa!");
     	    } else {
     	        request.setAttribute("ErrorNewPassword", "The password must be at least 6 characters long and contain at least one uppercase letter and one digit!");
     	    }
			
		}

		if (reNewPassword == null || reNewPassword.trim().isEmpty()) {
			hasError = true;
			 if (lang.equals("vi")) {
				 request.setAttribute("ErrorReNewPassword", "Vui lòng xác nhận mật khẩu!");
	            } else {
	            	request.setAttribute("ErrorReNewPassword", "Please confirm the password!");
	            }
			
		}
		if (!reNewPassword.equals(newPassword)) {
			hasError = true;
			 if (lang.equals("vi")) {
				 request.setAttribute("ErrorReNewPassword", "Xác nhận mật khẩu không khớp!");
	            } else {
	            	request.setAttribute("ErrorReNewPassword", "passphrase does not match!");
	            }
			
		}

		if (hasError) {
			request.getRequestDispatcher("/views/ClientTemplate/pages/account/Profile/ChangePassword.jsp")
					.forward(request, response);
		} else {
			try {
			user.setPassword(EncryptUtils.hashPassword(newPassword));
			String qrCodeFileName = createUserQRImage(user,newPassword, request, response);
		    user.setQR(qrCodeFileName);
			userDAO.update(user);

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
		    Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		    message.setSubject("Đổi mật khẩu");
 
		    MimeBodyPart messageBodyPart = new MimeBodyPart();

		    messageBodyPart.setText("Thành Viên " + user.getEmail()
		    		+ " vừa đổi mật khẩu. Đính kèm là QR code của bạn.");
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
			
		    CookiesUtils.add( "accountID", "", 0 , response);
		    response.sendRedirect(request.getContextPath() + "/client/signin");
			}catch (Exception e) {
				// TODO: handle exception
			}

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
	
	private Hashtable<EncodeHintType, Object> generateQRCodeHints() {
	    Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
	    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	    return hints;
	}
	

}
