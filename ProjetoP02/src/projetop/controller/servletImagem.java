package projetop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projetop.dao.ProdutoDao;
import projetop.entity.Produto;

/**
 * Servlet implementation class servletImagem
 */
@WebServlet("/servletImagem")
public class servletImagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileInputStream fis;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletImagem() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		Produto produto = new ProdutoDao().buscar(Integer.parseInt(codigo));
		
		File f = new File(produto.getFoto());
		fis = new FileInputStream(f);
		byte [] arrayImagem = new byte[(int)f.length()];
		fis.read(arrayImagem);
		
		response.getOutputStream().write(arrayImagem);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
