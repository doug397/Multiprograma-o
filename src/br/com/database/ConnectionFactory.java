package br.com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		String url="jdbc:mysql://localhost/visualstudio";
		try {
			System.out.println("Conectado Banco");
			return DriverManager.getConnection(url,"root","root");

		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();
		System.out.println("Conexao aberta, e agora?");
		conn.close();
		}

}
