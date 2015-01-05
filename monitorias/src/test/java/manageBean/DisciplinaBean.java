package manageBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.DisciplinaDAO;
import entity.Disciplina;

@ManagedBean(name="disciplinaBean")
@SessionScoped
public class DisciplinaBean implements Serializable {

	private Disciplina disciplina;
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private List<Disciplina> lista;
	
	public String inserirDisciplina() {
		disciplinaDAO.inserirDisciplina(disciplina);
		lista = null;
		return "listadisciplinas";
	}
	
	public Disciplina getDisciplina() {
		if(disciplina == null)
			disciplina = new Disciplina();
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public List<Disciplina> getLista() {
		if(lista == null) 
			lista = disciplinaDAO.getListaDisciplina();
		return lista;
	}
	
	public void setLista(List<Disciplina> lista) {
		this.lista = lista;
	}
	
}
