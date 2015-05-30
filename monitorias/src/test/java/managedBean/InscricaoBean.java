package managedBean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import DAO.InscricaoDAO;
import DAO.UsuarioDAO;
import entity.Aluno;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;
import entity.Inscricao;
import entity.Usuario;

@ManagedBean(name="inscricaoBean")
@SessionScoped

public class InscricaoBean {

	private Inscricao inscricao;
	private InscricaoDAO inscricaoDAO = new InscricaoDAO();
	private List<Inscricao> lista;
	private List<Inscricao> listaPorUsuario;
	private List<Inscricao> listaFiltro;

	private Edital edital;
	private Aluno aluno;
	private Disciplina disciplina;
	private Centro centro;

	public Inscricao getInscricao() {
		if (inscricao == null)
			inscricao = new Inscricao();
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public InscricaoDAO getDisciplinaDAO() {
		return inscricaoDAO;
	}

	public void setDisciplinaDAO(InscricaoDAO disciplinaDAO) {
		this.inscricaoDAO = disciplinaDAO;
	}

	public List<Inscricao> getLista() {
		if (lista == null)
			lista = inscricaoDAO.getListaInscricao();
		return lista;
	}

	public void setLista(List<Inscricao> lista) {
		this.lista = lista;
	}

	public List<Inscricao> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Inscricao> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public InscricaoDAO getInscricaoDAO() {
		return inscricaoDAO;
	}

	public void setInscricaoDAO(InscricaoDAO inscricaoDAO) {
		this.inscricaoDAO = inscricaoDAO;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Centro getCentro() {
		if(centro == null)
			return new Centro();
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	//Injeção de um bean
	@ManagedProperty("#{usuarioFace}")
	private UsuarioFace usuarioFace;

	public String inserirInscricao(List<EditalDisciplina> ds) {
		inscricao = new Inscricao();

		if (ds != null && ds.size()>0){

			Usuario usu = new Usuario();

			SecurityContext context = SecurityContextHolder.getContext();  
			if(context instanceof SecurityContext)  
			{  
				Authentication authentication = context.getAuthentication();  
				if(authentication instanceof Authentication){
					String aux = ((User)authentication.getPrincipal()).getUsername();
					System.out.println(aux);
					UsuarioDAO usuDAO = new UsuarioDAO();
					usu = usuDAO.getUsuario(aux);

				}
			}


			inscricao.setAluno(usu.getAluno());
			inscricao.setDataInscricao(new Date());
			inscricao.setEdital(ds.get(0).getEdital());

			boolean erro=false;
			String disciplinasNaoInscritas = "";
			for (EditalDisciplina ed : ds) {
				inscricao.setDisciplina(ed.getDisciplina());
				erro = inscricaoDAO.estaInscrito(inscricao); 
				if (!erro){
					inscricaoDAO.inserirInscricao(inscricao);
				}
				else
					disciplinasNaoInscritas += ed.getDisciplina().getNomeDisciplina()+"\n";
			}

			FacesContext fContext = FacesContext.getCurrentInstance();

			if (!disciplinasNaoInscritas.equals("")){
				fContext.addMessage(null, new FacesMessage("Você já estava inscrito nas disciplinas: "+disciplinasNaoInscritas, "X y z") );
			}
			else
				fContext.addMessage(null, new FacesMessage("Inscrição realizada com sucesso! \n No menu clique em Inscrição e depois acompanhar, para visualizar suas inscrições. ", "X y z") );
		}else{
			FacesContext fContext = FacesContext.getCurrentInstance();
			fContext.addMessage(null, new FacesMessage("Escolha pelo menos uma disciplina para poder se inscrever.", "Erro.") );
		}

		listaPorUsuario = null;
		return "acompanharInscricoes";
	}

	public UsuarioFace getUsuarioFace() {
		return usuarioFace;
	}

	public void setUsuarioFace(UsuarioFace usuarioFace) {
		this.usuarioFace = usuarioFace;
	}

	public List<Inscricao> getListaPorUsuario() {
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();

		SecurityContext context = SecurityContextHolder.getContext();  
		if(context instanceof SecurityContext)  
		{  
			Authentication authentication = context.getAuthentication();  
			if(authentication instanceof Authentication){;
				String aux = ((User)authentication.getPrincipal()).getUsername();
				usu = usuDAO.getUsuario(aux);

			}
		}
		
		if (usu.getTipoUsuario().equals("admin")){
			System.out.println("É ADMIN");
			return inscricaoDAO.getListaInscricao();
		}
		System.out.println("NÃO É ADMIN");
		listaPorUsuario = inscricaoDAO.getListaPorUsuario(usu);
		System.out.println("User: "+usu.getAluno()+"\nLista.size: "+listaPorUsuario.size());
		return listaPorUsuario;
	}

	public void setListaPorUsuario(List<Inscricao> listaPorUsuario) {
		this.listaPorUsuario = listaPorUsuario;
	}


	public String convertePeriodo(int id) {
		String periodo = "";
		if(id == 201) {
			periodo = "1º Semestre";
		} else if (id == 202) {
			periodo = "2º Semestre";
		} else if (id == 203) {
			periodo = "DPLE";
		}
		return periodo;
	}

}
