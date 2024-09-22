package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import entity.OrderItem;
import entity.Review;
import entity.User;
import utils.JpaUtils;

public class ReviewDAO {

	public Review create(Review review) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(review);
			transaction.commit();
			return review;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public Review update(Review review) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Review updatedReview = em.merge(review);
			transaction.commit();
			return updatedReview;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public boolean delete(Integer reviewId) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Review review = em.find(Review.class, reviewId);
			if (review != null) {
				em.remove(review);
				transaction.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public Review findById(Integer reviewId) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			return em.find(Review.class, reviewId);
		} finally {
			em.close();
		}
	}

	public Map<String, Integer> getProductReviewCounts(Integer productId) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("GetProductReviewCounts")
					.registerStoredProcedureParameter("product_id", Integer.class, ParameterMode.IN)
					.setParameter("product_id", productId);

			query.execute();

			Object[] result = (Object[]) query.getSingleResult();

			Map<String, Integer> reviewCounts = new HashMap<>();
			reviewCounts.put("five_star_count", (result[0] != null) ? ((Number) result[0]).intValue() : 0);
			reviewCounts.put("four_star_count", (result[1] != null) ? ((Number) result[1]).intValue() : 0);
			reviewCounts.put("three_star_count", (result[2] != null) ? ((Number) result[2]).intValue() : 0);
			reviewCounts.put("two_star_count", (result[3] != null) ? ((Number) result[3]).intValue() : 0);
			reviewCounts.put("one_star_count", (result[4] != null) ? ((Number) result[4]).intValue() : 0);

			return reviewCounts;
		} finally {
			em.close();
		}
	}

	public List<Review> getProductReviews(Integer productId) {
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	        StoredProcedureQuery query = em.createStoredProcedureQuery("GetProductReviews")
	            .registerStoredProcedureParameter("product_id", Integer.class, ParameterMode.IN)
	            .setParameter("product_id", productId);

	        query.execute();

	        @SuppressWarnings("unchecked")
	        List<Object[]> results = query.getResultList();

	        List<Review> reviews = new ArrayList<>();
	        for (Object[] row : results) {
	            Review review = new Review();
	            review.setUser(new User()); // Assuming you have a User class
	            review.getUser().setName((String) row[0]);
	            review.setCreateAt((Date) row[1]);
	            review.setRatingValue(((Number) row[2]).intValue());
	            review.setComment((String) row[3]);
	            // If you need to set the id, you might need to add it to your stored procedure result
	            // review.setId(((Number) row[4]).intValue());
	            reviews.add(review);
	        }

	        return reviews;
	    } finally {
	        em.close();
	    }
	}

	public static void main(String[] args) {
		ReviewDAO reviewDAO = new ReviewDAO();
		UserDAO uDao = new UserDAO();
		OrderItemDAO oiDao = new OrderItemDAO();
		try {
			int orderItemId = oiDao.getOrderItemIdByUserAndProduct(14, 2);
			OrderItem oi = oiDao.findById(orderItemId);
			User u = uDao.findByID(14);
			Review review = new Review();
			review.setComment("uwu");
			review.setCreateAt(new Date());
			review.setUpdateAt(new Date());
			review.setOrderedProduct(oi);
			review.setRatingValue(1);
			review.setUser(u);
			reviewDAO.create(review);
			System.out.println("oklun");
		} catch (Exception e) {
			System.out.println("ko ok");
		}
		
		// Test getProductReviewCounts
//		Integer productId = 2; // Replace with an actual product ID
//		Map<String, Integer> reviewCounts = reviewDAO.getProductReviewCounts(productId);
//		System.out.println("Review counts for product " + productId + ":");
//		for (Map.Entry<String, Integer> entry : reviewCounts.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
//
//		// Test getProductReviews
//		List<Review> reviews = reviewDAO.getProductReviews(productId);
//		System.out.println("\nReviews for product " + productId + ":");
//		for (Review review : reviews) {
//			System.out.println("Reviewer: " + review.getUser().getName());
//			System.out.println("Date: " + review.getCreateAt());
//			System.out.println("Rating: " + review.getRatingValue());
//			System.out.println("Comment: " + review.getComment());
//			System.out.println("---");
//		}
		
	}

}
