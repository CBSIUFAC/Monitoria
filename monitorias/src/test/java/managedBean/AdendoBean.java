package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import DAO.AdendoDAO;
import entity.Edital;
import entity.Adendo;

@ManagedBean(name="adendoBean")
@SessionScoped

public class AdendoBean {
	
	private Adendo adendo;
	private AdendoDAO adendoDAO = new AdendoDAO();
	private List<Adendo> lista;
	private List<Adendo> listaFiltro;
	
	private Edital edital;
	
	public Adendo getAdendo() {
		if (adendo == null)
			adendo = new Adendo();
		return adendo;
	}
	
	public void setAdendo(Adendo adendo) {
		this.adendo = adendo;
	}
	
	public AdendoDAO getDisciplinaDAO() {
		return adendoDAO;
	}
	
	public void setDisciplinaDAO(AdendoDAO disciplinaDAO) {
		this.adendoDAO = disciplinaDAO;
	}
	
	public List<Adendo> getLista() {
		if (lista == null)
			lista = adendoDAO.getListaAdendo();
		return lista;
	}
	
	public void setLista(List<Adendo> lista) {
		this.lista = lista;
	}
	
	public List<Adendo> getListaFiltro() {
		return listaFiltro;
	}
	
	public void setListaFiltro(List<Adendo> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}
	
	public Edital getEdital() {
		return edital;
	}
	
	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public AdendoDAO getAdendoDAO() {
		return adendoDAO;
	}

	public void setAdendoDAO(AdendoDAO adendoDAO) {
		this.adendoDAO = adendoDAO;
	}
	
}
