package DAO;

import entity.Payment_Type;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class Payment_TypeDAO {

    public void insert(Payment_Type paymentType) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(paymentType);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void update(Payment_Type paymentType) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(paymentType);
            trans.commit();
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
            Payment_Type paymentType = em.find(Payment_Type.class, id);
            if (paymentType != null) {
                em.remove(paymentType);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public Payment_Type findById(int id) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            return em.find(Payment_Type.class, id);
        } finally {
            em.close();
        }
    }

    public List<Payment_Type> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT pt FROM Payment_Type pt";
            TypedQuery<Payment_Type> query = em.createQuery(jpql, Payment_Type.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Payment_Type> findByName(String name) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT pt FROM Payment_Type pt WHERE pt.name LIKE :name";
            TypedQuery<Payment_Type> query = em.createQuery(jpql, Payment_Type.class);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}