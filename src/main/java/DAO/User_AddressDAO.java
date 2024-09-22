package DAO;

import java.util.List;

import javax.persistence.*;

import entity.Address;
import entity.User;
import entity.UserAddressId;
import entity.User_Address;
import utils.JpaUtils;

public class User_AddressDAO extends AbstractDAO<User_Address>{
	
	public void createAddressClient(int userId, String city, String ward, String street) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
	    try {
	        em.getTransaction().begin();

	        StoredProcedureQuery query = em.createStoredProcedureQuery("sp_CreateAddressClient")
	            .registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("city", String.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("ward", String.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("street", String.class, ParameterMode.IN);

	        query.setParameter("user_id", userId);
	        query.setParameter("city", city);
	        query.setParameter("ward", ward);
	        query.setParameter("street", street);

	        query.execute();

	        em.getTransaction().commit();
	        System.out.println("Create address success");
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        System.out.println("Cannot create address");
	        throw new RuntimeException("Error creating address", e);
	    }
	}
	
	public void createAddressAdmin(int userId, String city, String ward, String street,boolean is_Default) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
	    try {
	        em.getTransaction().begin();

	        StoredProcedureQuery query = em.createStoredProcedureQuery("sp_CreateAddressAdmin")
	            .registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("city", String.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("ward", String.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("street", String.class, ParameterMode.IN)
	            .registerStoredProcedureParameter("is_Default", Boolean.class, ParameterMode.IN);
	        query.setParameter("user_id", userId);
	        query.setParameter("city", city);
	        query.setParameter("ward", ward);
	        query.setParameter("street", street);
	        query.setParameter("is_Default", is_Default);
	        query.execute();

	        em.getTransaction().commit();
	        System.out.println("Create address success");
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        System.out.println("Cannot create address");
	        throw new RuntimeException("Error creating address", e);
	    }
	}
	
	public List<Object[]> selectAddressByUserId(int userId) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
	    String sql = "SELECT a.id, a.city, a.ward, a.street, ua.is_Default " +
	                 "FROM User_Address ua " +
	                 "JOIN Address a ON ua.address_id = a.id " +
	                 "WHERE ua.user_id = :userId";
	    
	    Query query = em.createNativeQuery(sql);
	    query.setParameter("userId", userId);
	    
	    return query.getResultList();
	}
	
	public List<Object[]> selectAddressByUserIdAndDefault(int userId) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
	    String sql = "SELECT a.id, a.city, a.ward, a.street, ua.is_Default " +
	                 "FROM User_Address ua " +
	                 "JOIN Address a ON ua.address_id = a.id " +
	                 "WHERE ua.user_id = :userId AND ua.is_Default = 0;";
	    
	    Query query = em.createNativeQuery(sql);
	    query.setParameter("userId", userId);
	    
	    return query.getResultList();
	}
	
	public List<Object[]> selectAddressByUserIdAndNotDefault(int userId) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
	    String sql = "SELECT a.id, a.city, a.ward, a.street, ua.is_Default " +
	                 "FROM User_Address ua " +
	                 "JOIN Address a ON ua.address_id = a.id " +
	                 "WHERE ua.user_id = :userId AND ua.is_Default = 1;";
	    
	    Query query = em.createNativeQuery(sql);
	    query.setParameter("userId", userId);
	    
	    return query.getResultList();
	}
    
    public void updateUserAddressAdmin(int userId, int addressId, String city, String ward, String street, boolean isDefault) {
    	EntityManager em = JpaUtils.getEntityManager();
    	EntityTransaction tran = em.getTransaction();
    	
        try {
            em.getTransaction().begin();
            
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_UpdateAddressAdmin")
                .registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("address_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("city", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ward", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("street", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_Default", Boolean.class, ParameterMode.IN)
                .setParameter("user_id", userId)
                .setParameter("address_id", addressId)
                .setParameter("city", city)
                .setParameter("ward", ward)
                .setParameter("street", street)
                .setParameter("is_Default", isDefault);
            
            query.execute();
            
            em.getTransaction().commit();
            System.out.println("User address updated successfully");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error updating user address: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public void updateUserAddressClient( int addressId, String city, String ward, String street) {
    	EntityManager em = JpaUtils.getEntityManager();
    	EntityTransaction tran = em.getTransaction();
    	
        try {
            em.getTransaction().begin();
            
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_UpdateAddressClient")
                .registerStoredProcedureParameter("address_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("city", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ward", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("street", String.class, ParameterMode.IN)
                .setParameter("address_id", addressId)
                .setParameter("city", city)
                .setParameter("ward", ward)
                .setParameter("street", street);
            query.execute();
            
            em.getTransaction().commit();
            System.out.println("User address updated successfully");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error updating user address: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public void deleteUserAddress(int userId, int addressId) {
    	EntityManager em = JpaUtils.getEntityManager();
    	EntityTransaction tran = em.getTransaction();
    	
        try {
            em.getTransaction().begin();
            
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_DeleteUserAddress")
                .registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("address_id", Integer.class, ParameterMode.IN)
                .setParameter("user_id", userId)
                .setParameter("address_id", addressId);
            
            query.execute();
            
            em.getTransaction().commit();
            System.out.println("User address deleted successfully");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error deleting user address: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public Address findByID(int id) {
	    EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();
	    tran.begin();

	    try {
	        String jpql = "SELECT u FROM Address u WHERE u.id = :id";
	        TypedQuery<Address> query = em.createQuery(jpql, Address.class);
	        query.setParameter("id", id);
	        Address Address = query.getSingleResult();
	        tran.commit();
	        return Address;
	    } catch (NoResultException e) {
	        tran.rollback();
	        return null;
	    } finally {
	        em.close();
	    }
	}
    
    public void setDefaultAddress(int userID, int addressID) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
            em.getTransaction().begin();
            
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_SetDefaultUserAddress")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, userID)
                .setParameter(2, addressID);

            query.execute();
            
            em.getTransaction().commit();
            System.out.println("User address updated successfully");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error updating user address: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    
    public static List<User_Address> findAllUserAddresses() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            TypedQuery<User_Address> query = em.createQuery("SELECT ua FROM User_Address ua", User_Address.class);
            List<User_Address> userAddresses = query.getResultList();
            return userAddresses;
        } finally {
            em.close();
        }
    }
    
    public User_Address findByUserAddressId(UserAddressId id) {
    	EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        String hql = "SELECT ua FROM User_Address ua WHERE ua.id = :id";
        Query query = em.createQuery(hql, User_Address.class);
        query.setParameter("id", id);
        return (User_Address) query.getSingleResult();
    }
    
    public List<User_Address> findByUserName(String UserName) {
    	EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            TypedQuery<User_Address> query = em.createQuery("SELECT ua FROM User_Address ua JOIN User u ON u.id = ua.user.id WHERE u.name like :UserName", User_Address.class);
            query.setParameter("UserName","%" + UserName + "%" );
            List<User_Address> userAddresses = query.getResultList();
            return userAddresses;
        } finally {
            em.close();
        }
    }
    
}
