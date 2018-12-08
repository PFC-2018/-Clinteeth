package clinteeth.dao;

import clinteeth.model.Procedimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProcedimentoDAO {

    Connection connection;

    public void cadastrarProcedimento(Procedimento procedimento) {
        String sqlCadastrarProcedimento = "INSERT INTO tbl_procedimento (descricao,preco) VALUES (?,?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarProcedimento, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, procedimento.getDescricao());
            ps.setDouble(2, procedimento.getPreco());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //pegar chave fk do autoincremento
            while (rs.next()) {
                procedimento.setprocedimentoID(rs.getInt("id"));
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

    public void alterarProcedimento(Procedimento procedimento) {
        String sqlAlterarProcedimento = "UPDATE tbl_procedimento SET descricao=? , preco =? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarProcedimento);
            ps.setString(1, procedimento.getDescricao());
            ps.setDouble(2, procedimento.getPreco());
            ps.setInt(3, procedimento.getprocedimentoID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.out.println("ERRO AO ATUALIZAR ");
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }

    public void excluirProcedimento(int id) {
        String sqlDeletarProcedimento = "UPDATE tbl_procedimento SET st='FALSE' WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarProcedimento);
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

    public Procedimento listarProcedimentoPorId(int id) {
        String sqlListarPorID = "SELECT id, descricao, preco FROM tbl_procedimento WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Procedimento procedimento = new Procedimento();
            while (rs.next()) {
                procedimento = new Procedimento(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"));
            }
            return procedimento;

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

    public Procedimento listarProcedimentoPorIdGson(int id) {
        String sqlListarPorID = "SELECT id, descricao, preco FROM tbl_procedimento WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Procedimento procedimento = new Procedimento();
            while (rs.next()) {
                procedimento = new Procedimento(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"));

            }

            return procedimento;

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

    public ArrayList<Procedimento> ListarTodosProcedimentos() {
        String sqlListarProcedimento = "SELECT id, descricao, preco FROM tbl_procedimento WHERE ST='TRUE' order by id;";
        ArrayList<Procedimento> procedimentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarProcedimento);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Procedimento procedimento = new Procedimento(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"));

                procedimentos.add(procedimento);//A cada linha cria e adc. um novo obj a lista
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
        return procedimentos;
    }

    public ArrayList<Procedimento> ListarTodosProcedimentosPorIDdoAtendimento(int id) {
        String sql = "SELECT IDPROCEDIMENTO FROM TBL_PROCEDIMENTO_ATENDIMENTO WHERE IDATENDIMENTO=?";
        ArrayList<Procedimento> procedimentos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Procedimento procedimento;
            while (rs.next()) {
                procedimento = new ProcedimentoDAO().listarProcedimentoPorId(rs.getInt("idprocedimento"));
                procedimentos.add(procedimento);
            }

            return procedimentos;

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

}
