package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.database.ConnectionFactory;
import br.com.dominio.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDAO {
	
	String url= "select *from usuario";
	
	private static final String SELECT_FROM_USUARIOS="select *from usuario";
	private static final String INSERT_USUARIO="insert into usuario" 
				   		+"(cpf,nome,login,senha)" 
					   		+ "values (?,?,?,?)";
	
	public ObservableList<Usuario> lista(){
		
		ObservableList<Usuario> usuarios=FXCollections.observableArrayList();
		

		PreparedStatement ps;
		try(Connection conn= new ConnectionFactory().getConnection()) {
			
			ps=conn.prepareStatement(SELECT_FROM_USUARIOS);
			ResultSet resultSet=ps.executeQuery();
			
			while(resultSet.next())
			{
	
				Usuario usuario= new Usuario();
				usuario.setId(resultSet.getString("id"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuarios.add(usuario);
			}
			
			ps.close();
			resultSet.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
			
		return usuarios;
	
	}
	
	public void adicionar(Usuario usuario) {
	   PreparedStatement ps;
	   
	   try (Connection conn =
			   new ConnectionFactory().getConnection()) { 
		   
		   
		   ps = conn.prepareStatement(INSERT_USUARIO);
				  
		   ps.setString(1, usuario.getCpf());
		   ps.setString(2, usuario.getNome());
		   ps.setString(3, usuario.getLogin());
		   ps.setString(4, usuario.getSenha());
		   
		   ps.execute();
		   ps.close();
		   
		   		   
				   
	   }catch (SQLException e) {
		   throw new RuntimeException(e);
	   }
		
	}

}
