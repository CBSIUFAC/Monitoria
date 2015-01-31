package DAO;

import java.util.List;

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

}
