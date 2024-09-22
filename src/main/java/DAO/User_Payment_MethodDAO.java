package DAO;

import entity.User_Payment_Method;
import entity.User;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class User_Payment_MethodDAO {

    public User_Payment_Method findById(int id) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            return em.find(User_Payment_Method.class, id);
        } finally {
            em.close();
        }
    }

    public List<User_Payment_Method> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT upm FROM User_Payment_Method upm";
            TypedQuery<User_Payment_Method> query = em.createQuery(jpql, User_Payment_Method.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public User_Payment_Method create(User_Payment_Method upm) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(upm);
            trans.commit();
            return upm;
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public User_Payment_Method update(User_Payment_Method upm) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(upm);
            trans.commit();
            return upm;
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User_Payment_Method upm = em.find(User_Payment_Method.class, id);
            if (upm != null) {
                em.remove(upm);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public List<User_Payment_Method> findByUser(User user) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT upm FROM User_Payment_Method upm WHERE upm.user = :user";
            TypedQuery<User_Payment_Method> query = em.createQuery(jpql, User_Payment_Method.class);
            query.setParameter("user", user);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public User_Payment_Method findDefaultForUser(User user) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT upm FROM User_Payment_Method upm WHERE upm.user = :user AND upm.isDefault = true";
            TypedQuery<User_Payment_Method> query = em.createQuery(jpql, User_Payment_Method.class);
            query.setParameter("user", user);
            List<User_Payment_Method> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public void setDefaultPaymentMethod(int id, User user) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            // First, set all payment methods for this user to non-default
            em.createQuery("UPDATE User_Payment_Method upm SET upm.isDefault = false WHERE upm.user = :user")
                .setParameter("user", user)
                .executeUpdate();
            
            // Then, set the specified payment method as default
            User_Payment_Method upm = em.find(User_Payment_Method.class, id);
            if (upm != null && upm.getUser().equals(user)) {
                upm.setDefault(true);
                em.merge(upm);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}