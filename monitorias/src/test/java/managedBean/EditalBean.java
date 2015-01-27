package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import DAO.CentroDAO;
import DAO.EditalDAO;
import entity.Centro;
import entity.Edital;

@ManagedBean(name="editalBean")
@SessionScoped
public class EditalBean {

	private Edital edital;
	private EditalDAO editalDAO = new EditalDAO();
	private List<Edital> lista;
	private List<Edital> listaFiltro;
	private Centro centro;
	private CentroDAO centroDAO = new CentroDAO();
	
	@ManagedProperty("#{centroBean}")
    private CentroBean centroBean;
	
	public CentroBean getCentroBean() {
		return centroBean;
	}

	public void setCentroBean(CentroBean centroBean) {
		this.centroBean = centroBean;
	}

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
	
    public List<Centro> completarCentro(String query) {

        List<Centro> todosCentros = centroBean.getLista();
        List<Centro> filtroCentros = new ArrayList<Centro>();
         
        for (int i = 0; i < todosCentros.size(); i++) {
            Centro centro = todosCentros.get(i);
            if(centro.getNome().toLowerCase().contains(query.toLowerCase())) {
                filtroCentros.add(centro);
            }
        }
        return filtroCentros;
    }
}
