package RestricaoAcesso;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import clinteeth.model.Login;

@WebFilter(filterName = "AcessoLogado", urlPatterns = {"/teste.jsp"}, dispatcherTypes = {DispatcherType.REQUEST})/*no tela.jsp inserir a tela inicial do sistema após o usuário efetuar o Login*/
public class AcessoLogado implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();
        Login usuarioLogado = (Login) sessaoUsuario.getAttribute("loginAutenticado");

        if (usuarioLogado != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("naoAutenticado.jsp");
        }

    }

    public void destroy() {
    }

}
