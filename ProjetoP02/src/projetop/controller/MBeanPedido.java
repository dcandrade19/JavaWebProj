package projetop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import projetop.dao.ClienteDao;
import projetop.dao.PedidoDao;
import projetop.dao.ProdutoDao;
import projetop.entity.Cliente;
import projetop.entity.Item;
import projetop.entity.Pedido;
import projetop.entity.Produto;


/**
 * Classe bean de controle do pedido
 * @author Diego
 *
 */
@ManagedBean(name = "mBeanPedido")
@SessionScoped
public class MBeanPedido {

	private String tela;

	private ArrayList<Item> itens = new ArrayList<Item>();

	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

	private ArrayList<Pedido> pedidosCliente = new ArrayList<Pedido>();

	private BigDecimal total = new BigDecimal(0);

	/**
	 * Salva o pedido do cliente
	 * @return
	 */
	public String salvarPedido() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");

		Pedido pedido = new Pedido();
		pedido.setData(new Date());
		pedido.setItens(itens);
		pedido.setCliente(cliente);
		pedido.setTotal(total);
		for (Item item : itens) {
			item.setPedido(pedido);
		}

		new PedidoDao().inserir(pedido);
		itens.clear();
		total = BigDecimal.ZERO;
		pedidos.add(pedido);

		return "Perfil.jsf";
	}

	/**
	 * Separa os pedidos exclusivos do cliente da sessão atual
	 */
	public void separarPedido() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");

		pedidos = new PedidoDao().listar();
		pedidosCliente.clear();
		for (Pedido pedido : pedidos) {
			if (pedido.getCliente().getCpf().equals(cliente.getCpf())) {
				pedidosCliente.add(pedido);
			}
		}

	}

	/**
	 * Adiciona os produtos ao item
	 * @param codigo
	 * @throws IOException
	 */
	public void adicionar(Integer codigo) throws IOException {

		Produto produto = new ProdutoDao().buscar(codigo);
		Item item = buscarProduto(produto);

		if (item == null) {
			item = new Item();
			item.setProduto(produto);
			item.setQuantidade(1);
			item.setTotal(produto.getPreco());
			itens.add(item);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Produto: " + produto.getNome() + " adicionado ao pedido!", ""));
		} else {
			item.setQuantidade(item.getQuantidade() + 1);
			item.setTotal(calcularCusto(item));
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Produto: " + produto.getNome() + " +1!", ""));
		}

		// adicionando o preco do produto ao preco total
		total = total.add(item.getProduto().getPreco());
	}

	/**
	 * Verifica se o produto já existe na lista de itens
	 * @param produto
	 * @return item ou null
	 */
	public Item buscarProduto(Produto produto) {

		for (Item item : itens) {
			if (item.getProduto().getCodigo() == produto.getCodigo()) {
				System.out.println("Produto já existe!");
				return item;
			}
		}
		System.out.println("Produto não existe!");
		return null;
	}

	/**
	 * Calcula o custo total do item de acordo com a quantidade
	 * @param item
	 * @return
	 */
	public BigDecimal calcularCusto(Item item) {

		BigDecimal custo = item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade()));

		return custo;
	}

	/**
	 * Altera as opções da tela durante a sessão de acordo
	 * com o tipo de cliente adm/user
	 */
	public void alterarTela() {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");

		if (cliente == null) {
			tela = "deslogado";
		} else if (cliente.getCpf().equals("000.000.000-00")) {
			tela = "adm";
		} else {
			tela = "logado";
		}
	}

	/**
	 * Remove o item da lista de itens e seta o valor total
	 * @param item
	 */
	public void remover(Item item) {

		itens.remove(item);
		// subtraindo o preco do produto do preco total
		total = total.subtract(item.getTotal());
	}

	/**
	 * Exclui o pedido do cliente
	 * @param pedido
	 */
	public void excluirPedido(Pedido pedido) {
		new PedidoDao().remover(pedido.getCodigo());
		pedidos = new PedidoDao().listar();
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pedido: " + pedido.getCodigo() + " excluido!", ""));
	}

	// Getters and Setters
	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public ArrayList<Pedido> getPedidosCliente() {
		return pedidosCliente;
	}

	public void setPedidosCliente(ArrayList<Pedido> pedidosCliente) {
		this.pedidosCliente = pedidosCliente;
	}

}
