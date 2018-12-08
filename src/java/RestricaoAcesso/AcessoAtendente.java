package RestricaoAcesso;

import clinteeth.dao.AtendenteDAO;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clinteeth.model.PessoasEnum;
import clinteeth.model.Login;
import clinteeth.model.Pessoa;

@WebFilter(filterName = "AcessoAtendente", urlPatterns = {"/atendente/*","/Paciente"}, dispatcherTypes = {DispatcherType.REQUEST})
public class AcessoAtendente implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpSession sessaoUsuario = ((HttpServletRequest)request).getSession();
        Login usuario = (Login) sessaoUsuario.getAttribute("loginAutenticado");
        
        if (usuario !=null && usuario.getTipo().equals(PessoasEnum.ATENDENTE)){
            AtendenteDAO atendenteDAO = new AtendenteDAO();
            Pessoa pessoa = new Pessoa();
            pessoa = atendenteDAO.listarPorId(usuario.getPessoa().getPessoaID());
            sessaoUsuario.setAttribute("Atendente", pessoa);
            
            chain.doFilter(request, response);            
        }else{
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/acessoNegado.jsp");
        }
    }    


    @Override
    public void destroy() {
    }
}
