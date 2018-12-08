package clinteeth.dao;

import clinteeth.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteDAO {

    Connection connection;

    public void cadastrarPaciente(Paciente paciente) {
        String sqlCadastrarPaciente = "INSERT INTO tbl_paciente (convenio,idPessoa) VALUES (?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarPaciente);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setString(1, paciente.getConvenio());
            ps.setInt(2, paciente.getPessoaID());

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

    public void alterarPaciente(Paciente paciente) {
        String sqlAlterarPaciente = "UPDATE tbl_paciente SET convenio=? ,idpessoa=? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarPaciente);            
            ps.setString(1, paciente.getConvenio());
            ps.setInt(2, paciente.getPessoaID());
            ps.setInt(3, paciente.getPacienteID());
            ps.executeUpdate();
            System.out.println("VALORES ALTERADOS");
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println("VALORES NAO ALTERADOS");
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }

            public boolean excluirPaciente(int id) {
        String sqlDeletarPessoa = "UPDATE tbl_paciente SET st='FALSE' WHERE idpessoa = ?;"; 
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
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
        return false;
    }
    
    public Paciente listarPorId(int id) {
        String sqlListarPorID = "SELECT id, idPessoa, convenio FROM tbl_paciente WHERE idpessoa= ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Paciente paciente = new Paciente();
            while (rs.next()) {
                paciente = new Paciente(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("convenio"));
            }
            return paciente;

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
    
    public Paciente listarPacientePorId(int id) {
        String sqlListarPorID = "SELECT id, idPessoa, convenio FROM tbl_paciente WHERE id= ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Paciente paciente = new Paciente();
            while (rs.next()) {
                paciente = new Paciente(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("convenio"));
            }
            return paciente;

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

    public ArrayList<Paciente> ListarTodosPacientes() {
        String sqlListarMedico = "SELECT id, idPessoa, convenio FROM tbl_paciente WHERE ST='TRUE';";
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarMedico);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Paciente paciente = new Paciente(rs.getInt("id"),
                        new PessoaDAO().listPorId(rs.getInt("idPessoa")),
                        rs.getString("convenio"));

                pacientes.add(paciente);//A cada linha cria e adc. um novo obj a lista
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
        return pacientes;
    }
}
