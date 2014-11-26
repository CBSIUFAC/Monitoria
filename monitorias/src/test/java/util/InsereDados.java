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
	
		Aluno aluno = new Aluno();
		aluno.setNome("Manoel Limeira");
		aluno.setCpf(122334);
		aluno.setDataNascimento(new Date(new String("09/05/1995")));
		aluno.setMatricula(204530);
		aluno.setRg(124556);

		AlunoDAO alunoDAO = new AlunoDAO();	
		//alunoDAO.inserirAluno(aluno);
		String str = "oe";
		List<Aluno> alunos = alunoDAO.buscaAluno(str);
		
		for (Aluno a : alunos) {
			System.out.println(a.getNome());
		}
	}
}
