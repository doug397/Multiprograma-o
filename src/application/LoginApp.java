package application;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginApp extends Application {
	
	private AnchorPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar;
	private Button btSair;
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		pane= new AnchorPane();
		pane.setPrefSize(400, 300);
		pane.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		pane.setId("pane");
		
		stage=primaryStage;
		
	     txLogin= new TextField();
		txLogin.setPromptText("Digite seu login");
		
		txSenha= new PasswordField();
		txSenha.setPromptText("Digite sua Senha");
		
	     btEntrar= new Button("Entrar");
		 btSair= new Button("Sair");
	
		
		
		pane.getChildren().addAll(txLogin,txSenha,btEntrar,btSair);
		
		Scene scene= new Scene(pane);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		

		
		
		txLogin.setLayoutX( (pane.getWidth() - txLogin.getWidth()) / 2);
		txLogin.setLayoutY(50);
		txSenha.setLayoutX((pane.getWidth() - txSenha.getWidth()) / 2);
		txSenha.setLayoutY(100);
		btEntrar.setLayoutX(
		(pane.getWidth() - btEntrar.getWidth()) / 2);
		btEntrar.setLayoutY(150);
		btSair.setLayoutX((pane.getWidth() - btSair.getWidth()) / 2);
		btSair.setLayoutY(200);
		
		 initListenes();
		
		
		
		
	}
	private void fecharApplicacao() {
		System.exit(0);
	}
	
	private void logar() {
		if(txLogin.getText().equals("admin") && 
			txSenha.getText().equals("admin")){
			// Abrir tela
			try {
				new Main().start(new Stage());
				stage.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Login e/ou senha invalidos"
					, "Erro",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static Stage getStage() {
		return getStage();
	}
	
	private void initListenes() {
		btSair.setOnAction(event->fecharApplicacao());
		btEntrar.setOnAction(event-> logar());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
