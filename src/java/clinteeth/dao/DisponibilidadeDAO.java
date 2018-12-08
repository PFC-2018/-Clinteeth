package clinteeth.dao;

import clinteeth.model.Agendamento;
import clinteeth.model.Dentista;
import clinteeth.model.DiasDaSemanasEnum;
import clinteeth.model.Disponibilidade;
import clinteeth.model.HorasEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class DisponibilidadeDAO {

    Connection connection;

    public void cadastrarDisponibilidade(Disponibilidade disponibilidade) {
        String sqlCadastrarDisponibilidade = "INSERT INTO tbl_disponibilidade (iddentista, datadisponivel, hora) VALUES (?, ?, ?);";

        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlCadastrarDisponibilidade);
            //Seguir a ordem do INSERT (Não esquecer).
            ps.setInt(1, disponibilidade.getDentista().getDentistaID());
            ps.setString(2,disponibilidade.getDtdisponivel().getDiasSemanas());
            ps.setString(3, disponibilidade.getHora().getHoras());
            System.out.println("oi");
            ps.executeUpdate();
            System.out.println("cadastrou");
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

//    public void alterarDisponibilidade(Disponibilidade disponibilidade) {
//        String sqlAlterarDisponibilidade = "UPDATE tbl_disponibilidade SET datadisponivel=?, hora=? WHERE iddentista=?;";
//        try {
//            connection = FabricaConexao.conexao();
//            PreparedStatement ps = connection.prepareStatement(sqlAlterarDisponibilidade);
//            //Seguir a ordem do INSERT (Não esquecer).                        
//            ps.setString(1, disponibilidade.getDtDisponivel());
//            ps.setTime(2, new java.sql.Time(disponibilidade.getHora().getTime()));
//            ps.setInt(3, disponibilidade.getDentista().getDentistaID());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e.getSQLState());
//        } finally {
//            try {
//                connection.close();//Precisa finalizar conexão sempre
//            } catch (SQLException exception) {
//                System.out.println(exception + "Erro ao fechar conexão. ");
//            }
//        }
//    }
//
    public ArrayList<Disponibilidade> ListarHorariosDisponiveis(int id) throws ParseException {
        String sqlListarHorarios = "SELECT iddentista, datadisponivel, hora FROM tbl_disponibilidade WHERE ST='TRUE' and iddentista=?;";
        ArrayList<Disponibilidade> diasindisponiveis = new ArrayList<>();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarHorarios);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select

            while (rs.next()) {//Move para a próxima linha com dados e valida se há próxima linha
                DiasDaSemanasEnum diasDaSemanasEnum = null;
                switch (rs.getString("datadisponivel")) {
                    case "DOMINGO": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.DOMINGO;
                        break;
                    }
                    case "SEGUNDA-FEIRA": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.SEGUNDA;
                        break;
                    }                    
                    case "TERÇA-FEIRA": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.TERCA;
                        break;
                    }
                    case "QUARTA-FEIRA": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.QUARTA;
                        break;
                    }
                    case "QUINTA-FEIRA": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.QUINTA;
                        break;
                    }
                    case "SEXTA-FEIRA": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.SEXTA;
                        break;
                    }
                    case "SÁBADO": {
                        diasDaSemanasEnum = DiasDaSemanasEnum.SABADO;
                        break;
                    }   
                    default: {
                        throw new IllegalArgumentException("Não foi possível converter o valor vindo do banco para um dia da semana. Verifique o que está chegando na conversão. ");
                    }
                }
                HorasEnum horasEnum = null;
                switch (rs.getString("hora")) {
                    case "07:00": {
                        horasEnum = HorasEnum.SETE;
                        break;
                    }
                    case "07:30": {
                        horasEnum = HorasEnum.SETEMEIA;
                        break;
                    }
                    case "08:00": {
                        horasEnum = HorasEnum.OITO;
                        break;
                    }
                    case "08:30": {
                        horasEnum = HorasEnum.OITOMEIA;
                        break;
                    }
                    case "09:00": {
                        horasEnum = HorasEnum.NOVE;
                        break;
                    }
                    case "09:30": {
                        horasEnum = HorasEnum.NOVEMEIA;
                        break;
                    }
                    case "10:00": {
                        horasEnum = HorasEnum.DEZ;
                        break;
                    }
                    case "10:30": {
                        horasEnum = HorasEnum.DEZMEIA;
                        break;
                    }
                    case "11:00": {
                        horasEnum = HorasEnum.ONZE;
                        break;
                    }
                    case "11:30": {
                        horasEnum = HorasEnum.ONZEMEIA;
                        break;
                    }
                    case "12:00": {
                        horasEnum = HorasEnum.MEIODIA;
                        break;
                    }
                    case "12:30": {
                        horasEnum = HorasEnum.MEIODIAMEIO;
                        break;
                    }
                    case "13:00": {
                        horasEnum = HorasEnum.UMA;
                        break;
                    }
                    case "13:30": {
                        horasEnum = HorasEnum.UMAMEIA;
                        break;
                    }
                    case "14:00": {
                        horasEnum = HorasEnum.DUAS;
                        break;
                    }
                    case "14:30": {
                        horasEnum = HorasEnum.DUASMEIA;
                        break;
                    }
                    case "15:00": {
                        horasEnum = HorasEnum.TRES;
                        break;
                    }
                    case "15:30": {
                        horasEnum = HorasEnum.TRESMEIA;
                        break;
                    }
                    case "16:00": {
                        horasEnum = HorasEnum.QUATRO;
                        break;
                    }
                    case "16:30": {
                        horasEnum = HorasEnum.QUATROMEIA;
                        break;
                    }
                    case "17:00": {
                        horasEnum = HorasEnum.CINCO;
                        break;
                    }
                    case "17:30": {
                        horasEnum = HorasEnum.CINCOMEIA;
                        break;
                    }
                    case "18:00": {
                        horasEnum = HorasEnum.SEIS;
                        break;
                    }
                    case "18:30": {
                        horasEnum = HorasEnum.SEISMEIA;
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("O valor vindo do banco não é uma hora válida. Verifique a conversão.");
                    }                       
                }
                Disponibilidade disponibilidade = new Disponibilidade(
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        diasDaSemanasEnum,
                        horasEnum);
                System.out.println(diasindisponiveis);
                diasindisponiveis.add(disponibilidade);//A cada linha cria e adc. um novo obj a lista                
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
        return diasindisponiveis;
    }
//
//    public void excluirDisponibilidade(int id) {
//        String sqlDeletarPessoa = "UPDATE tbl_disponibilidade SET st='FALSE' WHERE id = ?;";
//        try {
//            connection = FabricaConexao.conexao();
//            PreparedStatement ps = connection.prepareStatement(sqlDeletarPessoa);
//            ps.setInt(1, id);
//            int linhasAtualizadas = ps.executeUpdate();//Para saber se 1 linha foi inserida
//            if (linhasAtualizadas == 1) {
//                System.out.println("1 linha foi excluida com sucesso.  ");
//            } else {
//                System.out.println("Erro ao excluir. ");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getSQLState());
//        } finally {
//            try {
//                connection.close();//Precisa finalizar conexão sempre
//            } catch (SQLException exception) {
//                System.out.println("Erro ao fechar conexão. " + exception);
//            }
//        }
//    }
//
    public Disponibilidade listarDisponibilidadePorId(int id) {
        String sqlListarDisponibilidadePorId = "SELECT id, iddentista, datadisponivel, hora FROM tbl_disponibilidade WHERE id = ? AND ST='TRUE';";
        Disponibilidade disponibilidade = new Disponibilidade();
        try {
            connection = FabricaConexao.conexao();
            PreparedStatement ps = connection.prepareStatement(sqlListarDisponibilidadePorId);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();//Objeto para leitura das linhas retornadas pelo Select            
            while (rs.next()) {
                disponibilidade = new Disponibilidade(rs.getInt("id"),
                        new DentistaDAO().listarDentistaID(rs.getInt("iddentista")),
                        DiasDaSemanasEnum.valueOf(rs.getString("datadisponivel")),
                        HorasEnum.valueOf(rs.getString("hora")));
            }
            return disponibilidade;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        } finally {
            try {
                connection.close();//Precisa finalizar conexão sempre
            } catch (SQLException exception) {
                System.out.println("Erro ao fechar conexão. " + exception);
            }
        }
        return disponibilidade;
    }
//
    public Disponibilidade VerificarData(Agendamento agendamento) throws SQLException, ClassNotFoundException {
        //Passa o objeto Agendamento para fazer a verificação se o dentista estara disponivel para efetuar o agendamento
        String sqlVerificarData = "SELECT iddentista, datadisponivel, hora FROM tbl_disponibilidade WHERE datadisponivel = ? AND hora = ? AND iddentista = ? AND ST='TRUE'";
        Connection con = FabricaConexao.conexao();
        try {
            PreparedStatement ps = con.prepareStatement(sqlVerificarData);
            ps.setDate(1, new java.sql.Date(agendamento.getDtagendamento().getTime()));
            ps.setTime(2, new java.sql.Time(agendamento.getHragendamento().getTime()));
            ps.setInt(3, agendamento.getDentista().getDentistaID());
            ResultSet rs = ps.executeQuery();
            Dentista dentista = new Dentista();
            Disponibilidade disponibilidade = new Disponibilidade();
            if (rs.next()) {
                dentista.setDentistaID(rs.getInt("iddentista"));
                DiasDaSemanasEnum.valueOf(rs.getString("datadisponivel"));
                HorasEnum.valueOf(rs.getString("hora"));
                disponibilidade.setDentista(dentista);
                disponibilidade = null;
                System.out.println("EXISTE DATA E HORARIO INDISPONIVEL");
            } else {
                System.out.println("NÃO EXISTEM DATAS");
            }
            return disponibilidade;
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
    
    public Disponibilidade VerificarDataDisponivel(Disponibilidade disponibilidade) throws SQLException, ClassNotFoundException {
        //Passa Disponibilidade para verificar se a Disponibilidade ja existe
        String sqlVerificarData = "SELECT iddentista, datadisponivel, hora FROM tbl_disponibilidade WHERE datadisponivel = ? AND hora = ? AND iddentista = ? AND ST='TRUE'";
        Connection con = FabricaConexao.conexao();
        try {
            PreparedStatement ps = con.prepareStatement(sqlVerificarData);
            ps.setString(1, disponibilidade.getDtdisponivel().toString());
            ps.setString(2, disponibilidade.getHora().toString());
            ps.setInt(3, disponibilidade.getDentista().getDentistaID());
            ResultSet rs = ps.executeQuery();
            Dentista dentista = new Dentista();
            if (rs.next()) {
                dentista.setDentistaID(rs.getInt("iddentista"));
                DiasDaSemanasEnum.valueOf(rs.getString("datadisponivel"));
                HorasEnum.valueOf(rs.getString("hora"));
                disponibilidade.setDentista(dentista);
                disponibilidade = null;
                System.out.println("EXISTE DATA E HORARIO INDISPONIVEL");
            } else {
                System.out.println("NÃO EXISTEM DATAS");
            }
            return disponibilidade;
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
