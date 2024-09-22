package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Category;
import entity.Gallery;
import entity.Product;
import utils.JpaUtils;

public class GalleryDAO extends AbstractDAO<Gallery>{
	
	public List<Gallery> findAll() {
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	        String jpql = "SELECT g FROM Gallery g";
	        TypedQuery<Gallery> query = em.createQuery(jpql, Gallery.class);
	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e; 
	    } finally {
	        em.close(); 
	    }
	}


	 public Gallery findByID(int id) {
		    EntityManager em = JpaUtils.getEntityManager();
		    EntityTransaction tran = em.getTransaction();
		    tran.begin();

		    try {
		        String jpql = "SELECT g FROM Gallery g WHERE g.id = :id";
		        TypedQuery<Gallery> query = em.createQuery(jpql, Gallery.class);
		        query.setParameter("id", id);
		        Gallery pr = query.getSingleResult();
		        tran.commit();
		        return pr;
		    } catch (NoResultException e) {
		        tran.rollback();
		        return null;
		    } finally {
		        em.close();
		    }
		}
	 
	 public Gallery galleryCreate(Gallery gallery) {
		    EntityManager em = JpaUtils.getEntityManager();
		    EntityTransaction tran = em.getTransaction();

		    try {
		        tran.begin();

		        if (gallery.getProduct() != null) {
		            Product pr = em.find(Product.class, gallery.getProduct().getId());
		            if (pr != null) {
		            	gallery.setProduct(pr);
		            } else {
		                throw new IllegalArgumentException("Category not found");
		            }
		        }

		        em.persist(gallery);
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

		    return gallery;
		}

		
	

	public Gallery gallerytUpdate(Gallery gl) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			em.merge(gl);
			tran.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}
		return gl;
	}
	 
	
	public void delete(int id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			Gallery pr = em.find(Gallery.class, id);
			if(pr != null) {
				em.remove(pr);
			}else {
				throw new Exception("Sản phẩm không tồn tại");
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
	
	public List<Gallery> findByKeyWord(int keyword){
		
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		 String jpql = "SELECT g FROM Gallery g WHERE g.id LIKE :keyword";
	     TypedQuery<Gallery> query = em.createQuery(jpql, Gallery.class);
	     query.setParameter("keyword", "%" + keyword + "%");
		
		return query.getResultList();
		
	}
	
	
	
	List<String> findByProductId(int product_id)
	{
		String sql = "SELECT thumbnail FROM Gallery\r\n"
					+ "WHERE product_id = :product_id;";
		Query query = em.createNativeQuery(sql);
		query.setParameter("product_id", product_id);
		return query.getResultList();
	}
	/*
	 * public static void main(String[] args) { GalleryDAO galleryDAO = new
	 * GalleryDAO(); List<Gallery> list = galleryDAO.findAll(); for (Gallery gallery
	 * : list) { System.out.println(gallery); } }
	 */
}
