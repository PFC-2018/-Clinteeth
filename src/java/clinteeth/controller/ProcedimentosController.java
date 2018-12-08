
package clinteeth.controller;

import clinteeth.dao.ProcedimentoDAO;
import clinteeth.model.Procedimento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProcedimentosController", urlPatterns = {"/Procedimentos"})
public class ProcedimentosController extends HttpServlet {

    private static ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    Gson gson = new Gson();



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("btnAcionar")) {
            case "ListarTudo": {
                ListarTodosProcedimentos(request, response);
                break;
            }
            case "ListarPorID": {
                listarProcedimentoPorId(request, response);
                break;
            }
            case "ListarPorIDGson": {
                listarProcedimentoPorIdGson(request, response);
                break;
            }
            case "Excluir": {
                excluirProcedimento(request, response);
                break;
            }

        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                switch (request.getParameter("btnAcionar")) {
            case "Cadastrar": {
                cadastrarProcessamento(request, response);
                break;
            }

            case "Alterar": {
                alterarProcedimento(request, response);
                break;
            }
        }
    }

    private void ListarTodosProcedimentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             request.setAttribute("procedimentos", procedimentoDAO.ListarTodosProcedimentos());
             request.getRequestDispatcher("listarProcedimento.jsp").forward(request, response);
     
    }

    private void listarProcedimentoPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Procedimento procedimento = procedimentoDAO.listarProcedimentoPorId(id);
        request.setAttribute("procedimento", procedimento);
        request.getRequestDispatcher("atualizarProcedimento.jsp").forward(request, response);
 
    }
    
        private void listarProcedimentoPorIdGson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Procedimento procedimento = procedimentoDAO.listarProcedimentoPorIdGson(id);
        
                Gson gson = new GsonBuilder().serializeNulls().create();
                String procedimentosJson = gson.toJson(procedimento);
//        System.out.println(procedimento.getDescricao() +" - "+procedimento.getPreco());
        request.setAttribute("procedimentosJson", procedimentosJson);
            response.getWriter().print(procedimentosJson);
        //request.getRequestDispatcher("atualizarProcedimento.jsp").forward(request, response);
 
    }

    private void excluirProcedimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        procedimentoDAO.excluirProcedimento(id);
        request.setAttribute("procedimentos", procedimentoDAO.ListarTodosProcedimentos());
        request.getRequestDispatcher("listarProcedimento.jsp").forward(request, response);
    }

    private void alterarProcedimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Procedimento procedimento = new Procedimento();
        procedimento.setprocedimentoID(Integer.parseInt(request.getParameter("id")));
        procedimento.setDescricao(request.getParameter("descricao"));
        procedimento.setPreco(Double.parseDouble(request.getParameter("preco")));
        procedimentoDAO.alterarProcedimento(procedimento);
        
        ListarTodosProcedimentos(request, response);
        
       
    }

    private void cadastrarProcessamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        Procedimento procedimento = new Procedimento();
        procedimento.setDescricao(request.getParameter("descricao"));
        procedimento.setPreco(Double.parseDouble(request.getParameter("preco")));
        procedimentoDAO.cadastrarProcedimento(procedimento);
        ListarTodosProcedimentos(request, response);
    }

}
