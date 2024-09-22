package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.hibernate.procedure.ProcedureCall;

import entity.topProduct;
import utils.JpaUtils;

public class StaticDAO {
	
	public List<topProduct> getTopSellProduct() {
	    EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();

	    try {
	        tran.begin();

	        StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetBestSellProducts");
	        List<Object[]> results = query.getResultList();

	        List<topProduct> bestSellProducts = results.stream()
	            .map(result -> {
	            	topProduct proc = new topProduct();
	            	proc.setId((int) result[0]);
	            	proc.setName((String) result[2]);
	            	proc.setThumbnail((String) result[1]);
	            	proc.setPrice((double) result[3]);
	            	proc.setTotalSoldQuantity((int) result[4]);
	            	proc.setTotalRevenue((double) result[5]);
	                return proc;
	            })
	            .collect(Collectors.toList());

	        tran.commit();
	        return bestSellProducts;
	    } catch (Exception e) {
	        tran.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}
	
	public int getOrdersCountByMonth() {
		EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetOrdersCountByMonth");
            List<Integer> results = query.getResultList();
            if (!results.isEmpty()) {
            	Integer userCount = (Integer) results.get(0);
                return userCount.intValue();
            }else {
            	return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error executing sp_GetOrdersCountByMonth", e);
        }
    }
	
	public int getUsersCountByMonth() {
		EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetUsersCountByMonth");
            List<Integer> results = query.getResultList();
            if (!results.isEmpty()) {
                Integer userCount = (Integer) results.get(0);
                return userCount.intValue();
            }else {
            	return 0;
            }
         
        } catch (Exception e) {
            throw new RuntimeException("Error executing sp_GetOrdersCountByMonth", e);
        }
    }
	
	public double getRevenueCountByMonth() {
		EntityManager em = JpaUtils.getEntityManager();
	    EntityTransaction tran = em.getTransaction();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetRevenueByMonth");
            double revenue = (double) query.getSingleResult();
            return revenue;
        } catch (Exception e) {
            throw new RuntimeException("Error executing sp_GetOrdersCountByMonth", e);
        }
    }
	
	public List<Object[]> getRevenue(int year, int month) {
		EntityManager em = JpaUtils.getEntityManager();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetRevenue");

			query.registerStoredProcedureParameter("Year", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("Month", Integer.class, ParameterMode.IN);
			query.setParameter("Year", year);
			query.setParameter("Month", month);

			List<Object[]> results = query.getResultList();

			return results;
		} finally {
			em.close();
		}
	}

	public List<Object[]> sp_GetOrdersCount(int year, int month) {

		EntityManager em = JpaUtils.getEntityManager();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetOrdersCount");

			query.registerStoredProcedureParameter("year", Integer.class, javax.persistence.ParameterMode.IN);
			query.registerStoredProcedureParameter("month", Integer.class, javax.persistence.ParameterMode.IN);

			query.setParameter("year", year);
			query.setParameter("month", month);

			List<Object[]> listResults = query.getResultList();

			return listResults;
		} finally {
			em.close();
		}

	}

	public List<Object[]> getUsersCount(int year, int month) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetUsersCount");
			query.registerStoredProcedureParameter("year", Integer.class, javax.persistence.ParameterMode.IN);
			query.registerStoredProcedureParameter("month", Integer.class, javax.persistence.ParameterMode.IN);

			query.setParameter("year", year);
			query.setParameter("month", month);
			List<Object[]> listResults = query.getResultList();

			return listResults;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error getUsersCount STATICDAO");
		}
		return null;
	}

	public List<topProduct> getBestSellProducts() {
		EntityManager em = JpaUtils.getEntityManager();
		List<topProduct> listTopProducts = new ArrayList<>();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_GetBestSellProducts");

			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				topProduct t = new topProduct();
				t.setId((Integer) o[0]);
				t.setThumbnail((String) o[1]);
				t.setName((String) o[2]);
				t.setPrice((Double) o[3]);
				t.setTotalSoldQuantity((Integer) o[4]);
				t.setTotalRevenue((Double) o[5]);

				listTopProducts.add(t);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error staticDAO getBestSellProductsByTime");

		}

		return listTopProducts;
	}



	
}
