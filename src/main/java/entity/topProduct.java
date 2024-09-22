package entity;

public class topProduct {

	 private int id;
	 private String name;
	 private String thumbnail;
	 private Double price;
	 private Integer totalSoldQuantity;
	 private Double totalRevenue;
	 
	public topProduct() {
		super();
	}

	public topProduct(int id, String name, String thumbnail,Double price, Integer totalSoldQuantity, Double totalRevenue) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.price = price;
		this.totalSoldQuantity = totalSoldQuantity;
		this.totalRevenue = totalRevenue;
	}

	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getTotalSoldQuantity() {
		return totalSoldQuantity;
	}

	public void setTotalSoldQuantity(Integer totalSoldQuantity) {
		this.totalSoldQuantity = totalSoldQuantity;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	 
	
	 
}
