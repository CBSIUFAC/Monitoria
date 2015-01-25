package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.EditalDisciplinaDAO;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;

@ManagedBean(name="editalDisciplinaBean")
@SessionScoped

public class EditalDisciplinaBean {
	
	private EditalDisciplina editalDisciplina;
	private EditalDisciplinaDAO editalDisciplinaDAO = new EditalDisciplinaDAO();
	private List<EditalDisciplina> lista;
	private List<EditalDisciplina> listaFiltro;
	
	private Edital edital;
	private Disciplina disciplina;
	
	public EditalDisciplina getEditalDisciplina() {
		if (editalDisciplina == null)
			editalDisciplina = new EditalDisciplina();
		return editalDisciplina;
	}
	
	public void setEditalDisciplina(EditalDisciplina editalDisciplina) {
		this.editalDisciplina = editalDisciplina;
	}
	
	public EditalDisciplinaDAO getDisciplinaDAO() {
		return editalDisciplinaDAO;
	}
	
	public void setDisciplinaDAO(EditalDisciplinaDAO disciplinaDAO) {
		this.editalDisciplinaDAO = disciplinaDAO;
	}
	
	public List<EditalDisciplina> getLista() {
		if (lista == null)
			lista = editalDisciplinaDAO.getListaEditalDisciplina();
		return lista;
	}
	
	public void setLista(List<EditalDisciplina> lista) {
		this.lista = lista;
	}
	
	public List<EditalDisciplina> getListaFiltro() {
		return listaFiltro;
	}
	
	public void setListaFiltro(List<EditalDisciplina> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}
	
	public Edital getEdital() {
		return edital;
	}
	
	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public EditalDisciplinaDAO getEditalDisciplinaDAO() {
		return editalDisciplinaDAO;
	}

	public void setEditalDisciplinaDAO(EditalDisciplinaDAO editalDisciplinaDAO) {
		this.editalDisciplinaDAO = editalDisciplinaDAO;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}
