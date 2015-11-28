package managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.mysql.fabric.xmlrpc.base.Array;

import DAO.CentroDAO;
import DAO.DisciplinaDAO;
import DAO.EditalDAO;
import DAO.EditalDisciplinaDAO;
import DAO.UsuarioDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;
import entity.GeradorEdital;
import entity.Inscricao;
import entity.Usuario;

@ManagedBean(name="editalBean")
@SessionScoped
public class EditalBean {

	private Edital edital ;
	private Edital editalSelecionado;
	private Centro centro;
	private Centro centroSelecionado;
	private EditalDisciplina editalDisciplina = new EditalDisciplina();
	private List<EditalDisciplina> editaisDisciplinasNaoInscritas = new ArrayList<EditalDisciplina>();

	GeradorEdital[] beanArray;

	private List<Edital> lista;
	private List<Edital> listaFiltro;

	private CentroDAO centroDAO = new CentroDAO();
	private EditalDAO editalDAO = new EditalDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	private EditalDisciplinaBean editalDisciplinaBean = new EditalDisciplinaBean();
	private EditalDisciplinaDAO editalDisciplinaDAO = new EditalDisciplinaDAO();

	private List<Edital> editaisPorCentro;

	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ManagedProperty("#{disciplinaBean}")
	private DisciplinaBean disciplinaBean;
	
	private int id;
	
	@PostConstruct
	public void carregar(){
	   String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
	            
	   if(id != null && !id.equals("")){
		   System.out.println("idString= "+id+"\nidInt= "+this.id);
		  editalSelecionado = editalDAO.getEdital(Integer.valueOf(id));
	   }
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public EditalDisciplina getEditalDisciplina() {
		return editalDisciplina;
	}

	public void setEditalDisciplina(EditalDisciplina editalDisciplina) {
		this.editalDisciplina = editalDisciplina;
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

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
		disciplinaBean.setCentro(centro);
		disciplinaBean.setAno(edital.getAno());
		disciplinaBean.setPeriodo(edital.getPeriodo());
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


	public List<Edital> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Edital> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}

	public void inserirEdital(int totalVagas) throws JRException, IOException, ParseException{

		int count = 0;
		edital.setCentro(centro);
		edital.setTotalVagas(totalVagas);
		editalDAO.inserirEdital(edital);

		List<Disciplina> listaSelecionados = disciplinaBean.getDroppedDisciplinas();
		beanArray = new GeradorEdital[listaSelecionados.size()];

		for (Disciplina disciplina : listaSelecionados) {

			editalDisciplina.setVagas(disciplina.getVagas());
			editalDisciplina.setDisciplina(disciplina);
			editalDisciplina.setEdital(edital);

			editalDisciplinaDAO.inserirEditalDisciplina(editalDisciplina);
			GeradorEdital g = new GeradorEdital(edital, editalDisciplina, disciplina);
			beanArray[count] = g;
			count++;
		}

		disciplinaBean.setPrimeiroLoad(true);
		FacesMessage msg = new FacesMessage("Sucesso!", "Edital criado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msg);  	
		imprimirRelatorio(beanArray);	
	}

	public List<Edital> listaPorCentro(Centro c){
		EditalDAO dao = new EditalDAO();
		if(c==null){
			System.out.println("centro nulo");
			return dao.getListaEdital();
		}
		System.out.println("listaporcentro... centro="+c.getIdCentro());
		return dao.getListaEdital(c); 
	}
	
	public void verEditais() {
		RequestContext.getCurrentInstance().openDialog("verEditais");
	}

	public List<Edital> getEditaisPorCentro() {
		return editaisPorCentro;
	}

	public void setEditaisPorCentro(Centro c) {
		editaisPorCentro = editalDAO.getListaEdital(c);
	}

	public Centro getCentroSelecionado() {
		return centroSelecionado;
	}

	public void setCentroSelecionado(Centro centroSelecionado) {
		this.centroSelecionado = centroSelecionado;
	}

	public List<Edital> listaEditaisPorCentro(Centro c){
		return editalDAO.getListaEdital(c);
	}

	public Edital getEditalSelecionado() {
		return editalSelecionado;
	}

	public void setEditalSelecionado(Edital editalSelecionado) {
		this.editalSelecionado = editalSelecionado;
	}

	public void setEditaisPorCentro(List<Edital> editaisPorCentro) {
		this.editaisPorCentro = editaisPorCentro;
	}

	public void imprimirRelatorio(GeradorEdital[] g) throws IOException, ParseException {

		HashMap parametros = new HashMap();
		parametros.put("ID_EDITAL", edital.getIdEdital());
		FacesContext facesContext = FacesContext.getCurrentInstance();
	

		try {

			JRBeanArrayDataSource arrayDs = new JRBeanArrayDataSource(g, false);
			String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
			String caminhoReports = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports");

			JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoWebInf+"\\reports\\Edital.jasper", parametros, arrayDs);

			String caminhoFinal = "\\edital"+""+edital.getTitulo()+""+edital.getDataCriacao().getTime();

			File pdf = new File(caminhoReports+caminhoFinal);
			pdf.createNewFile();
			FileOutputStream arquivo = new FileOutputStream(pdf);

			JasperExportManager.exportReportToPdfStream(impressoraJasper, arquivo);
			
			edital.setSrcPDF("//reports/"+caminhoFinal);
			editalDAO.atualizarEdital(edital);

			edital = null;
			disciplinaBean.setDroppedDisciplinas(null);

			arquivo.flush();
			arquivo.close();


		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private StreamedContent file;

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public StreamedContent getFile() throws FileNotFoundException {

		String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
		InputStream stream = new FileInputStream(caminhoWebInf+editalSelecionado.getSrcPDF());  
		file = new DefaultStreamedContent(stream, "application/pdf", editalSelecionado.getTitulo()+".pdf");  

		return file;  
	}

	public List<EditalDisciplina> editaisDisciplinasNaoInscritas(Collection<EditalDisciplina> todas, List<Inscricao> doAluno){
		List<EditalDisciplina> resultado = new ArrayList<EditalDisciplina>();
		List<EditalDisciplina> ed = new ArrayList<EditalDisciplina>();

		for (EditalDisciplina ed1 : todas) {
			ed.add(ed1);
		}

		if (doAluno==null) 
			return ed;
		else
			if (doAluno.size()<1)
				return ed;

		for (int i = 0; i < ed.size(); i++) {
			boolean achouIgual=false;
			for (int j = 0; j < doAluno.size(); j++) {
				if (ed.get(i).getDisciplina().getIdDisciplina() == doAluno.get(j).getDisciplina().getIdDisciplina()){
					achouIgual = true;
					j=doAluno.size();
				}
			}
			if (achouIgual==false){
				resultado.add(ed.get(i));
			}
		}

		return resultado;

	}

	public List<EditalDisciplina> getEditaisDisciplinasNaoInscritas() {

		Usuario usu = new Usuario();  
		SecurityContext context = SecurityContextHolder.getContext();  
		if(context instanceof SecurityContext)  
		{  
			Authentication authentication = context.getAuthentication();  
			if(authentication instanceof Authentication){
				String aux = ((User)authentication.getPrincipal()).getUsername();
				UsuarioDAO usuDAO = new UsuarioDAO();
				usu = usuDAO.getUsuario(aux);

			}
		}
		editalSelecionado = editalDAO.getEdital(Integer.valueOf(id));;
		return editaisDisciplinasNaoInscritas(editalSelecionado.getEditaisDisciplinas(), usu.getAluno().getInscricoes());
	}

	public void setEditaisDisciplinasNaoInscritas(List<EditalDisciplina> editaisDisciplinasNaoInscritas) {
		this.editaisDisciplinasNaoInscritas = editaisDisciplinasNaoInscritas;
	}

	private void limparCampos() {
		edital = new Edital();
		centro = new Centro();
		editalDisciplina = new EditalDisciplina();
		lista = new ArrayList<Edital>();
		listaFiltro = new ArrayList<Edital>();
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
}

