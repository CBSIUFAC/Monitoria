package teste;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

// Classe ConexaoApp
class TesteBanco {

	// Método main inicia execução do aplicativo
	public static void main(String args[]){
		
		// Criando o objeto cnx do tipo Conexao.
		Conexao cnx =  new Conexao();
		Boolean autenticou = false;
		
		//Autenticação no sistema
		
		Scanner ler = new Scanner(System.in);
		String login, senha;
		
		for(int i=0; i<3; i++) {
			
			System.out.println();
			System.out.println("Informe o login do banco: ");
			login = ler.nextLine();
			System.out.println("Informe a senha do banco: ");
			senha = ler.nextLine();
			System.out.println();

			cnx.conecte("jdbc:db2://192.168.1.25:50000/dbsm", login, senha);		
			
			if(cnx.getConectado()) {
				System.out.println("Seja Bem Vindo ao JosinoSGBD!");
				//Conectando ao banco de dados
				
				System.out.println("Digite um comando SQL ou digite EXIT para sair: ");
				String controlador = ler.nextLine();
				
					while(!(controlador.equals("exit"))) {
					System.out.println(controlador);
						//Criando resultset e derivados
						ResultSet rs = null;
						ResultSetMetaData rsmd;
						int numeroColunas;
						
						// Consultando o banco de dados!
						rs = cnx.consulte(controlador);
						if(rs!=null) {
						
							try {
							
								rsmd = rs.getMetaData();
								numeroColunas = rsmd.getColumnCount();
								System.out.println("");
								System.out.println("");
								for(int k=1; k<=numeroColunas; k++) {
									System.out.printf("%-8s\t", rsmd.getColumnLabel(k));
								}
							
								System.out.println();
								
									while(rs.next()) {
	
										for(int l=1; l<=numeroColunas; l++) {
											System.out.printf("%-8s\t", rs.getObject(l));	
										}
										System.out.println();
										System.out.println("");
										System.out.println("");
									}
							
							} catch (SQLException sqle) {
								System.out.println();
								System.out.printf("Erro: #%d [%s]\n", sqle.getErrorCode(), sqle.getMessage());
						}
				}
					System.out.println("Digite um comando SQL ou digite EXIT para sair: ");
					System.out.println();
					controlador = ler.nextLine();
			}
				
			System.out.println("Obrigado por usar o SGBD do Josino.");
			cnx.desconecte();
		    break;
		    	
			} else {
				System.out.println("Tente logar novamente.");
			}
		}	
		
		if(!autenticou) {
			System.out.println("Você errou as 3 tentativas de login, inicialize o sistema novamente.");		
		}
		
   } 
}


