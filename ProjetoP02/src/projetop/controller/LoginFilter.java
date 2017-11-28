package projetop.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import projetop.entity.Cliente;

@WebFilter(urlPatterns = {"/TelaPedido.jsf", "/Perfil.jsf"})
public class LoginFilter implements Filter {

	
		@Override
		public void destroy() {	
		}

		@Override
		public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
				throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest)arg0;
			HttpServletResponse res = (HttpServletResponse)arg1;
			
			
			//tento capturar o usu�rio da sess�o
			Cliente cliente = (Cliente) req.getSession().
					getAttribute("cliente");
			
			//caso seja nulo redireciono para a tela de login
			//neste ponto adiciono qual tela o usu�rio tentava acessar
			if (cliente == null) {
				req.getSession().setAttribute("pagina", "TelaPedido.jsf");
				res.sendRedirect("TelaLogin.jsf");
			}
			
			arg2.doFilter(req, res);	
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {		
		}


}
