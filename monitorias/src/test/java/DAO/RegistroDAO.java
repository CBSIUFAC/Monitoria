package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Registro;
import entity.Relatorio;
import entity.Usuario;

public class RegistroDAO extends MasterDAO {
	
	public void inserirRegistro(Registro registro){
		inserirObjeto(registro);
	}
	
	public void deletarRegistro(Registro registro){
		deletarObjeto(registro);
	}
	
	public void atualizarRegistro(Registro registro){
		atualizarObjeto(registro);
	}
	
	public Registro getRegistro(int idRegistro){
		return getObjeto(Registro.class, idRegistro);
	}
	
	public List<Registro> getListaRegistro(){
		return getLista("from Registro r");
	}
	
	public List<Registro> buscaRegistro(int id){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Registro r where r.idRegistro like :re");
		qr.setParameter("%"+id+"%", "re");
		List<Registro> listaRegistro = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaRegistro;
	}

	//WHERE rel.idRelatorio like '%"+r.getIdRelatorio()+"%'"
	
	public List<Registro> getListaPorRelatorio(Relatorio r){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("select r from Registro r join r.relatorio l WHERE r.relatorio.idRelatorio = "+r.getIdRelatorio());
		System.out.println(qr);
		List<Registro> listaRegistros = qr.list();
		s.getTransaction().commit();
		s.close();
		System.out.println("Ainda pegou a lista poha");
		return listaRegistros;
	}
}
