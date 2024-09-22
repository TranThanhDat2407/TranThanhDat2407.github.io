package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Category;
import entity.Product;
import utils.JpaUtils;

public class ProductDAO extends AbstractDAO<Product>{

	@Override
	public Product findById(Class<Product> clazz, Integer id) {
		// TODO Auto-generated method stub
		return super.findById(clazz, id);
	}

	@Override
	public List<Product> findAll(Class<Product> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(clazz);
	}

	@Override
	public Product create(Product entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public Product update(Product entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Product delete(Product entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	
	public List<String> findThumbnailsByProductId(Integer productId) {
		EntityManager em = JpaUtils.getEntityManager();
        String jpql = "SELECT g.thumbnail FROM Gallery g WHERE g.product.id = :productId";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setParameter("productId", productId);
        return query.getResultList();
    }
	
	public int getProductItemId(int product_id) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT pi.id FROM ProductItem pi JOIN pi.product p WHERE p.id = :product_id";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            query.setParameter("product_id", product_id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return -1; // hoặc xử lý theo cách khác nếu không tìm thấy kết quả
        } finally {
            em.close();
        }
    }
	
	public List<Product> findAll() {
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	        String jpql = "SELECT p FROM Product p";
	        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e; 
	    } finally {
	        em.close(); 
	    }
	}


	 public Product findByID(int id) {
		    EntityManager em = JpaUtils.getEntityManager();
		    EntityTransaction tran = em.getTransaction();
		    tran.begin();

		    try {
		        String jpql = "SELECT p FROM Product p WHERE p.id = :id";
		        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		        query.setParameter("id", id);
		        Product pr = query.getSingleResult();
		        tran.commit();
		        return pr;
		    } catch (NoResultException e) {
		        tran.rollback();
		        return null;
		    } finally {
		        em.close();
		    }
		}
	 
	 public Product productCreate(Product product) {
		    EntityManager em = JpaUtils.getEntityManager();
		    EntityTransaction tran = em.getTransaction();

		    try {
		        tran.begin();

		        if (product.getCategory() != null) {
		            Category category = em.find(Category.class, product.getCategory().getId());
		            if (category != null) {
		                product.setCategory(category);
		            } else {
		                throw new IllegalArgumentException("Category not found");
		            }
		        }

		        em.persist(product);
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

		    return product;
		}

		
	

	public Product productUpdate(Product pr) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			em.merge(pr);
			tran.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}
		return pr;
	}
	 
	
	public void delete(int id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			Product pr = em.find(Product.class, id);
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
	
	public List<Product> findByKeyWord(String keyword){
		
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		 String jpql = "SELECT p FROM Product p WHERE p.name LIKE :keyword";
	     TypedQuery<Product> query = em.createQuery(jpql, Product.class);
	     query.setParameter("keyword", "%" + keyword + "%");
		
		return query.getResultList();
		
	}
}
