package managedBean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.InscricaoDAO;
import entity.Aluno;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;
import entity.Inscricao;

@ManagedBean(name="inscricaoBean")
@SessionScoped

public class InscricaoBean {

	private Inscricao inscricao;
	private InscricaoDAO inscricaoDAO = new InscricaoDAO();
	private List<Inscricao> lista;
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
	
	@ManagedProperty("#{usuarioFace}")
    private UsuarioFace usuarioFace;

	public void inserirInscricao(List<EditalDisciplina> ds) {
		inscricao = new Inscricao();

		inscricao.setAluno(usuarioFace.getUsu().getAluno());
		
		inscricao.setDataInscricao(new Date());
		inscricao.setEdital(ds.get(0).getEdital());
		
		boolean erro=false;
		
		for (EditalDisciplina ed : ds) {
			inscricao.setDisciplina(ed.getDisciplina());
			erro = inscricaoDAO.estaInscrito(inscricao); 
			if (!erro)
				inscricaoDAO.inserirInscricao(inscricao);
		}
		
		if (erro){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro na inscrição das disciplinas:", "X y z") );
		}
	}

	public UsuarioFace getUsuarioFace() {
		return usuarioFace;
	}

	public void setUsuarioFace(UsuarioFace usuarioFace) {
		this.usuarioFace = usuarioFace;
	}
}
