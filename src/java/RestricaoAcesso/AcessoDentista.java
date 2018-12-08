package RestricaoAcesso;

import clinteeth.dao.DentistaDAO;
import clinteeth.model.Dentista;
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

@WebFilter(filterName = "AcessoDentista", urlPatterns = {"/dentista/*","/Dentista","/Pessoa"}, dispatcherTypes = {DispatcherType.REQUEST})
public class AcessoDentista implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpSession sessaoUsuario = ((HttpServletRequest)request).getSession();
        Login usuario = (Login) sessaoUsuario.getAttribute("loginAutenticado");
        
        if (usuario !=null && usuario.getTipo().equals(PessoasEnum.DENTISTA)){
            DentistaDAO dentistaDAO = new DentistaDAO();
            Dentista dentista = new Dentista();
            dentista = dentistaDAO.listarDentistaIDporIDPESSOA(usuario.getPessoa().getPessoaID());
             sessaoUsuario.setAttribute("Dentista", dentista);           
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/acessoNegado.jsp");
        }
    }    


    @Override
    public void destroy() {
    }
}
