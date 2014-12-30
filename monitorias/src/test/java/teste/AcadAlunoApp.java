package teste;

import java.util.List;

import DAO.AlunoAcademicoDAO;
import DAO.AlunoDAO;
import entity.Aluno;
import entity.AlunoAcademico;

public class AcadAlunoApp {
	
	public static void main (String[] args ){
		
		AlunoAcademicoDAO acdDAO = new AlunoAcademicoDAO();
		AlunoAcademico alunoAcademico = new AlunoAcademico();
		AcadAlunoApp acadAlunoApp = new AcadAlunoApp();
		//acadAlunoApp.imprime();
		
		List<AlunoAcademico> alunos = acdDAO.buscaAlunoAcademico("João Josino Sobrinho Neto");
		
		for(AlunoAcademico a : alunos ) {
			System.out.println(a);
		}
	}
	
	public void imprime(){
		AlunoAcademicoDAO acdDAO = new AlunoAcademicoDAO();	
		System.out.println("aqui");
		List<AlunoAcademico> alunos = acdDAO.getListaAlunoAcademico();
        System.out.println(alunos==null?"nulo":"não");
		if (alunos.size() == 0)
			System.out.println("Não há aluno cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Aluno(s):");	
			for (AlunoAcademico a : alunos) {
				System.out.println(a);
			}
			System.out.println("\n");
		}
	}
}
