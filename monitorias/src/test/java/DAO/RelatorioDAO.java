package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Inscricao;
import entity.Relatorio;
import entity.Usuario;

public class RelatorioDAO extends MasterDAO {
	
	public void inserirRelatorio(Relatorio relatorio){
		inserirObjeto(relatorio);
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
		return getLista("from Relatorio r");
	}
	
	//Busca de relatório por data aproximada: ideal seria aqui utilizar duas datas como parâmetros 
	//para listar todos os relatorios que estão dentro daquele espaço de tempo, ou fazer isso em outro método.
	//Enquanto a decisão não é tomada, essa busca é feita pelo ID ou parte dele.
	public List<Relatorio> buscaRelatorio(int id){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Relatorio r where r.id like :re");
		qr.setParameter("%"+id+"%", "re");
		List<Relatorio> listaRelatorio = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaRelatorio;
	}

	public List<Relatorio> getListaPorUsuario(Usuario usu){
		Session s = getSession();
		s.beginTransaction();
		System.out.println("aqui foi");
		Query qr = s.createQuery("select r from Relatorio r join r.inscricao i WHERE i.aluno.matricula like '%"+usu.getAluno().getMatricula()+"%'");
		System.out.println("passou aqui");
		System.out.println(qr);
		List<Relatorio> listaRelatorios = qr.list();
		s.getTransaction().commit();
		s.close();
		System.out.println("Ainda pegou a lista poha");
		return listaRelatorios;
	}
	
}
