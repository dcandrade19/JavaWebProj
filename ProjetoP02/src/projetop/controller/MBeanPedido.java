package projetop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javafx.util.converter.BigDecimalStringConverter;
import projetop.dao.ProdutoDao;
import projetop.entity.Item;
import projetop.entity.Produto;

@ManagedBean (name = "mBeanPedido")
@SessionScoped
public class MBeanPedido {

	private ArrayList<Item> itens = new ArrayList<Item>();
	private BigDecimal total = new BigDecimal(0);
	
	public String adicionar(Integer codigo) {
		
		Item item = new Item();
		Produto produto = new ProdutoDao().buscar(codigo);
		
		item.setProduto(produto);
		item.setQuantidade(1);

		itens.add(item);

		// adicionando o preco do produto ao preco total
		total = total.add(new BigDecimal(produto.getPreco().toString()));
		
		return "TelaPedido.jsf";
	}

	
	public void remover(Item item) {
		
		itens.remove(item);
		// subtraindo o preco do produto do preco total
		total = total.subtract(new BigDecimal(item.getProduto().getPreco().toString()));
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
	
	
	
}
