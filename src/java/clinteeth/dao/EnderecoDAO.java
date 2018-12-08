package clinteeth.dao;

import clinteeth.model.Endereco;
import clinteeth.model.EstadosEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {

    Connection connection;

    public boolean cadastrarEndereco(Endereco endereco) {
        String sqlCadastrarEndereco = "INSERT INTO tbl_endereco (logradouro, numero, bairro, cidade, uf, complemento, cep) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarEndereco, PreparedStatement.RETURN_GENERATED_KEYS);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setString(1, endereco.getLogradouro());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getCidade());
            ps.setString(5, endereco.getUf().toString());
            ps.setString(6, endereco.getComplemento());
            ps.setString(7, endereco.getCep());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //pegar chave fk do autoincremento
            while (rs.next()) {
                endereco.setEnderecoID(rs.getInt("id"));

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

        return false;
    }

    public void alterarEndereco(Endereco endereco) {
        String sqlAlterarEndereco = "UPDATE tbl_endereco SET logradouro=?, numero=?, bairro=?, cidade=?, uf=?, complemento=?, cep=? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarEndereco);
            ps.setString(1, endereco.getLogradouro());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getCidade());
            ps.setString(5, endereco.getUf().toString());
            ps.setString(6, endereco.getComplemento());
            ps.setString(7, endereco.getCep());
            ps.setInt(8, endereco.getEnderecoID());
            ps.executeUpdate();
             } catch (SQLException e) {
              e.printStackTrace();
              System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }

    Endereco listPorId(int id) {
        String sqlListarPorID = "SELECT id, logradouro, numero, bairro, cidade, uf, complemento, cep FROM tbl_endereco WHERE id = ?;";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Endereco endereco = new Endereco();
            while (rs.next()) {
                endereco = new Endereco(
                        rs.getInt("id"),
                        rs.getString("logradouro"),
                        Integer.parseInt(rs.getString("numero")),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        EstadosEnum.valueOf(rs.getString("uf")),
                        rs.getString("complemento"),
                        rs.getString("cep"));
            }
            return endereco;

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
