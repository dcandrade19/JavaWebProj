package projetop.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projetop.entity.Pedido;

public class PedidoDao {

	
	
	public void inserir(Pedido pedido) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		
		em.getTransaction().begin();
		em.persist(pedido);
		em.getTransaction().commit();
		em.close();
	}
	
	public Pedido buscar(Integer codigo) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Pedido pedido = em.find(Pedido.class, codigo);		
		em.getTransaction().commit();
		em.close();
		return pedido;
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Pedido> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Pedido");
		ArrayList<Pedido> list = new ArrayList<Pedido>(q.getResultList());
		em.close();
		return list;
	}
	
	public void alterar(Pedido pedido) {	
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(pedido);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remover(Integer integer) {	
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Pedido pedido = em.find(Pedido.class, integer);		
		em.remove(pedido);
		em.getTransaction().commit();
		em.close();
	}
}
