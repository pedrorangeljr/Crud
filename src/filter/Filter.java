package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import conexao.SingleConnection;


@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

	private Connection connection;
   
    public Filter() {
        
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			chain.doFilter(request, response);
			
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

	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnection.getConnection();
	}

}
