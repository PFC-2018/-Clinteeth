package clinteeth.controller;

import clinteeth.dao.DentistaDAO;
import clinteeth.dao.PacienteDAO;
import clinteeth.dao.PagamentoDAO;
import clinteeth.dao.AtendimentoDAO;
import clinteeth.model.Atendimento;
import clinteeth.model.Dentista;
import clinteeth.model.Paciente;
import clinteeth.model.Pagamento;
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
import javax.swing.text.html.HTML;

@WebServlet(name = "PagamentoController", urlPatterns = {"/Pagamento"})
public class PagamentoController extends HttpServlet {

    private static AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
    private static DentistaDAO dentistaDAO = new DentistaDAO();
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static PagamentoDAO pagamentoDAO = new PagamentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "listarPacientesCombobox": {
                listarPacientesCombobox(request, response);
                break;
            }
            case "ListarTudo": {
                ListarPagamentoPorPacientes(request, response);
                break;
            }
            case "ListarPorIdPagamento": {
                listarPagamentoID(request, response);
                break;
            }
            case "ListarPorIdAtendimento": {
                listarAtendimentoID(request, response);
                break;
            }
            case "RelatorioEntreDatas": {
            try {
                relatorioEntreDatas(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }

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
                    cadastrarPagamento(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "Alterar": {
                try {
                    alterarPagamento(request, response);
                    break;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "ListarTudo": {
                ListarPagamentoPorPacientes(request, response);
                break;
            }

            case "RelatorioEntreDatas": {
            try {
                relatorioEntreDatas(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }

                break;
            }
        }
    }

    private void cadastrarPagamento(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {

        Atendimento atendimento = new Atendimento();
        atendimento.setAtendimentoID(Integer.parseInt(request.getParameter("id")));

        Dentista dentista = new Dentista();
        dentista.setDentistaID(Integer.parseInt(request.getParameter("idDentista")));

        Paciente paciente = new Paciente();
        paciente.setPacienteID(Integer.parseInt(request.getParameter("idPaciente")));

        Pagamento pagamento = new Pagamento();
        pagamento.setAtendimento(atendimento);
        pagamento.setDentista(dentista);
        pagamento.setPaciente(paciente);
        pagamento.setDtpagamento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtpagamento")).getTime()));
        pagamento.setValor(Double.parseDouble(request.getParameter("valor")));
        pagamento.setValorpago(Double.parseDouble(request.getParameter("valorpago")));
        if (pagamento.getValorpago() == pagamento.getValor()) {
            pagamento.setSituacao("PAGO");
            pagamentoDAO.cadastrarPagamento(pagamento);
            atendimento.setSituacao("FINALIZADO");
            atendimento.setStPagamento("PAGO");
            atendimentoDAO.atualizarSituacaoAtendimento(atendimento);
            listarPacientesCombobox(request, response);
        } else if (pagamento.getValorpago() < pagamento.getValor()) {
            pagamento.setSituacao("PAGO PARCIAL");
            pagamentoDAO.cadastrarPagamento(pagamento);
            atendimento.setSituacao("FINALIZADO");
            atendimento.setStPagamento("PAGO PARCIAL");
            atendimentoDAO.atualizarSituacaoAtendimento(atendimento);
            listarPacientesCombobox(request, response);
        }
        if (pagamento.getValorpago() > pagamento.getValor()) {
            String verificaValor = ("O valor pago não pode ser maior que o valor do atendimento");
            request.setAttribute("verificaValor", verificaValor);
            listarAtendimentoID(request, response);
        }
    }

    private void alterarPagamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, ParseException {

        Pagamento pagamento = new Pagamento();
        pagamento.setPagamentoID(Integer.parseInt(request.getParameter("idPagamento")));
        pagamento.setDtpagamento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtpagamento")).getTime()));
        pagamento.setValor(Double.parseDouble(request.getParameter("valor")));
        pagamento.setValorpago(Double.parseDouble(request.getParameter("valorPago")));
        if (pagamento.getValorpago() == pagamento.getValor()) {
            pagamento.setSituacao("PAGO");
            pagamentoDAO.alterarPagamento(pagamento);
            Atendimento atendimento = new Atendimento();
            atendimento.setSituacao("FINALIZADO");
            atendimento.setStPagamento("PAGO");
            atendimentoDAO.atualizarSituacaoAtendimento(atendimento);
            listarPacientesCombobox(request, response);
        } else if (pagamento.getValorpago() < pagamento.getValor()) {
            pagamento.setSituacao("PAGO PARCIAL");
            pagamentoDAO.alterarPagamento(pagamento);
            Atendimento atendimento = new Atendimento();
            atendimento.setSituacao("FINALIZADO");
            atendimento.setStPagamento("PAGO PARCIAL");
            atendimentoDAO.atualizarSituacaoAtendimento(atendimento);
            listarPacientesCombobox(request, response);
        }
        if (pagamento.getValorpago() > pagamento.getValor()) {
            String verificaValor = ("O valor pago não pode ser maior que o valor do atendimento");
            request.setAttribute("verificaValor", verificaValor);
            listarPagamentoID(request, response);
        }
    }

    private void listarPacientesCombobox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("/listarPagamento.jsp").forward(request, response);
        request.getRequestDispatcher("/relatorioPagamentosEntreDatas.jsp").forward(request, response);
        
    }

    private void ListarPagamentoPorPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("paciente"));
        request.setAttribute("pagamento", pagamentoDAO.ListarTodosPagamentos(id));
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("/listarPagamento.jsp").forward(request, response);
    }

    private void listarAtendimentoID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Atendimento atendimento = atendimentoDAO.listarAtendimentoID(id);
        request.setAttribute("atendimento", atendimento);
        request.getRequestDispatcher("/cadastrarPagamento.jsp").forward(request, response);
    }

    private void listarPagamentoID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pagamento pagamento = pagamentoDAO.listarPagamentoID(id);
        request.setAttribute("pagamento", pagamento);
        request.getRequestDispatcher("/atualizarPagamento.jsp").forward(request, response);
    }

    private void relatorioEntreDatas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicio = null;
        java.util.Date dataFinal = null;
        int id = Integer.parseInt(request.getParameter("paciente"));
        
        if (request.getParameter("dtpagamentoi") != null){
            dataInicio =  formato.parse(request.getParameter("dtpagamentoi"));
        }
        if (request.getParameter("dtpagamentof") != null) {
            dataFinal =  formato.parse(request.getParameter("dtpagamentof"));
        }
           
        if (dataInicio != null && dataFinal != null ) {
            request.setAttribute("pagamento", pagamentoDAO.ListarDatasPagamentosPago(
                new java.sql.Date(dataInicio.getTime()), 
                new java.sql.Date(dataFinal.getTime()),id));
        }
        
    
request.getRequestDispatcher("/relatorioPagamentosEntreDatas.jsp").forward(request, response);
}
        
}
