package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Inscricao;

public class InscricaoDAO extends MasterDAO {
	
	public void inserirInscricao(Inscricao inscricao){
		inserirInscricao(inscricao);
	}
	
	public void deletarInscricao(Inscricao inscricao){
		deletarObjeto(inscricao);
	}
	
	public void atualizarInscricao(Inscricao inscricao){
		atualizarObjeto(inscricao);
	}
	
	public Inscricao getInscricao(int idInscricao){
		return getObjeto(Inscricao.class, idInscricao);
	}
	
	public List<Inscricao> getListaInscricao(){
		return getLista("from Inscricao m");
	}
	
	//Busca de inscricao por data aproximada: ideal seria aqui utilizar duas datas como parâmetros 
	//para listar todos os editais que estão dentro daquele espaço de tempo, ou fazer isso em outro método.
	//Enquanto a decisão não é tomada, essa busca é feita pelo ID ou parte dele.
	public List<Inscricao> buscaInscricao(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Inscricao i where i.idInscricao like :mo");
		qr.setParameter("%"+str+"%", "mo");
		List<Inscricao> listaInscricao = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaInscricao;
	}

}
