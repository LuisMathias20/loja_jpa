package br.com.alura.loja_jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja_jpa.dao.CategoriaDao;
import br.com.alura.loja_jpa.dao.ClienteDao;
import br.com.alura.loja_jpa.dao.PedidoDao;
import br.com.alura.loja_jpa.dao.ProdutoDao;
import br.com.alura.loja_jpa.modelo.Categoria;
import br.com.alura.loja_jpa.modelo.Cliente;
import br.com.alura.loja_jpa.modelo.Pedido;
import br.com.alura.loja_jpa.modelo.Produto;
import br.com.alura.loja_jpa.utils.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		
//		Pedido pedido = em.find(Pedido.class, 1L);
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1L);
		
		em.close();
		
		em.close();
		
		System.out.println(pedido.getCliente().getCpf());
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito bom", new BigDecimal("800"), celulares);		
		Cliente cliente = new Cliente("Luis","123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);		
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	}
}
