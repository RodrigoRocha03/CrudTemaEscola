package boundary;

import control.disciplinaController;

import entity.disciplina;
import javafx.application.Application;
import javafx.beans.binding.Bindings;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.util.converter.NumberStringConverter;

public class disciplinaBoundary extends Application {

	// private TextField txtId = new TextField();
	private TextField txtNome = new TextField();

	private Button btnInserir = new Button("Inserir");
	private Button btnSalvar = new Button("Salvar");
	private Button btnBuscar = new Button("Buscar");
	private Button btnAlterar = new Button("Alterar");

	private disciplinaController control = new disciplinaController(); // composição

	private TableView<disciplina> table = new TableView<>();

	private void criarTabela() {
		TableColumn<disciplina, Long> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<disciplina, Long>("id"));

		TableColumn<disciplina, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<disciplina, String>("nome"));

		TableColumn<disciplina, String> col3 = new TableColumn<>("Ações");
		col3.setCellFactory((tbcol) -> {
			Button btnRemover = new Button("Remover");
			TableCell<disciplina, String> tcell = new TableCell<disciplina, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						btnRemover.setOnAction((e) -> {
							disciplina a = getTableView().getItems().get(getIndex());
							control.remover(a.getId());
						});
						setGraphic(btnRemover);
						setText(null);
					}
				}
			};
			return tcell;
		});
		
	
	
			
	

		table.getSelectionModel().selectedItemProperty().addListener((obs, old, novo) -> {
			if (novo == null) {
				control.fromEntity(old);
			} else {

				control.fromEntity(novo);
			}

		});

		table.getColumns().addAll(col1, col2, col3);
		table.setItems(control.getLista());
	}

	public void start(Stage stage) {
		BorderPane panPrincipal = new BorderPane();
		GridPane panCampos = new GridPane();
		Scene scn = new Scene(panPrincipal, 600, 400);

		panPrincipal.setTop(panCampos);
		panPrincipal.setCenter(table);

		criarTabela();

		// txtId.setEditable(false);

		// Bindings.bindBidirectional(txtId.textProperty(), control.id, new
		// NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nome);

		// panCampos.add(new Label("Id:"), 0, 0);
		// panCampos.add(txtId, 1, 0);
		panCampos.add(new Label("Nome:"), 0, 1);
		panCampos.add(txtNome, 1, 1);

		panCampos.add(btnInserir, 0, 5);
		panCampos.add(btnBuscar, 1, 5);
		panCampos.add(btnAlterar, 2, 5);
		panCampos.add(btnSalvar, 3, 5);

		btnInserir.setOnAction((e) -> {
			control.inserir();
		});

		btnBuscar.setOnAction((e) -> {
			control.buscar();
		});

		btnSalvar.setOnAction((e) -> {
			control.salvar();
			new Alert(Alert.AlertType.INFORMATION, "Disciplina salva com sucesso").showAndWait();
		});

		btnAlterar.setOnAction((e) -> {
			control.alterar();
		});

		stage.setScene(scn);
		stage.setTitle("Inserir disciplina");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(disciplinaBoundary.class, args);
	}

}
