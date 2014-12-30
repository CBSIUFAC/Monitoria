package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Aluno;
import entity.AlunoAcademico;

public class AlunoAcademicoDAO extends MasterDAO{
	
	public void inserirAlunoAcademico(AlunoAcademicoDAO alunoAcademico){
		inserirObjeto(alunoAcademico);
	}
	
	public void deletarAluno(AlunoAcademicoDAO alunoAcademico){
		deletarObjeto(alunoAcademico);
	}
	
	public void atualizarAluno(AlunoAcademicoDAO alunoAcademico){
		atualizarObjeto(alunoAcademico);
	}
	
	public AlunoAcademico getAlunoAcademico(int idAluno){
		return getObjeto(AlunoAcademico.class, idAluno);
	}
	
	public List<AlunoAcademico> getListaAlunoAcademico(){
		return getLista("from dbsm.ACAD_ALUNOS a");
	}
	
	//Busca de aluno por nome
	public List<AlunoAcademico> buscaAlunoAcademico(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from AlunoAcademico a where a.nome LIKE :no");
		qr.setParameter("no", "%"+str+"%");
		List<AlunoAcademico> listaAlunoACademico = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaAlunoACademico;
		
	}
}
