package br.com.alura.loja_jpa.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.vo.RelatorioDeVendasVo;
import br.com.alura.loja_jpa.dao.CategoriaDao;
import br.com.alura.loja_jpa.dao.ClienteDao;
import br.com.alura.loja_jpa.dao.PedidoDao;
import br.com.alura.loja_jpa.dao.ProdutoDao;
import br.com.alura.loja_jpa.modelo.Categoria;
import br.com.alura.loja_jpa.modelo.Cliente;
import br.com.alura.loja_jpa.modelo.ItemPedido;
import br.com.alura.loja_jpa.modelo.Pedido;
import br.com.alura.loja_jpa.modelo.Produto;
import br.com.alura.loja_jpa.utils.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);	
		Produto produto = produtoDao.buscarPorId(1L);
		
		em.getTransaction().begin();
		
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorId(1L);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL: "+totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		for(RelatorioDeVendasVo item : relatorio) {
			System.out.println(item.toString());
		}
		
		em.close();
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
