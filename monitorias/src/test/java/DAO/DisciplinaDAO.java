package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Centro;
import entity.Disciplina;
import entity.Edital;

public class DisciplinaDAO extends MasterDAO {
	
	public void inserirDisciplina(Disciplina disciplina){
		inserirObjeto(disciplina);
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
		return getLista("from Disciplina d where d.codigoDisciplina like '%CN6%'");
	}
	
	public List<Disciplina> getListaDisciplinaPorCentro(int ano, int periodo, Centro centro){		
		return getLista("from Disciplina d where d.centro.idCentro = "+centro.getIdCentro()+" and d.periodo = "+ periodo +"and d.ano = "+ano);
	}
	
	//Busca de disciplina por nome
	public List<Disciplina> buscaDisciplina(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Disciplina e where e.nome like :no");
		qr.setParameter("no","%"+str+"%");
		List<Disciplina> listaDisciplina = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaDisciplina;
	}

}
