/**
 * 
 */
package projetop.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Diego
 *
 */
@Entity
@Table(name="TAB_PRODUTO")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	private String nome;
	private String foto;
	private String descricao;
	private String tamanho;
	private String tipo;
	private BigDecimal preco;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo2) {
		this.codigo = codigo2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
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
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", foto=" + foto + ", descricao=" + descricao
				+ ", tamanho=" + tamanho + ", tipo=" + tipo + ", preco=" + preco + "]";
	}
	
}
