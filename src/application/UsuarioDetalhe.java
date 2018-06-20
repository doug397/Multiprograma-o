package application;

import java.nio.file.Path;

import br.com.dominio.Usuario;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UsuarioDetalhe extends Application {
	
	private AnchorPane pane;
	private ImageView imgItem;
	private Label lbnome,lbcpf,lbid,lblogin,lbsenha;
	private static Stage stage;
	public static ObservableList<Usuario> usuario;
	private Usuario user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		user=usuario.get(0);
		pane = new AnchorPane();
		pane.setPrefSize(600, 400);
		Scene scene= new Scene(pane);
		stage=primaryStage;
		
		imgItem= new ImageView(new Image("http://static.mercadoshops.com/carretilha-marine-saga-11000-lado-esquerdo-lancamento_iZ179737113XvZcXpZ1XfZ194632812-858346482-3.jpgXsZ194632812xIM.jpg"));

		imgItem.setFitHeight(100);
		imgItem.setEffect(new Reflection());
		
		lbnome=new  Label();
		lbnome.setText(user.getNome());
		
		lbcpf=new  Label();
		lbcpf.setText(user.getCpf());
		
		lblogin=new  Label();
		lblogin.setText(user.getLogin());
		
		lbsenha=new  Label();
		lbsenha.setText(user.getSenha());
		
		
		
		
		pane.getChildren().addAll(lbnome,lbcpf,lblogin,lbsenha,imgItem);
		
	   

		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle(usuario.get(0).getNome());
		primaryStage.show();
		
		
		imgItem.setLayoutX( (pane.getWidth() - imgItem.getFitWidth()) / 2);
		imgItem.setLayoutY(20);
		
		lbnome.setLayoutX( (pane.getWidth() - lbnome.getWidth()) / 2);
		lbnome.setLayoutY(150);
		
		lbcpf.setLayoutX( (pane.getWidth() - lbcpf.getWidth()) / 2);
		lbcpf.setLayoutY(200);
		
		lblogin.setLayoutX( (pane.getWidth() - lblogin.getWidth()) / 2);
		lblogin.setLayoutY(250);
		
		lbsenha.setLayoutX( (pane.getWidth() - lbsenha.getWidth()) / 2);
		lbsenha.setLayoutY(300);
		
		
	
		
		
		
		
	}
	


	public void setUsuario(ObservableList<Usuario> uSelected) {
		UsuarioDetalhe.usuario =uSelected;
	}
	public static ObservableList<Usuario> getUsuario() {
		return usuario;
	}


	public static void main(String[] args) {
		launch(args);
	}
	


}
