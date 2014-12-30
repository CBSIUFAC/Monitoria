package teste;

import java.util.List;

import DAO.CentroDAO;
import entity.Centro;

public class CentroApp {
	
public static void main (String[] args ){
		
		CentroDAO centroDAO = new CentroDAO();
		Centro centro = new Centro();
		CentroApp centroApp = new CentroApp();
		
		centro.setSigla("CCET");
		centro.setNome("CENTRO DE CI�NCIAS");
		centro.setNomeDiretor("Jos� Augusto Ferreira");
		centroDAO.inserirCentro(centro);
	
		centroApp.imprime();
		
		centro.setNome("CENTRO DE CI�NCIAS EXATAS E TECNOL�GICAS");
		centro.setNomeDiretor("Claudionor Alencar");
		centroDAO.atualizarCentro(centro);
	
		centroApp.imprime();
		//centroDAO.deletarCentro(centro);
		centroApp.imprime();		
	}
	
	public void imprime(){
		CentroDAO centroDAO = new CentroDAO();		
		List<Centro> centros = centroDAO.getListaCentro();
		if (centros.size() == 0)
			System.out.println("N�o h� centro cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Centro(s):");	
			for (Centro c : centros) {
				System.out.println(c);
			}	
			System.out.println("\n");
		}
	}
}
