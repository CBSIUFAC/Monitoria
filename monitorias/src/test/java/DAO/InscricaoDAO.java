package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Inscricao;
import entity.Usuario;

public class InscricaoDAO extends MasterDAO {
	
	public void inserirInscricao(Inscricao inscricao){
		inserirObjeto(inscricao);
	}
	
	public void deletarInscricao(Inscricao inscricao){
		deletarObjeto(inscricao);
	}
	
	public void atualizarInscricao(Inscricao inscricao){
		System.out.println("RES: "+inscricao);
		atualizarObjeto(inscricao);
	}
	
	public Inscricao getInscricao(int idInscricao){
		return getObjeto(Inscricao.class, idInscricao);
	}
	
	public List<Inscricao> getListaInscricao(){
		return getLista("from Inscricao i");
	}
	
	public boolean estaInscrito(Inscricao i){
		
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("FROM Inscricao WHERE fkAluno= "+i.getAluno().getMatricula()+" and fkDisciplina="+ i.getDisciplina().getId() +" and fkEdital ="+ i.getEdital().getIdEdital());
		List<Inscricao> listaInscricao = qr.list();
		
		s.getTransaction().commit();
		s.close();
		
		boolean result = false;
		
		if (listaInscricao.size()>=1)
			result = true;
		
		return result;
		
	}
	
	public List<Inscricao> getListaPorUsuario(Usuario usu){
		Session s = getSession();
		s.beginTransaction();
		//("from Inscricao i where i.fkAluno like '%"+usu.getAluno().getMatricula()+"%'")
		Query qr = s.createQuery("from Inscricao WHERE fkAluno like '%"+usu.getAluno().getMatricula()+"%'");
		System.out.println(qr);
		List<Inscricao> listaInscricao = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaInscricao;
	}
	
	public List<Inscricao> buscaInscricao(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Inscricao i where i.idInscricao like :in");
		qr.setParameter("%"+str+"%", "in");
		List<Inscricao> listaInscricao = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaInscricao;
	}

}
