package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DragDropEvent;

import DAO.DisciplinaDAO;
import DAO.EditalDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;

@ManagedBean(name="disciplinaBean")
@SessionScoped
public class DisciplinaBean {

	private Disciplina disciplina;
	private Disciplina disciplinaSelecionada;
	private List<Disciplina> droppedDisciplinas = new ArrayList<Disciplina>();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private List<Disciplina> listaFiltro;
	private String[] periodos = new String[]{"1º Semestre", "2º Semestre","DPLE"};
	private List<Disciplina> lista;
	private EditalBean editalBean;

	
    public EditalBean getEditalBean() {
		return editalBean;
	}


	public void setEditalBean(EditalBean editalBean) {
		this.editalBean = editalBean;
	}
	
    public void init() {
        droppedDisciplinas = new ArrayList<Disciplina>();
    }

    
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}


	public Disciplina getDisciplina() {
		if(disciplina == null)
			disciplina = new Disciplina();
		return disciplina;
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
	
	public String[] getPeriodos() {
        return periodos;
    }
     
	public void setPeriodos(String[] periodos) {
		this.periodos = periodos;
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
	
	public void onDisciplinaDrop(DragDropEvent ddEvent) {
        Disciplina d = ((Disciplina) ddEvent.getData());
        if (d != null)
        	System.out.println(d);
        else
        	System.out.println("d é nulo.");
        
        if(droppedDisciplinas != null){
        	droppedDisciplinas.add(d);
        	System.out.println("DroppedDisciplinas.size(): "+droppedDisciplinas.size());
        }else {
        	System.out.println("droppedDisciplinas é nulo.");
        }
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public List<Disciplina> getDroppedDisciplinas() {
		return droppedDisciplinas;
	}

	public void setDroppedDisciplinas(List<Disciplina> droppedDisciplinas) {
		this.droppedDisciplinas = droppedDisciplinas;
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
