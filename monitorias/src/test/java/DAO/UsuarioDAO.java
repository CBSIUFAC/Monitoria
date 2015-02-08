package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Inscricao;
import entity.Usuario;

public class UsuarioDAO extends MasterDAO{
	public void inserirUsuario(Usuario usu){
		inserirObjeto(usu);
	}
	public void atualizarUsuario(Usuario usu){
		atualizarObjeto(usu);
	}
	public void deletarUsuario(Usuario usu){
		deletarObjeto(usu);
	}
	public Usuario getUsuario(Integer idUsuario){
		return getObjeto(Usuario.class, idUsuario);
	}
	public List<Usuario> getListaUsuario(){
		return getLista(Usuario.class, "from Usuario u");
	}
	
	public Usuario getUsuario(String userName){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Usuario u where u.username = '"+userName+"'");
		List<Usuario> listaUsuarios = qr.list();
		s.getTransaction().commit();
		s.close();
		
		if (listaUsuarios.size()>0)
			return listaUsuarios.get(0);
		else
			return new Usuario();
	}
		
}
