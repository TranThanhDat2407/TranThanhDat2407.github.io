package controllers.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CookiesUtils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@WebServlet("/client/contact")
/**
 * Servlet implementation class ClientContactServlet
 */
public class ClientContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientContactServlet() {
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
		request.getRequestDispatcher("/views/ClientTemplate/pages/contact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String subject = request.getParameter("subject");
		String body = request.getParameter("message");
	    String lang = (String) request.getSession().getAttribute("lang");
		boolean hasError = false;

		if (name == null || name.trim().isEmpty()) {
            hasError = true;
            if (lang.equals("vi")) {
                request.setAttribute("ErrorName", "Tên không được để trống!");
            } else {
                request.setAttribute("ErrorName", "Please fill the name!");
            }
        }
		
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
		
		if (email == null || email.trim().isEmpty()) {
            hasError = true;
            if (lang.equals("vi")) {
                request.setAttribute("ErrorEmail", "Email không được để trống!");
            } else {
                request.setAttribute("ErrorEmail", "Please fill the email address!");
            }
		}
        else if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
        	hasError = true;
        	if (lang.equals("vi")) {
                request.setAttribute("ErrorEmail", "Email không đúng định dạng!");
            } else {
                request.setAttribute("ErrorEmail", "The email is not in the correct format!");
            }
        }
		
		if (subject == null || subject.trim().isEmpty()) {
            hasError = true;
            if (lang.equals("vi")) {
            	  request.setAttribute("ErrorSubject", "Tiêu đề không được để trống!"); 
            } else {
            	  request.setAttribute("ErrorSubject", "Please fill the subject!"); 
            }
          
		}
		
		if (body == null || body.trim().isEmpty()) {
            hasError = true;
            if (lang.equals("vi")) {
            	 request.setAttribute("ErrorBody", "Nội dung không được để trống!");
          } else {
        	  request.setAttribute("ErrorBody", "Please fill the content!");
          }
           
		}
    
		if (hasError) {
			request.getRequestDispatcher("/views/ClientTemplate/pages/contact.jsp").forward(request, response);
		} else {

			try {
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
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("datttps37451@fpt.edu.vn"));
				message.setSubject("Liên Hệ");

				String msg = "Tên: " + name + "<br>" + "Email: " + email + "<br>" + "Số Điện Thoại: " + phone + "<br>"
						+ "Tiêu Đề: " + subject + "<br>" + "Nội Dung: " + body;

				MimeBodyPart mimeBodyPart = new MimeBodyPart();
				mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(mimeBodyPart);

				message.setContent(multipart);

				Transport.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/views/ClientTemplate/index.jsp").forward(request, response);
		}

	}

}
