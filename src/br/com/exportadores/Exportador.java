package br.com.exportadores;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import br.com.dominio.Usuario;

public class Exportador {
	
	public void paraCSV(List<Usuario> usuarios) throws FileNotFoundException {
		
		PrintStream ps= new PrintStream("usuarios.csv");
		ps.println("Id,Cpf,Nome,Login,Senha");
		
		for(Usuario usuario : usuarios) {
			ps.println(String.format("%s,%s,%s,%s,%s",
				  usuario.getId(),
				  usuario.getCpf(),
				  usuario.getNome(),
				  usuario.getLogin(),
				  usuario.getSenha()));
		
			
		}
		ps.close();
	
	}
	
	public void paraJSon(List<Usuario> usuarios) {
		Gson gson = new Gson();
		String json = null;
			 json= gson.toJson(usuarios);
				try {
					FileWriter writer= new FileWriter("usuarios.json");
					writer.write(json);
					writer.close();
				}catch (Exception e) {
					throw new RuntimeException(e);
				}
			
	}
	
/*	public static void main(String[] args) throws FileNotFoundException {
		
		Usuario testU=new Usuario();
		testU.setId("101");
		testU.setCpf("123132132");
		testU.setLogin("12312");
		testU.setNome("Dosdosda");
		testU.setSenha("2121");
		
		Usuario testU2=new Usuario();
		testU2.setId("101");
		testU2.setCpf("123132132");
		testU2.setLogin("12312");
		testU2.setNome("Dosdosda");
		testU2.setSenha("2121");
		new Exportador().paraCSV(Arrays.asList(testU2,testU));
		
		new Exportador().paraJSon(Arrays.asList(testU2,testU));
		
		
	}*/

}
