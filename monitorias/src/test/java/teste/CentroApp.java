package teste;

import java.util.List;

import DAO.CentroDAO;
import entity.Centro;

public class CentroApp {
	
public static void main (String[] args ){
		
		CentroDAO centroDAO = new CentroDAO();
		Centro centro = new Centro();
		CentroApp centroApp = new CentroApp();
	
		centroApp.imprime();		
	}
	
	public void imprime(){
		CentroDAO centroDAO = new CentroDAO();		
		List<Centro> centros = centroDAO.getListaCentro();
		if (centros.size() == 0)
			System.out.println("Não há centro cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Centro(s):");	
			for (Centro c : centros) {
				System.out.println(c);
			}	
			System.out.println("\n");
		}
	}
}
