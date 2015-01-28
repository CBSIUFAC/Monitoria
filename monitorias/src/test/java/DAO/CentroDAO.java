package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Centro;

public class CentroDAO extends MasterDAO {
	
	public void inserirCentro(Centro centro){
		inserirObjeto(centro);
	}
	
	public void deletarCentro(Centro centro){
		deletarObjeto(centro);
	}
	
	public void atualizarCentro(Centro centro){
		atualizarObjeto(centro);
	}
	
	public Centro getCentro(int idCentro){
		return getObjeto(Centro.class, idCentro);
	}
	
	public List<Centro> getListaCentro(){
		
		List<Centro> centros = getLista("from Centro c where c.nomeCentro like '%Centro%'");
		if (centros.isEmpty()){
			return new ArrayList<Centro>();
		}else
			return centros;
	}
	
	//Busca de centro por nome
	public List<Centro> buscaCentro(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Centro c where c.nome like :no");
		qr.setParameter( "no","%"+str+"%");
		List<Centro> listaCentro = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaCentro;
	}
}
