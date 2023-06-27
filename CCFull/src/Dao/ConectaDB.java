package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {
    private Connection conexao;

	public ConectaBD() {
		
		String url = "jdbc:mariadb://localhost:3306/javasql";
		String user = "root";
		String pwd = "root";
		try {
			conexao = DriverManager.getConnection(url, user, pwd);
			System.out.println("Conexao realizada");
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}
	public Connection getConexao() {
		return conexao;
	}
    
}
