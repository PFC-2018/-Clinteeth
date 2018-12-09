package clinteeth.controller;

import clinteeth.dao.DisponibilidadeDAO;
import clinteeth.dao.DentistaDAO;
import clinteeth.model.Disponibilidade;
import clinteeth.model.Dentista;
import clinteeth.model.DiasDaSemanasEnum;
import clinteeth.model.DisponibilidadeDentista;
import clinteeth.model.EstadosEnum;
import clinteeth.model.HorasEnum;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisponibilidadeController", urlPatterns = {"/Disponibilidade"})
public class DisponibilidadeController extends HttpServlet {

    private static DisponibilidadeDAO disponibilidadeDAO = new DisponibilidadeDAO();
    private static DentistaDAO dentistaDAO = new DentistaDAO();
    public EstadosEnum[] estados() {
        return EstadosEnum.values();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "Excluir": {
                excluirDisponibilidade(request, response);
                break;
            }
            case "listarDentistasCombobox": {
                listarDentistasCombobox(request, response);
                break;
            }
            case "Cadastrar": {
                listarComboboxCadastrar(request, response);
                break;
            }
            case "ListarTudo": {
                try {
                    ListarDiasDisponiveisPorDentista(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(DisponibilidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "ListarPorID": {
                listarAgendamentoID(request, response);
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
                cadastrarDisponibilidade(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(DisponibilidadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }
            case "Alterar": {
//                try {
//                    alterarDisponibilidade(request, response);
//                } catch (ParseException ex) {
//                    Logger.getLogger(DisponibilidadeController.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SQLException ex) {
//                Logger.getLogger(DisponibilidadeController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(DisponibilidadeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
                break;
            }
            case "ListarTudo": {
                try {
                    ListarDiasDisponiveisPorDentista(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(DisponibilidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

        }
    }

//    private void cadastrarDisponibilidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
//                
//        Dentista dentista = new Dentista();
//        dentista.setDentistaID(Integer.parseInt(request.getParameter("dentista")));
//        
////        Disponibilidade disponibilidade = new Disponibilidade();
////        disponibilidade.setDentista(dentista);
//        
//            String [] diasSemanas = (request.getParameterValues("datadisponivel"));
//            for (String dia : diasSemanas){
//                String [] horas = (request.getParameterValues("hora"));
//                for (String horario : horas){
//                    Disponibilidade disponibilidade = new Disponibilidade();
//                    disponibilidade.setDentista(dentista);
//                    disponibilidade.setDtdisponivel(DiasDaSemanasEnum.valueOf(dia));
//                    disponibilidade.setHora(HorasEnum.valueOf(horario));
//                    disponibilidadeDAO.cadastrarDisponibilidade(disponibilidade);
//                }
//                
//            }          
////        Disponibilidade verifica = disponibilidadeDAO.VerificarDataDisponivel(disponibilidade);
////        if (verifica == null) {
////            String dataOcupada = ("Disponibilidade ja cadastrada nesta data e horário");
////            request.setAttribute("dataOcupada", dataOcupada);
////            request.getRequestDispatcher("dentista/cadastrarDisponibilidade.jsp").forward(request, response);
////        } else {
////            disponibilidadeDAO.cadastrarDisponibilidade(disponibilidade);
////            listarDentistasCombobox(request, response);
////        }
//    }

    private void alterarDisponibilidade(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, ClassNotFoundException, ServletException, IOException {
//        Dentista dentista = new Dentista();
//        dentista.setDentistaID(Integer.parseInt(request.getParameter("dentista")));
//        Disponibilidade disponibilidade = new Disponibilidade();
//        disponibilidade.setDentista(dentista);
//        disponibilidade.setDtDisponivel(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataindisponivel")).getTime()));
//        disponibilidade.setHora(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(request.getParameter("hora")).getTime()));
//        
//        Disponibilidade verifica = disponibilidadeDAO.VerificarDataDisponivel(disponibilidade);
//        if (verifica == null) {
//            String dataOcupada = ("Disponibilidade ja cadastrada nesta data e horário");
//            request.setAttribute("dataOcupada", dataOcupada);
//            request.getRequestDispatcher("dentista/atualizarDisponibilidade.jsp").forward(request, response);
//        } else {
//            disponibilidadeDAO.alterarDisponibilidade(disponibilidade);
//            listarDentistasCombobox(request, response);
//        }
    }

    private void excluirDisponibilidade(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        disponibilidadeDAO.excluirDisponibilidade(id);

    }

    private void ListarDiasDisponiveisPorDentista(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        String idDentistaSelecionado = request.getParameter("dentista");
        List<Disponibilidade> disponibilidades;
        List<DisponibilidadeDentista> disponibilidadeDentistaList = new ArrayList<>();
        java.util.List<Dentista> dentistas = dentistaDAO.ListarTodosDentistas();
        if (idDentistaSelecionado == null) {
            disponibilidades = disponibilidadeDAO.ListarHorariosDisponiveis(0);
        } else {
            disponibilidades = disponibilidadeDAO.ListarHorariosDisponiveis(Integer.parseInt(idDentistaSelecionado));
        }
        disponibilidades
                .forEach(d -> {
                    DisponibilidadeDentista disponibilidadeDentista = new DisponibilidadeDentista();
                    disponibilidadeDentista.setDentista(d.getDentista());
                    Dentista dentista = d.getDentista();
                    disponibilidadeDentista.setDiasDisponiveis(disponibilidades
                            .stream()
                            .filter(dd -> dd.getDentista().getDentistaID() == dentista.getDentistaID())
                            .map(dd -> dd.getDtdisponivel().getDiasSemanas())
                            .distinct()
                            .collect(Collectors.toList()));
                    disponibilidadeDentista.setHorariosDisponiveis(disponibilidades
                            .stream()
                            .filter(dd -> dd.getDentista().getDentistaID() == dentista.getDentistaID())
                            .map(dd -> dd.getHora().getHoras())
                            .distinct()
                            .collect(Collectors.toList()));
                    if (!disponibilidadeDentistaList.contains(disponibilidadeDentista)) {
                        disponibilidadeDentistaList.add(disponibilidadeDentista);
                    }
                });
        request.setAttribute("dentista", dentistas);
        request.setAttribute("disponibilidadeDentistaList", disponibilidadeDentistaList);
        request.getRequestDispatcher("dentista/listarDiasDisponiveis.jsp").forward(request, response);
    }

    private void listarDentistasCombobox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("dentista/listarDiasDisponiveis.jsp").forward(request, response);
    }

    private void listarComboboxCadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("dentista/cadastrarDisponibilidade.jsp").forward(request, response);
    }

    private void listarAgendamentoID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Disponibilidade disponibilidade = disponibilidadeDAO.listarDisponibilidadePorId(id);
        request.setAttribute("disponibilidade", disponibilidade);
        request.setAttribute("dentista", dentistaDAO.ListarTodosDentistas());
        request.getRequestDispatcher("dentista/atualizarDisponibilidade.jsp").forward(request, response);
    }

    private void cadastrarDisponibilidade(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        
        Dentista dentista = new Dentista();
        dentista.setDentistaID(Integer.parseInt(request.getParameter("dentista")));
        
            String [] diasSemanas = (request.getParameterValues("datadisponivel"));
            for (String dia : diasSemanas){
                String [] horas = (request.getParameterValues("hora"));
                for (String horario : horas){
                    Disponibilidade disponibilidade = new Disponibilidade();
                    disponibilidade.setDentista(dentista);
                    disponibilidade.setDtdisponivel(DiasDaSemanasEnum.valueOf(dia));
                    disponibilidade.setHora(HorasEnum.valueOf(horario));
                    
                    disponibilidadeDAO.cadastrarDisponibilidade(disponibilidade);
                }
            }
            ListarDiasDisponiveisPorDentista(request, response);
    }



}
