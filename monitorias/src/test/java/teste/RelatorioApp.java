package teste;

import java.sql.Time;
import java.util.List;

import DAO.RegistroDAO;
import DAO.RelatorioDAO;
import entity.Registro;
import entity.Relatorio;

public class RelatorioApp {
	
public static void main (String[] args ){
		
		RegistroDAO registroDAO = new RegistroDAO();
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		
		Relatorio relatorio = relatorioDAO.getRelatorio(18);
		
		RelatorioApp app = new RelatorioApp();

		app.imprime(relatorio);
		
		
}
	
	public void imprime(Relatorio r){
		RegistroDAO registroDAO = new RegistroDAO();;		
		List<Registro> registros = registroDAO.getListaPorRelatorio(r);
		if (registros.size() == 0)
			System.out.println("Não há relatorio cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Relatorio(s):");	
			for (Registro a : registros) {
				System.out.println(a);
			}	
			System.out.println("\n");
		}
	}


}
