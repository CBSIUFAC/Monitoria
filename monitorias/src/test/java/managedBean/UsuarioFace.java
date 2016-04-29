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

import DAO.AlunoDAO;
import DAO.UsuarioDAO;
import entity.Aluno;
import entity.Usuario;
@ManagedBean(name="usuarioFace")
@SessionScoped
public class UsuarioFace {

	private UsuarioDAO usuDAO = new UsuarioDAO();
	private Usuario usu;
	private Usuario novoUsuario = new Usuario();
	private String cpf = new String();

	public UsuarioFace(){  
		getUsu();
	}  

	public Usuario getUsu() {
		try{
			if (usu == null){
				usu = new Usuario();  
				SecurityContext context = SecurityContextHolder.getContext();  
				if(context instanceof SecurityContext)  
				{  
					Authentication authentication = context.getAuthentication();  
					if(authentication instanceof Authentication) {
						String aux = ((User) authentication.getPrincipal()).getUsername() == null?"É nulo":"Não é nulo";
						System.out.println(aux);
						usu = usuDAO.getUsuario(aux);
						return usu;
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("erro.");
		}

		return new Usuario();
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	List<Usuario> listaUsuario = null;

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String inserirUsuario(){
		AlunoDAO alunoDAO = new AlunoDAO();
		System.out.println(cpf);
		Aluno a = alunoDAO.buscaAlunoPorCpf(cpf);


		if (a != null){

			System.out.println("Aluno nulo");

			if (usuDAO.getUsuarioPorMatricula(a.getMatricula()) == null){
				System.out.println("Não existe login com esse aluno");
				novoUsuario.setAluno(a);
				novoUsuario.setPassword(criptografarSenha(novoUsuario.getPassword()));


				usuDAO.inserirUsuario(novoUsuario);

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Cadastro com sucesso: "+novoUsuario.getUsername());  
				FacesContext.getCurrentInstance().addMessage(null, msg);

				listaUsuario = null;
				novoUsuario = new Usuario();
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Já existe um login cadastrado com essa matrícula.");  
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Não existe aluno cadastrado com esse CPF.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Usuário excluído com sucesso!");  
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

	//Criptografia by Caelum
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
				+ ", senhaCriptografada=" + senhaCriptografada + "]]";
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

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}
}