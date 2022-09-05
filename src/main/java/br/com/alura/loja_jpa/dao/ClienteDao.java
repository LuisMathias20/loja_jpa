package br.com.alura.loja_jpa.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja_jpa.modelo.Cliente;
import br.com.alura.loja_jpa.modelo.Pedido;
import br.com.alura.loja_jpa.modelo.Produto;

public class ClienteDao {

	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}
}
