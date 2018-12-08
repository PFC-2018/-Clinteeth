package clinteeth.dao;

import clinteeth.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO {

    Connection connection;

    public void cadastrarPessoa(Pessoa pessoa) {
        String sqlCadastrarPessoa = "INSERT INTO tbl_pessoa (nome, rg, cpf, dataNascimento, telefone, celular, idEndereco) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getRg());
            ps.setString(3, pessoa.getCpf());
            ps.setDate(4, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            ps.setString(5, pessoa.getTelefone());
            ps.setString(6, pessoa.getCelular());
            ps.setInt(7, pessoa.getEndereco().getEnderecoID());
            ps.executeUpdate();
             ResultSet rs = ps.getGeneratedKeys(); //pegar chave fk do autoincremento
            while (rs.next()) {
                pessoa.setPessoaID(rs.getInt("id"));

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

    public ArrayList<Pessoa> listAll() {
//        String sqlListarPessoa = "SELECT id, nome, rg, cpf, dataNascimento, telefone, celular, email, senha, tbl_endereco.id as idEndereco,logradouro,numero,bairro,cidade,uf,complemento,cep FROM tbl_pessoa INNER JOIN tbl_endereco on tbl_pessoa.idEndereco = tbl_endereco.id WHERE st='TRUE';";
        String sqlListarPessoa = "SELECT id, nome, rg, cpf, dataNascimento, telefone, celular, idEndereco FROM tbl_pessoa WHERE st='TRUE';";
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarPessoa);
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

    public Pessoa listPorId(int id) {
        String sqlListarPorID = "SELECT id, nome, rg, cpf, dataNascimento, telefone, celular, idEndereco FROM tbl_pessoa WHERE id = ?;";
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

    public void alterarPessoa(Pessoa pessoa) {
        String sqlAlterarPessoa = "UPDATE tbl_pessoa SET idendereco=(SELECT id FROM tbl_endereco WHERE id = ?), nome=?, dataNascimento=?, rg=?, cpf=?, telefone=?, celular=? WHERE id=?;";
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlAlterarPessoa);
            ps.setInt(1, pessoa.getEndereco().getEnderecoID());
            ps.setString(2, pessoa.getNome());
            ps.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));//Converte data para formato do banco
            ps.setString(4, pessoa.getRg());
            ps.setString(5, pessoa.getCpf());
            ps.setString(6, pessoa.getTelefone());
            ps.setString(7, pessoa.getCelular());
            ps.setInt(8, pessoa.getPessoaID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println("ERRO AO ATUALIZAR PESSOA");
            e.printStackTrace();
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. ");
            }
        }
    }
    public void excluirPessoa(int id) {
        String sqlDeletarPessoa = "UPDATE tbl_pessoa SET st='FALSE' WHERE id = ?;"; 
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
    public Pessoa VerificarCPF(String verificaCPF) throws SQLException, ClassNotFoundException {
        String sqlVerificarCPF = "SELECT cpf FROM tbl_pessoa WHERE cpf = ?  AND ST='TRUE'";
        Connection con = FabricaConexao.conexao();
        try {
            PreparedStatement ps = con.prepareStatement(sqlVerificarCPF);
            ps.setString(1, verificaCPF);
            ResultSet rs = ps.executeQuery();
            Pessoa pessoa = new Pessoa();
            if (rs.next()) {
                pessoa.setCpf(verificaCPF);
                System.out.println("Objeto com valores do BD");
            } 
            return pessoa;
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
}
