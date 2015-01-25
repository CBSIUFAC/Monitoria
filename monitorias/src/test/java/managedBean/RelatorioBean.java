package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.DisciplinaDAO;
import DAO.RelatorioDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Relatorio;

@ManagedBean(name="relatorioBean")
@SessionScoped

public class RelatorioBean {
	
	private Relatorio relatorio;
	private RelatorioDAO relatorioDAO = new RelatorioDAO();
	private List<Relatorio> lista;
	private List<Relatorio> listaFiltro;
	
	private Disciplina disciplina;
	private Aluno aluno;
	
	public Relatorio getRelatorio() {
		if (relatorio == null)
			relatorio = new Relatorio();
		return relatorio;
	}
	
	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
	
	public RelatorioDAO getDisciplinaDAO() {
		return relatorioDAO;
	}
	
	public void setDisciplinaDAO(RelatorioDAO disciplinaDAO) {
		this.relatorioDAO = disciplinaDAO;
	}
	
	public List<Relatorio> getLista() {
		if (lista == null)
			lista = relatorioDAO.getListaRelatorio();
		return lista;
	}
	
	public void setLista(List<Relatorio> lista) {
		this.lista = lista;
	}
	
	public List<Relatorio> getListaFiltro() {
		return listaFiltro;
	}
	
	public void setListaFiltro(List<Relatorio> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}
