package clinteeth.controller;

import clinteeth.dao.EnderecoDAO;
import clinteeth.dao.LoginDAO;
import clinteeth.dao.PacienteDAO;
import clinteeth.dao.PessoaDAO;
import clinteeth.model.Endereco;
import clinteeth.model.EstadosEnum;
import clinteeth.model.Login;
import clinteeth.model.Paciente;
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

@WebServlet(name = "PacienteController", urlPatterns = {"/Paciente"})
public class PacienteController extends HttpServlet {

    private static EnderecoDAO enderecoDAO = new EnderecoDAO();
    private static LoginDAO loginDAO = new LoginDAO();
    private static PacienteDAO pacienteDAO = new PacienteDAO();
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
                ListarPacientePorId(request, response);
                break;
            }
            case "Excluir": {
                excluirPaciente(request, response);
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "Cadastrar": {
                cadastrarPaciente(request, response);
                break;
            }

            case "Alterar": {
                alterarPaciente(request, response);
                break;
            }

        }
    }

    private void cadastrarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            Paciente paciente = new Paciente();
            paciente.setNome(request.getParameter("nome"));
            paciente.setRg(request.getParameter("rg"));
            paciente.setCpf(request.getParameter("cpf"));
            paciente.setDataNascimento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataNascimento")).getTime()));
            paciente.setTelefone(request.getParameter("telefone"));
            paciente.setCelular(request.getParameter("celular"));            
            paciente.setConvenio(request.getParameter("convenio"));
            paciente.setEndereco(endereco);
            pessoaDAO.cadastrarPessoa(paciente);
            pacienteDAO.cadastrarPaciente(paciente);

            Login login = new Login();
            login.setEmail(request.getParameter("email"));
            login.setSenha(request.getParameter("senha"));
            login.setTipo(PessoasEnum.valueOf(request.getParameter("tipo")));
            login.setPessoa(paciente);
            paciente.setLogin(login);
            loginDAO.cadastrarLogin(login);

            /*if (pessoa.getTipo() ==  PacientesEnum.DENTISTA)*/
            listAll(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alterarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            Paciente paciente = new Paciente();
            paciente.setPacienteID(Integer.parseInt(request.getParameter("idPaciente")));
            paciente.setConvenio(request.getParameter("convenio"));
            paciente.setPessoa(pessoa);
            pacienteDAO.alterarPaciente(paciente);

            Login login = new Login();
            login.setEmail(request.getParameter("email"));
            login.setSenha(request.getParameter("senha"));
            login.setTipo(PessoasEnum.valueOf(request.getParameter("tipo")));
            login.setPessoa(pessoa);
            loginDAO.alterarLogin(login);

            listAll(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluirPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pessoaDAO.excluirPessoa(id);
        pacienteDAO.excluirPaciente(id);
        loginDAO.excluirLogin(id);
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("listarPacientes.jsp").forward(request, response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("atendente/listarPacientes.jsp").forward(request, response);
    }

    private void ListarPacientePorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = pacienteDAO.listarPorId(id);
        request.setAttribute("paciente", paciente);
        request.setAttribute("login", loginDAO.buscarPessoa(id));
        request.getRequestDispatcher("atendente/atualizarPacientes.jsp").forward(request, response);
    }

}