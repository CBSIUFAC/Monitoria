package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Aluno;

public class AlunoDAO extends MasterDAO{
	
	public void inserirAluno(Aluno aluno){
		inserirAluno(aluno);
	}
	
	public void deletarAluno(Aluno aluno){
		deletarObjeto(aluno);
	}
	
	public void atualizarAluno(Aluno aluno){
		atualizarObjeto(aluno);
	}
	
	public Aluno getAluno(int idAluno){
		return getObjeto(Aluno.class, idAluno);
	}
	
	public List<Aluno> getListaAluno(){
		return getLista("from Aluno m");
	}
	
	//Busca de aluno por nome
	public List<Aluno> buscaAluno(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Aluno a where a.nome like :mo");
		qr.setParameter("%"+str+"%", "mo");
		List<Aluno> listaAluno = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaAluno;
	}
}
