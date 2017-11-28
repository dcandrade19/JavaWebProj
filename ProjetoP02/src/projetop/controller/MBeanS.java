package projetop.controller;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import projetop.entity.Cliente;

@ManagedBean(name = "mBeanS")
public class MBeanS {

	private String cpf;
	private String nome;
	private String telefone;
	private String celular;
	private Date nascimento;
	private String email;
	private String senha;
	private String rua;
	private String complemento;
	private String cidade;
	private String estado;
	private String cep;
	private Boolean status;

	public void buscaUsuarioSession() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
		if (cliente != null) {
			this.cpf = cliente.getCpf();
			this.nome = cliente.getNome();
			this.telefone = cliente.getTelefone();
			this.celular = cliente.getCelular();
			this.nascimento = cliente.getNascimento();
			this.email = cliente.getEmail();
			this.senha = cliente.getSenha();
			this.rua = cliente.getRua();
			this.complemento = cliente.getComplemento();
			this.cidade = cliente.getCidade();
			this.estado = cliente.getEstado();
			this.cep = cliente.getCep();
			this.status = cliente.getStatus();
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
