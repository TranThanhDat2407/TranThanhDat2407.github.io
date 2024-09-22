package DAO;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import entity.Category;
import entity.User;
import utils.JpaUtils;

public class CategoryDAO extends AbstractDAO<Category> {

	public static List<Category> findAll() {

		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();

		String sql = "SELECT c from Category c";
		TypedQuery<Category> query = em.createQuery(sql, Category.class);

		return query.getResultList();

	}

	public Category findByID(int id) {
	    EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();
	    tran.begin();

	    try {
	        String jpql = "SELECT c FROM Category c WHERE c.id = :id";
	        TypedQuery<Category> query = em.createQuery(jpql, Category.class);
	        query.setParameter("id", id);
	        Category category = query.getSingleResult();
	        tran.commit();
	        return category;
	    } catch (NoResultException e) {
	        tran.rollback();
	        return null; 
	    } finally {
	        em.close();
	    }
	}


	public Category create(Category category) {
	    EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();

	    try {
	        tran.begin();

	        // Kiểm tra và xử lý thuộc tính parentCategory
	        if (category.getParentCategory() != null) {
	            Category parentCategory = em.find(Category.class, category.getParentCategory().getId());
	            if (parentCategory != null) {
	                category.setParentCategory(parentCategory);
	            } else {
	                throw new IllegalArgumentException("Parent category not found");
	            }
	        }

	        em.persist(category);
	        tran.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (tran.isActive()) {
	            tran.rollback();
	        }
	        throw e;
	    } finally {
	        em.close();
	    }
	    return category;
	}

	


	public  Category update(Category category) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();

		try {
			tran.begin();
			em.merge(category);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
		return category;
	}

	public void delete(int id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();

		try {
			tran.begin();
			Category category = em.find(Category.class, id);
			if (category != null) {
				em.remove(category);
			} else {
				throw new Exception("Dah mục không tồn tại");
			}
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public List<Category> findByKeyWord(String keyword) {

		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();

		String jpql = "SELECT c FROM Category c WHERE c.name LIKE :keyword";
		TypedQuery<Category> query = em.createQuery(jpql, Category.class);
		query.setParameter("keyword", "%" + keyword + "%");

		return query.getResultList();

	}

	public List<Category> listParent() {
		String jpql = "SELECT c FROM Category c WHERE c.parentCategory IS NULL";
		TypedQuery<Category> query = em.createQuery(jpql, Category.class);
		return query.getResultList();
	}

	public List<Category> listChild(int parentId) {
		String jpql = "SELECT c FROM Category c WHERE c.parentCategory.id = :parentId";
		TypedQuery<Category> query = em.createQuery(jpql, Category.class);
		query.setParameter("parentId", parentId);
		return query.getResultList();
	}

	// Phương thức bổ sung để lấy toàn bộ cây danh mục
	public List<Category> getAllCategories() {
		String jpql = "SELECT c FROM Category c LEFT JOIN FETCH c.subCategories ORDER BY c.parentCategory.id NULLS FIRST, c.id";
		TypedQuery<Category> query = em.createQuery(jpql, Category.class);
		return query.getResultList();
	}

	public static void main(String[] args) {
		CategoryDAO dao = new CategoryDAO();
		List<Category> list = dao.listParent();
		for (Category category : list) {
			System.out.println(category.getId() + ", " + category.getName());
			List<Category> list1 = dao.listChild(category.getId());
			for (Category category2 : list1) {
				System.out.println(category2.getId() + ", " + category2.getName());
			}
		}
	}
}