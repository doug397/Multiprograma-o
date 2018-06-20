package application;

import java.sql.Connection;

import javax.swing.JOptionPane;

import br.com.dao.UsuarioDAO;
import br.com.dominio.Usuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Cadastro extends Application {
	
	public TextField tf_nome,tf_cpf,tf_login,tf_senha;

	@Override
	public void start(Stage segundStagy) throws Exception {
	/*	 Group group= new Group();
		 Scene scene= new Scene(group,300,300);*/
		
		  Text nome= new Text("Nome");
		  Text cpf= new Text("CPF");
		  Text login= new Text("Login");
		  Text senha= new Text("Senha");
		  
		  tf_nome = new TextField();  
		  tf_cpf = new TextField();  
		  tf_login = new TextField();  
		  tf_senha = new TextField();  
		  
	     Button btn_cadastrar = new Button("Cadastrar"); 
	     Button voltar= new Button("Voltar");
		
	     btn_cadastrar.setOnAction(event -> cadastrar());
			
	     
	     GridPane gridPane= new GridPane();
	     gridPane.setMinSize(400, 200);
	     gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	     gridPane.setVgap(6); 
	     gridPane.setHgap(6);   
	     gridPane.setAlignment(Pos.CENTER); 
	     
	     gridPane.add(nome, 0, 0);
	     gridPane.add(tf_nome, 1, 0);
	     gridPane.add(cpf,0,1);
	     gridPane.add(tf_cpf, 1, 1);
	     gridPane.add(login, 0, 2);
	     gridPane.add(tf_login, 1, 2);
	     gridPane.add(senha, 0, 3);
	     gridPane.add(tf_senha,1 , 3);
	     gridPane.add(btn_cadastrar, 0, 4);
	     gridPane.add(voltar, 1, 4);
	     
		 
	/*	Button voltar= new Button("Voltar");
		voltar.setLayoutX(10);
		voltar.setLayoutY(10);
		*/
		voltar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Main m= new Main();
				m.start(segundStagy);
				
			}
		});
		
	//	group.getChildren().addAll(gridPane);
		
		Scene scene= new Scene(gridPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		segundStagy.setScene(scene);
		segundStagy.setTitle("Cadastro Usuario");
		segundStagy.show();
		
	
		
	}
	private void cadastrar() {
		UsuarioDAO u= new UsuarioDAO();
		Usuario uc= new Usuario();
		uc.setNome(tf_nome.getText());
		uc.setCpf(tf_cpf.getText());
		uc.setLogin(tf_login.getText());
		uc.setSenha(tf_senha.getText());
		
		u.adicionar(uc);
		
		
		 JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!");
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
