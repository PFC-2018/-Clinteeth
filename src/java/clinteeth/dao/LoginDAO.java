package clinteeth.dao;

import clinteeth.model.Login;
import clinteeth.model.Pessoa;
import clinteeth.model.PessoasEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Connection connection;

    public Login VerificarAcesso(String email, String senha) throws SQLException, ClassNotFoundException {
        String sqlVerificarAcesso = "SELECT email, senha, tipo, idpessoa FROM tbl_login WHERE email = ? AND senha = ? AND ST='TRUE'";
        Connection con = FabricaConexao.conexao();
        try {
            PreparedStatement ps = con.prepareStatement(sqlVerificarAcesso);
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            Login login = new Login();
            Pessoa pessoa = new Pessoa();
            if (rs.next()) {
                login.setEmail(rs.getString("email"));
                login.setSenha(rs.getString("senha"));
                login.setTipo(PessoasEnum.valueOf(rs.getString("tipo")));
                pessoa.setPessoaID(rs.getInt("idpessoa"));
                login.setPessoa(pessoa);
                System.out.println("Objeto com valores do BD");
            } else {
                login = null;
                System.out.println("Objeto Nulo");
            }
            return login;
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

    public boolean cadastrarLogin(Login login) {
        String sqlCadastrarLogin = "INSERT INTO tbl_login (email,senha,tipo,idPessoa) VALUES (?, ?, ?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarLogin);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getSenha());
            ps.setString(3, login.getTipo().toString());
            ps.setInt(4, login.getPessoa().getPessoaID());

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

        return false;
    }

    public void alterarLogin(Login login) {
        String sqlAlterarLogin = "UPDATE tbl_login SET email=?, senha=? WHERE idpessoa=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarLogin);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getSenha());
            ps.setInt(3, login.getPessoa().getPessoaID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }

           public void excluirLogin(int id) {
        String sqlDeletarPessoa = "UPDATE tbl_login SET st='FALSE' WHERE idpessoa = ?;"; 
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
    
    public Login buscarPessoa(int id) {
        String sqlbuscarPessoa = "SELECT id, email, senha, tipo FROM tbl_login WHERE idPessoa = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlbuscarPessoa);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Login login = new Login();
            while (rs.next()) {
                login = new Login(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        PessoasEnum.valueOf(rs.getString("tipo")));
            }

            return login;

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
    
    public Login buscarDentista(int id) {
        String sqlbuscarPessoa = "SELECT id, email, senha, tipo FROM tbl_login WHERE idPessoa = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlbuscarPessoa);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Login login = new Login();
            while (rs.next()) {
                login = new Login(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        PessoasEnum.valueOf(rs.getString("tipo")));
            }

            return login;

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
    
    public Login buscarPaciente(int id) {
        String sqlbuscarPessoa = "SELECT id, email, senha, tipo FROM tbl_login WHERE idPessoa = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlbuscarPessoa);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Login login = new Login();
            while (rs.next()) {
                login = new Login(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        PessoasEnum.valueOf(rs.getString("tipo")));
            }

            return login;

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
