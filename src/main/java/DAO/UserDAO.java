package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.User;
import utils.JpaUtils;

public class UserDAO extends AbstractDAO<User>{

	
	public List<User> findAll(){
		
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		String sql = "SELECT u from User u";
		TypedQuery<User> query = em.createQuery(sql,User.class);
		
		return query.getResultList();
		
	}

	 public User findByID(int id) {
		    EntityManager em = JpaUtils.getEntityManager();
		    EntityTransaction tran = em.getTransaction();
		    tran.begin();

		    try {
		        String jpql = "SELECT u FROM User u WHERE u.id = :id";
		        TypedQuery<User> query = em.createQuery(jpql, User.class);
		        query.setParameter("id", id);
		        User user = query.getSingleResult();
		        tran.commit();
		        return user;
		    } catch (NoResultException e) {
		        tran.rollback();
		        return null;
		    } finally {
		        em.close();
		    }
		}
	 
	 public User findByEmail(String email) {
		    EntityManager em = JpaUtils.getEntityManager();
		    EntityTransaction tran = em.getTransaction();
		    tran.begin();

		    try {
		        String jpql = "SELECT u FROM User u WHERE u.email = :email";
		        TypedQuery<User> query = em.createQuery(jpql, User.class);
		        query.setParameter("email", email);
		        User user = query.getSingleResult();
		        tran.commit();
		        return user;
		    } catch (NoResultException e) {
		        tran.rollback();
		        return null;
		    } finally {
		        em.close();
		    }
		}
		
	@Override
	public User create(User entity) {
		return super.create(entity);
	}

	public User update(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			em.merge(user);
			tran.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}
		return user;
	}
	 
	
	public void delete(int id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			User user = em.find(User.class, id);
			if(user != null) {
				em.remove(user);
			}else {
				throw new Exception("User Không Tồn Tại");
			}
			tran.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}	
	}
	
	public List<User> findByKeyWord(String keyword){
		
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		 String jpql = "SELECT o FROM User o WHERE o.name LIKE :keyword";
	     TypedQuery<User> query = em.createQuery(jpql, User.class);
	     query.setParameter("keyword", "%" + keyword + "%");
		
		return query.getResultList();
		
	}
	
	
	 
}
