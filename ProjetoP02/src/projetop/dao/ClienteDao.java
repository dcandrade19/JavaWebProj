package projetop.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projetop.entity.Cliente;

public class ClienteDao {

	public void inserir(Cliente cliente) {
		EntityManager em = Conexao.getInstance().createEntityManager();

		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
	}

	public Cliente buscar(String cpf) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, cpf);
		em.getTransaction().commit();
		em.close();
		return cliente;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Cliente");
		ArrayList<Cliente> list = new ArrayList<Cliente>(q.getResultList());
		em.close();
		return list;
	}

	public void alterar(Cliente cliente) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		em.close();
	}

	public void remover(String cpf) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, cpf);
		em.remove(cliente);
		em.getTransaction().commit();
		em.close();
	}
}
