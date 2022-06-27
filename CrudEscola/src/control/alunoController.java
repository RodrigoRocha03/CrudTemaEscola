package control;

import java.util.List;

import entity.aluno;

import javafx.beans.property.LongProperty;

import javafx.beans.property.SimpleLongProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.IAlunoDao;
import persistence.alunoDao;

public class alunoController {

	public LongProperty id = new SimpleLongProperty(0);
	public StringProperty nome = new SimpleStringProperty("");
	public StringProperty ra = new SimpleStringProperty("");
	public StringProperty rg = new SimpleStringProperty("");

	private ObservableList<aluno> alunos = FXCollections.observableArrayList();

	private IAlunoDao alunoDAO = new alunoDao();

	public void cadastrar() {
		aluno a = new aluno();
		fromEntity(a);
	}

	public void buscar() {
		alunos.clear();
		List<aluno> encontrados = alunoDAO.Buscar(nome.get());
		alunos.addAll(encontrados);
		if (!alunos.isEmpty()) {
			fromEntity(alunos.get(0));

		}
	}

	public void salvar() {
		aluno a = toEntity();
		alunoDAO.Cadastrar(a);
	}

	public void alterar() {
		aluno a = toEntity();
		alunoDAO.atualizar(id.get(), a);
	}

	public void remover(long id) {
		alunoDAO.remover(id);
	}
 
	public aluno toEntity() {
		aluno a = new aluno();
		a.setId(id.get());
		a.setNome(nome.get());
		a.setRa(ra.get());
		a.setRg(rg.get());
		return a;
	}

	public void fromEntity(aluno a) {
		id.set(a.getId());
		nome.set(a.getNome());
		ra.set(a.getRa());
		rg.set(a.getRg());

	}

	public ObservableList<aluno> getLista() {
		return alunos;
	}

}