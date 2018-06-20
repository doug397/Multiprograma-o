package application;
	

import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import br.com.dao.UsuarioDAO;
import br.com.dominio.Usuario;
import br.com.exportadores.Exportador;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	
    public 	ObservableList<Usuario> usuarios;
    public TableView<Usuario> tableView;
    public  ObservableList<Usuario> uSelected;
    public Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		
		Group group= new Group();
		Scene scene= new Scene(group,940,510);
		stage=primaryStage;
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	    usuarios= new UsuarioDAO().lista();
	    tableView=new TableView<Usuario>(usuarios);
		
		TableColumn nomeColumn= new TableColumn("ID");
		nomeColumn.setMinWidth(180);
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn nomeColumn2= new TableColumn("CPF");
		nomeColumn2.setMinWidth(180);
		nomeColumn2.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		
		TableColumn nomeColumn3= new TableColumn("NOME");
		nomeColumn3.setMinWidth(180);
		nomeColumn3.setCellValueFactory(new PropertyValueFactory<>("nome"));
		

		TableColumn nomeColumn4= new TableColumn("LOGIN");
		nomeColumn4.setMinWidth(180);
		nomeColumn4.setCellValueFactory(new PropertyValueFactory<>("login"));
		
		TableColumn nomeColumn5= new TableColumn("SENHA");
		nomeColumn5.setMinWidth(180);
		nomeColumn5.setCellValueFactory(new PropertyValueFactory<>("senha"));
		
		tableView.getColumns().addAll(nomeColumn,nomeColumn2,nomeColumn3,nomeColumn4,nomeColumn5);
	    
		Label placeholder = new Label();
		placeholder.setText("Não existem usuarios cadastrados!");
		
		tableView.setTableMenuButtonVisible(true);
		tableView.setPlaceholder(placeholder);
		tableView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 uSelected=tableView.getSelectionModel().getSelectedItems();
				 UsuarioDetalhe ud= new UsuarioDetalhe();
				 ud.setUsuario(uSelected);
	
				 
				try {
					ud.start(new Stage());
				}catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("setOnMouseClicked"+uSelected.get(0));
				
			}
		});
		
		final VBox vbox= new VBox(tableView);
		vbox.setPadding(new Insets(70,0,0,10));
		vbox.setId("vbox");
		
		
		Label label= new Label("Lista Usuarios");
		label.setId("label-listagem");
	

		Button exportaCsv= new Button("Exportar CSV");
		exportaCsv.setLayoutX(830);
		exportaCsv.setLayoutY(25);
		
		Button exportaJSOn= new Button("Exportar JSON");
		exportaJSOn.setLayoutX(730);
		exportaJSOn.setLayoutY(25);
		
		Button atualizar= new Button("Atualizar Lista");
		atualizar.setLayoutX(430);
		atualizar.setLayoutY(25);
		atualizar.setOnAction(event -> refresh());


		Button cadastro=new Button("Cadastrar");
		cadastro.setLayoutX(350);
		cadastro.setLayoutY(25);
		cadastro.setOnAction(event-> openCadastro(primaryStage));
		
		
		
		label.setFont(Font.font("Lucida Grande",FontPosture.REGULAR,30));
		label.setPadding(new Insets(20,0,10,10));
		
		Label progresso= new Label();
		progresso.setId("label-progresso");
		progresso.setLayoutX(655);
		progresso.setLayoutY(30);
		
		
	     exportaCsv.setOnAction(event -> {	
			Task<Void> task = new Task<Void>() {
				@Override
					protected Void call() throws Exception {
					dormePorVinteSegundos();
					exportarEmCSV(usuarios);
					return null;
				}
			};
			
			task.setOnRunning(
			e -> progresso.setText("Exportando..."));
			task.setOnSucceeded(
			e -> progresso.setText("Concluído!"));
			
			
			new Thread(task).start();
		});
	     
	     exportaJSOn.setOnAction(event -> {	
				Task<Void> task = new Task<Void>() {
					@Override
						protected Void call() throws Exception {
						dormePorVinteSegundos();
						exportarEmJSon(usuarios);
						return null;
					}
				};
				
				task.setOnRunning(
				e -> progresso.setText("Exportando..."));
				task.setOnSucceeded(
				e -> progresso.setText("Concluído!"));
				
				
				new Thread(task).start();
			});
	     
	     
		
		group.getChildren().addAll(label,vbox,exportaCsv,cadastro,progresso,atualizar,exportaJSOn);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistema de Usuarios");
		primaryStage.show();
	}
	
	private void openCadastro(Stage stage) {
		Cadastro cd= new Cadastro();
		try {
			cd.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private void refresh() {
	    usuarios= new UsuarioDAO().lista();
	    tableView.setItems(usuarios);
	    tableView.refresh();
	    System.out.println("Refrash");
		
	}
	
	private void exportarEmJSon(ObservableList<Usuario> usuarios) {
		new Exportador().paraJSon(usuarios);
		//JOptionPane.showMessageDialog(null,"Exportado Com Sucesso");
	}
	
	private void exportarEmCSV(ObservableList<Usuario> usuarios) {
		try {
			new Exportador().paraCSV(usuarios);
			//JOptionPane.showMessageDialog(null,"Exportado Com Sucesso");
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Erro ao Exportar"+e);
		}
	}
	
	private void dormePorVinteSegundos() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			System.out.println("Ops, ocorreu um erro: "+ e);
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
