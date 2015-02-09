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
		Aluno aluno = alunoDAO.buscaAlunoPorCpf("526.116.042-15");
		
		System.out.println("Imprimindo Aluno:");	
		System.out.println(aluno);


	}
}
