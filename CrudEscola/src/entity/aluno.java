package entity;

public class aluno {

	private long id;
	private String nome = "";
	private String ra = "";
	private String rg = "";

	@Override
	public String toString() {
		return "aluno [nome=" + nome + ", ra=" + ra + ", rg=" + rg + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}