package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.primefaces.event.SelectEvent;

@Entity
public class Edital implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEdital;
	
	@Column(nullable=false)
	private String titulo;
	
	@Temporal(TemporalType.DATE)
	private Date dataInscricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataFimInscricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataResultado;
	
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column(nullable=false)
	private int totalVagas;
	
	@Column
	private String srcPDF;
	
	@Column
	private int ano;
	
	@Column
	private int periodo;
	
	/* RELACIONAMENTOS */
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idCentro", name="fkCentro")
	private Centro centro;
	
	@OneToMany(mappedBy="edital")
	private List<Inscricao> inscricoes;
	
	@OneToMany(mappedBy="edital")
	private List<Adendo> adendos;

	@OneToMany(mappedBy="edital", fetch=FetchType.EAGER)
	private Collection<EditalDisciplina> editaisDisciplinas;
	
//	@OneToMany(mappedBy="edital")
//	private Collection<EditalProfessor> editaisProfessores;
//	
	/* GETTERS AND SETTERS */
	
	public List<Adendo> getAdendos() {
		return adendos;
	}
	
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Date getDataResultado() {
		return dataResultado;
	}

	public void setDataResultado(Date dataResultado) {
		this.dataResultado = dataResultado;
	}

	public int getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		this.totalVagas = totalVagas;
	}

	public String getSrcPDF() {
		return srcPDF;
	}

	public void setSrcPDF(String srcPDF) {
		this.srcPDF = srcPDF;
	}

	public Collection<EditalDisciplina> getEditaisDisciplinas() {
		return editaisDisciplinas;
	}

	public void setEditaisDisciplinas(
			Collection<EditalDisciplina> editaisDisciplinas) {
		this.editaisDisciplinas = editaisDisciplinas;
	}

//	public Collection<EditalProfessor> getEditaisProfessores() {
//		return editaisProfessores;
//	}
//
//	public void setEditaisProfessores(Collection<EditalProfessor> editaisProfessores) {
//		this.editaisProfessores = editaisProfessores;
//	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public void setAdendos(List<Adendo> adendos) {
		this.adendos = adendos;
	}

	public Date getDataFimInscricao() {
		return dataFimInscricao;
	}

	public void setDataFimInscricao(Date dataFimInscricao) {
		this.dataFimInscricao = dataFimInscricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@Override
	public String toString() {
		return "Edital [idEdital=" + idEdital + ", titulo=" + titulo
				+ ", dataInscricao=" + dataInscricao + ", dataResultado="
				+ dataResultado + ", totalVagas=" + totalVagas + ", srcPDF="
				+ srcPDF +" "+centro+"]";
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	public void aoEscolherData(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data selecionada", format.format(event.getObject())));
    }
	
}
