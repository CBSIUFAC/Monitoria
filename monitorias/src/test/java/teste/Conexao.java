package teste;

//importando as classes da api JDBC
import java.sql.*;
import com.ibm.db2.jcc.*;

//Classe Conexao
class Conexao {
	private Connection con = null;
	private Statement smt = null;
	private boolean conectado = false;


	// Método para conectar-se ao banco
	public boolean conecte(String url, String usuario, String senha){
		if(conectado==false) {
			try {
				con = DriverManager.getConnection(url, usuario, senha);
				smt = con.createStatement();
				conectado = true;
			} catch(SQLException sqle){
				System.out.printf("Erro: #%d [%s]\n", 
						sqle.getErrorCode(), sqle.getMessage());
						conectado = false;
			}
			return conectado;
		} else {
			System.out.println("O banco de dados já esta conectado.");
			return conectado;
		}
   }

	// Método para desconectar-se ao banco
	public boolean desconecte(){
		
		if(conectado) {
			try {
				con.close();
				System.out.printf("Conexão com o banco encerrada!\n");
				conectado = false;
				return conectado;
			}catch(SQLException sqle){
				System.out.printf("Erro: #%d [%s]\n", 
						sqle.getErrorCode(), sqle.getMessage());
		   }
		} else {
			System.out.println("O banco já está desconectado.");
		}
		return conectado;
	}

	// Método para executar instruções SQL de consulta
	public ResultSet consulte(String stringQuery){
		if(conectado) {
			try {
				smt = con.createStatement();
				return smt.executeQuery(stringQuery);
			}catch(SQLException sqle){
				System.out.printf("Erro: #%d [%s]\n", 
						sqle.getErrorCode(), sqle.getMessage());
						return null;
			}
		} else {
			System.out.println("Banco de dados desconectado, por favor conecte.");
			return null;
		}
			}
	
	public int atualize(String stringAtualize) {
		if(conectado) {
			try {
				smt = con.createStatement();
				return smt.executeUpdate(stringAtualize);
			} catch (SQLException sqle) {
				System.out.printf("Erro: #%d [%s]\n", sqle.getErrorCode(), sqle.getMessage());
				return 0;
			}
		} else {
		return 0;
		}
	}
		
	
	public boolean getConectado() {
		return conectado;
	}
	
	
}//Fim da classe Conexao