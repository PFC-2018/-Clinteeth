    package clinteeth.controller;

import clinteeth.dao.AgendaDAO;
import clinteeth.dao.DentistaDAO;
import clinteeth.dao.PacienteDAO;
import clinteeth.dao.AtendimentoDAO;
import clinteeth.dao.PagamentoDAO;
import clinteeth.dao.ProcedimentoDAO;
import clinteeth.model.Agendamento;
import clinteeth.model.Atendimento;
import clinteeth.model.Dentista;
import clinteeth.model.Paciente;
import clinteeth.model.Procedimento;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtendimentoController", urlPatterns = {"/Atendimento"})
public class AtendimentoController extends HttpServlet {

    private static DentistaDAO dentistaDAO = new DentistaDAO();
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static AgendaDAO agendaDAO = new AgendaDAO();
    private static ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private static AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
    private static PagamentoDAO pagamentoDAO = new PagamentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "listarDentistasCombobox": {
                listarDentistasCombobox(request, response);
                break;
            }
            case "Excluir": {
            excluirAtendimento(request, response);
                break;
            }
            case "ListarTudo": {
                ListarAtendimentoPorDentista(request, response);
                break;
            }
            case "ListarPorIdAgendamento": {
                listarAgendamentoID(request, response);
                break;
            }
            case "ListarPorId": {
                listarAtendimentoID(request, response);
                break;
            }
            case "Relatorio": {
                RelatorioPessoa(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "Cadastrar": {
                cadastrarAtendimento(request, response);
                break;
            }
            case "Alterar": {
                alterarAtendimento(request, response);
                break;
            }
            case "ListarTudo": {
                ListarAtendimentoPorDentista(request, response);
                break;
            }
            case "Relatorio": {
                RelatorioPessoa(request, response);
                break;
            }
        }
    }

    private void cadastrarAtendimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Agendamento agendamento = new Agendamento();
        agendamento.setAgendamentoID(Integer.parseInt(request.getParameter("idAgendamento")));
        agendamento.setSituacao("FINALIZADO");

        Dentista dentista = new Dentista();
        dentista.setDentistaID(Integer.parseInt(request.getParameter("idDentista")));

        Paciente paciente = new Paciente();
        paciente.setPacienteID(Integer.parseInt(request.getParameter("idPaciente")));

        String[] procedimentosID = request.getParameterValues("servicos");
        ArrayList<Procedimento> procedimentos = new ArrayList();
        procedimentoDAO = new ProcedimentoDAO();
        for (String idproc : procedimentosID) {
            procedimentos.add(procedimentoDAO.listarProcedimentoPorId(Integer.parseInt(idproc)));
        }

        Atendimento atendimento = new Atendimento();
        atendimento.setAgendamento(agendamento);
        atendimento.setDentista(dentista);
        atendimento.setPaciente(paciente);
        atendimento.setDesconto(Double.parseDouble(request.getParameter("desconto")));
//        atendimento.setTotal(Double.parseDouble(request.getParameter("total")));
        atendimento.setProcedimentos(procedimentos);
        atendimento.CalcularTotal();
        atendimento.setObservacoes(request.getParameter("observacoes"));
        atendimento.setSituacao("FINALIZADO");
        atendimento.setStPagamento("PENDENTE");
        atendimentoDAO.cadastrarAtendimento(atendimento);
        agendaDAO.atualizarSituacaoAgendamento(agendamento);
        atendimentoDAO.atualizarSituacaoAtendimento(atendimento);
        listarDentistasCombobox(request, response);
    }


    private void excluirAtendimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idatendimento"));
        atendimentoDAO.excluirAtendimento(id);
        Agendamento agendamento = new Agendamento();
        agendamento.setAgendamentoID(Integer.parseInt(request.getParameter("idagendamento")));
        agendamento.setSituacao("PENDENTE");
        agendaDAO.atualizarSituacaoAgendamento(agendamento);
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/listarAtendimento.jsp").forward(request, response);
    }

    private void listarDentistasCombobox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/listarAtendimento.jsp").forward(request, response);
    }


    private void ListarAtendimentoPorDentista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("dentista"));
        request.setAttribute("atendimento", atendimentoDAO.ListarTodosAtendimentos(id));
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/listarAtendimento.jsp").forward(request, response);
    }

    private void listarAtendimentoID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Atendimento atendimento = atendimentoDAO.listarAtendimentoID(id);
        ArrayList<Procedimento> procedimentos = procedimentoDAO.ListarTodosProcedimentosPorIDdoAtendimento(id);
        request.setAttribute("atendimento", atendimento);
        request.setAttribute("procedimentos", procedimentos);
        request.getRequestDispatcher("atualizarAtendimento.jsp").forward(request, response);
    }
//

    private void listarAgendamentoID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agendamento agendamento = agendaDAO.listarAgendamentoID(id);
        request.setAttribute("agenda", agendamento);
        request.setAttribute("procedimento", procedimentoDAO.ListarTodosProcedimentos());
        request.getRequestDispatcher("/cadastrarAtendimento.jsp").forward(request, response);

    }

    private void alterarAtendimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Atendimento atendimento = new Atendimento();
        atendimento.setAtendimentoID(Integer.parseInt(request.getParameter("idatendimento")));
        atendimento.setTotal(Double.parseDouble(request.getParameter("total")));
        atendimento.setDesconto(Double.parseDouble(request.getParameter("desconto")));
        atendimento.setObservacoes("observacoes");
        atendimentoDAO.alterarAtendimento(atendimento);
        listarDentistasCombobox(request, response);

    }

    private void RelatorioPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("paciente"));
        request.setAttribute("atendimento", atendimentoDAO.ListarTodosAtendimentos(id));
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("/relatorioAtendimento.jsp").forward(request, response);
    }

}
