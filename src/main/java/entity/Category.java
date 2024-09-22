package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
    // Constructors, getters, and setters

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int id, Category parentCategory, String name, List<Category> subCategories,
			List<Product> products) {
		super();
		this.id = id;
		this.parentCategory = parentCategory;
		this.name = name;
		this.subCategories = subCategories;
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Category> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	 public String getParentCategoryID() {
	        return (parentCategory != null) ? String.valueOf(parentCategory.getId()): " ";
	    }
    
    
}
