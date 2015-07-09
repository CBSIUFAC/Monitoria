package managedBean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.servlet.ServletContext;
import javax.swing.JSpinner.ListEditor;

import org.primefaces.event.RowEditEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import DAO.CentroDAO;
import DAO.DisciplinaDAO;
import DAO.EditalDAO;
import DAO.EditalDisciplinaDAO;
import DAO.InscricaoDAO;
import DAO.UsuarioDAO;
import entity.Aluno;
import entity.Centro;
import entity.Disciplina;
import entity.Edital;
import entity.EditalDisciplina;
import entity.Inscricao;
import entity.Usuario;

@ManagedBean(name="inscricaoBean")
@SessionScoped

public class InscricaoBean {

	private Inscricao inscricao;
	private Inscricao inscricaoTemp = new Inscricao();
	private InscricaoDAO inscricaoDAO = new InscricaoDAO();
	private EditalDAO editalDAO = new EditalDAO();
	private CentroDAO centroDAO = new CentroDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private EditalDisciplinaDAO editalDisciplinaDAO = new EditalDisciplinaDAO();
	private List<Inscricao> lista;
	private List<Inscricao> listaPorUsuario;
	private List<Edital> listaEditalPorCentro;
	private List<Disciplina> listaDisciplinaPorEditalCentro;
	private List<Inscricao> listaFiltro;
	private int[] listaDeStatus = {0,1,2,3,4};
	private Edital edital;
	private Aluno aluno;
	private Disciplina disciplina;
	private Centro centro;
	private Centro centroSelecionado;
	private Edital editalSelecionado;
	private Disciplina disciplinaSelecionada;
	private Integer statusSelecionado = null;

	public Inscricao getInscricao() {
		if (inscricao == null)
			inscricao = new Inscricao();
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public InscricaoDAO getDisciplinaDAO() {
		return inscricaoDAO;
	}

	public void setDisciplinaDAO(InscricaoDAO disciplinaDAO) {
		this.inscricaoDAO = disciplinaDAO;
	}

	public List<Inscricao> getLista() {
		if (lista == null){
			lista = inscricaoDAO.getListaInscricao();
		}
		return lista;
	}

	public void setLista(List<Inscricao> lista) {
		this.lista = lista;
	}

	public List<Inscricao> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(List<Inscricao> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public InscricaoDAO getInscricaoDAO() {
		return inscricaoDAO;
	}

	public void setInscricaoDAO(InscricaoDAO inscricaoDAO) {
		this.inscricaoDAO = inscricaoDAO;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Centro getCentro() {
		if(centro == null)
			return new Centro();
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	//Injeção de um bean
	@ManagedProperty("#{usuarioFace}")
	private UsuarioFace usuarioFace;

	public String inserirInscricao(List<EditalDisciplina> ds) {
		inscricao = new Inscricao();

		if (ds != null && ds.size()>0){

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


			inscricao.setAluno(usu.getAluno());
			inscricao.setDataInscricao(new Date());
			inscricao.setEdital(ds.get(0).getEdital());

			boolean erro=false;
			String disciplinasNaoInscritas = "";
			for (EditalDisciplina ed : ds) {
				inscricao.setDisciplina(ed.getDisciplina());
				inscricao.setStatus(0);
				erro = inscricaoDAO.estaInscrito(inscricao); 
				if (!erro){
					inscricaoDAO.inserirInscricao(inscricao);
				}
				else
					disciplinasNaoInscritas += ed.getDisciplina().getNomeDisciplina()+"\n";
			}

			FacesContext fContext = FacesContext.getCurrentInstance();

			if (!disciplinasNaoInscritas.equals("")){
				fContext.addMessage(null, new FacesMessage("Você já estava inscrito nas disciplinas: "+disciplinasNaoInscritas, "X y z") );
			}
			else
				fContext.addMessage(null, new FacesMessage("Inscrição realizada com sucesso! \n No menu clique em Inscrição e depois acompanhar, para visualizar suas inscrições. ", "X y z") );
		}else{
			FacesContext fContext = FacesContext.getCurrentInstance();
			fContext.addMessage(null, new FacesMessage("Escolha pelo menos uma disciplina para poder se inscrever.", "Erro.") );
		}

		listaPorUsuario = null;
		return "acompanharInscricoes";
	}

	public void editaStatus(ValueChangeEvent vce) {
		try{
			statusSelecionado = Integer.parseInt((String) vce.getNewValue());
		}catch(Exception e){
			statusSelecionado = null;
		}
	}

	public UsuarioFace getUsuarioFace() {
		return usuarioFace;
	}

	public void setUsuarioFace(UsuarioFace usuarioFace) {
		this.usuarioFace = usuarioFace;
	}

	public List<Inscricao> getListaPorUsuario() {
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();

		SecurityContext context = SecurityContextHolder.getContext();  
		if(context instanceof SecurityContext)  
		{  
			Authentication authentication = context.getAuthentication();  
			if(authentication instanceof Authentication){;
			String aux = ((User)authentication.getPrincipal()).getUsername();
			usu = usuDAO.getUsuario(aux);

			}
		}

		if (usu.getTipoUsuario().equals("admin")){
			return inscricaoDAO.getListaInscricao();
		}
		listaPorUsuario = inscricaoDAO.getListaPorUsuario(usu);
		return listaPorUsuario;
	}

	public void setListaPorUsuario(List<Inscricao> listaPorUsuario) {
		this.listaPorUsuario = listaPorUsuario;
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

	public Centro getCentroSelecionado() {
		return centroSelecionado;
	}

	public void setCentroSelecionado(Centro centroSelecionado) {
		this.centroSelecionado = centroSelecionado;
	}

	public Edital getEditalSelecionado() {
		return editalSelecionado;
	}

	public void setEditalSelecionado(Edital editalSelecionado) {
		this.editalSelecionado = editalSelecionado;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada; 
	}

	public List<Edital> getListaEditalPorCentro() {
		listaEditalPorCentro = editalDAO.getListaEdital(centroSelecionado);
		return listaEditalPorCentro;
	}

	public void setListaEditalPorCentro(List<Edital> listaEditalPorCentro) {
		this.listaEditalPorCentro = listaEditalPorCentro;
	}

	public void aoSelecionarCentro(){
		listaEditalPorCentro = editalDAO.getListaEdital(centroSelecionado);
	}

	public void loadEditais(){
		listaEditalPorCentro = editalDAO.getListaEdital(centroSelecionado);
		for (Edital edital : listaEditalPorCentro) {
			List<EditalDisciplina> eds = editalDisciplinaDAO.getListaPorEdital(edital);

			for (EditalDisciplina editalDisciplina : eds) {
				listaDisciplinaPorEditalCentro.add(editalDisciplina.getDisciplina());
			}
		}
	}

	public void loadDisciplinas(){
		List<EditalDisciplina> eds = editalDisciplinaDAO.getListaPorEdital(editalSelecionado);

		for (EditalDisciplina editalDisciplina : eds) {
			listaDisciplinaPorEditalCentro.add(editalDisciplina.getDisciplina());
		}
	}


	public List<Disciplina> getListaDisciplinaPorEditalCentro() {
		List<EditalDisciplina> eds = editalDisciplinaDAO.getListaPorEdital(editalSelecionado);

		for (EditalDisciplina editalDisciplina : eds) {
			listaDisciplinaPorEditalCentro.add(editalDisciplina.getDisciplina());
		}
		return listaDisciplinaPorEditalCentro;
	}

	public void setListaDisciplinaPorEditalCentro(
			List<Disciplina> listaDisciplinaPorEditalCentro) {
		this.listaDisciplinaPorEditalCentro = listaDisciplinaPorEditalCentro;
	}

	public Inscricao getInscricaoTemp() {
		return inscricaoTemp;
	}

	public void setInscricaoTemp(Inscricao inscricaoTemp) {
		this.inscricaoTemp = inscricaoTemp;
	}

	public EditalDAO getEditalDAO() {
		return editalDAO;
	}

	public void setEditalDAO(EditalDAO editalDAO) {
		this.editalDAO = editalDAO;
	}

	public CentroDAO getCentroDAO() {
		return centroDAO;
	}

	public void setCentroDAO(CentroDAO centroDAO) {
		this.centroDAO = centroDAO;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public int[] getListaDeStatus() {
		return listaDeStatus;
	}

	public void setListaDeStatus(int[] listaDeStatus) {
		this.listaDeStatus = listaDeStatus;
	}
	
	public String converteStatus(int i){
		
		switch (i){
			case 0:
				return "Solicitada";
			case 1:
				return "Deferida";
			case 2:
				return "Indeferida";
			case 3:
				return "Monitor Ativo";
			case 4:
				return "Monitor Desativado";
			default:
				return "status inválido";
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		Inscricao i = (Inscricao) event.getObject();
		inscricaoDAO.atualizarInscricao(i);
        FacesMessage msg = new FacesMessage("Inscrição editada", i.getIdInscricao()+"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Inscricao) event.getObject()).getIdInscricao()+"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void atualizaInscricoes(){
    	if (statusSelecionado == null){
    		FacesMessage msg = new FacesMessage("Erro:", "Escolha um status válido.");
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}else
    		if (listaFiltro.size()<1){
    			FacesMessage msg = new FacesMessage("Erro:", "Escolha pelo menos uma inscrição.");
    			FacesContext.getCurrentInstance().addMessage(null, msg);
    		}else{
    			for (int i=0; i<listaFiltro.size(); i++){
    				listaFiltro.get(i).setStatus(statusSelecionado);
    				inscricaoDAO.atualizarInscricao(listaFiltro.get(i));
    			}
    			FacesMessage msg = new FacesMessage("Sucesso:",(listaFiltro.size()>1?"Foram modificadas ":"Foi modificada ")+listaFiltro.size()+" "+(listaFiltro.size()>1?"inscrições.":"inscrição."));
    			FacesContext.getCurrentInstance().addMessage(null, msg);
    		}
    }

	public int getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(int statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}
}
