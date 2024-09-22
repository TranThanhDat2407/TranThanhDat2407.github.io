package entity;


import java.util.Date;

import javax.persistence.*;
import javax.persistence.Table;

import DAO.UserDAO;


@Entity
@Table(name = "[User]")

public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone", unique=true)
	private String phone;
	
	@Column(name = "email", unique=true)
	private String email;
	
	@Column(name = "role")
	private boolean role;
	
	@Column(name = "create_at")
	private Date create_at;
	
	@Column(name = "update_at")
	private Date update_at;
	
	@Column(name = "one_time_password")
	private String OTP;
	
	@Column(name = "otp_requested_time")
	private Date otpRequestedTime;
	
	@Column(name = "QR_IMG")
	private String QR;
	
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
	
	public boolean isOTPRequired() {
        if (this.getOTP() == null) {
            return false;
        }
         
        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();
         
        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }
         
        return true;
    }
	
	 public static void main(String[] args) {
	        UserDAO dao = new UserDAO();
	        User u = dao.findByEmail("datttps37451@fpt.edu.vn");

	        Boolean a = u.isOTPRequired();
	        System.out.println(new Date());
	        
	        System.out.println(u.getOtpRequestedTime());

	        System.out.println(a);
	    }
	
	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	public String getQR() {
		return QR;
	}

	public void setQR(String qR) {
		QR = qR;
	}

	public static long getOtpValidDuration() {
		return OTP_VALID_DURATION;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	public Date getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}
	
	
	
	
	
}
