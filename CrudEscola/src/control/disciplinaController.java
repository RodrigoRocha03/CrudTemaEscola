package control;

import java.util.List;


import entity.disciplina;
import javafx.beans.property.LongProperty;

import javafx.beans.property.SimpleLongProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import persistence.IDisciplinaDao;

import persistence.disciplinaDao;

public class disciplinaController {

	public LongProperty id = new SimpleLongProperty(0);
	public StringProperty nome = new SimpleStringProperty("");

	private ObservableList<disciplina> disciplinas = FXCollections.observableArrayList();

	private IDisciplinaDao disciplinaDAO = new disciplinaDao();

	public void inserir() {
		disciplina d = new disciplina();
		fromEntity(d);
	}

	public void buscar() {
		disciplinas.clear();
		List<disciplina> encontradas = disciplinaDAO.Buscar(nome.get());
		disciplinas.addAll(encontradas);
		if (!disciplinas.isEmpty()) {
			fromEntity(disciplinas.get(0));

		}
	}

	public void salvar() {
		disciplina d = toEntity();
		disciplinaDAO.Inserir(d);
	}

	public void alterar() {
		disciplina d = toEntity();
		disciplinaDAO.atualizar(id.get(), d);
	}

	public void remover(long id) {
		disciplinaDAO.remover(id);
	}
	
	

	public disciplina toEntity() {
		disciplina d = new disciplina();
		d.setId(id.get());
		d.setNome(nome.get());

		return d;
	}

	public void fromEntity(disciplina d) {
		id.set(d.getId());
		nome.set(d.getNome());

	}

	public ObservableList<disciplina> getLista() {
		return disciplinas;
	}

}