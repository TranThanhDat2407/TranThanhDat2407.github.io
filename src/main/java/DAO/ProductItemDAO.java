package DAO;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import entity.Gallery;
import entity.ProductItem;
import utils.JpaUtils;

public class ProductItemDAO extends AbstractDAO<ProductItem> {

	@Override
	public List<ProductItem> findAll(Class<ProductItem> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(clazz);
	}

	@Override
	public ProductItem create(ProductItem entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public ProductItem update(ProductItem entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public ProductItem delete(ProductItem entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}

    public List<ProductItem> findAllWithGalleries(int page, int pageSize, String sortField, String sortDirection, String keyword, Integer categoryId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            StringBuilder hql = new StringBuilder("SELECT DISTINCT pi FROM ProductItem pi JOIN FETCH pi.product p LEFT JOIN FETCH p.galleries JOIN p.category c");
            
            // Xây dựng điều kiện WHERE
            StringBuilder whereClause = new StringBuilder();
            if (keyword != null && !keyword.trim().isEmpty()) {
                whereClause.append("(LOWER(p.name) LIKE LOWER(:keyword) OR LOWER(p.description) LIKE LOWER(:keyword))");
            }
            if (categoryId != null) {
                if (whereClause.length() > 0) whereClause.append(" AND ");
                whereClause.append("c.id = :categoryId");
            }
            if (whereClause.length() > 0) {
                hql.append(" WHERE ").append(whereClause);
            }
            
            // Thêm sắp xếp
            if (sortField != null && !sortField.isEmpty()) {
                hql.append(" ORDER BY ");
                switch (sortField) {
                    case "price":
                        hql.append("pi.price");
                        break;
                    case "name":
                        hql.append("p.name");
                        break;
                    default:
                        hql.append("pi.id"); // Mặc định sắp xếp theo ID của ProductItem
                        break;
                }
                hql.append(sortDirection != null && sortDirection.equalsIgnoreCase("DESC") ? " DESC" : " ASC");
            }
            
            TypedQuery<ProductItem> query = em.createQuery(hql.toString(), ProductItem.class);
            
            // Đặt tham số
            if (keyword != null && !keyword.trim().isEmpty()) {
                query.setParameter("keyword", "%" + keyword.trim() + "%");
            }
            if (categoryId != null) {
                query.setParameter("categoryId", categoryId);
            }
            
            // Áp dụng phân trang
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public long countProductItems(String keyword, Integer categoryId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            StringBuilder jpql = new StringBuilder("SELECT COUNT(DISTINCT pi) FROM ProductItem pi JOIN pi.product p JOIN p.category c");
            StringBuilder whereClause = new StringBuilder();
            if (keyword != null && !keyword.trim().isEmpty()) {
                whereClause.append("(LOWER(p.name) LIKE LOWER(:keyword) OR LOWER(p.description) LIKE LOWER(:keyword))");
            }
            if (categoryId != null) {
                if (whereClause.length() > 0) whereClause.append(" AND ");
                whereClause.append("c.id = :categoryId");
            }
            if (whereClause.length() > 0) {
                jpql.append(" WHERE ").append(whereClause);
            }
            
            TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
            if (keyword != null && !keyword.trim().isEmpty()) {
                query.setParameter("keyword", "%" + keyword.trim() + "%");
            }
            if (categoryId != null) {
                query.setParameter("categoryId", categoryId);
            }
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Map<String, Object> getProductDetails(int productId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT NEW map(p.id as productId, p.name as productName, pi.id as productItemId, pi.price as price, pi.qtyInStock as qtyInStock, c.name as categoryName, p.description as description) " +
                    "FROM Product p " +
                    "INNER JOIN ProductItem pi ON p.id = pi.product.id " +
                    "INNER JOIN Category c ON p.category.id = c.id " +
                    "WHERE p.id = :productId";

            TypedQuery<Map> query = em.createQuery(jpql, Map.class);
            query.setParameter("productId", productId);

            List<Map> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    public ProductItem findByID(int id) {
	    EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();
	    tran.begin();

	    try {
	        String jpql = "SELECT pi FROM ProductItem pi WHERE pi.id = :id";
	        TypedQuery<ProductItem> query = em.createQuery(jpql, ProductItem.class);
	        query.setParameter("id", id);
	        ProductItem pr = query.getSingleResult();
	        tran.commit();
	        return pr;
	    } catch (NoResultException e) {
	        tran.rollback();
	        return null;
	    } finally {
	        em.close();
	    }
	}
	
	public List<ProductItem> findAll() {
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	        String jpql = "SELECT pi FROM ProductItem pi";
	        TypedQuery<ProductItem> query = em.createQuery(jpql, ProductItem.class);
	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e; 
	    } finally {
	        em.close(); 
	    }
	}
	
	public void updateOrInsertProductItem(int productId, int qtyInStock, float price, float originalPrice) {
	    EntityManager em = JpaUtils.getEntityManager();
	    
	    try {
	        em.getTransaction().begin();
	        
	        // Gọi stored procedure
	        StoredProcedureQuery query = em.createStoredProcedureQuery("sp_UpdateOrInsertProductItem");
	        
	        // Đặt các tham số
	        query.registerStoredProcedureParameter("product_id", Integer.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("qty_in_stock", Integer.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("price", Float.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("original_price", Float.class, ParameterMode.IN);
	        
	        query.setParameter("product_id", productId);
	        query.setParameter("qty_in_stock", qtyInStock);
	        query.setParameter("price", price);
	        query.setParameter("original_price", originalPrice);
	        
	        // Thực thi stored procedure
	        query.execute();
	        
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        throw new RuntimeException("Lỗi khi thực thi stored procedure: " + e.getMessage(), e);
	    } finally {
	        em.close();
	    }
	}
	
	public void deleteProductItem(int id) {
	    EntityManager em = JpaUtils.getEntityManager();
	    
	    try {
	        em.getTransaction().begin();
	        
	        // Gọi stored procedure
	        StoredProcedureQuery query = em.createStoredProcedureQuery("sp_DeleteProductItem");
	        
	        // Đăng ký tham số
	        query.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
	        
	        // Đặt giá trị cho tham số
	        query.setParameter("id", id);
	        
	        // Thực thi stored procedure
query.execute();
	        
	        em.getTransaction().commit();
	        System.out.println("ProductItem và các dữ liệu liên quan đã được xóa thành công.");
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        throw new RuntimeException("Lỗi khi xóa ProductItem: " + e.getMessage(), e);
	    } finally {
	        em.close();
	    }
	}
}
