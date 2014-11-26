package Teste;

import java.util.Date;
import java.util.List;

import DAO.AlunoDAO;
import entity.Aluno;

public class AlunoApp {
	
	public static void main (String[] args ){
		
		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();
		AlunoApp alunoApp = new AlunoApp();
		
		
		aluno.setCpf(1111);
		aluno.setNome("Lucas");
		aluno.setDataNascimento(new Date(new String("01/01/1090")));
		aluno.setMatricula(111);
		aluno.setRg(1111);
		alunoDAO.inserirAluno(aluno);
	
		alunoApp.imprime();
		
		aluno.setNome("Vitor Lucas Cordovil");
		aluno.setDataNascimento(new Date(new String("27/02/1994")));
		aluno.setMatricula(20120300);
		aluno.setRg(1131346);
		alunoDAO.atualizarAluno(aluno);
	
		alunoApp.imprime();
		
		alunoDAO.deletarAluno(aluno);
		
		alunoApp.imprime();
		
	}
	
	public void imprime(){
		AlunoDAO alunoDAO = new AlunoDAO();		
		List<Aluno> alunos = alunoDAO.getListaAluno();
			
		for (Aluno a : alunos) {
			System.out.println(a);
		}
	}

}
