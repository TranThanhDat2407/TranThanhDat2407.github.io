package entity;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class UserAddressId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public UserAddressId() {}

    public UserAddressId(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddressId that = (UserAddressId) o;
        return user.equals(that.user) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(user, address);
    }

	@Override
	public String toString() {
		return user.getId() + " " +address.getId();
	}
    
    
}