package persistence;

import java.util.List;

import entity.disciplina;

public interface IDisciplinaDao {

	public void Inserir(disciplina d);

	public List<disciplina> Buscar(String nome);

	public void remover(long id);

	public void atualizar(long id, disciplina a);

}
