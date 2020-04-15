/* Classe modelo responsavel
 * pela operações no banco de dados, ou seja,
 * será escrito codigo sql nas classes java*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDao {

	// Estabelece a conexão com banco de dados.

	private Connection connection;

	public UserPosDao() {

		// instancia a conexão para fazer as operações de sql
		connection = SingleConnection.getconnection();
	}

	public void salvar(Userposjava userposjava) {

		try {

			String sql = "insert into userposjava(nome, email) values( ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql); // retorna de dentro de uma conexão passado o
																			// sql que vai para dentro do banco
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute(); // executa no banco
			connection.commit();// salva no banco
			System.out.println("Salvo com Sucesso");

		} catch (Exception e) {

			try {

				connection.rollback(); // reverte operação

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	/* metodo que de consulta que retorna uma lista de dados */
	public List<Userposjava> listar() throws Exception {

		// Instancia a lista
		List<Userposjava> list = new ArrayList<Userposjava>();
		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery(); // executo no banco de dados

		while (resultado.next()) { // faz a interação com o resultado

			Userposjava userposjava = new Userposjava();
			list.add(userposjava);
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
		}

		return list;

	}
	
	
	public Userposjava buscar(Long id) throws Exception {

		// Instancia a lista
		Userposjava retorno = new Userposjava();
		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery(); // executo no banco de dados

		while (resultado.next()) { // retona um ou nenhum

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}

		return retorno;

	}
	
	

	// metodo Atualizar

	public void atualizar(Userposjava userposjava) {

		try {

			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, userposjava.getNome());
			statement.execute();
			connection.commit();

		} catch (Exception e) {

			try {

				connection.rollback();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	// metodo delete
	
	public void deletar(Long id) {
		
		try {
			
			String sql = "delete from userposjava where id =  " + id;
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.execute();
			connection.commit();
			System.out.println("Deletou com Sucesso");
			
		}catch(Exception e) {
			
			try {
				
				connection.rollback();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
