package clinteeth.controller;

import clinteeth.dao.LoginDAO;
import clinteeth.dao.PessoaDAO;
import clinteeth.model.Login;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

    private static LoginDAO loginDAO = new LoginDAO();
    private static PessoaDAO pessoaDAO = new PessoaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("btnAcionar")) {
            case "Sair": {
                Sair(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String btnAcionar = request.getParameter("btnAcionar");
            
            if (btnAcionar.equals("Entrar")) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                Login login = loginDAO.VerificarAcesso(email, senha); // chama a dao que verifica o login no banco e retorna se os dados são iguais ou não
                
                if (login != null) {
                    HttpSession sessaoUsuario = request.getSession();//cria uma sessao para o usuario
                    sessaoUsuario.setAttribute("loginAutenticado", login);                    
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                    //request.setAttribute("pessoas", pessoaDAO.listAll());
                    //request.getRequestDispatcher("dentista/listarPessoas.jsp").forward(request, response);
                } else {
                    String falhaLogin = ("Usuario e senha invalidos");
                    request.setAttribute("falhaLogin", falhaLogin);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else if (btnAcionar.equals("Sair")) {
                System.out.println("ENTROU NO SAIR");
                HttpSession sessaoUsuario = request.getSession();
                sessaoUsuario.removeAttribute("usuarioAutenticado");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                //((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/logout.jsp");
                System.out.println("LOGOUT");
            }
        } catch (SQLException erroSQL) {
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro", erroSQL);
            rd.forward(request, response);
        } catch (ClassNotFoundException erroClass) {
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro", erroClass);
            rd.forward(request, response);
        }

    }

    private void Sair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("ENTROU NO SAIR");
            HttpSession sessaoUsuario = request.getSession();
            sessaoUsuario.removeAttribute("usuarioAutenticado");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            //((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/logout.jsp");
            System.out.println("LOGOUT");
    }

}
