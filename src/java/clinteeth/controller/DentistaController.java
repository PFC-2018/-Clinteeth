package clinteeth.controller;

import clinteeth.dao.EnderecoDAO;
import clinteeth.dao.LoginDAO;
import clinteeth.dao.DentistaDAO;
import clinteeth.dao.PessoaDAO;
import clinteeth.model.Endereco;
import clinteeth.model.EstadosEnum;
import clinteeth.model.Login;
import clinteeth.model.Dentista;
import clinteeth.model.Pessoa;
import clinteeth.model.PessoasEnum;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DentistaController", urlPatterns = {"/Dentista"})
public class DentistaController extends HttpServlet {

    private static EnderecoDAO enderecoDAO = new EnderecoDAO();
    private static LoginDAO loginDAO = new LoginDAO();
    private static DentistaDAO dentistaDAO = new DentistaDAO();
    private static PessoaDAO pessoaDAO = new PessoaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "ListarTudo": {
                listAll(request, response);
                break;
            }
            case "ListarPorID": {
                ListarDentistaPorId(request, response);
                break;
            }
            case "Excluir": {
                excluirDentista(request, response);
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "Cadastrar": {
                cadastrarDentista(request, response);
                break;
            }

            case "Alterar": {
                alterarDentista(request, response);
                break;
            }

        }
    }

    private void cadastrarDentista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            Dentista dentista = new Dentista();
            dentista.setNome(request.getParameter("nome"));
            dentista.setRg(request.getParameter("rg"));
            dentista.setCpf(request.getParameter("cpf"));
            dentista.setDataNascimento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataNascimento")).getTime()));
            dentista.setTelefone(request.getParameter("telefone"));
            dentista.setCelular(request.getParameter("celular"));            
            dentista.setCro(request.getParameter("cro"));
            dentista.setEndereco(endereco);
            pessoaDAO.cadastrarPessoa(dentista);
            dentistaDAO.cadastrarDentista(dentista);

            Login login = new Login();
            login.setEmail(request.getParameter("email"));
            login.setSenha(request.getParameter("senha"));
            login.setTipo(PessoasEnum.valueOf(request.getParameter("tipo")));
            login.setPessoa(dentista);
            dentista.setLogin(login);
            loginDAO.cadastrarLogin(login);

            /*if (pessoa.getTipo() ==  DentistasEnum.DENTISTA)*/
            listAll(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(DentistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alterarDentista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Endereco endereco = new Endereco();
            endereco.setEnderecoID(Integer.parseInt(request.getParameter("idEndereco")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setUf(EstadosEnum.valueOf(request.getParameter("uf")));
            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setCep(request.getParameter("cep"));
            enderecoDAO.alterarEndereco(endereco);

            Pessoa pessoa = new Pessoa();
            pessoa.setPessoaID(Integer.parseInt(request.getParameter("idPessoa")));
            pessoa.setNome(request.getParameter("nome"));
            pessoa.setRg(request.getParameter("rg"));
            pessoa.setCpf(request.getParameter("cpf"));
            pessoa.setDataNascimento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataNascimento")).getTime()));//Converte String recebida na Servlet p/ Data
            pessoa.setTelefone(request.getParameter("telefone"));
            pessoa.setCelular(request.getParameter("celular"));            
            pessoa.setEndereco(endereco);
            pessoaDAO.alterarPessoa(pessoa);

            Dentista dentista = new Dentista();
            dentista.setDentistaID(Integer.parseInt(request.getParameter("idDentista")));
            dentista.setCro(request.getParameter("cro"));
            dentista.setPessoa(pessoa);
            dentistaDAO.alterarDentista(dentista);

            Login login = new Login();
            login.setEmail(request.getParameter("email"));
            login.setSenha(request.getParameter("senha"));
            login.setTipo(PessoasEnum.valueOf(request.getParameter("tipo")));
            login.setPessoa(pessoa);
            loginDAO.alterarLogin(login);

            listAll(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(DentistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluirDentista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pessoaDAO.excluirPessoa(id);
        dentistaDAO.excluirDentista(id);
        loginDAO.excluirLogin(id);
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("listarDentistas.jsp").forward(request, response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("dentista/listarDentistas.jsp").forward(request, response);
    }

    private void ListarDentistaPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Dentista dentista = dentistaDAO.listarPorId(id);
        request.setAttribute("dentista", dentista);
        request.setAttribute("login", loginDAO.buscarPessoa(id));
        request.getRequestDispatcher("dentista/atualizarDentistas.jsp").forward(request, response);
    }

}
