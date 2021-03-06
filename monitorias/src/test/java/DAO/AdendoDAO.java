package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Adendo;

public class AdendoDAO extends MasterDAO {
	
	public void inserirAdendo(Adendo adendo){
		inserirObjeto(adendo);
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
		return getLista("from Adendo a");
	}
	
	//Busca de adendo por titulo
	public List<Adendo> buscaAdendo(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Adendo a where a.titulo like :ti");
		qr.setParameter("%"+str+"%", "ti");
		List<Adendo> listaAdendo = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaAdendo;
	}
}
