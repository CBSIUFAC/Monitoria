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
	
	//Busca de inscricao por data aproximada: ideal seria aqui utilizar duas datas como par�metros 
	//para listar todos os editais que est�o dentro daquele espa�o de tempo, ou fazer isso em outro m�todo.
	//Enquanto a decis�o n�o � tomada, essa busca � feita pelo ID ou parte dele.
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
