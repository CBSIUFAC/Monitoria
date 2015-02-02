package managedBean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import DAO.AlunoDAO;
import DAO.InscricaoDAO;
import entity.Aluno;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
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
	
	public void inserirInscricao(Edital e, List<Disciplina> disciplinas) {
		inscricao = new Inscricao();
		
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.getAluno(12886);
		
		System.out.println(a);
		
		inscricao.setAluno(a);
		inscricao.setEdital(e);
		inscricao.setDataInscricao(new Date());
		for (Disciplina disciplina : disciplinas) {
			inscricao.setDisciplina(disciplina);
			inscricaoDAO.inserirInscricao(inscricao);
		}		
        addMessage("Welcome to Primefaces!!");
    }
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
