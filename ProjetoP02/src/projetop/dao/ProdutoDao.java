package projetop.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projetop.entity.Produto;

public class ProdutoDao {

	public void inserir(Produto produto) {
		EntityManager em = Conexao.getInstance().createEntityManager();

		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
	}

	public Produto buscar(Integer codigo) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, codigo);
		em.getTransaction().commit();
		em.close();
		return produto;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Produto> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Produto");
		ArrayList<Produto> list = new ArrayList<Produto>(q.getResultList());
		return list;
	}

	public void alterar(Produto produto) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
	}

	public void remover(Integer integer) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, integer);
		em.remove(produto);
		em.getTransaction().commit();
	}
}
