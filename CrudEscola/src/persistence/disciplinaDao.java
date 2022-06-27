package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.disciplina;

public class disciplinaDao implements IDisciplinaDao {

	private static final String URIDB = "jdbc:mariadb://127.0.0.1:3306/bddisciplina";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "8970Roferocha";

	public disciplinaDao() {
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
	public void Inserir(disciplina d) {
		try {
			Connection con = getConnection();
			String sql = "INSERT INTO disciplinas (id, nome) " + "VALUES (0, ?)";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, d.getNome());
			stmt.executeQuery();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<disciplina> Buscar(String nome) {
		List<disciplina> lista = new ArrayList<disciplina>();
		try {
			Connection con = getConnection();
			String sql = "SELECT * FROM disciplinas";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				disciplina d = new disciplina();
				d.setId(rs.getInt("id"));
				d.setNome(rs.getString("nome"));
				lista.add(d);
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
			String sql = "DELETE FROM disciplinas WHERE id = ?";
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
	public void atualizar(long id, disciplina d) {
		try {
			Connection con = getConnection();
			String sql = "UPDATE disciplinas SET nome = ? WHERE id = ?";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, d.getNome());
			stmt.setLong(2, d.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}