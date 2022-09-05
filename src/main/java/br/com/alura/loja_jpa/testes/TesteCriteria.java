package br.com.alura.loja_jpa.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja_jpa.dao.CategoriaDao;
import br.com.alura.loja_jpa.dao.ProdutoDao;
import br.com.alura.loja_jpa.modelo.Categoria;
import br.com.alura.loja_jpa.modelo.Produto;
import br.com.alura.loja_jpa.utils.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {	
		cadatrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		List<Produto> produtos = produtoDao.buscarPorParametrosComCriteria("Xiaomi Redmi", null, null);
		
		System.out.println(produtos.get(0).toString());
	}

	private static void cadatrarProduto() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Categoria celulares = new Categoria("CELULARES");
		CategoriaDao categoriaDao = new CategoriaDao(em);

		Produto celular = new Produto("Xiaomi Redmi", "Muito bom", new BigDecimal("800"), celulares);		
		ProdutoDao produtoDao = new ProdutoDao(em);		

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void cadatrarCategoria() { 
		Categoria celulares = new Categoria("CELULARES");

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		em.persist(celulares);
		celulares.getId().setNome("XPTO");
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.getId().setNome("1234");
		em.flush();
		
//		em.remove(celulares);
		em.flush();
		
		em.getTransaction().commit();
	}
}
