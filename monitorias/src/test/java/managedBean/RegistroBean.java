package managedBean;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.SessionImpl;
import org.hibernate.jdbc.Work;

import util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import DAO.RegistroDAO;
import DAO.RelatorioDAO;

import com.mysql.jdbc.Connection;

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
	
	/* MÉTODOS PARA GERAR OS RELATÓRIOS */
	
	public void gerarRelatorio(ActionEvent actionEvent) throws JRException, IOException {
		
		JasperPrint jasperPrint;
		List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();
		listaRelatorio.add(relatorio);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRelatorio", relatorio.getIdRelatorio());
		
		
		HibernateUtil.getConnection();
		final java.sql.Connection connection = HibernateUtil.connectionOk;
		System.out.println("Conexão: "+connection);
		String caminhoReport = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/Atividades.jasper");
		
		jasperPrint = JasperFillManager.fillReport(caminhoReport, parametros, connection);
		
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
	    httpServletResponse.setHeader("Content-disposition", "attachment; filename=report.pdf");  
	    ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
	    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
	    FacesContext.getCurrentInstance().responseComplete();  
	    
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
