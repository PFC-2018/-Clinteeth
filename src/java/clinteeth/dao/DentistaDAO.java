package clinteeth.dao;

import clinteeth.model.Dentista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DentistaDAO {

    Connection connection;

    public void cadastrarDentista(Dentista dentista) {
        String sqlCadastrarDentista = "INSERT INTO tbl_dentista (cro,idPessoa) VALUES (?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarDentista);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setString(1, dentista.getCro());
            ps.setInt(2, dentista.getPessoaID());

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

    public void alterarDentista(Dentista dentista) {
        String sqlAlterarDentista = "UPDATE tbl_dentista SET cro=? WHERE idpessoa=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarDentista);
            ps.setString(1, dentista.getCro());
            ps.setInt(2, dentista.getPessoaID());
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
        public void excluirDentista(int id) {
        String sqlDeletarPessoa = "UPDATE tbl_dentista SET st='FALSE' WHERE idpessoa = ?;"; 
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarPessoa);
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
        
    public Dentista listarPorId(int id) {//TRAZ OS DADOS PARA A ATUALIZAR DENTISTA
        String sqlListarPorID = "SELECT id, idPessoa, cro FROM tbl_dentista WHERE id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Dentista dentista = new Dentista();
            while (rs.next()) {
                dentista = new Dentista(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("cro"));
            }
            return dentista;

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

    public ArrayList<Dentista> ListarTodosDentistas() {
        String sqlListarMedico = "SELECT id, idPessoa, cro FROM tbl_dentista WHERE ST='TRUE';";
        ArrayList<Dentista> dentistas = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarMedico);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Dentista dentista = new Dentista(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("cro"));

                dentistas.add(dentista);//A cada linha cria e adc. um novo obj a lista
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
        return dentistas;
    }
    
    public Dentista listarDentistaID(int id) {
        String sqlListarPorID = "SELECT id, idPessoa, cro FROM tbl_dentista WHERE id= ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Dentista dentista = new Dentista();
            while (rs.next()) {
                dentista = new Dentista(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("cro"));
            }
            return dentista;

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
    
    public Dentista listarDentistaIDporIDPESSOA(int id) {
        String sqlListarPorID = "SELECT id, idPessoa, cro FROM tbl_dentista WHERE idPessoa= ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Dentista dentista = new Dentista();
            while (rs.next()) {
                dentista = new Dentista(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("cro"));
            }
            return dentista;

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
