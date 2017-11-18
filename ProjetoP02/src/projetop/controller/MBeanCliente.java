/**
 * 
 */
package projetop.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import projetop.dao.ClienteDao;
import projetop.dao.ProdutoDao;
import projetop.entity.Cliente;
import projetop.entity.Produto;

/**
 * @author Diego
 *
 */
@ManagedBean(name = "mBeanCliente")
public class MBeanCliente {

	static private ArrayList<Cliente> clientes;

	static private Cliente resultadoBusca;

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

	public void salvar() {
		Cliente cliente = new Cliente();

		cliente.setCpf(this.cpf);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setCelular(celular);
		cliente.setNascimento(nascimento);
		cliente.setEmail(email);
		cliente.setSenha(senha);
		cliente.setRua(rua);
		cliente.setComplemento(complemento);
		cliente.setCidade(cidade);
		cliente.setEstado(estado);
		cliente.setCep(cep);
		cliente.setStatus(status);

		if (this.cpf == null) {

			new ClienteDao().inserir(cliente);
		} else {
			new ClienteDao().alterar(cliente);
			System.out.println(cpf);
		}

		clientes = new ClienteDao().listar();

		System.out.println(cliente.toString());
	}

	public void alterar(Cliente cliente) {
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

	public void excluir(Cliente cliente) {
		new ClienteDao().remover(cliente.getCpf());
		clientes = new ClienteDao().listar();
		if (cliente == resultadoBusca) {
			resultadoBusca = null;
		}
		System.out.println("teste" + cliente.getCpf());
	}

	public void buscar() {
		if (cpf != null) {
			Cliente cliente = new ClienteDao().buscar(cpf);
			setResultadoBusca(cliente);
			if (cliente != null) {
				System.out.println("Resultado da busca: " + cliente);
				
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
					} else {
						System.out.println("CPF: " + cpf + " não localizado!");
					}
		} else {
			System.out.println("CPF Invalido!");
		}
	}
		
	public void entrar(String senha) {
		if (cpf != null) {
			Cliente cliente = new ClienteDao().buscar(cpf);
			if(cliente != null) {
				if(cliente.getSenha().equals(senha)) {
					System.out.println("Entrando como: " + cliente.getNome() + ".");
				} else {
					System.out.println("Senha errada!");
				}
			} else {
				System.out.println("CPF: " + cpf + " não localizado!");
			}
			
		}
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		MBeanCliente.clientes = clientes;
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

	public static Cliente getResultadoBusca() {
		return resultadoBusca;
	}

	public static void setResultadoBusca(Cliente resultadoBusca) {
		MBeanCliente.resultadoBusca = resultadoBusca;
	}

}
