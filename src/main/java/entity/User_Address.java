package entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User_Address")
public class User_Address {
	
    @EmbeddedId
    private UserAddressId id;

    @Column(name = "is_Default")
    private boolean isDefault;

    public User_Address() {}

    public User_Address(User user, Address address, boolean isDefault) {
        this.id = new UserAddressId(user, address);
        this.isDefault = isDefault;
    }

    public UserAddressId getId() {
        return id;
    }

    public void setId(UserAddressId id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        this.id.setUser(user);
    }

    public Address getAddress() {
        return id.getAddress();
    }

    public void setAddress(Address address) {
        this.id.setAddress(address);
    }

	@Override
	public String toString() {
		return "User_Address [id=" + id + ", isDefault=" + isDefault + "]";
	}
    
  
}