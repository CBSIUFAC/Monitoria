package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Disciplina;

public class DisciplinaDAO extends MasterDAO {
	
	public void inserirDisciplina(Disciplina disciplina){
		inserirDisciplina(disciplina);
	}
	
	public void deletarDisciplina(Disciplina disciplina){
		deletarObjeto(disciplina);
	}
	
	public void atualizarDisciplina(Disciplina disciplina){
		atualizarObjeto(disciplina);
	}
	
	public Disciplina getDisciplina(int idDisciplina){
		return getObjeto(Disciplina.class, idDisciplina);
	}
	
	public List<Disciplina> getListaDisciplina(){
		return getLista("from Disciplina m");
	}
	
	//Busca de disciplina por nome
	public List<Disciplina> buscaDisciplina(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Disciplina m where m.nome like :mo");
		qr.setParameter("%"+str+"%", "mo");
		List<Disciplina> listaDisciplina = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaDisciplina;
	}

}
