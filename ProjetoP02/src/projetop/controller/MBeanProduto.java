/**
 * 
 */
package projetop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import javax.faces.bean.ManagedBean;

import org.apache.catalina.core.ApplicationPart;


import projetop.dao.ProdutoDao;
import projetop.entity.Produto;

/**
 * @author Diego
 *
 */
@ManagedBean (name = "mBeanProduto")
public class MBeanProduto {

	 static private ArrayList<Produto> produtos;
	 
	 static private Produto resultadoBusca;
	 
 
	 private Integer codigo;
	 private String nome;
	 private ApplicationPart foto;
	 private String descricao;
	 private String tamanho;
	 private String tipo;
	 private BigDecimal preco;

	private FileOutputStream fos;
	 
	/**
	 *  Cria o Dao e chama a função que gera a list de Produtos.
	 *  Envia a lista para o array "produtos".
	 */
	public void carregar() {
		
		produtos = new ProdutoDao().listar();
		
	}
	
	
	public void salvar() {
		
		// Cria produto.
		Produto produto = new Produto();
		
		// Especificando o caminho da imagem
		String caminhoImagem = "c:\\imagens\\" +foto.getSubmittedFileName();
		
		try {
			// Cria o espaço na memoria que vai armazenar a imagem
			byte [] bytesImagem = new byte[(int)foto.getSize()];
			// Lê o conteudo da imagem e coloca dentro do array de bytes
			foto.getInputStream().read(bytesImagem);
			// Cria uma referencia ao arquivo que vai ser criado
			File f = new File(caminhoImagem);
			fos = new FileOutputStream(f);
			// Escreve o conteudo da imagem (upload) dentro do arquivo no servidor
			fos.write(bytesImagem);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// Seta os atributos.
		produto.setCodigo(this.codigo);
		produto.setNome(nome);
		produto.setFoto(caminhoImagem);
		produto.setDescricao(descricao);
		produto.setTamanho(tamanho);
		produto.setTipo(tipo);
		produto.setPreco(preco);
		
		// Verifica se o codigo é nulo, se sim insere o produto, se não altera.
		if(this.codigo == null) {
			
			new ProdutoDao().inserir(produto);
		} else {
			new ProdutoDao().alterar(produto);
			System.out.println(codigo);
		}
		
		// Atualiza a lista de produtos.
		produtos = new ProdutoDao().listar();
		
		// Printa as informações no console para confirmação.
		System.out.println(produto.toString());
	}
	
	public void alterar(Produto produto) {
		this.codigo = produto.getCodigo();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.tamanho = produto.getTamanho();
		this.tipo = produto.getTipo();
		this.preco = produto.getPreco();
	}
	
	
	public void excluir(Produto produto) {
		new ProdutoDao().remover(produto.getCodigo());
		produtos = new ProdutoDao().listar();
		if(produto == resultadoBusca) {
			resultadoBusca = null;
		}
		System.out.println("teste" + produto.getCodigo());
	}
	
	
	public void buscar() {
		if(codigo != null) {
			setResultadoBusca(new ProdutoDao().buscar(codigo));
			if(resultadoBusca != null) {
				System.out.println("Resultado da busca: " + resultadoBusca);
				for(Produto produto : produtos) {
					if(produto.getCodigo() == resultadoBusca.getCodigo() && produtos.size() > 1) {
						Collections.swap(produtos, 0, produtos.indexOf(produto));
						this.codigo = produto.getCodigo();
						this.nome = produto.getNome();
						this.descricao = produto.getDescricao();
						this.tamanho = produto.getTamanho();
						this.tipo = produto.getTipo();
						this.preco = produto.getPreco();
					}
				}
				
			} else {
				System.out.println("Codigo: " + codigo + " não localizado!");
			}
			
		} else {
			System.out.println("Codigo Invalido!");
		}
	}
	
	public Integer getCodigo() {
		return codigo;
	}

    public void setCodigo(Integer codigo) {
		
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ApplicationPart getFoto() {
		return foto;
	}
	public void setFoto(ApplicationPart foto) {
		this.foto = foto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public  ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		MBeanProduto.produtos = produtos;
	}

	public  Produto getResultadoBusca() {
		return resultadoBusca;
	}

	public  void setResultadoBusca(Produto resultadoBusca) {
		MBeanProduto.resultadoBusca = resultadoBusca;
	}

}
