package br.com.alura.loja_jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja_jpa.modelo.Categoria;
import br.com.alura.loja_jpa.modelo.Produto;
import br.com.alura.loja_jpa.utils.JPAUtil;

public class CadastroDeProduto2 {
	
	public static void main(String[] args) {	
		Categoria celulares = new Categoria("CELULARES");

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, 1l);
		produto.setDescricao("Teste 1");
		em.flush();
		produto.setDescricao("Teste 2");
		em.merge(produto);
		produto.setDescricao("Teste 3");
		em.getTransaction().commit();
		em.close();
	}

}
