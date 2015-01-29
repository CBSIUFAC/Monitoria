package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.CentroDAO;
import DAO.DisciplinaDAO;
import DAO.EditalDAO;
import DAO.EditalDisciplinaDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;

@ManagedBean(name="editalBean")
@SessionScoped
public class EditalBean {

	private Edital edital;
	private EditalDAO editalDAO = new EditalDAO();
	private List<Edital> lista;
	private List<Edital> listaFiltro;
	private Centro centro;
	private CentroDAO centroDAO = new CentroDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	
	private EditalDisciplinaBean editalDisciplinaBean = new EditalDisciplinaBean();
	private EditalDisciplina editalDisciplina = new EditalDisciplina();
	private EditalDisciplinaDAO editalDisciplinaDAO = new EditalDisciplinaDAO();
	
	private List<Disciplina> listaPorCentro;
	
	public EditalDisciplina getEditalDisciplina() {
		return editalDisciplina;
	}

	public void setEditalDisciplina(EditalDisciplina editalDisciplina) {
		this.editalDisciplina = editalDisciplina;
	}

	public List<Disciplina> getListaPorCentro() {
		System.out.println("getListaPorCentro() "+edital.getAno()+" "+ centro);
		listaPorCentro = disciplinaDAO.getListaDisciplinaPorCentro(edital.getAno(), edital.getPeriodo(), centro);
		return listaPorCentro;
	}

	public void setListaPorCentro(List<Disciplina> listaPorCentro) {
		this.listaPorCentro = listaPorCentro;
	}

	@ManagedProperty("#{disciplinaBean}")
    private DisciplinaBean disciplinaBean;

	public void setEdital(Edital edital) {
		this.edital = edital;
	}
	
	public Edital getEdital() {
		if(edital == null)
			edital = new Edital();
		return edital;
	}
	
	public void setLista(List<Edital> lista) {
		this.lista = lista;
	}
	
	public List<Edital> getLista() {
		if(lista == null) 
			lista = editalDAO.getListaEdital();
		return lista;
	}
	
	public void setEditalDAO(EditalDAO editalDAO) {
		this.editalDAO = editalDAO;
	}
	
	public EditalDAO getEditalDAO() {
		return editalDAO;
	}

	public List<Edital> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Edital> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
	public CentroDAO getCentroDAO() {
		return centroDAO;
	}
	
	public void setCentroDAO(CentroDAO centroDAO) {
		this.centroDAO = centroDAO;
	}
	
    public EditalDisciplinaBean getEditalDisciplinaBean() {
		return editalDisciplinaBean;
	}

	public void setEditalDisciplinaBean(EditalDisciplinaBean editalDisciplinaBean) {
		this.editalDisciplinaBean = editalDisciplinaBean;
	}

	public DisciplinaBean getDisciplinaBean() {
		return disciplinaBean;
	}

	public void setDisciplinaBean(DisciplinaBean disciplinaBean) {
		this.disciplinaBean = disciplinaBean;
	}
	
	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public EditalDisciplinaDAO getEditalDisciplinaDAO() {
		return editalDisciplinaDAO;
	}

	public void setEditalDisciplinaDAO(EditalDisciplinaDAO editalDisciplinaDAO) {
		this.editalDisciplinaDAO = editalDisciplinaDAO;
	}
	
	public List<Centro> completarCentro(String query) {
		  return centroDAO.buscaCentros(query);
    }
    
    public void inserirEdital(){
    	
    	edital.setCentro(centro);
    	editalDAO.inserirEdital(edital);
    	
    	for (Disciplina disciplina : disciplinaBean.getDroppedDisciplinas()) {
    		editalDisciplina.setDisciplina(disciplina);
    		editalDisciplina.setEdital(edital);
			editalDisciplinaDAO.inserirEditalDisciplina(editalDisciplina);
		}

    	FacesMessage msg = new FacesMessage("Sucesso!", "Edital criadoo!");
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    	
    }

    public String convertePeriodo(int id) {
    	System.out.println("Entrou no convertePeriodo do editalBean id = "+id);
		String periodo = "";
		if(id == 201) {
			periodo = "1º Semestre";
		} else if (id == 202) {
			periodo = "2º Semestre";
		} else if (id == 203) {
			periodo = "DPLE";
		}
		System.out.println("String de retorno = "+periodo);
		return periodo;
	}
}
