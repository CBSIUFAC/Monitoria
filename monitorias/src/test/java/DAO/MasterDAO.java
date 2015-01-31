package DAO;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class MasterDAO {

	public Session getSession(){
		return HibernateUtil.getSessionFactory().openSession();
	}

	/* Foi adicionada uma verificação se a transação ocorreu ou não, para evitar deadlocks. */

	public void inserirObjeto(Object obj) {
		Session s = null;
		Transaction tx = null; 
		try {
			s = getSession();
			tx = s.beginTransaction();
			s.save(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			if (s != null) s.close();
		}
	}

	public void deletarObjeto(Object obj) {
		Session s = null;
		Transaction tx = null; 
		try {
			s = getSession();
			tx = s.beginTransaction();
			s.delete(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			if (s != null) s.close();
		}
	}

	public void atualizarObjeto(Object obj) {
		Session s = null;
		Transaction tx = null; 
		try {
			s = getSession();
			tx = s.beginTransaction();
			s.update(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			if (s != null) s.close();
		}
	}

	//Retorna qualquer objeto
	public <T extends Serializable> T getObjeto(Class<T> classe, int id){
		Session s = null;
		Transaction tx = null;
		Serializable retorno = null;
		try {
			s = getSession();
			tx = s.beginTransaction();
			retorno = (Serializable)s.get(classe, id);
			s.getTransaction().commit();
		} catch (Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			if(s != null) s.close();
		}
		return (T)retorno;
	}

	//Retorna lista de qualquer objeto
	public <T extends Serializable> List<T> getLista(String str){
		Session s = null;
		Transaction tx = null;
		List<T> retorno = null;
		try {
			s = getSession();
			tx = s.beginTransaction();
			Query qr = s.createQuery(str);
			retorno = qr.list();
			s.getTransaction().commit();
		} catch (Exception e) {
			if(tx!=null) tx.rollback();
		} finally {
			if( s!=null) s.close();
		}
		return retorno;
	}

	public <T extends Serializable> List<T> getLista(Class<T> classe, String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery(str);
		List<T> retorno = qr.list();
		s.getTransaction().commit();
		s.close();
		return retorno;
	}
}