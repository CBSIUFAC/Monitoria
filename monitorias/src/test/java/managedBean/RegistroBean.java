package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.RegistroDAO;
import DAO.RelatorioDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Registro;
import entity.Relatorio;

@ManagedBean(name="registroBean")
@SessionScoped

public class RegistroBean {
	
	private Relatorio relatorio;
	private Registro registro;
	private RelatorioDAO relatorioDAO = new RelatorioDAO();
	private RegistroDAO registroDAO = new RegistroDAO();
	
	private List<Relatorio> lista;
	
	private List<Registro> listaRegistroPorRelatorio;
	
	private Disciplina disciplina;
	private Aluno aluno;
	
	public String insereRegistro() {
		
		System.out.println("Relatório ta vindo null? "+relatorio);
		System.out.println(registro);
		
		//Pegando o id passado do relatório.
		
		if(relatorio != null) {
			
			registro.setRelatorio(relatorio);
			registroDAO.inserirRegistro(registro);
			System.out.println("Registro inserido.");
			
		}
		
		return "listaRelatorios";
	}
	
	public void setListaRegistroPorRelatorio(List<Registro> listaRegistroPorRelatorio) {
		this.listaRegistroPorRelatorio = listaRegistroPorRelatorio;
	}
	
	public List<Registro> getListaRegistroPorRelatorio() {
		if(relatorio != null) {
			listaRegistroPorRelatorio = null;
			listaRegistroPorRelatorio = registroDAO.getListaPorRelatorio(relatorio);
			System.out.println("O relatório foi gettado!"+ listaRegistroPorRelatorio);
		} else {
			listaRegistroPorRelatorio = new ArrayList<Registro>();
			System.out.println("O relatório ta vindo nulo.");
		}
		return listaRegistroPorRelatorio;
	}
	
	public Relatorio getRelatorio() {
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
		lista = relatorioDAO.getListaRelatorio();
		return lista;
	}
	
	public void setLista(List<Relatorio> lista) {
		this.lista = lista;
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

	public Registro getRegistro() {
		if(registro == null) 
			registro = new Registro();
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public RelatorioDAO getRelatorioDAO() {
		return relatorioDAO;
	}

	public void setRelatorioDAO(RelatorioDAO relatorioDAO) {
		this.relatorioDAO = relatorioDAO;
	}

	public RegistroDAO getRegistroDAO() {
		return registroDAO;
	}

	public void setRegistroDAO(RegistroDAO registroDAO) {
		this.registroDAO = registroDAO;
	}
}
