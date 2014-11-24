package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Relatorio;

public class RelatorioDAO extends MasterDAO {
	
	public void inserirRelatorio(Relatorio relatorio){
		inserirRelatorio(relatorio);
	}
	
	public void deletarRelatorio(Relatorio relatorio){
		deletarObjeto(relatorio);
	}
	
	public void atualizarRelatorio(Relatorio relatorio){
		atualizarObjeto(relatorio);
	}
	
	public Relatorio getRelatorio(int idRelatorio){
		return getObjeto(Relatorio.class, idRelatorio);
	}
	
	public List<Relatorio> getListaRelatorio(){
		return getLista("from Relatorio m");
	}
	
	//Busca de relat�rio por data aproximada: ideal seria aqui utilizar duas datas como par�metros 
	//para listar todos os relatorios que est�o dentro daquele espa�o de tempo, ou fazer isso em outro m�todo.
	//Enquanto a decis�o n�o � tomada, essa busca � feita pelo ID ou parte dele.
	public List<Relatorio> buscaRelatorio(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Relatorio r where r.nome like :mo");
		qr.setParameter("%"+str+"%", "mo");
		List<Relatorio> listaRelatorio = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaRelatorio;
	}

}
