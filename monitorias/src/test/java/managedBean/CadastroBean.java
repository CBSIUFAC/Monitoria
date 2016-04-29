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
	private Usuario novoUsuario = new Usuario();
	private String cpf;
	private Date dataNascimento;

	public String inserirProprioUsuario(){

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = alunoDAO.buscaAlunoPorCpf(cpf);
		System.out.println("Aluno: "+aluno);
		System.out.println("Cpf: "+cpf);

		if (aluno != null) {
			System.out.println("1");
			if(aluno.getDataNascimento().equals(getDataNascimento())) {
				System.out.println("2");
				if (usuDAO.getUsuarioPorMatricula(aluno.getMatricula()) == null) {
					System.out.println("Entrou aqui?");
					System.out.println("Não existe login com esse aluno");
					novoUsuario.setAluno(aluno);
					novoUsuario.setPassword(criptografarSenha(novoUsuario.getPassword()));
					novoUsuario.setUsername(cpf);
					novoUsuario.setAtivo(true);
					novoUsuario.setTipoUsuario("usuario");
					usuDAO.inserirUsuario(novoUsuario);

					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Cadastro realizado com sucesso!");  
					FacesContext.getCurrentInstance().addMessage(null, msg);

					novoUsuario = new Usuario();

					return "login";

				} else {
					System.out.println("3");
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso","Já existe um login cadastrado com essa matrícula.");  
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} 

			} else {

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso","Não existe alunos cadastrados com esse cpf.");  
				FacesContext.getCurrentInstance().addMessage(null, msg);		
			}

		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso","Data de nascimento invalida.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "";
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
		System.out.println("aqui");
		this.cpf = cpf;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		System.out.println("Data nascimento"+ dataNascimento);
		this.dataNascimento = dataNascimento;
	}

}
