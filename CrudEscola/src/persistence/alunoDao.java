package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.aluno;

public class alunoDao implements IAlunoDao {

	private static final String URIDB = "jdbc:mariadb://127.0.0.1:3306/bdaluno";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "8970Roferocha";

	public alunoDao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URIDB, USUARIO, PASSWORD);
	}

	@Override
	public void Cadastrar(aluno a) {
		try {
			Connection con = getConnection();
			String sql = "INSERT INTO alunos (id,nome, ra, rg) " + "VALUES (0, ?, ?, ?)";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getRa());
			stmt.setString(3, a.getRg());
			stmt.executeQuery();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<aluno> Buscar(String nome) {
		List<aluno> lista = new ArrayList<aluno>();
		try {
			Connection con = getConnection();
			String sql = "SELECT * FROM alunos";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				aluno a = new aluno();
				a.setId(rs.getLong("id"));
				a.setNome(rs.getString("nome"));
				a.setRa(rs.getString("ra"));
				a.setRg(rs.getString("rg"));

				lista.add(a);

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void remover(long id) {
		try {
			Connection con = getConnection();
			String sql = "DELETE FROM alunos WHERE id = ?";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.executeQuery();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(long id, aluno a) {
		try {
			Connection con = getConnection();
			String sql = "UPDATE alunos SET nome = ?, ra = ?, rg = ? WHERE id = ?";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getRa());
			stmt.setString(3, a.getRg());
			stmt.setLong(4, a.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}