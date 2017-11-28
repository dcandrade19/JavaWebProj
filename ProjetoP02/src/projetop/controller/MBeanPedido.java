package projetop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import projetop.dao.PedidoDao;
import projetop.dao.ProdutoDao;
import projetop.entity.Cliente;
import projetop.entity.Item;
import projetop.entity.Pedido;
import projetop.entity.Produto;

@ManagedBean (name = "mBeanPedido")
@SessionScoped
public class MBeanPedido {

	private String tela;
	
	private ArrayList<Item> itens = new ArrayList<Item>();

	private BigDecimal total = new BigDecimal(0);
	
	public String salvarPedido() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
		
		Pedido pedido = new Pedido();
		pedido.setData(new Date());
		pedido.setItens(itens);
		pedido.setCliente(cliente);
		for(Item item : itens) {
			item.setPedido(pedido);
		}
		
		new PedidoDao().inserir(pedido);
		
		return "Perfil.jsf";
	}
	
	public void adicionar(Integer codigo) throws IOException {
		
		Produto produto = new ProdutoDao().buscar(codigo);
		
		Item item = buscarProduto(produto);
		
		if(item == null) {
			item = new Item();
			item.setProduto(produto);
			item.setQuantidade(1);
			item.setTotal(produto.getPreco());
			itens.add(item);
		} else {
			item.setQuantidade(item.getQuantidade() + 1);
			item.setTotal(calcularCusto(item));
		}
		
		// adicionando o preco do produto ao preco total
		total =	total.add(item.getProduto().getPreco());

		
	}

	

	public Item buscarProduto(Produto produto) {
		
		for(Item item : itens) {
			if(item.getProduto().getCodigo() == produto.getCodigo()) {
				return item;
			} 
		}
		
		return null;
	}
	
	public BigDecimal calcularCusto(Item item) {
		
		BigDecimal custo = item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade()));

		return custo;
	}
	
	
	public void alterarTela() {
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
		
		if(cliente == null) {
			tela = "deslogado";
		} else if(cliente.getCpf().equals("000.000.000-00")){
			tela = "adm";
		} else {
			tela = "logado";
		}
	}
	
	public void remover(Item item) {
		
		itens.remove(item);
		// subtraindo o preco do produto do preco total
		total = total.subtract(item.getTotal());
	}
	
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

	
}
