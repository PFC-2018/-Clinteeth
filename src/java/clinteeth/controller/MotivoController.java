package clinteeth.controller;

import clinteeth.dao.MotivoDAO;
import clinteeth.model.Motivo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MotivoController", urlPatterns = {"/Motivo"})
public class MotivoController extends HttpServlet {

    private static MotivoDAO motivoDAO = new MotivoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "ListarTudo": {
                listarMotivos(request, response);
                break;
            }
            case "ListarCombobox": {
                listarMotivosCombobox(request, response);
                break;
            }
            case "ListarPorID": {
                listarMotivosPorID(request, response);
                break;
            }
            case "Excluir": {
                excluirMotivo(request, response);
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("btnAcionar")) {
            case "Cadastrar": {
                cadastrarMotivo(request, response);
                break;
            }

            case "Alterar": {
                alterarMotivo(request, response);
                break;
            }

        }
    }

    private void cadastrarMotivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Motivo motivo = new Motivo();
        motivo.setDescricao(request.getParameter("descricao"));
        motivoDAO.cadastrarMotivo(motivo);
        listarMotivos(request, response);
    }

    private void alterarMotivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Motivo motivo = new Motivo();
        motivo.setId(Integer.parseInt(request.getParameter("idmotivo")));
        motivo.setDescricao(request.getParameter("descricao"));
        motivoDAO.alterarMotivo(motivo);
        listarMotivos(request, response);
    }

    private void excluirMotivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        motivoDAO.excluirMotivo(id);
        request.setAttribute("motivos", motivoDAO.listarMotivos());
        request.getRequestDispatcher("/listarMotivo.jsp").forward(request, response);
    }

    private void listarMotivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("motivos", motivoDAO.listarMotivos());
        request.getRequestDispatcher("/listarMotivo.jsp").forward(request, response);
    }
    
    private void listarMotivosCombobox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("motivo", motivoDAO.listarMotivos());        
    }

    private void listarMotivosPorID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Motivo motivo = motivoDAO.listarMotivoPorId(id);
        request.setAttribute("motivo", motivo);        
        request.getRequestDispatcher("/atualizarMotivo.jsp").forward(request, response);
    }

}
