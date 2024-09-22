package DAO;

import entity.Cart;
import entity.User;
import entity.ProductItem;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartDAO {

    public void create(Cart cart) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Cart findById(Integer id) {
        EntityManager em = JpaUtils.getEntityManager();
        Cart cart = em.find(Cart.class, id);
        em.close();
        return cart;
    }

    public List<Cart> findByUser(User user) {
        EntityManager em = JpaUtils.getEntityManager();
        String jpql = "SELECT DISTINCT c FROM Cart c JOIN FETCH c.productItem pi JOIN FETCH pi.product p LEFT JOIN FETCH p.galleries WHERE c.user = :user";
        TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
        query.setParameter("user", user);
        List<Cart> result = query.getResultList();
        em.close();
        return result;
    }

    public void update(Cart cart) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(cart);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, id);
            if (cart != null) {
                em.remove(cart);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Cart findByUserAndProductItem(User user, ProductItem productItem) {
        EntityManager em = JpaUtils.getEntityManager();
        String jpql = "SELECT c FROM Cart c WHERE c.user = :user AND c.productItem = :productItem";
        TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
        query.setParameter("user", user);
        query.setParameter("productItem", productItem);
        List<Cart> result = query.getResultList();
        em.close();
        return result.isEmpty() ? null : result.get(0);
    }

    public void updateQuantity(Integer cartId, Integer newQuantity) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, cartId);
            if (cart != null) {
                cart.setQty(newQuantity);
                em.merge(cart);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public int countCartByUser(int userId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT COUNT(DISTINCT c.productItem.id) FROM Cart c WHERE c.user.id = :userId";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("userId", userId);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
    	UserDAO udao = new UserDAO();
    	User user = udao.findByID(10);
    			CartDAO cartDAO = new CartDAO();
    			Integer itemCount = cartDAO.countCartByUser(10);
    			System.out.println("Number of items in user's cart: " + itemCount);
	}
}