package DAO;

import java.util.List;

import javax.persistence.*;

import utils.JpaUtils;

public class AbstractDAO<T> {
	
	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction tran = em.getTransaction();
	
	public T findById(Class<T> clazz, Integer id) 
	{
		return em.find(clazz, id);
	}
	
	protected List<T> findByHql(String hql, Class<T> clazz) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            TypedQuery<T> query = em.createQuery(hql, clazz);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
	
	public List<T> findAll(Class<T> clazz)
	{
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o from ").append(entityName).append(" o");
		
		TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
		return query.getResultList();
 	}
	
	public List<T> findAllPage(Class<T> clazz, int pageNumber, int pageSize)
	{
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o from ").append(entityName).append(" o");
		TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
		query.setFirstResult((pageNumber -1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
 	}
	
	public List<T> findAllProduct(Class<T> clazz, int existStock, int pageNumber, int pageSize)
	{
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o from ").append(entityName).append(" o");
		if(existStock > 0) 
		{
			sql.append(" WHERE qty_in_stock > 0");
		}
		TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
		query.setFirstResult((pageNumber -1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
 	}
	
	public T findOne(Class<T> clazz, String sql, Object... params) 
	{
		TypedQuery<T> query = em.createQuery(sql,clazz);
		for(int i = 0; i < params.length; i++) 
		{
			query.setParameter(i, params[i]);
		}
		List<T> result = query.getResultList();
		if(result.isEmpty()) 
		{
			return null;
		}
		return result.get(0);
	}
	
	public List<T> findMany(Class<T> clazz, String sql, Object... params)
	{
		TypedQuery<T> query = em.createQuery(sql,clazz);
		for(int i = 0; i < params.length; i++) 
		{
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findManyByNativeQuery(Class<T> clazz, String sql, Object... params)
	{
		Query query = em.createNativeQuery(sql,clazz);
		for(int i = 0; i < params.length; i++) 
		{
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	
	public T create(T entity) 
	{
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			System.out.println("Create success");
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Cannot insert entity " + entity.getClass().getSimpleName() + " to DB");
			throw new RuntimeException(e);
		}
	}
	
	public T update(T entity)
	{
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			System.out.println("Update success");
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Cannot update entity " + entity.getClass().getSimpleName() + " to DB");
			throw new RuntimeException(e);
		}
	}
	
	public T delete(T entity) 
	{
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			System.out.println("Delete success");
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Cannot delete entity " + entity.getClass().getSimpleName() + " to DB");
			throw new RuntimeException(e);
		}
	}
}























