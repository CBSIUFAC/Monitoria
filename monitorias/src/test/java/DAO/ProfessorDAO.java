package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Professor;

public class ProfessorDAO extends MasterDAO{
	
	public void inserirProfessor(Professor professor){
		inserirObjeto(professor);
	}
	
	public void deletarProfessor(Professor professor){
		deletarObjeto(professor);
	}
	
	public void atualizarProfessor(Professor professor){
		atualizarObjeto(professor);
	}
	
	public Professor getProfessor(int idProfessor){
		return getObjeto(Professor.class, idProfessor);
	}
	
	public List<Professor> getListaProfessor(){
		return getLista("from Professor a WHERE cpf is not null");
	}
	
	//Busca de professor por nome
	public List<Professor> buscaProfessor(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Professor a where a.nome LIKE :no");
		qr.setParameter("no", "%"+str+"%");
		List<Professor> listaProfessor = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaProfessor;
	}
}
