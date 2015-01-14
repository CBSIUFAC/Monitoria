package manageBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.AlunoDAO;
import entity.Aluno;

@ManagedBean(name="alunoBean")
@SessionScoped
public class AlunoBean {

	private Aluno aluno;
	private AlunoDAO alunoDAO = new AlunoDAO();
	private List<Aluno> lista;
	private List<Aluno> listaFiltro;
	
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Aluno getAluno() {
		if(aluno == null)
			aluno = new Aluno();
		return aluno;
	}
	
	public void setLista(List<Aluno> lista) {
		this.lista = lista;
	}
	
	public List<Aluno> getLista() {
		if(lista == null) 
			lista = alunoDAO.getListaAluno();
		return lista;
	}
	
	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}
	
	public AlunoDAO getAlunoDAO() {
		return alunoDAO;
	}

	public List<Aluno> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Aluno> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}
}
