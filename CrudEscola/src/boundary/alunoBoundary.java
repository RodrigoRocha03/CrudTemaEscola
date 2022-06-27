package boundary;

import control.alunoController;
import entity.aluno;
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

public class alunoBoundary extends Application {

	//private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtRa = new TextField();
	private TextField txtRg = new TextField();

	private Button btnCadastrar = new Button("Cadastrar");
	private Button btnSalvar = new Button("Salvar");
	private Button btnBuscar = new Button("Buscar");
	private Button btnAlterar = new Button("Alterar");

	private alunoController control = new alunoController();

	private TableView<aluno> table = new TableView<>();

	private void criarTabela() {
		TableColumn<aluno, Long> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<aluno, Long>("id"));

		TableColumn<aluno, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<aluno, String>("nome"));

		TableColumn<aluno, String> col3 = new TableColumn<>("RA");
		col3.setCellValueFactory(new PropertyValueFactory<aluno, String>("ra"));

		TableColumn<aluno, String> col4 = new TableColumn<>("RG");
		col4.setCellValueFactory(new PropertyValueFactory<aluno, String>("rg"));

		TableColumn<aluno, String> col5 = new TableColumn<>("Ações");
		col5.setCellFactory((tbcol) -> {
			Button btnRemover = new Button("Remover");
			TableCell<aluno, String> tcell = new TableCell<aluno, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						btnRemover.setOnAction((e) -> {
							aluno a = getTableView().getItems().get(getIndex());
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

		table.getColumns().addAll(col1, col2, col3, col4, col5);
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
		Bindings.bindBidirectional(txtRa.textProperty(), control.ra);
		Bindings.bindBidirectional(txtRg.textProperty(), control.rg);

		// panCampos.add(new Label("Id:"), 0, 0);
		// panCampos.add(txtId, 1, 0);
		panCampos.add(new Label("Nome:"), 0, 1);
		panCampos.add(txtNome, 1, 1);
		panCampos.add(new Label("Ra:"), 0, 2);
		panCampos.add(txtRa, 1, 2);
		panCampos.add(new Label("Rg:"), 0, 3);
		panCampos.add(txtRg, 1, 3);

		panCampos.add(btnCadastrar, 0, 5);
		panCampos.add(btnBuscar, 1, 5);
		panCampos.add(btnAlterar, 2, 5);
		panCampos.add(btnSalvar, 3, 5);

		btnCadastrar.setOnAction((e) -> {
			control.cadastrar();

		});

		btnBuscar.setOnAction((e) -> {
			control.buscar();
		});

		btnSalvar.setOnAction((e) -> {
			control.salvar();
			new Alert(Alert.AlertType.INFORMATION, "Aluno salvo com sucesso").showAndWait();
		});

		btnAlterar.setOnAction((e) -> {
			control.alterar();
		});

		stage.setScene(scn);
		stage.setTitle("Cadastro de aluno");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(alunoBoundary.class, args);
	}

}
