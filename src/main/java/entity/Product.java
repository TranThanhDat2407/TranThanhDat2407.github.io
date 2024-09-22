package entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @OneToMany(mappedBy = "product")
    private List<Gallery> galleries;

    @OneToMany(mappedBy = "product")
    private List<ProductItem> productItems;
    // Constructors, getters, and setters

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, Category category, String name, String description, Date createAt,
			Date updateAt, List<Gallery> galleries, List<ProductItem> productItems) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.galleries = galleries;
		this.productItems = productItems;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date date) {
		this.createAt = date;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date date) {
		this.updateAt = date;
	}
	public List<Gallery> getGalleries() {
		return galleries;
	}
	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}
	public List<ProductItem> getProductItems() {
		return productItems;
	}
	public void setProductItems(List<ProductItem> productItems) {
		this.productItems = productItems;
	}

    
}
