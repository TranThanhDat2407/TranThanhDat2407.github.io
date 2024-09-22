package entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "WishList")
public class WishList {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_item_id")
	private ProductItem productItem;

	
	
	public WishList(int id, User user, ProductItem productItem) {
		super();
		this.id = id;
		this.user = user;
		this.productItem = productItem;
	}
	public WishList() {
		// TODO Auto-generated constructor stub
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

	public ProductItem getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItem productItem) {
		this.productItem = productItem;
	}

	 @Override
	public String toString() {
		  return "WishList [id=" + id + ", user=" + user + ", productItem=" + productItem + "]";
	}

}
