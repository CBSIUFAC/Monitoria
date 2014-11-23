package util;

import java.util.Date;

import org.hibernate.Session;

import entity.Aluno;
import entity.Disciplina;
import entity.Edital;
import entity.Inscricao;
import entity.Professor;

public class InsereDados {

	public static void main(String[] args) {
		//Commit online
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		//aluno
		
		Aluno aluno = new Aluno();
		Date dataNascimento = new Date(new String("09/05/1995"));
		
		aluno.setNome("João Doido");
		aluno.setCpf(77777);
		aluno.setDataNascimento(dataNascimento);
		aluno.setMatricula(122234566);
		aluno.setRg(1442345);
		s.save(aluno);


		//professor
		
		Professor professor = new Professor();
		
		professor.setCpf(123445);
		professor.setNome("Clebes Brandão");
		professor.setRg(12345);
		s.save(professor);
		
		//Disciplina
		
		Disciplina disciplina = new Disciplina();
		
		disciplina.setCargaHoraria(50);
		disciplina.setCodigo("CCET45");
		disciplina.setNome("Matemática Elementar");
		s.save(disciplina);
		
		//Edital
		
		Edital edital = new Edital();
		
		edital.setDataInscricao(dataNascimento);
		edital.setDataResultado(dataNascimento);
		edital.setIdEdital(6);
		edital.setSrcPDF("teste");
		edital.setTitulo("Edital tunts tunts quero ve");
		edital.setTotalVagas(15);
		s.save(edital);
		
		//Inscrição
		
		Inscricao inscricao = new Inscricao();
		
		inscricao.setDataInscricao(dataNascimento);
		inscricao.setIdInscricao(2);
		inscricao.setEdital(edital);
		inscricao.setAluno(aluno);
		inscricao.setDisciplina(disciplina);
		s.save(inscricao);
		s.getTransaction().commit();
		
	}
}
