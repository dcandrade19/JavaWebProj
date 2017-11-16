/**
 * 
 */
package projetop.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import projetop.entity.Cliente;

/**
 * @author Diego
 *
 */
@ManagedBean (name = "mBeanCliente")
public class MBeanCliente {

	static private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	 String cpf;
	 String nome;
	 String telefone;
	 String celular;
	 Date nascimento;
	 String email;
	 String senha;
	 String rua;
	 String complemento;
	 String cidade;
	 String estado;
	 String cep;
	 Boolean status;
	 
	
	public void salvar() {
		Cliente cliente = new Cliente(cpf, nome, telefone, celular, nascimento, email,
				                      senha, rua, complemento, cidade, estado, cep, status);
		clientes.add(cliente);
		System.out.println(cliente.toString());
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

	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(ArrayList<Cliente> clientes) {
		MBeanCliente.clientes = clientes;
	}
	 
	 
}
