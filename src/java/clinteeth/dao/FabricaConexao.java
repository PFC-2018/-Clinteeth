
package clinteeth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    
        private static Connection connection = null; // atributo de interface para retornar uma instacia de conexão do banco.
    
    public static Connection conexao(){ // metodo que vai inicializar a seesão.
        try {
            String senha = "postgres";
            String usuario = "postgres";
            
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinteeth", usuario, senha);
        }catch (ClassNotFoundException | SQLException sqlexception) {
            System.out.println(sqlexception.getMessage());
           
        }finally { // irá executar caso tenha erro ou não
            return connection;
        }
    }
    
}
