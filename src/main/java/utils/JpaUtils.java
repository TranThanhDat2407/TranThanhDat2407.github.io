package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	static public EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TRAICAY_DUAN1_V1");
		return emf.createEntityManager();
	}
}
