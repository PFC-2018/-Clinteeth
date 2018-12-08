package clinteeth.dao;

import clinteeth.model.Paciente;
import clinteeth.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AtendenteDAO {

    Connection connection;
    
    public ArrayList<Pessoa> ListarTodosAtendentes() {

        String sqlListarAtendente = "SELECT A.id as\"id\", A.nome as\"nome\", A.rg as\"rg\", A.cpf as\"cpf\", A.dataNascimento as\"dataNascimento\", A.telefone as\"telefone\", A.celular as\"celular\", A.idEndereco as\"idEndereco\" FROM TBL_PESSOA A, TBL_LOGIN B WHERE A.ID=B.IDPESSOA AND B.TIPO='ATENDENTE' AND A.st='TRUE';";
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarAtendente);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select
            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                Pessoa pessoa = new Pessoa(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getDate("dataNascimento"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        new LoginDAO().buscarPessoa(rs.getInt("id")),
                        new EnderecoDAO().listPorId(rs.getInt("idEndereco")));

                System.out.println(pessoas);
                pessoas.add(pessoa);//A cada linha cria e adc. um novo obj a lista
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
        return pessoas;
    }

    public Pessoa listarPorId(int id) {
        String sqlListarPorID = "SELECT A.id as\"id\", A.nome as\"nome\", A.rg as\"rg\", A.cpf as\"cpf\", A.dataNascimento as\"dataNascimento\", A.telefone as\"telefone\", A.celular as\"celular\", A.idEndereco as\"idEndereco\" FROM tbl_pessoa A, TBL_LOGIN B WHERE A.ID=B.IDPESSOA AND B.TIPO='ATENDENTE' AND A.id = ?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPorID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Pessoa pessoa = new Pessoa();
            while (rs.next()) {
                pessoa = new Pessoa(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getDate("DataNascimento"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        new LoginDAO().buscarPessoa(rs.getInt("id")),
                        new EnderecoDAO().listPorId(rs.getInt("idEndereco")));
            }
            return pessoa;

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
