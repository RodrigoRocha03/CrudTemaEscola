package persistence;

import java.util.List;

import entity.aluno;

public interface IAlunoDao {

	public void Cadastrar(aluno a);

	public List<aluno> Buscar(String nome);

	public void remover(long id);

	public void atualizar(long id, aluno a);

}