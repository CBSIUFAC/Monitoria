package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.DisciplinaDAO;
import entity.Centro;
import entity.Disciplina;

@ManagedBean(name="disciplinaBean")
@SessionScoped
public class DisciplinaBean {

	private Disciplina disciplina;
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private List<Disciplina> lista;
	private List<Disciplina> listaFiltro;
	
	private Centro centro;
	
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Disciplina getDisciplina() {
		if(disciplina == null)
			disciplina = new Disciplina();
		return disciplina;
	}
	
	public void setLista(List<Disciplina> lista) {
		this.lista = lista;
	}
	
	public List<Disciplina> getLista() {
		if(lista == null) 
			lista = disciplinaDAO.getListaDisciplina();
		return lista;
	}
	
	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}
	
	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}

	public List<Disciplina> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Disciplina> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}
}
