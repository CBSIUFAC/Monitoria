package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.DisciplinaDAO;
import DAO.ProfessorDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Professor;

@ManagedBean(name="professorBean")
@SessionScoped
public class ProfessorBean {

	private Professor professor;
	private ProfessorDAO professorDAO = new ProfessorDAO();
	private List<Professor> lista;
	private List<Professor> listaFiltro;
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Professor getProfessor() {
		if(professor == null)
			professor = new Professor();
		return professor;
	}
	
	public void setLista(List<Professor> lista) {
		this.lista = lista;
	}
	
	public List<Professor> getLista() {
		if(lista == null) 
			lista = professorDAO.getListaProfessor();
		return lista;
	}
	
	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}
	
	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}

	public List<Professor> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Professor> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}
}
