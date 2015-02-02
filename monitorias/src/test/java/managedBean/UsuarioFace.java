package managedBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.RowEditEvent;

import entity.Aluno;
import entity.Usuario;
import DAO.AlunoDAO;
import DAO.UsuarioDAO;
@ManagedBean(name="usuarioFace")
@SessionScoped
public class UsuarioFace {
	UsuarioDAO usuDAO = new UsuarioDAO();
	Usuario usu = new Usuario();
	
	public Usuario getUsu() {
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	List<Usuario> listaUsuario = null;

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String inserirUsuario(){
		AlunoDAO a = new AlunoDAO();
		usu.setAluno(a.getAluno(12886));
		usu.setPassword(criptografarSenha(usu.getPassword()));
		usuDAO.inserirUsuario(usu);
		listaUsuario = null;
		usu=null;
		return "usuarios";
	}
	public void atualizarUsuario(RowEditEvent event){
		usu = (Usuario) event.getObject();
		usuDAO.atualizarUsuario(usu);
		FacesMessage msg = new FacesMessage("Usuario Editado", ((Usuario) event.getObject()).getUsername());  
		FacesContext.getCurrentInstance().addMessage(null, msg); 
	}

	public String deletarUsuario() throws ConstraintViolationException, Exception{
		try{  
			usuDAO.deletarUsuario(usu);
			listaUsuario = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Excluído com Sucesso");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch (ConstraintViolationException ce){  
			FacesContext msg = FacesContext.getCurrentInstance();  
			msg.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Atenção", ""));  
		}catch(Exception e){
			FacesContext msg = FacesContext.getCurrentInstance();  
			msg.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso", "Instabilidades, aguarde alguns segundos e tente novamente."));
		}
		return "usuarios";
	}



	public void cancelar(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Cancelado");  
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Usuario> getListaUsuario(){
		if(listaUsuario==null)
			listaUsuario = usuDAO.getListaUsuario();
		return listaUsuario;
	}
	
	
//  criptografia "caelum"
	private String senhaCriptografada;

	public String criptografarSenha(String senha){
		String sc = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02x", b));
			}
			sc = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sc;
	}
	
	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}
	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}
}