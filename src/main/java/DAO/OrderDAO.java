package DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import entity.Order;
import entity.User;
import utils.JpaUtils;

public class OrderDAO extends AbstractDAO<Order>{
	
    public Order findById(Integer id) {
        return super.findById(Order.class, id);
    }

    
    public List<Order> findAll() {
        return super.findAll(Order.class);
    }

    
    public List<Order> findAllPage(int pageNumber, int pageSize) {
        return super.findAllPage(Order.class, pageNumber, pageSize);
    }

    
    public List<Order> findByUser(Integer userId) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            String jpql = "SELECT o FROM Order o WHERE o.user.id = :userId";
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("userId", userId);
            List<Order> orders = query.getResultList();
            System.out.println(orders);
            tran.commit();
            return orders;
        } catch (NoResultException e) {
            tran.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    
    public List<Order> findByUserPaymentMethod(Integer userPaymentMethodId) {
        String jpql = "SELECT o FROM Order o WHERE o.userPaymentMethod.id = ?0";
        return super.findMany(Order.class, jpql, userPaymentMethodId);
    }

    
    public List<Order> findByAddress(Integer addressId) {
        String jpql = "SELECT o FROM Order o WHERE o.address.id = ?0";
        return super.findMany(Order.class, jpql, addressId);
    }

    
    public List<Order> findByOrderStatus(boolean orderStatus) {
        String jpql = "SELECT o FROM Order o WHERE o.orderStatus = ?0";
        return super.findMany(Order.class, jpql, orderStatus);
    }

    
    public List<Order> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT o FROM Order o WHERE o.createAt BETWEEN ?0 AND ?1";
        return super.findMany(Order.class, jpql, startDate, endDate);
    }

    
    public Order create(Order order) {
        return super.create(order);
    }

    
    public Order update(Order order) {
        return super.update(order);
    }

    
    public Order delete(Order order) {
        return super.delete(order);
    }
    
    public Order createOrder(int userId, int userPaymentMethodId, int addressId) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Order createdOrder = null;

        try {
            transaction.begin();

            // Call the stored procedure
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_CreateOrder")
                .registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("user_payment_method_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("address_id", Integer.class, ParameterMode.IN);

            query.setParameter("user_id", userId)
                 .setParameter("user_payment_method_id", userPaymentMethodId)
                 .setParameter("address_id", addressId);

            query.execute();

            // After the stored procedure execution, fetch the newly created order
            String jpql = "SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.id DESC";
            TypedQuery<Order> orderQuery = em.createQuery(jpql, Order.class);
            orderQuery.setParameter("userId", userId);
            orderQuery.setMaxResults(1);
            
            List<Order> resultList = orderQuery.getResultList();
            if (!resultList.isEmpty()) {
                createdOrder = resultList.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error creating order: " + e.getMessage());
        } finally {
            em.close();
        }

        return createdOrder;
    }
}
