package managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import DAO.InscricaoDAO;
import DAO.RelatorioDAO;
import DAO.UsuarioDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Inscricao;
import entity.Relatorio;
import entity.Usuario;

@ManagedBean(name="relatorioBean")
@SessionScoped

public class RelatorioBean {
	
	private Relatorio relatorio;
	private RelatorioDAO relatorioDAO = new RelatorioDAO();
	private List<Relatorio> lista;
	private List<Relatorio> listaPorUsuario;
	private List<Relatorio> listaFiltro;
	private List<Inscricao> listaInscricoesUsuario;
	
	private Disciplina disciplina;
	private Aluno aluno;
	
	public String criarRelatorio() {
		
		Inscricao inscricao = getInscricaoUsuario();
		System.out.println(getInscricaoUsuario());
		System.out.println(inscricao);
		
		if(inscricao.getStatus() == 3) {
			
			relatorio.setInscricao(inscricao);
			relatorioDAO.inserirRelatorio(relatorio);
		}
		
		
		return "listaRelatorios";
	}
	
	
	// Pega a lista de relatorios do usuario
	public List<Relatorio> getListaPorUsuario() {
		
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		
		SecurityContext context = SecurityContextHolder.getContext();  
		if(context instanceof SecurityContext)  
		{  
			Authentication authentication = context.getAuthentication();  
			if(authentication instanceof Authentication){;
				String aux = ((User)authentication.getPrincipal()).getUsername();
				usu = usuDAO.getUsuario(aux);
			}
		}
		
		listaPorUsuario = relatorioDAO.getListaPorUsuario(usu);
		
		for (Relatorio relatorio : listaPorUsuario) {
			System.out.println(relatorio);
		}
		
		return listaPorUsuario;
	}
	
	public void setListaPorUsuario(List<Relatorio> listaPorUsuario) {
		this.listaPorUsuario = listaPorUsuario;
	}
	
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
	
	public void setListaInscricoesUsuario(List<Inscricao> listaInscricoesUsuario) {
		this.listaInscricoesUsuario = listaInscricoesUsuario;
	}
	
	// Pega a unica inscrição ativa que o usuário tem (o único edital)
	public Inscricao getInscricaoUsuario() {
		
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();
		InscricaoDAO inscricaoDAO = new InscricaoDAO();
		Inscricao inscricaoValida = new Inscricao();
		
		SecurityContext context = SecurityContextHolder.getContext();  
		if(context instanceof SecurityContext)  
		{  
			Authentication authentication = context.getAuthentication();  
			if(authentication instanceof Authentication){;
				String aux = ((User)authentication.getPrincipal()).getUsername();
				usu = usuDAO.getUsuario(aux);
	
			}
		}
		
		listaInscricoesUsuario = inscricaoDAO.getListaPorUsuario(usu);
		
		for (Inscricao inscricao : listaInscricoesUsuario) {
			if(inscricao.getStatus() == 3) {
				inscricaoValida = inscricao;
				return inscricaoValida;
			}
		}
		return null;
	}
}
