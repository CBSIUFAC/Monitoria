package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.CentroDAO;
import entity.Centro;

@ManagedBean(name="centroBean", eager = true)
@SessionScoped
public class CentroBean {

	private Centro centro;
	private Centro centroSelecionado;
	
	private CentroDAO centroDAO = new CentroDAO();
	private List<Centro> lista;
	private List<Centro> listaFiltro;

	
	public Centro getCentroPorId(int id) {
		return centroDAO.getCentro(id);
	}
	
	public void setCentro(Centro Centro) {
		this.centro = Centro;
	}
	
	public Centro getCentro() {
		if(centro == null)
			centro = new Centro();
		return centro;
	}
	
	public void setLista(List<Centro> lista) {
		this.lista = lista;
	}
	
	public List<Centro> getLista() {
		if(lista == null) {
			lista = centroDAO.getListaCentro();
		}
		return lista;
	}
	
	public void setCentroDAO(CentroDAO centroDAO) {
		this.centroDAO = centroDAO;
	}
	
	public CentroDAO getCentroDAO() {
		return centroDAO;
	}

	public List<Centro> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Centro> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}

	public Centro getCentroSelecionado() {
		return centroSelecionado;
	}

	public void setCentroSelecionado(Centro centroSelecionado) {
		this.centroSelecionado = centroSelecionado;
	}
}
