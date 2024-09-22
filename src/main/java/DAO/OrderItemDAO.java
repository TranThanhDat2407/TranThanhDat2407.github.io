package DAO;

import entity.OrderItem;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemDAO {

    // Create
    public OrderItem create(OrderItem orderItem) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(orderItem);
            transaction.commit();
            return orderItem;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    // Read by ID
    public OrderItem findById(Integer id) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            return em.find(OrderItem.class, id);
        } finally {
            em.close();
        }
    }

    // Update
    public OrderItem update(OrderItem orderItem) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            OrderItem updatedOrderItem = em.merge(orderItem);
            transaction.commit();
            return updatedOrderItem;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    // Delete
    public boolean delete(Integer id) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            OrderItem orderItem = em.find(OrderItem.class, id);
            if (orderItem != null) {
                em.remove(orderItem);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    // Find all OrderItems
    public List<OrderItem> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT oi FROM OrderItem oi";
            TypedQuery<OrderItem> query = em.createQuery(jpql, OrderItem.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Find OrderItems by Order ID
    public List<OrderItem> findByOrderId(Integer orderId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId";
            TypedQuery<OrderItem> query = em.createQuery(jpql, OrderItem.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Find OrderItems by ProductItem ID
    public List<OrderItem> findByProductItemId(Integer productItemId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT oi FROM OrderItem oi WHERE oi.productItem.id = :productItemId";
            TypedQuery<OrderItem> query = em.createQuery(jpql, OrderItem.class);
            query.setParameter("productItemId", productItemId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getOrderItemIdByUserAndProduct(int userId, int productId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("GetOrderItemIdByUserAndProduct")
                .registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("product_id", Integer.class, ParameterMode.IN)
                .setParameter("user_id", userId)
                .setParameter("product_id", productId);

            query.execute();

            List<Object> result = query.getResultList();
            if (!result.isEmpty()) {
                return ((Number) result.get(0)).intValue();
            } else {
                return -1; // Or any other value to indicate no result found
            }
        } finally {
            em.close();
        }
    }
    public List<Map<String, Object>> findItemDetailByOrder_ID(int order_id) {
		EntityManager em = JpaUtils.getEntityManager();

		StoredProcedureQuery query = em.createStoredProcedureQuery("GetOrderItemDetails");
		query.registerStoredProcedureParameter("order_id", Integer.class, javax.persistence.ParameterMode.IN);
		query.setParameter("order_id", order_id);

		List<Object[]> results = query.getResultList();

		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Object[] row : results) {
			Map<String, Object> rowMap = new HashMap<>();
			rowMap.put("id", row[0]);// id orderItem
			rowMap.put("order_id", row[1]);
			rowMap.put("product_item_id", row[2]);
			rowMap.put("qty", row[3]);
			rowMap.put("price", row[4]);
			rowMap.put("phone", row[5]);
			rowMap.put("name", row[6]);
			rowMap.put("thumbnail", row[7]);
			rowMap.put("total", row[8]);
			rowMap.put("shipping_address", row[9]);
			rowMap.put("totalAmount", row[10]);
			rowMap.put("payment_type_name", row[12]);
			rowMap.put("nameUser", row[13]);

			rowMap.put("totalAll", row[14]);
			rowMap.put("qtyAll", row[15]);

			resultList.add(rowMap);
		}
		return resultList;

	}

    // Main method for testing
    public static void main(String[] args) {
//        OrderItemDAO orderItemDAO = new OrderItemDAO();
//
//        // Test create
//        OrderItem newOrderItem = new OrderItem();
//        // Set properties for the new order item
//        OrderItem createdOrderItem = orderItemDAO.create(newOrderItem);
//        System.out.println("Created OrderItem with ID: " + createdOrderItem.getId());
//
//        // Test findById
//        OrderItem foundOrderItem = orderItemDAO.findById(createdOrderItem.getId());
//        System.out.println("Found OrderItem: " + foundOrderItem.getId());
//
//        // Test update
//        foundOrderItem.setQty(5);
//        OrderItem updatedOrderItem = orderItemDAO.update(foundOrderItem);
//        System.out.println("Updated OrderItem quantity: " + updatedOrderItem.getQty());
//
//        // Test findAll
//        List<OrderItem> allOrderItems = orderItemDAO.findAll();
//        System.out.println("Total OrderItems: " + allOrderItems.size());
//
//        // Test findByOrderId
//        List<OrderItem> orderItemsByOrder = orderItemDAO.findByOrderId(1); // Replace with an actual order ID
//        System.out.println("OrderItems for Order 1: " + orderItemsByOrder.size());
//
//        // Test findByProductItemId
//        List<OrderItem> orderItemsByProductItem = orderItemDAO.findByProductItemId(1); // Replace with an actual product item ID
//        System.out.println("OrderItems for ProductItem 1: " + orderItemsByProductItem.size());
//
//        // Test delete
//        boolean deleted = orderItemDAO.delete(createdOrderItem.getId());
//        System.out.println("OrderItem deleted: " + deleted);
    	OrderItemDAO orderItemDAO = new OrderItemDAO();

        // ... (previous test cases remain the same)

        // Test getOrderItemIdByUserAndProduct
        int userId = 11; // Replace with an actual user ID
        int productId = 2; // Replace with an actual product ID
        int orderItemId = orderItemDAO.getOrderItemIdByUserAndProduct(userId, productId);
        if (orderItemId != -1) {
            System.out.println("OrderItem ID for User " + userId + " and Product " + productId + ": " + orderItemId);
        } else {
            System.out.println("No OrderItem found for User " + userId + " and Product " + productId);
        }
    	}
}