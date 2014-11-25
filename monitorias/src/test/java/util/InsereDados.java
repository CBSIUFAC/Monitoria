package util;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Edital;
import entity.Inscricao;
import entity.Professor;

public class InsereDados {

	public static void main(String[] args) {
		/*
		Aluno aluno = new Aluno();
		aluno.setNome("João Neto");
		aluno.setCpf(777555);
		aluno.setDataNascimento(new Date(new String("09/05/1995")));
		aluno.setMatricula(2012030);
		aluno.setRg(123456);
		*/
		
		AlunoDAO alunoDAO = new AlunoDAO();	
		List<Aluno> alunos = alunoDAO.buscaAluno("oido");
		
		//for (Aluno a : alunos) {
			//System.out.println(a.getNome());
		//}	
	}
}
