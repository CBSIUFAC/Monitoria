package managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import DAO.CentroDAO;
import DAO.DisciplinaDAO;
import DAO.EditalDAO;
import DAO.EditalDisciplinaDAO;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;
import entity.GeradorEdital;

@ManagedBean(name="editalBean")
@SessionScoped
public class EditalBean {

	private Edital edital ;
	private Edital editalSelecionado;
	private Centro centro;
	private Centro centroSelecionado;
	private EditalDisciplina editalDisciplina = new EditalDisciplina();

	GeradorEdital[] beanArray;
	
	private List<Edital> lista;
	private List<Edital> listaFiltro;

	private CentroDAO centroDAO = new CentroDAO();
	private EditalDAO editalDAO = new EditalDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	
	private EditalDisciplinaBean editalDisciplinaBean = new EditalDisciplinaBean();
	private EditalDisciplinaDAO editalDisciplinaDAO = new EditalDisciplinaDAO();
	
	private List<Edital> editaisPorCentro;
	
	@ManagedProperty("#{disciplinaBean}")
    private DisciplinaBean disciplinaBean;
	
	public EditalDisciplina getEditalDisciplina() {
		return editalDisciplina;
	}

	public void setEditalDisciplina(EditalDisciplina editalDisciplina) {
		this.editalDisciplina = editalDisciplina;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
		System.out.println("ta indo"+edital);
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
    		System.out.println(edital);
    		
    		editalDisciplina.setVagas(disciplina.getVagas());
    		editalDisciplina.setDisciplina(disciplina);
    		editalDisciplina.setEdital(edital);
			editalDisciplinaDAO.inserirEditalDisciplina(editalDisciplina);
			System.out.println("entrou");
			GeradorEdital g = new GeradorEdital(edital, editalDisciplina, disciplina);
			System.out.println(g);
			beanArray[count] = g;
			count++;
		}
    	
    	System.out.println("passou daqui tb");
    	FacesMessage msg = new FacesMessage("Sucesso!", "Edital criadoo!");
    	FacesContext.getCurrentInstance().addMessage(null, msg);  	
    	System.out.println("aqui tb");
    	imprimirRelatorio(beanArray);
    	System.out.println(beanArray.length);	
    }
    
    
    private void limparCampos() {
    	edital = new Edital();
    	centro = new Centro();
    	editalDisciplina = new EditalDisciplina();
    	lista = new ArrayList<Edital>();
    	listaFiltro = new ArrayList<Edital>();
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
		return periodo;
	}
	
	public List<Edital> listaPorCentro(Centro c){
		EditalDAO dao = new EditalDAO();
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

    	System.out.println("dentro do relatorio");
        HashMap parametros = new HashMap();
        parametros.put("ID_EDITAL", edital.getIdEdital());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println("Passou do context");
        try {
        	
           JRBeanArrayDataSource arrayDs = new JRBeanArrayDataSource(g, false);
           System.out.println("1");
           String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
           String caminhoReports = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports");
           
           JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoWebInf+"\\reports\\Edital.jasper", parametros, arrayDs);
           System.out.println("2");
           System.out.println(edital);
           	String caminhoFinal = "\\edital"+""+edital.getCentro().getNome();
           	
           	System.out.println(caminhoReports+caminhoFinal);
           	
            File pdf = new File(caminhoReports+caminhoFinal);
            pdf.createNewFile();
            FileOutputStream arquivo = new FileOutputStream(pdf);
            System.out.println(pdf.getAbsolutePath());
            
            JasperExportManager.exportReportToPdfStream(impressoraJasper, arquivo);
            
            edital.setSrcPDF("//reports/"+caminhoFinal);
            editalDAO.atualizarEdital(edital);
            
            arquivo.flush();
            arquivo.close();
            
            
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    
    private StreamedContent file;
    
//    public EditalBean() throws FileNotFoundException {        
//       InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/WEB-INF/reports/relatorio2.pdf");
//        file = new DefaultStreamedContent(stream, "application/pdf", "relatorio2.pdf");
//    	
//    }

    
    public void setFile(StreamedContent file) {
		this.file = file;
	}
    
    public StreamedContent getFile() throws FileNotFoundException {
    	
    	String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
    	InputStream stream = new FileInputStream(caminhoWebInf+editalSelecionado.getSrcPDF());  
    	//InputStream stream = new FileInputStream("D:\\Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\monitorias\\WEB-INF\\reports\\relatorio2.pdf");  
        file = new DefaultStreamedContent(stream, "application/pdf", "relatorio2.pdf");  
  
        return file;  
    } 
}
    
