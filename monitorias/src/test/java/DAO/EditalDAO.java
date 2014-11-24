package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Edital;

public class EditalDAO extends MasterDAO {
	public void inserirEdital(Edital edital){
		inserirEdital(edital);
	}
	
	public void deletarEdital(Edital edital){
		deletarObjeto(edital);
	}
	
	public void atualizarEdital(Edital edital){
		atualizarObjeto(edital);
	}
	
	public Edital getEdital(int idEdital){
		return getObjeto(Edital.class, idEdital);
	}
	
	public List<Edital> getListaEdital(){
		return getLista("from Edital m");
	}
	
	//Busca de edital por titulo
	public List<Edital> buscaEdital(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Edital m where m.titulo like :mo");
		qr.setParameter("%"+str+"%", "mo");
		List<Edital> listaEdital = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaEdital;
	}

}
