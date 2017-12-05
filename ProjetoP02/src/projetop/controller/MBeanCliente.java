/**
 * 
 */
package projetop.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import projetop.dao.ClienteDao;
import projetop.entity.Cliente;

/**
 * Classe bean de controle do cliente
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

	/**
	 * Verifica se existe uma conta de ADM caso não exista cria a conta
	 */
	public void setAdm() {

		if (new ClienteDao().buscar("000.000.000-00") == null) {
			Cliente cliente = new Cliente();
			cliente.setCpf("000.000.000-00");
			cliente.setNome("Adm");
			cliente.setSenha("admin");
			new ClienteDao().inserir(cliente);
			System.out.println("Conta ADM criada.");
		} else {
			System.out.println("Conta ADM localizada.");
		}
	}

	/**
	 * Salva um cliente caso o mesmo não exista na base, caso contrario
	 * altera o cliente existente
	 */
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
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente: " + cliente.getNome() + " salvo!", ""));
		} else {
			new ClienteDao().alterar(cliente);
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente: " + cliente.getNome() + " alterado!", ""));
			System.out.println(cpf);
		}

		clientes = new ClienteDao().listar();

		System.out.println(cliente.toString());
	}

	/**
	 * Atualiza a lista de clientes
	 */
	public void carregar() {

		clientes = new ClienteDao().listar();

	}

	/**
	 * Seta os atributos para alteração
	 * @param cliente
	 */
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

	/**
	 * Ativa e Desativa a conta do cliente
	 * @param cliente
	 */
	public void ativar_desativar(Cliente cliente) {
		if (cliente.getStatus()) {
			cliente.setStatus(false);
			new ClienteDao().alterar(cliente);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Cliente: " + cliente.getNome() + " desativado!", ""));
		} else {
			cliente.setStatus(true);
			new ClienteDao().alterar(cliente);
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente: " + cliente.getNome() + " ativado!", ""));
		}
	}

	/**
	 * Exclui o cliente da base
	 * @param cliente
	 */
	public void excluir(Cliente cliente) {
		String nome = cliente.getNome();
		new ClienteDao().remover(cliente.getCpf());
		clientes = new ClienteDao().listar();
		if (cliente == resultadoBusca) {
			resultadoBusca = null;
		}
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente: " + nome + " excluido!", ""));
	}

	/**
	 * Busca um cliente na base pelo CPF
	 */
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

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF: " + cliente.getCpf() + " localizado!", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF: " + cpf + " não localizado!", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF: inválido!", ""));
		}
	}

	/**
	 * Verifica se a senha e o cpf do cliente estão corretos
	 * caso esteja insere o cliente na sessão
	 * @param senha
	 * @return
	 */
	public String entrar(String senha) {
		if (cpf != null) {
			Cliente cliente = new ClienteDao().buscar(cpf);
			if (cliente != null) {
				if (cliente.getSenha().equals(senha)) {
					System.out.println("Entrando como: " + cliente.getNome() + ".");
					// capture o objeto de request
					// nele é possível recuperar a sessão
					HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
							.getRequest();
					// adiciono na sessão o usuário que fez o login
					req.getSession().setAttribute("cliente", cliente);

					// redireciono para tela que ele estava tentando acessar
					return "" + req.getSession().getAttribute("pagina");
				} else {
					FacesContext.getCurrentInstance().addMessage("",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos!", ""));
					return "";
				}
			}
		}
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dados inválidos!", ""));
		return "";
	}

	/**
	 * Retira o cliente atual da sessão
	 * @return
	 */
	public String sair() {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		req.getSession().setAttribute("cliente", null);

		return "index.jsf";
	}

	// Getters and Setters
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
