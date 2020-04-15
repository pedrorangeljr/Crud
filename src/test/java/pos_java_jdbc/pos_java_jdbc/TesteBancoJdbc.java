package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDao;
import model.Userposjava;

public class TesteBancoJdbc {
	
	// metodo para inserir dados no banco
	
	@Test 
	public void initBanco() {
		
		UserPosDao userPosDao = new UserPosDao();
		
		Userposjava userposjava = new Userposjava();
		
		userposjava.setNome("Rosa");
		userposjava.setEmail("Rosa@gmail.com");
		
		userPosDao.salvar(userposjava);
		
	}
	
    // metodo para listar registros do banco de dados
	
	@Test
	public void initListar() {
		
		UserPosDao dao = new UserPosDao();
		try {
			
			List<Userposjava> list = dao.listar();
			
			for(Userposjava userposjava : list) {
				
				System.out.println(userposjava);
				System.out.println("-------------------------------------");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	
	@Test
	public void initbuscar() {
		
		UserPosDao dao = new UserPosDao();
		
		try {
			
			Userposjava userposjava = dao.buscar(5L);
			System.out.println(userposjava);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void initAtualizar() {
		
		try {
			
		UserPosDao dao = new UserPosDao();
		
		Userposjava objetoBanco = dao.buscar(5l);
		objetoBanco.setNome("Nome mudado com objeto atualizar");
		dao.atualizar(objetoBanco);
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void initDeletar() {
		
		try {
			
			UserPosDao dao = new UserPosDao();
			dao.deletar(8L);
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	

}
