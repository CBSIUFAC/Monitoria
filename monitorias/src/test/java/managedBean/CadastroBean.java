package managedBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.NamedEntityGraph;

import DAO.AlunoDAO;
import DAO.UsuarioDAO;
import entity.Aluno;
import entity.Usuario;

@ManagedBean(name="cadastroBean")
@SessionScoped
public class CadastroBean {

	private UsuarioDAO usuDAO = new UsuarioDAO();
	private Usuario usu;
	private Usuario novoUsuario = new Usuario();
	private String cpf;
	private Date dataNascimento;
	List<Usuario> listaUsuario = null;
	
	public String inserirProprioUsuario(){
		
		//AlunoDAO alunoDAO = new AlunoDAO();
		System.out.println(cpf);
		System.out.println(dataNascimento);
		//Aluno a = alunoDAO.buscaAlunoPorCpf(cpf);
		
//		if (a != null){
//			
//			if(a.getDataNascimento().equals(getDataNascimento())) {
//			
//				if (usuDAO.getUsuarioPorMatricula(a.getMatricula()) == null){
//					System.out.println("Não existe login com esse aluno");
//					
//					novoUsuario.setAluno(a);
//					novoUsuario.setPassword(criptografarSenha(novoUsuario.getPassword()));
//	
//
//					usuDAO.inserirUsuario(novoUsuario);
//					
//					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Cadastro com sucesso: "+novoUsuario.getUsername());  
//					FacesContext.getCurrentInstance().addMessage(null, msg);
//					
//					listaUsuario = null;
//					novoUsuario = new Usuario();
//				}else{
//					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Já existe um login cadastrado com essa matrícula.");  
//					FacesContext.getCurrentInstance().addMessage(null, msg);
//				}
//			}else{
//				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Não existe aluno cadastrado com esse CPF.");  
//				FacesContext.getCurrentInstance().addMessage(null, msg);
//			}
//		}
		return "usuarios";
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


	public void setUsuDAO(UsuarioDAO usuDAO) {
		this.usuDAO = usuDAO;
	}


	public Usuario getUsu() {
		return usu;
	}


	public void setUsu(Usuario usu) {
		this.usu = usu;
	}


	public Usuario getNovoUsuario() {
		return novoUsuario;
	}


	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	

	
}
