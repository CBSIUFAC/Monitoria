package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FlowEvent;

import DAO.DisciplinaDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;

@ManagedBean(name="disciplinaBean")
@SessionScoped
public class DisciplinaBean {
	
	private boolean primeiroLoad = true;
	
	private Disciplina disciplina;
	private Edital edital;
	private Centro centro;
	private int ano;
	private int periodo;
	
	private Disciplina disciplinaSelecionada;
	private List<Disciplina> disciplinasSelecionadas = new ArrayList<Disciplina>();
	private List<Disciplina> listaFiltro;
	private List<Disciplina> droppedDisciplinas = new ArrayList<Disciplina>();
	private List<Disciplina> lista;
	private List<Disciplina> listaPorCentro;
	private String[] periodos = new String[]{"1º Semestre", "2º Semestre","DPLE"};
	
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	private EditalBean editalBean;
	
	private boolean back;
	
    public void init() {
        droppedDisciplinas = new ArrayList<Disciplina>();
    }
	
    public void adicionaDisciplina(Disciplina d){
    	primeiroLoad = false;
    	System.out.println("Primeiro load -- é? "+primeiroLoad);
    	droppedDisciplinas.add(d);
    	listaPorCentro.remove(d);
    	System.out.println(droppedDisciplinas.size());
    }

    
    
	public Edital getEdital() {
		return edital;
	}


	public void setEdital(Edital edital) {
		this.edital = edital;
	}


	public Centro getCentro() {
		return centro;
	}


	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
	
	public List<Disciplina> getListaPorCentro() {
		if(primeiroLoad) {
			System.out.println("GetListaPorCentro: primeiro load true");
			listaPorCentro = disciplinaDAO.getListaDisciplinaPorCentro(ano, periodo, centro);
			return listaPorCentro;
		} else {
			System.out.println("GetListaPorCentro: primeiro load false");
			return listaPorCentro;
		}
	}

	public void setListaPorCentro(List<Disciplina> listaPorCentro) {
		this.listaPorCentro = listaPorCentro;
	}
	
	
    public EditalBean getEditalBean() {
		return editalBean;
	}


	public void setEditalBean(EditalBean editalBean) {
		this.editalBean = editalBean;
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

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public List<Disciplina> getDroppedDisciplinas() {
		if(droppedDisciplinas == null)
			droppedDisciplinas = new ArrayList<Disciplina>();
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


	public void setAno(int ano) {	
		this.ano = ano;
	}
	
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	public int getTotalDeVagas(){
		int totalDeVagas=0;
		for (Disciplina d: droppedDisciplinas){
			totalDeVagas += d.getVagas();
		}
		return totalDeVagas;
	}

	public boolean isPrimeiroLoad() {
		return primeiroLoad;
	}

	public void setPrimeiroLoad(boolean primeiroLoad) {
		this.primeiroLoad = primeiroLoad;
	}

	public List<Disciplina> getDisciplinasSelecionadas() {
		return disciplinasSelecionadas;
	}

	public void setDisciplinasSelecionadas(List<Disciplina> disciplinasSelecionadas) {
		this.disciplinasSelecionadas = disciplinasSelecionadas;
	}

	public List<Disciplina> getListaPorEdital() {
			return disciplinasSelecionadas;
	}

	public int getAno() {
		return ano;
	}

	public int getPeriodo() {
		return periodo;
	}

	public boolean isBack() {
		return back;
	}

	public void setBack(boolean back) {
		this.back = back;
	}
}
