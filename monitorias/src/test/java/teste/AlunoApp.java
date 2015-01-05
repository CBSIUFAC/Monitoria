package teste;

import java.util.Date;
import java.util.List;

import DAO.AlunoDAO;
import entity.Aluno;

public class AlunoApp {
	
	public static void main (String[] args ){
		
		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();
		AlunoApp alunoApp = new AlunoApp();
		
//		aluno.setCpf(333);
//		aluno.setNome("João");
//		aluno.setDataNascimento(new Date(new String("01/01/1090")));
//		aluno.setMatricula(111);
//		aluno.setRg(1111);
		
//		alunoDAO.inserirAluno(aluno);

		alunoApp.imprime();
		
//		aluno.setNome("Vitor Lucas Cordovil");
//		aluno.setDataNascimento(new Date(new String("27/02/1994")));
//		aluno.setMatricula(20120300);
//		aluno.setRg(1131346);
//		
//		alunoDAO.atualizarAluno(aluno);
//		alunoApp.imprime();
//		alunoDAO.deletarAluno(aluno);
//		alunoApp.imprime();

		
	}
	
	public void imprime(){
		AlunoDAO alunoDAO = new AlunoDAO();		
		List<Aluno> alunos = alunoDAO.getListaAluno();
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
