package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "[Order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_payment_method_id")
    private User_Payment_Method userPaymentMethod;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "order_status")
    private boolean orderStatus;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    // Constructors, getters, and setters

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, User user, User_Payment_Method userPaymentMethod, String shippingAddress, double totalAmount,
			boolean orderStatus, Date createAt, Date updateAt) {
		super();
		this.id = id;
		this.user = user;
		this.userPaymentMethod = userPaymentMethod;
		this.shippingAddress = shippingAddress;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.createAt = createAt;
		this.updateAt = updateAt;
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
	public User_Payment_Method getUserPaymentMethod() {
		return userPaymentMethod;
	}
	public void setUserPaymentMethod(User_Payment_Method userPaymentMethod) {
		this.userPaymentMethod = userPaymentMethod;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean isOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
    
    
}
