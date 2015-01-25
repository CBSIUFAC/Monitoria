package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.InscricaoDAO;
import entity.Aluno;
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
	
}
