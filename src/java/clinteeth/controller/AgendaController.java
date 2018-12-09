package clinteeth.controller;

import clinteeth.dao.AgendaDAO;
import clinteeth.dao.EnderecoDAO;
import clinteeth.dao.LoginDAO;
import clinteeth.dao.DentistaDAO;
import clinteeth.dao.DisponibilidadeDAO;
import clinteeth.dao.PacienteDAO;
import clinteeth.dao.PessoaDAO;
import clinteeth.model.Agendamento;
import clinteeth.model.Dentista;
import clinteeth.model.DentistaIndisponivelException;
import clinteeth.model.Disponibilidade;
import clinteeth.model.Paciente;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AgendaController", urlPatterns = {"/Agenda"})
public class AgendaController extends HttpServlet {

    private static EnderecoDAO enderecoDAO = new EnderecoDAO();
    private static LoginDAO loginDAO = new LoginDAO();
    private static DentistaDAO dentistaDAO = new DentistaDAO();
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static PessoaDAO pessoaDAO = new PessoaDAO();
    private static AgendaDAO agendaDAO = new AgendaDAO();
    private static DisponibilidadeDAO indisponibilidadeDAO = new DisponibilidadeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "listarDentistasCombobox": {
                listarDentistasCombobox(request, response);
                break;
            }
            case "Excluir": {
                excluirAgendamento(request, response);
                break;
            }
            case "Cadastrar": {
                listarComboboxCadastrar(request, response);
                break;
            }
            case "ListarTudo": {
                ListarAgendamentoPorDentista(request, response);
                break;
            }
            case "ListarPorID": {
                listarAgendamentoID(request, response);
                break;
            }
            case "Cancelar": {
                cancelarAgendamento(request, response);
                break;
            }
            case "RelatorioAgendamento": {
            try {
                relatorioAgendamento(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
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
                    cadastrarAgendamento(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "Alterar": {
                try {
                    alterarAgendamento(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "ListarTudo": {
                ListarAgendamentoPorDentista(request, response);
                break;
            }
            case "RelatorioAgendamento": {
            try {
                relatorioAgendamento(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }
        }
    }

    private void cadastrarAgendamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            checkDisponibilidade(Integer.parseInt(request.getParameter("dentista")), request.getParameter("dtagendamento"), request.getParameter("hora"));
            Dentista dentista = new Dentista();
            dentista.setDentistaID(Integer.parseInt(request.getParameter("dentista")));

            Paciente paciente = new Paciente();
            paciente.setPacienteID(Integer.parseInt(request.getParameter("paciente")));

            Agendamento agendamento = new Agendamento();
            agendamento.setDentista(dentista);
            agendamento.setPaciente(paciente);
            agendamento.setDtagendamento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtagendamento")).getTime()));
            agendamento.setHragendamento(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(request.getParameter("hora")).getTime()));
            agendamento.setTitulo(request.getParameter("titulo"));
            agendamento.setObservacao(request.getParameter("obsagendamento"));
            agendamento.setSituacao("PENDENTE");

            Disponibilidade verificaIndisponibilidade = indisponibilidadeDAO.VerificarData(agendamento);
            if (verificaIndisponibilidade == null) {
                String dataIndisponivel = ("O dentista nao estara disponivel nesta data e horário");
                request.setAttribute("dataIndisponivel", dataIndisponivel);
                request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
                request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
                request.getRequestDispatcher("/cadastrarAgendamento.jsp").forward(request, response);
            } else {
                Agendamento verificaAgenda = agendaDAO.VerificarAgendamento(agendamento);
                if (verificaAgenda != null) {
                    agendaDAO.cadastrarAgendamento(agendamento);
                    listarDentistasCombobox(request, response);
                } else {
                    String dataIndisponivel = ("Ja existe um Agendamento para este dentista nesta data e horário");
                    request.setAttribute("dataIndisponivel", dataIndisponivel);
                    request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
                    request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
                    request.getRequestDispatcher("/cadastrarAgendamento.jsp").forward(request, response);
                }
            }
        }catch (DentistaIndisponivelException dentistaIndisponivelException) {
            String msgErro = dentistaIndisponivelException.getMsgErro();
            request.setAttribute("msgErro", msgErro);
            request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
            request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
            request.getRequestDispatcher("/cadastrarAgendamento.jsp").forward(request, response);            
        }
        catch (ParseException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void checkDisponibilidade(int idDentista, String dataConsulta, String horarioConsulta) throws ParseException {
        Dentista dentista = dentistaDAO.listarPorId(idDentista);
        LocalDate dtConsulta = LocalDate.parse(dataConsulta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime hrConsulta = LocalTime.parse(horarioConsulta, DateTimeFormatter.ofPattern("HH:mm"));
        java.util.List<Disponibilidade> disponibilidadeDentista = indisponibilidadeDAO.ListarHorariosDisponiveis(dentista.getDentistaID());
        java.util.List<String> diasDisponiveis = disponibilidadeDentista
                .stream()
                .map(dd -> dd.getDtdisponivel().getDiasSemanas())
                .distinct()
                .collect(Collectors.toList());
        java.util.List horariosDisponiveis = disponibilidadeDentista
                .stream()
                .map(dd -> dd.getHora().getHoras())
                .distinct()
                .collect(Collectors.toList());
        boolean atendeNoDia = true;
        switch (dtConsulta.getDayOfWeek()) {
            case SUNDAY: {
                atendeNoDia = diasDisponiveis.contains("DOMINGO");
                break;
            }            
            case MONDAY: {
                atendeNoDia = diasDisponiveis.contains("SEGUNDA-FEIRA");
                break;
            }
            case TUESDAY: {
                atendeNoDia = diasDisponiveis.contains("TERÇA-FEIRA");
                break;
            }
            case WEDNESDAY: {
                atendeNoDia = diasDisponiveis.contains("QUARTA-FEIRA");
                break;
            }            
            case THURSDAY: {
                atendeNoDia = diasDisponiveis.contains("QUINTA-FEIRA");
                break;
            }             
            case FRIDAY: {
                atendeNoDia = diasDisponiveis.contains("SEXTA-FEIRA");
                break;
            }     
            case SATURDAY: {
                atendeNoDia = diasDisponiveis.contains("SÁBADO");
                break;
            }
        }
        boolean atendeHora = horariosDisponiveis.contains(hrConsulta.format(DateTimeFormatter.ofPattern("HH:mm")));
        if (!atendeNoDia) {
            throw new DentistaIndisponivelException("DIA");
        }
        if (!atendeHora) {
            throw new DentistaIndisponivelException("HORARIO");
        }
    }

    private void alterarAgendamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            Dentista dentista = new Dentista();
            dentista.setDentistaID(Integer.parseInt(request.getParameter("dentista")));

            Paciente paciente = new Paciente();
            paciente.setPacienteID(Integer.parseInt(request.getParameter("paciente")));

            Agendamento agendamento = new Agendamento();
            agendamento.setAgendamentoID(Integer.parseInt(request.getParameter("idagendamento")));
            agendamento.setDentista(dentista);
            agendamento.setPaciente(paciente);
            agendamento.setDtagendamento(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtagendamento")).getTime()));
            agendamento.setHragendamento(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(request.getParameter("hora")).getTime()));
            agendamento.setTitulo(request.getParameter("titulo"));
            agendamento.setObservacao(request.getParameter("obsagendamento"));

            Disponibilidade verificaIndisponibilidade = indisponibilidadeDAO.VerificarData(agendamento);
            if (verificaIndisponibilidade == null) {
                String dataIndisponivel = ("O dentista nao estara disponivel nesta data e horário");
                request.setAttribute("dataIndisponivel", dataIndisponivel);
                request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
                request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
                request.getRequestDispatcher("/atualizarAgendamento.jsp").forward(request, response);
            } else {
                agendaDAO.alterarAgendamento(agendamento);
                listarDentistasCombobox(request, response);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluirAgendamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        agendaDAO.excluirAgendamento(id);
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/Agendamento.jsp").forward(request, response);
    }

    private void cancelarAgendamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idmotcanc = Integer.parseInt(request.getParameter("motivo"));
        agendaDAO.cancelarAgendamento(id, idmotcanc);
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/Agendamento.jsp").forward(request, response);
    }

    private void listarDentistasCombobox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/Agendamento.jsp").forward(request, response);
    }

    private void listarPacientesCombobox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("/Agendamento.jsp").forward(request, response);
    }

    private void listarComboboxCadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("/cadastrarAgendamento.jsp").forward(request, response);
    }

    private void ListarAgendamentoPorDentista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idDentistaSelecionado = request.getParameter("dentista");
        int id;
        if (idDentistaSelecionado == null) {
            id = 0;
        } else {
            id = Integer.parseInt(idDentistaSelecionado);
        }
        request.setAttribute("agenda", agendaDAO.ListarTodosAgendamentos(id));
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("/Agendamento.jsp").forward(request, response);
    }

    private void listarAgendamentoID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agendamento agendamento = agendaDAO.listarAgendamentoID(id);
        request.setAttribute("agenda", agendamento);
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.setAttribute("paciente", pacienteDAO.ListarTodosPacientes());
        request.getRequestDispatcher("/atualizarAgendamento.jsp").forward(request, response);
    }

    private void relatorioAgendamento(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicialAgenda = null;
        java.util.Date dataFinalAgenda = null;
        int id = Integer.parseInt(request.getParameter("dentista"));
        
        if (request.getParameter("dtpagamentoini") != null){
            dataInicialAgenda =  formato.parse(request.getParameter("dtpagamentoini"));
        }
        if (request.getParameter("dtpagamentofim") != null) {
            dataFinalAgenda =  formato.parse(request.getParameter("dtpagamentofim"));
        }
           
        if (dataInicialAgenda != null && dataFinalAgenda != null ) {
            request.setAttribute("agenda", agendaDAO.RelatorioAgendamento(
                new java.sql.Date(dataInicialAgenda.getTime()), 
                new java.sql.Date(dataFinalAgenda.getTime()),id));
        }
    
request.getRequestDispatcher("/relatorioAgendamento.jsp").forward(request, response);
}
}
