package clinteeth.dao;

import clinteeth.model.Agendamento;
import clinteeth.model.Dentista;
import clinteeth.model.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendaDAO {

    Connection connection;

    public void cadastrarAgendamento(Agendamento agendamento) {
        String sqlCadastrarAgendamento = "INSERT INTO tbl_agendamento (iddentista, idpaciente, dtagendamento, hora, titulo, obsagendamento, situacao) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarAgendamento);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setInt(1, agendamento.getDentista().getDentistaID());
            ps.setInt(2, agendamento.getPaciente().getPacienteID());
            ps.setDate(3, new java.sql.Date(agendamento.getDtagendamento().getTime()));
            ps.setTime(4, new java.sql.Time(agendamento.getHragendamento().getTime()));
            ps.setString(5, agendamento.getTitulo());
            ps.setString(6, agendamento.getObservacao());
            ps.setString(7, agendamento.getSituacao());
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

    public void alterarAgendamento(Agendamento agendamento) {
        String sqlAlterarAgendamento = "UPDATE tbl_agendamento SET iddentista=?, idpaciente=?, dtagendamento=?, hora=?, titulo=?, obsagendamento=? WHERE idagendamento=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarAgendamento);
            ps.setInt(1, agendamento.getDentista().getDentistaID());
            ps.setInt(2, agendamento.getPaciente().getPacienteID());
            ps.setDate(3, new java.sql.Date(agendamento.getDtagendamento().getTime()));//Converte data para formato do banco
            ps.setTime(4, new java.sql.Time(agendamento.getHragendamento().getTime()));
            ps.setString(5, agendamento.getTitulo());
            ps.setString(6, agendamento.getObservacao());
            ps.setInt(7, agendamento.getAgendamentoID());
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

    public void excluirAgendamento(int id) {
        String sqlDeletarAgendamento = "UPDATE tbl_agendamento SET st='FALSE',situacao='EXCLUIDO' WHERE idagendamento = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarAgendamento);
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

    public void cancelarAgendamento(int id, int idmotcanc) {
        String sqlCancelarAgendamento = "UPDATE tbl_agendamento SET situacao='CANCELADO', idmotcanc = ? WHERE idagendamento = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCancelarAgendamento);
            ps.setInt(1, idmotcanc);
            ps.setInt(2, id);
            int linhasAtualizadas = ps.executeUpdate();//Para saber se 1 linha foi inserida
            if (linhasAtualizadas == 1) {
                System.out.println("1 linha foi cancelada com sucesso.  ");
            } else {
                System.out.println("Erro ao cancelar. ");
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
    
    public ArrayList<Agendamento> ListarTodosAgendamentos(int id) {
        String sqlListarAgendamento = "SELECT idagendamento, iddentista, idpaciente, dtagendamento, hora, titulo, obsagendamento, situacao FROM tbl_agendamento WHERE ST='TRUE' AND iddentista = ? AND situacao ='PENDENTE' ORDER BY dtagendamento, hora;";
        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarAgendamento);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Agendamento agendamento = new Agendamento(rs.getInt("idagendamento"),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDate("dtagendamento"),
                        rs.getTime("hora"),
                        rs.getString("titulo"),
                        rs.getString("obsagendamento"),
                        rs.getString("situacao"));

                agendamentos.add(agendamento);//A cada linha cria e adc. um novo obj a lista
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
        return agendamentos;
    }

    public Agendamento listarAgendamentoID(int id) {
        String sqlListarPorID = "SELECT idagendamento, iddentista, idpaciente, dtagendamento, hora, titulo, obsagendamento, situacao FROM tbl_agendamento WHERE ST='TRUE' AND idagendamento = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            Agendamento agendamento = new Agendamento();
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                agendamento = new Agendamento(rs.getInt("idagendamento"),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDate("dtagendamento"),
                        rs.getTime("hora"),
                        rs.getString("titulo"),
                        rs.getString("obsagendamento"),
                        rs.getString("situacao"));
            }
            return agendamento;
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
    
    
    public Agendamento VerificarAgendamento(Agendamento agendamento) throws SQLException, ClassNotFoundException {
        String sqlVerificarData = "SELECT iddentista, dtagendamento, hora FROM tbl_agendamento WHERE dtagendamento = ? AND hora = ? AND iddentista = ? AND ST='TRUE'";
        Connection con = FabricaConexao.conexao();
        try {
            PreparedStatement ps = con.prepareStatement(sqlVerificarData);
            ps.setDate(1, new java.sql.Date(agendamento.getDtagendamento().getTime()));
            ps.setTime(2, new java.sql.Time(agendamento.getHragendamento().getTime()));
            ps.setInt(3, agendamento.getDentista().getDentistaID());            
            ResultSet rs = ps.executeQuery();
            Dentista dentista = new Dentista();
            Paciente paciente = new Paciente();
            if (rs.next()) {
                dentista.setDentistaID(rs.getInt("iddentista"));                
                        agendamento.setDtagendamento(rs.getDate("dtagendamento"));
                        agendamento.setHragendamento(rs.getTime("hora"));
                        agendamento.setDentista(dentista);
                        agendamento = null;
                System.out.println("AGENDAMENTO JA EXISTENTE");
            } else {
                System.out.println("AGENDAMENTO NAO EXISTE");
            }
            return agendamento;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public void atualizarSituacaoAgendamento(Agendamento agendamento) {
        String sqlDeletarAgendamento = "UPDATE tbl_agendamento SET situacao=? WHERE idagendamento = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarAgendamento);
            ps.setString(1, agendamento.getSituacao());
            ps.setInt(2, agendamento.getAgendamentoID());
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
    
    public ArrayList<Agendamento> RelatorioAgendamento(Date dataInicialAgenda, Date dataFinalAgenda, int id) {
        String sqlListarRelatoriosAgendamento = "SELECT idagendamento, iddentista, idpaciente, dtagendamento, hora, titulo, obsagendamento, situacao FROM tbl_agendamento WHERE dtagendamento between ? and ? and iddentista=? and ST='TRUE'  and situacao ='FINALIZADO' ORDER BY dtagendamento, hora;";
        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarRelatoriosAgendamento);
            ps.setDate(1, (java.sql.Date) dataInicialAgenda);
            ps.setDate(2, (java.sql.Date) dataFinalAgenda); 
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Agendamento agendamento = new Agendamento(
                        rs.getInt("idagendamento"),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDate("dtagendamento"),
                        rs.getTime("hora"),
                        rs.getString("titulo"),
                        rs.getString("obsagendamento"),
                        rs.getString("situacao"));

                agendamentos.add(agendamento);//A cada linha cria e adc. um novo obj a lista
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
        return agendamentos;
    }    
}
