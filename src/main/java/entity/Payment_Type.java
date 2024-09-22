package entity;

import javax.persistence.*;

@Entity
@Table(name = "Payment_Type")
public class Payment_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    // Constructors, getters, and setters

	public Payment_Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment_Type(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    
}
