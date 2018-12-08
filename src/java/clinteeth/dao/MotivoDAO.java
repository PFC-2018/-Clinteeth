package clinteeth.dao;

import clinteeth.model.Motivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotivoDAO {

    Connection connection;

    public void cadastrarMotivo(Motivo motivo) {
        String sqlCadastrarMotivo = "INSERT INTO tbl_motivo (descricao) VALUES (?);";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarMotivo, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, motivo.getDescricao());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //pegar chave fk do autoincremento

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

    public ArrayList<Motivo> listarMotivos() {
//        String sqlListarMotivo = "SELECT id, nome, rg, cpf, dataNascimento, telefone, celular, email, senha, tbl_endereco.id as idEndereco,logradouro,numero,bairro,cidade,uf,complemento,cep FROM tbl_motivo INNER JOIN tbl_endereco on tbl_motivo.idEndereco = tbl_endereco.id WHERE st='TRUE';";
        String sqlListarMotivo = "SELECT id, descricao FROM tbl_motivo WHERE st='TRUE';";
        ArrayList<Motivo> motivos = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarMotivo);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Motivo motivo = new Motivo(rs.getInt("id"),
                        rs.getString("descricao"));

                System.out.println(motivos);
                motivos.add(motivo);//A cada linha cria e adc. um novo obj a lista
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
        return motivos;
    }

    public Motivo listarMotivoPorId(int id) {
        String sqlListarPorID = "SELECT id, descricao FROM tbl_motivo WHERE id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Motivo motivo = new Motivo();
            while (rs.next()) {
                motivo = new Motivo(rs.getInt("id"),
                        rs.getString("descricao"));
            }
            return motivo;

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

    public void alterarMotivo(Motivo motivo) {
        String sqlAlterarMotivo = "UPDATE tbl_motivo SET descricao = ? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarMotivo);
            ps.setString(1, motivo.getDescricao());           
            ps.setInt(2, motivo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println("ERRO AO ATUALIZAR MOTIVO");
            e.printStackTrace();
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }

    public void excluirMotivo(int id) {
        String sqlDeletarMotivo = "UPDATE tbl_motivo SET st='FALSE' WHERE id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlDeletarMotivo);
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
}
