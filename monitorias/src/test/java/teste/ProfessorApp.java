package teste;

import java.util.Date;
import java.util.List;

import DAO.ProfessorDAO;
import entity.Professor;

public class ProfessorApp {
	
	public static void main (String[] args ){
		
		ProfessorApp professorApp = new ProfessorApp();
		professorApp.imprime();
		
	}
	
	public void imprime() {
		ProfessorDAO professorDAO = new ProfessorDAO();		
		List<Professor> professores = professorDAO.getListaProfessor();
		if (professores.size() == 0)
			System.out.println("Não há professor cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Professor(es):");
			for (Professor a : professores) {
				System.out.println(a);
			}
			System.out.println("\n");
		}
	}
}
