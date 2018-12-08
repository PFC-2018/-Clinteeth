package clinteeth.dao;

import clinteeth.model.Atendimento;
import clinteeth.model.Procedimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AtendimentoDAO {

    Connection connection;

    public void cadastrarAtendimento(Atendimento atendimento) {
        String sqlCadastrarAtendimento = "INSERT INTO tbl_atendimento (idagendamento, iddentista, idpaciente, total, desconto, observacao, situacao, stpagamento) VALUES (?,?,?,?,?,?,?,?);";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarAtendimento, PreparedStatement.RETURN_GENERATED_KEYS);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setInt(1, atendimento.getAgendamento().getAgendamentoID());
            ps.setInt(2, atendimento.getDentista().getDentistaID());
            ps.setInt(3, atendimento.getPaciente().getPacienteID());
            ps.setDouble(4, atendimento.getTotal());
            ps.setDouble(5, atendimento.getDesconto());
            ps.setString(6, atendimento.getObservacoes());
            ps.setString(7, atendimento.getSituacao());
            ps.setString(8, atendimento.getStPagamento());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //pegar chave fk do autoincremento
            while (rs.next()) {
                atendimento.setAtendimentoID(rs.getInt("id"));
            }

            for (Procedimento procedimento : atendimento.getProcedimentos()) {
                cadastrarProcedimentoAtendimento(procedimento, atendimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println(exception + "Erro ao fechar conexão. ");
            }
        }
    }

    private void cadastrarProcedimentoAtendimento(Procedimento procedimento, Atendimento atendimento) {
        String sqlCadastrarProcedimentoAtendimento = "INSERT INTO tbl_Procedimento_Atendimento (idProcedimento,idAtendimento) VALUES (?,?)";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarProcedimentoAtendimento);

            ps.setInt(1, procedimento.getprocedimentoID());
            ps.setInt(2, atendimento.getAtendimentoID());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println(exception + "Erro ao fechar conexão. ");
            }
        }
    }

    public ArrayList<Atendimento> ListarTodosAtendimentos(int id) {
        String sqlListarAtendimento = "SELECT id,idAgendamento,idDentista,idPaciente,total,desconto,observacao,situacao,stpagamento FROM tbl_atendimento WHERE idDentista=? AND stpagamento='PENDENTE';";
        ArrayList<Atendimento> atendimentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarAtendimento);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Atendimento atendimento = new Atendimento(
                        rs.getInt("id"),
                        new AgendaDAO().listarAgendamentoID(rs.getInt("idagendamento")),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDouble("total"),
                        rs.getDouble("desconto"),
                        rs.getString("observacao"),
                        rs.getString("situacao"),
                        rs.getString("stpagamento"));

                atendimentos.add(atendimento);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão." + exception.getMessage());
            }
        }
        return atendimentos;
    }

    public void alterarAtendimento(Atendimento atendimento) {
        String sqlAlterarAtendimento = "UPDATE tbl_atendimento SET total=?, desconto=?, observacoes=? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarAtendimento);
            ps.setDouble(1, atendimento.getTotal());
            ps.setDouble(1, atendimento.getDesconto());
            ps.setString(3, atendimento.getObservacoes());
            ps.setInt(4, atendimento.getAtendimentoID());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.out.println("ERRO AO ATUALIZAR DENTISTA");
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }

    public void excluirAtendimento(int id) {
        String sqlDeletarAtendimento = "UPDATE tbl_atendimento SET st='FALSE',situacao='EXCLUIDOS' WHERE id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarAtendimento);
            ps.setInt(1, id);
            int linhasAtualizadas = ps.executeUpdate();//Para saber se 1 linha foi inserida
            if (linhasAtualizadas == 1) {
                System.out.println("1 linha foi excluida com sucesso.  ");
            } else {
                System.out.println("Erro ao excluir. ");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }



    public Atendimento listarAtendimentoID(int id) {
        String sqlListarPorID = "SELECT id, idagendamento, iddentista, idpaciente, total, desconto,observacao FROM tbl_atendimento where id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            Atendimento atendimento = new Atendimento();
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                atendimento = new Atendimento(
                        rs.getInt("id"),
                        new AgendaDAO().listarAgendamentoID(rs.getInt("idagendamento")),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDouble("total"),
                        rs.getDouble("desconto"),
                        rs.getString("observacao"));
                ProcedimentoDAO procedimentos = new ProcedimentoDAO();
                atendimento.setProcedimentos(procedimentos.ListarTodosProcedimentosPorIDdoAtendimento(id));
                        
            }

            return atendimento;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
        public ArrayList<Atendimento> RelatorioTodosAtendimentos(int id) {
        String sqlListarAtendimento = "SELECT ID,IDAGENDAMENTO,IDDENTISTA,IDPACIENTE,TOTAL,DESCONTO,OBSERVACAO FROM TBL_ATENDIMENTO WHERE nome like = ?";
        ArrayList<Atendimento> atendimentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarAtendimento);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Atendimento atendimento = new Atendimento(rs.getInt("id"),
                        new AgendaDAO().listarAgendamentoID(rs.getInt("idagendamento")),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDouble("total"),
                        rs.getDouble("desconto"),
                        rs.getString("observacao"));

                atendimentos.add(atendimento);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão." + exception.getMessage());
            }
        }
        return atendimentos;
    }

    public void atualizarSituacaoAtendimento(Atendimento atendimento) {
        String sqlAgendamento = "UPDATE tbl_atendimento SET situacao=?, stpagamento=? WHERE id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAgendamento);
            ps.setString(1, atendimento.getSituacao());
            ps.setString(2, atendimento.getStPagamento());
            ps.setInt(3, atendimento.getAtendimentoID());
            int linhasAtualizadas = ps.executeUpdate();//Para saber se 1 linha foi inserida
            if (linhasAtualizadas == 1) {
                System.out.println("1 linha foi atualizada com sucesso.  ");
            } else {
                System.out.println("Erro ao atualizar. ");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }    
    }

}
