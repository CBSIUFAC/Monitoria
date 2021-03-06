package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Edital;
import entity.EditalDisciplina;

public class EditalDisciplinaDAO extends MasterDAO {
	
	public void inserirEditalDisciplina(EditalDisciplina editalDisciplina){
		inserirObjeto(editalDisciplina);
	}
	
	public void deletarEditalDisciplina(EditalDisciplina editalDisciplina){
		deletarObjeto(editalDisciplina);
	}
	
	public void atualizarEditalDisciplina(EditalDisciplina editalDisciplina){
		atualizarObjeto(editalDisciplina);
	}
	
	public EditalDisciplina getEditalDisciplina(int idEditalDisciplina){
		return getObjeto(EditalDisciplina.class, idEditalDisciplina);
	}
	
	public List<EditalDisciplina> getListaEditalDisciplina(){
		return getLista("from EditalDisciplina ed");
	}
	
	//Busca de editalDisciplina pelo id total ou parte dele.
	public List<EditalDisciplina> buscaEditalDisciplina(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from EditalDisciplina e where e.idEditalDisciplina like :ed");
		qr.setParameter("%"+str+"%", "ed");
		List<EditalDisciplina> listaEditalDisciplina = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaEditalDisciplina;
	}

	public List<EditalDisciplina> getListaPorEdital(Edital edital) {
		if (edital == null)
			return new ArrayList<EditalDisciplina>();
		return getLista("from EditalDisciplina ed where ed.edital.idEdital = "+edital.getIdEdital());
	}

}
