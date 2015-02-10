package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Aluno;
import entity.Usuario;

public class AlunoDAO extends MasterDAO{
	
	public void inserirAluno(Aluno aluno){
		inserirObjeto(aluno);
	}
	
	public void deletarAluno(Aluno aluno){
		deletarObjeto(aluno);
	}
	
	public void atualizarAluno(Aluno aluno){
		atualizarObjeto(aluno);
	}
	
	public Aluno getAluno(int idAluno){
		return getObjeto(Aluno.class, idAluno);
	}
	
	public List<Aluno> getListaAluno(){
		return getLista("from Aluno a ");
	}
	
	//Busca de aluno por nome
	public List<Aluno> buscaAluno(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Aluno a where a.nome LIKE :no");
		qr.setParameter("no", "%"+str+"%");
		List<Aluno> listaAluno = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaAluno;
	}
	
	public Aluno buscaAlunoPorCpf(String cpf){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Aluno a where a.cpf like '%"+cpf+"%'");
		List<Aluno> listaUsuarios = qr.list();
		s.getTransaction().commit();
		s.close();
		
		if (listaUsuarios.size()>0)
			return listaUsuarios.get(0);
		else
			return null;
	}
}
