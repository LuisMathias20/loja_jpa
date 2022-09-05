package br.com.alura.loja_jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja_jpa");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
