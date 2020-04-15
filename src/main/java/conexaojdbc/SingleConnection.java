package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "Pe68288120";
	private static String user = "postgres";
	private static Connection connection =null;
	
	static {
		
		conectar();
	}
	
	
	public SingleConnection() {
		
		conectar();
	}
	
	private static void conectar() { // metodo para conectar no banco de dados
		
		try {
			
			if(connection == null) {
				// carregamento do driver(o tipo de banco) a ser usado.
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				
			}
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static Connection getconnection() {
		
		return connection;
	}

}
