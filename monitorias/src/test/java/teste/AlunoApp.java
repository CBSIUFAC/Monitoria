package teste;

import java.util.Date;
import java.util.List;

import DAO.AlunoDAO;
import entity.Aluno;

public class AlunoApp {
	
	public static void main (String[] args ){
		
		AlunoApp alunoApp = new AlunoApp();
		
		alunoApp.imprime();
	}
	
	
	public void imprime() {
		AlunoDAO alunoDAO = new AlunoDAO();		
		List<Aluno> alunos = alunoDAO.buscaAlunoPorCpf("526.116.042-15");
		if (alunos.size() == 0)
			System.out.println("Não há aluno cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Aluno(s):");	
			for (Aluno a : alunos) {
				System.out.println(a);
			}
			System.out.println("\n");
		}
	}
}
