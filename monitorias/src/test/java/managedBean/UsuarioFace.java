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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import entity.Aluno;
import entity.Usuario;
import DAO.AlunoDAO;
import DAO.UsuarioDAO;
@ManagedBean(name="usuarioFace")
@SessionScoped
public class UsuarioFace {
	private UsuarioDAO usuDAO = new UsuarioDAO();
	private Usuario usu = new Usuario();
	private String cpf = new String();
	
	public UsuarioFace(){  
        usu = new Usuario();  
        SecurityContext context = SecurityContextHolder.getContext();  
        if(context instanceof SecurityContext)  
        {  
            Authentication authentication = context.getAuthentication();  
            if(authentication instanceof Authentication){
            	String aux = ((User)authentication.getPrincipal()).getUsername();
            	System.out.println(aux);
                usu = usuDAO.getUsuario(aux);
            }  
        }  
    }  
	
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

//	public String inserirUsuario(){
//		AlunoDAO alunoDAO = new AlunoDAO();
//		System.out.println(cpf);
//		Aluno a = alunoDAO.getAlunoPorCPF(cpf);
//		
//		usu.setAluno(a);
//		usu.setPassword(criptografarSenha(usu.getPassword()));
//		
//		System.out.println("Usuario: "+usu);
//		System.out.println("Aluno: "+a);
//		
//		usuDAO.inserirUsuario(usu);
//		
//		listaUsuario = null;
//		usu=null;
//		return "usuarios";
//	}
//	
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

	public UsuarioDAO getUsuDAO() {
		return usuDAO;
	}

	@Override
	public String toString() {
		return "UsuarioFace [usuDAO=" + usuDAO + ", usu=" + usu
				+ ", listaUsuario=" + listaUsuario
				+ ", senhaCriptografada=" + senhaCriptografada + "]";
	}

	public void setUsuDAO(UsuarioDAO usuDAO) {
		this.usuDAO = usuDAO;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}