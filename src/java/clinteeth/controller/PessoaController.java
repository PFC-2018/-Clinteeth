package clinteeth.controller;

import clinteeth.dao.AtendenteDAO;
import clinteeth.dao.EnderecoDAO;
import clinteeth.dao.LoginDAO;
import clinteeth.dao.PessoaDAO;
import clinteeth.model.Endereco;
import clinteeth.model.EstadosEnum;
import clinteeth.model.Login;
import clinteeth.model.Pessoa;
import clinteeth.model.PessoasEnum;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PessoaController", urlPatterns = {"/Pessoa"})
public class PessoaController extends HttpServlet {

    private static EnderecoDAO enderecoDAO = new EnderecoDAO();
    private static LoginDAO loginDAO = new LoginDAO();
    private static PessoaDAO pessoaDAO = new PessoaDAO();
    private static AtendenteDAO atendenteDAO = new AtendenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "ListarTudo": {
                listAll(request, response);
                break;
            }
            case "ListarPorID": {
                listPorID(request, response);
                break;
            }
            case "Excluir": {
                excluirPessoa(request, response);
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "Cadastrar": {
            try {
                cadastrarPessoa(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }

            case "Alterar": {
                alterarPessoa(request, response);
                break;
            }

        }
    }

    private void cadastrarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {

            Endereco endereco = new Endereco();
            endereco.setLogradouro(request.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setUf(EstadosEnum.valueOf(request.getParameter("uf")));
            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setCep(request.getParameter("cep"));
            enderecoDAO.cadastrarEndereco(endereco);

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(request.getParameter("nome"));
            pessoa.setRg(request.getParameter("rg")); 
            pessoa.setCpf(request.getParameter("cpf")); 
            pessoa.setDataNascimento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataNascimento")).getTime()));
            pessoa.setTelefone(request.getParameter("telefone"));
            pessoa.setCelular(request.getParameter("celular"));
            pessoa.setEndereco(endereco);
            String cpf = request.getParameter("cpf");
            pessoa = pessoaDAO.VerificarCPF(cpf);
            if(cpf.equals(pessoa.getCpf())){
                String verificaCPF = ("CPF Já Existe");
                request.setAttribute("verificaCPF", verificaCPF);
            }else{
                pessoaDAO.cadastrarPessoa(pessoa);
            }


            Login login = new Login();
            login.setEmail(request.getParameter("email"));
            login.setSenha(request.getParameter("senha"));
            login.setTipo(PessoasEnum.valueOf(request.getParameter("tipo")));
            login.setPessoa(pessoa);
            pessoa.setLogin(login);
            loginDAO.cadastrarLogin(login);

            /*if (pessoa.getTipo() ==  PessoasEnum.DENTISTA)*/
            listAll(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alterarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Endereco endereco = new Endereco();
            endereco.setEnderecoID(Integer.parseInt(request.getParameter("id")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setUf(EstadosEnum.valueOf(request.getParameter("uf")));
            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setCep(request.getParameter("cep"));
            enderecoDAO.alterarEndereco(endereco);

            Endereco ee = pessoaDAO.listPorId(Integer.parseInt(request.getParameter("id"))).getEndereco();
            Pessoa pessoa = new Pessoa();
            pessoa.setPessoaID(Integer.parseInt(request.getParameter("id")));
            pessoa.setNome(request.getParameter("nome"));
            pessoa.setRg(request.getParameter("rg"));
            pessoa.setCpf(request.getParameter("cpf"));
            pessoa.setDataNascimento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataNascimento")).getTime()));//Converte String recebida na Servlet p/ Data
            pessoa.setTelefone(request.getParameter("telefone"));
            pessoa.setCelular(request.getParameter("celular"));
            pessoa.setEndereco(endereco);
            pessoaDAO.alterarPessoa(pessoa);

            Login login = new Login();
            login.setEmail(request.getParameter("email"));
            login.setSenha(request.getParameter("senha"));
            login.setTipo(PessoasEnum.valueOf(request.getParameter("tipo")));
            login.setPessoa(pessoa);
            loginDAO.alterarLogin(login);

            listAll(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluirPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pessoaDAO.excluirPessoa(id);
        loginDAO.excluirLogin(id);
        request.setAttribute("pessoas", pessoaDAO.listAll());
        request.getRequestDispatcher("listarPessoas.jsp").forward(request, response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pessoas", atendenteDAO.ListarTodosAtendentes());
        request.getRequestDispatcher("dentista/listarPessoas.jsp").forward(request, response);
    }

    private void listPorID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pessoa pessoa = atendenteDAO.listarPorId(id);
        request.setAttribute("pessoa", pessoa);
        request.setAttribute("login", loginDAO.buscarPessoa(id));
        request.getRequestDispatcher("dentista/atualizarPessoas.jsp").forward(request, response);
    }

}
