package br.com.alura.loja_jpa.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja_jpa.dao.CategoriaDao;
import br.com.alura.loja_jpa.dao.ProdutoDao;
import br.com.alura.loja_jpa.modelo.Categoria;
import br.com.alura.loja_jpa.modelo.Produto;
import br.com.alura.loja_jpa.utils.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {	
		cadatrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
		
		for(Produto produto : todos) {
			System.out.println(produto.getNome());
		}
		
		List<Produto> todosPorNome = produtoDao.buscarPorNome("Xiaomi Redmi");
		
		for(Produto produto : todosPorNome) {
			System.out.println(produto.getNome());
		}
		
		List<Produto> todosPorNomCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		
		for(Produto produto : todosPorNomCategoria) {
			System.out.println(produto.getNome());
		}
		
		BigDecimal precoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println(precoProduto);
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
