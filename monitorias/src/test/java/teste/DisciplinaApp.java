package Teste;

import java.util.Date;
import java.util.List;

import DAO.DisciplinaDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Professor;

public class DisciplinaApp {
public static void main (String[] args ){
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		Disciplina disciplina = new Disciplina();
		DisciplinaApp disciplinaApp = new DisciplinaApp();
		Centro centro = new Centro();
		Professor professor = new Professor();
		
		centro.setSigla("CCET");
		professor.setCpf(0);
		
		disciplina.setNome("TESI II");
		disciplina.setCodigo("CCET050");
		disciplina.setCargaHoraria(30);
		disciplina.setCentro(centro);
		disciplina.setProfessor(professor);
		
		disciplinaDAO.inserirDisciplina(disciplina);
		disciplinaApp.imprime();
		
		centro.setSigla("CDT");
		professor.setCpf(10);
		
		disciplina.setNome("TÓPICOS ESPECIAIS EM SISTEMAS DE INFORMAÇÃO - II");
		disciplina.setCargaHoraria(60);
		disciplina.setCentro(centro);
		disciplina.setProfessor(professor);

		disciplinaDAO.atualizarDisciplina(disciplina);
	
		disciplinaApp.imprime();
		
		disciplinaDAO.deletarDisciplina(disciplina);
		
		disciplinaApp.imprime();
		
	}
	
	public void imprime(){
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();		
		List<Disciplina> disciplinas = disciplinaDAO.getListaDisciplina();
		if (disciplinas.size() == 0)
			System.out.println("Não há disciplina cadastrada no sistema.");
		else{
			System.out.println("Imprimindo Disciplina(s):");	
			for (Disciplina a : disciplinas) {
				System.out.println(a);
			}
		
			System.out.println("\n");
		}
	}

}
