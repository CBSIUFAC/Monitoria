package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Adendo;

public class AdendoDAO extends MasterDAO {
	public void inserirAdendo(Adendo adendo){
		inserirAdendo(adendo);
	}
	
	public void deletarAdendo(Adendo adendo){
		deletarObjeto(adendo);
	}
	
	public void atualizarAdendo(Adendo adendo){
		atualizarObjeto(adendo);
	}
	
	public Adendo getAdendo(int idAdendo){
		return getObjeto(Adendo.class, idAdendo);
	}
	
	public List<Adendo> getListaAdendo(){
		return getLista("from Adendo m");
	}
	
	//Busca de adendo por titulo
	public List<Adendo> buscaAdendo(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Adendo m where m.titulo like :mo");
		qr.setParameter("%"+str+"%", "mo");
		List<Adendo> listaAdendo = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaAdendo;
	}
}
