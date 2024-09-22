package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User_Payment_Method")
public class User_Payment_Method {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private Payment_Type paymentType;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(name = "provider")
    private String provider;

    @Column(name = "is_default")
    private boolean isDefault;
    // Constructors, getters, and setters

	public User_Payment_Method() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User_Payment_Method(int id, User user, Payment_Type paymentType, String cardNumber, String cardHolderName,
			Date expiryDate, String provider, boolean isDefault) {
		super();
		this.id = id;
		this.user = user;
		this.paymentType = paymentType;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.provider = provider;
		this.isDefault = isDefault;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Payment_Type getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Payment_Type paymentType) {
		this.paymentType = paymentType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

    
}
