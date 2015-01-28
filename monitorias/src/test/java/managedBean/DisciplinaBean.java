package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.DragDropEvent;

import DAO.DisciplinaDAO;
import entity.Centro;
import entity.Disciplina;

@ManagedBean(name="disciplinaBean")
@SessionScoped
public class DisciplinaBean {

	private Disciplina disciplina;
	private Disciplina disciplinaSelecionada;
	private List<Disciplina> droppedDisciplinas = new ArrayList<Disciplina>();;
	
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private List<Disciplina> lista;
	private List<Disciplina> listaFiltro;
	private String[] periodos = new String[]{"1º Semestre", "2º Semestre","DPLE"};

	private Centro centro;

    public void init() {
        lista = disciplinaDAO.getListaDisciplina();
        droppedDisciplinas = new ArrayList<Disciplina>();
    }
	
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
        }else
        	System.out.println("droppedDisciplinas é nulo.");
        
        if(lista != null)
        	lista.remove(d);
        else
        	System.out.println("lista nula");
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
}
