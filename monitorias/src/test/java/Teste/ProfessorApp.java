package Teste;

import java.util.Date;
import java.util.List;

import DAO.ProfessorDAO;
import entity.Professor;

public class ProfessorApp {

	public static void main(String[] args) {
		
		ProfessorDAO professorDAO = new ProfessorDAO();
		Professor professor = new Professor();
		ProfessorApp professorApp = new ProfessorApp();
		
		
		professor.setCpf(0);
		professor.setNome("Professor Teste");
		professor.setRg(0);
		professorDAO.inserirProfessor(professor);
	
		professorApp.imprime();
		
		professor.setNome("Vitor Lucas Cordovil");
		professor.setRg(1131346);
		
		professorDAO.atualizarProfessor(professor);
	
		professorApp.imprime();
		
		
		professorDAO.deletarProfessor(professor);
		
		professorApp.imprime();
		
	}
	
	public void imprime(){
		ProfessorDAO professorDAO = new ProfessorDAO();		
		List<Professor> professores = professorDAO.getListaProfessor();
		if (professores.size() == 0)
			System.out.println("Não há professor cadastrado no banco de dados.");
		else{
			System.out.println("Imprimindo Professor:");	
			for (Professor a : professores) {
				System.out.println(a);
			}
		
			System.out.println("\n");
		}
	}

}
