package clinteeth.dao;

import clinteeth.model.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class PagamentoDAO {

    Connection connection;

    public void cadastrarPagamento(Pagamento pagamento) {
        String sqlCadastrarPagamento = "INSERT INTO tbl_pagamento (idatendimento, iddentista, idpaciente, dtpagamento, valor, valorpago, situacao) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarPagamento);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setInt(1, pagamento.getAtendimento().getAtendimentoID());
            ps.setInt(2, pagamento.getDentista().getDentistaID());
            ps.setInt(3, pagamento.getPaciente().getPacienteID());
            ps.setDate(4, new java.sql.Date(pagamento.getDtpagamento().getTime()));
            ps.setDouble(5, pagamento.getValor());
            ps.setDouble(6, pagamento.getValorpago());
            ps.setString(7, pagamento.getSituacao());
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

   public void alterarPagamento(Pagamento pagamento) {
        String sqlAlterarPagamento = "UPDATE tbl_pagamento SET  dtpagamento=?, valorpago=?, situacao=? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarPagamento);
            ps.setDate(1, new java.sql.Date(pagamento.getDtpagamento().getTime()));//Converte data para formato do banco
            ps.setDouble(2, pagamento.getValorpago());
            ps.setString(3, pagamento.getSituacao());
            ps.setInt(4, pagamento.getPagamentoID());
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

    public ArrayList<Pagamento> ListarTodosPagamentos(int id) {
        String sqlListarPagamento = "SELECT id, idatendimento,iddentista,idpaciente, dtpagamento, valor, valorpago, situacao FROM tbl_pagamento WHERE idpaciente = ? AND situacao='PAGO PARCIAL';";
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPagamento);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Pagamento pagamento = new Pagamento(
                        rs.getInt("id"),
                        new AtendimentoDAO().listarAtendimentoID(rs.getInt("idatendimento")),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDate("dtpagamento"),
                        rs.getDouble("valor"),
                        rs.getDouble("valorpago"),
                        rs.getString("situacao"));
                pagamentos.add(pagamento);//A cada linha cria e adc. um novo obj a lista
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
        return pagamentos;
    }

    public Pagamento listarPagamentoID(int id) {
        String sqlListarPorID = "SELECT id, idatendimento, iddentista, idpaciente, dtpagamento, valor, valorpago,situacao FROM tbl_pagamento WHERE idatendimento = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            Pagamento pagamento = new Pagamento();
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                pagamento = new Pagamento(
                        rs.getInt("id"),
                        new AtendimentoDAO().listarAtendimentoID(rs.getInt("idatendimento")),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDate("dtpagamento"),
                        rs.getDouble("valor"),
                        rs.getDouble("valorpago"),
                        rs.getString("situacao"));
            }
            return pagamento;
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

    public void atualizarSituacaoPagamento(Pagamento pagamento) {
        String sqlDeletarAgendamento = "UPDATE tbl_pagamento SET situacao=? WHERE id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarAgendamento);
            ps.setString(1, pagamento.getSituacao());
            ps.setInt(2, pagamento.getPagamentoID());
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
 
       public ArrayList<Pagamento> ListarDatasPagamentosPago(Date dataInicial, Date dataFinal, int id) {
        String sqlListarDatasPagamento = "select * from tbl_pagamento where dtpagamento between ? and ? and idpaciente=? and situacao ='PAGO';";
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarDatasPagamento);
            ps.setDate(1, (java.sql.Date) dataInicial);
            ps.setDate(2, (java.sql.Date) dataFinal);
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Pagamento pagamento = new Pagamento(
                        rs.getInt("id"),
                        new AtendimentoDAO().listarAtendimentoID(rs.getInt("idatendimento")),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        new PacienteDAO().listarPacientePorId(rs.getInt("idpaciente")),
                        rs.getDate("dtpagamento"),
                        rs.getDouble("valor"),
                        rs.getDouble("valorpago"),
                        rs.getString("situacao"));
                pagamentos.add(pagamento);//A cada linha cria e adc. um novo obj a lista
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
        return pagamentos;
    }
}