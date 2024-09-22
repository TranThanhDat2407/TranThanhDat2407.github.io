package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entity.ProductItem;
import entity.User;
import entity.WishList;
import utils.JpaUtils;

public class WishListDAO extends AbstractDAO<WishList> {
	@Override
	public WishList create(WishList entity) {
		// TODO Auto-generated method stub
		return super.create(entity);

	}

	public List<WishList> getListWlByID_User(int id_user) {
		List<WishList> list = new ArrayList<>();
		EntityManager em = JpaUtils.getEntityManager();

		try {
			em.getTransaction().begin();
			String jpql = "SELECT w FROM WishList w WHERE w.user.id = :userId";
			TypedQuery<WishList> query = em.createQuery(jpql, WishList.class);
			query.setParameter("userId", id_user);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		return list;
	}

	  public void deleteWishList(int idProductItem, int idUser) throws Exception {
	        EntityManager em = JpaUtils.getEntityManager();
	        EntityTransaction tran = null;
	        try {
	            tran = em.getTransaction();
	            tran.begin();

	            String jpql = "DELETE FROM WishList wl WHERE wl.user.id = :idUser AND wl.productItem.id = :idProductItem";
	            Query query = em.createQuery(jpql);
	            query.setParameter("idUser", idUser);
	            query.setParameter("idProductItem", idProductItem);

	            int result = query.executeUpdate();
	            if (result == 0) {
	                throw new Exception("WishList không tồn tại");
	            }

	            tran.commit();
	        } catch (Exception e) {
	            if (tran != null && tran.isActive()) {
	                tran.rollback();
	            }
	            e.printStackTrace();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

	public int getCountQtyProductWishList(int id_user) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("countQtyWishList");

			query.registerStoredProcedureParameter("id_userWL", Integer.class, javax.persistence.ParameterMode.IN);
			query.setParameter("id_userWL", id_user);

			query.execute();

			Object result = query.getSingleResult();
			return (int) result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error executing countQtyWishList", e);
		} finally {
			em.close();
		}
	}

	public List<Object[]> getFillProduct_WishList(int user_id) {
		EntityManager em = JpaUtils.getEntityManager();

		try {

			StoredProcedureQuery query = em.createStoredProcedureQuery("fillItemWishList2");
			query.registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN);
			query.setParameter("user_id", user_id);
			List<Object[]> listResult = query.getResultList();

			return listResult;

		} finally {
			em.close();
		}
	}

	public static void main(String[] args) throws Exception {
		WishListDAO dao = new WishListDAO();
		dao.deleteWishList(7,2);
		System.out.println("Deleted");
	}
}
